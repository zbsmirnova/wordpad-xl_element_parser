package listeners;

import process.Process;
import process.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FrameListener implements ActionListener {
    private View view;
    private File file;

    public FrameListener(View view){this.view = view;}

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        int ret = chooser.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            file = chooser.getCurrentDirectory(); //getSelectedFile();
            Process.getProcess().setFolder(file);

            view.dispose();
        }
    }

    public File getFile() {
        return file;
    }
}
