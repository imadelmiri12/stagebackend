package ma.zyn.app.unit.dao.facade.core.commun;

import ma.zyn.app.bean.core.commun.Vessel;
import ma.zyn.app.dao.facade.core.commun.VesselDao;

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
public class VesselDaoTest {

@Autowired
    private VesselDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        Vessel entity = new Vessel();
        entity.setCode(code);
        underTest.save(entity);
        Vessel loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        Vessel loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Vessel entity = new Vessel();
        entity.setId(id);
        underTest.save(entity);
        Vessel loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Vessel entity = new Vessel();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Vessel loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Vessel> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Vessel> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Vessel given = constructSample(1);
        Vessel saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
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
