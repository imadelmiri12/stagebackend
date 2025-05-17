package ma.zyn.app.dao.facade.core.mensuel;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelSecteurDetail;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface RapportEvenementMensuelSecteurDetailDao extends AbstractRepository<RapportEvenementMensuelSecteurDetail,Long>  {

    List<RapportEvenementMensuelSecteurDetail> findByRapportEvenementMensuelSecteurId(Long id);
    int deleteByRapportEvenementMensuelSecteurId(Long id);
    long countByRapportEvenementMensuelSecteurId(Long id);
    List<RapportEvenementMensuelSecteurDetail> findByTypeEvenementCode(String code);
    List<RapportEvenementMensuelSecteurDetail> findByTypeEvenementId(Long id);
    int deleteByTypeEvenementId(Long id);
    int deleteByTypeEvenementCode(String code);
    long countByTypeEvenementCode(String code);


}
