package basePackage;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class FileManager {
	private Path rootPath ;
	private  List<Path> fileList;
	public FileManager(Path rootPath) throws IOException {
		//super();
		this.rootPath = rootPath;
		fileList = new ArrayList<Path>();
		collectFileList(rootPath);
	}
	private void collectFileList(Path path) throws IOException{
		if(Files.isRegularFile(path)) fileList.add(rootPath.relativize(path));
		if(Files.isDirectory(path)) {
			try(DirectoryStream<Path> stream = Files.newDirectoryStream(path);){
				for(Path file : stream) {
					if(Files.isRegularFile(path)) fileList.add(rootPath.relativize(path));
					if(Files.isDirectory(path)) collectFileList(file);
				}
			}
		}
	}
	public List<Path> getFileList() {
		return fileList;
	}
}
