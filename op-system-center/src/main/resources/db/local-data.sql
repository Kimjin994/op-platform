-- local profile 预置后台管理员：手机号 13800000000 / 密码 123456
-- created_at / updated_at 存的是毫秒时间戳
INSERT INTO admin_user (created_at, updated_at, version, phone, password, username, valid_status)
VALUES (1700000000000, 1700000000000, 0, '13800000000',
        '$2a$10$cddjFTyWIvjCeM/18lPKzev./C1jblcrZ1TYfBoKY.v6q4WjiCd0u', 'admin', 1);

-- 超级管理员角色，预置管理员 admin(id=1) 绑定该角色
INSERT INTO role (created_at, updated_at, version, role, name, remark, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'ADMIN', '超级管理员', '跳过接口权限校验', 1);
INSERT INTO admin_user_role (created_at, updated_at, version, user_id, role_id)
VALUES (1700000000000, 1700000000000, 0, 1, 1);
-- 接口权限码，与控制器 @PreAuthorize 一致
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'asset:asset:in', 'asset:asset:in', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'asset:asset:list', 'asset:asset:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'asset:asset:out', 'asset:asset:out', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'asset:asset:transfer', 'asset:asset:transfer', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'asset:asset:view', 'asset:asset:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'asset:inOut:list', 'asset:inOut:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'asset:inOut:view', 'asset:inOut:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'asset:lifecycle:create', 'asset:lifecycle:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'asset:lifecycle:list', 'asset:lifecycle:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'asset:lifecycle:view', 'asset:lifecycle:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'asset:sku:create', 'asset:sku:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'asset:sku:invalid', 'asset:sku:invalid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'asset:sku:list', 'asset:sku:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'asset:sku:update', 'asset:sku:update', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'asset:sku:valid', 'asset:sku:valid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'asset:sku:view', 'asset:sku:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'asset:warehouse:create', 'asset:warehouse:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'asset:warehouse:invalid', 'asset:warehouse:invalid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'asset:warehouse:list', 'asset:warehouse:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'asset:warehouse:update', 'asset:warehouse:update', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'asset:warehouse:valid', 'asset:warehouse:valid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'asset:warehouse:view', 'asset:warehouse:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:enterprise:create', 'invoice:enterprise:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:enterprise:invalid', 'invoice:enterprise:invalid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:enterprise:list', 'invoice:enterprise:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:enterprise:update', 'invoice:enterprise:update', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:enterprise:valid', 'invoice:enterprise:valid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:enterprise:view', 'invoice:enterprise:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:invoice:confirm', 'invoice:invoice:confirm', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:invoice:create', 'invoice:invoice:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:invoice:invalid', 'invoice:invoice:invalid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:invoice:list', 'invoice:invoice:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:invoice:update', 'invoice:invoice:update', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:invoice:valid', 'invoice:invoice:valid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:invoice:view', 'invoice:invoice:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:payItem:create', 'invoice:payItem:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:payItem:invalid', 'invoice:payItem:invalid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:payItem:list', 'invoice:payItem:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:payItem:update', 'invoice:payItem:update', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:payItem:valid', 'invoice:payItem:valid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:payItem:view', 'invoice:payItem:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:receipt:abandon', 'invoice:receipt:abandon', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:receipt:create', 'invoice:receipt:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:receipt:exchange', 'invoice:receipt:exchange', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:receipt:invalid', 'invoice:receipt:invalid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:receipt:list', 'invoice:receipt:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:receipt:update', 'invoice:receipt:update', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:receipt:valid', 'invoice:receipt:valid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:receipt:view', 'invoice:receipt:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:receiptItem:create', 'invoice:receiptItem:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:receiptItem:list', 'invoice:receiptItem:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:receiptItem:update', 'invoice:receiptItem:update', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:receiptItem:view', 'invoice:receiptItem:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:router:create', 'invoice:router:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:router:invalid', 'invoice:router:invalid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:router:list', 'invoice:router:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:router:update', 'invoice:router:update', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:router:valid', 'invoice:router:valid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:router:view', 'invoice:router:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:taxRate:create', 'invoice:taxRate:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:taxRate:invalid', 'invoice:taxRate:invalid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:taxRate:list', 'invoice:taxRate:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:taxRate:update', 'invoice:taxRate:update', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:taxRate:valid', 'invoice:taxRate:valid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'invoice:taxRate:view', 'invoice:taxRate:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'message:ding:create', 'message:ding:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'message:ding:invalid', 'message:ding:invalid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'message:ding:list', 'message:ding:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'message:ding:update', 'message:ding:update', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'message:ding:valid', 'message:ding:valid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'message:ding:view', 'message:ding:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'message:record:create', 'message:record:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'message:record:invalid', 'message:record:invalid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'message:record:list', 'message:record:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'message:record:send', 'message:record:send', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'message:record:update', 'message:record:update', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'message:record:valid', 'message:record:valid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'message:record:view', 'message:record:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'message:template:create', 'message:template:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'message:template:invalid', 'message:template:invalid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'message:template:list', 'message:template:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'message:template:update', 'message:template:update', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'message:template:valid', 'message:template:valid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'message:template:view', 'message:template:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'message:verify:create', 'message:verify:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'message:verify:invalid', 'message:verify:invalid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'message:verify:list', 'message:verify:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'message:verify:update', 'message:verify:update', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'message:verify:valid', 'message:verify:valid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'message:verify:view', 'message:verify:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'perm:admin:create', 'perm:admin:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'perm:admin:grant', 'perm:admin:grant', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'perm:admin:invalid', 'perm:admin:invalid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'perm:admin:list', 'perm:admin:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'perm:admin:update', 'perm:admin:update', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'perm:admin:valid', 'perm:admin:valid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'perm:admin:view', 'perm:admin:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'perm:platform:create', 'perm:platform:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'perm:platform:invalid', 'perm:platform:invalid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'perm:platform:list', 'perm:platform:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'perm:platform:update', 'perm:platform:update', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'perm:platform:valid', 'perm:platform:valid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'perm:platform:view', 'perm:platform:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'perm:resource:create', 'perm:resource:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'perm:resource:invalid', 'perm:resource:invalid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'perm:resource:list', 'perm:resource:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'perm:resource:update', 'perm:resource:update', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'perm:resource:valid', 'perm:resource:valid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'perm:resource:view', 'perm:resource:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'perm:role:create', 'perm:role:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'perm:role:grant', 'perm:role:grant', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'perm:role:invalid', 'perm:role:invalid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'perm:role:list', 'perm:role:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'perm:role:update', 'perm:role:update', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'perm:role:valid', 'perm:role:valid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'perm:role:view', 'perm:role:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:category:create', 'template:category:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:category:invalid', 'template:category:invalid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:category:list', 'template:category:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:category:update', 'template:category:update', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:category:valid', 'template:category:valid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:category:view', 'template:category:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:data:gen', 'template:data:gen', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:data:mock', 'template:data:mock', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:data:verify', 'template:data:verify', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:dict:create', 'template:dict:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:dict:invalid', 'template:dict:invalid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:dict:list', 'template:dict:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:dict:update', 'template:dict:update', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:dict:valid', 'template:dict:valid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:dict:view', 'template:dict:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:gen:create', 'template:gen:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:gen:invalid', 'template:gen:invalid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:gen:list', 'template:gen:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:gen:update', 'template:gen:update', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:gen:valid', 'template:gen:valid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:gen:view', 'template:gen:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:item:create', 'template:item:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:item:invalid', 'template:item:invalid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:item:list', 'template:item:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:item:update', 'template:item:update', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:item:valid', 'template:item:valid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:item:view', 'template:item:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:mock:create', 'template:mock:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:mock:invalid', 'template:mock:invalid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:mock:list', 'template:mock:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:mock:update', 'template:mock:update', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:mock:valid', 'template:mock:valid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:mock:view', 'template:mock:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:object:create', 'template:object:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:object:invalid', 'template:object:invalid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:object:list', 'template:object:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:object:update', 'template:object:update', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:object:valid', 'template:object:valid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:object:view', 'template:object:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:verify:create', 'template:verify:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:verify:invalid', 'template:verify:invalid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:verify:list', 'template:verify:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:verify:update', 'template:verify:update', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:verify:valid', 'template:verify:valid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'template:verify:view', 'template:verify:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'trade:order:cancel', 'trade:order:cancel', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'trade:order:complete', 'trade:order:complete', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'trade:order:create', 'trade:order:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'trade:order:invalid', 'trade:order:invalid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'trade:order:list', 'trade:order:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'trade:order:revise', 'trade:order:revise', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'trade:order:update', 'trade:order:update', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'trade:order:valid', 'trade:order:valid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'trade:order:view', 'trade:order:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'trade:orderLifecycle:create', 'trade:orderLifecycle:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'trade:orderLifecycle:invalid', 'trade:orderLifecycle:invalid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'trade:orderLifecycle:list', 'trade:orderLifecycle:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'trade:orderLifecycle:update', 'trade:orderLifecycle:update', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'trade:orderLifecycle:valid', 'trade:orderLifecycle:valid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'trade:orderLifecycle:view', 'trade:orderLifecycle:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'trade:revise:create', 'trade:revise:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'trade:revise:invalid', 'trade:revise:invalid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'trade:revise:list', 'trade:revise:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'trade:revise:update', 'trade:revise:update', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'trade:revise:valid', 'trade:revise:valid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'trade:revise:view', 'trade:revise:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'user:account:create', 'user:account:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'user:account:invalid', 'user:account:invalid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'user:account:list', 'user:account:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'user:account:update', 'user:account:update', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'user:account:valid', 'user:account:valid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'user:account:view', 'user:account:view', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'user:base:create', 'user:base:create', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'user:base:invalid', 'user:base:invalid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'user:base:list', 'user:base:list', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'user:base:update', 'user:base:update', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'user:base:valid', 'user:base:valid', 1);
INSERT INTO resource (created_at, updated_at, version, name, code, valid_status)
VALUES (1700000000000, 1700000000000, 0, 'user:base:view', 'user:base:view', 1);
