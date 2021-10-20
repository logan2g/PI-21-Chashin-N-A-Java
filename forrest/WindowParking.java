package forrest;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class WindowParking {
    private static final int windowHeight = 600;
    private static final int windowWidth = 1100;
    //buttons
    private JButton btnSetBroneCar;
    private JButton btnSetZenit;
    private JButton btnTake;
    //labels
    private JLabel lPos;
    private JLabel lTakeVeh;
    //masked textBox
    public static JFormattedTextField tFPos;
    //panel
    private JPanel elems;

    public WindowParking(){
        JFrame fr = new JFrame("Parking");
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(windowWidth, windowHeight);
        fr.setResizable(false);
        fr.setLocationRelativeTo(null);
        fr.getContentPane().setLayout(null);
        WindowParkingPaint pp = new WindowParkingPaint();
        pp.setBounds(0,0,windowWidth - 220, windowHeight);
        fr.add(pp);
        elems = new JPanel(new FlowLayout(FlowLayout.CENTER));
        elems.setBounds(890,0,200,windowHeight);
        //start init
        //btns
        btnSetBroneCar = new JButton("Припарковать бронемашину");
        btnSetZenit = new JButton("Припарковать зенитку");
        btnTake = new JButton("Забрать");
        //labels
        lPos = new JLabel("     Место           ");
        lTakeVeh = new JLabel("         Забрать транспорт         ");
        //textBox
        tFPos = new JFormattedTextField();
        tFPos.setColumns(2);
        MaskFormatter numFormatter = null;
        try {
            numFormatter = new MaskFormatter("##");
            //numFormatter.setPlaceholderCharacter('_');
            numFormatter.install(tFPos);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        //add
        elems.add(btnSetBroneCar);
        elems.add(btnSetZenit);
        elems.add(lTakeVeh);
        elems.add(lPos);
        elems.add(tFPos);
        elems.add(btnTake);
        //listeners
        btnSetBroneCar.setActionCommand("setBron");
        btnSetBroneCar.addActionListener(pp);
        btnSetZenit.setActionCommand("setZenit");
        btnSetZenit.addActionListener(pp);
        btnTake.setActionCommand("take");
        btnTake.addActionListener(pp);
        //end init
        fr.add(elems);
        fr.setVisible(true);
    }
}
