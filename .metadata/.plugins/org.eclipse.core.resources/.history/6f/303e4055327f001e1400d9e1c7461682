#updated 07112023 Bookstore18 DELETED ANSWERS, use this script to create bookstorecommentsdb


SET FOREIGN_KEY_CHECKS=0;


DROP TABLE IF EXISTS `comment`;
DROP TABLE IF EXISTS category; 
DROP TABLE IF EXISTS book; 
DROP TABLE IF EXISTS application_users;

SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE application_users
(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
firstname VARCHAR(100) NOT NULL,
lastname VARCHAR(100) NOT NULL,
username VARCHAR(250) NOT NULL,
password_hash VARCHAR(250) NOT NULL,
`role`  VARCHAR(100) NOT NULL
);

#tunnus/salasana, esim admin/admin, john/john, majlis/majlis
INSERT INTO application_users (firstname, lastname, username, password_hash, `role`) 
VALUES ("", "", "user","$2a$10$RIqlxElPXzQKJayHKJwSNOxDMnMh.j.OHwQvOoPj0gld.sbXsqqgK" ,"USER"),
("", "", "admin", "$2a$10$aGjp6jEUEspwUkQrCbGAWuKScc9DRHTQ6LXMRX2TAM5A6tzHdy8/6", "ADMIN"), 
("", "", "huolto", "$2a$10$lDtQP3VTBBHPocsCga.a6.iqXrQq2S3.nlaWJieniRwYlOUjpttUS", "ADMIN"),
("John", "Smith", "john", "$2a$10$Y8mUEwuYmdcPNhAyYR0rM.JEPW5aRgszj8h.Bk58IXapvMVnL.zLO", "USER"),
("Maj-Lis", "säkerhetsbyggare", "majlis", "$2a$10$am1hN1A77jRbknyCYztw2.Fx.nz2iYEscVFg5fcHXBaA/ewyVxjki", "USER");

CREATE TABLE category(
categoryid BIGINT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL,
PRIMARY KEY (categoryid)
);

INSERT INTO category (`name`) 
VALUES ("It"),
("Ohjelmointi"),
("Tietokannat"),
("Pilvipalvelut");


CREATE TABLE book(
id BIGINT NOT NULL AUTO_INCREMENT,
title VARCHAR(300) NOT NULL,
author VARCHAR(100) NOT NULL,
isbn VARCHAR(20) NOT NULL,
publication_year BIGINT,
price DECIMAL(10,2),
categoryid BIGINT,
applicationuserid BIGINT,
PRIMARY KEY (id), 
FOREIGN KEY(categoryid) REFERENCES category(categoryid),
FOREIGN KEY (applicationuserid) REFERENCES application_users(id)
);

#INSERT INTO book (title, author, isbn, publication_year, price, categoryid) 
#VALUES ("Koodaajan käsikirja", "Hilda Koodinkirjoittaja", "132-312-123", 2000, 19.90, 2), 
#("Tämän haluat tietää pilvipalveluiden käyttöönotosta", "Pekka Palvelin", "401-404-403-201", 2010, 46.90, 4),
#("SQL-perusteet", "Teppo Tietokanta", "10-20-30-40", 2012, 34.90, 3),
#("Python3", "Douglas Django, Ethan Enum", "123-456-789-10", 2020, 54.90, 2),
#("Sulautetut järjestelmät", "Heikki Hiippari", "123-456-789-10", 2020, 54.90, 1);

INSERT INTO book (title, author, isbn, publication_year, price, categoryid, applicationuserid) 
VALUES ("Käyttäjän lisäämät kirjat", "Testataan appuserid fk", "123-456-789-10", 2020, 54.90, 1, 3),
("Käyttäjän lisäämät kirjat", "Testataan appuserid fk", "123-456-789-10", 2020, 54.90, 1, 2),
("Käyttäjän lisäämät kirjat", "Testataan appuserid fk", "123-456-789-10", 2020, 54.90, 1, 1),
("Koodaajan käsikirja", "Hilda Koodinkirjoittaja", "132-312-123", 2000, 19.90, 2, 1), 
("Tämän haluat tietää pilvipalveluiden käyttöönotosta", "Pekka Palvelin", "401-404-403-201", 2010, 46.90, 4, 1),
("SQL-perusteet", "Teppo Tietokanta", "10-20-30-40", 2012, 34.90, 3, 1),
("Python3", "Douglas Django, Ethan Enum", "123-456-789-10", 2020, 54.90, 2, 2),
("Sulautetut järjestelmät", "Heikki Hiippari", "123-456-789-10", 2020, 54.90, 1, 3);


CREATE TABLE `comment`(
commentid BIGINT NOT NULL AUTO_INCREMENT,
commentmessage VARCHAR(800) NOT NULL,
bookid BIGINT,
applicationuserid BIGINT,
commentcreated VARCHAR (20),
PRIMARY KEY (commentid),
FOREIGN KEY(bookid) REFERENCES book(id), 
FOREIGN KEY (applicationuserid) REFERENCES application_users(id)
);


INSERT INTO `comment` (commentmessage, bookid, applicationuserid, commentcreated)
VALUES ("Kommentti 1", 1,2,"2023-10-31 23:41:45"),
("Toka kommentti", 4,2,"2023-10-31 23:41:45"), 
("... ja vielä vähän kommentointia", 4,2,"2023-10-31 23:41:45"), 
("... ja vielä vähän lisää kommentointia", 3,2,"2023-10-31 23:41:45"),
("paljonpaljontekstiä ja vielä vähänlisäätekstiä jotta saadaan pitkä kommentti", 3,2,"2023-10-31 23:41:45"),
("paljonpaljontekstiä ja vielä vähänlisäätekstiä jotta saadaan vielä toinen pitkä kommentti", 5,1,"2023-10-31 23:41:45");










SELECT * FROM `comment` WHERE bookid=3;








