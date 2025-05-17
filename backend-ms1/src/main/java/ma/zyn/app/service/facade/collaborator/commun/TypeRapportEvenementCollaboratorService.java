package ma.zyn.app.service.facade.collaborator.commun;

import java.util.List;
import ma.zyn.app.bean.core.commun.TypeRapportEvenement;
import ma.zyn.app.dao.criteria.core.commun.TypeRapportEvenementCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface TypeRapportEvenementCollaboratorService {







	TypeRapportEvenement create(TypeRapportEvenement t);

    TypeRapportEvenement update(TypeRapportEvenement t);

    List<TypeRapportEvenement> update(List<TypeRapportEvenement> ts,boolean createIfNotExist);

    TypeRapportEvenement findById(Long id);

    TypeRapportEvenement findOrSave(TypeRapportEvenement t);

    TypeRapportEvenement findByReferenceEntity(TypeRapportEvenement t);

    TypeRapportEvenement findWithAssociatedLists(Long id);

    List<TypeRapportEvenement> findAllOptimized();

    List<TypeRapportEvenement> findAll();

    List<TypeRapportEvenement> findByCriteria(TypeRapportEvenementCriteria criteria);

    List<TypeRapportEvenement> findPaginatedByCriteria(TypeRapportEvenementCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TypeRapportEvenementCriteria criteria);

    List<TypeRapportEvenement> delete(List<TypeRapportEvenement> ts);

    boolean deleteById(Long id);

    List<List<TypeRapportEvenement>> getToBeSavedAndToBeDeleted(List<TypeRapportEvenement> oldList, List<TypeRapportEvenement> newList);

}
