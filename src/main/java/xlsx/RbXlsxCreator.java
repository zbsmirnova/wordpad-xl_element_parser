package xlsx;

import element.Element;
import element.Rb;
import org.apache.poi.ss.usermodel.CellType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class RbXlsxCreator extends XlsxCreator{
    List<? extends Element> rbList;

    public RbXlsxCreator(List<? extends Element> rbList){
        this.rbList = rbList;
        row = resultSheet.createRow(rownum);
        fileName = ((Rb)rbList.get(0)).getDate() + ".xls";

        createCell(0, "Образец №");
        createCell(1, "85/87");
        createCell(2, "85/87 err");

    }

    @Override
    public void createXlsxFile() {
        if (rbList.isEmpty()) return;
        for (Element element : rbList) {
            Rb rb = (Rb) element;
            if (rb.getSample().startsWith("nat")) continue;
            rownum++;
            row = resultSheet.createRow(rownum);

            // sample (A)
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(rb.getSample());
            // 85/87 (B)
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(rb.getRb8587());
            // Err (C)
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(rb.getRberr());
        }
    }

    public void writeXlsxFile(File destDir){
        //"C:\\Users\\Зинаида\\Google Диск\\ИГГД\\измерения\\2018\\Rb\\"
        String filePath = destDir.toString() + fileName;
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try(FileOutputStream outFile = new FileOutputStream(file)) {
            workbook.write(outFile);
            //System.out.println("Created file: " + file.getAbsolutePath());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
