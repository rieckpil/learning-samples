package de.rieckpil.learning;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public abstract class MvcTest {

    @Mock
    private BookService mockedBookService;

    @Before
    public void setup() {
        when(mockedBookService.getBooks()).thenReturn(List.of(new Book("Java 11", "Java", "42")));
        RestAssuredMockMvc.standaloneSetup(new BookController(mockedBookService));
    }
}
