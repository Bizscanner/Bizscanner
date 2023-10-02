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

function DoubleBarGraph({
  firstGraphData,
  secondGraphData,
  title,
  config,
  firstLabel,
  secondLabel,
}) {
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
        label: firstLabel,
        data: firstGraphData,
        backgroundColor: 'rgba(255, 99, 132, 0.5)',
      },
      {
        label: secondLabel,
        data: secondGraphData,
        backgroundColor: 'rgba(54, 162, 235, 0.5)',
      },
    ],
  };
  return <Bar options={options} data={data} />;
}

export default DoubleBarGraph;
