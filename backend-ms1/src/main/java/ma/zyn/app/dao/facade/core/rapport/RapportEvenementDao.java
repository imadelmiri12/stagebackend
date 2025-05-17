package ma.zyn.app.dao.facade.core.rapport;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.rapport.RapportEvenement;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.rapport.RapportEvenement;
import java.util.List;


@Repository
public interface RapportEvenementDao extends AbstractRepository<RapportEvenement,Long>  {
    RapportEvenement findByCode(String code);
    int deleteByCode(String code);

    List<RapportEvenement> findByPortCode(String code);
    List<RapportEvenement> findByPortId(Long id);
    int deleteByPortId(Long id);
    int deleteByPortCode(String code);
    long countByPortCode(String code);
    List<RapportEvenement> findByTypeEvenementCode(String code);
    List<RapportEvenement> findByTypeEvenementId(Long id);
    int deleteByTypeEvenementId(Long id);
    int deleteByTypeEvenementCode(String code);
    long countByTypeEvenementCode(String code);
    List<RapportEvenement> findByCollaboratorId(Long id);
    int deleteByCollaboratorId(Long id);
    long countByCollaboratorEmail(String email);
    List<RapportEvenement> findByVesselCode(String code);
    List<RapportEvenement> findByVesselId(Long id);
    int deleteByVesselId(Long id);
    int deleteByVesselCode(String code);
    long countByVesselCode(String code);
    List<RapportEvenement> findByTypeRapportEvenementCode(String code);
    List<RapportEvenement> findByTypeRapportEvenementId(Long id);
    int deleteByTypeRapportEvenementId(Long id);
    int deleteByTypeRapportEvenementCode(String code);
    long countByTypeRapportEvenementCode(String code);
    List<RapportEvenement> findBySecteurCode(String code);
    List<RapportEvenement> findBySecteurId(Long id);
    int deleteBySecteurId(Long id);
    int deleteBySecteurCode(String code);
    long countBySecteurCode(String code);
    List<RapportEvenement> findByJournalAmelioarationId(Long id);
    int deleteByJournalAmelioarationId(Long id);
    long countByJournalAmelioarationId(Long id);

    @Query("SELECT NEW RapportEvenement(item.id,item.code) FROM RapportEvenement item")
    List<RapportEvenement> findAllOptimized();

}
