create sequence book_seq;
create table book(
    id bigint primary key,
    title varchar(100) not null,
    author varchar(100) not null,
    isbn varchar(20) not null,
    book_status varchar(15) not null,
    version integer not null
);