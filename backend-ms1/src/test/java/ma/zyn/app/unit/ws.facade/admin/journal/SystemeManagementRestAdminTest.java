package ma.zyn.app.unit.ws.facade.admin.journal;

import ma.zyn.app.bean.core.journal.SystemeManagement;
import ma.zyn.app.service.impl.admin.journal.SystemeManagementAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.journal.SystemeManagementRestAdmin;
import ma.zyn.app.ws.converter.journal.SystemeManagementConverter;
import ma.zyn.app.ws.dto.journal.SystemeManagementDto;
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
public class SystemeManagementRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private SystemeManagementAdminServiceImpl service;
    @Mock
    private SystemeManagementConverter converter;

    @InjectMocks
    private SystemeManagementRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllSystemeManagementTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<SystemeManagementDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<SystemeManagementDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveSystemeManagementTest() throws Exception {
        // Mock data
        SystemeManagementDto requestDto = new SystemeManagementDto();
        SystemeManagement entity = new SystemeManagement();
        SystemeManagement saved = new SystemeManagement();
        SystemeManagementDto savedDto = new SystemeManagementDto();

        // Mock the converter to return the systemeManagement object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved systemeManagement DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<SystemeManagementDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        SystemeManagementDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved systemeManagement DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLabel(), responseBody.getLabel());
        assertEquals(savedDto.getStyle(), responseBody.getStyle());
    }

}
