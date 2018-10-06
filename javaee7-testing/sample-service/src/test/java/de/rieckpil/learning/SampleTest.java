package de.rieckpil.learning;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.CustomMatcher;
import org.hamcrest.Matcher;
import org.junit.Test;

public class SampleTest {

	@Test
	public void testLists() {
		List<String> stringList = Arrays.asList("java", "pythona", "dukea");
		assertThat(stringList, hasItem("java"));
		assertThat(stringList, hasItems("java", "pythona"));
		assertThat(stringList, everyItem(containsString("a")));
		assertThat(stringList, both(hasItem("java")).and(hasItem("dukea")));
		assertThat(stringList, either(hasItem("java1")).or(hasItem("dukea")));
	}

	@Test
	public void testWithHamcrest() {
		String result = "HelloWorld!";
		assertThat(result, is("HelloWorld!"));
	}

	@Test
	public void customMatcher() {
		Matcher<String> containsJ = new CustomMatcher<String>("contains j") {

			@Override
			public boolean matches(Object item) {
				if (!(item instanceof String)) {
					return false;
				}

				String content = (String) item;
				return content.contains("j");
			}
		};
		
		assertThat("java", containsJ);
	}

}
