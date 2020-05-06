import java.io.*;
import java.nio.file.*;
import java.util.zip.*;

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
		try (ZipOutputStream zipOutput = new ZipOutputStream( Files.newOutputStream(zipFile));){
			ZipEntry zipEntry = new ZipEntry(source.getFileName().toString());
			zipOutput.putNextEntry(zipEntry);
			Files.copy(source, zipOutput);
			zipOutput.closeEntry();
		}
		
	}
	
}
