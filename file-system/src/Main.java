//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome to File System Demo");

        Directory root = new Directory("Movies");

        File border = new File("Border");
        root.add(border);

        Directory comedy = new Directory("Comedy");
        comedy.add(new File("Hulchul"));
        comedy.add(new File("Hera Pheri"));

        Directory action = new Directory("Action");
        action.add(new File("Gladiator"));

        root.add(comedy);
        root.add(action);

        root.ls(""); // Start with no indentation
    }
}