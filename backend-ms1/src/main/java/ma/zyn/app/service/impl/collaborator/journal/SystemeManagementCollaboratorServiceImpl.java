package ma.zyn.app.service.impl.collaborator.journal;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.journal.SystemeManagement;
import ma.zyn.app.dao.criteria.core.journal.SystemeManagementCriteria;
import ma.zyn.app.dao.facade.core.journal.SystemeManagementDao;
import ma.zyn.app.dao.specification.core.journal.SystemeManagementSpecification;
import ma.zyn.app.service.facade.collaborator.journal.SystemeManagementCollaboratorService;
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
public class SystemeManagementCollaboratorServiceImpl implements SystemeManagementCollaboratorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public SystemeManagement update(SystemeManagement t) {
        SystemeManagement loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{SystemeManagement.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public SystemeManagement findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public SystemeManagement findOrSave(SystemeManagement t) {
        if (t != null) {
            SystemeManagement result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<SystemeManagement> findAll() {
        return dao.findAll();
    }

    public List<SystemeManagement> findByCriteria(SystemeManagementCriteria criteria) {
        List<SystemeManagement> content = null;
        if (criteria != null) {
            SystemeManagementSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private SystemeManagementSpecification constructSpecification(SystemeManagementCriteria criteria) {
        SystemeManagementSpecification mySpecification =  (SystemeManagementSpecification) RefelexivityUtil.constructObjectUsingOneParam(SystemeManagementSpecification.class, criteria);
        return mySpecification;
    }

    public List<SystemeManagement> findPaginatedByCriteria(SystemeManagementCriteria criteria, int page, int pageSize, String order, String sortField) {
        SystemeManagementSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(SystemeManagementCriteria criteria) {
        SystemeManagementSpecification mySpecification = constructSpecification(criteria);
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
    public List<SystemeManagement> delete(List<SystemeManagement> list) {
		List<SystemeManagement> result = new ArrayList();
        if (list != null) {
            for (SystemeManagement t : list) {
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
    public SystemeManagement create(SystemeManagement t) {
        SystemeManagement loaded = findByReferenceEntity(t);
        SystemeManagement saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public SystemeManagement findWithAssociatedLists(Long id){
        SystemeManagement result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<SystemeManagement> update(List<SystemeManagement> ts, boolean createIfNotExist) {
        List<SystemeManagement> result = new ArrayList<>();
        if (ts != null) {
            for (SystemeManagement t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    SystemeManagement loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, SystemeManagement t, SystemeManagement loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public SystemeManagement findByReferenceEntity(SystemeManagement t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<SystemeManagement> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<SystemeManagement>> getToBeSavedAndToBeDeleted(List<SystemeManagement> oldList, List<SystemeManagement> newList) {
        List<List<SystemeManagement>> result = new ArrayList<>();
        List<SystemeManagement> resultDelete = new ArrayList<>();
        List<SystemeManagement> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<SystemeManagement> oldList, List<SystemeManagement> newList, List<SystemeManagement> resultUpdateOrSave, List<SystemeManagement> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                SystemeManagement myOld = oldList.get(i);
                SystemeManagement t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                SystemeManagement myNew = newList.get(i);
                SystemeManagement t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public SystemeManagementCollaboratorServiceImpl(SystemeManagementDao dao) {
        this.dao = dao;
    }

    private SystemeManagementDao dao;
}
