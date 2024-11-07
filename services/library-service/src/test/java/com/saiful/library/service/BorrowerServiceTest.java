package com.saiful.library.service;

import com.saiful.library.domain.RegisterBorrower;
import com.saiful.library.exception.BorrowerException;
import com.saiful.library.repository.BorrowerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BorrowerServiceTest {
    private BorrowerService borrowerService;

    @Mock
    private BorrowerRepository borrowerRepository;

    @Test
    void testRegisterShouldSuccess(){
        borrowerService = new BorrowerServiceImpl(borrowerRepository);
        Mockito.when(borrowerRepository.isEmailExist("test@email.com"))
                .thenReturn(false);
        var request = new RegisterBorrower();
        request.setEmail("test@email.com");
        request.setName("john wick");
        borrowerService.register(request);
    }

    @Test
    void testRegisterFailedEmailExist(){
        borrowerService = new BorrowerServiceImpl(borrowerRepository);
        Mockito.when(borrowerRepository.isEmailExist("test@email.com"))
                .thenReturn(true);
        var request = new RegisterBorrower();
        request.setEmail("test@email.com");
        request.setName("john wick");
        assertThrows(BorrowerException.class, () -> borrowerService.register(request));
    }

}