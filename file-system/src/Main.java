//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome to File System Demo");

        Directory movieDirectory = new Directory("Movies");

        FileSystem border = new File("Border");

        movieDirectory.add(border);

        Directory comedyMovieDirectory = new Directory("ComedyMovies");
        File hulchul = new File("Hulchul");
        comedyMovieDirectory.add(hulchul);

        movieDirectory.add(comedyMovieDirectory);

        movieDirectory.ls();
    }
}