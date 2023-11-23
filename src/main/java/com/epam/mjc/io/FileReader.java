package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileReader {

	public Profile getDataFromFile(File file) {
		String path = file.getPath();		
		String name = "", age = "", email = "", phone = "";
		try (FileInputStream is = new FileInputStream(path)) {
			String fileString = "";
			int ch;
			while ((ch = is.read()) != -1) {
				fileString += (char)ch;
			} 

			String[] profileValues = fileString.split("\n", 0);
			for (int i = 0; i < profileValues.length; ++i) {
				if (profileValues[i].contains("Name")) {
					name = profileValues[i].substring(profileValues[i].indexOf(":") + 2);
				} else if (profileValues[i].contains("Age")) {
					age = profileValues[i].substring(profileValues[i].indexOf(":") + 2);
				} else if (profileValues[i].contains("Email")) {
					email = profileValues[i].substring(profileValues[i].indexOf(":") + 2);
				} else if (profileValues[i].contains("Phone")) {
					phone = profileValues[i].substring(profileValues[i].indexOf(":") + 2);
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		Profile profile = new Profile(name, Integer.valueOf(age), email, Long.valueOf(phone));

		return profile;
	}
}
