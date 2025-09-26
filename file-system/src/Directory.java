import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Directory implements FileSystem{
    private String directoryName;
    List<FileSystem> fileSystemList;

    public Directory(String directoryName) {
        this.directoryName = directoryName;
        this.fileSystemList = new CopyOnWriteArrayList<>();  // thread safe in case multiple threads simultaneously update
    }

    public void add(FileSystem fs){
        this.fileSystemList.add(fs);
    }

    public void remove(FileSystem fs){
        this.fileSystemList.remove(fs);
    }

    @Override
    public void ls(String indent) {
        System.out.println(indent + "Directory: " + directoryName);
        for (FileSystem fs : fileSystemList) {
            fs.ls(indent + "   "); // add indentation
        }
    }
}
