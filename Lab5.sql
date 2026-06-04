USE master;
GO

CREATE DATABASE PolyOE;
GO

USE PolyOE;
GO

CREATE TABLE Users(
    Id NVARCHAR(20) NOT NULL,
    Password NVARCHAR(50) NOT NULL,
    Fullname NVARCHAR(50) NOT NULL,
    Email NVARCHAR(50) NOT NULL,
    Admin BIT NOT NULL,
    PRIMARY KEY(Id)
);
GO

INSERT INTO Users VALUES ('U01', '123', N'Nguyễn Văn A', 'a@gmail.com', 0);
INSERT INTO Users VALUES ('U02', '456', N'Trần Thị B', 'b@fpt.edu.vn', 0);
INSERT INTO Users VALUES ('U03', '789', N'Admin User', 'admin@fpt.edu.vn', 1);
INSERT INTO Users VALUES ('U04', 'abc', N'Lê Văn C', 'c@yahoo.com', 0);
GO