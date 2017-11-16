package mdatum.udc.com.m_datum.sincronizacion;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jaime on 16/11/17.
 */

public class LoginBody {

    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;


    public LoginBody(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
