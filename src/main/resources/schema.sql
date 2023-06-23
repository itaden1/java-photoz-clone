create table if not exists photoz (
    id IDENTITY PRIMARY KEY NOT NULL,
    file_name varchar(255) NOT NULL,
    content_type varchar(255) NOT NULL,
    data binary varying
);

create table if not exists users (
    id IDENTITY PRIMARY KEY NOT NULL,
    email_address varchar(255) NOT NULL,
    password varchar(255) NOT NULL
);
insert into users (email_address, password) values ('admin@test.com', 'password');