package com.student.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//@EqualsAndHashCode

@Data
@Entity
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addressId;
	private String plotNo;
	private String city;
	private String state;

	@OneToOne
	@JoinColumn(name = "student_id")
	private Student student;
	
}
