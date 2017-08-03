package br.com.santander.msg.emailbanking;

import java.io.IOException;

public class EmailBankingFileSplitter {

	public static void main(String[] args) {
//		String filename = "D:\\ambiente-de-trabalho\\workspace\\java-msgbat-email-banking-file-splitter\\src\\main\\resources\\input.txt";
//		String filename = "C:\\MBB_App\\DFS\\EmailBanking - 230Mb.uy";
//		String filename = "C:\\MBB_App\\DFS\\EmailBanking.uy";
		String filename = "C:\\MBB_App\\DFS\\EmailBanking - 60.uy.bkp";
		
		String labelSystem = System.getProperty("labelSystem");
		String profileName = System.getProperty("profileName");
		String projectName = System.getProperty("projectName");
		int numberOfProfiles = Integer.parseInt(System.getProperty("numberOfProfiles"));
		int numberOfRecords = Integer.parseInt(System.getProperty("numberOfRecords"));
		String inputPath = System.getProperty("inputPath");
		String outputPath = "C:\\MBB_App\\DFS";
		String BASE_LOG_PATH = "C:\\MBB_App\\DFS\\logs\\";
		String runningMode = System.getProperty("runningMode");
		
		try {
			FileSplitter fileSplitter = new FileSplitter(labelSystem, profileName, projectName, numberOfRecords, inputPath, outputPath);
			fileSplitter.split();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
