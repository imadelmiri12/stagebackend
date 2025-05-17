package ma.zyn.app.dao.facade.core.commun;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.commun.TypeVessel;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.commun.TypeVessel;
import java.util.List;


@Repository
public interface TypeVesselDao extends AbstractRepository<TypeVessel,Long>  {
    TypeVessel findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW TypeVessel(item.id,item.label) FROM TypeVessel item")
    List<TypeVessel> findAllOptimized();

}
