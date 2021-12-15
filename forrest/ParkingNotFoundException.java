package forrest;

public class ParkingNotFoundException extends Exception{
    public ParkingNotFoundException(int i){
        super("Не найден автомобиль по месту " + i);
    }
}
