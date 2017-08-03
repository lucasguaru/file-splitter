package br.com.santander.msg.emailbanking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import br.com.santander.msg.emailbanking.util.TrailerReader;


public class TrailerReaderTest {

	@Test
	public void testReadTrailer() {
		String filename = "C:\\ambiente-de-trabalho\\workspace\\file-splitter\\src\\main\\resources\\input.txt";
		try {
			String trailer = TrailerReader.readTrailer(filename);
			assertEquals("99TRAILER", trailer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

}
