package ma.zyn.app.unit.service.impl.collaborator.journal;

import ma.zyn.app.bean.core.journal.SystemeManagement;
import ma.zyn.app.dao.facade.core.journal.SystemeManagementDao;
import ma.zyn.app.service.impl.collaborator.journal.SystemeManagementCollaboratorServiceImpl;

import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;



import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class SystemeManagementCollaboratorServiceImplTest {

    @Mock
    private SystemeManagementDao repository;
    private AutoCloseable autoCloseable;
    private SystemeManagementCollaboratorServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new SystemeManagementCollaboratorServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllSystemeManagement() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveSystemeManagement() {
        // Given
        SystemeManagement toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteSystemeManagement() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetSystemeManagementById() {
        // Given
        Long idToRetrieve = 1L; // Example SystemeManagement ID to retrieve
        SystemeManagement expected = new SystemeManagement(); // You need to replace SystemeManagement with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        SystemeManagement result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private SystemeManagement constructSample(int i) {
		SystemeManagement given = new SystemeManagement();
        given.setCode("code-"+i);
        given.setLabel("label-"+i);
        given.setStyle("style-"+i);
        return given;
    }

}
