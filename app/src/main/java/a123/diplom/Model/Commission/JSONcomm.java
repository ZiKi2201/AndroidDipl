package a123.diplom.Model.Commission;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by BlackOut on 24.05.2017.
 */

public class JSONcomm {
    @SerializedName("result")
    @Expose
    private List<Comm> comm;

    public List<Comm> getComm() {
        return comm;
    }

    public void setComm(List<Comm> comm) {
        this.comm = comm;
    }

    public boolean isValid(){
        if (comm!=null && comm.size()!=0)
            return true;


        return false;
    }
}
