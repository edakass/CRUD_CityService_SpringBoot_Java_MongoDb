package city_example.cityService.exception;

public class CityAlreadyExistsException extends RuntimeException  {
    public  CityAlreadyExistsException(String msg){
        super(msg);
    }
}
