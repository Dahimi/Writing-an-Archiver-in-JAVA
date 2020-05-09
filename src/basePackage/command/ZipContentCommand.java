package basePackage.command;

import basePackage.FileProperties;
import basePackage.ZipFileManager;
import basePackage.utilities.ConsoleHelper;

public class ZipContentCommand  extends ZipCommand {

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		ConsoleHelper.writeMessage("Viewing contents of the archive.");
		ZipFileManager zipFileManager =  getZipFileManager();
		ConsoleHelper.writeMessage("Archive contents:");
		for(FileProperties fileProperties : zipFileManager.getFileList()) {
			ConsoleHelper.writeMessage(fileProperties.toString());			
		}
		ConsoleHelper.writeMessage("Archive contents viewed.");
	}

}
