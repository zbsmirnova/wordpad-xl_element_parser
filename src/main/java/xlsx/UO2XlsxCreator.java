package xlsx;

import element.Element;
import element.Rb;
import element.UO2;

import java.util.List;

public class UO2XlsxCreator extends XlsxCreator{
    List<? extends Element> uo2List;

    public UO2XlsxCreator(List<? extends Element> uo2List){
        this.uo2List = uo2List;
        row = resultSheet.createRow(rownum);
        fileName = ((Rb)Element.elements.get(0)).getDate() + ".xls";

        createCell(0, "Образец №");
        createCell(1, "85/87");
        createCell(2, "85/87 err");

    }
    @Override
    public void createXlsxFile() {
        if(uo2List.isEmpty()) return;

    }
}
