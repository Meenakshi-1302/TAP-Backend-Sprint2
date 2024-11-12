package com.rts.tap.service;

import com.rts.tap.model.Candidate;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface CandidateService {
    void save(Candidate candidate);
    /*Commented by team-D
     *  Author- Meenkashi Priyadharshini Ananthan*/
//    void bulkSave(MultipartFile csvFile) throws Exception;
    List<Candidate> findAll();
    Candidate findById(Long id);
    void update(Candidate candidate);
    void delete(Long id);
}

