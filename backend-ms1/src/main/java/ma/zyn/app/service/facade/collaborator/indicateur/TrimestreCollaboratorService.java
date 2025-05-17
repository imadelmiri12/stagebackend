package ma.zyn.app.service.facade.collaborator.indicateur;

import java.util.List;
import ma.zyn.app.bean.core.indicateur.Trimestre;
import ma.zyn.app.dao.criteria.core.indicateur.TrimestreCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface TrimestreCollaboratorService {







	Trimestre create(Trimestre t);

    Trimestre update(Trimestre t);

    List<Trimestre> update(List<Trimestre> ts,boolean createIfNotExist);

    Trimestre findById(Long id);

    Trimestre findOrSave(Trimestre t);

    Trimestre findByReferenceEntity(Trimestre t);

    Trimestre findWithAssociatedLists(Long id);

    List<Trimestre> findAllOptimized();

    List<Trimestre> findAll();

    List<Trimestre> findByCriteria(TrimestreCriteria criteria);

    List<Trimestre> findPaginatedByCriteria(TrimestreCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TrimestreCriteria criteria);

    List<Trimestre> delete(List<Trimestre> ts);

    boolean deleteById(Long id);

    List<List<Trimestre>> getToBeSavedAndToBeDeleted(List<Trimestre> oldList, List<Trimestre> newList);

}
