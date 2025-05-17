package ma.zyn.app.dao.facade.core.commun;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.commun.TypeCollaborator;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.commun.TypeCollaborator;
import java.util.List;


@Repository
public interface TypeCollaboratorDao extends AbstractRepository<TypeCollaborator,Long>  {
    TypeCollaborator findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW TypeCollaborator(item.id,item.label) FROM TypeCollaborator item")
    List<TypeCollaborator> findAllOptimized();

}
