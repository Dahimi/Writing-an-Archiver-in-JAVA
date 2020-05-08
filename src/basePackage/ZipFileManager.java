package basePackage;

import java.io.*;
import java.nio.file.*;
import java.util.zip.*;

import basePackage.exception.PathNotFoundException;

public class ZipFileManager {
	private Path zipFile ;

	public ZipFileManager(Path zipFile) {
		super();
		this.zipFile = zipFile;
	}

	public Path getZipFile() {
		return zipFile;
	}

	public void setZipFile(Path zipFile) {
		this.zipFile = zipFile;
	}
	public void createZip(Path source) throws Exception{
		if(! Files.exists(zipFile.getParent())) Files.createDirectories(zipFile.getParent());
		try (ZipOutputStream zipOutput = new ZipOutputStream( Files.newOutputStream(zipFile));){
			/*ZipEntry zipEntry = new ZipEntry(source.getFileName().toString());
			zipOutput.putNextEntry(zipEntry);
			Files.copy(source, zipOutput);
			zipOutput.closeEntry();*/
			
			if(Files.isRegularFile(source))
			addNewZipEntry(zipOutput, source.getParent(), source.getFileName());
			else if(Files.isDirectory(source)) {
				FileManager fileManager = new FileManager(source);
				for(Path file : fileManager.getFileList()) {
					addNewZipEntry(zipOutput, source, file);
				}
			}
			else throw new PathNotFoundException() ;
		}
		
	}
	 private void addNewZipEntry(ZipOutputStream zipOutputStream, Path filePath, Path fileName) throws Exception{
		try( InputStream input = Files.newInputStream(filePath.resolve(fileName));){
			ZipEntry zipEntry = new ZipEntry(fileName.toString());
			zipOutputStream.putNextEntry(zipEntry);
			//Files.copy(source, zipOutput);
			copyData(input, zipOutputStream);
			zipOutputStream.closeEntry();
		}
	 }
	 private void copyData(InputStream in, OutputStream out) throws Exception{
		 while(in.available() > 0 ){
				out.write(in.read());
			}
	 }
	
}
