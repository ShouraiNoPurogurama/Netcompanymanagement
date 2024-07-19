window.onload = function () {
    var dataPoints = [];
    var tableRows = document.querySelectorAll('tbody tr');
    var sum = 0;

    tableRows.forEach(function (row) {
        var y = parseInt(row.querySelector('.service-register-number').innerText);
        sum += y;
    })

    tableRows.forEach(function (row) {
        var label = row.querySelector('.service-name').innerText;
        var y = parseInt(row.querySelector('.service-register-number').innerText);
        dataPoints.push({ label: label, q: y, y: 100 * y / sum });
    });

    var chart = new CanvasJS.Chart("chartContainer", {
        animationEnabled: true,
        backgroundColor: "rgba(0,0,0,0)",
        title: {
            padding: 20,
            text: "Overview For Services Register "
        },
        legend: {
            maxWidth: 700,
            itemWidth: 200,
        },
        data: [{
            type: "pie",
            showInLegend: true,
            legendText: "{label} {q}",
            startAngle: 240,
            yValueFormatString: "##0.00\"%\"",
            indexLabel: "{label} {y}",
            dataPoints: dataPoints
        }],

    });
    chart.render();
}
