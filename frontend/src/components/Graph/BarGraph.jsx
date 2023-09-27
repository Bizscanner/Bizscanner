import React from 'react';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';
import { Bar } from 'react-chartjs-2';

ChartJS.register(CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend);

function BarGraph({ graphData, title, config }) {
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
        label: '점포수',
        data: graphData,
        backgroundColor: 'rgba(255, 99, 132, 0.5)',
      },
    ],
  };
  return <Bar options={options} data={data} />;
}

export default BarGraph;
