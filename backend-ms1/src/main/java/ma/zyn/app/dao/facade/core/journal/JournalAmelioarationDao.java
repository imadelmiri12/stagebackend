package ma.zyn.app.dao.facade.core.journal;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.journal.JournalAmelioaration;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface JournalAmelioarationDao extends AbstractRepository<JournalAmelioaration,Long>  {

    List<JournalAmelioaration> findBySystemeManagementCode(String code);
    List<JournalAmelioaration> findBySystemeManagementId(Long id);
    int deleteBySystemeManagementId(Long id);
    int deleteBySystemeManagementCode(String code);
    long countBySystemeManagementCode(String code);
    List<JournalAmelioaration> findByCategorieEcrateCode(String code);
    List<JournalAmelioaration> findByCategorieEcrateId(Long id);
    int deleteByCategorieEcrateId(Long id);
    int deleteByCategorieEcrateCode(String code);
    long countByCategorieEcrateCode(String code);
    List<JournalAmelioaration> findByOrigineEcrateCode(String code);
    List<JournalAmelioaration> findByOrigineEcrateId(Long id);
    int deleteByOrigineEcrateId(Long id);
    int deleteByOrigineEcrateCode(String code);
    long countByOrigineEcrateCode(String code);
    List<JournalAmelioaration> findByTypeActionCode(String code);
    List<JournalAmelioaration> findByTypeActionId(Long id);
    int deleteByTypeActionId(Long id);
    int deleteByTypeActionCode(String code);
    long countByTypeActionCode(String code);
    List<JournalAmelioaration> findByCollaboratorId(Long id);
    int deleteByCollaboratorId(Long id);
    long countByCollaboratorEmail(String email);
    List<JournalAmelioaration> findByCritereEvaluationCode(String code);
    List<JournalAmelioaration> findByCritereEvaluationId(Long id);
    int deleteByCritereEvaluationId(Long id);
    int deleteByCritereEvaluationCode(String code);
    long countByCritereEvaluationCode(String code);
    List<JournalAmelioaration> findByResultatEvaluationCode(String code);
    List<JournalAmelioaration> findByResultatEvaluationId(Long id);
    int deleteByResultatEvaluationId(Long id);
    int deleteByResultatEvaluationCode(String code);
    long countByResultatEvaluationCode(String code);


}
