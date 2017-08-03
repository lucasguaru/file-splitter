package br.com.santander.msg.emailbanking;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileOutput {
	private static final Logger logger = LoggerFactory.getLogger(FileOutput.class);
	
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	private String header;
	private String trailer;
	private int lineCount;
	private int totalLineCount;
	private int recordCount;
	private int fileCount;
	private Writer writer;
	private int recordQty;
	private String outputFolder;
	private String outputFileNamePattern;

	public FileOutput(String outputFolder, String outputFileNamePattern, String header, String trailer, int recordQty) {
		this.outputFolder = outputFolder;
		this.outputFileNamePattern = outputFileNamePattern;
		this.header = header;
		this.trailer = trailer;
		this.recordQty = recordQty;
		this.fileCount = 0;
		newFile();
	}

	public void append(String line) {
		this.totalLineCount++;
		if (line.startsWith("0")) {// Linha de dados
			this.lineCount++;
			this.write(line);
		} else if (line.startsWith("98")) {// Fim de registro
			this.lineCount++;
			this.write(line);
			recordCount++;
			saveRegister();
		} else if (line.startsWith("99")) {// Trailer fim de arquivo
			this.writeTrailer();
			saveFile();
			logger.debug("Trailer atingido com registro iniciado por '99'. Total de {} linhas lidas no arquivo", this.totalLineCount);
		}
	}

	private void writeTrailer() {
		if (this.lineCount > 0) {
			this.write(this.trailer);
		}
	}

	private void saveRegister() {
		if (this.recordCount >= this.recordQty) {
			this.writeTrailer();
			saveFile();
			newFile();
		} else {
			try {
				this.writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void write(String line) {
		try {
			if (this.lineCount == 1) {
				String filename = this.outputFolder + "\\" + String.format(this.outputFileNamePattern, this.fileCount);
				logger.debug("Criando novo arquivo[{}] de nome {}", this.fileCount, filename);
				this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "UTF-8"));
				this.writer.write(header + LINE_SEPARATOR);
			}
			this.writer.write(line + LINE_SEPARATOR);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveFile() {
		try {
			if (this.lineCount > 0) {
				logger.debug("Salvando e fechando arquivo [{}]", this.fileCount);
				this.writer.flush();
				this.writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void newFile() {
		this.recordCount = 0;
		this.fileCount++;
		this.lineCount = 0;
	}

}
