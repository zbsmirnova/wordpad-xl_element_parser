package process;

import xlsx.XlsxCreator;

import java.util.List;

public class Controller {
    private View view;

    public Controller(View view) { this.view = view; }

    public static void main(String[] args) {
        //View view = new View();
        //view.chooseIncomeFolder();
        //Controller controller = new Controller(view);
        DirChooser.init();
        createNewXlsx();

    }

    public static void createNewXlsx(){
        Process.processIncomeDir();
        List<XlsxCreator> XlsxCreatorsList = XlsxCreator.makeXlsxCreatorList();
        for (XlsxCreator creator : XlsxCreatorsList) {
            creator.createXlsxFile();
        }
    }
}
