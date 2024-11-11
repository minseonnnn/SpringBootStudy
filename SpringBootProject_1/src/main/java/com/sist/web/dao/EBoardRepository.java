package com.sist.web.dao;
import java.util.*;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.*;
@Repository
public interface EBoardRepository extends ElasticsearchRepository<EBoard, Integer>{
	// 상세보기
    //	public EBoard findById(int id);
	// 전체 데이터 검색 findAll()
	// 수정 , 추가 save()
	// 삭제 => delete()
	
}
