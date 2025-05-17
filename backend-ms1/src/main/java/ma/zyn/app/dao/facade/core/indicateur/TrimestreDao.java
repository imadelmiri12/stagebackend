package ma.zyn.app.dao.facade.core.indicateur;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.indicateur.Trimestre;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.indicateur.Trimestre;
import java.util.List;


@Repository
public interface TrimestreDao extends AbstractRepository<Trimestre,Long>  {
    Trimestre findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Trimestre(item.id,item.label) FROM Trimestre item")
    List<Trimestre> findAllOptimized();

}
