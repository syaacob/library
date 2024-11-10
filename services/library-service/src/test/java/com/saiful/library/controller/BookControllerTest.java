package com.saiful.library.controller;

import com.saiful.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = BookController.class)
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void testRegisterBookShouldSuccess() throws Exception{
        String json = "{\"title\" : \"test title\", \"isbn\": \"9781399610094\", \"author\" : \"jonathan wilson\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void testRegisterBookShouldFailedInvalidIsbnFormat() throws Exception{
        String json = "{\"title\" : \"test title\", \"isbn\": \"9781\", \"author\" : \"jonathan wilson\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

}