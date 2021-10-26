package city_example.cityService.exception;

public class CityNotFoundException extends RuntimeException{
    public  CityNotFoundException(String msg){
        super(msg);
    }
}
