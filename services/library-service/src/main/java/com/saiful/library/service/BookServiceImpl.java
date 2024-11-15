package com.saiful.library.service;

import com.saiful.library.converter.BookConverter;
import com.saiful.library.domain.Book;
import com.saiful.library.domain.RegisterBook;
import com.saiful.library.entity.BookEntity;
import com.saiful.library.entity.BookStatus;
import com.saiful.library.exception.BookException;
import com.saiful.library.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Long register(RegisterBook request) {
        log.info("register new book {} - {}", request.getIsbn(), request.getTitle());
        if(!isValidIsbn(request)){
            throw new BookException("invalid isbn, existing author and title miss match", HttpStatus.BAD_REQUEST);
        }

        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthor(request.getAuthor());
        bookEntity.setTitle(request.getTitle());
        bookEntity.setIsbn(request.getIsbn());
        bookEntity.setBookStatus(BookStatus.AVAILABLE);

        bookRepository.save(bookEntity);
        return bookEntity.getId();
    }

    @Override
    public Page<Book> searchBook(Integer size, Integer page) {
        return bookRepository.findAll( PageRequest.of(page, size))
                    .map(BookConverter::convert);
    }

    private boolean isValidIsbn(RegisterBook request){
        Page<BookEntity> existingIsbn = bookRepository.findAllByIsbn(request.getIsbn(),
                PageRequest.of(0, 1,Sort.by(Sort.Direction.DESC, "id")));
        if (!existingIsbn.isEmpty()){
            BookEntity existingEntity = existingIsbn.getContent().get(0);
            return existingEntity.getTitle().equalsIgnoreCase(request.getTitle()) &&
                    existingEntity.getAuthor().equalsIgnoreCase(request.getAuthor());
        }
        return true;
    }
}
