package ma.zyn.app.unit.ws.facade.admin.rapport;

import ma.zyn.app.bean.core.rapport.RapportEvenement;
import ma.zyn.app.service.impl.admin.rapport.RapportEvenementAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.rapport.RapportEvenementRestAdmin;
import ma.zyn.app.ws.converter.rapport.RapportEvenementConverter;
import ma.zyn.app.ws.dto.rapport.RapportEvenementDto;
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
public class RapportEvenementRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private RapportEvenementAdminServiceImpl service;
    @Mock
    private RapportEvenementConverter converter;

    @InjectMocks
    private RapportEvenementRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllRapportEvenementTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<RapportEvenementDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<RapportEvenementDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveRapportEvenementTest() throws Exception {
        // Mock data
        RapportEvenementDto requestDto = new RapportEvenementDto();
        RapportEvenement entity = new RapportEvenement();
        RapportEvenement saved = new RapportEvenement();
        RapportEvenementDto savedDto = new RapportEvenementDto();

        // Mock the converter to return the rapportEvenement object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved rapportEvenement DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<RapportEvenementDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        RapportEvenementDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved rapportEvenement DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getDateEvenement(), responseBody.getDateEvenement());
        assertEquals(savedDto.getDateSoumission(), responseBody.getDateSoumission());
        assertEquals(savedDto.getAttachments(), responseBody.getAttachments());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
        assertEquals(savedDto.getRecommendation(), responseBody.getRecommendation());
        assertEquals(savedDto.getDirectivePmjChef(), responseBody.getDirectivePmjChef());
        assertEquals(savedDto.getMois(), responseBody.getMois());
        assertEquals(savedDto.getAnnee(), responseBody.getAnnee());
        assertEquals(savedDto.getWindDirection(), responseBody.getWindDirection());
        assertEquals(savedDto.getWindForce(), responseBody.getWindForce());
        assertEquals(savedDto.getCurrentDirection(), responseBody.getCurrentDirection());
        assertEquals(savedDto.getCurrentForce(), responseBody.getCurrentForce());
        assertEquals(savedDto.getSwellHeigth(), responseBody.getSwellHeigth());
        assertEquals(savedDto.getSwellDirection(), responseBody.getSwellDirection());
        assertEquals(savedDto.getFonde(), responseBody.getFonde());
    }

}
