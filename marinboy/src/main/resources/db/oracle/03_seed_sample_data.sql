-- 시술 데이터를 안전하게 다시 만들 수 있도록 샘플 예약 데이터를 먼저 초기화합니다.
DELETE FROM SALON_RESERVATION;
DELETE FROM SALON_CUSTOMER;
DELETE FROM SALON_SERVICE_ITEM;

-- Oracle 모드에서 사용할 정리된 샘플 시술 데이터를 입력합니다.
INSERT INTO SALON_SERVICE_ITEM (ID, NAME, CATEGORY, DURATION_MINUTES, PRICE, DESCRIPTION, IMAGE_URL, ADDITIONAL_IMAGE_URLS, TOP_RANK) VALUES
(1, '시그니처 컷', '컷', 60, 35000, '상담을 포함한 맞춤형 커트 시술입니다.', 'https://images.unsplash.com/photo-1522337360788-8b13dee7a37e?auto=format&fit=crop&w=900&q=80', 'https://images.unsplash.com/photo-1517832606299-7ae9b720a186?auto=format&fit=crop&w=900&q=80', 2);

INSERT INTO SALON_SERVICE_ITEM (ID, NAME, CATEGORY, DURATION_MINUTES, PRICE, DESCRIPTION, IMAGE_URL, ADDITIONAL_IMAGE_URLS, TOP_RANK) VALUES
(2, '빠른 정리 컷', '컷', 30, 18000, '짧은 시간에 앞머리와 라인을 정리하는 시술입니다.', 'https://images.unsplash.com/photo-1595476108010-b4d1f102b1b1?auto=format&fit=crop&w=900&q=80', null, null);

INSERT INTO SALON_SERVICE_ITEM (ID, NAME, CATEGORY, DURATION_MINUTES, PRICE, DESCRIPTION, IMAGE_URL, ADDITIONAL_IMAGE_URLS, TOP_RANK) VALUES
(3, '뿌리 염색', '염색', 90, 70000, '기존 컬러에 맞춰 뿌리 부분을 자연스럽게 정리합니다.', 'https://images.unsplash.com/photo-1562322140-8baeececf3df?auto=format&fit=crop&w=900&q=80', 'https://images.unsplash.com/photo-1582095133179-bfd08e2fc6b3?auto=format&fit=crop&w=900&q=80', null);

INSERT INTO SALON_SERVICE_ITEM (ID, NAME, CATEGORY, DURATION_MINUTES, PRICE, DESCRIPTION, IMAGE_URL, ADDITIONAL_IMAGE_URLS, TOP_RANK) VALUES
(4, '웨이브 펌', '펌', 120, 95000, '1인 미용실에서 진행하는 부드러운 웨이브 펌 시술입니다.', 'https://images.unsplash.com/photo-1633681926022-84c23e8cb2d6?auto=format&fit=crop&w=900&q=80', 'https://images.unsplash.com/photo-1523263685509-57c1d050d19b?auto=format&fit=crop&w=900&q=80', 1);

INSERT INTO SALON_SERVICE_ITEM (ID, NAME, CATEGORY, DURATION_MINUTES, PRICE, DESCRIPTION, IMAGE_URL, ADDITIONAL_IMAGE_URLS, TOP_RANK) VALUES
(5, '두피 클리닉', '클리닉', 60, 55000, '두피 상태를 점검하고 진정 케어를 진행하는 클리닉입니다.', 'https://images.unsplash.com/photo-1560066984-138dadb4c035?auto=format&fit=crop&w=900&q=80', null, 3);

INSERT INTO SALON_SERVICE_ITEM (ID, NAME, CATEGORY, DURATION_MINUTES, PRICE, DESCRIPTION, IMAGE_URL, ADDITIONAL_IMAGE_URLS, TOP_RANK) VALUES
(6, '젤 네일 기본', '네일', 60, 40000, '손톱 케어와 기본 젤 컬러를 진행하는 네일 시술입니다.', 'https://images.unsplash.com/photo-1604654894610-df63bc536371?auto=format&fit=crop&w=900&q=80', 'https://images.unsplash.com/photo-1610992015732-2449b76344bc?auto=format&fit=crop&w=900&q=80', null);

INSERT INTO SALON_SERVICE_ITEM (ID, NAME, CATEGORY, DURATION_MINUTES, PRICE, DESCRIPTION, IMAGE_URL, ADDITIONAL_IMAGE_URLS, TOP_RANK) VALUES
(7, '꾸미기 화장', '메이크업', 80, 110000, '소개팅, 촬영, 중요한 약속 전 자연스럽게 완성하는 메이크업입니다.', 'https://images.unsplash.com/photo-1487412947147-5cebf100ffc2?auto=format&fit=crop&w=900&q=80', 'https://images.unsplash.com/photo-1512496015851-a90fb38ba796?auto=format&fit=crop&w=900&q=80', null);

INSERT INTO SALON_SERVICE_ITEM (ID, NAME, CATEGORY, DURATION_MINUTES, PRICE, DESCRIPTION, IMAGE_URL, ADDITIONAL_IMAGE_URLS, TOP_RANK) VALUES
(8, '신부 화장', '웨딩', 150, 250000, '본식 또는 웨딩 촬영을 위한 신부 헤어와 메이크업 패키지입니다.', 'https://images.unsplash.com/photo-1519741497674-611481863552?auto=format&fit=crop&w=900&q=80', 'https://images.unsplash.com/photo-1523438885200-e635ba2c371e?auto=format&fit=crop&w=900&q=80', null);

-- 미용실 예약 규칙을 이미 만족하는 샘플 예약 데이터를 입력합니다.
INSERT INTO SALON_CUSTOMER (ID, NAME, EMAIL, PHONE, SOCIAL_PROVIDER, SOCIAL_ID, CREATED_AT, UPDATED_AT) VALUES
(1, '김서연', 'seoyeon@example.com', '010-1234-5678', null, null, TIMESTAMP '2030-01-01 09:00:00', TIMESTAMP '2030-01-01 09:00:00');

INSERT INTO SALON_CUSTOMER (ID, NAME, EMAIL, PHONE, SOCIAL_PROVIDER, SOCIAL_ID, CREATED_AT, UPDATED_AT) VALUES
(2, '박지훈', 'jihoon@example.com', '010-8765-4321', null, null, TIMESTAMP '2030-01-01 09:00:00', TIMESTAMP '2030-01-01 09:00:00');

INSERT INTO SALON_RESERVATION (
    ID,
    SERVICE_ID,
    CUSTOMER_ID,
    CUSTOMER_NAME,
    CUSTOMER_EMAIL,
    CUSTOMER_PHONE,
    RESERVATION_DATE_TIME,
    NO_SHOW_POLICY_AGREED,
    STATUS,
    MEMO
) VALUES (
    1,
    1,
    1,
    '김서연',
    'seoyeon@example.com',
    '010-1234-5678',
    TIMESTAMP '2030-01-15 10:00:00',
    1,
    'CONFIRMED',
    '옆머리는 자연스럽게 정리 요청'
);

INSERT INTO SALON_RESERVATION (
    ID,
    SERVICE_ID,
    CUSTOMER_ID,
    CUSTOMER_NAME,
    CUSTOMER_EMAIL,
    CUSTOMER_PHONE,
    RESERVATION_DATE_TIME,
    NO_SHOW_POLICY_AGREED,
    STATUS,
    MEMO
) VALUES (
    2,
    2,
    2,
    '박지훈',
    'jihoon@example.com',
    '010-8765-4321',
    TIMESTAMP '2030-01-15 13:30:00',
    1,
    'REQUESTED',
    '점심시간에 가능한 빠른 커트 요청'
);

COMMIT;
