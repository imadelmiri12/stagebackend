package ma.zyn.app.service.impl.collaborator.journal;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.journal.CategorieEcrate;
import ma.zyn.app.dao.criteria.core.journal.CategorieEcrateCriteria;
import ma.zyn.app.dao.facade.core.journal.CategorieEcrateDao;
import ma.zyn.app.dao.specification.core.journal.CategorieEcrateSpecification;
import ma.zyn.app.service.facade.collaborator.journal.CategorieEcrateCollaboratorService;
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
public class CategorieEcrateCollaboratorServiceImpl implements CategorieEcrateCollaboratorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CategorieEcrate update(CategorieEcrate t) {
        CategorieEcrate loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{CategorieEcrate.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public CategorieEcrate findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public CategorieEcrate findOrSave(CategorieEcrate t) {
        if (t != null) {
            CategorieEcrate result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<CategorieEcrate> findAll() {
        return dao.findAll();
    }

    public List<CategorieEcrate> findByCriteria(CategorieEcrateCriteria criteria) {
        List<CategorieEcrate> content = null;
        if (criteria != null) {
            CategorieEcrateSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private CategorieEcrateSpecification constructSpecification(CategorieEcrateCriteria criteria) {
        CategorieEcrateSpecification mySpecification =  (CategorieEcrateSpecification) RefelexivityUtil.constructObjectUsingOneParam(CategorieEcrateSpecification.class, criteria);
        return mySpecification;
    }

    public List<CategorieEcrate> findPaginatedByCriteria(CategorieEcrateCriteria criteria, int page, int pageSize, String order, String sortField) {
        CategorieEcrateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CategorieEcrateCriteria criteria) {
        CategorieEcrateSpecification mySpecification = constructSpecification(criteria);
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
    public List<CategorieEcrate> delete(List<CategorieEcrate> list) {
		List<CategorieEcrate> result = new ArrayList();
        if (list != null) {
            for (CategorieEcrate t : list) {
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
    public CategorieEcrate create(CategorieEcrate t) {
        CategorieEcrate loaded = findByReferenceEntity(t);
        CategorieEcrate saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public CategorieEcrate findWithAssociatedLists(Long id){
        CategorieEcrate result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CategorieEcrate> update(List<CategorieEcrate> ts, boolean createIfNotExist) {
        List<CategorieEcrate> result = new ArrayList<>();
        if (ts != null) {
            for (CategorieEcrate t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    CategorieEcrate loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, CategorieEcrate t, CategorieEcrate loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public CategorieEcrate findByReferenceEntity(CategorieEcrate t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<CategorieEcrate> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<CategorieEcrate>> getToBeSavedAndToBeDeleted(List<CategorieEcrate> oldList, List<CategorieEcrate> newList) {
        List<List<CategorieEcrate>> result = new ArrayList<>();
        List<CategorieEcrate> resultDelete = new ArrayList<>();
        List<CategorieEcrate> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<CategorieEcrate> oldList, List<CategorieEcrate> newList, List<CategorieEcrate> resultUpdateOrSave, List<CategorieEcrate> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                CategorieEcrate myOld = oldList.get(i);
                CategorieEcrate t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                CategorieEcrate myNew = newList.get(i);
                CategorieEcrate t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public CategorieEcrateCollaboratorServiceImpl(CategorieEcrateDao dao) {
        this.dao = dao;
    }

    private CategorieEcrateDao dao;
}
