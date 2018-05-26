package de.rieckpil.learning.springboot2bookdata.transactions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTableWhere;


@RunWith(SpringRunner.class)
@JdbcTest(includeFilters = @ComponentScan.Filter(value = Service.class))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Rollback(false)
    public void tryInsertShouldWork() {

        try {
            transactionService.tryInsert();
        }catch (RuntimeException e){
            assertThat(e.getMessage(), is("Ooops... an error happened!"));
        }

        assertThat(countRowsInTableWhere(jdbcTemplate, "person", "name like 'Mike%'"), is(0));
    }

    @Test
    public void doInsertShouldWork() {
        transactionService.doInsert();
        assertThat(countRowsInTableWhere(jdbcTemplate, "person", "name like 'Mike%'"), is(1));
    }

}