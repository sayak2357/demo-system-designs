package service;

import entity.Book;
import entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LMS {
    private Map<Integer, User> users;
    private Map<Integer, List<Book>> borrowedBooks;
    private Map<String,Book> books;

    public LMS() {
        this.users = new HashMap<>();
        this.borrowedBooks = new HashMap<>();
        this.books = new HashMap<>();
    }

    public List<Book> searchBook(String query){
        List<Book> result = new ArrayList<>();
        for(Book book:this.books.values()){
            if(book.getAvailableCount()>0 && (book.getAuthor().equalsIgnoreCase(query) || book.getTitle().equalsIgnoreCase(query))){
                result.add(book);
            }
        }
        return result;
    }

    public boolean registerUser(User user){
        this.users.put(user.getUserId(),user);
        return true;
    }
    public String borrowBook(int userId,String ISBN){
        User user = this.users.get(userId);
        Book book = this.books.get(ISBN);
        if(user==null)
            return "user not found!";
        if(book == null)
            return "book not found";
        if(book.getAvailableCount()==0)
            return "book no longer available";
        book.borrowBook();
        if(this.borrowedBooks.containsKey(userId)){
            this.borrowedBooks.get(userId).add(book);
        }
        else{
            List<Book> newList = new ArrayList<>();
            newList.add(book);
            this.borrowedBooks.put(userId,newList);
        }
        return "book borrowed";
    }
    public String returBook(int userId,String ISBN){
        User user = this.users.get(userId);
        if(user==null)
            return "user not found!";
        if(!this.borrowedBooks.containsKey(userId))
            return "this user have not taken any book";
        List<Book> borrowedUserBooks = this.borrowedBooks.get(userId);
        if(borrowedUserBooks.isEmpty())
            return "this user have not taken any book";
        for(Book book:borrowedUserBooks){
            if(book.getISBN().equalsIgnoreCase(ISBN)){
                book.returnBook();
                borrowedUserBooks.remove(book);
                return "book returned successfully";
            }
        }
        return "no book found";
    }
    public boolean addBook(Book book){
        this.books.put(book.getISBN(),book);
        return true;
    }
}
