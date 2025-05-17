package ma.zyn.app.dao.facade.core.commun;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.commun.Port;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.commun.Port;
import java.util.List;


@Repository
public interface PortDao extends AbstractRepository<Port,Long>  {
    Port findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Port(item.id,item.label) FROM Port item")
    List<Port> findAllOptimized();

}
