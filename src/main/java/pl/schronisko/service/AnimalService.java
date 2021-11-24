package pl.schronisko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.schronisko.model.Animal;
import pl.schronisko.repository.AnimalRepository;

import java.util.List;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;

    public List<Animal> listAll() {
        return (List<Animal>) animalRepository.findAll();
    }
}
