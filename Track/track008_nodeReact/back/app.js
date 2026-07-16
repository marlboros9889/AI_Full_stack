// router
const express = require('express');
const session = require('express-session');
const passport = require('passport');
// 환경변수 .env
const dotenv = require('dotenv');
const morgan = require('morgan');
const cors = require('cors');
const fs = require('fs');
const https = require('https');

// swaggerUi (api 문서 자동화)
const swaggerUi = require('swagger-ui-express');
const swaggerJsdoc = require('swagger-jsdoc');
// 라우터 연결
const userRouter = require('./routes/user');

// 설정 초기화
dotenv.config();
const app = express();

// 미들웨어 설정
app.use(express.json()); // 수정: json() 호출 형식으로 변경
app.use(express.urlencoded({ extended: true }));
app.use(morgan('dev'));
app.use(cors({
  origin: 'http://localhost:3000',
  credentials: true
}));

// 세션 설정
app.use(session({
  secret: process.env.COOKIE_SECRET,
  resave: false,
  saveUninitialized: false,
  cookie: { httpOnly: true, secure: false }
}));

// passport 초기화
app.use(passport.initialize());
app.use(passport.session());

const passportConfig = require('./passport');
passportConfig();

// Swagger 설정
const swaggerOptions = {
  definition: {
    openapi: '3.0.0',
    info: {
      title: 'User API',
      version: '1.0.0',
      description: '회원가입, 로그인, 사용자 조회/수정/삭제 API 문서',
    },
  },
  apis: ['./routes/*.js'],
};
const swaggerSpecs = swaggerJsdoc(swaggerOptions);

// 라우팅
app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(swaggerSpecs));
app.use('/user', userRouter);
app.get('/', (req, res) => { res.send('hello express'); });

// 에러 핸들링
app.use((err, req, res, next) => {
  console.error('서버 에러:', err.stack);
  res.status(500).json({ message: '서버 내부 오류 발생' });
});

// 서버 실행
const PORT = process.env.PORT || 3065;
if (fs.existsSync('./cert/server.key') && fs.existsSync('./cert/server.crt')) {
  const options = {
    key: fs.readFileSync('./cert/server.key'),
    cert: fs.readFileSync('./cert/server.crt'),
  };
  https.createServer(options, app).listen(PORT, () => {
    console.log(`✅ HTTPS 서버 실행 중! https://localhost:${PORT}`);
    console.log(`✅ Swagger UI: https://localhost:${PORT}/api-docs`);
  });
} else {
  app.listen(PORT, () => {
    console.log(`✅ HTTP 서버 실행 중! http://localhost:${PORT}`);
    console.log(`✅ Swagger UI: http://localhost:${PORT}/api-docs`);
  });
}