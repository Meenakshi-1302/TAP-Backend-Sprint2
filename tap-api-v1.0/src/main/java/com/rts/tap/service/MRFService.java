package com.rts.tap.service;

import java.util.List;

import com.rts.tap.model.MRF;

public interface MRFService {

	MRF addMrf(MRF mrf);

	MRF updateMrf(Long mrfId, MRF mrf);

	String deleteMrfById(long mrfId);

	MRF getMrfById(long mrfId);

	List<MRF> getAllMrf();

}
