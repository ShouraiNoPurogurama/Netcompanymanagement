//toggle dropdown menu
$(document).ready(function () {
    var reloadTable = document.getElementById('option-table')
    if(reloadTable != null)   window.location.hash = 'options'
    $('.dropdown-toggle').on('click', function () {
      var dropdownMenu = $(this).siblings('#fucking-dropdown-menu')
      dropdownMenu.toggle();
      var table = document.getElementById('fucking-table')
      var dropdown = dropdownMenu[0];
      table.style.height = dropdown.style.display === 'block' ? '190px' : 'auto'
      // table.style.height = '190px'
    });
  });