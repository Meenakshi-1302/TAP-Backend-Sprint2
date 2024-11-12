package com.rts.tap.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dto.CandidateDto;
import com.rts.tap.dto.VendorDto;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.MRFCandidate;
import com.rts.tap.model.Vendor;
import com.rts.tap.service.VendorService;

import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;

/** 
 * author: Jeevarajan Rajarajacholan, Vasanth Thopey Sivakumar
 * version: v1.0
 * updated at: 04-11-2024
**/

@RestController
@CrossOrigin(APIConstants.CROSS_ORIGIN_URL)
@RequestMapping(APIConstants.VENDOR_URL)
public class VendorController {

    private VendorService vendorService;
    private static final Logger logger = LoggerFactory.getLogger(VendorController.class);

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    /**
	 * this api will accept Vendor dto and perform add operation and return Vendor object
	 * @param(vendor's Organization Name) - String
	 * @param(vendor's email Id) - String  
	 * @return Vendor - object
	**/
    @PostMapping
	public ResponseEntity<Vendor> doAddNewVendor(@RequestBody VendorDto vendorDto) throws MessagingException{		
		return new ResponseEntity<>(vendorService.addNewVendor(vendorDto),HttpStatus.OK);
	}

    /**
	 * this api will accept vendor id and perform fetch operation and return VendorDto as object
	 * @param(vendor's id) - long  
	 * @return VendorDto - object
	**/
    
//    @PostMapping(APIConstants.VENDOR_ADD_CANDIDATE)
//    public ResponseEntity<String> addCandidate(@RequestBody Candidate candidate, @RequestParam MultipartFile candidateResume)throws MessagingException{
//    	return new ResponseEntity<>(vendorService.addNewCandidate(candidate, candidateResume),HttpStatus.OK);
//    }
    @PostMapping(APIConstants.VENDOR_ADD_CANDIDATE)
    public ResponseEntity<String> addCandidate(@ModelAttribute CandidateDto candidateDto) throws MessagingException {
        String resultMessage = vendorService.addNewCandidate(candidateDto);
        return new ResponseEntity<>(resultMessage, HttpStatus.OK);
    }



    
    
    @GetMapping(APIConstants.VENDOR_GET_BY_ID)
    public ResponseEntity<VendorDto> getVendorById(@PathVariable Long id) {
        return ResponseEntity.ok(vendorService.getVendorById(id));
    }

    /**
	 * this api will perform fetch operation and return all vendors available 
	 * in list of VendorDto as object  
	 * @return list of VendorDto - object
	**/
    @GetMapping(APIConstants.VENDOR_GET_ALL)
    public ResponseEntity<List<VendorDto>> getAllVendors() {
        return ResponseEntity.ok(vendorService.getAllVendors());
    }

    /**
	 * this api will accept Vendor dto and vendor Id and perform udpate operation and return Vendor object
	 * sample params
	 * @param(vendor's Organization Name) - String
	 * @param(vendor's email Id) - String, 
	 * @return Vendor - object
	**/
    @PutMapping(APIConstants.VENDOR_UPDATE)
    public ResponseEntity<Vendor> doUpdateVendor(@PathVariable Long id, @RequestBody VendorDto vendor) {
        try {
            Vendor updatedVendor = vendorService.updateVendor(id, vendor);
            if (updatedVendor == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(updatedVendor, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception for debugging
            logger.error("Error updating vendor", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
	 * this api will accept vendor id and perform delete operation and return deletion message
	 * @param(vendor's id) - long  
	 * @return String - message for deletion status
	**/
    @DeleteMapping(APIConstants.VENDOR_DELETE)
    public ResponseEntity<String> doDeleteVendor(@PathVariable Long id) {
    	try {
    		vendorService.deleteVendor(id);
    		return ResponseEntity.ok().body(MessageConstants.VENDOR_DELETED_SUCCESS);			
		} catch (Exception e) {
			logger.error("Error deleting vendor", e);
			return ResponseEntity.ok().body(MessageConstants.VENDOR_DELETED_FAILED);			
		}
    }
    
    @PostMapping("/login")
    public ResponseEntity<VendorDto> doVendorLogin(@RequestBody VendorDto vendorDto){
    	try {
            VendorDto vendor = vendorService.dologin(vendorDto);
            if (vendor == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(vendor, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception for debugging
            logger.error("Error logging in vendor", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/countBySourceAndYear")
    public ResponseEntity<List<String>> getCandidateCountBySourceAndYear(
            @RequestParam("sourceId") Long sourceId,
            @RequestParam("year") int year) {  // Make sure the year parameter is passed

        List<String> result = vendorService.getCandidateCountBySourceAndYear(sourceId, year);
        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/bulk")
    public void bulkUpload(@RequestParam("file") MultipartFile file) throws IOException {
        List<CandidateDto> candidateDTOs = new ArrayList<>();

        try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
            var sheet = workbook.getSheetAt(0);
            
            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Skip header row
                Row row = sheet.getRow(i);
                if (row != null) {
                    CandidateDto candidateDTO = new CandidateDto();
                    
                    // First name (STRING cell type)
                    candidateDTO.setFirstName(getStringCellValue(row.getCell(0)));
                    
                    // Last name (STRING cell type)
                    candidateDTO.setLastName(getStringCellValue(row.getCell(1)));
                    
                    // Mobile number (STRING cell type)
                    candidateDTO.setMobileNumber(getStringCellValue(row.getCell(2)));
                    
                    // Email (STRING cell type)
                    candidateDTO.setEmail(getStringCellValue(row.getCell(3)));
                    
                    // Experience (NUMERIC cell type, assuming it is an integer)
                    candidateDTO.setExperience((int) getNumericCellValue(row.getCell(4)));
                    
                    // Resume (STRING cell type)
                    candidateDTO.setResume(getStringCellValue(row.getCell(5)));
                    
                    // Source (STRING cell type)
                    candidateDTO.setSource(getStringCellValue(row.getCell(6)));
                    
                    // Source ID (NUMERIC cell type)
                    candidateDTO.setSourceId((long) getNumericCellValue(row.getCell(7)));
                    
                    // Skill (STRING cell type)
                    candidateDTO.setSkill(getStringCellValue(row.getCell(8)));
                    
                    // Location (STRING cell type)
                    candidateDTO.setLocation(getStringCellValue(row.getCell(9)));
                    
                    // PAN Number (STRING cell type)
                    candidateDTO.setPanNumber(getStringCellValue(row.getCell(10)));
                    
                    // Status (STRING cell type)
                    candidateDTO.setStatus(getStringCellValue(row.getCell(11)));
                    
                    // Assigned at (STRING cell type, assuming it's a date string)
                    candidateDTO.setAssignedAt(parseDate(getStringCellValue(row.getCell(12))));
                    
                    //candidateDTO.setMrfCandidate(getStringCellValue(row.getCell(13)));

                    // Add to the list of candidate DTOs
                    candidateDTOs.add(candidateDTO);
                }
            }
        }

        // Pass the DTOs to the service for saving
        vendorService.saveAllCandidates(candidateDTOs); 
    }

    // Utility method to safely get string value from a cell, handling different cell types
    private String getStringCellValue(Cell cell) {
        if (cell == null) return "";  // If the cell is null, return an empty string
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";  // Default case if the cell type is unexpected
        }
    }

    // Utility method to safely get numeric value from a cell, handling different cell types
    private double getNumericCellValue(Cell cell) {
        if (cell == null) return 0;  // Return 0 if cell is null
        switch (cell.getCellType()) {
            case NUMERIC:
                return cell.getNumericCellValue();
            case STRING:
                try {
                    return Double.parseDouble(cell.getStringCellValue());  // Convert string to double if needed
                } catch (NumberFormatException e) {
                    return 0;  // Return 0 if the value is not a valid number
                }
            default:
                return 0;  // Return 0 for any other case
        }
    }

    // Utility method to parse date from a string (you might need to adjust the format)
    private LocalDateTime parseDate(String dateStr) {
        try {
            return LocalDateTime.parse(dateStr);  // Adjust the format if necessary (e.g., using DateTimeFormatter)
        } catch (Exception e) {
            return null;  // Return null if parsing fails
        }
    }

    
   
}
