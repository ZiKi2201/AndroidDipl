package a123.diplom.Model.Group;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by BlackOut on 07.05.2017.
 */

public class JSON {
    @SerializedName("groups")
    @Expose
    private List<Groups> groups = null;

    public List<Groups> getGroups() {
        return groups;
    }
    public void setGroups(List<Groups> groups) {
        this.groups = groups;
    }
}
