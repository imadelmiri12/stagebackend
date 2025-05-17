package ma.zyn.app.service.impl.admin.indicateur;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.indicateur.Trimestre;
import ma.zyn.app.dao.criteria.core.indicateur.TrimestreCriteria;
import ma.zyn.app.dao.facade.core.indicateur.TrimestreDao;
import ma.zyn.app.dao.specification.core.indicateur.TrimestreSpecification;
import ma.zyn.app.service.facade.admin.indicateur.TrimestreAdminService;
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
public class TrimestreAdminServiceImpl implements TrimestreAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Trimestre update(Trimestre t) {
        Trimestre loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Trimestre.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Trimestre findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Trimestre findOrSave(Trimestre t) {
        if (t != null) {
            Trimestre result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Trimestre> findAll() {
        return dao.findAll();
    }

    public List<Trimestre> findByCriteria(TrimestreCriteria criteria) {
        List<Trimestre> content = null;
        if (criteria != null) {
            TrimestreSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private TrimestreSpecification constructSpecification(TrimestreCriteria criteria) {
        TrimestreSpecification mySpecification =  (TrimestreSpecification) RefelexivityUtil.constructObjectUsingOneParam(TrimestreSpecification.class, criteria);
        return mySpecification;
    }

    public List<Trimestre> findPaginatedByCriteria(TrimestreCriteria criteria, int page, int pageSize, String order, String sortField) {
        TrimestreSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TrimestreCriteria criteria) {
        TrimestreSpecification mySpecification = constructSpecification(criteria);
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
    public List<Trimestre> delete(List<Trimestre> list) {
		List<Trimestre> result = new ArrayList();
        if (list != null) {
            for (Trimestre t : list) {
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
    public Trimestre create(Trimestre t) {
        Trimestre loaded = findByReferenceEntity(t);
        Trimestre saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Trimestre findWithAssociatedLists(Long id){
        Trimestre result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Trimestre> update(List<Trimestre> ts, boolean createIfNotExist) {
        List<Trimestre> result = new ArrayList<>();
        if (ts != null) {
            for (Trimestre t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Trimestre loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Trimestre t, Trimestre loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Trimestre findByReferenceEntity(Trimestre t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Trimestre> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Trimestre>> getToBeSavedAndToBeDeleted(List<Trimestre> oldList, List<Trimestre> newList) {
        List<List<Trimestre>> result = new ArrayList<>();
        List<Trimestre> resultDelete = new ArrayList<>();
        List<Trimestre> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Trimestre> oldList, List<Trimestre> newList, List<Trimestre> resultUpdateOrSave, List<Trimestre> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Trimestre myOld = oldList.get(i);
                Trimestre t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Trimestre myNew = newList.get(i);
                Trimestre t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public TrimestreAdminServiceImpl(TrimestreDao dao) {
        this.dao = dao;
    }

    private TrimestreDao dao;
}
