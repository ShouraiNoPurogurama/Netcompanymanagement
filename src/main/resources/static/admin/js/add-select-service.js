var serviceCounter = 1;
var maxSelectNum = document.getElementById('available-service-number').value;
console.log(maxSelectNum);

function addServiceSelect() {

    if (maxSelectNum>1) {
        var serviceList = [];
        var serviceContainer = document.getElementById('service-container');
        var selectElement = document.createElement('select');
        selectElement.className = 'form-control';
        selectElement.setAttribute('aria-label', 'Default select example');
        var serviceName = 'serviceId_' + serviceCounter++;
        selectElement.setAttribute('name', serviceName);
        selectElement.setAttribute('id', 'service');
        selectElement.setAttribute('form', 'fucking-form');

        //create an empty array to store the options
        var options = [];

        //get the select element by id
        var originalSelect = document.getElementById('service');

        //loop through each option in the original select element
        for (var i = 0; i < originalSelect.options.length; i++) {
            //get the value and text of each option and add it to the options array
            var optionValue = originalSelect.options[i].value;
            var optionText = originalSelect.options[i].text;
            options.push({ value: optionValue, text: optionText });
        }

        //iterate over the options array and add them to the serviceList array
        options.forEach(function (option) {
            serviceList.push({ serviceId: option.value, serviceName: option.text });
        });

        //iterate over the serviceList array and add the options to the new select element
        serviceList.forEach(function (service) {
            var option = document.createElement('option');
            option.value = service.serviceId;
            option.text = service.serviceName;
            selectElement.appendChild(option);
        });

        serviceContainer.appendChild(selectElement);
        maxSelectNum--;
    } else {
        show_Err_alert()
    }
}