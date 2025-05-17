package ma.zyn.app.service.impl.admin.commun;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.commun.TypeEvenement;
import ma.zyn.app.dao.criteria.core.commun.TypeEvenementCriteria;
import ma.zyn.app.dao.facade.core.commun.TypeEvenementDao;
import ma.zyn.app.dao.specification.core.commun.TypeEvenementSpecification;
import ma.zyn.app.service.facade.admin.commun.TypeEvenementAdminService;
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
public class TypeEvenementAdminServiceImpl implements TypeEvenementAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeEvenement update(TypeEvenement t) {
        TypeEvenement loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{TypeEvenement.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public TypeEvenement findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public TypeEvenement findOrSave(TypeEvenement t) {
        if (t != null) {
            TypeEvenement result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<TypeEvenement> findAll() {
        return dao.findAll();
    }

    public List<TypeEvenement> findByCriteria(TypeEvenementCriteria criteria) {
        List<TypeEvenement> content = null;
        if (criteria != null) {
            TypeEvenementSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private TypeEvenementSpecification constructSpecification(TypeEvenementCriteria criteria) {
        TypeEvenementSpecification mySpecification =  (TypeEvenementSpecification) RefelexivityUtil.constructObjectUsingOneParam(TypeEvenementSpecification.class, criteria);
        return mySpecification;
    }

    public List<TypeEvenement> findPaginatedByCriteria(TypeEvenementCriteria criteria, int page, int pageSize, String order, String sortField) {
        TypeEvenementSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TypeEvenementCriteria criteria) {
        TypeEvenementSpecification mySpecification = constructSpecification(criteria);
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
    public List<TypeEvenement> delete(List<TypeEvenement> list) {
		List<TypeEvenement> result = new ArrayList();
        if (list != null) {
            for (TypeEvenement t : list) {
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
    public TypeEvenement create(TypeEvenement t) {
        TypeEvenement loaded = findByReferenceEntity(t);
        TypeEvenement saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public TypeEvenement findWithAssociatedLists(Long id){
        TypeEvenement result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeEvenement> update(List<TypeEvenement> ts, boolean createIfNotExist) {
        List<TypeEvenement> result = new ArrayList<>();
        if (ts != null) {
            for (TypeEvenement t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    TypeEvenement loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, TypeEvenement t, TypeEvenement loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public TypeEvenement findByReferenceEntity(TypeEvenement t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<TypeEvenement> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<TypeEvenement>> getToBeSavedAndToBeDeleted(List<TypeEvenement> oldList, List<TypeEvenement> newList) {
        List<List<TypeEvenement>> result = new ArrayList<>();
        List<TypeEvenement> resultDelete = new ArrayList<>();
        List<TypeEvenement> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<TypeEvenement> oldList, List<TypeEvenement> newList, List<TypeEvenement> resultUpdateOrSave, List<TypeEvenement> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                TypeEvenement myOld = oldList.get(i);
                TypeEvenement t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                TypeEvenement myNew = newList.get(i);
                TypeEvenement t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public TypeEvenementAdminServiceImpl(TypeEvenementDao dao) {
        this.dao = dao;
    }

    private TypeEvenementDao dao;
}
