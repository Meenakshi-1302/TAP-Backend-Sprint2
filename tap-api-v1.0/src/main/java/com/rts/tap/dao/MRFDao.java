package com.rts.tap.dao;

import java.util.List;

import com.rts.tap.model.MRF;
import com.rts.tap.model.MRFAgreement;
import com.rts.tap.model.MRFCriteria;
import com.rts.tap.model.MRFStatus;

public interface MRFDao {

	MRF mrfSave(MRF mrf);

	MRF mrfCriteriaSave(MRFCriteria mrfCriteria);

	MRF mrfAgreementSave(MRFAgreement mrfAgreement);

	MRF mrfStatusSave(MRFStatus mrfStatus);

	MRF mrfUpdate(Long mrfId, MRF mrf);

	String mrfDelete(Long mrfId);

	MRF getMrf(long mrfId);

	List<MRF> getAllMRF();
	public MRF findById(Long mrfId);

}


