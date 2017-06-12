package a123.diplom.Model.Count;


public class CountRequest {
    public Integer points;
    public String user;
    public String student;

    public CountRequest(Integer points, String user, String student) {
        this.points = points;
        this.user = user;
        this.student = student;
    }
}

