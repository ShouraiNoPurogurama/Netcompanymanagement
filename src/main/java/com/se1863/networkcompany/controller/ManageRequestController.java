package com.se1863.networkcompany.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.se1863.networkcompany.entity.Request;
import com.se1863.networkcompany.entity.Technician;
import com.se1863.networkcompany.exception.BlankInputException;
import com.se1863.networkcompany.service.IPattern;
import com.se1863.networkcompany.service.RequestService;
import com.se1863.networkcompany.service.TechnicianService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class ManageRequestController implements IPattern {

    RequestService requestService;
    TechnicianService techniciannService;

    @GetMapping("/view-request")
    public String viewRequest(Model model, @RequestParam(required = false) Request searched) {
        List<Request> requests = requestService.findAll();
        model.addAttribute("requestList", requests);
        model.addAttribute("searched", searched);
        return "admin/view-request";
    }

    @PostMapping("/search-request")
    public String searchRequestHandler(@RequestParam String requestId, RedirectAttributes redirectAttributes) {
        Request request = requestService.findById(requestId);
        redirectAttributes.addAttribute("searched", request);
        return "redirect:/admin/view-request";
    }

    @PostMapping("/confirm-request")
    public String confirmRequestHandler(@RequestParam String requestId, RedirectAttributes redirectAttributes) {
        requestService.confirmRequest(requestId);
        redirectAttributes.addFlashAttribute("confirmStatus", SUCCESS);
        redirectAttributes.addFlashAttribute("confirmedRequest", requestId);
        redirectAttributes.addFlashAttribute("message", "Confirmed request " + requestId + " successfully.");
        return "redirect:/admin/view-request";
    }

    @PostMapping("/deny-request")
    public String denyRequestHandler(@RequestParam String requestId, @RequestParam String cancellationReason,
            RedirectAttributes redirectAttributes) {
        try {
            requestService.denyRequest(requestId, cancellationReason);
        } catch (BlankInputException e) {
            redirectAttributes.addFlashAttribute("denyStatus", FAILED);
            redirectAttributes.addFlashAttribute("deniedRequest", requestId);
            redirectAttributes.addFlashAttribute("message", e.getLocalizedMessage());
            return "redirect:/admin/view-request";

        }
        redirectAttributes.addFlashAttribute("denyStatus", SUCCESS);
        redirectAttributes.addFlashAttribute("deniedRequest", requestId);
        redirectAttributes.addFlashAttribute("message", "Denied request " + requestId + " successfully.");
        return "redirect:/admin/view-request";
    }

    @GetMapping("/assign-request")
    public String assignRequest(Model model, @RequestParam String requestId) {
        Request request = requestService.findById(requestId);
        List<Technician> technicians = techniciannService.findAllAvailableTechnicians();

        model.addAttribute("technicianList", technicians);
        model.addAttribute("request", request);

        return "admin/assign-request";
    }

    @PostMapping("/assign-request")
    public String assignRequestHandler(@RequestParam String technicianId, @RequestParam String requestId,
            RedirectAttributes redirectAttributes) {
        Technician technician = techniciannService.saveStatus(technicianId);
        Request request = requestService.assignToTechnician(requestId, technician);
        techniciannService.assignNewRequest(request, technician);
        request = requestService.saveRequestProcessingStatus(request);

        redirectAttributes.addFlashAttribute("status", SUCCESS);
        redirectAttributes.addFlashAttribute("message", "Assign request " + requestId + " successfully.");

        return "redirect:/admin/view-request";
    }

}
