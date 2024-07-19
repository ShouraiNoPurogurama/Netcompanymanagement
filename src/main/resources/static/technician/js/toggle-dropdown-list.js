$(document).ready(function () {
    $('.dropdown-toggle').click(function () {
        var tr = $(this).closest('.request-row');
        // Toggle its height between 150px and 0px
        var expanded = $(this).attr('aria-expanded') === 'true';
        var reasonInput = document.getElementById('reasonInput');
        if(reasonInput != null) reasonInput.style.display != 'none' ? 'none' : 'block'
        if (expanded) {
            tr.css('height', '0');
        } else {
            tr.css('height', '150px');
        }
    });
});
