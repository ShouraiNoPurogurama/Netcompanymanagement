package com.se1863.networkcompany.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.transaction.TransactionSystemException;

import com.se1863.networkcompany.entity.Option;
import com.se1863.networkcompany.entity.Service;
import com.se1863.networkcompany.exception.DuplicateInputException;
import com.se1863.networkcompany.exception.IllegalPriceValueException;
import com.se1863.networkcompany.repository.OptionRepository;
import com.se1863.networkcompany.repository.ServiceRepository;
import com.se1863.networkcompany.service.IPattern;
import com.se1863.networkcompany.service.OptionService;
import com.se1863.networkcompany.service.Utils;

import lombok.AllArgsConstructor;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class OptionServiceImpl implements IPattern, OptionService {
    OptionRepository optionRepository;
    ServiceRepository serviceRepository;

    @Override
    public Option findById(String id) {
        return optionRepository.findById(id).get();
    }

    @Override
    public Option save(Option option) {
        return optionRepository.save(option);
    }

    @Override
    public Set<Option> findBycontractId(String id) {
        return optionRepository.findBycontractId(id);
    }

    @Override
    public Option findByDurationAndServiceId(Integer duration, String serviceId) {
        return optionRepository.findByDurationAndServiceId(duration, serviceId);
    }

    @Override
    public List<Option> findAllOptionByServiceId(String serviceId) {
        return optionRepository.findAllOptionByServiceId(serviceId);
    }

    @Override
    public String findLastOptionId() {
        return optionRepository.findLastOptionId();
    }

    @Override
    // For updating service
    public String getOptionIdFromIdNumber(int idNum) {
        String idStr = String.valueOf(idNum);
        while (idStr.length() < 3) {
            idStr = "0" + idStr;
        }
        System.out.println(idStr);
        return idStr;
    }

    @Override
    public Map<String, String> extractOptionStatusFromReqAtt(Map<String, String> reqParams) {
        Pattern pattern = Pattern.compile(IPattern.reqParamOptionIdPat);
        Matcher mat;
        Map<String, String> optionStatus = new HashMap<>();
        for (String key : reqParams.keySet()) {
            mat = pattern.matcher(key);
            if (mat.find()) {
                String extractedOptionId = mat.group("id");
                optionStatus.put(extractedOptionId, reqParams.get(key));
            }
        }
        return optionStatus;
    }

    @Override
    // (OptionId, price)
    public Map<String, String> extractOptionPriceMap(Map<String, String> reqParams)
            throws IllegalPriceValueException {
        Map<String, String> result = new HashMap<>();
        reqParams.keySet().stream()
                .filter(key -> key.matches(IPattern.reqParamOptionPricePat))
                .forEach(key -> {
                    Pattern pat = Pattern.compile(reqParamOptionPricePat);
                    Matcher mat = pat.matcher(key);
                    if (mat.find()) {
                        String optionId = mat.group("id");
                        if (reqParams.get(key).isBlank() || Integer.parseInt(reqParams.get(key)) <= 0) {
                            throw new IllegalPriceValueException(optionId);
                        }
                        result.put(optionId, reqParams.get(key));
                    }
                });
        return result;
    }

    @Override
    // (optionId, price)
    public Map<String, String> getChangedPrice(Map<String, String> optionPriceMap) throws IllegalPriceValueException {
        Map<String, String> result = optionPriceMap.keySet().stream()
                .map(key -> findById(key))
                // Price nhap vao tu Map khac price co san trong Option
                .filter(option -> {
                    if (optionPriceMap.get(option.getOptionId()).isEmpty()
                            || Integer.parseInt(optionPriceMap.get(option.getOptionId())) <= 0)
                        throw new IllegalPriceValueException("Price must be a positive number.");
                    return option.getPrice() != Integer.parseInt(optionPriceMap.get(option.getOptionId()));
                })
                .collect(Collectors.toMap(entry -> entry.getOptionId(),
                        entry -> optionPriceMap.get(entry.getOptionId())));
        return result;
    }

    @Override
    public List<Option> createOptionWithNewPrice(Map<String, String> changedPriceOption) {
        List<Option> newOptions = changedPriceOption.keySet().stream()
                .filter(key -> {
                    if (Integer.parseInt(changedPriceOption.get(key)) <= 0)
                        throw new TransactionSystemException("Negative price encountered for option: " + key);
                    return true; // allow the curr stream to be passed to next layer
                })
                .map(key -> new Option(findById(key).getDuration(), Integer.parseInt(changedPriceOption.get(key)), 1))
                .collect(Collectors.toList());
        return newOptions;
    }

    @Override
    public void setReplacedStatusForOptions(List<Option> changedPriceOption, Service service) {
        changedPriceOption.stream()
                .forEach(option -> {
                    Option o = findByDurationAndServiceId(option.getDuration(), service.getServiceId());
                    o.setStatus(REPLACED);
                    save(o);
                });
    }

    @Override
    public boolean saveOptionsWithNewPrice(List<Option> optionsWithNewPrice, Service service) {
        int[] idNumber = { 0 };
        String lastOptionId = findLastOptionId();
        Pattern pat = Pattern.compile(IPattern.optionIdPat);
        Matcher mat = pat.matcher(lastOptionId);
        if (mat.find()) {
            idNumber[0] = Integer.parseInt(mat.group("number"));
        }
        optionsWithNewPrice.stream()
                .forEach(option -> {
                    String id = "OPT" + getOptionIdFromIdNumber(++idNumber[0]);
                    option.setOptionId(id);
                    option.setService(service);
                    save(option);
                });
        return true;
    }

    @Override
    // (month-xx , enable|disable)
    public Map<String, String> extractOptionDurationFromReqAtt(Map<String, String> reqParams) {
        Pattern pattern = Pattern.compile(IPattern.reqParamOptionDurationPat);
        Matcher mat;
        Map<String, String> optionDuration = new HashMap<>();
        for (String key : reqParams.keySet()) {
            mat = pattern.matcher(key);
            if (mat.find()) {
                String extractedDurationNum = mat.group("number");
                optionDuration.put(extractedDurationNum, reqParams.get(key));
            }
        }
        return optionDuration;
    }

    @Override
    public List<String> getChosenDuration(Map<String, String> optionDuration) {
        List<String> result = optionDuration.keySet().stream()
                .filter(key -> optionDuration.get(key).equals(ENABLE))
                .collect(Collectors.toList());
        return result;
    }

    public void sortDurationList(List<String> chosenDurations) {
        Collections.sort(chosenDurations, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.parseInt(o1) - Integer.parseInt(o2);
            }
        });
    }

    @Override
    // (duration, price)
    public Map<String, String> extractOptionPriceFromReqAtt(Map<String, String> reqParams)
            throws IllegalPriceValueException {
        Pattern pattern = Pattern.compile(IPattern.reqParamOptionPriceNumPat);
        Matcher mat;
        Map<String, String> optionPrice = new HashMap<>();
        for (String key : reqParams.keySet()) {
            mat = pattern.matcher(key);
            if (mat.find()) {
                String extractedDurationNum = mat.group("number");
                if (Integer.parseInt(reqParams.get(key)) <= 0)
                    throw new IllegalPriceValueException(extractedDurationNum + " months");
                //ex: put (24, 1200000)
                optionPrice.put(extractedDurationNum, reqParams.get(key));
            }
        }
        return optionPrice;
    }

    @Override
    // (serviceId, optionId)
    public Set<Option> extractOptionSetFromReqParams(Map<String, String> reqParams) {
        return reqParams.keySet().stream()
                .filter(serviceId -> reqParams.get(serviceId).matches(optionIdPat))
                .map(serviceId -> findById(reqParams.get(serviceId)))
                .collect(Collectors.toSet());
    }

    @Override
    public String getServiceNameFromRequestParams(Map<String, String> reqParams) throws IllegalArgumentException {
        String serviceName = reqParams.get("serviceName");
        if (serviceName.isBlank())
            throw new IllegalArgumentException("Blank service name");
        return serviceName;
    }

    @Override
    public String getDescriptionFromRequestParams(Map<String, String> reqParams) throws IllegalArgumentException {
        String description = reqParams.get("description");
        if (description.isBlank())
            throw new IllegalArgumentException("Blank description");
        return description;
    }

    @Override
    public String findLastServiceId() {
        return serviceRepository.findLastServiceId();
    }

    @Override
    // Map(duration, price)
    public Set<Option> createNewOption(Map<String, String> optionPrices, Service service) {
        String lastOptionId = findLastOptionId();
        int[] idNumber = { 0 };
        Pattern pat = Pattern.compile(IPattern.optionIdPat);
        Matcher mat = pat.matcher(lastOptionId);

        if (mat.find()) {
            idNumber[0] = Integer.parseInt(mat.group("number"));
        }

        Set<Option> newOptions = optionPrices.keySet().stream()
                .filter(key -> {
                    if (Integer.parseInt(optionPrices.get(key)) <= 0)
                        throw new TransactionSystemException("Negative price encountered for option: " + key);
                    return true; // allow the curr stream to be passed to next layer
                })
                .map(duration -> new Option(
                        "OPT" + getOptionIdFromIdNumber(++idNumber[0]),
                        Integer.parseInt(duration),
                        Integer.parseInt(optionPrices.get(duration)),
                        AVAILABLE,
                        service,
                        null))
                .collect(Collectors.toSet());
        return newOptions;
    }

    @Override
    public Service createServiceWithNameAndDescription(Map<String, String> reqParams) throws IllegalArgumentException {
        String serviceName = getServiceNameFromRequestParams(reqParams);
        String description = getDescriptionFromRequestParams(reqParams);
        String lastServiceId = findLastServiceId();
        int serviceIdNum = 0;
        String newServiceIdNumStr = null;
        Pattern pat = Pattern.compile(serviceIdPat);
        Matcher mat = pat.matcher(lastServiceId);
        if (mat.find()) {
            serviceIdNum = Integer.parseInt(mat.group("number"));
            newServiceIdNumStr = String.valueOf(++serviceIdNum);
        }

        while (newServiceIdNumStr.length() < 3) {
            newServiceIdNumStr = "0" + newServiceIdNumStr;
        }
        newServiceIdNumStr = "SVI" + newServiceIdNumStr;
        Service service = new Service(newServiceIdNumStr, serviceName, description, AVAILABLE, null, null);
        return service;
    }

    @Override
    public void appendOptionsToService(Service newService, Set<Option> newOptions) {
        newService.setServiceOptions(newOptions);
    }

    @Override
    public void saveNewOptions(Set<Option> newOptions) {
        newOptions.stream()
                .forEach(option -> {
                    save(option);
                });
    }

    @Override
    public Service saveNewService(Service newService) {
        return serviceRepository.save(newService);
    }

    @Override
    public Map<String, Option> generateServiceIdAndOptionMapFromDuration(List<String> chooseService, Integer duration) {
        if (!Utils.validateDuplicationOfList(chooseService))
            throw new DuplicateInputException("A service must be selected once.");
        Map<String, Option> serviceIdAndOption = chooseService.stream()
                .collect(Collectors.toMap(serviceId -> serviceId,
                        serviceId -> findByDurationAndServiceId(duration, serviceId)));
        return serviceIdAndOption;
    }
}
