package com.sist.web.restcontroller;
import com.sist.web.entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.*;
import java.util.*;
@RestController
@CrossOrigin(origins = "*") // port가 다른 경우 접속허용 
/*
 *   val
 *   var 
 *   
 *   data class Aaa
 */
public class MemberRestController {
   @Autowired
   private MemberDAO mDao;
   // /member/login/${id}/${pwd} => params:{}
   @GetMapping("/member/login/{id}/{pwd}")
   public ResponseEntity<Map> memberLogin(@PathVariable("id") String id,
		   @PathVariable("pwd") String pwd)
   {
	     Map map=new HashMap();
	     try
	     {
	    	 int count=mDao.idCount(id);
	    	 if(count==0) // id가 없는 상태 
	    	 {
	    		 map.put("msg", "NOID");
	    	 }
	    	 else // id가 존재하는 상태 
	    	 {
	    		 ReactMemberEntity vo=mDao.findById(id);
	    		 // id에 해당되는 전체 데이터 읽기
	    		 if(vo.getPwd().equals(pwd)) // 로그인 
	    		 {
	    			 map.put("msg", "OK");
	    			 map.put("name", vo.getName());
	    			 map.put("id", vo.getId());
	    			 map.put("sex", vo.getSex());
	    		 }
	    		 else  // 비밀번호가 틀린 상태 
	    		 {
	    			 map.put("msg", "NOPWD");
	    		 }
	    	 }
	     }catch(Exception ex)
	     {
	    	 return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	    	 // error => isError , error   onError
	    	 // 404 , 403 , 415 , 500 , 400 
	     }
	     return new ResponseEntity<>(map,HttpStatus.OK);// onSuccess
   }
}