package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.ReactBoardEntity;
import java.util.*;
@Repository
public interface BoardDAO extends JpaRepository<ReactBoardEntity, Integer>{
    @Query(value="SELECT * FROM reactBoard "
    		    +"ORDER BY no DESC "
    		    +"LIMIT :start,10",nativeQuery = true)
    public List<ReactBoardEntity> boardListData(@Param("start") int start);
    
    public ReactBoardEntity findByNo(int no);
    
    // count() , save() , delete() 
}