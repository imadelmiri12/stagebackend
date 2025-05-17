package ma.zyn.app.dao.facade.core.journal;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.journal.OrigineEcrate;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.journal.OrigineEcrate;
import java.util.List;


@Repository
public interface OrigineEcrateDao extends AbstractRepository<OrigineEcrate,Long>  {
    OrigineEcrate findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW OrigineEcrate(item.id,item.label) FROM OrigineEcrate item")
    List<OrigineEcrate> findAllOptimized();

}
