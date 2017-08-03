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
	
	public static void main(String[] args) {
		String filename = "C:\\ambiente-de-trabalho\\workspace\\file-splitter\\src\\main\\resources\\input.txt";
		try {
			String trailer = TrailerReader.readTrailer(filename);
			System.out.println(trailer);
			if (!trailer.equals("99TRAILER")) {
				System.err.println("Arquivo não leu como esperado");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
