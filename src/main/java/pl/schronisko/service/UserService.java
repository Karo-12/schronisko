package pl.schronisko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.schronisko.exception.EmailAlreadyInDatabaseException;
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
    public void register(User user) throws EmailAlreadyInDatabaseException {
        if(isEmailInDatabase(user.getEmail())) {
            throw new EmailAlreadyInDatabaseException("Na podany adres e-mail zostal juz zarejestrowany uzytkownik");
        } else
        userRepository.save(user);
    }
    public void saveEdit(User user) throws EmailAlreadyInDatabaseException{
        Optional<User> user2 = userRepository.findById(user.getId());
         if(!Objects.equals(user2.get().getEmail(), user.getEmail()) && isEmailInDatabase(user.getEmail())) {
            throw new EmailAlreadyInDatabaseException("Na podany adres e-mail zostal juz zarejestrowany uzytkownik");
        } else userRepository.save(user);
    }
    public void save(User user) throws EmailAlreadyInDatabaseException {
        if(isEmailInDatabase(user.getEmail())){
            throw new EmailAlreadyInDatabaseException("Na podany adres e-mail zostal juz zarejestrowany uzytkownik");
        } else userRepository.save(user);
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
        else if(hasUserAnyAnimal(id)) {
            throw new UserHasAnimalException("Użytkownik już posiada zwierze");
        } else userRepository.deleteById(id);
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
    public boolean isEmailInDatabase(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

}
