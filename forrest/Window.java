package forrest;

import javax.swing.*;
import java.awt.*;

public class Window {
    private static final int windowHeight = 600;
    private static final int windowWidth = 800;
    //buttons
    private JButton btnCreateZenit;
    private JButton btnCreateBase;
    private JButton btnUp;
    private JButton btnDown;
    private JButton btnLeft;
    private JButton btnRight;
    private JPanel btns;

    public Window(){
        JFrame fr = new JFrame("Zenit");
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(windowWidth, windowHeight);
        fr.setResizable(false);
        fr.setLocationRelativeTo(null);
        //paint
        paintClass pc = new paintClass();
        fr.add(pc);
        //buttons
        //init
        btns = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnCreateZenit = new JButton("Создать зенитку");
        btnCreateBase = new JButton("Создать базу");
        btnDown = new JButton("\\|/");
        btnUp = new JButton("/|\\");
        btnLeft = new JButton("<=");
        btnRight = new JButton("=>");
        //adding
        btns.add(btnCreateBase);
        btns.add(btnCreateZenit);
        btns.add(btnLeft);
        btns.add(btnUp);
        btns.add(btnDown);
        btns.add(btnRight);
        //conn with listeners
        btnCreateBase.setActionCommand("createBase");
        btnCreateBase.addActionListener(pc);
        btnCreateZenit.setActionCommand("createZenit");
        btnCreateZenit.addActionListener(pc);
        btnUp.setActionCommand("up");
        btnUp.addActionListener(pc);
        btnDown.setActionCommand("down");
        btnDown.addActionListener(pc);
        btnLeft.setActionCommand("left");
        btnLeft.addActionListener(pc);
        btnRight.setActionCommand("right");
        btnRight.addActionListener(pc);
        //end_of_buttons
        fr.add(btns, BorderLayout.SOUTH);
        fr.setVisible(true);
    }
}
