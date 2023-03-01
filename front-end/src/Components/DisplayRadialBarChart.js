
import React, { Component } from "react";
import ReactApexChart from "react-apexcharts";

class DisplayRadialBarChart extends Component {
  constructor(props) {
    super(props);

    this.state = {
      // N/A (Not Attempted), Attempted, Solved
      series: [
        props.percentUsersNotAttempted,
        props.percentUsersAttempted,
        props.percentUsersSolved
      ],
      options: {
        labels: ["N/A", "Attempted", "Solved"],
        colors: ["#FD7F20", "#0096FF", "#50C878"],
        title: {
          text: `${props.problemName} Activity`,
          align: 'center',
          margin: 10,
          offsetX: 0,
          offsetY: 0,
          floating: false,
          style: {
            fontSize: '14px',
            fontWeight: 'bold',
            fontFamily: "'Google Sans', sans-serif, system-ui",
            color: '#263238'
          },
        },
        chart: {
          width: 380,
          background: "radial-gradient( circle 900px at 60% 40%, rgba(255,255,255,1) 0%, rgba(138,192,216,1) 90% )",
          animations: {
            enabled: true,
            easing: 'easeinout',
            speed: 400,
            animateGradually: {
              enabled: true,
              delay: 150
            },
            dynamicAnimation: {
              enabled: true,
              speed: 350
            }
          }
        },
        plotOptions: {
          total: {
            show: true,
            label: 'Total',
            color: '#373d3f',
            fontSize: '16px',
            fontFamily: undefined,
            fontWeight: 600
          }
        },
        fill: {
          type: "gradient",
          gradient: {
            shade: "dark",
            type: "vertical",
            gradientToColors: ["#f8d568"],
            stops: [25, 100]
          }
        },
        stroke: {
          lineCap: "round"
        },
        legend: {
          show: true,
          showForSingleSeries: false,
          showForNullSeries: true,
          showForZeroSeries: true,
          position: 'bottom',
          horizontalAlign: 'center',
          floating: false,
          fontSize: '14px',
          fontFamily: 'Helvetica, Arial',
          fontWeight: 400,
          formatter: undefined,
          inverseOrder: false,
          width: undefined,
          height: undefined,
          tooltipHoverFormatter: undefined,
          customLegendItems: [],
          offsetX: 0,
          offsetY: 0,
          labels: {
            colors: undefined,
            useSeriesColors: false
          },
          markers: {
            width: 12,
            height: 12,
            strokeWidth: 1,
            strokeColor: '#000',
            fillColors: undefined,
            radius: 12,
            customHTML: undefined,
            onClick: undefined,
            offsetX: 0,
            offsetY: 0,
          },
          itemMargin: {
            horizontal: 7,
            vertical: 0
          },
          onItemClick: {
            toggleDataSeries: true
          },
          onItemHover: {
            highlightDataSeries: true
          },
        }
      }
    };
  }

  render() {
    return (
      <div id="chart">
        <ReactApexChart type="radialBar"
                        series={this.state.series}
                        options={this.state.options} />
      </div>
    );
  }
}

export default DisplayRadialBarChart;
