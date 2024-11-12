package com.rts.tap.serviceimplementation;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rts.tap.dao.CandidateDao;
import com.rts.tap.model.Candidate;
import com.rts.tap.service.CandidateService;
//import com.rts.tap.utils.CSVParserUtils;

@Service
public class CandidateServiceImpl implements CandidateService {

    private CandidateDao candidateDao;

    public CandidateServiceImpl(CandidateDao candidateDao) {
		super();
		this.candidateDao = candidateDao;
	}

	@Override
    public void save(Candidate candidate) {
    	candidateDao.save(candidate);
    }
	/*Commented by team-D 
	 * Author- Meenkashi Priyadharshini Ananthan*/
	
//	@Override
//    public void bulkSave(MultipartFile csvFile) throws Exception {
//        List<Candidate> candidates = CSVParserUtils.parseCSV(csvFile);
//
//        for (Candidate candidate : candidates) {
//            candidateDao.save(candidate);
//        }
//    }

    @Override
    public List<Candidate> findAll() {
        return candidateDao.findAll();
    }

    @Override
    public Candidate findById(Long id) {
        return candidateDao.findById(id);
    }

    @Override
    public void update(Candidate candidate) {
    	candidateDao.update(candidate);
    }

    @Override
    public void delete(Long id) {
    	candidateDao.delete(id);
    }
}

