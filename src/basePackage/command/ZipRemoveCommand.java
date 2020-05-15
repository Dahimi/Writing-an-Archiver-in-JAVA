package basePackage.command;

import java.nio.file.Path;
import java.nio.file.Paths;

import basePackage.ZipFileManager;
import basePackage.exception.PathNotFoundException;
import basePackage.utilities.ConsoleHelper;

public class ZipRemoveCommand extends ZipCommand {

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		
			ConsoleHelper.writeMessage("Removing a file.");
			ZipFileManager zipFileManager =  getZipFileManager();
			ConsoleHelper.writeMessage(" enter the relative path to the file or directory to be removed");
			Path file = Paths.get(ConsoleHelper.readString());
			zipFileManager.removeFile(file);
			ConsoleHelper.writeMessage( "Removing operation is done");
			
			
	}

}
