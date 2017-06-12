package a123.diplom.Model.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by BlackOut on 14.05.2017.
 */

public class SessionReg {
    @SerializedName("connect")
    @Expose
    public boolean connect;

    public boolean getConnect()
    {
        return connect;
    }

    public void setConnect(boolean connect)
    {
        this.connect = connect;
    }
}
