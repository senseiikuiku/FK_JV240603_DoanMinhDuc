create database quanlythuvien;
use quanlythuvien;

create table BookType(
	TypeId int primary key auto_increment,
    TypeName varchar(50) not null unique,
    Description Text,
    isDeleted bit(0) default 0
);

create table Book(
	BookId int primary key auto_increment,
    BookName varchar(100) not null unique,
    Title varchar(200) not null,
    Author varchar(200) not null,
    TotalPages int not null check (TotalPages >0),
    Content text not null,
    Publisher varchar(100) not null, 
    Price decimal(10,2) not null check (Price >0),
    TypeId int not null,
    foreign key(TypeId) references BookType(TypeId),
    isDeleted bit default 0
);

select b.TypeName, count(Book.BookId) AS Total
from BookType b
Left join Book ON b.TypeId=Book.TypeId
Group by b.TypeName;

select *from BookType 
	