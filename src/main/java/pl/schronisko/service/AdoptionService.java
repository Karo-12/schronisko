package pl.schronisko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.schronisko.model.Adoption;
import pl.schronisko.model.AdoptionId;
import pl.schronisko.repository.AdoptionRepository;
import pl.schronisko.model.Reservation;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdoptionService {
    @Autowired
    AdoptionRepository adoptionRepository;
    public List<Adoption> listAll() {
        return (List<Adoption>) adoptionRepository.findAll();
    }
    public Integer nextAdoptionId() {
        List<Adoption> adoptions = listAll();
        Integer lastId = 0;
        if(adoptions.size() == 0) lastId= 1;
        else {
            lastId = adoptions.get(adoptions.size() - 1).getId().getIdAdoption();
            lastId += 1;
        }
        return lastId;
    }
    public void saveAdoption(Reservation reservation) {
        Adoption adoption = new Adoption();
        AdoptionId id = new AdoptionId();
        id.setIdUser(reservation.getId().getIdUser());
        id.setIdAnimal(reservation.getId().getIdAnimal());
        id.setIdAdoption(nextAdoptionId());
        adoption.setId(id);
        adoption.setAddress(reservation.getAddress());
        adoption.setBirthday(reservation.getBirthday());
        adoption.setDate(LocalDate.now());
        adoption.setDescription(reservation.getDescription());
        adoption.setName(reservation.getName());
        adoption.setSurname(reservation.getSurname());
        adoptionRepository.save(adoption);
    }
    public Page<Adoption> findPaginatedAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return adoptionRepository.findAll(pageable);
    }
}
