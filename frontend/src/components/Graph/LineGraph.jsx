import React from 'react';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';
import { Line } from 'react-chartjs-2';

ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend);

function LineGraph({ graphData, title, config, dataLabel }) {
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
        label: dataLabel,
        data: graphData,
        borderColor: 'rgb(255, 99, 132)',
        backgroundColor: 'rgba(255, 99, 132, 0.5)',
        datalabels: {
          display: false,
        },
      },
    ],
  };
  return <Line options={options} data={data} />;
}

export default LineGraph;
