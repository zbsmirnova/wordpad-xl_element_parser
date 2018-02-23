package xlsx;

import element.*;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import process.Process;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class XlsxCreator {
    //public static Map<ElementType, List<? extends Element>> resultMap;
    File folder;
    String fileName;
    HSSFWorkbook workbook;
    HSSFSheet resultSheet;
    int rownum;
    Cell cell;
    Row row;
    HSSFCellStyle style;

    public XlsxCreator() {
        workbook = new HSSFWorkbook();
        resultSheet = workbook.createSheet("result");
        rownum = 0;
        style = createStyleForTitle(workbook);
        row = resultSheet.createRow(rownum);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Образец №");
        cell.setCellStyle(style);
    }

    public void setFolder(File file) {
        this.folder = file;
    }

    protected static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    public abstract void createXlsxFile();

    public void createCell(int cellNum, String cellText){
        cell = row.createCell(cellNum, CellType.STRING);
        cell.setCellValue(cellText);
        cell.setCellStyle(style);
    }

    public static List<XlsxCreator> makeXlsxCreatorList(){
        List<XlsxCreator> xlsxCreators = new ArrayList<>();

        if(!Rb.resultRbList.isEmpty()) {
            RbXlsxCreator rbXlsxCreator = new RbXlsxCreator(Rb.resultRbList);//resultMap.get(ElementType.Rb));
            xlsxCreators.add(rbXlsxCreator);
        }
        if (!Pb.resultPbList.isEmpty()){
                PbXlsxCreator pbXlsxCreator = new PbXlsxCreator(Pb.resultPbList);
                xlsxCreators.add(pbXlsxCreator);
            }
        if(!UO2.resultUo2List.isEmpty()){
            UO2XlsxCreator uo2XlsxCreator = new UO2XlsxCreator(UO2.resultUo2List);
            xlsxCreators.add(uo2XlsxCreator);
        }

        return xlsxCreators;
    }


}
