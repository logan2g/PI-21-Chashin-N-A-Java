package forrest;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;
import org.apache.log4j.*;
import org.apache.log4j.xml.*;

public class WindowParkingPaint extends JComponent implements ActionListener, ListSelectionListener {
    private static final int windowHeight = 600;
    private static final int windowWidth = 900;
    private Image holst = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
    private Graphics2D g2d = (Graphics2D) holst.getGraphics();
    private Random r;
    private ParkingCollection parkingCollection;
    private Stack<ITransport> takenCars;
    private JFileChooser fileChooser = new JFileChooser();
    private static final Logger logger = LogManager.getLogger("WindowParkingPaint");

    public WindowParkingPaint(){
        super();
        DOMConfigurator.configure("log4j.xml");
        r = new Random();
        parkingCollection = new ParkingCollection(windowWidth, windowHeight);
        takenCars = new Stack<>();
        prepare_holst();
        logger.fatal("TEST MESSAGE!!!");
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
        //logger.info("Перешли на парковку " + WindowParking.lBParkings.getModel().getElementAt(WindowParking.lBParkings.getSelectedIndex()));
    }

    private void addCar(ITransport car) {
        if (car != null && WindowParking.lBParkings.getSelectedIndex() > -1) {
            try {
                if (parkingCollection.getValue(WindowParking.lBParkings.getModel().getElementAt(WindowParking.lBParkings.getSelectedIndex())).add(car)) {
                    Draw();
                    logger.info("Добавлен автомобиль " + car);
                } else {
                    JOptionPane.showMessageDialog(null, "Транспорт не удалось поставить");
                }
            }
            catch (ParkingOverflowException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Переполнение", JOptionPane.ERROR_MESSAGE);
                logger.warn(ex.getMessage());
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
                logger.fatal(ex.getMessage());
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "addPark":
                String parkName = WindowParking.parkNameTf.getText();
                if (parkName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Введите имя паровки!");
                    return;
                }
                logger.info("Добавили парковку " + parkName);
                parkingCollection.AddParking(parkName);
                ReloadLevels();
                break;
            case "delPark":
                if (WindowParking.lBParkings.getSelectedIndex() > -1) {
                    int dialRes = JOptionPane.showConfirmDialog(null, "Вы хотите удалить парковку " + WindowParking.lBParkings.getModel().getElementAt(WindowParking.lBParkings.getSelectedIndex()) + " ?", "Предупреждение", JOptionPane.OK_CANCEL_OPTION);
                    if (dialRes == JOptionPane.OK_OPTION) {
                        logger.info("Удалили парковку " + WindowParking.lBParkings.getModel().getElementAt(WindowParking.lBParkings.getSelectedIndex()));
                        parkingCollection.DelParking(WindowParking.lBParkings.getModel().getElementAt(WindowParking.lBParkings.getSelectedIndex()));
                        ReloadLevels();
                    }
                }
                break;
            case "add":
                try {
                    WindowBronConfig formConf = new WindowBronConfig();
                    formConf.addEvent(this::addCar);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    logger.fatal(ex.getMessage());
                }
                break;
            case "take":
                if (!WindowParking.tFPos.getText().equals("") && WindowParking.lBParkings.getSelectedIndex() > -1) {
                    String tmp = WindowParking.tFPos.getText().trim();
                    if (tmp.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Введите номер места!");
                        return;
                    }
                    Parking parking = parkingCollection.getValue(WindowParking.lBParkings.getModel().getElementAt(WindowParking.lBParkings.getSelectedIndex()));
                    ITransport car = null;
                    try {
                        car = parking.takeBrone(Integer.parseInt(tmp));
                        if (car != null) {
                            takenCars.add(car);
                            logger.info("Изъят автомобиль " +  car + " с места " + tmp);
                        }
                        Draw();
                    }
                    catch (ParkingNotFoundException ex){
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Не найдено", JOptionPane.ERROR_MESSAGE);
                        logger.error(ex.getMessage());
                    }
                    catch (Exception ex){
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
                        logger.fatal(ex.getMessage());
                    }
                }
                break;
            case "takeStack":
                if (!takenCars.empty()) {
                    WindowZenit wind = new WindowZenit(takenCars.pop());
                } else {
                    JOptionPane.showMessageDialog(null, "Стек пуст!");
                }
                break;
            case "saveColl":
                fileChooser.setDialogTitle("Открытие выходного файла");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    JOptionPane.showMessageDialog(this, "Файл \"" + fileChooser.getSelectedFile() + "\" выбран как выходной");
                    try {
                        parkingCollection.SaveData(fileChooser.getSelectedFile().getAbsolutePath());
                        logger.info("Сохранено в файл " + fileChooser.getSelectedFile().getAbsolutePath());
                        JOptionPane.showMessageDialog(this, "Сохранили!");
                    }
                    catch (Exception ex){
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Неизвестная ошибка при сохранении", JOptionPane.ERROR_MESSAGE);
                        logger.fatal(ex.getMessage());
                    }
                }
                break;
            case "loadFileColl":
                fileChooser.setDialogTitle("Открытие входного файла");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                result = fileChooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    JOptionPane.showMessageDialog(this, "Файл \"" + fileChooser.getSelectedFile() + "\" выбран как входной");
                    try {
                        parkingCollection.LoadData(fileChooser.getSelectedFile().getAbsolutePath());
                        JOptionPane.showMessageDialog(this, "Загрузили!");
                        logger.info("Загружено из файла " + fileChooser.getSelectedFile().getAbsolutePath());
                        ReloadLevels();
                        Draw();
                    }
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Неизвестная ошибка при агрузке", JOptionPane.ERROR_MESSAGE);
                        logger.fatal(ex.getMessage());
                    }
                }
                break;
            case "saveCurLevel":
                fileChooser.setDialogTitle("Открытие выходного файла");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                result = fileChooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    JOptionPane.showMessageDialog(this, "Файл \"" + fileChooser.getSelectedFile() + "\" выбран как выходной");
                    try {
                        parkingCollection.saveLevel(fileChooser.getSelectedFile().getAbsolutePath(), WindowParking.lBParkings.getSelectedValue());
                        logger.info("Сохранено в файл " + fileChooser.getSelectedFile().getAbsolutePath());
                        JOptionPane.showMessageDialog(this, "Сохранили!");
                    }
                    catch (Exception ex){
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Неизвестная ошибка при сохранении", JOptionPane.ERROR_MESSAGE);
                        logger.fatal(ex.getMessage());
                    }
                }
                break;
            case "loadLevel":
                fileChooser.setDialogTitle("Открытие входного файла");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                result = fileChooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    JOptionPane.showMessageDialog(this, "Файл \"" + fileChooser.getSelectedFile() + "\" выбран как входной");
                    try {
                        parkingCollection.loadLevel(fileChooser.getSelectedFile().getAbsolutePath());
                        JOptionPane.showMessageDialog(this, "Загрузили!");
                        logger.info("Загружено из файла " + fileChooser.getSelectedFile().getAbsolutePath());
                        ReloadLevels();
                        Draw();
                    }
                    catch (Exception ex){
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Неизвестная ошибка при агрузке", JOptionPane.ERROR_MESSAGE);
                        logger.fatal(ex.getMessage());
                    }
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
