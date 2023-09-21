import './globals.css';
import localFont from 'next/font/local';
import { ProviderWrapper } from '@/redux/ProviderWrapper';
import Header from './Header';

const tMoney = localFont({
  src: [
    {
      path: '../../public/fonts/TmoneyRoundWindRegular.ttf',
      weight: '400',
      style: 'normal',
    },
    {
      path: '../../public/fonts/TmoneyRoundWindExtraBold.ttf',
      weight: '700',
      style: 'normal',
    },
  ],
});

export const metadata = {
  title: 'Create Next App',
  description: 'Generated by create next app',
};

export default function RootLayout({ children }) {
  return (
    <html lang="ko" className={tMoney.className}>
      <body className="flex flex-col min-h-screen">
        <Header></Header>
        <ProviderWrapper>{children}</ProviderWrapper>
      </body>
    </html>
  );
}
