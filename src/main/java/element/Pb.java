package element;

import java.util.ArrayList;
import java.util.List;

public class Pb extends Element{
    private String date;
    private String sample;
    private double pb206204;
    private double pb206204err;
    private double pb206207;
    private double pb206207err;
    private double pb206208;
    private double pb206208err;
    private boolean isIsotopic = true;
    public static List<Pb> resultPbList = new ArrayList<>();

    public Pb(String sample, String date, double pb206204, double pb206204err, double pb206207,
              double pb206207err, double pb206208, double pb206208err, boolean isIsotopic) {
        this.date = date;
        this.sample = sample;
        this.pb206204 = pb206204;
        this.pb206204err = pb206204err;
        this.pb206207 = pb206207;
        this.pb206207err = pb206207err;
        this.pb206208 = pb206208;
        this.pb206208err = pb206208err;
        this.isIsotopic = isIsotopic;
        resultPbList.add(this);
    }

    public String getDate() {
        return date;
    }

    public String getSample() {
        return sample;
    }

    public double getPb206204() {
        return pb206204;
    }

    public double getPb206204err() {
        return pb206204err;
    }

    public double getPb206207() {
        return pb206207;
    }

    public double getPb206207err() {
        return pb206207err;
    }

    public double getPb206208() {
        return pb206208;
    }

    public double getPb206208err() {
        return pb206208err;
    }

    public boolean isIsotopic() {
        return isIsotopic;
    }
}
