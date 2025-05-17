package ma.zyn.app.unit.dao.facade.core.commun;

import ma.zyn.app.bean.core.commun.TypeRapportEvenement;
import ma.zyn.app.dao.facade.core.commun.TypeRapportEvenementDao;

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
public class TypeRapportEvenementDaoTest {

@Autowired
    private TypeRapportEvenementDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        TypeRapportEvenement entity = new TypeRapportEvenement();
        entity.setCode(code);
        underTest.save(entity);
        TypeRapportEvenement loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        TypeRapportEvenement loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        TypeRapportEvenement entity = new TypeRapportEvenement();
        entity.setId(id);
        underTest.save(entity);
        TypeRapportEvenement loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        TypeRapportEvenement entity = new TypeRapportEvenement();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        TypeRapportEvenement loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<TypeRapportEvenement> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<TypeRapportEvenement> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        TypeRapportEvenement given = constructSample(1);
        TypeRapportEvenement saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private TypeRapportEvenement constructSample(int i) {
		TypeRapportEvenement given = new TypeRapportEvenement();
        given.setIndexation(i);
        given.setCode("code-"+i);
        given.setLabel("label-"+i);
        given.setStyle("style-"+i);
        given.setColor("color-"+i);
        return given;
    }

}
