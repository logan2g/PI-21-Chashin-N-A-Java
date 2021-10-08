package forrest;

import javax.swing.*;
import java.awt.*;

public class Window {
    private static final int windowHeight = 600;
    private static final int windowWidth = 800;
    //buttons
    private JButton btnCreate;
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
        btnCreate = new JButton("Создать");
        btnDown = new JButton("\\|/");
        btnUp = new JButton("/|\\");
        btnLeft = new JButton("<=");
        btnRight = new JButton("=>");
        //adding
        btns.add(btnCreate);
        btns.add(btnLeft);
        btns.add(btnUp);
        btns.add(btnDown);
        btns.add(btnRight);
        //conn with listeners
        btnCreate.setActionCommand("create");
        btnCreate.addActionListener(pc);
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
