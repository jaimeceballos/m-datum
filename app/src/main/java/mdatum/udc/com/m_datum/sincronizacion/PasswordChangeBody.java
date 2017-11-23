package mdatum.udc.com.m_datum.sincronizacion;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jaime on 22/11/17.
 */

public class PasswordChangeBody {

    @SerializedName("new_password1")
    private String new_password1;

    @SerializedName("new_password2")
    private String new_password2;

    public PasswordChangeBody(String new_password1, String new_password2) {
        this.new_password1 = new_password1;
        this.new_password2 = new_password2;
    }


    public String getNew_password1() {
        return new_password1;
    }

    public void setNew_password1(String new_password1) {
        this.new_password1 = new_password1;
    }

    public String getNew_password2() {
        return new_password2;
    }

    public void setNew_password2(String new_password2) {
        this.new_password2 = new_password2;
    }
}
