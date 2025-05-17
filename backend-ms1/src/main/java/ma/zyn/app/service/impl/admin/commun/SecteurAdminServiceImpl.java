package ma.zyn.app.service.impl.admin.commun;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.commun.Secteur;
import ma.zyn.app.dao.criteria.core.commun.SecteurCriteria;
import ma.zyn.app.dao.facade.core.commun.SecteurDao;
import ma.zyn.app.dao.specification.core.commun.SecteurSpecification;
import ma.zyn.app.service.facade.admin.commun.SecteurAdminService;
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
public class SecteurAdminServiceImpl implements SecteurAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Secteur update(Secteur t) {
        Secteur loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Secteur.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Secteur findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Secteur findOrSave(Secteur t) {
        if (t != null) {
            Secteur result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Secteur> findAll() {
        return dao.findAll();
    }

    public List<Secteur> findByCriteria(SecteurCriteria criteria) {
        List<Secteur> content = null;
        if (criteria != null) {
            SecteurSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private SecteurSpecification constructSpecification(SecteurCriteria criteria) {
        SecteurSpecification mySpecification =  (SecteurSpecification) RefelexivityUtil.constructObjectUsingOneParam(SecteurSpecification.class, criteria);
        return mySpecification;
    }

    public List<Secteur> findPaginatedByCriteria(SecteurCriteria criteria, int page, int pageSize, String order, String sortField) {
        SecteurSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(SecteurCriteria criteria) {
        SecteurSpecification mySpecification = constructSpecification(criteria);
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
    public List<Secteur> delete(List<Secteur> list) {
		List<Secteur> result = new ArrayList();
        if (list != null) {
            for (Secteur t : list) {
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
    public Secteur create(Secteur t) {
        Secteur loaded = findByReferenceEntity(t);
        Secteur saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Secteur findWithAssociatedLists(Long id){
        Secteur result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Secteur> update(List<Secteur> ts, boolean createIfNotExist) {
        List<Secteur> result = new ArrayList<>();
        if (ts != null) {
            for (Secteur t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Secteur loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Secteur t, Secteur loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Secteur findByReferenceEntity(Secteur t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Secteur> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Secteur>> getToBeSavedAndToBeDeleted(List<Secteur> oldList, List<Secteur> newList) {
        List<List<Secteur>> result = new ArrayList<>();
        List<Secteur> resultDelete = new ArrayList<>();
        List<Secteur> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Secteur> oldList, List<Secteur> newList, List<Secteur> resultUpdateOrSave, List<Secteur> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Secteur myOld = oldList.get(i);
                Secteur t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Secteur myNew = newList.get(i);
                Secteur t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public SecteurAdminServiceImpl(SecteurDao dao) {
        this.dao = dao;
    }

    private SecteurDao dao;
}
