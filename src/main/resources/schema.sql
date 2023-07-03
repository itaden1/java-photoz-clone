create table if not exists photos (
    id IDENTITY PRIMARY KEY NOT NULL,
    file_name varchar(255) NOT NULL,
    content_type varchar(255) NOT NULL,
    data binary varying
);

create table if not exists users (
    id IDENTITY PRIMARY KEY NOT NULL,
    email_address varchar(255) NOT NULL UNIQUE,
    password varchar(255) NOT NULL
);
drop table if exists auth_tokens;
create table if not exists auth_tokens (
    id IDENTITY NOT NULL,
    user_id int NOT NULL,
    token UUID NOT NULL UNIQUE,
    created_date_time timestamp,
    token_expiry timestamp,
    refresh_token UUID NOT NULL,
    refresh_token_expiry timestamp,

    CONSTRAINT fk_auth_tokens_users FOREIGN KEY (id) REFERENCES users(id)
)