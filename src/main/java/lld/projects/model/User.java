package lld.projects.model;

public class User {
    int userId;
    String userName;
    String contact;

    public User(int userId, String userName, String contact) {
        this.userId = userId;
        this.userName = userName;
        this.contact = contact;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
