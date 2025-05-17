package ma.zyn.app.dao.facade.core.mensuel;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelSecteur;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface RapportEvenementMensuelSecteurDao extends AbstractRepository<RapportEvenementMensuelSecteur,Long>  {

    List<RapportEvenementMensuelSecteur> findByRapportEvenementMensuelId(Long id);
    int deleteByRapportEvenementMensuelId(Long id);
    long countByRapportEvenementMensuelCode(String code);
    List<RapportEvenementMensuelSecteur> findBySecteurCode(String code);
    List<RapportEvenementMensuelSecteur> findBySecteurId(Long id);
    int deleteBySecteurId(Long id);
    int deleteBySecteurCode(String code);
    long countBySecteurCode(String code);


    RapportEvenementMensuelSecteur findByRapportEvenementMensuelIdAndSecteurId(Long rapportEvenementMensuelId, Long secteurId);
}
