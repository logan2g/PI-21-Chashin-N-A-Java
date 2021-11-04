package forrest;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Stack;

public class WindowParkingPaint extends JComponent implements ActionListener, ListSelectionListener {
    private static final int windowHeight = 600;
    private static final int windowWidth = 900;
    private Image holst = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
    private Graphics2D g2d = (Graphics2D) holst.getGraphics();
    private Random r;
    private ParkingCollection parkingCollection;
    private Stack<ITransport> takenCars;

    public WindowParkingPaint(){
        super();
        r = new Random();
        parkingCollection = new ParkingCollection(windowWidth, windowHeight);
        takenCars = new Stack<>();
        prepare_holst();
    }

    private void Draw()
    {
        prepare_holst();
        if(WindowParking.lBParkings.getSelectedIndex() > -1) {
            Parking parking = parkingCollection.getValue(WindowParking.lBParkings.getModel().getElementAt(WindowParking.lBParkings.getSelectedIndex()));
            parking.Draw(g2d);
        }
    }

    private void ReloadLevels(){
        parkingCollection.updateKeys();
        int ind = WindowParking.lBParkings.getSelectedIndex();
        DefaultListModel lModel = (DefaultListModel) WindowParking.lBParkings.getModel();
        lModel.clear();
        for(int i = 0 ; i < parkingCollection.Keys.size(); i++){
            lModel.addElement(parkingCollection.Keys.get(i));
        }
        if(WindowParking.lBParkings.getModel().getSize() > 0 && (ind == -1 || ind >= WindowParking.lBParkings.getModel().getSize())){
            WindowParking.lBParkings.setSelectedIndex(0);
        }
        else if (WindowParking.lBParkings.getModel().getSize() > 0 && ind > -1 && ind < WindowParking.lBParkings.getModel().getSize())
        {
            WindowParking.lBParkings.setSelectedIndex(ind);
        }
    }

    private void prepare_holst(){
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0,0, holst.getWidth(this), holst.getHeight(this));
        g2d.setColor(Color.BLACK);
    }

    public void valueChanged(ListSelectionEvent e){
        Draw();
    }

    public void actionPerformed(ActionEvent e){
        switch(e.getActionCommand()){
            case "addPark":
                String parkName = WindowParking.parkNameTf.getText();
                if(parkName.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Введите имя паровки!");
                    return;
                }
                parkingCollection.AddParking(parkName);
                ReloadLevels();
                break;
            case "delPark":
                if(WindowParking.lBParkings.getSelectedIndex() > -1){
                    int dialRes = JOptionPane.showConfirmDialog(null, "Вы хотите удалить парковку " + WindowParking.lBParkings.getModel().getElementAt(WindowParking.lBParkings.getSelectedIndex()) + " ?", "Предупреждение", JOptionPane.OK_CANCEL_OPTION);
                    if(dialRes == JOptionPane.OK_OPTION) {
                        parkingCollection.DelParking(WindowParking.lBParkings.getModel().getElementAt(WindowParking.lBParkings.getSelectedIndex()));
                        ReloadLevels();
                    }
                }
                break;
            case "setBron":
                if(WindowParking.lBParkings.getSelectedIndex() > -1) {
                    Color mainColor = JColorChooser.showDialog(null, "Choose a main color", Color.RED);
                    ;
                    if (mainColor != null) {
                        ITransport car = new BroneCar(100, 1000, mainColor, false);
                        Parking parking = parkingCollection.getValue(WindowParking.lBParkings.getModel().getElementAt(WindowParking.lBParkings.getSelectedIndex()));
                        if (parking.add(car)) {
                            Draw();
                        } else {
                            JOptionPane.showMessageDialog(null, "Парковка переполнена");
                        }
                    }
                }
                break;
            case "setZenit":
                if(WindowParking.lBParkings.getSelectedIndex() > -1) {
                    Color mainColor = JColorChooser.showDialog(null, "Choose a main color", Color.RED);
                    if (mainColor != null) {
                        Color dopColor = JColorChooser.showDialog(null, "Choose an additional color", Color.RED);
                        if (dopColor != null) {
                            ITransport car = new BroneZenit(100, 1000, mainColor, dopColor,
                                    true, true, 2, 4);
                            Parking parking = parkingCollection.getValue(WindowParking.lBParkings.getModel().getElementAt(WindowParking.lBParkings.getSelectedIndex()));
                            if (parking.add(car)) {
                                Draw();
                            } else {
                                JOptionPane.showMessageDialog(null, "Парковка переполнена");
                            }
                        }
                    }
                }
                break;
            case "take":
                if(!WindowParking.tFPos.getText().equals("") && WindowParking.lBParkings.getSelectedIndex() > -1){
                    String tmp =  WindowParking.tFPos.getText().trim();
                    if(tmp.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Введите номер места!");
                        return;
                    }
                    Parking parking = parkingCollection.getValue(WindowParking.lBParkings.getModel().getElementAt(WindowParking.lBParkings.getSelectedIndex()));
                    ITransport car = parking.takeBrone(Integer.parseInt(tmp));
                    if(car != null)
                    {
                        takenCars.add(car);
                    }
                    Draw();
                }
                break;
            case "takeStack":
                if(!takenCars.empty()){
                    WindowZenit wind = new WindowZenit(takenCars.pop());
                }
                else{
                    JOptionPane.showMessageDialog(null, "Стек пуст!");
                }
                break;
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(holst != null){
            g.drawImage(holst, 0,0,this);
        }
        super.repaint();
    }
}
