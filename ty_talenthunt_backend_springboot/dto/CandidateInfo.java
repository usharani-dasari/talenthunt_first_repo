package com.tyss.ty_talenthunt_backend_springboot.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data

@Entity
@Table(name = "candidate_info")
public class CandidateInfo {
	@Id
	@GeneratedValue
	@Column
	private Integer candidateId;
	
	@NotNull(message = "candidate name cannot be null")
	@Size(min = 1, max = 30, message = "Candidate name should be in between 1 and 30 characters")
	@Pattern(regexp = "^[a-zA-Z_ ]*$",message = "candidate name should contain only characters")
	@Column(name = "candidate_name")
	private String candidateName;
	
	@ElementCollection
	@CollectionTable(name = "candidate_skills")
	@Column
	private List<@NotBlank(message = "skill cannot be blank") String> skills;
	
	@Column(name = "recruiter_name")
	private String recruiterName;
	
	@Column(name = "ctc")
	private double ctc;
	
	@Column(name = "ectc")
	private double ectc;
	
	@Column(name = "contact_number")
	private Long contactNumber;
	
	@Column(name = "mail_id" , unique = true)
	private String mailId;
	
	@Column(name = "tot_exp")
	private double totalExperience;
	
	@Column(name = "rel_exp")
	private double relExperience;
	
	@Column(name = "curr_organisation")
	private String currOrganisation;
	
	@Column(name = "notice_period")
	private double noticePeriod;
	
	@Column(name = "current_location")
	private String currentLoc;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "joining_date")
	private LocalDate joiningDate; 
	
	@Column(name = "deployment_date ")
	private   LocalDate deploymentDate;
	
	@Column(name = "client")
    private String client;

}
