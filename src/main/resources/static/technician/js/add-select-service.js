var serviceCounter = 1;

function addServiceSelect() {
    var serviceList = [];
    var serviceContainer = document.getElementById('service-container');
    var selectElement = document.createElement('select');
    selectElement.className = 'form-control';
    selectElement.setAttribute('aria-label', 'Default select example');
    var serviceName = 'serviceId_' + serviceCounter++;
    selectElement.setAttribute('name', serviceName);
    selectElement.setAttribute('id', 'service');
    selectElement.setAttribute('form', 'fucking-form');
    
    // Create an empty array to store the options
    var options = [];

    // Get the select element by its ID
    var originalSelect = document.getElementById('service');

    // Loop through each option in the original select element
    for (var i = 0; i < originalSelect.options.length; i++) {
        // Get the value and text of each option and add it to the options array
        var optionValue = originalSelect.options[i].value;
        var optionText = originalSelect.options[i].text;
        options.push({ value: optionValue, text: optionText });
    }

    // Iterate over the options array and add them to the serviceList array
    options.forEach(function(option) {
        serviceList.push({ serviceId: option.value, serviceName: option.text });
    });

    // Iterate over the serviceList array and add the options to the new select element
    serviceList.forEach(function(service) {
        var option = document.createElement('option');
        option.value = service.serviceId;
        option.text = service.serviceName;
        selectElement.appendChild(option);
    });

    serviceContainer.appendChild(selectElement);
}