// pages/signup.js : 일반 회원가입과 소셜 로그인을 제공하는 페이지
import React, { useEffect } from 'react';
import { Button, Col, Divider, Form, Input, Row, Spin, message } from 'antd';
import { useDispatch, useSelector } from 'react-redux';
import { useRouter } from 'next/router';
import { resetUserState, signupRequest } from '../reducers/authReducer';

const API_BASE_URL = 'http://localhost:8080';

export default function SignupPage() {
  const dispatch = useDispatch();
  const router = useRouter();
  const { error, loading, success } = useSelector((state) => state.auth);

  // 1. 일반 회원가입 요청
  const onFinish = (values) => {
    dispatch(signupRequest(values));
  };

  // 2. 회원가입 성공 후 마이페이지로 이동
  useEffect(() => {
    if (!success) return;
    message.success('회원가입이 완료되었습니다.');
    dispatch(resetUserState());
    router.push('/mypage');
  }, [success, dispatch, router]);

  // 3. 공급자별 OAuth2 인증 화면으로 이동
  const socialLogin = (provider) => {
    window.location.href = `${API_BASE_URL}/oauth2/authorization/${provider}`;
  };

  return (
    <Row justify="center">
      <Col xs={22} sm={16} md={10} lg={8}>
        <h1>회원가입</h1>
        {loading && <Spin />}
        {error && <p style={{ color: 'red' }}>{error}</p>}

        <Form layout="vertical" onFinish={onFinish}>
          <Form.Item label="이메일" name="email" rules={[{ required: true, type: 'email', message: '이메일을 입력하세요.' }]}>
            <Input />
          </Form.Item>
          <Form.Item label="비밀번호" name="password" rules={[{ required: true, min: 4, message: '비밀번호는 4자 이상 입력하세요.' }]}>
            <Input.Password />
          </Form.Item>
          <Form.Item label="닉네임" name="nickname" rules={[{ required: true, message: '닉네임을 입력하세요.' }]}>
            <Input />
          </Form.Item>
          <Button block type="primary" htmlType="submit" loading={loading}>일반 회원가입</Button>
        </Form>

        <Divider>또는 소셜 계정으로 시작</Divider>
        <Row gutter={[8, 8]}>
          <Col span={8}><Button block onClick={() => socialLogin('google')}>Google</Button></Col>
          <Col span={8}><Button block onClick={() => socialLogin('kakao')}>Kakao</Button></Col>
          <Col span={8}><Button block onClick={() => socialLogin('naver')}>Naver</Button></Col>
        </Row>
      </Col>
    </Row>
  );
}
