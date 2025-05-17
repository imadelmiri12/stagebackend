package ma.zyn.app.unit.ws.facade.admin.mensuel;

import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuel;
import ma.zyn.app.service.impl.admin.mensuel.RapportEvenementMensuelAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.mensuel.RapportEvenementMensuelRestAdmin;
import ma.zyn.app.ws.converter.mensuel.RapportEvenementMensuelConverter;
import ma.zyn.app.ws.dto.mensuel.RapportEvenementMensuelDto;
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
public class RapportEvenementMensuelRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private RapportEvenementMensuelAdminServiceImpl service;
    @Mock
    private RapportEvenementMensuelConverter converter;

    @InjectMocks
    private RapportEvenementMensuelRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllRapportEvenementMensuelTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<RapportEvenementMensuelDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<RapportEvenementMensuelDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveRapportEvenementMensuelTest() throws Exception {
        // Mock data
        RapportEvenementMensuelDto requestDto = new RapportEvenementMensuelDto();
        RapportEvenementMensuel entity = new RapportEvenementMensuel();
        RapportEvenementMensuel saved = new RapportEvenementMensuel();
        RapportEvenementMensuelDto savedDto = new RapportEvenementMensuelDto();

        // Mock the converter to return the rapportEvenementMensuel object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved rapportEvenementMensuel DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<RapportEvenementMensuelDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        RapportEvenementMensuelDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved rapportEvenementMensuel DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getDateEmission(), responseBody.getDateEmission());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
        assertEquals(savedDto.getMois(), responseBody.getMois());
        assertEquals(savedDto.getAnnee(), responseBody.getAnnee());
        assertEquals(savedDto.getNombreMouvement(), responseBody.getNombreMouvement());
        assertEquals(savedDto.getTotal(), responseBody.getTotal());
        assertEquals(savedDto.getIndicateurGlobal(), responseBody.getIndicateurGlobal());
        assertEquals(savedDto.getNombreMouvementCumul(), responseBody.getNombreMouvementCumul());
        assertEquals(savedDto.getTotalCumul(), responseBody.getTotalCumul());
        assertEquals(savedDto.getIndicateurGlobalCumul(), responseBody.getIndicateurGlobalCumul());
    }

}
