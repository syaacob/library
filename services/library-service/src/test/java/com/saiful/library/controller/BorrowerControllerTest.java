package com.saiful.library.controller;

import com.saiful.library.service.BorrowerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = BorrowerController.class)
class BorrowerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BorrowerService borrowerService;

    @Test
    void testSaveBorrowerShouldSuccess() throws Exception {
        String json = "{\"name\": \"john wick\", \"email\": \"john.w@libary.test\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/borrowers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    void testSaveBorrowerNullNameShouldFailed() throws Exception {
        String json = "{\"email\": \"john.w@libary.test\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/borrowers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }

    @Test
    void testSaveBorrowerInvalidEmailShouldFailed() throws Exception {
        String json = "{\"name\": \"john wick\", \"email\": \"john.w_libary.test\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/borrowers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void testListAllBorrowersShouldSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/borrowers")
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

}