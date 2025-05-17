package ma.zyn.app.dao.facade.core.commun;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.commun.Secteur;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.commun.Secteur;
import java.util.List;


@Repository
public interface SecteurDao extends AbstractRepository<Secteur,Long>  {
    Secteur findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Secteur(item.id,item.label) FROM Secteur item")
    List<Secteur> findAllOptimized();

}
