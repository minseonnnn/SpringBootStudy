package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name="project_seoul_location")
@Data
public class SeoulEntity {
	@Id
	private int no;
	private String title,poster,msg,address;
}
