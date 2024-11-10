package com.saiful.library.service;

import com.saiful.library.converter.BorrowerConverter;
import com.saiful.library.domain.Borrower;
import com.saiful.library.domain.RegisterBorrower;
import com.saiful.library.entity.BorrowerEntity;
import com.saiful.library.exception.BorrowerException;
import com.saiful.library.repository.BorrowerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BorrowerServiceImpl implements BorrowerService {
    private final BorrowerRepository borrowerRepository;

    public BorrowerServiceImpl(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    @Override
    public Long register(RegisterBorrower request) {
        log.info("registering {}", request.getName());
        if(borrowerRepository.isEmailExist(request.getEmail())){
            log.warn("email already exist {}", request.getEmail());
            throw new BorrowerException("email already exist", HttpStatus.BAD_REQUEST);
        }
        BorrowerEntity entity = new BorrowerEntity();
        entity.setEmail(request.getEmail());
        entity.setName(request.getName());

        borrowerRepository.save(entity);
        return entity.getId();
    }

    @Override
    public Page<Borrower> listAll(int page, int size) {
        return borrowerRepository.findAll(PageRequest.of(page, size))
                .map(BorrowerConverter::convert);
    }
}
