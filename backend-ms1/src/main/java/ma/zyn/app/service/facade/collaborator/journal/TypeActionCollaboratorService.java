package ma.zyn.app.service.facade.collaborator.journal;

import java.util.List;
import ma.zyn.app.bean.core.journal.TypeAction;
import ma.zyn.app.dao.criteria.core.journal.TypeActionCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface TypeActionCollaboratorService {







	TypeAction create(TypeAction t);

    TypeAction update(TypeAction t);

    List<TypeAction> update(List<TypeAction> ts,boolean createIfNotExist);

    TypeAction findById(Long id);

    TypeAction findOrSave(TypeAction t);

    TypeAction findByReferenceEntity(TypeAction t);

    TypeAction findWithAssociatedLists(Long id);

    List<TypeAction> findAllOptimized();

    List<TypeAction> findAll();

    List<TypeAction> findByCriteria(TypeActionCriteria criteria);

    List<TypeAction> findPaginatedByCriteria(TypeActionCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TypeActionCriteria criteria);

    List<TypeAction> delete(List<TypeAction> ts);

    boolean deleteById(Long id);

    List<List<TypeAction>> getToBeSavedAndToBeDeleted(List<TypeAction> oldList, List<TypeAction> newList);

}
