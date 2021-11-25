package forrest;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.*;
import java.io.IOException;


public class WindowBronConfig implements ItemListener {
    private static final int windowHeight = 300;
    private static final int windowWidth = 620;

    //panels
    private JPanel panelType;
    private JPanel panelParams;
    private JPanel panelColors;
    private JPanel panelDraw;
    public static JPanel panelWeapon;
    //labels
    private JLabel lBase;
    private JLabel lAdditional;
    private JLabel lMainColor;
    private JLabel lDopColor;
    private JLabel lMaxSpeed;
    private JLabel lWeight;
    private JLabel lNumWeap;
    private JLabel lTypeWeap;
    private JLabel lDefWeap;
    private JLabel lHeavyWeap;
    private JLabel lAIMWeap;
    //radiobuttons
    private JRadioButton two;
    private JRadioButton four;
    private JRadioButton six;
    //checkbox
    private JCheckBox cBWeapon;
    private JCheckBox cBHead;
    //button
    private JButton btnAdd;
    private JButton btnCancel;
    //numeric updown
    private JSpinner spinMaxSpeed;
    private JSpinner spinWeight;
    //color panels
    private JPanel RedColor;
    private JPanel YellowColor;
    private JPanel BlackColor;
    private JPanel WhiteColor;
    private JPanel GrayColor;
    private JPanel OrangeColor;
    private JPanel GreenColor;
    private JPanel BlueColor;
    //listeners&drawing
    private WindowBronConfigPaint configPaint;
    //frame
    private JFrame fr;
    public static BroneCar car;
    private ITransportDelegate eventAddCar;

    public WindowBronConfig(){
        fr = new JFrame("Выбор транспорта");
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fr.setSize(windowWidth, windowHeight);
        fr.setResizable(false);
        fr.setLocationRelativeTo(null);
        fr.getContentPane().setLayout(null);
        fr.setResizable(false);
        car = null;
        //paint class
        configPaint = new WindowBronConfigPaint();
        //panels
        panelType = new JPanel(null);
        panelType.setBorder(BorderFactory.createTitledBorder("Тип транспорта"));
        panelParams = new JPanel(null);
        panelParams.setBorder(BorderFactory.createTitledBorder("Параметры"));
        panelDraw = new JPanel(null);
        panelDraw.setBackground(Color.WHITE);
        panelDraw.setTransferHandler(new TransferHandler() {
            public int getSourceActions(JComponent c) {
                return TransferHandler.COPY;
            }

            public boolean importData(TransferSupport info) {
                Transferable t = info.getTransferable();
                String data;

                try {
                    if (t.getTransferData(DataFlavor.stringFlavor) instanceof IWeapon) {
                        if (car != null && car.getClass() == BroneZenit.class) {
                            ((BroneZenit) car).setWeapon((IWeapon) t.getTransferData(DataFlavor.stringFlavor));
                            ((BroneZenit) car).setn_w(getCountWeapons());
                            panelDraw.repaint();
                        }
                        return true;
                    } else {
                        data = (String) t.getTransferData(DataFlavor.stringFlavor);
                    }
                } catch (Exception e) {
                    return false;
                }
                switch (data) {
                    case "Бронемашина":
                        car = new BroneCar((int) spinMaxSpeed.getValue(), (int) spinWeight.getValue(), Color.BLACK, false);
                        break;
                    case "Зенитка":
                        int countWeapon = getCountWeapons();
                        car = new BroneZenit(
                                (int) spinMaxSpeed.getValue(),
                                (int) spinWeight.getValue(),
                                Color.BLACK,
                                Color.CYAN,
                                cBWeapon.isSelected(),
                                cBHead.isSelected(),
                                1,
                                countWeapon);
                        break;
                    default:
                        return false;
                }
                panelDraw.repaint();
                return true;
            }

            public boolean canImport(TransferHandler.TransferSupport info) {
                try {
                    return info.isDataFlavorSupported(DataFlavor.stringFlavor) ||
                            (info.getTransferable().getTransferData(DataFlavor.stringFlavor) instanceof IWeapon);
                } catch (UnsupportedFlavorException | IOException e) {
                    e.printStackTrace();
                }
                return false;
            }
        });
        panelColors = new JPanel(null);
        panelColors.setBorder(BorderFactory.createTitledBorder("Цвета"));
        panelWeapon = new JPanel(null);
        panelWeapon.setBorder(BorderFactory.createTitledBorder("Тип оружия"));
        panelWeapon.setVisible(false);
        //rbuttons
        ButtonGroup n_group = new ButtonGroup();
        two = new JRadioButton("2");
        four = new JRadioButton("4");
        six = new JRadioButton("6");
        n_group.add(two);
        n_group.add(four);
        n_group.add(six);
        two.addItemListener(this);
        four.addItemListener(this);
        six.addItemListener(this);
        //checkbox
        cBWeapon = new JCheckBox("Пушки");
        cBWeapon.addItemListener(this);
        cBHead = new JCheckBox("Радар");
        //labels
        lBase = addBroneLabels("Бронемашина");
        lAdditional = addBroneLabels("Зенитка");
        lMaxSpeed = new JLabel("Макс скорость");
        lWeight = new JLabel("Вес");
        lMainColor = new JLabel("Основной цвет");
        lMainColor.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lMainColor.setTransferHandler(new TransferHandler() {
            public int getSourceActions(JComponent c) {
                return TransferHandler.COPY;
            }

            public boolean importData(TransferSupport info) {
                Transferable t = info.getTransferable();
                Color data;
                try {
                    data = (Color) t.getTransferData(DataFlavor.stringFlavor);
                } catch (Exception e) {
                    return false;
                }
                if (car != null) {
                    car.MainColor = data;
                    panelDraw.repaint();
                }
                return true;
            }

            public boolean canImport(TransferHandler.TransferSupport info) {
                try {
                    return info.getTransferable().getTransferData(DataFlavor.stringFlavor).getClass() == Color.class;
                } catch (UnsupportedFlavorException | IOException e) {
                    return false;
                }
            }
        });
        lDopColor = new JLabel("Доп цвет");
        lDopColor.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lDopColor.setTransferHandler(new TransferHandler() {
            public int getSourceActions(JComponent c) {
                return TransferHandler.COPY;
            }

            public boolean importData(TransferSupport info) {
                Transferable t = info.getTransferable();
                Color data;
                try {
                    data = (Color) t.getTransferData(DataFlavor.stringFlavor);
                } catch (Exception e) {
                    return false;
                }
                if (car != null) {
                    if (car.getClass() == BroneZenit.class) {
                        ((BroneZenit) car).setDopColor(data);
                        panelDraw.repaint();
                    }
                }
                return true;
            }

            public boolean canImport(TransferHandler.TransferSupport info) {
                try {
                    return info.getTransferable().getTransferData(DataFlavor.stringFlavor).getClass() == Color.class;
                } catch (UnsupportedFlavorException | IOException e) {
                    return false;
                }
            }
        });
        lNumWeap = new JLabel("Кол-во");
        lTypeWeap = new JLabel("Тип");
        lDefWeap = addWeaponLabels("Стандарт", 1);
        lHeavyWeap = addWeaponLabels("Тяжелый", 2);
        lAIMWeap = addWeaponLabels("Самонав", 3);
        //btns
        btnAdd = new JButton("Добавить");
        btnAdd.addActionListener(e -> {
            if (WindowBronConfig.car == null) {
                JOptionPane.showMessageDialog(fr, "Сначала создайте транспорт!", "Добавление транспорта", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (eventAddCar != null) {
                eventAddCar.BronDelegate(car);
            }
            fr.dispatchEvent(new WindowEvent(fr, WindowEvent.WINDOW_CLOSING));
        });
        btnCancel = new JButton("Отмена");
        btnCancel.addActionListener(e -> {
            fr.dispose();
        });
        //spinner model. same for both spinners
        SpinnerModel valueModel = new SpinnerNumberModel(150, 100,1000, 1);
        //spinners
        spinMaxSpeed = new JSpinner(valueModel);
        spinWeight = new JSpinner(valueModel);
        //color panels
        RedColor = addColorPanel(Color.RED);
        YellowColor = addColorPanel(Color.YELLOW);
        BlackColor = addColorPanel(Color.BLACK);
        WhiteColor = addColorPanel(Color.WHITE);
        GrayColor = addColorPanel(Color.GRAY);
        OrangeColor = addColorPanel(Color.ORANGE);
        GreenColor = addColorPanel(Color.GREEN);
        BlueColor = addColorPanel(Color.BLUE);
        //adding elems
        //types
        lBase.setBounds(7,20,120,20);
        lAdditional.setBounds(7,45,120,20);
        panelType.add(lBase);
        panelType.add(lAdditional);
        panelType.setBounds(10,10,135,80);
        fr.add(panelType);
        //params
        lMaxSpeed.setBounds(7,20,100,20);
        spinMaxSpeed.setBounds(70,45,50,20);
        lWeight.setBounds(7,65,100,20);
        spinWeight.setBounds(70,90,50,20);
        cBWeapon.setBounds(125, 15,70,20);
        cBHead.setBounds(125,40,70,20);
        panelParams.add(lMaxSpeed);
        panelParams.add(spinMaxSpeed);
        panelParams.add(lWeight);
        panelParams.add(spinWeight);
        panelParams.add(cBWeapon);
        panelParams.add(cBHead);
        panelParams.setBounds(10,150,200,120);
        fr.add(panelParams);
        //draw panel
        configPaint.setBounds(0,0,200,140);
        panelDraw.add(configPaint);
        panelDraw.setBounds(150, 10, 200,140);
        fr.add(panelDraw);
        //color panels
        lMainColor.setBounds(10,20,110,20);
        lDopColor.setBounds(130,20,110,20);
        RedColor.setBounds(10,50,50,50);
        YellowColor.setBounds(70,50,50,50);
        BlackColor.setBounds(130,50,50,50);
        WhiteColor.setBounds(190,50,50,50);
        GrayColor.setBounds(10, 110,50,50);
        OrangeColor.setBounds(70,110,50,50);
        GreenColor.setBounds(130,110,50,50);
        BlueColor.setBounds(190,110,50,50);
        panelColors.add(lMainColor);
        panelColors.add(lDopColor);
        panelColors.add(RedColor);
        panelColors.add(YellowColor);
        panelColors.add(BlackColor);
        panelColors.add(WhiteColor);
        panelColors.add(GrayColor);
        panelColors.add(OrangeColor);
        panelColors.add(GreenColor);
        panelColors.add(BlueColor);
        panelColors.setBounds(360,10,250,170);
        fr.add(panelColors);
        //buttons
        btnCancel.setBounds(520,245,90,20);
        btnAdd.setBounds(425,245,90,20);
        fr.add(btnAdd);
        fr.add(btnCancel);
        //weapon panel
        lNumWeap.setBounds(10,15,50,20);
        two.setBounds(10, 40,40,20);
        four.setBounds(10,65,40,20);
        six.setBounds(10,90,40,20);
        lTypeWeap.setBounds(65,15,50,20);
        lDefWeap.setBounds(65,40,65,20);
        lHeavyWeap.setBounds(65,65,65,20);
        lAIMWeap.setBounds(65,90,65,20);
        panelWeapon.add(lNumWeap);
        panelWeapon.add(two);
        panelWeapon.add(four);
        panelWeapon.add(six);
        panelWeapon.add(lTypeWeap);
        panelWeapon.add(lDefWeap);
        panelWeapon.add(lHeavyWeap);
        panelWeapon.add(lAIMWeap);
        panelWeapon.setBounds(220,150,140,120);
        fr.add(panelWeapon);
        //show form
        fr.setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource().getClass() == JCheckBox.class){
            JCheckBox tmp = (JCheckBox) e.getSource();
            if(tmp.getText().equals("Пушки")){
                panelWeapon.setVisible(tmp.isSelected());
            }
        }
        if(e.getSource().getClass() == JRadioButton.class){
            if(car != null && car.getClass() == BroneZenit.class){
                ((BroneZenit) car).setn_w(getCountWeapons());
                panelDraw.repaint();
            }
        }
    }

    public int getCountWeapons(){
        if(four.isSelected()) return 4;
        if(six.isSelected()) return 6;
        return 2;
    }

    public void addEvent(ITransportDelegate ev){
        eventAddCar = ev;
    }

    private JLabel addWeaponLabels(String name, int type) {
        IWeapon weapon;
        switch (type) {
            case 2:
                weapon = new Weapon2();
                break;
            case 3:
                weapon = new Weapon3();
                break;
            default:
                weapon = new Weapon1();
        }
        weapon.setn_w(2);
        JLabel DNDLabel = new JLabel(name);
        DNDLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        DNDLabel.setTransferHandler(new TransferHandler() {
            public int getSourceActions(JComponent c) {
                return TransferHandler.COPY;
            }

            public boolean canImport(TransferSupport support) {
                return false;
            }

            protected Transferable createTransferable(JComponent c) {
                return new Transferable() {
                    @Override
                    public DataFlavor[] getTransferDataFlavors() {
                        return new DataFlavor[0];
                    }

                    @Override
                    public boolean isDataFlavorSupported(DataFlavor flavor) {
                        return flavor == DataFlavor.stringFlavor;
                    }

                    @Override
                    public Object getTransferData(DataFlavor flavor) {
                        return weapon;
                    }
                };
            }
        });
        DNDLabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouseAction(e);
            }
        });
        return DNDLabel;
    }


    private JPanel addColorPanel(Color color) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setTransferHandler(new TransferHandler() {
            public int getSourceActions(JComponent c) {
                return TransferHandler.COPY;
            }

            public boolean canImport(TransferSupport support) {
                return false;
            }

            protected Transferable createTransferable(JComponent c) {
                return new Transferable() {
                    @Override
                    public DataFlavor[] getTransferDataFlavors() {
                        return new DataFlavor[0];
                    }

                    @Override
                    public boolean isDataFlavorSupported(DataFlavor flavor) {
                        return flavor == DataFlavor.stringFlavor;
                    }

                    @Override
                    public Object getTransferData(DataFlavor flavor) {
                        return panel.getBackground();
                    }
                };
            }
        });
        panel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouseAction(e);
            }
        });
        return panel;
    }

    private JLabel addBroneLabels(String name) {
        JLabel DNDLabel = new JLabel(name);
        DNDLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        DNDLabel.setTransferHandler(new TransferHandler() {
            public int getSourceActions(JComponent c) {
                return TransferHandler.COPY;
            }

            public boolean canImport(TransferSupport support) {
                return false;
            }

            protected Transferable createTransferable(JComponent c) {
                return new StringSelection(((JLabel) c).getText());
            }
        });
        DNDLabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouseAction(e);
            }
        });
        return DNDLabel;
    }

    public void mouseAction(MouseEvent e){
        if (SwingUtilities.isLeftMouseButton(e)) {
            JComponent c = (JComponent) e.getSource();
            TransferHandler handler = c.getTransferHandler();
            handler.exportAsDrag(c, e, TransferHandler.COPY);
        }
    }
}