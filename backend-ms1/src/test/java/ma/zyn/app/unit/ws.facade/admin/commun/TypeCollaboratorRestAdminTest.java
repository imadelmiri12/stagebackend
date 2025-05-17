package ma.zyn.app.unit.ws.facade.admin.commun;

import ma.zyn.app.bean.core.commun.TypeCollaborator;
import ma.zyn.app.service.impl.admin.commun.TypeCollaboratorAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.commun.TypeCollaboratorRestAdmin;
import ma.zyn.app.ws.converter.commun.TypeCollaboratorConverter;
import ma.zyn.app.ws.dto.commun.TypeCollaboratorDto;
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
public class TypeCollaboratorRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private TypeCollaboratorAdminServiceImpl service;
    @Mock
    private TypeCollaboratorConverter converter;

    @InjectMocks
    private TypeCollaboratorRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllTypeCollaboratorTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<TypeCollaboratorDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<TypeCollaboratorDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveTypeCollaboratorTest() throws Exception {
        // Mock data
        TypeCollaboratorDto requestDto = new TypeCollaboratorDto();
        TypeCollaborator entity = new TypeCollaborator();
        TypeCollaborator saved = new TypeCollaborator();
        TypeCollaboratorDto savedDto = new TypeCollaboratorDto();

        // Mock the converter to return the typeCollaborator object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved typeCollaborator DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<TypeCollaboratorDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        TypeCollaboratorDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved typeCollaborator DTO
        assertEquals(savedDto.getIndexation(), responseBody.getIndexation());
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLabel(), responseBody.getLabel());
        assertEquals(savedDto.getStyle(), responseBody.getStyle());
        assertEquals(savedDto.getColor(), responseBody.getColor());
    }

}
