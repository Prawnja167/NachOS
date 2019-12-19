package nachos.proj1;

public class FileManager implements Runnable {
	String fileName;
	String fileType;
	int fileSize;
	int fileSender;

	public FileManager(String fileName, String fileType, int fileSize, int fileSender) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileSize = fileSize;
		this.fileSender = fileSender;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public int getFileSender() {
		return fileSender;
	}

	public void setFileSender(int fileSender) {
		this.fileSender = fileSender;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println("From: "+fileSender);
			System.out.println("Name: "+fileName);
			System.out.println("Type: "+fileType);
			System.out.println("Size: "+fileSize);
			
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
