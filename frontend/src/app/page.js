'use client';
import Button from '@/components/Button';
import Lottie from 'lottie-react';
import Link from 'next/link';
import LandingLottie from '@/assets/lotties/Landing.json';
import { useEffect, useRef } from 'react';

export default function Home() {
  const lottieRef = useRef(null);

  useEffect(() => {
    lottieRef.current.setSpeed(0.7);
  }, []);

  const lottieStyle = {
    position: 'fixed',
    zIndex: -1,
    flexGrow: '1',
    width: '100%',
  };
  return (
    <main className="flex flex-col items-center justify-center grow">
      <Lottie
        autoPlay={true}
        animationData={LandingLottie}
        loop={true}
        style={lottieStyle}
        lottieRef={lottieRef}
      />
      <div className="text-4xl font-bold text-center">
        <p className="mb-8 text-5xl drop-shadow-2xl">
          창업의 모든것 <br />
          비즈 스캐너에서 쉽고 간편하게
        </p>
        <Link href="/scan" className="w-1/2 p-4 text-white bg-primary rounded-small">
          시작하기
        </Link>
      </div>
    </main>
  );
}
