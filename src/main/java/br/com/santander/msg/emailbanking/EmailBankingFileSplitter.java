package br.com.santander.msg.emailbanking;

import java.io.IOException;

public class EmailBankingFileSplitter {

	public static void main(String[] args) {
		String filename = "D:\\ambiente-de-trabalho\\workspace\\java-msgbat-email-banking-file-splitter\\src\\main\\resources\\input.txt";
		try {
			FileSplitter.split(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
