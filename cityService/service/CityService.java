package city_example.cityService.service;
import city_example.cityService.exception.CityAlreadyExistsException;
import city_example.cityService.exception.CityNotFoundException;
import city_example.cityService.model.City;
import city_example.cityService.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public List<City> getCities(String name){
        if(name==null){
            return cityRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        }else{
           return  null;

       }
    }

    public City createCity(City newCity) {
        Optional<City> cityByName=cityRepository.findByName(newCity.getName());

        if(cityByName.isPresent()){
            throw new CityAlreadyExistsException("City already exists with name  : "+newCity.getName());
        }
        return cityRepository.save(newCity);
    }

    public void deleteCity(String id) {
        cityRepository.deleteById(id);
    }

    public City getCityById(String id) {
       return cityRepository.findById(id)
                .orElseThrow(()->new CityNotFoundException("City not found  : "+id));
    }

    public void updateCity(String id, City newCity) {
        City oldCity=getCityById(id);
        oldCity.setName(newCity.getName());
        cityRepository.save(oldCity);
    }
}
