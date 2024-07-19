package com.se1863.networkcompany.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.se1863.networkcompany.entity.Option;
import com.se1863.networkcompany.entity.Service;
import com.se1863.networkcompany.exception.IllegalPriceValueException;

public interface OptionService {
    void setReplacedStatusForOptions(List<Option> changedPriceOption, Service service);
    void appendOptionsToService(Service newService, Set<Option> newOptions);
    void saveNewOptions(Set<Option> newOptions);
    void sortDurationList(List<String> chosenDurations);

    boolean saveOptionsWithNewPrice(List<Option> optionsWithNewPrice, Service service);

    String findLastOptionId();
    String findLastServiceId();
    String getOptionIdFromIdNumber(int idNum);
    String getServiceNameFromRequestParams(Map<String, String> reqParams);
    String getDescriptionFromRequestParams(Map<String, String> reqParams);

    Option findById(String id);
    Option save(Option option);
    Option findByDurationAndServiceId(Integer duration, String serviceId);

    List<Option> findAllOptionByServiceId(String serviceId);
    List<Option> createOptionWithNewPrice(Map<String, String> changedPriceOption);
    List<String> getChosenDuration(Map<String, String> optionDuration);

    Set<Option> findBycontractId(String id);
    Set<Option> extractOptionSetFromReqParams(Map<String, String> reqParams);
    Set<Option> createNewOption(Map<String, String> optionPrices, Service service);

    Map<String, String> extractOptionPriceFromReqAtt(Map<String, String> reqParams) throws IllegalPriceValueException;
    Map<String, String> extractOptionStatusFromReqAtt(Map<String, String> reqParams);
    Map<String, String> extractOptionPriceMap(Map<String, String> reqParams) throws IllegalPriceValueException;
    Map<String, String> extractOptionDurationFromReqAtt(Map<String, String> reqParams);
    Map<String, String> getChangedPrice(Map<String, String> optionPriceMap) throws IllegalPriceValueException;
    Map<String, Option> generateServiceIdAndOptionMapFromDuration(List<String> chooseService, Integer duration);

    Service createServiceWithNameAndDescription(Map<String, String> reqParams);
    Service saveNewService(Service newService);
    
}
