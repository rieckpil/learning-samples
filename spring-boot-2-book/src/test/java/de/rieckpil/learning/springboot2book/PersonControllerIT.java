package de.rieckpil.learning.springboot2book;

import de.rieckpil.learning.springboot2book.entities.Person;
import de.rieckpil.learning.springboot2book.repositories.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Year;
import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = SecuredService.class
))
public class PersonControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepository personRepository;

    @Test
    public void testUnauthorizedAccessToAllPersons() throws Exception {

        Person p = new Person();
        p.setLastName("Riecks");
        p.setAge(22);
        p.setId(1L);

        when(personRepository.findAll()).thenReturn(Arrays.asList(p));

        mockMvc.perform(get("/persons")).andExpect(status().isUnauthorized());

    }

    @Test
    @WithMockUser(
            username = "rieckpil",
            roles = {"USER", "ADMIN", "SUPERADMIN"}
    )
    public void testAuthorizedAccessToAllPersons() throws Exception {

        int age = 22;
        String lastName = "Meier";

        Person p = new Person();
        p.setLastName(lastName);
        p.setAge(age);
        p.setId(1L);

        when(personRepository.findAll()).thenReturn(Arrays.asList(p));

        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].lastName", equalTo(lastName.toUpperCase())))
                .andExpect(jsonPath("$[0].id", equalTo(1)))
                .andExpect(jsonPath("$[0].dob", equalTo(Year.now().getValue() - age)));

    }
}
