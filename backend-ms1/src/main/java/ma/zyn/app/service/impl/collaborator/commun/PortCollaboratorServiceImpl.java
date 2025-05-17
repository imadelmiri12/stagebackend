package ma.zyn.app.service.impl.collaborator.commun;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.commun.Port;
import ma.zyn.app.dao.criteria.core.commun.PortCriteria;
import ma.zyn.app.dao.facade.core.commun.PortDao;
import ma.zyn.app.dao.specification.core.commun.PortSpecification;
import ma.zyn.app.service.facade.collaborator.commun.PortCollaboratorService;
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
public class PortCollaboratorServiceImpl implements PortCollaboratorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Port update(Port t) {
        Port loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Port.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Port findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Port findOrSave(Port t) {
        if (t != null) {
            Port result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Port> findAll() {
        return dao.findAll();
    }

    public List<Port> findByCriteria(PortCriteria criteria) {
        List<Port> content = null;
        if (criteria != null) {
            PortSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private PortSpecification constructSpecification(PortCriteria criteria) {
        PortSpecification mySpecification =  (PortSpecification) RefelexivityUtil.constructObjectUsingOneParam(PortSpecification.class, criteria);
        return mySpecification;
    }

    public List<Port> findPaginatedByCriteria(PortCriteria criteria, int page, int pageSize, String order, String sortField) {
        PortSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(PortCriteria criteria) {
        PortSpecification mySpecification = constructSpecification(criteria);
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
    public List<Port> delete(List<Port> list) {
		List<Port> result = new ArrayList();
        if (list != null) {
            for (Port t : list) {
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
    public Port create(Port t) {
        Port loaded = findByReferenceEntity(t);
        Port saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Port findWithAssociatedLists(Long id){
        Port result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Port> update(List<Port> ts, boolean createIfNotExist) {
        List<Port> result = new ArrayList<>();
        if (ts != null) {
            for (Port t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Port loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Port t, Port loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Port findByReferenceEntity(Port t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Port> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Port>> getToBeSavedAndToBeDeleted(List<Port> oldList, List<Port> newList) {
        List<List<Port>> result = new ArrayList<>();
        List<Port> resultDelete = new ArrayList<>();
        List<Port> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Port> oldList, List<Port> newList, List<Port> resultUpdateOrSave, List<Port> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Port myOld = oldList.get(i);
                Port t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Port myNew = newList.get(i);
                Port t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public PortCollaboratorServiceImpl(PortDao dao) {
        this.dao = dao;
    }

    private PortDao dao;
}
