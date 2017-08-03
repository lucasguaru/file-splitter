package br.com.santander.msg.emailbanking;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import br.com.santander.msg.emailbanking.util.TrailerReader;

public class FileReaderTest {

	@Test
	public void testReadTrailer() {
		String filename = "D:\\ambiente-de-trabalho\\workspace\\java-msgbat-email-banking-file-splitter\\src\\main\\resources\\input.txt";
		try {
			String trailer = TrailerReader.readTrailer(filename);
			assertEquals("TRAILER", trailer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

}
