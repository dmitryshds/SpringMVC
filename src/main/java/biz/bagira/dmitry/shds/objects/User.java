package biz.bagira.dmitry.shds.objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Дмитрий on 31.07.2016.
 */
public class User {
    @NotNull @Size(min = 3, message = "min 3 characters")
    private String name;
    @NotNull @Size(min = 5, max = 8, message = "min 5 max 8 characters")
    private String password;
    private boolean admin;

    public User(String usernamevalue) {
        this.name = usernamevalue;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
