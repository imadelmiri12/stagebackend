package ma.zyn.app.unit.dao.facade.core.mensuel;

import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelSecteur;
import ma.zyn.app.dao.facade.core.mensuel.RapportEvenementMensuelSecteurDao;

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

import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuel ;
import ma.zyn.app.bean.core.commun.Secteur ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class RapportEvenementMensuelSecteurDaoTest {

@Autowired
    private RapportEvenementMensuelSecteurDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        RapportEvenementMensuelSecteur entity = new RapportEvenementMensuelSecteur();
        entity.setId(id);
        underTest.save(entity);
        RapportEvenementMensuelSecteur loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        RapportEvenementMensuelSecteur entity = new RapportEvenementMensuelSecteur();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        RapportEvenementMensuelSecteur loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<RapportEvenementMensuelSecteur> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<RapportEvenementMensuelSecteur> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        RapportEvenementMensuelSecteur given = constructSample(1);
        RapportEvenementMensuelSecteur saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private RapportEvenementMensuelSecteur constructSample(int i) {
		RapportEvenementMensuelSecteur given = new RapportEvenementMensuelSecteur();
        given.setRapportEvenementMensuel(new RapportEvenementMensuel(1L));
        given.setSecteur(new Secteur(1L));
        given.setTotal(BigDecimal.TEN);
        given.setIndicateurGlobal(BigDecimal.TEN);
        given.setTotalCumul(BigDecimal.TEN);
        given.setIndicateurGlobalCumul(BigDecimal.TEN);
        return given;
    }

}
