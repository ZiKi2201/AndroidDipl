package a123.diplom.Model.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by BlackOut on 12.05.2017.
 */

public class Registration {
    @SerializedName("connect")
    @Expose
    public boolean connect;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("session_key")
    @Expose
    public String session_key;
    @SerializedName("login")
    @Expose
    public String login;
    @SerializedName("pass")
    @Expose
    public String pass;

    public String getPass()
    {
        return pass;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public boolean getConnect()
    {
        return connect;
    }

    public void setConnect(boolean connect)
    {
        this.connect = connect;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public String getSession_key()
    {
        return session_key;
    }

    public void setSession_key(String session_key)
    {
        this.session_key = session_key;
    }


}
