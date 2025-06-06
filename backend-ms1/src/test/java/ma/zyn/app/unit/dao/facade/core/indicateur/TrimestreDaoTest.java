package ma.zyn.app.unit.dao.facade.core.indicateur;

import ma.zyn.app.bean.core.indicateur.Trimestre;
import ma.zyn.app.dao.facade.core.indicateur.TrimestreDao;

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
public class TrimestreDaoTest {

@Autowired
    private TrimestreDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        Trimestre entity = new Trimestre();
        entity.setCode(code);
        underTest.save(entity);
        Trimestre loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        Trimestre loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Trimestre entity = new Trimestre();
        entity.setId(id);
        underTest.save(entity);
        Trimestre loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Trimestre entity = new Trimestre();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Trimestre loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Trimestre> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Trimestre> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Trimestre given = constructSample(1);
        Trimestre saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Trimestre constructSample(int i) {
		Trimestre given = new Trimestre();
        given.setCode("code-"+i);
        given.setLabel("label-"+i);
        given.setStyle("style-"+i);
        return given;
    }

}
