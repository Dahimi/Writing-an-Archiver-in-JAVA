package basePackage;

import java.io.*; 
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;

import basePackage.exception.NoSuchZipFileException;
import basePackage.exception.PathNotFoundException;
import basePackage.utilities.ConsoleHelper;

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
	public List<FileProperties> getFileList() throws Exception{
		if(!Files.isRegularFile(zipFile)) throw new NoSuchZipFileException();
		List<FileProperties> list = new LinkedList<FileProperties>();
		try(ZipInputStream zipInput = new ZipInputStream(Files.newInputStream(zipFile));){
			ZipEntry ze = null;
			while(	(ze = zipInput.getNextEntry()) != null) {
				ByteArrayOutputStream tempOut =   new ByteArrayOutputStream();
				 copyData(zipInput, tempOut);
				FileProperties properties = new FileProperties(ze.getName(), tempOut.size(), ze.getCompressedSize(),ze.getMethod());
				list.add(properties);
				zipInput.closeEntry();
			}
		}
		return list;
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
	public void extractAll(Path outputFolder) throws Exception{
			if (!Files.isRegularFile(zipFile)) {
				throw new NoSuchZipFileException();
			}
			if(!Files.exists(outputFolder)) Files.createDirectories(outputFolder);
			try( ZipInputStream ziptInputStream = new ZipInputStream(Files.newInputStream(zipFile));){
				ZipEntry ze = null; 				
				while((ze = ziptInputStream.getNextEntry())!= null) {
					Path fileRelativeName = Paths.get(ze.getName());
					extractNewZipEntry(ziptInputStream, outputFolder, fileRelativeName);
				}
			}
			
				
	}
	public void addFile(Path absolutePath) throws Exception{
		addFiles(Collections.singletonList(absolutePath));
	}
	public void addFiles(List<Path> absolutePathList) throws Exception{
		if (!Files.isRegularFile(zipFile)) {
			throw new NoSuchZipFileException();
		}
		Path tempArchive = Files.createTempFile("temp", "Archive");
		try( ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zipFile));
			 ZipOutputStream zipOutput = new ZipOutputStream( Files.newOutputStream(tempArchive));
			){
			ZipEntry ze = null;
			List<String> archiveFiles = new ArrayList<String>();
			while(	(ze = zipInputStream.getNextEntry()) != null) {
					Path file = Paths.get(ze.getName());
					archiveFiles.add(ze.getName());
					zipOutput.putNextEntry(new ZipEntry(file.toString()));
					copyData(zipInputStream, zipOutput);
					zipOutput.closeEntry();				   
				    zipInputStream.closeEntry();
			}
			for(Path addedFile : absolutePathList) {
				if(!Files.isRegularFile(addedFile )) throw new PathNotFoundException();
				if(archiveFiles.contains(addedFile.getFileName().toString())) ConsoleHelper.writeMessage("File already exist");
				else {
					addNewZipEntry(zipOutput, addedFile.getParent(), addedFile.getFileName());
				}
			}
		}
		Files.move(tempArchive, zipFile, StandardCopyOption.REPLACE_EXISTING);
	}
	public void removeFile(Path path) throws Exception{
		removeFiles(Collections.singletonList(path));
	}
	public void removeFiles(List<Path> pathList) throws Exception {
		if (!Files.isRegularFile(zipFile)) {
			throw new NoSuchZipFileException();
		}
		Path tempArchive = Files.createTempFile("temp", "Archive");
		try( ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zipFile));
			 ZipOutputStream zipOutput = new ZipOutputStream( Files.newOutputStream(tempArchive));
			){
			ZipEntry ze = null;
			while(	(ze = zipInputStream.getNextEntry()) != null) {
					Path file = Paths.get(ze.getName());
				   if(pathList.contains(file)) {
						ConsoleHelper.writeMessage("The file : " + file.getFileName() + "is removed from the archive");
						//continue ;
					}
				   else {
					   zipOutput.putNextEntry(new ZipEntry(file.toString()));
					   copyData(zipInputStream, zipOutput);
					   zipOutput.closeEntry();
				   }
				   zipInputStream.closeEntry();
			}
		}
		Files.move(tempArchive, zipFile, StandardCopyOption.REPLACE_EXISTING);
	}
	private List<Path> allArchiveFiles(ZipInputStream ziptInputStream) throws IOException{
		ZipEntry ze = null;
		List<Path> pathList = new LinkedList<Path>();
		while(	(ze = ziptInputStream.getNextEntry()) != null) {
			pathList.add(Paths.get(ze.getName()));
			ziptInputStream.closeEntry();
		}
		return pathList;
	}
	private void extractNewZipEntry(ZipInputStream ziptInputStream, Path OutputDir, Path fileRelativeName) throws Exception{
		Path absolutPath = OutputDir.resolve(fileRelativeName);
		if(Files.notExists(absolutPath.getParent())) Files.createDirectories(absolutPath.getParent());
		try( OutputStream output = Files.newOutputStream(absolutPath);){
			//ZipEntry zipEntry = new ZipEntry(fileRelativeName.toString());
			copyData(ziptInputStream, output);
			ziptInputStream.closeEntry();
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
		 byte[] buffer = new byte[8 * 1024];
	        int len;
	        while ((len = in.read(buffer)) > 0) {
	            out.write(buffer, 0, len);
	        }
	 }
	
}
