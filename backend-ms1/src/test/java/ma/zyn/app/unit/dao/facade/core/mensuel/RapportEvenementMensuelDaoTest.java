package ma.zyn.app.unit.dao.facade.core.mensuel;

import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuel;
import ma.zyn.app.dao.facade.core.mensuel.RapportEvenementMensuelDao;

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
public class RapportEvenementMensuelDaoTest {

@Autowired
    private RapportEvenementMensuelDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        RapportEvenementMensuel entity = new RapportEvenementMensuel();
        entity.setCode(code);
        underTest.save(entity);
        RapportEvenementMensuel loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        RapportEvenementMensuel loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        RapportEvenementMensuel entity = new RapportEvenementMensuel();
        entity.setId(id);
        underTest.save(entity);
        RapportEvenementMensuel loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        RapportEvenementMensuel entity = new RapportEvenementMensuel();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        RapportEvenementMensuel loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<RapportEvenementMensuel> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<RapportEvenementMensuel> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        RapportEvenementMensuel given = constructSample(1);
        RapportEvenementMensuel saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private RapportEvenementMensuel constructSample(int i) {
		RapportEvenementMensuel given = new RapportEvenementMensuel();
        given.setCode("code-"+i);
        given.setDateEmission(LocalDateTime.now());
        given.setDescription("description-"+i);
        given.setMois(i);
        given.setAnnee(i);
        given.setNombreMouvement(i);
        given.setTotal(BigDecimal.TEN);
        given.setIndicateurGlobal(BigDecimal.TEN);
        given.setNombreMouvementCumul(i);
        given.setTotalCumul(BigDecimal.TEN);
        given.setIndicateurGlobalCumul(BigDecimal.TEN);
        return given;
    }

}
