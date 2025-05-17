package ma.zyn.app.unit.service.impl.collaborator.journal;

import ma.zyn.app.bean.core.journal.ResultatEvaluation;
import ma.zyn.app.dao.facade.core.journal.ResultatEvaluationDao;
import ma.zyn.app.service.impl.collaborator.journal.ResultatEvaluationCollaboratorServiceImpl;

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
class ResultatEvaluationCollaboratorServiceImplTest {

    @Mock
    private ResultatEvaluationDao repository;
    private AutoCloseable autoCloseable;
    private ResultatEvaluationCollaboratorServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ResultatEvaluationCollaboratorServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllResultatEvaluation() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveResultatEvaluation() {
        // Given
        ResultatEvaluation toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteResultatEvaluation() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetResultatEvaluationById() {
        // Given
        Long idToRetrieve = 1L; // Example ResultatEvaluation ID to retrieve
        ResultatEvaluation expected = new ResultatEvaluation(); // You need to replace ResultatEvaluation with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        ResultatEvaluation result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private ResultatEvaluation constructSample(int i) {
		ResultatEvaluation given = new ResultatEvaluation();
        given.setCode("code-"+i);
        given.setLabel("label-"+i);
        given.setStyle("style-"+i);
        return given;
    }

}
