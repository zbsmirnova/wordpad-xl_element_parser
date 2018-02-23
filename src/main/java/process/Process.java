package process;

import element.*;
import parser.Parser;
import parser.PbParser;
import parser.RbParser;
import parser.UO2Parser;
import xlsx.*;
import xlsx.XlsxCreator;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Process {
    private static Process process = new Process();
    //private static Map<ElementType, List<? extends Element>> resultMap = makeElementList();
    public List<? extends XlsxCreator> xlsxCreatorsList;

    private static File folder;

    boolean isMatt = true;
    boolean isFinnigan = false;
    static boolean isRb = false;
    static boolean isPb = false;
    static boolean isUO2 = false;

    private Process(){ }

    public static Process getProcess(){
        return process;
    }

    public static void setFolder(File folder) {
        Process.folder = folder;
    }

    public static void processIncomeDir() {
        //Map<ElementType, List<? extends Element>> resultMap = new HashMap<>();

        //File file = new File("D:\\28Jan18\\7838C1 .TXT");
        //File folder = new File("D:\\23Apr17");
        while (folder == null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        File[] folderEntry = folder.listFiles();
        for(File file : folderEntry) {
            if(!file.getName().endsWith(".TXT")) continue;
            List<String> stringList = new ArrayList<>();
            try {
                stringList = Files.readAllLines(file.toPath(), Charset.forName("windows-1251")); //Charset.defaultCharset()); //reading and writing into the list all the strings of the file
            } catch (IOException e) {
                e.printStackTrace();
            }

            boolean isMeasuremenrValid = validation(stringList); //checking if file is valid and contains no mistakes
            if(isMeasuremenrValid) {
                ElementType elementType = findOutElementType(stringList); //проверяем какой элемент в файле
                Parser parser = makeParser(stringList, elementType); // создать парсер соответствующий элементу
                Element element = parser.parse();//в зависимости от элемента пропарсить файл, извлечь нужные данные, вернуть элемент
            }
        }



//        resultMap.put(ElementType.Rb, Rb.resultRbList);
//        resultMap.put(ElementType.Pb, Pb.resultPbList);
//        resultMap.put(ElementType.UO2, UO2.resultUo2List);

        //return resultMap;
    }

//    XlsxCreator xlsxCreator = makeXlsxCreator();
//        xlsxCreator.createXlsxFile();

    static boolean validation(List stringList) {

        if(stringList.size() < 88) return false;

        String analysisNum7 = (String) stringList.get(7); // строка Analysis
        String methodNum8 = (String) stringList.get(8);   // строка Method
        // определяем какой элемент (Rb или Pb), в завосимости от этого выставляем флаги
        if (analysisNum7.contains("Rb") && methodNum8.contains("Rb")) isRb = true;
        else if (analysisNum7.contains("Pb") && methodNum8.contains("Pb")) isPb = true;

        return true;
    }

    static Parser makeParser(List stringList, ElementType elementType){
        // создаем необходимый парсер
        Parser parser = null;
        switch (elementType) {
            case Rb: parser = new RbParser(stringList);
            case Pb: parser = new PbParser(stringList);
            case UO2: parser = new UO2Parser(stringList);
        }
        return parser;
    }


    private static ElementType findOutElementType(List<String> stringList){
        ElementType elementType = null;
        String methodString = stringList.get(8);
        if(methodString.contains("METHOD FILE")) {
            if (methodString.contains("Rb")) elementType = ElementType.Rb;
            else if (methodString.contains("Pb")) elementType = ElementType.Pb;
            else if (methodString.contains("UO2")) elementType =  ElementType.UO2;
        }
       // else throw new Exception.WrongMesurementException();

        return elementType;
    }
}
