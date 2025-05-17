package ma.zyn.app.service.impl.admin.journal;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.journal.ResultatEvaluation;
import ma.zyn.app.dao.criteria.core.journal.ResultatEvaluationCriteria;
import ma.zyn.app.dao.facade.core.journal.ResultatEvaluationDao;
import ma.zyn.app.dao.specification.core.journal.ResultatEvaluationSpecification;
import ma.zyn.app.service.facade.admin.journal.ResultatEvaluationAdminService;
import ma.zyn.app.zynerator.service.AbstractServiceImpl;
import static ma.zyn.app.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zyn.app.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
@Service
public class ResultatEvaluationAdminServiceImpl implements ResultatEvaluationAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ResultatEvaluation update(ResultatEvaluation t) {
        ResultatEvaluation loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ResultatEvaluation.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ResultatEvaluation findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ResultatEvaluation findOrSave(ResultatEvaluation t) {
        if (t != null) {
            ResultatEvaluation result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ResultatEvaluation> findAll() {
        return dao.findAll();
    }

    public List<ResultatEvaluation> findByCriteria(ResultatEvaluationCriteria criteria) {
        List<ResultatEvaluation> content = null;
        if (criteria != null) {
            ResultatEvaluationSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private ResultatEvaluationSpecification constructSpecification(ResultatEvaluationCriteria criteria) {
        ResultatEvaluationSpecification mySpecification =  (ResultatEvaluationSpecification) RefelexivityUtil.constructObjectUsingOneParam(ResultatEvaluationSpecification.class, criteria);
        return mySpecification;
    }

    public List<ResultatEvaluation> findPaginatedByCriteria(ResultatEvaluationCriteria criteria, int page, int pageSize, String order, String sortField) {
        ResultatEvaluationSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ResultatEvaluationCriteria criteria) {
        ResultatEvaluationSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ResultatEvaluation> delete(List<ResultatEvaluation> list) {
		List<ResultatEvaluation> result = new ArrayList();
        if (list != null) {
            for (ResultatEvaluation t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}else{
                    dao.deleteById(t.getId());
                }
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ResultatEvaluation create(ResultatEvaluation t) {
        ResultatEvaluation loaded = findByReferenceEntity(t);
        ResultatEvaluation saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ResultatEvaluation findWithAssociatedLists(Long id){
        ResultatEvaluation result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ResultatEvaluation> update(List<ResultatEvaluation> ts, boolean createIfNotExist) {
        List<ResultatEvaluation> result = new ArrayList<>();
        if (ts != null) {
            for (ResultatEvaluation t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ResultatEvaluation loadedItem = dao.findById(t.getId()).orElse(null);
                    if (isEligibleForCreateOrUpdate(createIfNotExist, t, loadedItem)) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ResultatEvaluation t, ResultatEvaluation loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ResultatEvaluation findByReferenceEntity(ResultatEvaluation t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<ResultatEvaluation> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<ResultatEvaluation>> getToBeSavedAndToBeDeleted(List<ResultatEvaluation> oldList, List<ResultatEvaluation> newList) {
        List<List<ResultatEvaluation>> result = new ArrayList<>();
        List<ResultatEvaluation> resultDelete = new ArrayList<>();
        List<ResultatEvaluation> resultUpdateOrSave = new ArrayList<>();
        if (isEmpty(oldList) && isNotEmpty(newList)) {
            resultUpdateOrSave.addAll(newList);
        } else if (isEmpty(newList) && isNotEmpty(oldList)) {
            resultDelete.addAll(oldList);
        } else if (isNotEmpty(newList) && isNotEmpty(oldList)) {
			extractToBeSaveOrDelete(oldList, newList, resultUpdateOrSave, resultDelete);
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }

    private void extractToBeSaveOrDelete(List<ResultatEvaluation> oldList, List<ResultatEvaluation> newList, List<ResultatEvaluation> resultUpdateOrSave, List<ResultatEvaluation> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ResultatEvaluation myOld = oldList.get(i);
                ResultatEvaluation t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ResultatEvaluation myNew = newList.get(i);
                ResultatEvaluation t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public ResultatEvaluationAdminServiceImpl(ResultatEvaluationDao dao) {
        this.dao = dao;
    }

    private ResultatEvaluationDao dao;
}
