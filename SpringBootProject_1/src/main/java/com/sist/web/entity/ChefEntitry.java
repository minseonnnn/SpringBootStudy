package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/*
 *   CHEF text 
POSTER text 
MEM_CONT1 int 
MEM_CONT3 text 
MEM_CONT7 text 
MEM_CONT2 int
 */
@Entity(name="chef")
@Data
public class ChefEntitry {
   @Id
   private String chef;
   private String poster;
   private int mem_cont1,mem_cont2;
   private String mem_cont3,mem_cont7;
   
}
