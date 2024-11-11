package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.ReactMemberEntity;

@Repository
public interface MemberDAO extends JpaRepository<ReactMemberEntity, Integer>{
    @Query(value="SELECT COUNT(*) FROM reactMember "
    		    +"WHERE id=:id",nativeQuery = true)
    public int idCount(@Param("id") String id);
    
    public ReactMemberEntity findById(String id);
}