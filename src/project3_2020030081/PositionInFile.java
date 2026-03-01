package project3_2020030081;

public class PositionInFile {
	
	String fileName;
	long offset;
	
	
	public PositionInFile(String fileName, long offset) {
		this.fileName = fileName;
		this.offset = offset;
	}
	
	
	public String getFileName() {
		return fileName;
	}

	public long getOffset() {
		return offset;
	}


	@Override
	public String toString() {
		return "PositionInFile [fileName=" + fileName + ", offset=" + offset + "]";
	}
	
	
	
	

}
