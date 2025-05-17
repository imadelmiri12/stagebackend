package ma.zyn.app.dao.facade.core.journal;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.journal.SystemeManagement;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.journal.SystemeManagement;
import java.util.List;


@Repository
public interface SystemeManagementDao extends AbstractRepository<SystemeManagement,Long>  {
    SystemeManagement findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW SystemeManagement(item.id,item.label) FROM SystemeManagement item")
    List<SystemeManagement> findAllOptimized();

}
