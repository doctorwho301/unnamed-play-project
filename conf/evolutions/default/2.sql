# --- Sample data set

# --- !Ups

insert into category (id,name) values ( 1,'comic' );
insert into category (id,name) values ( 2,'Action Figure' );
insert into category (id,name) values ( 3,'Poster' );

insert into product (id,name,description,price) values ( 1,'Gwenpool #1','First issue of the Gwenpool comics',55.00 );
insert into product (id,name,description,price) values ( 2,'Superman','The man of steel in his trademarked pose',90.00 );



insert into user (email,name,username,password,role,address1,address2,city) values ( 'user@products.com', 'User Eugine', 'Me Me Eugine', 'password', 'user', '1 Meme St.', 'Dankville', 'de wey');
insert into user (email,name,username,password,role,address1,address2,city) values ( 'admin@products.com', 'Admin Aiden', 'Me Me Eugine', 'password', 'admin', '1 Meme St.', 'Dankville', 'de wey');

