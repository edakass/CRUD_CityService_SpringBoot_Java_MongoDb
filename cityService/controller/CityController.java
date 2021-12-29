package city_example.cityService.controller;

import city_example.cityService.exception.CityAlreadyExistsException;
import city_example.cityService.exception.CityNotFoundException;
import city_example.cityService.model.City;
import city_example.cityService.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/cities")
@AllArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping
    public ResponseEntity<List<City>> getCities(@RequestParam(required = false) String name){
        return new ResponseEntity<>(cityService.getCities(name),OK);

    }

    @GetMapping("/get/{id}")
    public  ResponseEntity<City> getCity(@PathVariable String id){
        return new ResponseEntity<>(getCityById(id),OK);
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<City> createCity(@RequestBody City newCity){
        return new ResponseEntity<>(cityService.createCity(newCity), CREATED);

    }

    @PutMapping("/update/{id}")
    public  ResponseEntity<Void> getCity(@PathVariable String id,@RequestBody City newCity){
        cityService.updateCity(id,newCity);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<Void> deleteCity(@PathVariable String id){
        cityService.deleteCity(id);
        return new ResponseEntity<>(OK);
    }

    private City getCityById(String id){

        return  cityService.getCityById(id);
    }

    @ExceptionHandler(CityNotFoundException.class)
    public  ResponseEntity<String> handleCityNotFoundException(CityNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(CityAlreadyExistsException.class)
    public  ResponseEntity<String> handleCityAlreadyExistsException(CityAlreadyExistsException ex){
        return new ResponseEntity<>(ex.getMessage(), CONFLICT);
    }
}
