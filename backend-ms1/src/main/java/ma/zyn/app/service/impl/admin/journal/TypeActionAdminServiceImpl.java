package ma.zyn.app.service.impl.admin.journal;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.journal.TypeAction;
import ma.zyn.app.dao.criteria.core.journal.TypeActionCriteria;
import ma.zyn.app.dao.facade.core.journal.TypeActionDao;
import ma.zyn.app.dao.specification.core.journal.TypeActionSpecification;
import ma.zyn.app.service.facade.admin.journal.TypeActionAdminService;
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
public class TypeActionAdminServiceImpl implements TypeActionAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeAction update(TypeAction t) {
        TypeAction loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{TypeAction.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public TypeAction findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public TypeAction findOrSave(TypeAction t) {
        if (t != null) {
            TypeAction result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<TypeAction> findAll() {
        return dao.findAll();
    }

    public List<TypeAction> findByCriteria(TypeActionCriteria criteria) {
        List<TypeAction> content = null;
        if (criteria != null) {
            TypeActionSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private TypeActionSpecification constructSpecification(TypeActionCriteria criteria) {
        TypeActionSpecification mySpecification =  (TypeActionSpecification) RefelexivityUtil.constructObjectUsingOneParam(TypeActionSpecification.class, criteria);
        return mySpecification;
    }

    public List<TypeAction> findPaginatedByCriteria(TypeActionCriteria criteria, int page, int pageSize, String order, String sortField) {
        TypeActionSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TypeActionCriteria criteria) {
        TypeActionSpecification mySpecification = constructSpecification(criteria);
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
    public List<TypeAction> delete(List<TypeAction> list) {
		List<TypeAction> result = new ArrayList();
        if (list != null) {
            for (TypeAction t : list) {
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
    public TypeAction create(TypeAction t) {
        TypeAction loaded = findByReferenceEntity(t);
        TypeAction saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public TypeAction findWithAssociatedLists(Long id){
        TypeAction result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeAction> update(List<TypeAction> ts, boolean createIfNotExist) {
        List<TypeAction> result = new ArrayList<>();
        if (ts != null) {
            for (TypeAction t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    TypeAction loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, TypeAction t, TypeAction loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public TypeAction findByReferenceEntity(TypeAction t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<TypeAction> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<TypeAction>> getToBeSavedAndToBeDeleted(List<TypeAction> oldList, List<TypeAction> newList) {
        List<List<TypeAction>> result = new ArrayList<>();
        List<TypeAction> resultDelete = new ArrayList<>();
        List<TypeAction> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<TypeAction> oldList, List<TypeAction> newList, List<TypeAction> resultUpdateOrSave, List<TypeAction> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                TypeAction myOld = oldList.get(i);
                TypeAction t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                TypeAction myNew = newList.get(i);
                TypeAction t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public TypeActionAdminServiceImpl(TypeActionDao dao) {
        this.dao = dao;
    }

    private TypeActionDao dao;
}
