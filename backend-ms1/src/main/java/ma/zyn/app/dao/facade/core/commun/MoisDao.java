package ma.zyn.app.dao.facade.core.commun;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.commun.Mois;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.commun.Mois;
import java.util.List;


@Repository
public interface MoisDao extends AbstractRepository<Mois,Long>  {
    Mois findByCode(String code);
    int deleteByCode(String code);

    List<Mois> findByTrimestreCode(String code);
    List<Mois> findByTrimestreId(Long id);
    int deleteByTrimestreId(Long id);
    int deleteByTrimestreCode(String code);
    long countByTrimestreCode(String code);

    @Query("SELECT NEW Mois(item.id,item.label) FROM Mois item")
    List<Mois> findAllOptimized();

}
