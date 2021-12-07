public class User {
    private String username;
    private String password;

    public User(){}

    public User (String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Username: " + username + "\nPassword: " + password;
    }

    @Override
    public boolean equals(Object obj) {
        return this.username.equals(((User)obj).getUsername());
    }

    public String getUsername() {
        return username;
    }
}
