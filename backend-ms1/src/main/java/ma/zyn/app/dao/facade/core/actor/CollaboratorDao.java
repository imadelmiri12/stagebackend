package ma.zyn.app.dao.facade.core.actor;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.actor.Collaborator;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.actor.Collaborator;
import java.util.List;


@Repository
public interface CollaboratorDao extends AbstractRepository<Collaborator,Long>  {
    Collaborator findByEmail(String email);
    int deleteByEmail(String email);

    List<Collaborator> findByTypeCollaboratorCode(String code);
    List<Collaborator> findByTypeCollaboratorId(Long id);
    int deleteByTypeCollaboratorId(Long id);
    int deleteByTypeCollaboratorCode(String code);
    long countByTypeCollaboratorCode(String code);
    Collaborator findByUsername(String username);

    @Query("SELECT NEW Collaborator(item.id,item.email) FROM Collaborator item")
    List<Collaborator> findAllOptimized();

}
