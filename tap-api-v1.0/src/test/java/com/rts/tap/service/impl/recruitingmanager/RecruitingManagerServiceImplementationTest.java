package com.rts.tap.service.impl.recruitingmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rts.tap.dao.RecruitingManagerDao;
import com.rts.tap.dto.MRFVendorDto;
import com.rts.tap.model.Employee;
import com.rts.tap.model.MRF;
import com.rts.tap.model.MRFRecruitingManager;
import com.rts.tap.serviceimplementation.RecruitingManagerServiceImplementation;

@ExtendWith(MockitoExtension.class) 
class RecruitingManagerServiceImplementationTest {

	@Mock
    private RecruitingManagerDao recruitingManagerDao;
 
    @InjectMocks
    private RecruitingManagerServiceImplementation recruitingManagerService;
 
    @Test
    public void testFetchRecruitingManagerById() {
        long id = 1L;
        Employee expectedEmployee = new Employee(); 
        when(recruitingManagerDao.getRecruitingManagerById(id)).thenReturn(expectedEmployee); 
        Employee actualEmployee = recruitingManagerService.fetchRecruitingManagerById(id); 
        assertEquals(expectedEmployee, actualEmployee);
        verify(recruitingManagerDao).getRecruitingManagerById(id);
    }
    
    @Test
    public void testGetAllMrfsAssignedForRM() {
        long id = 1L;
        MRFRecruitingManager mrfRecruitingManager1 = new MRFRecruitingManager();
        MRFRecruitingManager mrfRecruitingManager2 = new MRFRecruitingManager();
        List<MRFRecruitingManager> expectedList = Arrays.asList(mrfRecruitingManager1, mrfRecruitingManager2); 
        when(recruitingManagerDao.getAllMrfsAssignedForRM(id)).thenReturn(expectedList); 
        List<MRFRecruitingManager> actualList = recruitingManagerService.getAllMrfsAssignedForRM(id); 
        assertEquals(expectedList, actualList);
        verify(recruitingManagerDao).getAllMrfsAssignedForRM(id);
    }
    
    @Test
    public void testGetMrfById() {
        long id = 1L;
        MRF expectedMrf = new MRF(); 
        when(recruitingManagerDao.getMrfById(id)).thenReturn(expectedMrf); 
        MRF actualMrf = recruitingManagerService.getMrfById(id); 
        assertEquals(expectedMrf, actualMrf);
        verify(recruitingManagerDao).getMrfById(id);
    }
    
    @Test
    public void testMrfAssignToVendor() {
        MRFVendorDto mrfVendorDto = new MRFVendorDto();
        mrfVendorDto.setMrfId(1L);
        mrfVendorDto.setVendorId(2L);
        mrfVendorDto.setRecrutingManagerId(3L);
        String expectedMessage = "MRF assigned to vendor successfully"; 
        when(recruitingManagerDao.assignMrfToVendor(mrfVendorDto)).thenReturn(expectedMessage); 
        String actualMessage = recruitingManagerService.mrfAssignToVendor(mrfVendorDto); 
        assertEquals(expectedMessage, actualMessage);
        verify(recruitingManagerDao).assignMrfToVendor(mrfVendorDto);
    }

}
