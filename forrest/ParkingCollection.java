package forrest;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ParkingCollection {
        private final Map<String, Parking<ITransport, IWeapon>> parkStages;
    public List<String> Keys;
    private int pictureWidth;
    private int pictureHeight;
    private final char separator = ':';

    public ParkingCollection(int picWidth, int picHeight){
        parkStages = new HashMap<>();
        Keys = new LinkedList<>(parkStages.keySet());
        pictureWidth = picWidth;
        pictureHeight = picHeight;
    }

    public void updateKeys(){
        Keys = new LinkedList<>(parkStages.keySet());
    }

    public void AddParking(String name)
    {
        if (!parkStages.containsKey(name)) {
            parkStages.put(name, new Parking<ITransport, IWeapon>(pictureWidth, pictureHeight));
        }
    }

    public void DelParking(String name)
    {
        if (parkStages.containsKey(name)) parkStages.remove(name);
    }

    public ITransport getParkingValue(String ind, int intInd){
        Parking p = getValue(ind);
        if(p != null) return p.getValue(intInd);
        return null;
    }

    public Parking<ITransport, IWeapon> getValue(String ind){
        if(parkStages.containsKey(ind)){
            return parkStages.get(ind);
        }
        return null;
    }

    public void saveLevel(String filename, String parkName) {
        if(!(parkStages.containsKey(parkName))) throw new IndexOutOfBoundsException();
        try {
            Files.deleteIfExists(Paths.get(filename));
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        try(FileWriter writer = new FileWriter(filename, false)) {
            writer.write("ParkingLevel\n");
            writer.write("Parking" + separator + parkName + "\n");
            ITransport car = null;
            for (int i = 0; (car = parkStages.get(parkName).getValue(i)) != null; i++) {
                if (car != null) {
                    //если место не пустое
                    //Записываем тип машины
                    if (car.getClass() == BroneCar.class) {
                        writer.write("BroneCar" + separator);
                    }
                    if (car.getClass() == BroneZenit.class) {
                        writer.write("BroneZenit" + separator);
                    }
                    //Записываемые параметры
                    writer.write(car.toString() + "\n");
                }
            }
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void SaveData(String filename){
        try {
            Files.deleteIfExists(Paths.get(filename));
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        try(FileWriter writer = new FileWriter(filename, false)) {
            writer.write("ParkingCollection\n");
            for (Map.Entry<String, Parking<ITransport, IWeapon>> level : parkStages.entrySet()) {
                writer.write("Parking" + separator + level.getKey() + "\n");
                ITransport car = null;
                for (int i = 0; (car = level.getValue().getValue(i)) != null; i++) {
                    if (car != null) {
                        //если место не пустое
                        //Записываем тип машины
                        if (car.getClass() == BroneCar.class) {
                            writer.write("BroneCar" + separator);
                        }
                        if (car.getClass() == BroneZenit.class) {
                            writer.write("BroneZenit" + separator);
                        }
                        //Записываемые параметры
                        writer.write(car.toString() + "\n");
                    }
                }
            }
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void loadLevel(String filename) throws Exception {
        File in = new File(filename);
        if(!in.exists()){
            throw new FileNotFoundException();
        }
        try(BufferedReader reader = new BufferedReader(new FileReader(in))) {
            String strs = reader.readLine();
            if(!strs.contains("ParkingLevel")){
                throw new InvalidPropertiesFormatException("Неверный формат файла");
            }
            strs = reader.readLine();
            String key = "";
            ITransport car = null;
            if (strs.contains("Parking"))
            {
                //начинаем новую парковку
                key = strs.split(String.valueOf(separator))[1];
                if (parkStages.containsKey(key)) parkStages.get(key).clear();
                else parkStages.put(key, new Parking<ITransport,IWeapon>(pictureWidth, pictureHeight));
                while((strs = reader.readLine()) != null) {
                    if (strs.contains("BroneCar")) {
                        car = new BroneCar(strs.split(String.valueOf(separator))[1]);
                    } else if (strs.contains("BroneZenit")) {
                        car = new BroneZenit(strs.split(String.valueOf(separator))[1]);
                    }
                    try {
                        parkStages.get(key).add(car);
                    } catch (ParkingOverflowException ex) {
                        //TODO log
                    }
                }
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void LoadData(String filename) throws Exception {
        File in = new File(filename);
        if(!in.exists()){
            throw new FileNotFoundException();
        }
        try(BufferedReader reader = new BufferedReader(new FileReader(in))) {
            String strs = reader.readLine();
            if(strs.contains("ParkingCollection")){
                parkStages.clear();
            }
            else
            {
                throw new InvalidPropertiesFormatException("Неверный формат файла");
            }
            abstrBron car = null;
            String key = "";
            while((strs = reader.readLine()) != null){
                if (strs.contains("Parking"))
                {
                    //начинаем новую парковку
                    key = strs.split(String.valueOf(separator))[1];
                    parkStages.put(key, new Parking<ITransport,IWeapon>(pictureWidth, pictureHeight));
                }
                else if (strs.contains(String.valueOf(separator)))
                {
                    if (strs.contains("BroneCar"))
                    {
                        car = new BroneCar(strs.split(String.valueOf(separator))[1]);
                    }
                    else if (strs.contains("BroneZenit"))
                    {
                        car = new BroneZenit(strs.split(String.valueOf(separator))[1]);
                    }
                    try {
                        parkStages.get(key).add(car);
                    } catch (ParkingOverflowException ex) {
                        //TODO log
                    }
                }
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
