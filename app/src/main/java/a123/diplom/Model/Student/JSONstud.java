package a123.diplom.Model.Student;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by BlackOut on 08.05.2017.
 */

public class JSONstud {
    @SerializedName("students")
    @Expose
    private List<Students> students = null;

    public List<Students> getStudents() {
        return students;
    }
    public void setStudents(List<Students> students) {
        this.students = students;
    }
}
