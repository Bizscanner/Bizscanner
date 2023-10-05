import React from 'react';
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import ChartDataLabels from 'chartjs-plugin-datalabels';
import { Pie } from 'react-chartjs-2';

ChartJS.register(ArcElement, Tooltip, Legend, ChartDataLabels);

function PieGraph({ graphData, title, config }) {
  const options = {
    responsive: true,
    plugins: {
      legend: {
        position: 'top',
      },
      title: {
        display: true,
        text: `${title}`,
      },
    },
  };

  const data = {
    labels: config.labels,
    datasets: [
      {
        data: graphData,
        backgroundColor: config.backgroundColor,
        borderColor: config.borderColor,
        borderWidth: 1,
        datalabels: {
          labels: {
            name: {
              align: 'center',
              color: 'black',
              font: { size: 16, weight: 'bold', color: 'black' },
              formatter: function (value, ctx) {
                return ctx.chart.data.labels[ctx.dataIndex];
              },
            },
          },
        },
      },
    ],
  };

  return <Pie options={options} data={data} />;
}

export default PieGraph;
