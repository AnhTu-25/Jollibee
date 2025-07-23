CREATE DATABASE jollibee;
GO

USE jollibee;
GO

-- Tạo bảng loại sản phẩm
IF OBJECT_ID('loai_sanpham', 'U') IS NOT NULL DROP TABLE loai_sanpham;
CREATE TABLE loai_sanpham (
    ma_loai INT IDENTITY(1,1) PRIMARY KEY,
    ten_loai VARCHAR(100) NOT NULL
);
GO

-- Tạo bảng sản phẩm
IF OBJECT_ID('sanpham', 'U') IS NOT NULL DROP TABLE sanpham;
CREATE TABLE sanpham (
    ma_sp INT IDENTITY(1,1) PRIMARY KEY,
    ten_sp VARCHAR(100) NOT NULL,
    don_gia FLOAT,
    so_luong INT,
    ma_loai INT FOREIGN KEY REFERENCES loai_sanpham(ma_loai)
);
GO

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
