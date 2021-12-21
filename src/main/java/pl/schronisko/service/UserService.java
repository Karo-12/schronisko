package pl.schronisko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.schronisko.exception.UserHasAnimalException;
import pl.schronisko.exception.UserNotFoundException;
import pl.schronisko.model.Animal;
import pl.schronisko.model.User;
import pl.schronisko.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnimalService animalService;

    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }
    public void save(User user) {
        userRepository.save(user);
    }
    public User get(Integer id) throws UserNotFoundException {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) return result.get();
        throw new UserNotFoundException("Could not find any user with ID: "+id);
    }
    public void delete(Integer id) throws UserNotFoundException, UserHasAnimalException {
        Long count = userRepository.countById(id);
        if(count == null || count == 0) {
            throw new UserNotFoundException("Could not find any user with ID: "+id);
        }
        if(hasUserAnyAnimal(id)) {
            throw new UserHasAnimalException("User with ID: "+ id + "has animal.");
        }
        userRepository.deleteById(id);
    }
    public List<User> listEmployees() {
        return userRepository.findByRoleIs("ROLE_EMPLOYEE");
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean hasUserAnyAnimal(Integer idUser) {
        List<Animal> animals = animalService.listAll();
        for(Animal animal : animals) {
            if(Objects.equals(animal.getIdUser().getId(), idUser)) {
                return true;
            }
        }
        return false;
    }

}
