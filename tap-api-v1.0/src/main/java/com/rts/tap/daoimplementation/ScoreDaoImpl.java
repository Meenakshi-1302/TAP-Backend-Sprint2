package com.rts.tap.daoimplementation;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rts.tap.dao.ScoreDao;
import com.rts.tap.model.Score;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ScoreDaoImpl implements ScoreDao {

    private EntityManager entityManager;

    public ScoreDaoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public void save(Score score) {
        entityManager.persist(score);
    }

    public void update(Score score) {
        entityManager.merge(score);
    }

    public Score findById(Long id) {
        return entityManager.find(Score.class, id);
    }

    public List<Score> findAll() {
        return entityManager.createQuery("from Score", Score.class).getResultList();
    }

    public void delete(Long id) {
        Score score = findById(id);
        if (score != null) {
            entityManager.remove(score);
        }
    }
    
    public List<Score> findByMrfId(Long id) {
        String hql = "SELECT s FROM Score s WHERE s.assessment.recruitmentProcess.mrf.mrfId = :mrfId";
        return entityManager.createQuery(hql, Score.class)
                            .setParameter("mrfId", id)
                            .getResultList();
    }

}

