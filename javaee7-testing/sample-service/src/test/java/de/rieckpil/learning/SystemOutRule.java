package de.rieckpil.learning;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class SystemOutRule implements TestRule {

	@Override
	public Statement apply(Statement base, Description description) {
		return new Statement() {

			@Override
			public void evaluate() throws Throwable {
				base.evaluate();
				System.out.println(description.getClassName() + "-" + description.getMethodName() + " tested");
			}

		};
	}

}
