CREATE DATABASE PolyCafe
ON
	(NAME = PolyCafe,
	FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\PolyCafe.mdf',
	SIZE = 10MB,
	MAXSIZE = 50MB,
	FILEGROWTH = 5MB)
LOG ON
	(NAME = PolyCafe_log,
	FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\PolyCafe_log.ldf',
	SIZE = 5MB,
	MAXSIZE = 25MB,
	FILEGROWTH = 5MB );
go

USE PolyCafe;
go

CREATE TABLE Categories(
    Id NVARCHAR(20) NOT NULL,
    Name NVARCHAR(50) NOT NULL,
    PRIMARY KEY(Id)
)

CREATE TABLE Drinks(
    Id NVARCHAR(20) NOT NULL,
    Name NVARCHAR(50) NOT NULL,
    UnitPrice FLOAT NOT NULL,
    Discount FLOAT NOT NULL,
    Image NVARCHAR(50) NOT NULL,
    Available BIT NOT NULL,
    CategoryId NVARCHAR(20) NOT NULL,
    PRIMARY KEY(Id),
    FOREIGN KEY(CategoryId) REFERENCES Categories(Id) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
)

CREATE TABLE Cards(
    Id INT NOT NULL,
    Status INT NOT NULL,
    PRIMARY KEY(Id)
)

CREATE TABLE Users(
    Username NVARCHAR(20) NOT NULL,
    Password NVARCHAR(50) NOT NULL,
    Enabled BIT NOT NULL,
    Fullname NVARCHAR(50) NOT NULL,
    Photo NVARCHAR(50) NOT NULL,
    Manager BIT NOT NULL,
    PRIMARY KEY(Username)
)

CREATE TABLE Bills(
    Id BIGINT NOT NULL IDENTITY(10000, 1),
    Username NVARCHAR(20) NOT NULL,
    CardId INT NOT NULL,
    Checkin DATETIME NOT NULL,
    Checkout DATETIME NULL,
    Status INT NOT NULL,
    PRIMARY KEY(Id),
    FOREIGN KEY(Username) REFERENCES Users(Username) 
        ON UPDATE CASCADE,
    FOREIGN KEY(CardId) REFERENCES Cards(Id) 
        ON UPDATE CASCADE
)

CREATE TABLE BillDetails(
    Id BIGINT NOT NULL IDENTITY(100000, 1),
    BillId BIGINT NOT NULL,
    DrinkId NVARCHAR(20) NOT NULL,
    UnitPrice FLOAT NOT NULL,
    Discount FLOAT NOT NULL,
    Quantity INT NOT NULL,
    PRIMARY KEY(Id),
    FOREIGN KEY(BillId) REFERENCES Bills(Id) 
        ON DELETE CASCADE,
    FOREIGN KEY(DrinkId) REFERENCES Drinks(Id) 
        ON UPDATE CASCADE
)

USE PolyCafe;
GO

-- Insert into Categories
INSERT INTO Categories (Id, Name) VALUES
('CAT001', 'Coffee'),
('CAT002', 'Tea'),
('CAT003', 'Juice'),
('CAT004', 'Smoothie'),
('CAT005', 'Soda');
GO

-- Insert into Drinks
INSERT INTO Drinks (Id, Name, UnitPrice, Discount, Image, Available, CategoryId) VALUES
('D001', 'Espresso', 2.5, 0.0, 'espresso.png', 1, 'CAT001'),
('D002', 'Green Tea', 2.0, 0.1, 'greentea.png', 1, 'CAT002'),
('D003', 'Orange Juice', 3.0, 0.0, 'orangejuice.png', 1, 'CAT003'),
('D004', 'Mango Smoothie', 4.0, 0.2, 'mangosmoothie.png', 0, 'CAT004'),
('D005', 'Cola', 1.5, 0.0, 'cola.png', 1, 'CAT005');
GO

-- Insert into Cards
INSERT INTO Cards (Id, Status) VALUES
(101, 1),
(102, 0),
(103, 1),
(104, 2),
(105, 1);
GO

-- Insert into Users
INSERT INTO Users (Username, Password, Enabled, Fullname, Photo, Manager) VALUES
('user1', 'password1', 1, 'John Doe', 'user1.jpg', 0),
('phuong', '123', 1, 'Jane Smith', 'user2.jpg', 0),
('manager1', 'admin123', 1, 'Alice Brown', 'manager1.jpg', 1),
('user3', 'password3', 0, 'Bob Johnson', 'user3.jpg', 0),
('user4', 'password4', 1, 'Emma Wilson', 'user4.jpg', 0),
('user1@gmail.com', '123', 1, 'Nguyễn Văn Tèo', 'trump-small.png', 1);
GO

-- Insert into Bills
INSERT INTO Bills (Username, CardId, Checkin, Checkout, Status) VALUES
('user1', 101, '2025-05-21 08:00:00', '2025-05-21 09:00:00', 1),
('user2', 103, '2025-05-21 09:00:00', NULL, 0),
('manager1', 105, '2025-05-21 10:00:00', '2025-05-21 11:00:00', 1),
('user4', 101, '2025-05-21 11:30:00', NULL, 0),
('user1', 103, '2025-05-21 12:00:00', '2025-05-21 13:00:00', 1);
GO

-- Insert into BillDetails
INSERT INTO BillDetails (BillId, DrinkId, UnitPrice, Discount, Quantity) VALUES
(10000, 'D001', 2.5, 0.0, 2),
(10000, 'D002', 2.0, 0.1, 1),
(10001, 'D003', 3.0, 0.0, 1),
(10002, 'D005', 1.5, 0.0, 3),
(10003, 'D001', 2.5, 0.0, 1);
GO

