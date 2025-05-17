package ma.zyn.app.unit.service.impl.admin.journal;

import ma.zyn.app.bean.core.journal.OrigineEcrate;
import ma.zyn.app.dao.facade.core.journal.OrigineEcrateDao;
import ma.zyn.app.service.impl.admin.journal.OrigineEcrateAdminServiceImpl;

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
class OrigineEcrateAdminServiceImplTest {

    @Mock
    private OrigineEcrateDao repository;
    private AutoCloseable autoCloseable;
    private OrigineEcrateAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new OrigineEcrateAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllOrigineEcrate() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveOrigineEcrate() {
        // Given
        OrigineEcrate toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteOrigineEcrate() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetOrigineEcrateById() {
        // Given
        Long idToRetrieve = 1L; // Example OrigineEcrate ID to retrieve
        OrigineEcrate expected = new OrigineEcrate(); // You need to replace OrigineEcrate with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        OrigineEcrate result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private OrigineEcrate constructSample(int i) {
		OrigineEcrate given = new OrigineEcrate();
        given.setCode("code-"+i);
        given.setLabel("label-"+i);
        given.setStyle("style-"+i);
        return given;
    }

}
