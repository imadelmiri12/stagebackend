package ma.zyn.app.service.impl.admin.mensuel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelSecteurDetail;
import ma.zyn.app.dao.criteria.core.mensuel.RapportEvenementMensuelSecteurDetailCriteria;
import ma.zyn.app.dao.facade.core.mensuel.RapportEvenementMensuelSecteurDetailDao;
import ma.zyn.app.dao.specification.core.mensuel.RapportEvenementMensuelSecteurDetailSpecification;
import ma.zyn.app.service.facade.admin.mensuel.RapportEvenementMensuelSecteurDetailAdminService;
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

import ma.zyn.app.service.facade.admin.commun.TypeEvenementAdminService ;
import ma.zyn.app.bean.core.commun.TypeEvenement ;
import ma.zyn.app.service.facade.admin.mensuel.RapportEvenementMensuelSecteurAdminService ;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelSecteur ;

import java.util.List;
@Service
public class RapportEvenementMensuelSecteurDetailAdminServiceImpl implements RapportEvenementMensuelSecteurDetailAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public RapportEvenementMensuelSecteurDetail update(RapportEvenementMensuelSecteurDetail t) {
        RapportEvenementMensuelSecteurDetail loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{RapportEvenementMensuelSecteurDetail.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public RapportEvenementMensuelSecteurDetail findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public RapportEvenementMensuelSecteurDetail findOrSave(RapportEvenementMensuelSecteurDetail t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            RapportEvenementMensuelSecteurDetail result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<RapportEvenementMensuelSecteurDetail> findAll() {
        return dao.findAll();
    }

    public List<RapportEvenementMensuelSecteurDetail> findByCriteria(RapportEvenementMensuelSecteurDetailCriteria criteria) {
        List<RapportEvenementMensuelSecteurDetail> content = null;
        if (criteria != null) {
            RapportEvenementMensuelSecteurDetailSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private RapportEvenementMensuelSecteurDetailSpecification constructSpecification(RapportEvenementMensuelSecteurDetailCriteria criteria) {
        RapportEvenementMensuelSecteurDetailSpecification mySpecification =  (RapportEvenementMensuelSecteurDetailSpecification) RefelexivityUtil.constructObjectUsingOneParam(RapportEvenementMensuelSecteurDetailSpecification.class, criteria);
        return mySpecification;
    }

    public List<RapportEvenementMensuelSecteurDetail> findPaginatedByCriteria(RapportEvenementMensuelSecteurDetailCriteria criteria, int page, int pageSize, String order, String sortField) {
        RapportEvenementMensuelSecteurDetailSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(RapportEvenementMensuelSecteurDetailCriteria criteria) {
        RapportEvenementMensuelSecteurDetailSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<RapportEvenementMensuelSecteurDetail> findByRapportEvenementMensuelSecteurId(Long id){
        return dao.findByRapportEvenementMensuelSecteurId(id);
    }
    public int deleteByRapportEvenementMensuelSecteurId(Long id){
        return dao.deleteByRapportEvenementMensuelSecteurId(id);
    }
    public long countByRapportEvenementMensuelSecteurId(Long id){
        return dao.countByRapportEvenementMensuelSecteurId(id);
    }
    public List<RapportEvenementMensuelSecteurDetail> findByTypeEvenementCode(String code){
        return dao.findByTypeEvenementCode(code);
    }
    public List<RapportEvenementMensuelSecteurDetail> findByTypeEvenementId(Long id){
        return dao.findByTypeEvenementId(id);
    }
    public int deleteByTypeEvenementCode(String code){
        return dao.deleteByTypeEvenementCode(code);
    }
    public int deleteByTypeEvenementId(Long id){
        return dao.deleteByTypeEvenementId(id);
    }
    public long countByTypeEvenementCode(String code){
        return dao.countByTypeEvenementCode(code);
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
    public List<RapportEvenementMensuelSecteurDetail> delete(List<RapportEvenementMensuelSecteurDetail> list) {
		List<RapportEvenementMensuelSecteurDetail> result = new ArrayList();
        if (list != null) {
            for (RapportEvenementMensuelSecteurDetail t : list) {
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
    public RapportEvenementMensuelSecteurDetail create(RapportEvenementMensuelSecteurDetail t) {
        RapportEvenementMensuelSecteurDetail loaded = findByReferenceEntity(t);
        RapportEvenementMensuelSecteurDetail saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public RapportEvenementMensuelSecteurDetail findWithAssociatedLists(Long id){
        RapportEvenementMensuelSecteurDetail result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<RapportEvenementMensuelSecteurDetail> update(List<RapportEvenementMensuelSecteurDetail> ts, boolean createIfNotExist) {
        List<RapportEvenementMensuelSecteurDetail> result = new ArrayList<>();
        if (ts != null) {
            for (RapportEvenementMensuelSecteurDetail t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    RapportEvenementMensuelSecteurDetail loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, RapportEvenementMensuelSecteurDetail t, RapportEvenementMensuelSecteurDetail loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public RapportEvenementMensuelSecteurDetail findByReferenceEntity(RapportEvenementMensuelSecteurDetail t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(RapportEvenementMensuelSecteurDetail t){
        if( t != null) {
            t.setRapportEvenementMensuelSecteur(rapportEvenementMensuelSecteurService.findOrSave(t.getRapportEvenementMensuelSecteur()));
            t.setTypeEvenement(typeEvenementService.findOrSave(t.getTypeEvenement()));
        }
    }



    public List<RapportEvenementMensuelSecteurDetail> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<RapportEvenementMensuelSecteurDetail>> getToBeSavedAndToBeDeleted(List<RapportEvenementMensuelSecteurDetail> oldList, List<RapportEvenementMensuelSecteurDetail> newList) {
        List<List<RapportEvenementMensuelSecteurDetail>> result = new ArrayList<>();
        List<RapportEvenementMensuelSecteurDetail> resultDelete = new ArrayList<>();
        List<RapportEvenementMensuelSecteurDetail> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<RapportEvenementMensuelSecteurDetail> oldList, List<RapportEvenementMensuelSecteurDetail> newList, List<RapportEvenementMensuelSecteurDetail> resultUpdateOrSave, List<RapportEvenementMensuelSecteurDetail> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                RapportEvenementMensuelSecteurDetail myOld = oldList.get(i);
                RapportEvenementMensuelSecteurDetail t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                RapportEvenementMensuelSecteurDetail myNew = newList.get(i);
                RapportEvenementMensuelSecteurDetail t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    @Autowired
    private TypeEvenementAdminService typeEvenementService ;
    @Autowired
    private RapportEvenementMensuelSecteurAdminService rapportEvenementMensuelSecteurService ;

    public RapportEvenementMensuelSecteurDetailAdminServiceImpl(RapportEvenementMensuelSecteurDetailDao dao) {
        this.dao = dao;
    }

    private RapportEvenementMensuelSecteurDetailDao dao;
}
