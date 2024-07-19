package com.se1863.networkcompany.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.se1863.networkcompany.entity.Option;
import com.se1863.networkcompany.entity.Service;
import com.se1863.networkcompany.exception.DuplicateInputException;
import com.se1863.networkcompany.exception.IllegalPriceValueException;
import com.se1863.networkcompany.service.ClientService;
import com.se1863.networkcompany.service.IPattern;
import com.se1863.networkcompany.service.OptionService;
import com.se1863.networkcompany.service.ServiceService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class ManageServiceController implements IPattern {

    ServiceService serviceService;
    OptionService optionService;
    ClientService clientService;

    @GetMapping("/view-service")
    public String viewService(Model model, @RequestParam(required = false) String searchedService) {
        List<Service> services = serviceService.findAllOrderByStatus();
        Map<String, Integer> serviceAndNumberOfRegister = serviceService.findNumberOfRegisters(services);
        model.addAttribute("serviceList", services);
        model.addAttribute("serviceAndNumberOfRegister", serviceAndNumberOfRegister);
        return "admin/view-service";
    }

    @PostMapping("/search-service")
    public String searchServiceHandler(@RequestParam String searchInput, RedirectAttributes redirectAttributes) {
        Service service = serviceService.findById(searchInput);
        if (service != null) {
            Integer numberOfRegister = serviceService.findNumberOfRegister(service.getServiceId());
            List<Option> options = optionService.findAllOptionByServiceId(service.getServiceId());
            redirectAttributes.addFlashAttribute("searchedServiceOptions", options);
            redirectAttributes.addFlashAttribute("numberOfRegister", numberOfRegister);
            redirectAttributes.addAttribute("searchedService", service.getServiceId());
        } else {
            redirectAttributes.addAttribute("searchNotFound", "true");
        }
        return "redirect:/admin/view-service";
    }

    // CREATE SERVICE PAGE
    @GetMapping("/create-service")
    public String createService(Model model) {
        List<Integer> monthOptions = Arrays.asList(6, 12, 24);
        model.addAttribute("monthOptions", monthOptions);
        return "admin/create-service";
    }

    @PostMapping("/enter-option")
    public String enterOptionsHandler(@RequestParam Map<String, String> reqParams,
            RedirectAttributes redirectAttributes) {

        //validate params
        if (reqParams.get("serviceName").isBlank() || reqParams.get("description").isBlank()) {
            redirectAttributes.addFlashAttribute("status", FAILED);
            redirectAttributes.addFlashAttribute("message", "Please enter Name and Description.");
            for (String key : reqParams.keySet()) {
                if (key.equals("description") || key.equals("serviceName"))
                    redirectAttributes.addFlashAttribute(key, reqParams.get(key));
            }
            return "redirect:/admin/create-service";
        }

        //validate duplication of service name
        try {
            serviceService.checkDuplicatedService(reqParams.get("serviceName").trim());
        } catch (DuplicateInputException e) {
            redirectAttributes.addFlashAttribute("status", FAILED);
            redirectAttributes.addFlashAttribute("message", e.getLocalizedMessage());
            for (String key : reqParams.keySet()) {
                if (key.equals("description") || key.equals("serviceName"))
                redirectAttributes.addFlashAttribute(key, reqParams.get(key));
            }
            return "redirect:/admin/create-service";
        }
        
        Map<String, String> optionDurations = optionService.extractOptionDurationFromReqAtt(reqParams);
        List<String> chosenDurations = optionService.getChosenDuration(optionDurations);
        optionService.sortDurationList(chosenDurations);
        redirectAttributes.addFlashAttribute("chosenDurations", chosenDurations);

        // send all attributes to next req
        reqParams.keySet().stream()
                .forEach(key -> {
                    redirectAttributes.addFlashAttribute(key, reqParams.get(key));
                });
        return "redirect:/admin/create-service";
    }

    @PostMapping("/create-service")
    public String createServiceHandler(@RequestParam Map<String, String> reqParams,
            RedirectAttributes redirectAttributes) {
        try {
            Map<String, String> optionPrices = optionService.extractOptionPriceFromReqAtt(reqParams);
            Service newService = optionService.createServiceWithNameAndDescription(reqParams);
            Set<Option> newOptions = optionService.createNewOption(optionPrices, newService);
            optionService.saveNewService(newService); // with null option field
            optionService.appendOptionsToService(newService, newOptions);
            optionService.saveNewOptions(newOptions);
            optionService.saveNewService(newService);
        } catch (IllegalPriceValueException e) {
            redirectAttributes.addFlashAttribute("status", FAILED);
            redirectAttributes.addFlashAttribute("message", e.getLocalizedMessage());
            // add flash attribute to keep the description and service name stay at
            // refreshed page
            for (String key : reqParams.keySet()) {
                if (key.equals("description") || key.equals("serviceName"))
                    redirectAttributes.addFlashAttribute(key, reqParams.get(key));
            }
            return "redirect:/admin/create-service";
        }
        redirectAttributes.addFlashAttribute("createStatus", SUCCESS);
        redirectAttributes.addFlashAttribute("message", "Created service " + reqParams.get("serviceName") + " successfully.");
        return "redirect:/admin/view-service";
    }

    // UPDATE SERVICE PAGE
    @PostMapping("/update-service")
    public String updateServiceHandler(@RequestParam Map<String, String> reqParams,
            RedirectAttributes redirectAttributes) {
        String serviceId = reqParams.get("serviceID");
        String serviceName = reqParams.get("serviceName");
        String description = reqParams.get("description");
        Service service = serviceService.findById(serviceId);
        boolean hasChange = false;
        hasChange = serviceService.updateServiceName(service, serviceName) || hasChange == true;
        hasChange = serviceService.updateServiceDescription(service, description) || hasChange == true;
        hasChange =serviceService.updateAllOptionStatus(reqParams) || hasChange == true;
        serviceService.save(service);
        if (hasChange)
        redirectAttributes.addFlashAttribute("updatedService", serviceId);
        redirectAttributes.addFlashAttribute("updateStatus", hasChange ? SUCCESS : FAILED);
        return "redirect:/admin/view-service";
    }

    @PostMapping("/update-option")
    public String updateOptionHandler(@RequestParam Map<String, String> reqParams,
            RedirectAttributes redirectAttributes) {
        String serviceId = reqParams.get("searchInput");
        Service currService = serviceService.findById(serviceId);
        Map<String, String> changedPriceOption;
        try {
            Map<String, String> optionPriceMap = optionService.extractOptionPriceMap(reqParams);
            changedPriceOption = optionService.getChangedPrice(optionPriceMap);
            List<Option> optionsWithNewPrice;
            optionsWithNewPrice = optionService.createOptionWithNewPrice(changedPriceOption);
            optionService.setReplacedStatusForOptions(optionsWithNewPrice, currService);
            optionService.saveOptionsWithNewPrice(optionsWithNewPrice, currService);
        } catch (IllegalPriceValueException e) {
            redirectAttributes.addFlashAttribute("updateStatus", FAILED);
            redirectAttributes.addFlashAttribute("message", e.getLocalizedMessage());
            return "redirect:/admin/view-service";
        }
        boolean hasChange = changedPriceOption.size() > 0;
        redirectAttributes.addFlashAttribute("updateStatus", hasChange ? SUCCESS : EMPTY);
        if (hasChange)
            redirectAttributes.addFlashAttribute("updatedService", serviceId);
        return "redirect:/admin/view-service";
    }

    // DISABLE ALL SERVICE
    @GetMapping("/disable-service")
    public String disableService(Model model) {
        List<Service> services = serviceService.findAllOrderByStatus();
        Map<String, Integer> serviceAndNumberOfRegister = serviceService.findNumberOfRegisters(services);
        
        // IF SERVICES HAS BEEN DISABLED -> DISPLAY ENABLE ALL BUTTON
        boolean disabledAll = serviceService.checkAllServiceHasBeenDisabled();
        model.addAttribute("disabledAll", disabledAll);
        model.addAttribute("serviceList", services);
        model.addAttribute("serviceAndNumberOfRegister", serviceAndNumberOfRegister);
        return "admin/disable-service";
    }

    @PostMapping("/disable-service")
    // (serviceId, serviceId)
    public String disableServiceHandler(@RequestParam Map<String, String> reqParams,
            RedirectAttributes redirectAttributes) {

        //DISABLE ALL SERVICES IF NOT FOUND A SPECIFIC SERVICE ID FROM PARAMS
        if (reqParams.size() == 0) {
            serviceService.disableAllService();
            redirectAttributes.addFlashAttribute("status", SUCCESS);
            redirectAttributes.addFlashAttribute("message", "Disabled all services successfully.");
            return "redirect:/admin/disable-service";
        }

        serviceService.disableService(reqParams);
        redirectAttributes.addFlashAttribute("status", SUCCESS);
        redirectAttributes.addFlashAttribute("message",
                "Disabled service " + reqParams.keySet().toString() + " successfully.");
        return "redirect:/admin/disable-service";
    }

    @PostMapping("/enable-service")
    // (serviceId, serviceId)
    public String enableServiceHandler(@RequestParam Map<String, String> reqParams,
            RedirectAttributes redirectAttributes) {
        if(reqParams.size() == 0) {
            serviceService.enableAllService();
            redirectAttributes.addFlashAttribute("status", SUCCESS);
            redirectAttributes.addFlashAttribute("message",
            "Enabled all service successfully.");
            return "redirect:/admin/disable-service";
        }

        serviceService.enableService(reqParams);
        redirectAttributes.addFlashAttribute("status", SUCCESS);
        redirectAttributes.addFlashAttribute("message",
                "Enabled service " + reqParams.keySet().toString() + " successfully.");
        return "redirect:/admin/disable-service";
    }

}
