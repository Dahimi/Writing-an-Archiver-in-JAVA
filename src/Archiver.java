import java.io.*;
import java.nio.file.*;

import command.*;
import utilities.*;

public class Archiver {
	
	public static void main(String[] args) throws Exception {
		Path zipFile ;
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));){
			ConsoleHelper.writeMessage("Enter the zipFile full path ");
			zipFile = Paths.get(reader.readLine());
		
		ZipFileManager zipFileManager = new ZipFileManager(zipFile);
		ConsoleHelper.writeMessage("Enter the full path of the file to be zipped");
		Path fileToBeZipped = Paths.get(reader.readLine());
		zipFileManager.createZip(fileToBeZipped);
		Command exitCommand = new ExitCommand();
		exitCommand.execute();
		}
	}
}
