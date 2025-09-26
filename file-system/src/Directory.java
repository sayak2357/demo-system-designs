import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystem{
    private String directoryName;
    List<FileSystem> fileSystemList;

    public Directory(String directoryName) {
        this.directoryName = directoryName;
        this.fileSystemList = new ArrayList<>();
    }

    public void add(FileSystem fs){
        this.fileSystemList.add(fs);
    }

    public void ls(){
        System.out.println("directort name: "+directoryName);
        for(FileSystem fs:fileSystemList){
            fs.ls();
        }
    }
}
