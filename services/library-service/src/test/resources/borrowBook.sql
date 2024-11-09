insert into borrower(id, name, email) values(10, 'dexter', 'dexter@killer');
insert into borrower(id, name, email) values(20, 'bosch', 'bosch@detective');

insert into book(id, title, author, isbn,book_status, version) values(1000, 'with old breed', 'dexter@killer','9780891419198','AVAILABLE', 1);
insert into book(id, title, author, isbn,book_status, version) values(1001, 'with old breed', 'dexter@killer','9780891419198','BORROWED', 1);

insert into book(id, title, author, isbn,book_status, version) values(1002, 'how starbucks save my life', 'michael gill','9780007268863','BORROWED', 1);
insert into book_borrower(id,borrower_id,book_id,borrow_date, expected_return_date) values(3000, 20, 1002, '2024-11-09 11:20:19','2024-11-18 23:59:59');