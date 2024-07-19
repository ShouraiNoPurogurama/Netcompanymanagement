package com.se1863.networkcompany.service;

public interface IPattern {
    //SERVICE STATUS
    final Integer AVAILABLE = 1;
    final Integer UNAVAILABLE = 0;
    final Integer DISABLED = 0;
    final Integer REPLACED = -1;

    //CRUD ACTION STATUS
    final String SUCCESS = "success";
    final String FAILED = "failed";
    final String EMPTY = "empty";

    //CHOOSE WHEN CREATE/UPDATE STATUS
    final String ENABLE = "enable";
    final String DISABLE = "disable";

    //REQUEST STATUS CONST
    final String CREATED = "Created";
    final String PENDING = "Pending";
    final String PROCESSING = "Processing";
    final String COMPLETED = "Completed";
    final String CANCELLED = "Cancelled";

    //For creating new option
    final String reqParamServiceIdPat = "service-status-(?<id>OPT\\d{3})";
    final String reqParamOptionIdPat = "option-status-(?<id>OPT\\d{3})";
    final String reqParamOptionPricePat = "(?<id>OPT(?<number>\\d{3}))-price";
    final String reqParamOptionPriceNumPat = "(?<number>6|12|24)-price";
    final String reqParamOptionDurationPat = "month-(?<number>6|12|24)";

    //For creating new service
    final String optionIdPat = "OPT(?<number>\\d{3})";
    final String serviceIdPat = "SVI(?<number>\\d{3})";
    final String constractIdPat = "CON(?<number>\\d{3})";
    final String requestIdPat = "REQ(?<number>\\d{3})";
    final String transactionIdPat = "TRAN(?<number>\\d{3})";

    final String createServiceIdPat = "serviceId_(?<number>\\d+)";

    //Email service template
    final String blockAccountNoti = "Account Blocked Notification";
    final String ACCOUNT_BLOCKED = "ACCOUNT_BLOCKED";
}
