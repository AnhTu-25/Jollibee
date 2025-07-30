CREATE DATABASE jollibee;
GO

USE jollibee;
GO

-- Tạo bảng loại sản phẩm
CREATE TABLE loai_sanpham (
    ma_loai INT IDENTITY(1,1) PRIMARY KEY,
    ten_loai VARCHAR(100) NOT NULL
);

-- Tạo bảng sản phẩm
CREATE TABLE sanpham (
    ma_sp INT IDENTITY(1,1) PRIMARY KEY,
    ten_sp VARCHAR(100) NOT NULL,
    don_gia FLOAT,
    so_luong INT,
    ma_loai INT FOREIGN KEY REFERENCES loai_sanpham(ma_loai)
);

-- Tạo bảng Users
CREATE TABLE Users (
    Id INT PRIMARY KEY IDENTITY,
    Username NVARCHAR(50) NOT NULL UNIQUE,
    Password NVARCHAR(100) NOT NULL,
    FullName NVARCHAR(100),
    Role NVARCHAR(20),
    Enabled BIT DEFAULT 1,
    Photo NVARCHAR(255) NOT NULL
);

CREATE TABLE Bills (
    id INT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(50) NOT NULL,
    totalAmount DECIMAL(18, 2) DEFAULT 0,
    createdDate DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (username) REFERENCES Users(username)
);

-- Thêm dữ liệu vào bảng loại sản phẩm
INSERT INTO loai_sanpham (ten_loai)
VALUES 
('Food'),
('Drink'),
('Combo'),
('Dessert');

-- Thêm dữ liệu vào bảng sản phẩm
INSERT INTO sanpham (ten_sp, don_gia, so_luong, ma_loai)
VALUES 
('Fried Chicken Combo + Pepsi', 75000, 50, 3),
('Crispy Fried Chicken Rice', 55000, 30, 1),
('Peach Orange Lemongrass Tea', 25000, 40, 2),
('Chocolate Ice Cream', 20000, 20, 4),
('Burger Combo + Fries + Drink', 85000, 25, 3),
('Coca-Cola', 15000, 60, 2);

-- Thêm dữ liệu vào bảng Users
INSERT INTO Users (username, password, fullname, photo, Role, Enabled)
VALUES 
('admin', '123', N'Quản trị viên', 'jlb1.jpg', 'Manager', 1),
('staff', '1234', N'Nhân viên 1','jlb1.jpg', 'Staff', 1);