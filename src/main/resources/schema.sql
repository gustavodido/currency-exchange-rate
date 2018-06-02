CREATE TABLE User (
  id         BINARY(16) PRIMARY KEY,
  first_name VARCHAR(50)  NOT NULL,
  last_name  VARCHAR(50)  NOT NULL,
  username   VARCHAR(30)  NOT NULL,
  email      VARCHAR(255) NOT NULL,
  password   VARCHAR(30)  NOT NULL,
  device_id  VARCHAR(255) NULL
);
