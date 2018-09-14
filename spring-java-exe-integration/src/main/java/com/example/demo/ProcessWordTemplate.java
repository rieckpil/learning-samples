package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.ThreadLocalRandom;

public class ProcessWordTemplate {

	private static final String PATH_TO_EXE = "C:\\Temp\\OfficeToPDF.exe";
	private static final String PATH_TO_TEMPLATE = "C:\\Temp\\TEMPLATE.docx";
	private static final String PATH_TO_OUTPUT = "C:\\Temp\\";

	public static void main(String[] args) throws IOException, InterruptedException {
//		new Thread(ProcessWordTemplate::processTemplate).start();
//		new Thread(ProcessWordTemplate::processTemplate).start();
//		new Thread(ProcessWordTemplate::processTemplate).start();
//		new Thread(ProcessWordTemplate::processTemplate).start();
//		new Thread(ProcessWordTemplate::processTemplate).start();

		processTemplate();
		processTemplate();
		processTemplate();
		processTemplate();
		processTemplate();
	}

	private static byte[] processTemplate() {
		long start = System.currentTimeMillis();
		String outputFilePath = PATH_TO_OUTPUT + "OUTPUT" + ThreadLocalRandom.current().nextLong() + ".pdf";
		Process process;
		try {
			process = new ProcessBuilder(PATH_TO_EXE, PATH_TO_TEMPLATE, outputFilePath).start();
			process.waitFor();

			System.out.println("Result of Office processing: " + process.exitValue());

			if (new File(outputFilePath).exists()) {
				File file = new File(outputFilePath);
				byte[] fileContent = Files.readAllBytes(file.toPath());
				System.out.println("Took: " + (System.currentTimeMillis() - start) / 1000 + " seconds to process");
				return fileContent;
			} else {
				System.out.println(outputFilePath + " does not exist");
			}
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
