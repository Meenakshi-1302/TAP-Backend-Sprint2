
package com.rts.tap.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rts.tap.constants.APIConstants;
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.model.MRF;
import com.rts.tap.service.MRFService;

@RestController
@RequestMapping(APIConstants.BASE_URL)
@CrossOrigin(MessageConstants.ORGIN)
public class MRFController {

	private MRFService mrfService;

	public MRFController(MRFService mrfService) {
		super();
		this.mrfService = mrfService;
	}

	@PostMapping(APIConstants.ADD_MRF)
	public ResponseEntity<MRF> saveMRF(@RequestParam("mrf") String mrfJson,
			@RequestParam("sla") MultipartFile slaFile) {
		try {

			if (slaFile == null || slaFile.isEmpty()) {
				return ResponseEntity.badRequest().body(null);
			}

			ObjectMapper objectMapper = new ObjectMapper();
			MRF mrf = objectMapper.readValue(mrfJson, MRF.class);

			byte[] slaBytes = slaFile.getBytes();

			if (mrf.getMrfAgreement() != null) {
				mrf.getMrfAgreement().setServiceLevelAgreement(slaBytes);
			} else {
				return ResponseEntity.badRequest().body(null);
			}

			MRF savedMRF = mrfService.addMrf(mrf);
			return ResponseEntity.ok(savedMRF);
		} catch (JsonProcessingException e) {
			return ResponseEntity.badRequest().body(null);
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PutMapping(APIConstants.UPDATE_MRF)
	public ResponseEntity<MRF> updateMRF(@PathVariable Long mrfId, @RequestBody MRF mrf,
			@RequestParam(value = "sla", required = false) MultipartFile slaFile) {
		try {
			if (slaFile != null && !slaFile.isEmpty()) {
				byte[] slaBytes = slaFile.getBytes();
				mrf.getMrfAgreement().setServiceLevelAgreement(slaBytes);
			}
			MRF updatedMRF = mrfService.updateMrf(mrfId, mrf);
			return ResponseEntity.ok(updatedMRF);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping(APIConstants.GET_MRF)
	public ResponseEntity<MRF> getMRFById(@PathVariable Long mrfId) {
		MRF mrf = mrfService.getMrfById(mrfId);
		return mrf != null ? ResponseEntity.ok(mrf) : ResponseEntity.notFound().build();
	}

	@GetMapping(APIConstants.GET_ALL_MRF)
	public ResponseEntity<List<MRF>> getAllMRFs() {
		List<MRF> mrfs = mrfService.getAllMrf();
		return ResponseEntity.ok(mrfs);
	}

	@DeleteMapping(APIConstants.DELETE_MRF)
	public ResponseEntity<String> deleteMRF(@PathVariable Long mrfId) {
		String msg = mrfService.deleteMrfById(mrfId);
		return ResponseEntity.ok(msg);

	}

}
