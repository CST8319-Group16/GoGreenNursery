DROP DATABASE IF EXISTS go_green;
DROP USER IF EXISTS 'go_green_user'@'%';

CREATE DATABASE IF NOT EXISTS go_green;
CREATE USER 'go_green_user'@'%' IDENTIFIED BY 'go_green_password';
GRANT ALL PRIVILEGES ON go_green.* TO 'go_green_user'@'%';
FLUSH PRIVILEGES;

USE go_green;

CREATE TABLE User (
                      UserID int AUTO_INCREMENT PRIMARY KEY,
                      Username varchar(255),
                      Password varchar(255),
                      Email varchar(255),
                      Usertype varchar(255)
);

INSERT INTO User (Username, Password, Email, Usertype)
VALUES
    ('Admin', 'adminpassword', 'admin@example.com', 'Admin'),
    ('Yan', 'yanpassword', 'Yan@example.com', 'Registered User');



CREATE TABLE Address (
                         AddressID int AUTO_INCREMENT PRIMARY KEY,
                         UserID int,
                         FullName varchar(255),
                         Address varchar(255),
                         City varchar(255),
                         Country varchar(255),
                         PostalCode varchar(255),
                         Phone varchar(255),
                         FOREIGN KEY (UserID) REFERENCES User(UserID)
);

CREATE TABLE Category (
                          CategoryID int AUTO_INCREMENT PRIMARY KEY,
                          CategoryName varchar(255)
);

CREATE TABLE Product (
                         ProductID int AUTO_INCREMENT PRIMARY KEY,
                         ProductName varchar(255),
                         ProductDesc text,
                         Price decimal(10, 2),
                         Stock int,
                         CategoryID int,
                         ImageURL varchar(255),
                         FOREIGN KEY (CategoryID) REFERENCES Category(CategoryID)
);

CREATE TABLE `Order` (
                         OrderID int AUTO_INCREMENT PRIMARY KEY,
                         UserID int,
                         OrderDate datetime,
                         Status varchar(255),
                         Total decimal(10, 2),
                         FOREIGN KEY (UserID) REFERENCES User(UserID)
);

CREATE TABLE OrderDetails (
                              OrderDetailID int AUTO_INCREMENT PRIMARY KEY,
                              OrderID int,
                              ProductID int,
                              Quantity int,
                              Price decimal(10, 2),
                              FOREIGN KEY (OrderID) REFERENCES `Order`(OrderID),
                              FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);