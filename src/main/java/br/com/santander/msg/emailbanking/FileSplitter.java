package br.com.santander.msg.emailbanking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.santander.msg.emailbanking.util.TrailerReader;

public class FileSplitter {
	private static final Logger logger = LoggerFactory.getLogger(FileSplitter.class);
	private String labelSystem;
	private String profileName;
	private String projectName;
	private int numberOfRecords;
	private String inputPath;
	private String outputPath;

	public FileSplitter(String labelSystem, String profileName, String projectName, int numberOfRecords,
			String inputPath, String outputPath) {
		this.labelSystem = labelSystem;
		this.profileName = profileName;
		this.projectName = projectName;
		this.numberOfRecords = numberOfRecords;
		this.inputPath = inputPath;
		this.outputPath = outputPath;
	}

	public void split() throws IOException {
		String inputFileName = this.getInputFileName();
		String outputFolder = this.getOutputFolder();

		BufferedReader br = null;
		FileReader fr = null;

		long startTime = System.currentTimeMillis();
		

		logger.info("Lendo arquivo {} com tamanho de {} bytes.", inputFileName, new File(inputFileName).length());

		// pega o trailer para escrever em todos os arquivos splitados
		String trailer = TrailerReader.readTrailer(inputFileName);

		try {
			fr = new FileReader(inputFileName);
			br = new BufferedReader(fr);
			String sCurrentLine = null;
			String header = br.readLine();// Le o header

			String outputFileNamePattern = this.projectName + "_%07d." + this.labelSystem;
			FileOutput fileOutput = new FileOutput(outputFolder, outputFileNamePattern, header, trailer, this.numberOfRecords);
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

		long endTime = System.currentTimeMillis();

		logger.info("O split do arquivo {} levou {} milesegundos.", inputFileName, (endTime - startTime));
	}

	private String getOutputFolder() {
		return String.format("%s/%s/Input/%s.1/", outputPath, projectName, profileName);
	}

	private String getInputFileName() {
		return String.format("%s/%s/Input/%s.%s", inputPath, projectName, projectName, labelSystem);
	}

}
