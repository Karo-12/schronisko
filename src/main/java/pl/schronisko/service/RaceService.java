package pl.schronisko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.schronisko.exception.RaceNotFoundException;
import pl.schronisko.model.Race;
import pl.schronisko.model.Type;
import pl.schronisko.repository.RaceRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RaceService {
    @Autowired
    private RaceRepository raceRepository;
    @Autowired
    private TypeService typeService;

    public List<Race> listAll() {
        return (List<Race>) raceRepository.findAll();
    }
    public Race findRaceById(Integer id) throws RaceNotFoundException{
        Optional<Race> result = raceRepository.findById(id);
        if (result.isPresent()) return result.get();
        else throw new RaceNotFoundException("Could not find Animal with id "+ id);
    }
    public void save(Race race) { raceRepository.save(race);
    }
    public boolean isRaceInBase(String race) {
        List <Race> races = listAll();
        for(Race r : races) {
            if(Objects.equals(r.getName(), race)) return true;
        }
        return false;
    }
    public Race getRaceByName(String name) {
        return raceRepository.findByName(name);
    }
    public Race getRace(String type, String race) {
        Race r = getRaceByName(race);
        Type t = typeService.getTypeByName(type);
        if(t == null) {
            return createNewRaceAndType(type,race);
        } else if(r == null) {
            return createNewRace(t,race);
        } else return r;
    }
    public Race createNewRaceAndType(String type, String race) {
        Type t = new Type();
        t.setName(type);
        typeService.save(t);
        Race r = new Race();
        r.setName(race);
        r.setIdType(t);
        save(r);
        return r;
    }
    public Race createNewRace(Type type, String race) {
        Race r = new Race();
        r.setName(race);
        r.setIdType(type);
        save(r);
        return r;
    }

}
