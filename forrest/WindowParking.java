package forrest;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;

public class WindowParking {
    private static final int windowHeight = 600;
    private static final int windowWidth = 1100;
    //buttons
    private JButton btnSetBroneCar;
    private JButton btnSetZenit;
    private JButton btnTake;
    private JButton btnAddPark;
    private JButton btnDelPark;
    private JButton btnTakeFromStack;
    //labels
    private JLabel lPos;
    private JLabel lTakeVeh;
    private JLabel lParkName;
    //textfield
    public static JTextField parkNameTf;
    //listbox
    public static JList<String> lBParkings;
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
        btnTake = new JButton("     Отложить в Стек      ");
        btnAddPark = new JButton("       Добавить парковку       ");
        btnDelPark = new JButton("       Удалить парковку         ");
        btnTakeFromStack = new JButton("    Взять из Стека    ");
        //labels
        lPos = new JLabel("     Место           ");
        lTakeVeh = new JLabel("         Забрать транспорт         ");
        lParkName = new JLabel("      Парковка          ");
        //textfield
        parkNameTf = new JTextField(10);
        //lbox
        lBParkings = new JList<>(new DefaultListModel<String>());
        lBParkings.setLayoutOrientation(JList.VERTICAL);
        lBParkings.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //textBox
        tFPos = new JFormattedTextField();
        tFPos.setColumns(2);
        //maskformatter
        MaskFormatter numFormatter = null;
        try {
            numFormatter = new MaskFormatter("##");
            numFormatter.install(tFPos);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        //add
        elems.add(lParkName);
        elems.add(parkNameTf);
        elems.add(btnAddPark);
        elems.add(lBParkings);
        elems.add(btnDelPark);
        elems.add(btnSetBroneCar);
        elems.add(btnSetZenit);
        elems.add(lTakeVeh);
        elems.add(lPos);
        elems.add(tFPos);
        elems.add(btnTake);
        elems.add(btnTakeFromStack);
        //listeners
        lBParkings.addListSelectionListener(pp);
        btnAddPark.setActionCommand("addPark");
        btnAddPark.addActionListener(pp);
        btnDelPark.setActionCommand("delPark");
        btnDelPark.addActionListener(pp);
        btnSetBroneCar.setActionCommand("setBron");
        btnSetBroneCar.addActionListener(pp);
        btnSetZenit.setActionCommand("setZenit");
        btnSetZenit.addActionListener(pp);
        btnTake.setActionCommand("take");
        btnTake.addActionListener(pp);
        btnTakeFromStack.setActionCommand("takeStack");
        btnTakeFromStack.addActionListener(pp);
        //end init
        fr.add(elems);
        fr.setVisible(true);
    }
}
