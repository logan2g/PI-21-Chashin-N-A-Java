package forrest;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ParkingCollection {
    private final Map<String, Parking<ITransport, IWeapon>> parkStages;
    public List<String> Keys;
    private int pictureWidth;
    private int pictureHeight;

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
}
