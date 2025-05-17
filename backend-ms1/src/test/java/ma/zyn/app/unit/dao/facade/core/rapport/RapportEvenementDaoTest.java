package ma.zyn.app.unit.dao.facade.core.rapport;

import ma.zyn.app.bean.core.rapport.RapportEvenement;
import ma.zyn.app.dao.facade.core.rapport.RapportEvenementDao;

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

import ma.zyn.app.bean.core.actor.Collaborator ;
import ma.zyn.app.bean.core.commun.TypeRapportEvenement ;
import ma.zyn.app.bean.core.commun.Vessel ;
import ma.zyn.app.bean.core.commun.Port ;
import ma.zyn.app.bean.core.commun.Secteur ;
import ma.zyn.app.bean.core.commun.TypeEvenement ;
import ma.zyn.app.bean.core.journal.JournalAmelioaration ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class RapportEvenementDaoTest {

@Autowired
    private RapportEvenementDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        RapportEvenement entity = new RapportEvenement();
        entity.setCode(code);
        underTest.save(entity);
        RapportEvenement loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        RapportEvenement loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        RapportEvenement entity = new RapportEvenement();
        entity.setId(id);
        underTest.save(entity);
        RapportEvenement loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        RapportEvenement entity = new RapportEvenement();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        RapportEvenement loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<RapportEvenement> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<RapportEvenement> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        RapportEvenement given = constructSample(1);
        RapportEvenement saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private RapportEvenement constructSample(int i) {
		RapportEvenement given = new RapportEvenement();
        given.setCode("code-"+i);
        given.setDateEvenement(LocalDateTime.now());
        given.setDateSoumission(LocalDateTime.now());
        given.setAttachments("attachments-"+i);
        given.setDescription("description-"+i);
        given.setRecommendation("recommendation-"+i);
        given.setDirectivePmjChef("directivePmjChef-"+i);
        given.setPort(new Port(1L));
        given.setMois(i);
        given.setAnnee(i);
        given.setWindDirection(BigDecimal.TEN);
        given.setWindForce(BigDecimal.TEN);
        given.setCurrentDirection(BigDecimal.TEN);
        given.setCurrentForce(BigDecimal.TEN);
        given.setSwellHeigth(BigDecimal.TEN);
        given.setSwellDirection(BigDecimal.TEN);
        given.setTypeEvenement(new TypeEvenement(1L));
        given.setCollaborator(new Collaborator(1L));
        given.setVessel(new Vessel(1L));
        given.setFonde(false);
        given.setTypeRapportEvenement(new TypeRapportEvenement(1L));
        given.setSecteur(new Secteur(1L));
        given.setJournalAmelioaration(new JournalAmelioaration(1L));
        return given;
    }

}
