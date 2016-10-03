CREATE USER IF NOT EXISTS 'itechart'@'localhost' IDENTIFIED BY 'itechart';
CREATE DATABASE IF NOT EXISTS dmitry_kach_db;
GRANT ALL PRIVILEGES ON dmitry_kach_db. * TO 'itechart'@'localhost';