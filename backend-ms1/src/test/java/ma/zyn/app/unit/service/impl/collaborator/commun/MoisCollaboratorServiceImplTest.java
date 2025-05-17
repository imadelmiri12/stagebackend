package ma.zyn.app.unit.service.impl.collaborator.commun;

import ma.zyn.app.bean.core.commun.Mois;
import ma.zyn.app.dao.facade.core.commun.MoisDao;
import ma.zyn.app.service.impl.collaborator.commun.MoisCollaboratorServiceImpl;

import ma.zyn.app.bean.core.indicateur.Trimestre ;
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
class MoisCollaboratorServiceImplTest {

    @Mock
    private MoisDao repository;
    private AutoCloseable autoCloseable;
    private MoisCollaboratorServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new MoisCollaboratorServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllMois() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveMois() {
        // Given
        Mois toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteMois() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetMoisById() {
        // Given
        Long idToRetrieve = 1L; // Example Mois ID to retrieve
        Mois expected = new Mois(); // You need to replace Mois with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Mois result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Mois constructSample(int i) {
		Mois given = new Mois();
        given.setCode("code-"+i);
        given.setLabel("label-"+i);
        given.setStyle("style-"+i);
        given.setNumero(i);
        given.setTrimestre(new Trimestre(1L));
        return given;
    }

}
