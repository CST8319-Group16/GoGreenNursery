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
    ('admin', 'adminpassword', 'admin@example.com', 'admin'),
    ('yan', 'yanpassword', 'Yan@example.com', 'registered user');


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


-- Insert plants into Product table -Joshua
INSERT INTO Product (productName, productDesc, price, stock, categoryId, imageURL) VALUES
('Aloe Vera', 'A low-maintenance succulent known for its medicinal properties.', 10.99, 100, 1, 'images/aloe_vera.jpg'),
('Spider Plant', 'A popular houseplant that is easy to care for and has air-purifying qualities.', 8.99, 150, 1, 'images/spider_plant.jpg'),
('Peace Lily', 'A beautiful plant that helps improve indoor air quality.', 12.99, 80, 1, 'images/peace_lily.jpg'),
('Snake Plant', 'A hardy plant known for its ability to thrive in low light conditions.', 15.99, 120, 1, 'images/snake_plant.jpg'),
('Fern', 'A lush, green plant that adds a touch of nature to any space.', 9.99, 200, 1, 'images/fern.jpg'),
('Succulent', 'A small, drought-tolerant plant perfect for indoor settings.', 5.99, 300, 1, 'images/succulent.jpg'),
('Orchid', 'An elegant plant with beautiful blooms, perfect for decoration.', 18.99, 60, 1, 'images/orchid.jpg'),
('Cactus', 'A low-maintenance plant that thrives in dry environments.', 7.99, 250, 1, 'images/cactus.jpg'),
('Bamboo', 'A fast-growing plant that brings a touch of the tropics indoors.', 14.99, 90, 1, 'images/bamboo.jpg'),
('Fiddle Leaf Fig', 'A trendy plant known for its large, glossy leaves.', 20.99, 70, 1, 'images/fiddle_leaf_fig.jpg'),
('Monstera', 'A popular plant with distinctive split leaves.', 22.99, 80, 1, 'images/monstera.jpg'),
('Pothos', 'An easy-care plant with trailing vines.', 11.99, 130, 1, 'images/pothos.jpg'),
('ZZ Plant', 'A hardy plant that requires very little maintenance.', 13.99, 100, 1, 'images/zz_plant.jpg'),
('Rubber Plant', 'A striking plant with large, shiny leaves.', 16.99, 110, 1, 'images/rubber_plant.jpg'),
('Jade Plant', 'A symbol of good luck, this plant is easy to grow.', 8.99, 140, 1, 'images/jade_plant.jpg');


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