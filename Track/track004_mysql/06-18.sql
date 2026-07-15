USE sb_erp_db;
SELECT * FROM reservation;
SELECT * FROM resource;


USE sb_erp_db;

SELECT
    res_id, com_id, res_code, res_name, res_type, quantity, remark, created_at, updated_at
FROM resource
WHERE com_id = 1
ORDER BY res_id DESC
LIMIT 0, 10;

desc resource;
use resource;
USE sb_erp_db;

-- 1. 기존 데이터와 충돌 방지를 위해 id를 명시하지 않고 입력 (권장)
INSERT INTO `resource` 
(`com_id`, `res_code`, `res_name`, `res_type`, `quantity`, `remark`, `created_at`, `updated_at`) 
VALUES
(1, 'RM001', '대회의실',   'Room', 1,  '최대 20인 수용', NOW(), NOW()),
(1, 'RM002', '소회의실A',  'Room', 1,  '최대 6인 수용',  NOW(), NOW()),
(1, 'RM003', '소회의실B',  'Room', 1,  '최대 6인 수용',  NOW(), NOW()),
(2, 'RM001', '공장 회의실', 'Room', 1,  '최대 10인 수용', NOW(), NOW()),
(3, 'RM001', '고객 상담실', 'Room', 2,  '상담 전용',      NOW(), NOW());



DESC resource;

ALTER TABLE resource MODIFY res_type VARCHAR(50);