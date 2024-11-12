package com.rts.tap.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rts.tap.dao.BusinessUnitDao;
import com.rts.tap.model.BusinessUnit;
import com.rts.tap.service.BusinessUnitService;


@Service
public class BusinessUnitServiceImpl implements BusinessUnitService {

    @Autowired
    BusinessUnitDao repo;

	public void addBusinessUnit(BusinessUnit businessUnit) {
		repo.save(businessUnit);
	}

//	@Override
//	public List<Organization> getAllOrganization() {
//		return repo.getAllOrganization();
//		 
//	}

}
