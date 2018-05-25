package de.rieckpil.learning.springboot2book;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest
public class SecuredControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getGreetingShouldWork() throws Exception{
        mockMvc.perform(get("/greeting").with(user("Tester"))).andExpect(content().string("Hello, Tester."));
    }

}