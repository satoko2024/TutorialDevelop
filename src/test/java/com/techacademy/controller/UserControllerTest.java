package com.techacademy.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.techacademy.entity.User;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = mockMvc;
    }

    @Test
    @WithMockUser  // Mockユーザーで認証済みにする
    void testGetList() throws Exception {
        MvcResult result = mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("userlist"))
                .andExpect(model().hasNoErrors())
                .andExpect(view().name("user/list"))
                .andReturn();

        @SuppressWarnings("unchecked")
        List<User> userList = (List<User>) result.getModelAndView().getModel().get("userlist");
        assertEquals(3, userList.size());

        assertEquals(1, userList.get(0).getId());
        assertEquals("キラメキ太郎", userList.get(0).getName());

        assertEquals(2, userList.get(1).getId());
        assertEquals("キラメキ次郎", userList.get(1).getName());

        assertEquals(3, userList.get(2).getId());
        assertEquals("キラメキ花子", userList.get(2).getName());
    }
}
