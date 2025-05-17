package ma.zyn.app.unit.service.impl.admin.mensuel;

import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelTypeEvenement;
import ma.zyn.app.dao.facade.core.mensuel.RapportEvenementMensuelTypeEvenementDao;
import ma.zyn.app.service.impl.admin.mensuel.RapportEvenementMensuelTypeEvenementAdminServiceImpl;

import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuel ;
import ma.zyn.app.bean.core.commun.TypeEvenement ;
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
class RapportEvenementMensuelTypeEvenementAdminServiceImplTest {

    @Mock
    private RapportEvenementMensuelTypeEvenementDao repository;
    private AutoCloseable autoCloseable;
    private RapportEvenementMensuelTypeEvenementAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new RapportEvenementMensuelTypeEvenementAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllRapportEvenementMensuelTypeEvenement() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveRapportEvenementMensuelTypeEvenement() {
        // Given
        RapportEvenementMensuelTypeEvenement toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteRapportEvenementMensuelTypeEvenement() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetRapportEvenementMensuelTypeEvenementById() {
        // Given
        Long idToRetrieve = 1L; // Example RapportEvenementMensuelTypeEvenement ID to retrieve
        RapportEvenementMensuelTypeEvenement expected = new RapportEvenementMensuelTypeEvenement(); // You need to replace RapportEvenementMensuelTypeEvenement with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        RapportEvenementMensuelTypeEvenement result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private RapportEvenementMensuelTypeEvenement constructSample(int i) {
		RapportEvenementMensuelTypeEvenement given = new RapportEvenementMensuelTypeEvenement();
        given.setRapportEvenementMensuel(new RapportEvenementMensuel(1L));
        given.setTypeEvenement(new TypeEvenement(1L));
        given.setTotal(BigDecimal.TEN);
        given.setTotalCumul(BigDecimal.TEN);
        given.setIndicateurGlobalCumul(BigDecimal.TEN);
        return given;
    }

}
