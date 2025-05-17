package ma.zyn.app.dao.facade.core.journal;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.journal.CategorieEcrate;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.journal.CategorieEcrate;
import java.util.List;


@Repository
public interface CategorieEcrateDao extends AbstractRepository<CategorieEcrate,Long>  {
    CategorieEcrate findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW CategorieEcrate(item.id,item.label) FROM CategorieEcrate item")
    List<CategorieEcrate> findAllOptimized();

}
