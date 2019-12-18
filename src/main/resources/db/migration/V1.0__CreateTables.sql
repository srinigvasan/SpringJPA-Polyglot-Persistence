create table Product(productId int PRIMARY KEY AUTO_INCREMENT,productName varchar(100));
create table Reviews(reviewId int PRIMARY KEY AUTO_INCREMENT,reviewContent varchar(100),productId int);
create table Comments(commentId int PRIMARY KEY AUTO_INCREMENT,reviewId int,reviewComments varchar(100));
