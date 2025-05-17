package ma.zyn.app.service.impl.collaborator.commun;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.commun.TypeVessel;
import ma.zyn.app.dao.criteria.core.commun.TypeVesselCriteria;
import ma.zyn.app.dao.facade.core.commun.TypeVesselDao;
import ma.zyn.app.dao.specification.core.commun.TypeVesselSpecification;
import ma.zyn.app.service.facade.collaborator.commun.TypeVesselCollaboratorService;
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
public class TypeVesselCollaboratorServiceImpl implements TypeVesselCollaboratorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeVessel update(TypeVessel t) {
        TypeVessel loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{TypeVessel.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public TypeVessel findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public TypeVessel findOrSave(TypeVessel t) {
        if (t != null) {
            TypeVessel result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<TypeVessel> findAll() {
        return dao.findAll();
    }

    public List<TypeVessel> findByCriteria(TypeVesselCriteria criteria) {
        List<TypeVessel> content = null;
        if (criteria != null) {
            TypeVesselSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private TypeVesselSpecification constructSpecification(TypeVesselCriteria criteria) {
        TypeVesselSpecification mySpecification =  (TypeVesselSpecification) RefelexivityUtil.constructObjectUsingOneParam(TypeVesselSpecification.class, criteria);
        return mySpecification;
    }

    public List<TypeVessel> findPaginatedByCriteria(TypeVesselCriteria criteria, int page, int pageSize, String order, String sortField) {
        TypeVesselSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TypeVesselCriteria criteria) {
        TypeVesselSpecification mySpecification = constructSpecification(criteria);
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
    public List<TypeVessel> delete(List<TypeVessel> list) {
		List<TypeVessel> result = new ArrayList();
        if (list != null) {
            for (TypeVessel t : list) {
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
    public TypeVessel create(TypeVessel t) {
        TypeVessel loaded = findByReferenceEntity(t);
        TypeVessel saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public TypeVessel findWithAssociatedLists(Long id){
        TypeVessel result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeVessel> update(List<TypeVessel> ts, boolean createIfNotExist) {
        List<TypeVessel> result = new ArrayList<>();
        if (ts != null) {
            for (TypeVessel t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    TypeVessel loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, TypeVessel t, TypeVessel loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public TypeVessel findByReferenceEntity(TypeVessel t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<TypeVessel> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<TypeVessel>> getToBeSavedAndToBeDeleted(List<TypeVessel> oldList, List<TypeVessel> newList) {
        List<List<TypeVessel>> result = new ArrayList<>();
        List<TypeVessel> resultDelete = new ArrayList<>();
        List<TypeVessel> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<TypeVessel> oldList, List<TypeVessel> newList, List<TypeVessel> resultUpdateOrSave, List<TypeVessel> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                TypeVessel myOld = oldList.get(i);
                TypeVessel t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                TypeVessel myNew = newList.get(i);
                TypeVessel t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public TypeVesselCollaboratorServiceImpl(TypeVesselDao dao) {
        this.dao = dao;
    }

    private TypeVesselDao dao;
}
