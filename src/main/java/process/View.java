package process;

import listeners.FrameListener;
import xlsx.XlsxCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class View extends JFrame{ //implements ActionListener{
    private Controller controller;


    public void setController(Controller controller){ this.controller = controller;}

    public View(){
        super();
        this.chooseIncomeFolder();
    }

    public void chooseIncomeFolder() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createVerticalGlue());

        final JLabel label = new JLabel();
        label.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(label);

        panel.add(Box.createRigidArea(new Dimension(10, 10)));

        JButton sourceButton = new JButton("Выбрать папку с данными");
        sourceButton.setAlignmentX(CENTER_ALIGNMENT);

        FrameListener frameListener = new FrameListener(this);
        sourceButton.addActionListener(frameListener);
        File file = frameListener.getFile();

        panel.add(sourceButton);
//        panel.add(destinationButton);
        panel.add(Box.createVerticalGlue());
        getContentPane().add(panel);

        setPreferredSize(new Dimension(260, 220));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

}
