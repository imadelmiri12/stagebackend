package ma.zyn.app.service.impl.collaborator.commun;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.commun.TypeRapportEvenement;
import ma.zyn.app.dao.criteria.core.commun.TypeRapportEvenementCriteria;
import ma.zyn.app.dao.facade.core.commun.TypeRapportEvenementDao;
import ma.zyn.app.dao.specification.core.commun.TypeRapportEvenementSpecification;
import ma.zyn.app.service.facade.collaborator.commun.TypeRapportEvenementCollaboratorService;
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
public class TypeRapportEvenementCollaboratorServiceImpl implements TypeRapportEvenementCollaboratorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeRapportEvenement update(TypeRapportEvenement t) {
        TypeRapportEvenement loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{TypeRapportEvenement.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public TypeRapportEvenement findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public TypeRapportEvenement findOrSave(TypeRapportEvenement t) {
        if (t != null) {
            TypeRapportEvenement result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<TypeRapportEvenement> findAll() {
        return dao.findAll();
    }

    public List<TypeRapportEvenement> findByCriteria(TypeRapportEvenementCriteria criteria) {
        List<TypeRapportEvenement> content = null;
        if (criteria != null) {
            TypeRapportEvenementSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private TypeRapportEvenementSpecification constructSpecification(TypeRapportEvenementCriteria criteria) {
        TypeRapportEvenementSpecification mySpecification =  (TypeRapportEvenementSpecification) RefelexivityUtil.constructObjectUsingOneParam(TypeRapportEvenementSpecification.class, criteria);
        return mySpecification;
    }

    public List<TypeRapportEvenement> findPaginatedByCriteria(TypeRapportEvenementCriteria criteria, int page, int pageSize, String order, String sortField) {
        TypeRapportEvenementSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TypeRapportEvenementCriteria criteria) {
        TypeRapportEvenementSpecification mySpecification = constructSpecification(criteria);
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
    public List<TypeRapportEvenement> delete(List<TypeRapportEvenement> list) {
		List<TypeRapportEvenement> result = new ArrayList();
        if (list != null) {
            for (TypeRapportEvenement t : list) {
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
    public TypeRapportEvenement create(TypeRapportEvenement t) {
        TypeRapportEvenement loaded = findByReferenceEntity(t);
        TypeRapportEvenement saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public TypeRapportEvenement findWithAssociatedLists(Long id){
        TypeRapportEvenement result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeRapportEvenement> update(List<TypeRapportEvenement> ts, boolean createIfNotExist) {
        List<TypeRapportEvenement> result = new ArrayList<>();
        if (ts != null) {
            for (TypeRapportEvenement t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    TypeRapportEvenement loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, TypeRapportEvenement t, TypeRapportEvenement loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public TypeRapportEvenement findByReferenceEntity(TypeRapportEvenement t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<TypeRapportEvenement> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<TypeRapportEvenement>> getToBeSavedAndToBeDeleted(List<TypeRapportEvenement> oldList, List<TypeRapportEvenement> newList) {
        List<List<TypeRapportEvenement>> result = new ArrayList<>();
        List<TypeRapportEvenement> resultDelete = new ArrayList<>();
        List<TypeRapportEvenement> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<TypeRapportEvenement> oldList, List<TypeRapportEvenement> newList, List<TypeRapportEvenement> resultUpdateOrSave, List<TypeRapportEvenement> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                TypeRapportEvenement myOld = oldList.get(i);
                TypeRapportEvenement t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                TypeRapportEvenement myNew = newList.get(i);
                TypeRapportEvenement t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public TypeRapportEvenementCollaboratorServiceImpl(TypeRapportEvenementDao dao) {
        this.dao = dao;
    }

    private TypeRapportEvenementDao dao;
}
