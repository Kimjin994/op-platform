-- local profile 预置后台管理员：手机号 13800000000 / 密码 123456
-- created_at / updated_at 存的是毫秒时间戳
INSERT INTO admin_user (created_at, updated_at, version, phone, password, username, valid_status)
VALUES (1700000000000, 1700000000000, 0, '13800000000',
        '$2a$10$cddjFTyWIvjCeM/18lPKzev./C1jblcrZ1TYfBoKY.v6q4WjiCd0u', 'admin', 1);
