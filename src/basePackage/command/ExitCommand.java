package basePackage.command;
import basePackage.utilities.*;
public class ExitCommand implements Command {

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		ConsoleHelper.writeMessage("Bye !");
	}

}
