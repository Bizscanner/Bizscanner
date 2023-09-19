/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './app/**/*.{js,ts,jsx,tsx,mdx}',
    './pages/**/*.{js,ts,jsx,tsx,mdx}',
    './components/**/*.{js,ts,jsx,tsx,mdx}',

    // Or if using `src` directory:
    './src/**/*.{js,ts,jsx,tsx,mdx}',
  ],
  theme: {
    extend: {
      colors: {
        white: '#FFFFFF',
        primary: '#0064FF',
        black: '#000000',
        disabled: '#9CA2AA',
        background: '#F4F6FA',
        outline: '#E5E5E5',
        dim: 'rgba(0, 0, 0, 0.40)',
      },
      borderRadius: {
        small: '10px',
        medium: '20px',
        large: '30px',
      },
    },
  },
  plugins: [],
};
