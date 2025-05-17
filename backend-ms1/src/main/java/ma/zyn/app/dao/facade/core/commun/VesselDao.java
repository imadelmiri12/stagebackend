package ma.zyn.app.dao.facade.core.commun;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.commun.Vessel;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.commun.Vessel;
import java.util.List;


@Repository
public interface VesselDao extends AbstractRepository<Vessel,Long>  {
    Vessel findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Vessel(item.id,item.label) FROM Vessel item")
    List<Vessel> findAllOptimized();

}
