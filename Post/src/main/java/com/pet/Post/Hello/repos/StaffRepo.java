package com.pet.Post.Hello.repos;

import com.pet.Post.Hello.domain.Staff;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StaffRepo extends CrudRepository<Staff, Integer> {
    List<Staff> findByTag(String tag);
    Staff findById(Long id);
    void deleteById(Long id);
}
