public class File implements FileSystem{
    private String fileName;

    public File(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public void ls(){
        System.out.println("file name: "+fileName);
    }
}
