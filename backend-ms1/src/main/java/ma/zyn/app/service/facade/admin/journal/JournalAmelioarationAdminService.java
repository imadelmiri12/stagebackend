package ma.zyn.app.service.facade.admin.journal;

import java.util.List;
import ma.zyn.app.bean.core.journal.JournalAmelioaration;
import ma.zyn.app.dao.criteria.core.journal.JournalAmelioarationCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface JournalAmelioarationAdminService {



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




	JournalAmelioaration create(JournalAmelioaration t);

    JournalAmelioaration update(JournalAmelioaration t);

    List<JournalAmelioaration> update(List<JournalAmelioaration> ts,boolean createIfNotExist);

    JournalAmelioaration findById(Long id);

    JournalAmelioaration findOrSave(JournalAmelioaration t);

    JournalAmelioaration findByReferenceEntity(JournalAmelioaration t);

    JournalAmelioaration findWithAssociatedLists(Long id);

    List<JournalAmelioaration> findAllOptimized();

    List<JournalAmelioaration> findAll();

    List<JournalAmelioaration> findByCriteria(JournalAmelioarationCriteria criteria);

    List<JournalAmelioaration> findPaginatedByCriteria(JournalAmelioarationCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(JournalAmelioarationCriteria criteria);

    List<JournalAmelioaration> delete(List<JournalAmelioaration> ts);

    boolean deleteById(Long id);

    List<List<JournalAmelioaration>> getToBeSavedAndToBeDeleted(List<JournalAmelioaration> oldList, List<JournalAmelioaration> newList);

}
