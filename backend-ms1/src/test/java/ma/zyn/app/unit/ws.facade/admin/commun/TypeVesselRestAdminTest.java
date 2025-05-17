package ma.zyn.app.unit.ws.facade.admin.commun;

import ma.zyn.app.bean.core.commun.TypeVessel;
import ma.zyn.app.service.impl.admin.commun.TypeVesselAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.commun.TypeVesselRestAdmin;
import ma.zyn.app.ws.converter.commun.TypeVesselConverter;
import ma.zyn.app.ws.dto.commun.TypeVesselDto;
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
public class TypeVesselRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private TypeVesselAdminServiceImpl service;
    @Mock
    private TypeVesselConverter converter;

    @InjectMocks
    private TypeVesselRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllTypeVesselTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<TypeVesselDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<TypeVesselDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveTypeVesselTest() throws Exception {
        // Mock data
        TypeVesselDto requestDto = new TypeVesselDto();
        TypeVessel entity = new TypeVessel();
        TypeVessel saved = new TypeVessel();
        TypeVesselDto savedDto = new TypeVesselDto();

        // Mock the converter to return the typeVessel object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved typeVessel DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<TypeVesselDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        TypeVesselDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved typeVessel DTO
        assertEquals(savedDto.getIndexation(), responseBody.getIndexation());
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLabel(), responseBody.getLabel());
        assertEquals(savedDto.getStyle(), responseBody.getStyle());
        assertEquals(savedDto.getColor(), responseBody.getColor());
    }

}
