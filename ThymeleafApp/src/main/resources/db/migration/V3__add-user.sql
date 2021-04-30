create table users(username varchar(100) not null primary key, encoded_password varchar(255));
insert into users(username, encoded_password)
values('user1', '75fad10202d564a75f9f178357f5548824988580a0e1e46eb5861273e7bc17adb12a918d8380f0d6');
insert into users(username, encoded_password)
values('user2', '75fad10202d564a75f9f178357f5548824988580a0e1e46eb5861273e7bc17adb12a918d8380f0d6');
alter table customers add username varchar(100) not null default 'user1';
alter table customers add constraint fk_customers_username foreign key (username) references users;