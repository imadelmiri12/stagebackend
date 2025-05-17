package ma.zyn.app.dao.facade.core.commun;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.commun.TypeRapportEvenement;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.commun.TypeRapportEvenement;
import java.util.List;


@Repository
public interface TypeRapportEvenementDao extends AbstractRepository<TypeRapportEvenement,Long>  {
    TypeRapportEvenement findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW TypeRapportEvenement(item.id,item.label) FROM TypeRapportEvenement item")
    List<TypeRapportEvenement> findAllOptimized();

}
