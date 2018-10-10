package de.rieckpil.learning;

import javax.enterprise.inject.Model;

@Model
public class Index {

	private String input;
	private String output;

	public Object ok() {
		this.output = this.input;
		System.out.println("output: " + output);
		return null;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

}
