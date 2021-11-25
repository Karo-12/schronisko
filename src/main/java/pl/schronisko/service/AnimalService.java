package pl.schronisko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.schronisko.exception.AnimalNotFoundException;
import pl.schronisko.model.Animal;
import pl.schronisko.model.AnimalId;
import pl.schronisko.repository.AnimalRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;


    public List<Animal> listAll() {
        return (List<Animal>) animalRepository.findAll();
    }

    public Animal getAnimalById (AnimalId id) throws AnimalNotFoundException {
        Optional<Animal> result = animalRepository.findById(id);
        if (result.isPresent()) return result.get();
        else throw new AnimalNotFoundException("Could not find Animal with id "+ id.getIdAnimal());
    }

}
