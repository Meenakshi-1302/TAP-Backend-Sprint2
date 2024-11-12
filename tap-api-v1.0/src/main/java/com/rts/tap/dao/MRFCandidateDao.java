package com.rts.tap.dao;

import java.util.List;

import com.rts.tap.model.Candidate;
import com.rts.tap.model.MRFCandidate;

public interface MRFCandidateDao {

	 List<MRFCandidate> getAllCandidates();
	 MRFCandidate getCandidateById(Long id);
	MRFCandidate saveCandidate(MRFCandidate mrfCandidate);
	void deleteCandidate(Long id);
	List<Candidate> getRemainingCandidates(Long mrfId);
}
