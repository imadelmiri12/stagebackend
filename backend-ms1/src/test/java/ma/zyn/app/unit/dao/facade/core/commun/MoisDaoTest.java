package ma.zyn.app.unit.dao.facade.core.commun;

import ma.zyn.app.bean.core.commun.Mois;
import ma.zyn.app.dao.facade.core.commun.MoisDao;

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

import ma.zyn.app.bean.core.indicateur.Trimestre ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class MoisDaoTest {

@Autowired
    private MoisDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        Mois entity = new Mois();
        entity.setCode(code);
        underTest.save(entity);
        Mois loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        Mois loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Mois entity = new Mois();
        entity.setId(id);
        underTest.save(entity);
        Mois loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Mois entity = new Mois();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Mois loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Mois> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Mois> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Mois given = constructSample(1);
        Mois saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
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
