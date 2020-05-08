package basePackage;

import java.util.*;

import basePackage.command.*;

public class CommandExecutor {
	
	private static final Map<Operation, Command>	allKnownCommandsMap = new HashMap<Operation, Command>(){
		{
			put(Operation.ADD, new ZipAddCommand());
			put(Operation.REMOVE,new ZipRemoveCommand());
			put(Operation.CREATE,new ZipCreateCommand());
			put(Operation.CONTENT,new ZipContentCommand());
			put(Operation.EXTRACT,new ZipExtractCommand());
			put(Operation.EXIT,new ExitCommand());
			
		}
	};
	private CommandExecutor(){
       
    }
    public static void execute(Operation operation) throws Exception{
         allKnownCommandsMap.get(operation).execute();
    }
}
