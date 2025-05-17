package ma.zyn.app.unit.service.impl.collaborator.indicateur;

import ma.zyn.app.bean.core.indicateur.Trimestre;
import ma.zyn.app.dao.facade.core.indicateur.TrimestreDao;
import ma.zyn.app.service.impl.collaborator.indicateur.TrimestreCollaboratorServiceImpl;

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
class TrimestreCollaboratorServiceImplTest {

    @Mock
    private TrimestreDao repository;
    private AutoCloseable autoCloseable;
    private TrimestreCollaboratorServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new TrimestreCollaboratorServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllTrimestre() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveTrimestre() {
        // Given
        Trimestre toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteTrimestre() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetTrimestreById() {
        // Given
        Long idToRetrieve = 1L; // Example Trimestre ID to retrieve
        Trimestre expected = new Trimestre(); // You need to replace Trimestre with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Trimestre result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Trimestre constructSample(int i) {
		Trimestre given = new Trimestre();
        given.setCode("code-"+i);
        given.setLabel("label-"+i);
        given.setStyle("style-"+i);
        return given;
    }

}
