DROP TABLE IF EXISTS site_user;
CREATE TABLE site_user (
  id SERIAL PRIMARY KEY,
  username VARCHAR(255) UNIQUE,
  password VARCHAR(255),
  email VARCHAR(255) UNIQUE
);

DROP TABLE IF EXISTS coupon;
CREATE TABLE coupon (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) UNIQUE,
  available_stock INTEGER,
  description VARCHAR(255),
  start_date DATE,
  end_date DATE
);