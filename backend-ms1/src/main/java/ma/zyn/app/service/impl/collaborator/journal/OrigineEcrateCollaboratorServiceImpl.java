package ma.zyn.app.service.impl.collaborator.journal;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.journal.OrigineEcrate;
import ma.zyn.app.dao.criteria.core.journal.OrigineEcrateCriteria;
import ma.zyn.app.dao.facade.core.journal.OrigineEcrateDao;
import ma.zyn.app.dao.specification.core.journal.OrigineEcrateSpecification;
import ma.zyn.app.service.facade.collaborator.journal.OrigineEcrateCollaboratorService;
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
public class OrigineEcrateCollaboratorServiceImpl implements OrigineEcrateCollaboratorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public OrigineEcrate update(OrigineEcrate t) {
        OrigineEcrate loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{OrigineEcrate.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public OrigineEcrate findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public OrigineEcrate findOrSave(OrigineEcrate t) {
        if (t != null) {
            OrigineEcrate result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<OrigineEcrate> findAll() {
        return dao.findAll();
    }

    public List<OrigineEcrate> findByCriteria(OrigineEcrateCriteria criteria) {
        List<OrigineEcrate> content = null;
        if (criteria != null) {
            OrigineEcrateSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private OrigineEcrateSpecification constructSpecification(OrigineEcrateCriteria criteria) {
        OrigineEcrateSpecification mySpecification =  (OrigineEcrateSpecification) RefelexivityUtil.constructObjectUsingOneParam(OrigineEcrateSpecification.class, criteria);
        return mySpecification;
    }

    public List<OrigineEcrate> findPaginatedByCriteria(OrigineEcrateCriteria criteria, int page, int pageSize, String order, String sortField) {
        OrigineEcrateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(OrigineEcrateCriteria criteria) {
        OrigineEcrateSpecification mySpecification = constructSpecification(criteria);
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
    public List<OrigineEcrate> delete(List<OrigineEcrate> list) {
		List<OrigineEcrate> result = new ArrayList();
        if (list != null) {
            for (OrigineEcrate t : list) {
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
    public OrigineEcrate create(OrigineEcrate t) {
        OrigineEcrate loaded = findByReferenceEntity(t);
        OrigineEcrate saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public OrigineEcrate findWithAssociatedLists(Long id){
        OrigineEcrate result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<OrigineEcrate> update(List<OrigineEcrate> ts, boolean createIfNotExist) {
        List<OrigineEcrate> result = new ArrayList<>();
        if (ts != null) {
            for (OrigineEcrate t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    OrigineEcrate loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, OrigineEcrate t, OrigineEcrate loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public OrigineEcrate findByReferenceEntity(OrigineEcrate t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<OrigineEcrate> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<OrigineEcrate>> getToBeSavedAndToBeDeleted(List<OrigineEcrate> oldList, List<OrigineEcrate> newList) {
        List<List<OrigineEcrate>> result = new ArrayList<>();
        List<OrigineEcrate> resultDelete = new ArrayList<>();
        List<OrigineEcrate> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<OrigineEcrate> oldList, List<OrigineEcrate> newList, List<OrigineEcrate> resultUpdateOrSave, List<OrigineEcrate> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                OrigineEcrate myOld = oldList.get(i);
                OrigineEcrate t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                OrigineEcrate myNew = newList.get(i);
                OrigineEcrate t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public OrigineEcrateCollaboratorServiceImpl(OrigineEcrateDao dao) {
        this.dao = dao;
    }

    private OrigineEcrateDao dao;
}
