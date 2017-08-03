package br.com.santander.msg.emailbanking;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import br.com.santander.msg.emailbanking.util.TrailerReader;

public class FileSplitter {
	
	public static void split(String filename) throws IOException {
		BufferedReader br = null;
		FileReader fr = null;
		//pega o trailer para escrever em todos os arquivos splitados
		String trailer = TrailerReader.readTrailer(filename);
		
		try {
			fr = new FileReader(filename);
			br = new BufferedReader(fr);
			String sCurrentLine = null;
			String header = br.readLine();//Le o header
			
			FileOutput fileOutput = new FileOutput(header, trailer, 2);
			while ((sCurrentLine = br.readLine()) != null) {
				fileOutput.append(sCurrentLine);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}
	}

}
