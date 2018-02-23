package element;

import java.util.ArrayList;
import java.util.List;

public class Rb extends Element{
    private double rb8587;
    private double rberr;
    private String sample;
    private String date;
    public static List<Rb> resultRbList = new ArrayList<>();


    public Rb(String sample, String date, double rb8587, double rberr) {
        this.sample = sample;
        this.date = date;
        this.rb8587 = rb8587;
        this.rberr = rberr;
        resultRbList.add(this);
    }

    @Override
    public String toString() {
        return "85/87 = " + rb8587 + ", err = " + rberr;
    }

    public Double getRb8587() {
        return rb8587;
    }

    public Double getRberr() {
        return rberr;
    }

    public String getSample() {
        return sample;
    }

    public String getDate() {
        return date;
    }
}
