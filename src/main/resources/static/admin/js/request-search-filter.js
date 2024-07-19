document.addEventListener('DOMContentLoaded', function() {
    // Get the search input element
    const searchClient = document.querySelector('input[class="form-control form-input searchClient"]');
    console.log(searchClient)
    const secondTable = document.querySelector('#fucking-table');
    console.log(secondTable)
    // Add an event listener to the search input
    searchClient.addEventListener('input', function(event) {
        const keyword = event.target.value.toLowerCase();
        const clientRows = secondTable.querySelectorAll('tbody tr');
        
        clientRows.forEach(function(row) {
            const clientIdCell = row.querySelector('.client-id');

            const clientId = clientIdCell.value.toLowerCase();

            console.log(clientId);

            if (clientId.includes(keyword)) {
                row.style.display = 'table-row';
            } else {
                row.style.display = 'none';
            }
        });
    });

    // Add an event listener to the button
    secondTable.addEventListener('click', function(event) {
        if (event.target && event.target.matches('.choose-client-btn')) {
            event.preventDefault();
            const clientId = event.target.getAttribute('value');
            searchClient.value = clientId;

            const inputEvent = new Event('input');
            searchClient.dispatchEvent(inputEvent);
        }
    });
});
