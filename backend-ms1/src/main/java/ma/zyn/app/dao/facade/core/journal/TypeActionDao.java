package ma.zyn.app.dao.facade.core.journal;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.journal.TypeAction;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.journal.TypeAction;
import java.util.List;


@Repository
public interface TypeActionDao extends AbstractRepository<TypeAction,Long>  {
    TypeAction findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW TypeAction(item.id,item.label) FROM TypeAction item")
    List<TypeAction> findAllOptimized();

}
