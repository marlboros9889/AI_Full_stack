import React from 'react';
import { useRouter } from 'next/router';

const API_BASE_URL = 'http://localhost:8080';

// 소셜 공급자 선택 후 Spring Boot OAuth2 인증 화면으로 이동한다.
export default function LoginPage() {
  const router = useRouter();
  const socialLogin = (provider) => {
    window.location.href = `${API_BASE_URL}/oauth2/authorization/${provider}`;
  };

  return (
    <main style={{ maxWidth: '420px', margin: '60px auto', textAlign: 'center' }}>
      <h1>소셜 로그인</h1>
      {router.query.error === 'oauth' && (
        <p style={{ color: 'red' }}>로그인에 실패했습니다. 다시 선택해 주세요.</p>
      )}
      <button type="button" onClick={() => socialLogin('google')}>Google 로그인</button>
      <button type="button" onClick={() => socialLogin('kakao')}>Kakao 로그인</button>
      <button type="button" onClick={() => socialLogin('naver')}>Naver 로그인</button>
    </main>
  );
}
