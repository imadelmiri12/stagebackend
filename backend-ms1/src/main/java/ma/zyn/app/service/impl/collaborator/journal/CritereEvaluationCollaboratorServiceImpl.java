package ma.zyn.app.service.impl.collaborator.journal;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.journal.CritereEvaluation;
import ma.zyn.app.dao.criteria.core.journal.CritereEvaluationCriteria;
import ma.zyn.app.dao.facade.core.journal.CritereEvaluationDao;
import ma.zyn.app.dao.specification.core.journal.CritereEvaluationSpecification;
import ma.zyn.app.service.facade.collaborator.journal.CritereEvaluationCollaboratorService;
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
public class CritereEvaluationCollaboratorServiceImpl implements CritereEvaluationCollaboratorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CritereEvaluation update(CritereEvaluation t) {
        CritereEvaluation loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{CritereEvaluation.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public CritereEvaluation findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public CritereEvaluation findOrSave(CritereEvaluation t) {
        if (t != null) {
            CritereEvaluation result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<CritereEvaluation> findAll() {
        return dao.findAll();
    }

    public List<CritereEvaluation> findByCriteria(CritereEvaluationCriteria criteria) {
        List<CritereEvaluation> content = null;
        if (criteria != null) {
            CritereEvaluationSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private CritereEvaluationSpecification constructSpecification(CritereEvaluationCriteria criteria) {
        CritereEvaluationSpecification mySpecification =  (CritereEvaluationSpecification) RefelexivityUtil.constructObjectUsingOneParam(CritereEvaluationSpecification.class, criteria);
        return mySpecification;
    }

    public List<CritereEvaluation> findPaginatedByCriteria(CritereEvaluationCriteria criteria, int page, int pageSize, String order, String sortField) {
        CritereEvaluationSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CritereEvaluationCriteria criteria) {
        CritereEvaluationSpecification mySpecification = constructSpecification(criteria);
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
    public List<CritereEvaluation> delete(List<CritereEvaluation> list) {
		List<CritereEvaluation> result = new ArrayList();
        if (list != null) {
            for (CritereEvaluation t : list) {
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
    public CritereEvaluation create(CritereEvaluation t) {
        CritereEvaluation loaded = findByReferenceEntity(t);
        CritereEvaluation saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public CritereEvaluation findWithAssociatedLists(Long id){
        CritereEvaluation result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CritereEvaluation> update(List<CritereEvaluation> ts, boolean createIfNotExist) {
        List<CritereEvaluation> result = new ArrayList<>();
        if (ts != null) {
            for (CritereEvaluation t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    CritereEvaluation loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, CritereEvaluation t, CritereEvaluation loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public CritereEvaluation findByReferenceEntity(CritereEvaluation t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<CritereEvaluation> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<CritereEvaluation>> getToBeSavedAndToBeDeleted(List<CritereEvaluation> oldList, List<CritereEvaluation> newList) {
        List<List<CritereEvaluation>> result = new ArrayList<>();
        List<CritereEvaluation> resultDelete = new ArrayList<>();
        List<CritereEvaluation> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<CritereEvaluation> oldList, List<CritereEvaluation> newList, List<CritereEvaluation> resultUpdateOrSave, List<CritereEvaluation> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                CritereEvaluation myOld = oldList.get(i);
                CritereEvaluation t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                CritereEvaluation myNew = newList.get(i);
                CritereEvaluation t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public CritereEvaluationCollaboratorServiceImpl(CritereEvaluationDao dao) {
        this.dao = dao;
    }

    private CritereEvaluationDao dao;
}
