import React from 'react';
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import { Pie } from 'react-chartjs-2';

ChartJS.register(ArcElement, Tooltip, Legend);

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
        label: '# of Votes',
        data: graphData,
        backgroundColor: config.backgroundColor,
        borderColor: config.borderColor,
        borderWidth: 1,
      },
    ],
  };

  return <Pie options={options} data={data} />;
}

export default PieGraph;
