import Button from '@/components/Button';
import Link from 'next/link';

export default function Home() {
  return (
    <main className="flex items-center justify-center grow">
      <div className="text-4xl font-bold text-center">
        <p className="mb-8">
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
