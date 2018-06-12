package ca.bell.eside.client.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import ca.bell.eside.client.template.UserCredentials;

public class EnvelopeUtility implements UserCredentials{
	
	

	public byte[] getDocument() {
		String fileName = SignTest1File;
		return getDocument(fileName);
	}
	
	public byte[] getDocument(String fileName) {
		byte[] fileBytes = null;
		try {
			Path path = Paths.get(userDir + fileName);
			fileBytes = Files.readAllBytes(path);
		} catch (IOException e) {
			System.out.println("Exception: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
		return fileBytes;
	}
	

}
