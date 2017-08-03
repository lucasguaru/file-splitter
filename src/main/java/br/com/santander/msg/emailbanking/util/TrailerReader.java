package br.com.santander.msg.emailbanking.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.input.ReversedLinesFileReader;

public class TrailerReader {
	
	public static String readTrailer(String filename) throws IOException {
			ReversedLinesFileReader fileReader = new ReversedLinesFileReader(new File(filename), StandardCharsets.UTF_8);
			String line = fileReader.readLine();
			fileReader.close();
			return line;
	}
	
	
}
