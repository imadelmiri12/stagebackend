package ma.zyn.app.dao.facade.core.mensuel;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelTypeEvenement;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface RapportEvenementMensuelTypeEvenementDao extends AbstractRepository<RapportEvenementMensuelTypeEvenement,Long>  {

    List<RapportEvenementMensuelTypeEvenement> findByRapportEvenementMensuelId(Long id);
    int deleteByRapportEvenementMensuelId(Long id);
    long countByRapportEvenementMensuelCode(String code);
    List<RapportEvenementMensuelTypeEvenement> findByTypeEvenementCode(String code);
    List<RapportEvenementMensuelTypeEvenement> findByTypeEvenementId(Long id);
    int deleteByTypeEvenementId(Long id);
    int deleteByTypeEvenementCode(String code);
    long countByTypeEvenementCode(String code);


    RapportEvenementMensuelTypeEvenement findByRapportEvenementMensuelIdAndTypeEvenementId(Long idRapportEvenementMensuel, Long idTypeEvenement);
}
