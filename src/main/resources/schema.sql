drop table if exists auth_tokens;
drop table if exists photos;
drop table if exists follow;
drop table if exists users;

create table if not exists users (
    id IDENTITY PRIMARY KEY NOT NULL,
    email_address varchar(255) NOT NULL UNIQUE,
    password varchar(255) NOT NULL
);

create table if not exists follow (
    id IDENTITY PRIMARY KEY NOT NULL,
    follower_id int NOT NULL,
    following_id int NOT NULL,
    created timestamp,
    accepted BOOLEAN,
    accepted_date_time timestamp,
    FOREIGN KEY(FOLLOWER_ID) REFERENCES USERS(id),
    FOREIGN KEY (FOLLOWING_ID) REFERENCES USERS(id)
);

create table if not exists photos (
    id IDENTITY PRIMARY KEY NOT NULL,
    user_id int NOT NULL,
    file_name varchar(255) NOT NULL,
    content_type varchar(255) NOT NULL,
    data binary varying,
    FOREIGN KEY(USER_ID) REFERENCES USERS(id)
);

create table if not exists auth_tokens (
    id IDENTITY NOT NULL,
    user_id int NOT NULL,
    token varchar(32) NOT NULL UNIQUE,
    created_date_time timestamp,
    token_expiry timestamp,
    refresh_token varchar(32) NOT NULL,
    refresh_token_expiry timestamp,
    FOREIGN KEY(USER_ID) REFERENCES USERS(id)
)