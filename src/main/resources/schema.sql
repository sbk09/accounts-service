CREATE TABLE IF NOT EXISTS customer(
    customer_id int AUTO_INCREMENT PRIMARY KEY,
    name varchar(100) NOT NULL,
    email varchar(100) NOT NULL,
    mobile_number varchar(20) NOT NULL,
    created_date date NOT NULL,
    created_by varchar(20) NOT NULL,
    updated_date date DEFAULT NULL,
    updated_by varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS accounts(
    customer_id int NOT NULL,
    account_number int AUTO_INCREMENT PRIMARY KEY,
    account_type varchar(100) NOT NULL,
    branch_address varchar(200) NOT NULL,
    created_date date NOT NULL,
    created_by varchar(20) NOT NULL,
    updated_date date DEFAULT NULL,
    updated_by varchar(20) DEFAULT NULL
);

CREATE SEQUENCE "MY_OWN_CUSTOMER_SEQ"
MAXVALUE 999999999
INCREMENT BY 50
START WITH 202700
NOCACHE
NOCYCLE;