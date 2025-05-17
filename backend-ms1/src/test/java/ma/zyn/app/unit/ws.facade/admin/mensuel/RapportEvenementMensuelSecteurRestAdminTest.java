package ma.zyn.app.unit.ws.facade.admin.mensuel;

import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelSecteur;
import ma.zyn.app.service.impl.admin.mensuel.RapportEvenementMensuelSecteurAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.mensuel.RapportEvenementMensuelSecteurRestAdmin;
import ma.zyn.app.ws.converter.mensuel.RapportEvenementMensuelSecteurConverter;
import ma.zyn.app.ws.dto.mensuel.RapportEvenementMensuelSecteurDto;
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
public class RapportEvenementMensuelSecteurRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private RapportEvenementMensuelSecteurAdminServiceImpl service;
    @Mock
    private RapportEvenementMensuelSecteurConverter converter;

    @InjectMocks
    private RapportEvenementMensuelSecteurRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllRapportEvenementMensuelSecteurTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<RapportEvenementMensuelSecteurDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<RapportEvenementMensuelSecteurDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveRapportEvenementMensuelSecteurTest() throws Exception {
        // Mock data
        RapportEvenementMensuelSecteurDto requestDto = new RapportEvenementMensuelSecteurDto();
        RapportEvenementMensuelSecteur entity = new RapportEvenementMensuelSecteur();
        RapportEvenementMensuelSecteur saved = new RapportEvenementMensuelSecteur();
        RapportEvenementMensuelSecteurDto savedDto = new RapportEvenementMensuelSecteurDto();

        // Mock the converter to return the rapportEvenementMensuelSecteur object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved rapportEvenementMensuelSecteur DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<RapportEvenementMensuelSecteurDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        RapportEvenementMensuelSecteurDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved rapportEvenementMensuelSecteur DTO
        assertEquals(savedDto.getTotal(), responseBody.getTotal());
        assertEquals(savedDto.getIndicateurGlobal(), responseBody.getIndicateurGlobal());
        assertEquals(savedDto.getTotalCumul(), responseBody.getTotalCumul());
        assertEquals(savedDto.getIndicateurGlobalCumul(), responseBody.getIndicateurGlobalCumul());
    }

}
