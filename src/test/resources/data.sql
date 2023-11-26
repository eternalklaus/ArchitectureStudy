INSERT INTO site_user (email,password,username) VALUES
    ('test@test.com','$2a$10$gNswjkAa698rc56rOJTeDe87Ao0dU.joI/7Vqx75x.zw.fwhswi4K','test');

INSERT INTO coupon (id, available_stock, name, description, start_date, end_date) VALUES
    ('1', '100', 'COUPON_1', '2023 상의 쿠폰', NOW(), NOW() + INTERVAL '1 day');
INSERT INTO coupon (id, available_stock, name, description, start_date, end_date) VALUES
    ('2', '100', 'COUPON_2', '2023 상의 쿠폰', NOW(), NOW() + INTERVAL '1 day');
INSERT INTO coupon (id, available_stock, name, description, start_date, end_date) VALUES
    ('3', '10', 'COUPON_3', '2023 신발 쿠폰', NOW(), NOW() + INTERVAL '1 month');