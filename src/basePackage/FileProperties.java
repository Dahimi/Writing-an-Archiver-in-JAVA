package basePackage;

public class FileProperties {
	private String name ; 
	private long size ;
	private  long compressedSize;
	private int compressionMethod;
	
	
	public String getName() {
		return name;
	}



	public long getSize() {
		return size;
	}



	public FileProperties(String name, long size, long compressedSize, int compressionMethod) {
		//super();
		this.name = name;
		this.size = size;
		this.compressedSize = compressedSize;
		this.compressionMethod = compressionMethod;
	}



	public long getCompressedSize() {
		return compressedSize;
	}
	public long getCompressionRatio() {
		return 100 - ((compressedSize * 100) / size);
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if(size > 0)
		return name + (size/1024) + " KB (" + (compressedSize/1024) +" KB) compression: " + getCompressionRatio() +"%";
		else return name;
	}



	public int getCompressionMethod() {
		return compressionMethod;
	}
	


	
}
