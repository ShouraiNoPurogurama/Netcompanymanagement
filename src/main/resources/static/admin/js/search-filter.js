// Get the search input element
const searchInput = document.querySelector('input[name="searchInput"]');

// Add an event listener to the search input
searchInput.addEventListener('input', function (event) {
  const keyword = event.target.value.toLowerCase(); // Get the lowercase keyword entered

  // Get all client rows
  const clientRows = document.querySelectorAll('tbody tr');

  // Loop through each client row
  clientRows.forEach(function (row) {
    const phoneNumberCell = row.getElementsByClassName('phone-number');

    const phoneNumber = phoneNumberCell[0].textContent.toLowerCase();

    if (phoneNumber.includes(keyword)) {
      // If it matches, display the row
      row.style.display = 'table-row';
    } else {
      row.style.display = 'none';
    }
  });
});