package com.sist.web.restcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.web.dao.*;
import com.sist.web.entity.*;
@RestController
@CrossOrigin(origins = "*") // 모든 port허용  => 3000 => 3001 .....
public class MainRestController {
  @Autowired
  private FoodHouseDAO fDao;
  
  @Autowired
  private RecipeDAO rDao;
  
  @GetMapping("/main")
  public ResponseEntity<Map> main_data(){
	  Map map=new HashMap();
	  
	  try
	  {
		  List<FoodHouseVO> fList=fDao.foodHitTop9();
		  List<RecipeEntity> rList=rDao.recipeMainData();
		  map.put("oneData", fList.get(0));
		  List<FoodHouseVO> twoData=new ArrayList<FoodHouseVO>();
		  for(int i=1;i<=4;i++)
		  {
			  twoData.add(fList.get(i));
		  }
		  List<FoodHouseVO> threeData=new ArrayList<FoodHouseVO>();
		  for(int i=5;i<=8;i++)
		  {
			  threeData.add(fList.get(i));
		  }
		  map.put("oneData", fList.get(0));
		  map.put("twoData", twoData);
		  map.put("threeData", threeData);
		  map.put("rList", rList);
		  System.out.println(map);
	  }catch(Exception ex)
	  {
		  return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	  return new ResponseEntity<>(map,HttpStatus.OK);
	  // HttpStatus.OK => 200 => 정상 수행 
  }
}