package process;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class DirChooser extends JPanel implements ActionListener {
    JButton button1;

    JFileChooser chooser;
    String choosertitle;

    public DirChooser() {
        button1 = new JButton("Do it");
        button1.addActionListener(this);
        add(button1);

    }

    public void actionPerformed(ActionEvent e) {
        int result;

        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(choosertitle);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        //
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                   //Process.setFolder(chooser.getCurrentDirectory());
                    File file = chooser.getSelectedFile();
                    Process.setFolder(file);
                    //Process.processIncomeDir();
                    System.out.println(file.toString());

                   remove(button1);
                   JButton button2 = new JButton("destination");
                   button2.addActionListener(new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent e) {
                           int result;

                           chooser = new JFileChooser();
                           chooser.setCurrentDirectory(new java.io.File("."));
                           chooser.setDialogTitle(choosertitle);
                           chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                           //

                           if (chooser.showOpenDialog(DirChooser.this) == JFileChooser.APPROVE_OPTION) {
                               File destination = chooser.getSelectedFile();
                               System.out.println(destination.toString());
                               SwingUtilities.windowForComponent(DirChooser.this).dispose(); //закрыть окно после выбора папки
                           }
                       }
                   });
                   add(button2);
                   revalidate();
                   repaint();

        }
        else {
            System.out.println("No Selection ");
        }
    }

    public Dimension getPreferredSize(){
        return new Dimension(200, 200);
    }

    public static void init() {
        JFrame frame = new JFrame("");
        DirChooser panel = new DirChooser();
        frame.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );
        frame.getContentPane().add(panel,"Center");
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}