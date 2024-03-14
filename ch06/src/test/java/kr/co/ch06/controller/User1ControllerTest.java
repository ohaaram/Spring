package kr.co.ch06.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = User1Controller.class)
class User1ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
     void list() throws Exception {

        mockMvc.perform(get("/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("/list"))
                .andDo(print());
    }


    void register() {
    }


    void testRegister() {
    }


    void modify() {
    }


    void testModify() {
    }


    void delete() {
    }
}