package pl.schronisko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.schronisko.exception.RaceNotFoundException;
import pl.schronisko.model.Race;
import pl.schronisko.repository.RaceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RaceService {
    @Autowired
    private RaceRepository raceRepository;

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

}
