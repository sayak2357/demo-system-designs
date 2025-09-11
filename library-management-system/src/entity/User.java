package entity;

public class User {
    public static int id=1;
    private int userId;
    private String name;
    public User(String name){
        this.name = name;
        this.userId = id;
        id++;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                '}';
    }
}
