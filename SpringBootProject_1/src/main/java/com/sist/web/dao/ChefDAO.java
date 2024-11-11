package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.ChefEntitry;
@Repository
public interface ChefDAO extends JpaRepository<ChefEntitry, String>{
   public ChefEntitry findByChef(String chef);
}
