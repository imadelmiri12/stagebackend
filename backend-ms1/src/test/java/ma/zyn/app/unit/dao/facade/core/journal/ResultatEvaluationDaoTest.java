package ma.zyn.app.unit.dao.facade.core.journal;

import ma.zyn.app.bean.core.journal.ResultatEvaluation;
import ma.zyn.app.dao.facade.core.journal.ResultatEvaluationDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ResultatEvaluationDaoTest {

@Autowired
    private ResultatEvaluationDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        ResultatEvaluation entity = new ResultatEvaluation();
        entity.setCode(code);
        underTest.save(entity);
        ResultatEvaluation loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        ResultatEvaluation loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        ResultatEvaluation entity = new ResultatEvaluation();
        entity.setId(id);
        underTest.save(entity);
        ResultatEvaluation loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        ResultatEvaluation entity = new ResultatEvaluation();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        ResultatEvaluation loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<ResultatEvaluation> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<ResultatEvaluation> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        ResultatEvaluation given = constructSample(1);
        ResultatEvaluation saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private ResultatEvaluation constructSample(int i) {
		ResultatEvaluation given = new ResultatEvaluation();
        given.setCode("code-"+i);
        given.setLabel("label-"+i);
        given.setStyle("style-"+i);
        return given;
    }

}
