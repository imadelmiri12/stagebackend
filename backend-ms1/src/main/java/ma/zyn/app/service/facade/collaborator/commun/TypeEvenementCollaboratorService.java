package ma.zyn.app.service.facade.collaborator.commun;

import java.util.List;
import ma.zyn.app.bean.core.commun.TypeEvenement;
import ma.zyn.app.dao.criteria.core.commun.TypeEvenementCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface TypeEvenementCollaboratorService {







	TypeEvenement create(TypeEvenement t);

    TypeEvenement update(TypeEvenement t);

    List<TypeEvenement> update(List<TypeEvenement> ts,boolean createIfNotExist);

    TypeEvenement findById(Long id);

    TypeEvenement findOrSave(TypeEvenement t);

    TypeEvenement findByReferenceEntity(TypeEvenement t);

    TypeEvenement findWithAssociatedLists(Long id);

    List<TypeEvenement> findAllOptimized();

    List<TypeEvenement> findAll();

    List<TypeEvenement> findByCriteria(TypeEvenementCriteria criteria);

    List<TypeEvenement> findPaginatedByCriteria(TypeEvenementCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TypeEvenementCriteria criteria);

    List<TypeEvenement> delete(List<TypeEvenement> ts);

    boolean deleteById(Long id);

    List<List<TypeEvenement>> getToBeSavedAndToBeDeleted(List<TypeEvenement> oldList, List<TypeEvenement> newList);

}
