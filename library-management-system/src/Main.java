import entity.Book;
import entity.User;
import service.LMS;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");
        LMS lms = new LMS();
        Book sherlock = new Book("sh123","Sherlock Holmes","Arthur Conan Doyle",10);
        User u1 = new User("sam");
        lms.registerUser(u1);
        lms.addBook(sherlock);
        System.out.println(lms.borrowBook(u1.getUserId(),sherlock.getISBN()));
    }
}