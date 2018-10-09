package de.rieckpil.learning;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.rieckpil.learning.injection.LoggerProducer;

@RunWith(Arquillian.class)
public class LoggerIntegrationTest {
	
	@Inject
	LoggerTestSupport loggerTest;

	@Deployment
	public static WebArchive create() {
		return ShrinkWrap.create(WebArchive.class).addClasses(LoggerProducer.class, LoggerTestSupport.class, Logger.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void testLoggerConfiguration() {
		String expectedName = loggerTest.getClass().getName();
		String actualName = loggerTest.getLog().getName();
		assertThat(actualName, is(expectedName));
	}

}
