package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileReader {

	public Profile getDataFromFile(File file) {
		String path = file.getPath();		
		String name = "";
		String age = "";
		String email = "";
		String phone = "";
		try (FileInputStream is = new FileInputStream(path)) {
			StringBuilder fileString = new StringBuilder();
			int ch;
			while ((ch = is.read()) != -1) {
				fileString.append((char)ch);
			} 

			String[] profileValues = fileString.toString().split("\n", 0);
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

		return new Profile(name, Integer.valueOf(age), email, Long.valueOf(phone));
	}
}
