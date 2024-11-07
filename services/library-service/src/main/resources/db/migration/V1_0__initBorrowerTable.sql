create sequence borrower_seq;
create table borrower(
    id bigint primary key,
    name varchar(50) not null,
    email varchar(50) not null
);
create unique index unique_email on borrower(email);