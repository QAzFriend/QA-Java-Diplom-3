package api;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String email;
    private String password;
    private String name;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
