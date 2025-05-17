package ma.zyn.app.unit.dao.facade.core.journal;

import ma.zyn.app.bean.core.journal.JournalAmelioaration;
import ma.zyn.app.dao.facade.core.journal.JournalAmelioarationDao;

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

import ma.zyn.app.bean.core.journal.TypeAction ;
import ma.zyn.app.bean.core.actor.Collaborator ;
import ma.zyn.app.bean.core.journal.ResultatEvaluation ;
import ma.zyn.app.bean.core.journal.SystemeManagement ;
import ma.zyn.app.bean.core.journal.CritereEvaluation ;
import ma.zyn.app.bean.core.journal.CategorieEcrate ;
import ma.zyn.app.bean.core.journal.OrigineEcrate ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class JournalAmelioarationDaoTest {

@Autowired
    private JournalAmelioarationDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        JournalAmelioaration entity = new JournalAmelioaration();
        entity.setId(id);
        underTest.save(entity);
        JournalAmelioaration loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        JournalAmelioaration entity = new JournalAmelioaration();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        JournalAmelioaration loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<JournalAmelioaration> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<JournalAmelioaration> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        JournalAmelioaration given = constructSample(1);
        JournalAmelioaration saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private JournalAmelioaration constructSample(int i) {
		JournalAmelioaration given = new JournalAmelioaration();
        given.setDate(LocalDateTime.now());
        given.setAnnee(i);
        given.setDateButoir(LocalDateTime.now());
        given.setDateRealisation(LocalDateTime.now());
        given.setDateEvaluation(LocalDateTime.now());
        given.setDateCloture(LocalDateTime.now());
        given.setAvancement(BigDecimal.TEN);
        given.setDescriptionEcrat("descriptionEcrat-"+i);
        given.setDescriptionAction("descriptionAction-"+i);
        given.setCommentaire("commentaire-"+i);
        given.setCauseSuppose("causeSuppose-"+i);
        given.setSystemeManagement(new SystemeManagement(1L));
        given.setCategorieEcrate(new CategorieEcrate(1L));
        given.setOrigineEcrate(new OrigineEcrate(1L));
        given.setTypeAction(new TypeAction(1L));
        given.setCollaborator(new Collaborator(1L));
        given.setCritereEvaluation(new CritereEvaluation(1L));
        given.setResultatEvaluation(new ResultatEvaluation(1L));
        return given;
    }

}
