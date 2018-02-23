package xlsx;

import element.Element;
import element.Pb;
import element.Rb;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PbXlsxCreator extends XlsxCreator{
    List<? extends Element> pbList;


    public PbXlsxCreator(List<? extends Element> pbList){
        this.pbList = pbList;
        row = resultSheet.createRow(rownum);
        fileName = ((Pb) Element.elements.get(0)).getDate() + ".xls";

        createCell(0, "Образец№");
        createCell(1, "206/204");
        createCell(2, "206/204 err");
        createCell(3, "206/207");
        createCell(4, "206/207 err");
        createCell(5, "206/208");
        createCell(6, "206/208 err");

    }
    @Override
    public void createXlsxFile() {
        if(pbList.isEmpty()) return;
        for(Element element : Element.elements){
            Pb pb = (Pb) element;
            if(pb.getSample().startsWith("srm") || pb.getSample().startsWith("SRM")) continue;
            rownum++;
            row = resultSheet.createRow(rownum);

            // sample (A)
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(pb.getSample());
            // 206/204 (B)
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(pb.getPb206204());
            // Err (C)
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(pb.getPb206204err());
            //206/207 (D)
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(pb.getPb206207());
            // Err (E)
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(pb.getPb206207err());
            //206/208 (F)
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue(pb.getPb206208());
            // Err (G)
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue(pb.getPb206208err());

    }
        String filePath = "C:\\Users\\Зинаида\\Google Диск\\ИГГД\\измерения\\2018\\Pb\\" + fileName;
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try(FileOutputStream outFile = new FileOutputStream(file)) {
            workbook.write(outFile);
            //System.out.println("Created file: " + file.getAbsolutePath());
        }
        catch (IOException e){
            e.printStackTrace();
        }


}}
