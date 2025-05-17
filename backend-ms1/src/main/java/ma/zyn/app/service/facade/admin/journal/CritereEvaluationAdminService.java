package ma.zyn.app.service.facade.admin.journal;

import java.util.List;
import ma.zyn.app.bean.core.journal.CritereEvaluation;
import ma.zyn.app.dao.criteria.core.journal.CritereEvaluationCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface CritereEvaluationAdminService {







	CritereEvaluation create(CritereEvaluation t);

    CritereEvaluation update(CritereEvaluation t);

    List<CritereEvaluation> update(List<CritereEvaluation> ts,boolean createIfNotExist);

    CritereEvaluation findById(Long id);

    CritereEvaluation findOrSave(CritereEvaluation t);

    CritereEvaluation findByReferenceEntity(CritereEvaluation t);

    CritereEvaluation findWithAssociatedLists(Long id);

    List<CritereEvaluation> findAllOptimized();

    List<CritereEvaluation> findAll();

    List<CritereEvaluation> findByCriteria(CritereEvaluationCriteria criteria);

    List<CritereEvaluation> findPaginatedByCriteria(CritereEvaluationCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CritereEvaluationCriteria criteria);

    List<CritereEvaluation> delete(List<CritereEvaluation> ts);

    boolean deleteById(Long id);

    List<List<CritereEvaluation>> getToBeSavedAndToBeDeleted(List<CritereEvaluation> oldList, List<CritereEvaluation> newList);

}
