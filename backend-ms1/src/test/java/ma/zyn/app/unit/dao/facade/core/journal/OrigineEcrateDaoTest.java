package ma.zyn.app.unit.dao.facade.core.journal;

import ma.zyn.app.bean.core.journal.OrigineEcrate;
import ma.zyn.app.dao.facade.core.journal.OrigineEcrateDao;

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
public class OrigineEcrateDaoTest {

@Autowired
    private OrigineEcrateDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        OrigineEcrate entity = new OrigineEcrate();
        entity.setCode(code);
        underTest.save(entity);
        OrigineEcrate loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        OrigineEcrate loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        OrigineEcrate entity = new OrigineEcrate();
        entity.setId(id);
        underTest.save(entity);
        OrigineEcrate loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        OrigineEcrate entity = new OrigineEcrate();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        OrigineEcrate loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<OrigineEcrate> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<OrigineEcrate> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        OrigineEcrate given = constructSample(1);
        OrigineEcrate saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private OrigineEcrate constructSample(int i) {
		OrigineEcrate given = new OrigineEcrate();
        given.setCode("code-"+i);
        given.setLabel("label-"+i);
        given.setStyle("style-"+i);
        return given;
    }

}
