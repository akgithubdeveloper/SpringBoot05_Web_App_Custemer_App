package org.app.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Customer implements Serializable{

	
	
	@Id
	@GeneratedValue
	private Integer custId;
	private String custCode;
	private String custName;
	private String custType;
	private String note;
	
	
}
