package com.rts.tap.dao;

import java.util.List;
import com.rts.tap.model.Candidate;

public interface CandidateDao {

	void save(Candidate candidate);
	

	void update(Candidate candidate);

	Candidate findById(Long id);

	List<Candidate> findAll();

	void delete(Long id);
}
