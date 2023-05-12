$(document).ready(function () {
    var chart, res;
    const ctx = document.getElementById('myChart');
    const createDonutChart = () => {
        chart.destroy()
        const data1 = {
            labels: ["match1", "match2", "match3", "match4", "match5"],
            datasets: [
                {
                    label: "TeamA Score",
                    data: [10, 50, 25, 70, 40],
                    backgroundColor: [
                        "#A2C3DB",
                        "#8871A0",
                        "#8AAF22",
                        "#DCB12D",
                        "#3F9F9F"
                    ],
                    borderColor: [
                        "#000000",
                        "#000000",
                        "#000000",
                        "#000000",
                        "#000000"
                    ],
                    borderWidth: [1, 1, 1, 1, 1]
                }
            ]
        };
        const options = {
            responsive: true,
            aspectRatio: 2,
            title: {
                display: true,
                position: "top",
                text: "Doughnut Chart",
                fontSize: 18,
                fontColor: "#111"
            },
            legend: {
                display: true,
                position: "bottom",
                labels: {
                    fontColor: "#333",
                    fontSize: 16
                }
            }
        };
        chart = new Chart(ctx, {
            type: "doughnut",
            data: data1,
            options: options
        });
    }
    const createBarChart = () => {
        if (chart) {
            chart.destroy()
        }
        const log_level_map = res.reduce((acc, val) => {
            acc[val.logger] = acc[val.logger] ?? [];
            acc[val.logger].push(val)
            return acc
        }, {})
        chart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: [...Object.keys(log_level_map)],
                datasets: [{
                    label: 'Number Of Logs Per Service',
                    data: [...Object.keys(log_level_map).map(lg => log_level_map[lg].length)],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        })
    }
    const requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };
    fetch("http://localhost:8085/api/serverlogs", requestOptions)
        .then(response => response.json())
        .then(result => {
            res = result
            createBarChart(result, ctx);
            const about = document.getElementById("about")
            const home = document.getElementById("home")
            about.onclick = createDonutChart
            home.onclick = createBarChart
        }).catch(error => console.log('error', error))

});
