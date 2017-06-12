package a123.diplom.Model.Commission;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Comm {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("points")
    @Expose
    public String points;
    @SerializedName("user_id")
    @Expose
    public String user_id;
    @SerializedName("user_email")
    @Expose
    public String user_email;
    @SerializedName("student_id")
    @Expose
    public String student_id;


    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getPoints()
    {
        return points;
    }

    public void setPoints(String points)
    {
        this.points = points;
    }

    public String getUser_id()
    {
        return user_id;
    }

    public void setUser_id(String user_id)
    {
        this.user_id = user_id;
    }

    public String getUser_email()
    {
        return user_email;
    }

    public void setUser_email(String user_email)
    {
        this.user_email = user_email;
    }

    public String getStudent_id()
    {
        return student_id;
    }

    public void setStudent_id(String student_id)
    {
        this.student_id = student_id;
    }


}
