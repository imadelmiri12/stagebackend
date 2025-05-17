package ma.zyn.app.service.facade.collaborator.journal;

import java.util.List;
import ma.zyn.app.bean.core.journal.OrigineEcrate;
import ma.zyn.app.dao.criteria.core.journal.OrigineEcrateCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface OrigineEcrateCollaboratorService {







	OrigineEcrate create(OrigineEcrate t);

    OrigineEcrate update(OrigineEcrate t);

    List<OrigineEcrate> update(List<OrigineEcrate> ts,boolean createIfNotExist);

    OrigineEcrate findById(Long id);

    OrigineEcrate findOrSave(OrigineEcrate t);

    OrigineEcrate findByReferenceEntity(OrigineEcrate t);

    OrigineEcrate findWithAssociatedLists(Long id);

    List<OrigineEcrate> findAllOptimized();

    List<OrigineEcrate> findAll();

    List<OrigineEcrate> findByCriteria(OrigineEcrateCriteria criteria);

    List<OrigineEcrate> findPaginatedByCriteria(OrigineEcrateCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(OrigineEcrateCriteria criteria);

    List<OrigineEcrate> delete(List<OrigineEcrate> ts);

    boolean deleteById(Long id);

    List<List<OrigineEcrate>> getToBeSavedAndToBeDeleted(List<OrigineEcrate> oldList, List<OrigineEcrate> newList);

}
