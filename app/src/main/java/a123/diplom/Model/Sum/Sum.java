package a123.diplom.Model.Sum;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by BlackOut on 02.06.2017.
 */

public class Sum {
    @SerializedName("sum")
    @Expose
    private int sum;

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
