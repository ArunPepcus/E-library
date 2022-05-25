package com.pepcus.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pepcus.models.Shelves;
@Repository
public interface ShelvesRepository extends JpaRepository<Shelves, Integer> {

}
