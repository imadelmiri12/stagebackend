package ma.zyn.app.unit.ws.facade.admin.journal;

import ma.zyn.app.bean.core.journal.JournalAmelioaration;
import ma.zyn.app.service.impl.admin.journal.JournalAmelioarationAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.journal.JournalAmelioarationRestAdmin;
import ma.zyn.app.ws.converter.journal.JournalAmelioarationConverter;
import ma.zyn.app.ws.dto.journal.JournalAmelioarationDto;
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
public class JournalAmelioarationRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private JournalAmelioarationAdminServiceImpl service;
    @Mock
    private JournalAmelioarationConverter converter;

    @InjectMocks
    private JournalAmelioarationRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllJournalAmelioarationTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<JournalAmelioarationDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<JournalAmelioarationDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveJournalAmelioarationTest() throws Exception {
        // Mock data
        JournalAmelioarationDto requestDto = new JournalAmelioarationDto();
        JournalAmelioaration entity = new JournalAmelioaration();
        JournalAmelioaration saved = new JournalAmelioaration();
        JournalAmelioarationDto savedDto = new JournalAmelioarationDto();

        // Mock the converter to return the journalAmelioaration object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved journalAmelioaration DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<JournalAmelioarationDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        JournalAmelioarationDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved journalAmelioaration DTO
        assertEquals(savedDto.getDate(), responseBody.getDate());
        assertEquals(savedDto.getAnnee(), responseBody.getAnnee());
        assertEquals(savedDto.getDateButoir(), responseBody.getDateButoir());
        assertEquals(savedDto.getDateRealisation(), responseBody.getDateRealisation());
        assertEquals(savedDto.getDateEvaluation(), responseBody.getDateEvaluation());
        assertEquals(savedDto.getDateCloture(), responseBody.getDateCloture());
        assertEquals(savedDto.getAvancement(), responseBody.getAvancement());
        assertEquals(savedDto.getDescriptionEcrat(), responseBody.getDescriptionEcrat());
        assertEquals(savedDto.getDescriptionAction(), responseBody.getDescriptionAction());
        assertEquals(savedDto.getCommentaire(), responseBody.getCommentaire());
        assertEquals(savedDto.getCauseSuppose(), responseBody.getCauseSuppose());
    }

}
