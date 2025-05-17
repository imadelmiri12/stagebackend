package ma.zyn.app.unit.ws.facade.admin.mensuel;

import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelTypeEvenement;
import ma.zyn.app.service.impl.admin.mensuel.RapportEvenementMensuelTypeEvenementAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.mensuel.RapportEvenementMensuelTypeEvenementRestAdmin;
import ma.zyn.app.ws.converter.mensuel.RapportEvenementMensuelTypeEvenementConverter;
import ma.zyn.app.ws.dto.mensuel.RapportEvenementMensuelTypeEvenementDto;
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
public class RapportEvenementMensuelTypeEvenementRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private RapportEvenementMensuelTypeEvenementAdminServiceImpl service;
    @Mock
    private RapportEvenementMensuelTypeEvenementConverter converter;

    @InjectMocks
    private RapportEvenementMensuelTypeEvenementRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllRapportEvenementMensuelTypeEvenementTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<RapportEvenementMensuelTypeEvenementDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<RapportEvenementMensuelTypeEvenementDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveRapportEvenementMensuelTypeEvenementTest() throws Exception {
        // Mock data
        RapportEvenementMensuelTypeEvenementDto requestDto = new RapportEvenementMensuelTypeEvenementDto();
        RapportEvenementMensuelTypeEvenement entity = new RapportEvenementMensuelTypeEvenement();
        RapportEvenementMensuelTypeEvenement saved = new RapportEvenementMensuelTypeEvenement();
        RapportEvenementMensuelTypeEvenementDto savedDto = new RapportEvenementMensuelTypeEvenementDto();

        // Mock the converter to return the rapportEvenementMensuelTypeEvenement object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved rapportEvenementMensuelTypeEvenement DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<RapportEvenementMensuelTypeEvenementDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        RapportEvenementMensuelTypeEvenementDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved rapportEvenementMensuelTypeEvenement DTO
        assertEquals(savedDto.getTotal(), responseBody.getTotal());
        assertEquals(savedDto.getTotalCumul(), responseBody.getTotalCumul());
        assertEquals(savedDto.getIndicateurGlobalCumul(), responseBody.getIndicateurGlobalCumul());
    }

}
