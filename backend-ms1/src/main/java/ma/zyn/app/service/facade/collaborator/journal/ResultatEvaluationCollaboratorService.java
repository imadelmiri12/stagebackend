package ma.zyn.app.service.facade.collaborator.journal;

import java.util.List;
import ma.zyn.app.bean.core.journal.ResultatEvaluation;
import ma.zyn.app.dao.criteria.core.journal.ResultatEvaluationCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface ResultatEvaluationCollaboratorService {







	ResultatEvaluation create(ResultatEvaluation t);

    ResultatEvaluation update(ResultatEvaluation t);

    List<ResultatEvaluation> update(List<ResultatEvaluation> ts,boolean createIfNotExist);

    ResultatEvaluation findById(Long id);

    ResultatEvaluation findOrSave(ResultatEvaluation t);

    ResultatEvaluation findByReferenceEntity(ResultatEvaluation t);

    ResultatEvaluation findWithAssociatedLists(Long id);

    List<ResultatEvaluation> findAllOptimized();

    List<ResultatEvaluation> findAll();

    List<ResultatEvaluation> findByCriteria(ResultatEvaluationCriteria criteria);

    List<ResultatEvaluation> findPaginatedByCriteria(ResultatEvaluationCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ResultatEvaluationCriteria criteria);

    List<ResultatEvaluation> delete(List<ResultatEvaluation> ts);

    boolean deleteById(Long id);

    List<List<ResultatEvaluation>> getToBeSavedAndToBeDeleted(List<ResultatEvaluation> oldList, List<ResultatEvaluation> newList);

}
