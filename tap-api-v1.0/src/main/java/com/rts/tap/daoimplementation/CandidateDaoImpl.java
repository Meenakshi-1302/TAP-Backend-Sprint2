package com.rts.tap.daoimplementation;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rts.tap.dao.CandidateDao;
import com.rts.tap.model.Candidate;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CandidateDaoImpl implements CandidateDao {

	private EntityManager entityManager;

	public CandidateDaoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public void save(Candidate candidate) {
		entityManager.persist(candidate);
	}
	
	

	public List<Candidate> findAll() {
		return entityManager.createQuery("from Candidate", Candidate.class).getResultList();
	}

	public Candidate findById(Long id) {
		return entityManager.find(Candidate.class, id);
	}

	public void update(Candidate candidate) {
		entityManager.merge(candidate);
	}

	public void delete(Long id) {
		Candidate candidate = findById(id);
		if (candidate != null) {
			entityManager.remove(candidate);
		}
	}
	
	
}
