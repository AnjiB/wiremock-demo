package com.anji.mock.wiremockdemo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResourceUtil {

	public static String getFileContent(String fileName) throws IOException {
		
		String content = null;
		
		try (InputStream is = ResourceUtil.class.getResourceAsStream(fileName)) {
			content = readFromStream(is);
		}
		
		return content;
	}
	
	
	private static String readFromStream(InputStream is) throws IOException {
		StringBuilder sBuilder = new StringBuilder();
		try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			String line;
			while((line = br.readLine())!= null) {
				sBuilder.append(line).append('\n');
			}
		}
		
		return sBuilder.toString();
	}
	
}
