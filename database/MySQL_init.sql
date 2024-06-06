DROP DATABASE IF EXISTS go_green;
DROP USER IF EXISTS 'go_green_user'@'%';

CREATE DATABASE IF NOT EXISTS go_green;
CREATE USER 'group16'@'%' IDENTIFIED BY 'group16';
GRANT ALL PRIVILEGES ON go_green.* TO 'group16'@'%';
FLUSH PRIVILEGES;

USE go_green;

CREATE TABLE User (
                      userId int AUTO_INCREMENT PRIMARY KEY,
                      username varchar(255),
                      password varchar(255),
                      email varchar(255),
                      userType varchar(255)
);

INSERT INTO User (username, password, email, userType)
VALUES
    ('Admin', 'adminpassword', 'admin@example.com', 'Admin'),
    ('Yan', 'yanpassword', 'Yan@example.com', 'Registered User');

CREATE TABLE Address (
                         addressId int AUTO_INCREMENT PRIMARY KEY,
                         userId int,
                         fullName varchar(255),
                         address varchar(255),
                         city varchar(255),
                         country varchar(255),
                         postalCode varchar(255),
                         phone varchar(255),
                         FOREIGN KEY (userId) REFERENCES User(userId)
);

CREATE TABLE Category (
                          categoryId int AUTO_INCREMENT PRIMARY KEY,
                          categoryName varchar(255)
);

CREATE TABLE Product (
                         productId int AUTO_INCREMENT PRIMARY KEY,
                         productName varchar(255),
                         productDesc text,
                         price decimal(10, 2),
                         stock int,
                         categoryId int,
                         imageURL varchar(255),
                         FOREIGN KEY (categoryId) REFERENCES Category(categoryId)
);

CREATE TABLE `Order` (
                         orderId int AUTO_INCREMENT PRIMARY KEY,
                         userId int,
                         orderDate datetime,
                         status varchar(255),
                         total decimal(10, 2),
                         FOREIGN KEY (userId) REFERENCES User(userId)
);

CREATE TABLE OrderDetails (
                              orderDetailId int AUTO_INCREMENT PRIMARY KEY,
                              orderId int,
                              productId int,
                              quantity int,
                              price decimal(10, 2),
                              FOREIGN KEY (orderId) REFERENCES `Order`(orderId),
                              FOREIGN KEY (productId) REFERENCES Product(productId)
);