package ma.zyn.app.service.impl.admin.commun;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.commun.Vessel;
import ma.zyn.app.dao.criteria.core.commun.VesselCriteria;
import ma.zyn.app.dao.facade.core.commun.VesselDao;
import ma.zyn.app.dao.specification.core.commun.VesselSpecification;
import ma.zyn.app.service.facade.admin.commun.VesselAdminService;
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
public class VesselAdminServiceImpl implements VesselAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Vessel update(Vessel t) {
        Vessel loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Vessel.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Vessel findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Vessel findOrSave(Vessel t) {
        if (t != null) {
            Vessel result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Vessel> findAll() {
        return dao.findAll();
    }

    public List<Vessel> findByCriteria(VesselCriteria criteria) {
        List<Vessel> content = null;
        if (criteria != null) {
            VesselSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private VesselSpecification constructSpecification(VesselCriteria criteria) {
        VesselSpecification mySpecification =  (VesselSpecification) RefelexivityUtil.constructObjectUsingOneParam(VesselSpecification.class, criteria);
        return mySpecification;
    }

    public List<Vessel> findPaginatedByCriteria(VesselCriteria criteria, int page, int pageSize, String order, String sortField) {
        VesselSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(VesselCriteria criteria) {
        VesselSpecification mySpecification = constructSpecification(criteria);
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
    public List<Vessel> delete(List<Vessel> list) {
		List<Vessel> result = new ArrayList();
        if (list != null) {
            for (Vessel t : list) {
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
    public Vessel create(Vessel t) {
        Vessel loaded = findByReferenceEntity(t);
        Vessel saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Vessel findWithAssociatedLists(Long id){
        Vessel result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Vessel> update(List<Vessel> ts, boolean createIfNotExist) {
        List<Vessel> result = new ArrayList<>();
        if (ts != null) {
            for (Vessel t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Vessel loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Vessel t, Vessel loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Vessel findByReferenceEntity(Vessel t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Vessel> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Vessel>> getToBeSavedAndToBeDeleted(List<Vessel> oldList, List<Vessel> newList) {
        List<List<Vessel>> result = new ArrayList<>();
        List<Vessel> resultDelete = new ArrayList<>();
        List<Vessel> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Vessel> oldList, List<Vessel> newList, List<Vessel> resultUpdateOrSave, List<Vessel> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Vessel myOld = oldList.get(i);
                Vessel t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Vessel myNew = newList.get(i);
                Vessel t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public VesselAdminServiceImpl(VesselDao dao) {
        this.dao = dao;
    }

    private VesselDao dao;
}
