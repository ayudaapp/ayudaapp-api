package com.ayuda.dao.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ayuda.rest.domain.Chore;


/**
 * Repository can be used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */

//public interface ChoresRepository extends JpaRepository<Chore, Long> {
@Repository
public interface ChoresRepository extends PagingAndSortingRepository<Chore, Long> {
    Chore[] findHotelById(String Id);
    Page findAll(Pageable pageable);
}
