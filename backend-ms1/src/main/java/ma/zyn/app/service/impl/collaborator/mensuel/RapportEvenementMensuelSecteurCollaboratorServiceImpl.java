package ma.zyn.app.service.impl.collaborator.mensuel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelSecteur;
import ma.zyn.app.dao.criteria.core.mensuel.RapportEvenementMensuelSecteurCriteria;
import ma.zyn.app.dao.facade.core.mensuel.RapportEvenementMensuelSecteurDao;
import ma.zyn.app.dao.specification.core.mensuel.RapportEvenementMensuelSecteurSpecification;
import ma.zyn.app.service.facade.collaborator.mensuel.RapportEvenementMensuelSecteurCollaboratorService;
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

import ma.zyn.app.service.facade.collaborator.mensuel.RapportEvenementMensuelCollaboratorService ;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuel ;
import ma.zyn.app.service.facade.collaborator.mensuel.RapportEvenementMensuelSecteurDetailCollaboratorService ;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelSecteurDetail ;
import ma.zyn.app.service.facade.collaborator.commun.SecteurCollaboratorService ;
import ma.zyn.app.bean.core.commun.Secteur ;

import java.util.List;
@Service
public class RapportEvenementMensuelSecteurCollaboratorServiceImpl implements RapportEvenementMensuelSecteurCollaboratorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public RapportEvenementMensuelSecteur update(RapportEvenementMensuelSecteur t) {
        RapportEvenementMensuelSecteur loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{RapportEvenementMensuelSecteur.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public RapportEvenementMensuelSecteur findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public RapportEvenementMensuelSecteur findOrSave(RapportEvenementMensuelSecteur t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            RapportEvenementMensuelSecteur result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<RapportEvenementMensuelSecteur> findAll() {
        return dao.findAll();
    }

    public List<RapportEvenementMensuelSecteur> findByCriteria(RapportEvenementMensuelSecteurCriteria criteria) {
        List<RapportEvenementMensuelSecteur> content = null;
        if (criteria != null) {
            RapportEvenementMensuelSecteurSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private RapportEvenementMensuelSecteurSpecification constructSpecification(RapportEvenementMensuelSecteurCriteria criteria) {
        RapportEvenementMensuelSecteurSpecification mySpecification =  (RapportEvenementMensuelSecteurSpecification) RefelexivityUtil.constructObjectUsingOneParam(RapportEvenementMensuelSecteurSpecification.class, criteria);
        return mySpecification;
    }

    public List<RapportEvenementMensuelSecteur> findPaginatedByCriteria(RapportEvenementMensuelSecteurCriteria criteria, int page, int pageSize, String order, String sortField) {
        RapportEvenementMensuelSecteurSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(RapportEvenementMensuelSecteurCriteria criteria) {
        RapportEvenementMensuelSecteurSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<RapportEvenementMensuelSecteur> findByRapportEvenementMensuelId(Long id){
        return dao.findByRapportEvenementMensuelId(id);
    }
    public int deleteByRapportEvenementMensuelId(Long id){
        return dao.deleteByRapportEvenementMensuelId(id);
    }
    public long countByRapportEvenementMensuelCode(String code){
        return dao.countByRapportEvenementMensuelCode(code);
    }
    public List<RapportEvenementMensuelSecteur> findBySecteurCode(String code){
        return dao.findBySecteurCode(code);
    }
    public List<RapportEvenementMensuelSecteur> findBySecteurId(Long id){
        return dao.findBySecteurId(id);
    }
    public int deleteBySecteurCode(String code){
        return dao.deleteBySecteurCode(code);
    }
    public int deleteBySecteurId(Long id){
        return dao.deleteBySecteurId(id);
    }
    public long countBySecteurCode(String code){
        return dao.countBySecteurCode(code);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            deleteAssociatedLists(id);
            dao.deleteById(id);
        }
        return condition;
    }

    public void deleteAssociatedLists(Long id) {
        rapportEvenementMensuelSecteurDetailService.deleteByRapportEvenementMensuelSecteurId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<RapportEvenementMensuelSecteur> delete(List<RapportEvenementMensuelSecteur> list) {
		List<RapportEvenementMensuelSecteur> result = new ArrayList();
        if (list != null) {
            for (RapportEvenementMensuelSecteur t : list) {
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
    public RapportEvenementMensuelSecteur create(RapportEvenementMensuelSecteur t) {
        RapportEvenementMensuelSecteur loaded = findByReferenceEntity(t);
        RapportEvenementMensuelSecteur saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getRapportEvenementMensuelSecteurDetails() != null) {
                t.getRapportEvenementMensuelSecteurDetails().forEach(element-> {
                    element.setRapportEvenementMensuelSecteur(saved);
                    rapportEvenementMensuelSecteurDetailService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public RapportEvenementMensuelSecteur findWithAssociatedLists(Long id){
        RapportEvenementMensuelSecteur result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setRapportEvenementMensuelSecteurDetails(rapportEvenementMensuelSecteurDetailService.findByRapportEvenementMensuelSecteurId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<RapportEvenementMensuelSecteur> update(List<RapportEvenementMensuelSecteur> ts, boolean createIfNotExist) {
        List<RapportEvenementMensuelSecteur> result = new ArrayList<>();
        if (ts != null) {
            for (RapportEvenementMensuelSecteur t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    RapportEvenementMensuelSecteur loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, RapportEvenementMensuelSecteur t, RapportEvenementMensuelSecteur loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(RapportEvenementMensuelSecteur rapportEvenementMensuelSecteur){
    if(rapportEvenementMensuelSecteur !=null && rapportEvenementMensuelSecteur.getId() != null){
        List<List<RapportEvenementMensuelSecteurDetail>> resultRapportEvenementMensuelSecteurDetails= rapportEvenementMensuelSecteurDetailService.getToBeSavedAndToBeDeleted(rapportEvenementMensuelSecteurDetailService.findByRapportEvenementMensuelSecteurId(rapportEvenementMensuelSecteur.getId()),rapportEvenementMensuelSecteur.getRapportEvenementMensuelSecteurDetails());
            rapportEvenementMensuelSecteurDetailService.delete(resultRapportEvenementMensuelSecteurDetails.get(1));
        emptyIfNull(resultRapportEvenementMensuelSecteurDetails.get(0)).forEach(e -> e.setRapportEvenementMensuelSecteur(rapportEvenementMensuelSecteur));
        rapportEvenementMensuelSecteurDetailService.update(resultRapportEvenementMensuelSecteurDetails.get(0),true);
        }
    }








    public RapportEvenementMensuelSecteur findByReferenceEntity(RapportEvenementMensuelSecteur t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(RapportEvenementMensuelSecteur t){
        if( t != null) {
        }
    }



    public List<RapportEvenementMensuelSecteur> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<RapportEvenementMensuelSecteur>> getToBeSavedAndToBeDeleted(List<RapportEvenementMensuelSecteur> oldList, List<RapportEvenementMensuelSecteur> newList) {
        List<List<RapportEvenementMensuelSecteur>> result = new ArrayList<>();
        List<RapportEvenementMensuelSecteur> resultDelete = new ArrayList<>();
        List<RapportEvenementMensuelSecteur> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<RapportEvenementMensuelSecteur> oldList, List<RapportEvenementMensuelSecteur> newList, List<RapportEvenementMensuelSecteur> resultUpdateOrSave, List<RapportEvenementMensuelSecteur> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                RapportEvenementMensuelSecteur myOld = oldList.get(i);
                RapportEvenementMensuelSecteur t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                RapportEvenementMensuelSecteur myNew = newList.get(i);
                RapportEvenementMensuelSecteur t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    @Autowired
    private RapportEvenementMensuelCollaboratorService rapportEvenementMensuelService ;
    @Autowired
    private RapportEvenementMensuelSecteurDetailCollaboratorService rapportEvenementMensuelSecteurDetailService ;
    @Autowired
    private SecteurCollaboratorService secteurService ;

    public RapportEvenementMensuelSecteurCollaboratorServiceImpl(RapportEvenementMensuelSecteurDao dao) {
        this.dao = dao;
    }

    private RapportEvenementMensuelSecteurDao dao;
}
