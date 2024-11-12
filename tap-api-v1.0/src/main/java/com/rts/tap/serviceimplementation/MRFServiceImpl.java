package com.rts.tap.serviceimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rts.tap.dao.MRFDao;
import com.rts.tap.model.MRF;
import com.rts.tap.model.MRFAgreement;
import com.rts.tap.model.MRFCriteria;
import com.rts.tap.model.MRFStatus;
import com.rts.tap.service.MRFService;

@Service
public class MRFServiceImpl implements MRFService {

	@Autowired
	MRFDao mrfDao;

	@Override
	public MRF addMrf(MRF mrf) {
		MRFCriteria mrfCriteria = mrf.getMrfCriteria();
		MRFAgreement mrfAgreement = mrf.getMrfAgreement();
		MRFStatus mrfStatus = mrf.getMrfStatus();
		
		mrfDao.mrfStatusSave(mrfStatus);
		mrfDao.mrfAgreementSave(mrfAgreement);
		mrfDao.mrfCriteriaSave(mrfCriteria);
		
		mrf.setMrfAgreement(mrfAgreement);
		mrf.setMrfCriteria(mrfCriteria);
		mrf.setMrfStatus(mrfStatus);
		
		return mrfDao.mrfSave(mrf);
	}

	@Override
	public MRF updateMrf(Long mrfId, MRF mrf) {
		return mrfDao.mrfUpdate(mrfId, mrf);
	}

	@Override
	public String deleteMrfById(long mrfId) {
		return mrfDao.mrfDelete(mrfId);
	}

	@Override
	public MRF getMrfById(long mrfId) {
		return mrfDao.getMrf(mrfId);
	}

	@Override
	public List<MRF> getAllMrf() {
		return mrfDao.getAllMRF();
	}

}
