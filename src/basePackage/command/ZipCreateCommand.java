package basePackage.command;
import basePackage.utilities.ConsoleHelper;

import java.nio.file.Path;
import java.nio.file.Paths;

import basePackage.*;
import basePackage.exception.PathNotFoundException;
public class ZipCreateCommand extends ZipCommand {

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		try {
		ConsoleHelper.writeMessage("Creating an archive.");
		ZipFileManager zipFileManager =  getZipFileManager();
		ConsoleHelper.writeMessage(" enter the full path to the file or directory to be zipped");
		Path file = Paths.get(ConsoleHelper.readString());
		zipFileManager.createZip(file);
		ConsoleHelper.writeMessage( "Archive created.");
		}
		catch(PathNotFoundException  ex) {
			ConsoleHelper.writeMessage("You didn't correctly enter a file name or directory.");
		}
	}

}
