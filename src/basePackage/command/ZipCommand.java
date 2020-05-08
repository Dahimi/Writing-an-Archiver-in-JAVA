package basePackage.command;

import java.nio.file.Path;
import java.nio.file.Paths;

import basePackage.*;
import basePackage.utilities.*;

 
public abstract class ZipCommand implements Command {
	public ZipFileManager getZipFileManager() throws Exception{
		ConsoleHelper.writeMessage("Enter the zip file full path ");
		Path zipFile = Paths.get(ConsoleHelper.readString());
		ZipFileManager zipFileManager = new ZipFileManager(zipFile);
		return zipFileManager;
	}
}
