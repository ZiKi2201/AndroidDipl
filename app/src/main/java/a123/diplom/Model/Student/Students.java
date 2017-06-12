package a123.diplom.Model.Student;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Students  {
    @SerializedName("id")
    @Expose
    private String id_stud;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("id_group")
    @Expose
    private String id_group;
    @SerializedName("theme")
    @Expose
    private String theme;
    @SerializedName("coins")
    @Expose
    private int coins;
    @SerializedName("select")
    @Expose
    private boolean select;

    public String getId_stud() {
        return id_stud;
    }

    public void setId_stud(String id_stud) {
        this.id_stud = id_stud;
    }

    public String getId_group() {
        return id_group;
    }

    public void setId_group(String id_group) {
        this.id_group = id_group;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public boolean getSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }



}

