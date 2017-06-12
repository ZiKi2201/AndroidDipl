package a123.diplom.Model.Count;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by BlackOut on 24.05.2017.
 */

public class CountMember {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("points")
    @Expose
    public String points;
    @SerializedName("user_id")
    @Expose
    public String user_id;
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

    public String getStudent_id()
    {
        return student_id;
    }

    public void setStudent_id(String student_id)
    {
        this.student_id = student_id;
    }
}
