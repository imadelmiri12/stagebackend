package ma.zyn.app.dao.facade.core.commun;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.commun.TypeEvenement;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.commun.TypeEvenement;
import java.util.List;


@Repository
public interface TypeEvenementDao extends AbstractRepository<TypeEvenement,Long>  {
    TypeEvenement findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW TypeEvenement(item.id,item.label) FROM TypeEvenement item")
    List<TypeEvenement> findAllOptimized();

}
