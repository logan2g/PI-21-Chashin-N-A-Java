package forrest;

public class ParkingOverflowException extends Exception{
    public ParkingOverflowException(){
        super("На парковке нет свободных мест");
    }
}
