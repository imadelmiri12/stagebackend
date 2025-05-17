package ma.zyn.app.unit.dao.facade.core.mensuel;

import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelSecteurDetail;
import ma.zyn.app.dao.facade.core.mensuel.RapportEvenementMensuelSecteurDetailDao;

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

import ma.zyn.app.bean.core.commun.TypeEvenement ;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelSecteur ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class RapportEvenementMensuelSecteurDetailDaoTest {

@Autowired
    private RapportEvenementMensuelSecteurDetailDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        RapportEvenementMensuelSecteurDetail entity = new RapportEvenementMensuelSecteurDetail();
        entity.setId(id);
        underTest.save(entity);
        RapportEvenementMensuelSecteurDetail loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        RapportEvenementMensuelSecteurDetail entity = new RapportEvenementMensuelSecteurDetail();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        RapportEvenementMensuelSecteurDetail loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<RapportEvenementMensuelSecteurDetail> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<RapportEvenementMensuelSecteurDetail> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        RapportEvenementMensuelSecteurDetail given = constructSample(1);
        RapportEvenementMensuelSecteurDetail saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private RapportEvenementMensuelSecteurDetail constructSample(int i) {
		RapportEvenementMensuelSecteurDetail given = new RapportEvenementMensuelSecteurDetail();
        given.setRapportEvenementMensuelSecteur(new RapportEvenementMensuelSecteur(1L));
        given.setTotal(BigDecimal.TEN);
        given.setIndicateurGlobal(BigDecimal.TEN);
        given.setTypeEvenement(new TypeEvenement(1L));
        return given;
    }

}
