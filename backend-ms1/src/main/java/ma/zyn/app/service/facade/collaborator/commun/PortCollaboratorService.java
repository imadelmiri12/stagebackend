package ma.zyn.app.service.facade.collaborator.commun;

import java.util.List;
import ma.zyn.app.bean.core.commun.Port;
import ma.zyn.app.dao.criteria.core.commun.PortCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface PortCollaboratorService {







	Port create(Port t);

    Port update(Port t);

    List<Port> update(List<Port> ts,boolean createIfNotExist);

    Port findById(Long id);

    Port findOrSave(Port t);

    Port findByReferenceEntity(Port t);

    Port findWithAssociatedLists(Long id);

    List<Port> findAllOptimized();

    List<Port> findAll();

    List<Port> findByCriteria(PortCriteria criteria);

    List<Port> findPaginatedByCriteria(PortCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(PortCriteria criteria);

    List<Port> delete(List<Port> ts);

    boolean deleteById(Long id);

    List<List<Port>> getToBeSavedAndToBeDeleted(List<Port> oldList, List<Port> newList);

}
