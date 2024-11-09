create sequence book_borrower_seq;
create table book_borrower(
    id bigint primary key,
    borrower_id bigint not null,
    book_id bigint not null,
    borrow_date timestamp not null,
    expected_return_date timestamp not null,
    actual_return_date timestamp,
    foreign key(borrower_id) references borrower(id),
    foreign key(book_id) references book(id)
);