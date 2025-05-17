package ma.zyn.app.service.impl.admin.rapport;


import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuel;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelSecteur;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelTypeEvenement;
import ma.zyn.app.service.facade.admin.mensuel.RapportEvenementMensuelAdminService;
import ma.zyn.app.service.facade.admin.mensuel.RapportEvenementMensuelSecteurAdminService;
import ma.zyn.app.service.facade.admin.mensuel.RapportEvenementMensuelTypeEvenementAdminService;
import ma.zyn.app.service.impl.admin.mensuel.RapportEvenementMensuelTypeEvenementAdminServiceImpl;
import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.rapport.RapportEvenement;
import ma.zyn.app.dao.criteria.core.rapport.RapportEvenementCriteria;
import ma.zyn.app.dao.facade.core.rapport.RapportEvenementDao;
import ma.zyn.app.dao.specification.core.rapport.RapportEvenementSpecification;
import ma.zyn.app.service.facade.admin.rapport.RapportEvenementAdminService;
import ma.zyn.app.zynerator.service.AbstractServiceImpl;

import static ma.zyn.app.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zyn.app.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zyn.app.service.facade.admin.actor.CollaboratorAdminService;
import ma.zyn.app.bean.core.actor.Collaborator;
import ma.zyn.app.service.facade.admin.commun.TypeRapportEvenementAdminService;
import ma.zyn.app.bean.core.commun.TypeRapportEvenement;
import ma.zyn.app.service.facade.admin.commun.VesselAdminService;
import ma.zyn.app.bean.core.commun.Vessel;
import ma.zyn.app.service.facade.admin.commun.PortAdminService;
import ma.zyn.app.bean.core.commun.Port;
import ma.zyn.app.service.facade.admin.commun.SecteurAdminService;
import ma.zyn.app.bean.core.commun.Secteur;
import ma.zyn.app.service.facade.admin.commun.TypeEvenementAdminService;
import ma.zyn.app.bean.core.commun.TypeEvenement;
import ma.zyn.app.service.facade.admin.journal.JournalAmelioarationAdminService;
import ma.zyn.app.bean.core.journal.JournalAmelioaration;

import java.util.List;
import java.util.Random;

@Service
public class RapportEvenementAdminServiceImpl implements RapportEvenementAdminService {

    @Autowired
    private RapportEvenementMensuelAdminService rapportEvenementMensuelAdminService;
    @Autowired
    private RapportEvenementMensuelTypeEvenementAdminServiceImpl rapportEvenementMensuelTypeEvenementAdminService;
    @Autowired
    private RapportEvenementMensuelSecteurAdminService rapportEvenementMensuelSecteurAdminService;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public RapportEvenement update(RapportEvenement t) {
        RapportEvenement loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{RapportEvenement.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public RapportEvenement findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public RapportEvenement findOrSave(RapportEvenement t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            RapportEvenement result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<RapportEvenement> findAll() {
        return dao.findAll();
    }

    public List<RapportEvenement> findByCriteria(RapportEvenementCriteria criteria) {
        List<RapportEvenement> content = null;
        if (criteria != null) {
            RapportEvenementSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private RapportEvenementSpecification constructSpecification(RapportEvenementCriteria criteria) {
        RapportEvenementSpecification mySpecification = (RapportEvenementSpecification) RefelexivityUtil.constructObjectUsingOneParam(RapportEvenementSpecification.class, criteria);
        return mySpecification;
    }

    public List<RapportEvenement> findPaginatedByCriteria(RapportEvenementCriteria criteria, int page, int pageSize, String order, String sortField) {
        RapportEvenementSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(RapportEvenementCriteria criteria) {
        RapportEvenementSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<RapportEvenement> findByPortCode(String code) {
        return dao.findByPortCode(code);
    }

    public List<RapportEvenement> findByPortId(Long id) {
        return dao.findByPortId(id);
    }

    public int deleteByPortCode(String code) {
        return dao.deleteByPortCode(code);
    }

    public int deleteByPortId(Long id) {
        return dao.deleteByPortId(id);
    }

    public long countByPortCode(String code) {
        return dao.countByPortCode(code);
    }

    public List<RapportEvenement> findByTypeEvenementCode(String code) {
        return dao.findByTypeEvenementCode(code);
    }

    public List<RapportEvenement> findByTypeEvenementId(Long id) {
        return dao.findByTypeEvenementId(id);
    }

    public int deleteByTypeEvenementCode(String code) {
        return dao.deleteByTypeEvenementCode(code);
    }

    public int deleteByTypeEvenementId(Long id) {
        return dao.deleteByTypeEvenementId(id);
    }

    public long countByTypeEvenementCode(String code) {
        return dao.countByTypeEvenementCode(code);
    }

    public List<RapportEvenement> findByCollaboratorId(Long id) {
        return dao.findByCollaboratorId(id);
    }

    public int deleteByCollaboratorId(Long id) {
        return dao.deleteByCollaboratorId(id);
    }

    public long countByCollaboratorEmail(String email) {
        return dao.countByCollaboratorEmail(email);
    }

    public List<RapportEvenement> findByVesselCode(String code) {
        return dao.findByVesselCode(code);
    }

    public List<RapportEvenement> findByVesselId(Long id) {
        return dao.findByVesselId(id);
    }

    public int deleteByVesselCode(String code) {
        return dao.deleteByVesselCode(code);
    }

    public int deleteByVesselId(Long id) {
        return dao.deleteByVesselId(id);
    }

    public long countByVesselCode(String code) {
        return dao.countByVesselCode(code);
    }

    public List<RapportEvenement> findByTypeRapportEvenementCode(String code) {
        return dao.findByTypeRapportEvenementCode(code);
    }

    public List<RapportEvenement> findByTypeRapportEvenementId(Long id) {
        return dao.findByTypeRapportEvenementId(id);
    }

    public int deleteByTypeRapportEvenementCode(String code) {
        return dao.deleteByTypeRapportEvenementCode(code);
    }

    public int deleteByTypeRapportEvenementId(Long id) {
        return dao.deleteByTypeRapportEvenementId(id);
    }

    public long countByTypeRapportEvenementCode(String code) {
        return dao.countByTypeRapportEvenementCode(code);
    }

    public List<RapportEvenement> findBySecteurCode(String code) {
        return dao.findBySecteurCode(code);
    }

    public List<RapportEvenement> findBySecteurId(Long id) {
        return dao.findBySecteurId(id);
    }

    public int deleteBySecteurCode(String code) {
        return dao.deleteBySecteurCode(code);
    }

    public int deleteBySecteurId(Long id) {
        return dao.deleteBySecteurId(id);
    }

    public long countBySecteurCode(String code) {
        return dao.countBySecteurCode(code);
    }

    public List<RapportEvenement> findByJournalAmelioarationId(Long id) {
        return dao.findByJournalAmelioarationId(id);
    }

    public int deleteByJournalAmelioarationId(Long id) {
        return dao.deleteByJournalAmelioarationId(id);
    }

    public long countByJournalAmelioarationId(Long id) {
        return dao.countByJournalAmelioarationId(id);
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
    public List<RapportEvenement> delete(List<RapportEvenement> list) {
        List<RapportEvenement> result = new ArrayList();
        if (list != null) {
            for (RapportEvenement t : list) {
                if (dao.findById(t.getId()).isEmpty()) {
                    result.add(t);
                } else {
                    dao.deleteById(t.getId());
                }
            }
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public RapportEvenement create(RapportEvenement t) {
        init(t);
        RapportEvenement loaded = findByReferenceEntity(t);
        RapportEvenement saved;
        if (loaded == null) {
            saved = dao.save(t);
            RapportEvenementMensuel loadedRapportEvenementMensuel = rapportEvenementMensuelAdminService.findByMoisAndAnnee(t.getMois(), t.getAnnee());
            if (loadedRapportEvenementMensuel == null) {
                RapportEvenementMensuel rapportEvenementMensuel = new RapportEvenementMensuel();
                rapportEvenementMensuel.setMois(t.getMois());
                rapportEvenementMensuel.setAnnee(t.getAnnee());
                rapportEvenementMensuel.setTotal(BigDecimal.ONE);

                List<TypeEvenement> typeEvenements = typeEvenementService.findAll();
                rapportEvenementMensuel.setRapportEvenementMensuelTypeEvenements(new ArrayList<>());

                for (TypeEvenement typeEvenement : typeEvenements) {
                    if (typeEvenement.getCode().equals(t.getTypeEvenement().getCode()))
                        rapportEvenementMensuel.getRapportEvenementMensuelTypeEvenements().add(new RapportEvenementMensuelTypeEvenement(BigDecimal.ONE, typeEvenement));
                    else
                        rapportEvenementMensuel.getRapportEvenementMensuelTypeEvenements().add(new RapportEvenementMensuelTypeEvenement(BigDecimal.ZERO, typeEvenement));

                }

                rapportEvenementMensuel.setRapportEvenementMensuelSecteurs(new ArrayList<>());
                List<Secteur> secteurs = secteurService.findAll();
                for (Secteur secteur : secteurs) {
                    if (secteur.getCode().equals(t.getSecteur().getCode()))
                        rapportEvenementMensuel.getRapportEvenementMensuelSecteurs().add(new RapportEvenementMensuelSecteur(BigDecimal.ONE,secteur));
                    else
                        rapportEvenementMensuel.getRapportEvenementMensuelSecteurs().add(new RapportEvenementMensuelSecteur(BigDecimal.ZERO, secteur));

                }
                rapportEvenementMensuelAdminService.create(rapportEvenementMensuel);
            } else {
                loadedRapportEvenementMensuel.setTotal(loadedRapportEvenementMensuel.getTotal().add(BigDecimal.ONE));

                RapportEvenementMensuelTypeEvenement rapportEvenementMensuelTypeEvenement = rapportEvenementMensuelTypeEvenementAdminService.findByRapportEvenementMensuelIdAndTypeEvenementId(loadedRapportEvenementMensuel.getId(), t.getTypeEvenement().getId());
                if (rapportEvenementMensuelTypeEvenement != null) {
                    rapportEvenementMensuelTypeEvenement.setTotal(rapportEvenementMensuelTypeEvenement.getTotal().add(BigDecimal.ONE));
                    rapportEvenementMensuelTypeEvenementAdminService.update(rapportEvenementMensuelTypeEvenement);
                }
                RapportEvenementMensuelSecteur rapportEvenementMensuelSecteur = rapportEvenementMensuelSecteurAdminService.findByRapportEvenementMensuelIdAndSecteurId(loadedRapportEvenementMensuel.getId(), t.getSecteur().getId());
                if (rapportEvenementMensuelSecteur != null) {
                    rapportEvenementMensuelSecteur.setTotal(rapportEvenementMensuelSecteur.getTotal().add(BigDecimal.ONE));
                    rapportEvenementMensuelSecteurAdminService.update(rapportEvenementMensuelSecteur);
                }


            }

        } else {
            saved = null;
        }
        return saved;
    }

    public void init(RapportEvenement t) {
        t.setJournalAmelioaration(null);
        t.setDateSoumission(LocalDateTime.now());
        t.setCode(t.getTypeEvenement().getCode() + "-" + t.getSecteur().getCode() + "-" + LocalDateTime.now().toString());
        t.setCollaborator(t.getCollaborator());
    }

    public RapportEvenement findWithAssociatedLists(Long id) {
        RapportEvenement result = dao.findById(id).orElse(null);
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<RapportEvenement> update(List<RapportEvenement> ts, boolean createIfNotExist) {
        List<RapportEvenement> result = new ArrayList<>();
        if (ts != null) {
            for (RapportEvenement t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    RapportEvenement loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, RapportEvenement t, RapportEvenement loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }


    public RapportEvenement findByReferenceEntity(RapportEvenement t) {
        return t == null ? null : dao.findByCode(t.getCode());
    }

    public void findOrSaveAssociatedObject(RapportEvenement t) {
        if (t != null) {
            t.setPort(portService.findOrSave(t.getPort()));
            t.setTypeEvenement(typeEvenementService.findOrSave(t.getTypeEvenement()));
            t.setCollaborator(collaboratorService.findOrSave(t.getCollaborator()));
            t.setVessel(vesselService.findOrSave(t.getVessel()));
            t.setTypeRapportEvenement(typeRapportEvenementService.findOrSave(t.getTypeRapportEvenement()));
            t.setSecteur(secteurService.findOrSave(t.getSecteur()));
            t.setJournalAmelioaration(journalAmelioarationService.findOrSave(t.getJournalAmelioaration()));
        }
    }


    public List<RapportEvenement> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<RapportEvenement>> getToBeSavedAndToBeDeleted(List<RapportEvenement> oldList, List<RapportEvenement> newList) {
        List<List<RapportEvenement>> result = new ArrayList<>();
        List<RapportEvenement> resultDelete = new ArrayList<>();
        List<RapportEvenement> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<RapportEvenement> oldList, List<RapportEvenement> newList, List<RapportEvenement> resultUpdateOrSave, List<RapportEvenement> resultDelete) {
        for (int i = 0; i < oldList.size(); i++) {
            RapportEvenement myOld = oldList.get(i);
            RapportEvenement t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
            if (t != null) {
                resultUpdateOrSave.add(t); // update
            } else {
                resultDelete.add(myOld);
            }
        }
        for (int i = 0; i < newList.size(); i++) {
            RapportEvenement myNew = newList.get(i);
            RapportEvenement t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
            if (t == null) {
                resultUpdateOrSave.add(myNew); // create
            }
        }
    }


    @Autowired
    private CollaboratorAdminService collaboratorService;
    @Autowired
    private TypeRapportEvenementAdminService typeRapportEvenementService;
    @Autowired
    private VesselAdminService vesselService;
    @Autowired
    private PortAdminService portService;
    @Autowired
    private SecteurAdminService secteurService;
    @Autowired
    private TypeEvenementAdminService typeEvenementService;
    @Autowired
    private JournalAmelioarationAdminService journalAmelioarationService;

    public RapportEvenementAdminServiceImpl(RapportEvenementDao dao) {
        this.dao = dao;
    }

    private RapportEvenementDao dao;
}
