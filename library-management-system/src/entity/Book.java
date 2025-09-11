package entity;

public class Book {
    private String ISBN;
    private String title;
    private String author;
    private int totalCount;
    private int availableCount;

    public Book(String ISBN, String name, String author, int totalCount) {
        this.ISBN = ISBN;
        this.title = name;
        this.author = author;
        this.totalCount = totalCount;
        this.availableCount = totalCount;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getAvailableCount() {
        return availableCount;
    }
    public boolean borrowBook(){
        if(this.availableCount>0){
            this.availableCount--;
            return true;
        }
        return false;
    }
    public boolean returnBook(){
        if(this.availableCount<this.totalCount){
            this.availableCount++;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN='" + ISBN + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
