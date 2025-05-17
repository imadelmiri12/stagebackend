package ma.zyn.app.service.impl.admin.mensuel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuel;
import ma.zyn.app.dao.criteria.core.mensuel.RapportEvenementMensuelCriteria;
import ma.zyn.app.dao.facade.core.mensuel.RapportEvenementMensuelDao;
import ma.zyn.app.dao.specification.core.mensuel.RapportEvenementMensuelSpecification;
import ma.zyn.app.service.facade.admin.mensuel.RapportEvenementMensuelAdminService;
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

import ma.zyn.app.service.facade.admin.mensuel.RapportEvenementMensuelTypeEvenementAdminService ;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelTypeEvenement ;
import ma.zyn.app.service.facade.admin.mensuel.RapportEvenementMensuelSecteurAdminService ;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelSecteur ;

import java.util.List;
@Service
public class RapportEvenementMensuelAdminServiceImpl implements RapportEvenementMensuelAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public RapportEvenementMensuel update(RapportEvenementMensuel t) {
        RapportEvenementMensuel loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{RapportEvenementMensuel.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public RapportEvenementMensuel findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public RapportEvenementMensuel findOrSave(RapportEvenementMensuel t) {
        if (t != null) {
            RapportEvenementMensuel result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<RapportEvenementMensuel> findAll() {
        return dao.findAll();
    }

    public List<RapportEvenementMensuel> findByCriteria(RapportEvenementMensuelCriteria criteria) {
        List<RapportEvenementMensuel> content = null;
        if (criteria != null) {
            RapportEvenementMensuelSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private RapportEvenementMensuelSpecification constructSpecification(RapportEvenementMensuelCriteria criteria) {
        RapportEvenementMensuelSpecification mySpecification =  (RapportEvenementMensuelSpecification) RefelexivityUtil.constructObjectUsingOneParam(RapportEvenementMensuelSpecification.class, criteria);
        return mySpecification;
    }

    public List<RapportEvenementMensuel> findPaginatedByCriteria(RapportEvenementMensuelCriteria criteria, int page, int pageSize, String order, String sortField) {
        RapportEvenementMensuelSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(RapportEvenementMensuelCriteria criteria) {
        RapportEvenementMensuelSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
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
        rapportEvenementMensuelSecteurService.deleteByRapportEvenementMensuelId(id);
        rapportEvenementMensuelTypeEvenementService.deleteByRapportEvenementMensuelId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<RapportEvenementMensuel> delete(List<RapportEvenementMensuel> list) {
		List<RapportEvenementMensuel> result = new ArrayList();
        if (list != null) {
            for (RapportEvenementMensuel t : list) {
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
    public RapportEvenementMensuel create(RapportEvenementMensuel t) {
        RapportEvenementMensuel loaded = findByReferenceEntity(t);
        RapportEvenementMensuel saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getRapportEvenementMensuelSecteurs() != null) {
                t.getRapportEvenementMensuelSecteurs().forEach(element-> {
                    element.setRapportEvenementMensuel(saved);
                    rapportEvenementMensuelSecteurService.create(element);
                });
            }
            if (t.getRapportEvenementMensuelTypeEvenements() != null) {
                t.getRapportEvenementMensuelTypeEvenements().forEach(element-> {
                    element.setRapportEvenementMensuel(saved);
                    rapportEvenementMensuelTypeEvenementService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public RapportEvenementMensuel findWithAssociatedLists(Long id){
        RapportEvenementMensuel result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setRapportEvenementMensuelSecteurs(rapportEvenementMensuelSecteurService.findByRapportEvenementMensuelId(id));
            result.setRapportEvenementMensuelTypeEvenements(rapportEvenementMensuelTypeEvenementService.findByRapportEvenementMensuelId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<RapportEvenementMensuel> update(List<RapportEvenementMensuel> ts, boolean createIfNotExist) {
        List<RapportEvenementMensuel> result = new ArrayList<>();
        if (ts != null) {
            for (RapportEvenementMensuel t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    RapportEvenementMensuel loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, RapportEvenementMensuel t, RapportEvenementMensuel loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(RapportEvenementMensuel rapportEvenementMensuel){
    if(rapportEvenementMensuel !=null && rapportEvenementMensuel.getId() != null){
        List<List<RapportEvenementMensuelSecteur>> resultRapportEvenementMensuelSecteurs= rapportEvenementMensuelSecteurService.getToBeSavedAndToBeDeleted(rapportEvenementMensuelSecteurService.findByRapportEvenementMensuelId(rapportEvenementMensuel.getId()),rapportEvenementMensuel.getRapportEvenementMensuelSecteurs());
            rapportEvenementMensuelSecteurService.delete(resultRapportEvenementMensuelSecteurs.get(1));
        emptyIfNull(resultRapportEvenementMensuelSecteurs.get(0)).forEach(e -> e.setRapportEvenementMensuel(rapportEvenementMensuel));
        rapportEvenementMensuelSecteurService.update(resultRapportEvenementMensuelSecteurs.get(0),true);
        List<List<RapportEvenementMensuelTypeEvenement>> resultRapportEvenementMensuelTypeEvenements= rapportEvenementMensuelTypeEvenementService.getToBeSavedAndToBeDeleted(rapportEvenementMensuelTypeEvenementService.findByRapportEvenementMensuelId(rapportEvenementMensuel.getId()),rapportEvenementMensuel.getRapportEvenementMensuelTypeEvenements());
            rapportEvenementMensuelTypeEvenementService.delete(resultRapportEvenementMensuelTypeEvenements.get(1));
        emptyIfNull(resultRapportEvenementMensuelTypeEvenements.get(0)).forEach(e -> e.setRapportEvenementMensuel(rapportEvenementMensuel));
        rapportEvenementMensuelTypeEvenementService.update(resultRapportEvenementMensuelTypeEvenements.get(0),true);
        }
    }








    public RapportEvenementMensuel findByReferenceEntity(RapportEvenementMensuel t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<RapportEvenementMensuel> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<RapportEvenementMensuel>> getToBeSavedAndToBeDeleted(List<RapportEvenementMensuel> oldList, List<RapportEvenementMensuel> newList) {
        List<List<RapportEvenementMensuel>> result = new ArrayList<>();
        List<RapportEvenementMensuel> resultDelete = new ArrayList<>();
        List<RapportEvenementMensuel> resultUpdateOrSave = new ArrayList<>();
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
    public RapportEvenementMensuel findByMoisAndAnnee(Integer mois, Integer annee) {
        return dao.findByMoisAndAnnee(mois,annee);
    }

    private void extractToBeSaveOrDelete(List<RapportEvenementMensuel> oldList, List<RapportEvenementMensuel> newList, List<RapportEvenementMensuel> resultUpdateOrSave, List<RapportEvenementMensuel> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                RapportEvenementMensuel myOld = oldList.get(i);
                RapportEvenementMensuel t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                RapportEvenementMensuel myNew = newList.get(i);
                RapportEvenementMensuel t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    @Autowired
    private RapportEvenementMensuelTypeEvenementAdminService rapportEvenementMensuelTypeEvenementService ;
    @Autowired
    private RapportEvenementMensuelSecteurAdminService rapportEvenementMensuelSecteurService ;

    public RapportEvenementMensuelAdminServiceImpl(RapportEvenementMensuelDao dao) {
        this.dao = dao;
    }

    private RapportEvenementMensuelDao dao;
}
