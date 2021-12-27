package pl.schronisko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.schronisko.exception.AnimalNotFoundException;
import pl.schronisko.exception.UserNotFoundException;
import pl.schronisko.model.Animal;
import pl.schronisko.repository.AnimalRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;


    public List<Animal> listAll() {
        return (List<Animal>) animalRepository.findAll();
    }
    public List<Animal> listAvailableAnimals() {
        return animalRepository.findByStatusIs("available");
    }
    public Animal getAnimalById (Integer id) throws AnimalNotFoundException {
        Optional<Animal> result = animalRepository.findById(id);
        if (result.isPresent()) return result.get();
        else throw new AnimalNotFoundException("Could not find Animal with id "+ id);
    }
    public void save(Animal animal) {
        animalRepository.save(animal);
    }
    public void delete(Integer id) throws UserNotFoundException {
        Long count = animalRepository.countById(id);
        if(count == null || count == 0) {
            throw new UserNotFoundException("Could not find any user with ID: "+id);
        }
        animalRepository.deleteById(id);
    }
    public boolean isAnimalReserved(Integer idAnimal) {
        List<Animal> animals = listAll();
        Animal result = null;
        for(Animal animal : animals) {
            if(Objects.equals(animal.getId(), idAnimal)) {
                result = animal;
            }
        }
        assert result != null;
        return result.getStatus().equals("reserved");
    }
    public boolean isAnimalAdopted(Integer idAnimal) {
        List<Animal> animals = listAll();
        Animal result = null;
        for(Animal animal : animals) {
            if(Objects.equals(animal.getId(), idAnimal)) {
                result = animal;
            }
        }
        assert result != null;
        return result.getStatus().equals("adopted");
    }
}
