package parser;

import element.Element;
import element.Pb;

import java.util.List;

public class PbParser implements Parser {
    String dateString2;
    String fileNameString4;
    String resultString206204;
    String resultString206207;
    String resultString206208;
    boolean isIsotopic = true;

    public PbParser(List<String> stringList){
        this.dateString2 = stringList.get(2);
        this.fileNameString4 = stringList.get(4);
        String sampleIdent = stringList.get(3);
        if(sampleIdent.contains("con") || sampleIdent.contains("CON") || sampleIdent.contains("c")) isIsotopic = false;
        String[] resultStrings = findResultString(stringList);
        this.resultString206204 = resultStrings[0];
        this.resultString206207 = resultStrings[1];
        this.resultString206208 = resultStrings[2];
    }

    @Override
    public Element parse() {
        Pb pb = null;

        String[] ratio206204 = resultString206204.split("\\s+");
        double pb206204 = new Double(ratio206204[1]);
        double pb206204err = new Double(ratio206204[5]);

        String[] ratio206207 = resultString206207.split("\\s+");
        double pb206207 = new Double(ratio206207[1]);
        double pb206207err = new Double(ratio206207[5]);

        String[] ratio206208 = resultString206208.split("\\s+");
        double pb206208 = new Double(ratio206208[1]);
        double pb206208err = new Double(ratio206208[5]);

        String[] fileName = fileNameString4.split("\\s+");
        String[] sampleList = fileName[2].split("\\\\");
        String date = sampleList[2];
        String sample = sampleList[3];

        pb = new Pb(sample, date, pb206204, pb206204err, pb206207,
                    pb206207err, pb206208, pb206208err, isIsotopic);

        return pb;
    }

    private String[] findResultString(List<String> stringList){
        int summaryString = 0;
        for (int i = stringList.size() - 1; i > 0 ; i--) {
            if(stringList.get(i).equals("RUN SUMMARY SCANWISE WITH  2 -SIGMA TEST")){
                summaryString = i;
                break;
            }
        }
        String[] resultStrings = new String[3];
        resultStrings[0] = stringList.get(summaryString + 10);
        resultStrings[1] = stringList.get(summaryString + 14);
        resultStrings[2] = stringList.get(summaryString + 16);

        return resultStrings;
    }
}
