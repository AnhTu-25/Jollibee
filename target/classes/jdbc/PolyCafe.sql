
    CREATE DATABASE jollibee;

GO

USE jollibee;
GO

-- Tạo bảng loại sản phẩm
IF OBJECT_ID('loai_sanpham', 'U') IS NOT NULL DROP TABLE loai_sanpham;
CREATE TABLE loai_sanpham (
    MaLH INT IDENTITY(1,1) PRIMARY KEY,
    TenLH VARCHAR(100) NOT NULL
);
GO

-- Tạo bảng sản phẩm
IF OBJECT_ID('sanpham', 'U') IS NOT NULL DROP TABLE sanpham;
CREATE TABLE sanpham (
    MaSP INT IDENTITY(1,1) PRIMARY KEY,
    TenSP VARCHAR(100) NOT NULL,
    DonGia FLOAT,
    SoLuong INT,
    MaLH INT FOREIGN KEY REFERENCES loai_sanpham(MaLH)
);
GO