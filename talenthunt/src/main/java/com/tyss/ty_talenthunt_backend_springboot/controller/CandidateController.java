package com.tyss.ty_talenthunt_backend_springboot.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.executable.ValidateOnExecution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.ty_talenthunt_backend_springboot.dto.CandidateInfo;
import com.tyss.ty_talenthunt_backend_springboot.dto.RequirementInfo;
import com.tyss.ty_talenthunt_backend_springboot.dto.Response;
import com.tyss.ty_talenthunt_backend_springboot.service.TalentHuntService;

@CrossOrigin(allowedHeaders = "*" ,origins = "*",allowCredentials = "true" )
@RestController
@ValidateOnExecution
@RequestMapping(path = "/candidate")
public class CandidateController {
	@Autowired
	private TalentHuntService service;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		CustomDateEditor editor = new CustomDateEditor(format, true);
		binder.registerCustomEditor(Date.class, editor);
	}

	@PostMapping(path = "/add" , consumes = MediaType.APPLICATION_JSON_VALUE ,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addCandidate(@Valid @RequestBody CandidateInfo candidateInfo){

		CandidateInfo candidateInfoNew = service.addCandidate(candidateInfo);
		Response resp = new Response();
		if(candidateInfoNew != null) {
			resp.setError(false);
			resp.setData(candidateInfoNew);;
			return new ResponseEntity<Object>(resp , HttpStatus.OK);
		}else {
			resp.setError(true);
			resp.setMessage("Candidate not added");
			return new ResponseEntity<Object>(resp , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(path = "/deletebyid/{candidateId}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteById(@PathVariable("candidateId") Integer candidateId){
		Response resp = new Response();
		CandidateInfo candidate = service.deleteCandidate(candidateId);
		if(candidate != null) {
			resp.setError(false);
			resp.setData("Candidate with ID " + candidateId + " and name " + candidate.getCandidateName() + " deleted");
			return new ResponseEntity<Object>(resp , HttpStatus.OK);
		}else {
			resp.setError(true);
			resp.setMessage("Candidate Not Deleted");
			return new ResponseEntity<Object>(resp , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path = "/searchbyid/{id}")
	public ResponseEntity<Object> searchById(@PathVariable("id") int id){
		Response resp = new Response();
		CandidateInfo candidateInfo = service.getCandidateById(id);
		if(candidateInfo != null) {
			resp.setError(false);
			resp.setData(candidateInfo);
			return new ResponseEntity<Object>(resp , HttpStatus.OK);
		}else {
			resp.setError(true);
			resp.setMessage("candidate not found");
			return new ResponseEntity<Object>(resp , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(path = "/update" , consumes = MediaType.APPLICATION_JSON_VALUE ,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateCandidate(@Valid @RequestBody CandidateInfo candidateInfo){
		System.err.println(candidateInfo.getCandidateId());
		Response resp = new Response();
		CandidateInfo candidateInfoNew = service.updateCandidate(candidateInfo);
		if(candidateInfoNew != null) {
			resp.setError(false);
			resp.setData("Candidate with ID " + candidateInfoNew.getCandidateId() + " is updated");
			return new ResponseEntity<Object>(resp , HttpStatus.OK);
		}else {
			resp.setError(true);
			resp.setMessage("Candidate  not Modified");
			return new ResponseEntity<Object>(resp , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping(path = "/searchbyemail/{mailId}")
	public ResponseEntity<Object> searchById(@PathVariable("mailId") String mailId){
		Response resp = new Response();
		CandidateInfo candidateInfo = service.getCandidateByEmail(mailId);
		if(candidateInfo != null) {
			resp.setError(false);
			resp.setData(candidateInfo);
			return new ResponseEntity<Object>(resp , HttpStatus.OK);
		}else {
			resp.setError(true);
			resp.setMessage("candidate not found");
			return new ResponseEntity<Object>(resp , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path = "/getall" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllCandidates(){
		List<CandidateInfo> candidates = service.getAllCandidates();
		Response resp = new Response();
		if(candidates != null) {
			resp.setError(false);
			resp.setData(candidates);
			return new ResponseEntity<Object>(resp , HttpStatus.OK);
		}else {
			resp.setError(true);
			resp.setMessage("Candidates not found");
			return new ResponseEntity<Object>(resp , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path = "/getallactivecandidates" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllActiveCandidates(){
		List<CandidateInfo> candidates = service.getAllActiveCandidates();
		Response resp = new Response();
		if(candidates != null) {
			resp.setError(false);
			resp.setData(candidates);
			return new ResponseEntity<Object>(resp , HttpStatus.OK);
		}else {
			resp.setError(true);
			resp.setMessage("Candidates not found");
			return new ResponseEntity<Object>(resp , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
