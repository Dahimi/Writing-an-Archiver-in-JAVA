package basePackage.command;

import java.nio.file.Path;
import java.nio.file.Paths;

import basePackage.ZipFileManager;
import basePackage.exception.PathNotFoundException;
import basePackage.utilities.ConsoleHelper;

public class ZipExtractCommand extends ZipCommand {

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				try {
				ConsoleHelper.writeMessage("Extracting an archive.");
				ZipFileManager zipFileManager =  getZipFileManager();
				ConsoleHelper.writeMessage(" enter the full path to the directory where the zip file will be extracted");
				Path dir = Paths.get(ConsoleHelper.readString());
				zipFileManager.extractAll(dir);
				ConsoleHelper.writeMessage( "Archive extracted.");
				}
				catch(PathNotFoundException  ex) {
					ConsoleHelper.writeMessage("You didn't correctly enter a file name or directory.");
				}
	}

}
