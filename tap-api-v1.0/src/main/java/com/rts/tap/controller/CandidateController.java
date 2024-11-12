package com.rts.tap.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.model.Candidate;
import com.rts.tap.service.CandidateService;

@CrossOrigin(origins=APIConstants.FRONT_END_URL)
@RestController
@RequestMapping(path=APIConstants.BASE_CANDIDATE_URL)
public class CandidateController {

    private CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
		super();
		this.candidateService = candidateService;
	}
    
    @PostMapping(APIConstants.SAVE_CANDIDATE_URL)
    public void createCandiadte(@RequestBody Candidate candidate) {
    	 candidateService.save(candidate);
    	
    }

//    @PostMapping(APIConstants.SAVE_CANDIDATE_URL)
//    public void createCandidate(@RequestParam("firstName") String firstName,
//                                @RequestParam("lastName") String lastName,
//                                @RequestParam("mobileNumber") String mobileNumber,
//                                @RequestParam("email") String email,
//                                @RequestParam("experience") int experience,
//                                @RequestParam("resume") String resume,
//                                @RequestParam("source") String source,
//                                @RequestParam("sourceId") long sourceId,
//                                @RequestParam("skill") String skill,
//                                @RequestParam("location") String location,
//                                @RequestParam("panNumber") String panNumber,
//                                @RequestParam("status") String status,
//                                @RequestParam("candidateResume") MultipartFile candidateResume)
//    							 {
//
//        try {
//            byte[] resumeData = resume.getBytes(); // Convert the resume file to byte array
//            
//            Candidate candidate = new Candidate();
//            candidate.setFirstName(firstName);
//            candidate.setLastName(lastName);
//            candidate.setMobileNumber(mobileNumber);
//            candidate.setEmail(email);
//            candidate.setExperience(experience);
//            candidate.setResume(resume);
//            candidate.setSource(source);
//            candidate.setSourceId(sourceId);
//            candidate.setSkill(skill);
//            candidate.setLocation(location);
//            candidate.setPanNumber(panNumber);
//            candidate.setStatus(status);
//            candidate.setCandidateResume(resumeData); // Set the BLOB (resume)
//
//            candidateService.save(candidate); // Save the candidate with the resume (BLOB)
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("Error uploading candidate: " + e.getMessage());
//        }
//    }

	/*Commented by team- D 
	 * Author - Meenakshi Priyadharshini Ananthan*/
//	@PostMapping("/uploadCandidates")
//    public String uploadCandidates(@RequestParam("file") MultipartFile file) {
//        try {
//            candidateService.bulkSave(file);
//            return "Candidates uploaded successfully!";
//        } catch (Exception e) {
//            return "Error uploading candidates: " + e.getMessage();
//        }
//    }
	


    @GetMapping(path=APIConstants.GET_ALL_CANDIDATE_URL)
    public List<Candidate> getAllCandidates() {
        return candidateService.findAll();
    }

    @GetMapping(path=APIConstants.GET_BY_ID_CANDIDATE_URL)
    public Candidate getCandidateById(@PathVariable Long id) {
        return candidateService.findById(id);
    }

    @PutMapping(path=APIConstants.UPDATE_CANDIDATE_URL)
    public void updateCandidate(@PathVariable Long id, @RequestBody Candidate candidate) {
        candidate.setCandidateId(id);
        candidateService.update(candidate);
    }

    @DeleteMapping(path=APIConstants.DELETE_CANDIDATE_URL)
    public void deleteCandidate(@PathVariable Long id) {
        candidateService.delete(id);
    }
}

