package pl.schronisko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.schronisko.model.Type;
import pl.schronisko.repository.TypeRepository;

import java.util.List;
import java.util.Objects;

@Service
public class TypeService {
    @Autowired
    private TypeRepository typeRepository;

    public List<Type> listAll() {
        return (List<Type>) typeRepository.findAll();
    }
    public void save(Type type) { typeRepository.save(type);
    }
    public boolean isTypeInBase(String type) {
        List<Type> types = listAll();
        for(Type typ : types) {
            if(Objects.equals(typ.getName(), type)) return true;
        }
        return false;
    }
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }
}
