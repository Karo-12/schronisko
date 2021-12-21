package pl.schronisko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.schronisko.model.Type;
import pl.schronisko.repository.TypeRepository;

import java.util.List;

@Service
public class TypeService {
    @Autowired
    private TypeRepository typeRepository;

    public List<Type> listAll() {
        return (List<Type>) typeRepository.findAll();
    }
    public void save(Type type) { typeRepository.save(type);
    }
}
