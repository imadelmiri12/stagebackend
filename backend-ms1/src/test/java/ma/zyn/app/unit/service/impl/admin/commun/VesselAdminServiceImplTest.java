package ma.zyn.app.unit.service.impl.admin.commun;

import ma.zyn.app.bean.core.commun.Vessel;
import ma.zyn.app.dao.facade.core.commun.VesselDao;
import ma.zyn.app.service.impl.admin.commun.VesselAdminServiceImpl;

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
class VesselAdminServiceImplTest {

    @Mock
    private VesselDao repository;
    private AutoCloseable autoCloseable;
    private VesselAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new VesselAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllVessel() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveVessel() {
        // Given
        Vessel toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteVessel() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetVesselById() {
        // Given
        Long idToRetrieve = 1L; // Example Vessel ID to retrieve
        Vessel expected = new Vessel(); // You need to replace Vessel with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Vessel result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Vessel constructSample(int i) {
		Vessel given = new Vessel();
        given.setCode("code-"+i);
        given.setLabel("label-"+i);
        given.setStyle("style-"+i);
        given.setLoa(BigDecimal.TEN);
        given.setDraft(BigDecimal.TEN);
        given.setAirDraft(BigDecimal.TEN);
        return given;
    }

}
