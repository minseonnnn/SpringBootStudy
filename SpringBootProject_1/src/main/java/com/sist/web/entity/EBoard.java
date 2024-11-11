package com.sist.web.entity;

import org.springframework.data.elasticsearch.annotations.Document;

import jakarta.persistence.Id;
import lombok.Data;
// DB => @Entity , ElasticSearch => @Document 
@Document(indexName = "eboard")
@Data
public class EBoard {
  @Id // primary key => 검색 (no를 주면 인식을 못함 id를 줘서 만들어야 함)
  private int id;
  private int hit;
  private String name,subject,content,pwd,regdate;
}
