package ma.zyn.app.service.impl.admin.mensuel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelTypeEvenement;
import ma.zyn.app.dao.criteria.core.mensuel.RapportEvenementMensuelTypeEvenementCriteria;
import ma.zyn.app.dao.facade.core.mensuel.RapportEvenementMensuelTypeEvenementDao;
import ma.zyn.app.dao.specification.core.mensuel.RapportEvenementMensuelTypeEvenementSpecification;
import ma.zyn.app.service.facade.admin.mensuel.RapportEvenementMensuelTypeEvenementAdminService;
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

import ma.zyn.app.service.facade.admin.mensuel.RapportEvenementMensuelAdminService ;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuel ;
import ma.zyn.app.service.facade.admin.commun.TypeEvenementAdminService ;
import ma.zyn.app.bean.core.commun.TypeEvenement ;

import java.util.List;
@Service
public class RapportEvenementMensuelTypeEvenementAdminServiceImpl implements RapportEvenementMensuelTypeEvenementAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public RapportEvenementMensuelTypeEvenement update(RapportEvenementMensuelTypeEvenement t) {
        RapportEvenementMensuelTypeEvenement loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{RapportEvenementMensuelTypeEvenement.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public RapportEvenementMensuelTypeEvenement findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public RapportEvenementMensuelTypeEvenement findOrSave(RapportEvenementMensuelTypeEvenement t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            RapportEvenementMensuelTypeEvenement result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<RapportEvenementMensuelTypeEvenement> findAll() {
        return dao.findAll();
    }

    public List<RapportEvenementMensuelTypeEvenement> findByCriteria(RapportEvenementMensuelTypeEvenementCriteria criteria) {
        List<RapportEvenementMensuelTypeEvenement> content = null;
        if (criteria != null) {
            RapportEvenementMensuelTypeEvenementSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private RapportEvenementMensuelTypeEvenementSpecification constructSpecification(RapportEvenementMensuelTypeEvenementCriteria criteria) {
        RapportEvenementMensuelTypeEvenementSpecification mySpecification =  (RapportEvenementMensuelTypeEvenementSpecification) RefelexivityUtil.constructObjectUsingOneParam(RapportEvenementMensuelTypeEvenementSpecification.class, criteria);
        return mySpecification;
    }

    public List<RapportEvenementMensuelTypeEvenement> findPaginatedByCriteria(RapportEvenementMensuelTypeEvenementCriteria criteria, int page, int pageSize, String order, String sortField) {
        RapportEvenementMensuelTypeEvenementSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(RapportEvenementMensuelTypeEvenementCriteria criteria) {
        RapportEvenementMensuelTypeEvenementSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<RapportEvenementMensuelTypeEvenement> findByRapportEvenementMensuelId(Long id){
        return dao.findByRapportEvenementMensuelId(id);
    }
    public int deleteByRapportEvenementMensuelId(Long id){
        return dao.deleteByRapportEvenementMensuelId(id);
    }
    public long countByRapportEvenementMensuelCode(String code){
        return dao.countByRapportEvenementMensuelCode(code);
    }
    public List<RapportEvenementMensuelTypeEvenement> findByTypeEvenementCode(String code){
        return dao.findByTypeEvenementCode(code);
    }
    public List<RapportEvenementMensuelTypeEvenement> findByTypeEvenementId(Long id){
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
    public List<RapportEvenementMensuelTypeEvenement> delete(List<RapportEvenementMensuelTypeEvenement> list) {
		List<RapportEvenementMensuelTypeEvenement> result = new ArrayList();
        if (list != null) {
            for (RapportEvenementMensuelTypeEvenement t : list) {
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
    public RapportEvenementMensuelTypeEvenement create(RapportEvenementMensuelTypeEvenement t) {
        RapportEvenementMensuelTypeEvenement loaded = findByReferenceEntity(t);
        RapportEvenementMensuelTypeEvenement saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public RapportEvenementMensuelTypeEvenement findWithAssociatedLists(Long id){
        RapportEvenementMensuelTypeEvenement result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<RapportEvenementMensuelTypeEvenement> update(List<RapportEvenementMensuelTypeEvenement> ts, boolean createIfNotExist) {
        List<RapportEvenementMensuelTypeEvenement> result = new ArrayList<>();
        if (ts != null) {
            for (RapportEvenementMensuelTypeEvenement t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    RapportEvenementMensuelTypeEvenement loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, RapportEvenementMensuelTypeEvenement t, RapportEvenementMensuelTypeEvenement loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public RapportEvenementMensuelTypeEvenement findByReferenceEntity(RapportEvenementMensuelTypeEvenement t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(RapportEvenementMensuelTypeEvenement t){
        if( t != null) {
            t.setRapportEvenementMensuel(rapportEvenementMensuelService.findOrSave(t.getRapportEvenementMensuel()));
            t.setTypeEvenement(typeEvenementService.findOrSave(t.getTypeEvenement()));
        }
    }



    public List<RapportEvenementMensuelTypeEvenement> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<RapportEvenementMensuelTypeEvenement>> getToBeSavedAndToBeDeleted(List<RapportEvenementMensuelTypeEvenement> oldList, List<RapportEvenementMensuelTypeEvenement> newList) {
        List<List<RapportEvenementMensuelTypeEvenement>> result = new ArrayList<>();
        List<RapportEvenementMensuelTypeEvenement> resultDelete = new ArrayList<>();
        List<RapportEvenementMensuelTypeEvenement> resultUpdateOrSave = new ArrayList<>();
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

    @Override
    public RapportEvenementMensuelTypeEvenement findByRapportEvenementMensuelIdAndTypeEvenementId(Long idRapportEvenementMensuel, Long idTypeEvenement) {
        return dao.findByRapportEvenementMensuelIdAndTypeEvenementId(idRapportEvenementMensuel,idTypeEvenement);
    }

    private void extractToBeSaveOrDelete(List<RapportEvenementMensuelTypeEvenement> oldList, List<RapportEvenementMensuelTypeEvenement> newList, List<RapportEvenementMensuelTypeEvenement> resultUpdateOrSave, List<RapportEvenementMensuelTypeEvenement> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                RapportEvenementMensuelTypeEvenement myOld = oldList.get(i);
                RapportEvenementMensuelTypeEvenement t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                RapportEvenementMensuelTypeEvenement myNew = newList.get(i);
                RapportEvenementMensuelTypeEvenement t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    @Autowired
    private RapportEvenementMensuelAdminService rapportEvenementMensuelService ;
    @Autowired
    private TypeEvenementAdminService typeEvenementService ;

    public RapportEvenementMensuelTypeEvenementAdminServiceImpl(RapportEvenementMensuelTypeEvenementDao dao) {
        this.dao = dao;
    }

    private RapportEvenementMensuelTypeEvenementDao dao;
}
