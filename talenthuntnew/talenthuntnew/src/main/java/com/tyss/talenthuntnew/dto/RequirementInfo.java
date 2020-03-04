package com.tyss.talenthuntnew.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "Requirement_Info")
public class RequirementInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "string_based_custom_sequence")
	@GenericGenerator(name = "string_based_custom_sequence", strategy = "com.tyss.talenthuntnew.dto.RequirementIdGenerator")
	private String reqId;

	@Column
	@NotBlank(message = "client full name is mandatory")
	@Pattern(regexp = "^[a-zA-Z_ ]*$", message = "client full name should contain only characters")
	@Size(max = 30, min = 1, message = "client full name should be in between 1 & 30")
	private String clientName;

	@Column
	@NotBlank(message = "client short name is mandatory")
	@Pattern(regexp = "^[a-zA-Z_ ]*$", message = "client short name should contain only characters")
	@Size(max = 30, min = 1, message = "client short name should be in between 1 & 30")
	private String clientshortName;

	@Column
	@NotBlank(message = "job title is mandatory")
	@Pattern(regexp = "^[a-zA-Z0-9_ ]*$", message = "client short name should contain only characters")
	@Size(max = 25, min = 1, message = "title should be in between 1 & 25")
	private String title;

	@Column
	@Lob
	private String description;

	@Column
	@Min(value = 1, message = "minimum 1 position should be there")
	@Max(value = 500000, message = "max number of positions are 500000")
	private Integer positions;

	@Column
	@NotBlank(message = "location is mandatory")
	@Size(max = 20, min = 1, message = "location should be in between 1 & 25")
	private String location;

	@Column
	@Max(value = 100, message = "no one will have more than 100 years experiance")
	private int yearOfExp;

	@Column
	@NotBlank(message = "qualification is mandatory")
	@Pattern(regexp = "[^0-9]+$", message = "qualification cannot contain numbers")
	private String qualification;

	@ElementCollection
	@CollectionTable(name = "skills")
	@Column
	private List<String> skills;

	@Column
	private LocalDate startDate;

	@Column
	@Future(message = "ending date cannot be today or less")
	private LocalDate endDate;

	@Column
	@Max(value = 1000000000 ,  message = "no one will have more than 1000000000 salary")
	private Long salaryFrom;

	@Column
	@Max(value = 1000000000 ,  message = "no one will have more than 1000000000 salary")
	private Long salaryTo;
	
	@Column
	private String status;

}
