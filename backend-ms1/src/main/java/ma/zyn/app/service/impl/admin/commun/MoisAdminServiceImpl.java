package ma.zyn.app.service.impl.admin.commun;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.commun.Mois;
import ma.zyn.app.dao.criteria.core.commun.MoisCriteria;
import ma.zyn.app.dao.facade.core.commun.MoisDao;
import ma.zyn.app.dao.specification.core.commun.MoisSpecification;
import ma.zyn.app.service.facade.admin.commun.MoisAdminService;
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

import ma.zyn.app.service.facade.admin.indicateur.TrimestreAdminService ;
import ma.zyn.app.bean.core.indicateur.Trimestre ;

import java.util.List;
@Service
public class MoisAdminServiceImpl implements MoisAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Mois update(Mois t) {
        Mois loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Mois.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Mois findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Mois findOrSave(Mois t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Mois result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Mois> findAll() {
        return dao.findAll();
    }

    public List<Mois> findByCriteria(MoisCriteria criteria) {
        List<Mois> content = null;
        if (criteria != null) {
            MoisSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private MoisSpecification constructSpecification(MoisCriteria criteria) {
        MoisSpecification mySpecification =  (MoisSpecification) RefelexivityUtil.constructObjectUsingOneParam(MoisSpecification.class, criteria);
        return mySpecification;
    }

    public List<Mois> findPaginatedByCriteria(MoisCriteria criteria, int page, int pageSize, String order, String sortField) {
        MoisSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(MoisCriteria criteria) {
        MoisSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Mois> findByTrimestreCode(String code){
        return dao.findByTrimestreCode(code);
    }
    public List<Mois> findByTrimestreId(Long id){
        return dao.findByTrimestreId(id);
    }
    public int deleteByTrimestreCode(String code){
        return dao.deleteByTrimestreCode(code);
    }
    public int deleteByTrimestreId(Long id){
        return dao.deleteByTrimestreId(id);
    }
    public long countByTrimestreCode(String code){
        return dao.countByTrimestreCode(code);
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
    public List<Mois> delete(List<Mois> list) {
		List<Mois> result = new ArrayList();
        if (list != null) {
            for (Mois t : list) {
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
    public Mois create(Mois t) {
        Mois loaded = findByReferenceEntity(t);
        Mois saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Mois findWithAssociatedLists(Long id){
        Mois result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Mois> update(List<Mois> ts, boolean createIfNotExist) {
        List<Mois> result = new ArrayList<>();
        if (ts != null) {
            for (Mois t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Mois loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Mois t, Mois loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Mois findByReferenceEntity(Mois t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Mois t){
        if( t != null) {
            t.setTrimestre(trimestreService.findOrSave(t.getTrimestre()));
        }
    }



    public List<Mois> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Mois>> getToBeSavedAndToBeDeleted(List<Mois> oldList, List<Mois> newList) {
        List<List<Mois>> result = new ArrayList<>();
        List<Mois> resultDelete = new ArrayList<>();
        List<Mois> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Mois> oldList, List<Mois> newList, List<Mois> resultUpdateOrSave, List<Mois> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Mois myOld = oldList.get(i);
                Mois t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Mois myNew = newList.get(i);
                Mois t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    @Autowired
    private TrimestreAdminService trimestreService ;

    public MoisAdminServiceImpl(MoisDao dao) {
        this.dao = dao;
    }

    private MoisDao dao;
}
