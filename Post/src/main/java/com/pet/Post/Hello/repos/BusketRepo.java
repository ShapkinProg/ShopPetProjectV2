package com.pet.Post.Hello.repos;

import com.pet.Post.Hello.domain.Busket;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface BusketRepo extends CrudRepository<Busket, Integer> {
    ArrayList<Busket> findAllByUserid(Long id);
    void deleteByStaffidAndUserid(Long idS, Long idU);
    Busket findByStaffidAndUserid(Long idS, Long idU);
    void deleteById(Long id);
    ArrayList<Busket> findAllByStaffidAndUserid(Long idS, Long idU);

}
