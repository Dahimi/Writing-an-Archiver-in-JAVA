package basePackage;

import java.io.*; 
import java.nio.file.*;

import basePackage.command.*;
import basePackage.exception.NoSuchZipFileException;
import basePackage.utilities.*;

public class Archiver {
	
	public static void main(String[] args) throws Exception {
	/*	Path zipFile ;
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));){
			ConsoleHelper.writeMessage("Enter the zipFile full path ");
			zipFile = Paths.get(reader.readLine());
		
		ZipFileManager zipFileManager = new ZipFileManager(zipFile);
		ConsoleHelper.writeMessage("Enter the full path of the file to be zipped");
		Path fileToBeZipped = Paths.get(reader.readLine());
		zipFileManager.createZip(fileToBeZipped);
		Command exitCommand = new ExitCommand();
		exitCommand.execute();
		
		
		}*/
		Operation operation = null ;
	
		do {
			try {
			operation = askOperation();
			CommandExecutor.execute(operation);
			}
			catch(NoSuchZipFileException  e1) {
				ConsoleHelper.writeMessage("You didn't select an archive or you selected an invalid file.");
			}
			catch(Exception e2) {
			
				ConsoleHelper.writeMessage( "An error occurred. Please check the entered data.");
			}
		}while(operation != Operation.EXIT);
	}
	public static Operation askOperation() throws IOException{
		ConsoleHelper.writeMessage("Select an operation:" + Operation.CREATE.ordinal()+ " - Zip files into an archive");
		ConsoleHelper.writeMessage(Operation.ADD.ordinal() + " - Add a file to an archive");
		ConsoleHelper.writeMessage(Operation.REMOVE.ordinal() + " - Remove a file from an archive");
		ConsoleHelper.writeMessage(Operation.EXTRACT.ordinal() + " - Extract an archive");
		ConsoleHelper.writeMessage(Operation.CONTENT.ordinal() + " - View the contents of an archive");
		ConsoleHelper.writeMessage(Operation.EXIT.ordinal() + " - Exit");
		switch(ConsoleHelper.readInt()) {
		case 1 : return Operation.ADD;
		case 2 : return Operation.REMOVE;
		case 3 : return Operation.EXTRACT;
		case 4 : return Operation.CONTENT;
		case 0 : return Operation.CREATE;
		case 5 : return Operation.EXIT;
		default : askOperation();
		}
		 
		return null;
	}
}
