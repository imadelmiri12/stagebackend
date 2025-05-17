package ma.zyn.app.unit.ws.facade.admin.journal;

import ma.zyn.app.bean.core.journal.ResultatEvaluation;
import ma.zyn.app.service.impl.admin.journal.ResultatEvaluationAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.journal.ResultatEvaluationRestAdmin;
import ma.zyn.app.ws.converter.journal.ResultatEvaluationConverter;
import ma.zyn.app.ws.dto.journal.ResultatEvaluationDto;
import org.aspectj.lang.annotation.Before;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ResultatEvaluationRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private ResultatEvaluationAdminServiceImpl service;
    @Mock
    private ResultatEvaluationConverter converter;

    @InjectMocks
    private ResultatEvaluationRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllResultatEvaluationTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<ResultatEvaluationDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<ResultatEvaluationDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveResultatEvaluationTest() throws Exception {
        // Mock data
        ResultatEvaluationDto requestDto = new ResultatEvaluationDto();
        ResultatEvaluation entity = new ResultatEvaluation();
        ResultatEvaluation saved = new ResultatEvaluation();
        ResultatEvaluationDto savedDto = new ResultatEvaluationDto();

        // Mock the converter to return the resultatEvaluation object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved resultatEvaluation DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<ResultatEvaluationDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        ResultatEvaluationDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved resultatEvaluation DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLabel(), responseBody.getLabel());
        assertEquals(savedDto.getStyle(), responseBody.getStyle());
    }

}
