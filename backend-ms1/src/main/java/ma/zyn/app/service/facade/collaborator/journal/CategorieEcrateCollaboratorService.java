package ma.zyn.app.service.facade.collaborator.journal;

import java.util.List;
import ma.zyn.app.bean.core.journal.CategorieEcrate;
import ma.zyn.app.dao.criteria.core.journal.CategorieEcrateCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface CategorieEcrateCollaboratorService {







	CategorieEcrate create(CategorieEcrate t);

    CategorieEcrate update(CategorieEcrate t);

    List<CategorieEcrate> update(List<CategorieEcrate> ts,boolean createIfNotExist);

    CategorieEcrate findById(Long id);

    CategorieEcrate findOrSave(CategorieEcrate t);

    CategorieEcrate findByReferenceEntity(CategorieEcrate t);

    CategorieEcrate findWithAssociatedLists(Long id);

    List<CategorieEcrate> findAllOptimized();

    List<CategorieEcrate> findAll();

    List<CategorieEcrate> findByCriteria(CategorieEcrateCriteria criteria);

    List<CategorieEcrate> findPaginatedByCriteria(CategorieEcrateCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CategorieEcrateCriteria criteria);

    List<CategorieEcrate> delete(List<CategorieEcrate> ts);

    boolean deleteById(Long id);

    List<List<CategorieEcrate>> getToBeSavedAndToBeDeleted(List<CategorieEcrate> oldList, List<CategorieEcrate> newList);

}
