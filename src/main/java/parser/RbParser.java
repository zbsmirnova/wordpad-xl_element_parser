package parser;

import element.Element;
import element.Rb;

import java.util.*;

public class RbParser implements Parser {
    String dateString2;
    String fileNameString4;
    String resultString80;
    private static List<Rb> resultRbList = new ArrayList<>();

    public RbParser(List<String> stringList){
        this.dateString2 = stringList.get(2);
        this.fileNameString4 = stringList.get(4);
        this.resultString80 = stringList.get(stringList.size() - 9);
    }
    @Override
    public Element parse() {
        Rb rb = null;
        String[] ratios = resultString80.split("\\s+");
        double rb8587 = new Double(ratios[1]);
        double rb8587err = new Double(ratios[5]);

        String[] fileName = fileNameString4.split("\\s+");
        String[] sampleList = fileName[2].split("\\\\");
        String date = sampleList[2];
        String sample = sampleList[3];

        rb = new Rb(sample, date, rb8587, rb8587err);

        System.out.println(sample);
        return rb;
    }

}
