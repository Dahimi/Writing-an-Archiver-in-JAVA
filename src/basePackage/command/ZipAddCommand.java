package basePackage.command;

import java.nio.file.Path;
import java.nio.file.Paths;

import basePackage.ZipFileManager;
import basePackage.exception.PathNotFoundException;
import basePackage.utilities.ConsoleHelper;

public class ZipAddCommand extends ZipCommand {

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		try {
			
		
		ConsoleHelper.writeMessage("Adding a file.");
		ZipFileManager zipFileManager =  getZipFileManager();
		ConsoleHelper.writeMessage(" enter the full path to the file or directory to be added");
		Path file = Paths.get(ConsoleHelper.readString());
		zipFileManager.addFile(file);
		ConsoleHelper.writeMessage( "Adding operation is done");
		}
		catch(PathNotFoundException  ex) {
			ConsoleHelper.writeMessage("You didn't correctly enter a file name or directory.");
		}
	}

}
