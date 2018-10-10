package de.rieckpil.learning;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gargoylesoftware.htmlunit.WebConsole.Logger;

import de.rieckpil.learning.injection.DataResolver;
import de.rieckpil.learning.injection.LoggerProducer;
import de.rieckpil.learning.injection.SuperService;

@RunWith(Arquillian.class)
public class InjectEmAllTest {

	@Inject
	SuperService superService;

	@Deployment
	public static WebArchive create() {
		return ShrinkWrap.create(WebArchive.class)
				.addClasses(SuperService.class, DataResolver.class, LoggerProducer.class, Logger.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void testInjection() {
		assertEquals("Hello", superService.doFoo());
	}

}
