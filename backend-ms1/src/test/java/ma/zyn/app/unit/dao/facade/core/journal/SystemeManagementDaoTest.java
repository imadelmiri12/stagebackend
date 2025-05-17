package ma.zyn.app.unit.dao.facade.core.journal;

import ma.zyn.app.bean.core.journal.SystemeManagement;
import ma.zyn.app.dao.facade.core.journal.SystemeManagementDao;

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
public class SystemeManagementDaoTest {

@Autowired
    private SystemeManagementDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        SystemeManagement entity = new SystemeManagement();
        entity.setCode(code);
        underTest.save(entity);
        SystemeManagement loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        SystemeManagement loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        SystemeManagement entity = new SystemeManagement();
        entity.setId(id);
        underTest.save(entity);
        SystemeManagement loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        SystemeManagement entity = new SystemeManagement();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        SystemeManagement loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<SystemeManagement> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<SystemeManagement> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        SystemeManagement given = constructSample(1);
        SystemeManagement saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private SystemeManagement constructSample(int i) {
		SystemeManagement given = new SystemeManagement();
        given.setCode("code-"+i);
        given.setLabel("label-"+i);
        given.setStyle("style-"+i);
        return given;
    }

}
