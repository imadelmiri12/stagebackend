package ma.zyn.app.service.facade.collaborator.commun;

import java.util.List;
import ma.zyn.app.bean.core.commun.TypeCollaborator;
import ma.zyn.app.dao.criteria.core.commun.TypeCollaboratorCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface TypeCollaboratorCollaboratorService {







	TypeCollaborator create(TypeCollaborator t);

    TypeCollaborator update(TypeCollaborator t);

    List<TypeCollaborator> update(List<TypeCollaborator> ts,boolean createIfNotExist);

    TypeCollaborator findById(Long id);

    TypeCollaborator findOrSave(TypeCollaborator t);

    TypeCollaborator findByReferenceEntity(TypeCollaborator t);

    TypeCollaborator findWithAssociatedLists(Long id);

    List<TypeCollaborator> findAllOptimized();

    List<TypeCollaborator> findAll();

    List<TypeCollaborator> findByCriteria(TypeCollaboratorCriteria criteria);

    List<TypeCollaborator> findPaginatedByCriteria(TypeCollaboratorCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TypeCollaboratorCriteria criteria);

    List<TypeCollaborator> delete(List<TypeCollaborator> ts);

    boolean deleteById(Long id);

    List<List<TypeCollaborator>> getToBeSavedAndToBeDeleted(List<TypeCollaborator> oldList, List<TypeCollaborator> newList);

}
