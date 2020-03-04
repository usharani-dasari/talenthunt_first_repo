package com.tyss.talenthuntnew.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.talenthuntnew.dto.RequirementIdGenerator;
import com.tyss.talenthuntnew.dto.RequirementInfo;
import com.tyss.talenthuntnew.exception.GlobalCustomException;
import com.tyss.talenthuntnew.repo.RequirementInfoRepo;


@Service
public class TalentHuntServiceJpaImpl implements TalentHuntService {
	
	@Autowired
	private RequirementInfoRepo requirementRepo;
	@Autowired
	private RequirementIdGenerator generator;
	
	@Override
	public RequirementInfo addRequirement(RequirementInfo req) {
		if(req == null) throw new GlobalCustomException("Requirement Information cannot be null");
		if(req.getStartDate() != null && req.getEndDate() != null) {
			if(req.getStartDate().isAfter(req.getEndDate())) throw new GlobalCustomException("start date cannot be greater than end Date");
		}else {
			throw new GlobalCustomException("Start Date and End Date are Mandatory");
		}
		if(req.getPositions() != null) {
			if(req.getPositions() <= 0) throw new GlobalCustomException("postions cannot be Zero or lesser than that");
		}else {
			throw new GlobalCustomException("positions are mandatory");
		}
		if(req.getSalaryFrom() != null && req.getSalaryTo() != null) {
			if(req.getSalaryFrom() > req.getSalaryTo()) throw new GlobalCustomException("starting salary cannot be greater than ending salary");
		}else {
			throw new GlobalCustomException(" slary From and salaryTo are Mandatory");
		}
		
	    generator.generate(req);
	    req.setStatus("Active");
	    requirementRepo.save(req);
	    return req;
		
	}

	@Override
	public List<RequirementInfo> getAllRequirements() {
	     return  requirementRepo.findAll();
	}

	@Override
	public boolean deleteRequirement(String reqId) {
		requirementRepo.deleteRequirement(reqId);
		return true;
	}

	@Override
	public RequirementInfo searchRequirementById(String reqId) {
		return requirementRepo.searchById(reqId);
	}

	@Override
	public boolean updateRequirement(RequirementInfo req) {
		RequirementInfo requirement = requirementRepo.findById(req.getReqId()).get();
		requirement.getSkills().clear();
		requirement.setSkills(req.getSkills());
		requirementRepo.updateRequirement(req.getClientName() , req.getClientshortName() , req.getDescription() , req.getEndDate() ,
				req.getStartDate() , req.getLocation() , req.getPositions() , req.getQualification() , req.getSalaryFrom() ,
				req.getSalaryTo() , req.getTitle() , req.getYearOfExp() , req.getStatus() , req.getReqId());
		return true;
	}

}
