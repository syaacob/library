package com.saiful.library.controller;

import com.saiful.library.service.BorrowBookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = BookBorrowerController.class)
class BookBorrowerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BorrowBookService borrowBookService;

    @Test
    void testBorrowBookShouldSuccess() throws Exception {
        String json ="{\"borrowerId\" :1,\"bookId\": 1000 }";
        mockMvc.perform(MockMvcRequestBuilders.post("/bookBorrowers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void testBorrowBookShouldFailedNoBorrowerId() throws Exception {
        String json ="{\"bookId\": 1000 }";
        mockMvc.perform(MockMvcRequestBuilders.post("/bookBorrowers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void testReturnBookShouldSuccess() throws Exception {
        String json ="{\"bookId\": 1000 }";
        mockMvc.perform(MockMvcRequestBuilders.post("/bookBorrowers/returns")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

}