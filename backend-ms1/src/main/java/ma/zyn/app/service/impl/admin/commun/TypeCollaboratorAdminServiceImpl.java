package ma.zyn.app.service.impl.admin.commun;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.commun.TypeCollaborator;
import ma.zyn.app.dao.criteria.core.commun.TypeCollaboratorCriteria;
import ma.zyn.app.dao.facade.core.commun.TypeCollaboratorDao;
import ma.zyn.app.dao.specification.core.commun.TypeCollaboratorSpecification;
import ma.zyn.app.service.facade.admin.commun.TypeCollaboratorAdminService;
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
public class TypeCollaboratorAdminServiceImpl implements TypeCollaboratorAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeCollaborator update(TypeCollaborator t) {
        TypeCollaborator loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{TypeCollaborator.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public TypeCollaborator findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public TypeCollaborator findOrSave(TypeCollaborator t) {
        if (t != null) {
            TypeCollaborator result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<TypeCollaborator> findAll() {
        return dao.findAll();
    }

    public List<TypeCollaborator> findByCriteria(TypeCollaboratorCriteria criteria) {
        List<TypeCollaborator> content = null;
        if (criteria != null) {
            TypeCollaboratorSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private TypeCollaboratorSpecification constructSpecification(TypeCollaboratorCriteria criteria) {
        TypeCollaboratorSpecification mySpecification =  (TypeCollaboratorSpecification) RefelexivityUtil.constructObjectUsingOneParam(TypeCollaboratorSpecification.class, criteria);
        return mySpecification;
    }

    public List<TypeCollaborator> findPaginatedByCriteria(TypeCollaboratorCriteria criteria, int page, int pageSize, String order, String sortField) {
        TypeCollaboratorSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TypeCollaboratorCriteria criteria) {
        TypeCollaboratorSpecification mySpecification = constructSpecification(criteria);
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
    public List<TypeCollaborator> delete(List<TypeCollaborator> list) {
		List<TypeCollaborator> result = new ArrayList();
        if (list != null) {
            for (TypeCollaborator t : list) {
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
    public TypeCollaborator create(TypeCollaborator t) {
        TypeCollaborator loaded = findByReferenceEntity(t);
        TypeCollaborator saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public TypeCollaborator findWithAssociatedLists(Long id){
        TypeCollaborator result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeCollaborator> update(List<TypeCollaborator> ts, boolean createIfNotExist) {
        List<TypeCollaborator> result = new ArrayList<>();
        if (ts != null) {
            for (TypeCollaborator t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    TypeCollaborator loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, TypeCollaborator t, TypeCollaborator loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public TypeCollaborator findByReferenceEntity(TypeCollaborator t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<TypeCollaborator> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<TypeCollaborator>> getToBeSavedAndToBeDeleted(List<TypeCollaborator> oldList, List<TypeCollaborator> newList) {
        List<List<TypeCollaborator>> result = new ArrayList<>();
        List<TypeCollaborator> resultDelete = new ArrayList<>();
        List<TypeCollaborator> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<TypeCollaborator> oldList, List<TypeCollaborator> newList, List<TypeCollaborator> resultUpdateOrSave, List<TypeCollaborator> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                TypeCollaborator myOld = oldList.get(i);
                TypeCollaborator t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                TypeCollaborator myNew = newList.get(i);
                TypeCollaborator t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public TypeCollaboratorAdminServiceImpl(TypeCollaboratorDao dao) {
        this.dao = dao;
    }

    private TypeCollaboratorDao dao;
}
