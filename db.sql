CREATE TABLE address
(
    id INTEGER PRIMARY KEY AUTO_INCREMENT, 
    city VARCHAR(20) NOT NULL,
    landmark VARCHAR(50), 
    pincode VARCHAR(20), 
    plot_number VARCHAR(50), 
    state VARCHAR(20)
);

INSERT INTO address VALUES(default, 'Surat', 'Jaweri Bazaar', '395003', '16B Jalaram Nagar', 'Gujarat');
INSERT INTO address VALUES(default, 'Chennai', 'DMK HQ', '600001', '8A Anna Colony', 'Tamilnadu');
INSERT INTO address VALUES(default, 'Pune', 'Rani Garden', '411002', '139 Shastri Peth', 'Maharashtra');
INSERT INTO address VALUES(default, 'Pune', 'Panchshil Plaza', '411005', '98 Worker Zone', 'Maharashtra');
INSERT INTO address VALUES(default, 'Cuttack', 'Power Park', '753002', '302 Rajamouli Center', 'Orissa');
INSERT INTO address VALUES(default, 'Mangalore', 'Asif Restro', '574142', '255/26 Kamal Heights', 'Karnataka');

CREATE TABLE admin_customers
(
    id INTEGER PRIMARY KEY AUTO_INCREMENT, 
    contact_number bigint,
    first_name VARCHAR(15) NOT NULL, 
    last_name VARCHAR(15) NOT NULL, 
    password VARCHAR(20) NOT NULL, 
    role VARCHAR(20),
    user_email VARCHAR(30) NOT NULL UNIQUE,
    address_id INTEGER,
    FOREIGN KEY(address_id) REFERENCES address (id)
);

INSERT INTO admin_customers VALUES(default, 9562315478, 'Swapnil', 'Potdar', 'potdarsa007', 'Admin', 'potdarswapnil007@gmail.com',3);
INSERT INTO admin_customers VALUES(default, 9362515579, 'Swati', 'Kapoor', 'papakipari', 'Customer', 'swatik45@gmail.com',1);
INSERT INTO admin_customers VALUES(default, 9876215428, 'Rajesh', 'Tomar', 'yourstrulyRT', 'Customer', 'rtomarRT@gmail.com',2);

CREATE TABLE employees
(
    id INTEGER PRIMARY KEY AUTO_INCREMENT, 
    adhar_file_name VARCHAR(255),
    available bit(1) NOT NULL,
    contact_number bigint,
    emp_email VARCHAR(30) NOT NULL UNIQUE,
    experience INTEGER NOT NULL,
    first_name VARCHAR(15) NOT NULL, 
    last_name VARCHAR(15) NOT NULL, 
    password VARCHAR(20) NOT NULL, 
    verified bit(1) NOT NULL,
    address_id INTEGER,
    FOREIGN KEY(address_id) REFERENCES address (id),
    prof_id INTEGER NOT NULL,
    FOREIGN KEY(prof_id) REFERENCES professions (id)
);

INSERT INTO employees VALUES(default, 'Rohan Mhatre', 1, 8562451723, 'rmprofessional@gmail.com', 2, 'Rohan', 'Mhatre', 'aajtak', 1, 4, 1);
INSERT INTO employees VALUES(default, 'Kamesh Saraf', 1, 8592481743, 'happywashmcrepair@gmail.com', 3, 'Kamesh', 'Saraf', 'ganraj', 0, 5, 6);
INSERT INTO employees VALUES(default, 'Anjali Shetty', 0, 9852459783, 'aspests@gmail.com', 1, 'Anjali', 'Shetty', 'sashabanks', 1, 6, 4);

CREATE TABLE professions
(
    id INTEGER PRIMARY KEY AUTO_INCREMENT, 
    basic_charge DOUBLE,
    prof_name VARCHAR(15) NOT NULL UNIQUE
);

INSERT INTO professions VALUES(default, 1500, 'AC Repair');
INSERT INTO professions VALUES(default, 5500, 'Painting');
INSERT INTO professions VALUES(default, 1590, 'Home Cleaning');
INSERT INTO professions VALUES(default, 2500, 'Pest Control');
INSERT INTO professions VALUES(default, 1200, 'Fridge Repair');
INSERT INTO professions VALUES(default, 1600, 'Wash m/c Repair');

CREATE TABLE feedbacks
(
    id INTEGER PRIMARY KEY AUTO_INCREMENT, 
    feedback VARCHAR(500),
    rating VARCHAR(255),
    emp_id INTEGER NOT NULL,
    FOREIGN KEY(emp_id) REFERENCES employees (id),
    customer_name VARCHAR(255),
);

INSERT INTO feedbacks VALUES(default, 'Service is very very good.', '4.5', 7, 'Benny Dayal');
INSERT INTO feedbacks VALUES(default, 'Amazing service and most importantly, they are worth to money.', '4', 8, 'Rupamati Sharma');

CREATE TABLE orders
(
    id INTEGER PRIMARY KEY AUTO_INCREMENT, 
    amount DOUBLE NOT NULL,
    isfeedback bit(1) NOT NULL,
    order_date DATE,
    order_status VARCHAR(20),
    service_name VARCHAR(15),
    emp_id INTEGER,
    FOREIGN KEY(emp_id) REFERENCES employees (id),
    cust_id INTEGER,
    FOREIGN KEY(cust_id) REFERENCES admin_customers (id)
);

INSERT INTO orders VALUES(default, 1600, 1, '2021-02-03', 'Completed', 'Wash m/c Repair', 8, 2);
INSERT INTO orders VALUES(default, 2500, 1, '2021-02-04', 'Completed', 'Pest Control', 9, 3);