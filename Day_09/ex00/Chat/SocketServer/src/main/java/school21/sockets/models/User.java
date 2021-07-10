package school21.sockets.models;

import java.util.Objects;

public class User {
    private Long Identifier;
    private String Name;
    private String Password;
    
    public User() {
        Identifier = 0L;
        Name = "gmail";
        Password = "";
        Name = "testName";
    }

    
    public User(Long identifier, String Name) {
        Identifier = identifier;
        Name = Name;
    }
    
    
    public User(String Name, String password) {
        this.Name = Name;
        this.Password = password;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Long getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(Long identifier) {
        Identifier = identifier;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        Name = Name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Identifier.equals(user.Identifier) && Name.equals(user.Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Identifier, Name);
    }

    @Override
    public String toString() {
        return "User{" +
                "Identifier=" + Identifier +
                ", Name='" + Name + '\'' +
                '}';
    }
}
