package com.tyss.talenthuntnew.service;

import java.util.List;

import com.tyss.talenthuntnew.dto.RequirementInfo;

public interface TalentHuntService {
	public RequirementInfo addRequirement(RequirementInfo req); 
	public List<RequirementInfo> getAllRequirements();
	public boolean deleteRequirement(String reqId);
	public RequirementInfo searchRequirementById(String reqId);
	public boolean updateRequirement(RequirementInfo req);

}
