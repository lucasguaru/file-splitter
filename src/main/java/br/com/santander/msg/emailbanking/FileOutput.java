package br.com.santander.msg.emailbanking;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class FileOutput {
	
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	private String header;
	private String trailer;
	private int lineCount;
	private int recordCount;
	private int fileCount;
	private Writer writer;
	private int recordQty;

	public FileOutput(String header, String trailer, int recordQty) {
		this.header = header;
		this.trailer = trailer;
		this.recordQty = recordQty;
		this.fileCount = 0;
		newFile();
	}

	public void append(String line) {
		// TODO Auto-generated method stub
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
				this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("filename" + this.fileCount + ".txt"), "UTF-8"));
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
