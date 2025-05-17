package ma.zyn.app.service.facade.collaborator.journal;

import java.util.List;
import ma.zyn.app.bean.core.journal.SystemeManagement;
import ma.zyn.app.dao.criteria.core.journal.SystemeManagementCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface SystemeManagementCollaboratorService {







	SystemeManagement create(SystemeManagement t);

    SystemeManagement update(SystemeManagement t);

    List<SystemeManagement> update(List<SystemeManagement> ts,boolean createIfNotExist);

    SystemeManagement findById(Long id);

    SystemeManagement findOrSave(SystemeManagement t);

    SystemeManagement findByReferenceEntity(SystemeManagement t);

    SystemeManagement findWithAssociatedLists(Long id);

    List<SystemeManagement> findAllOptimized();

    List<SystemeManagement> findAll();

    List<SystemeManagement> findByCriteria(SystemeManagementCriteria criteria);

    List<SystemeManagement> findPaginatedByCriteria(SystemeManagementCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(SystemeManagementCriteria criteria);

    List<SystemeManagement> delete(List<SystemeManagement> ts);

    boolean deleteById(Long id);

    List<List<SystemeManagement>> getToBeSavedAndToBeDeleted(List<SystemeManagement> oldList, List<SystemeManagement> newList);

}
