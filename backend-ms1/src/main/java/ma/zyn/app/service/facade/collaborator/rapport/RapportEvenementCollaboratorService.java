package ma.zyn.app.service.facade.collaborator.rapport;

import java.util.List;
import ma.zyn.app.bean.core.rapport.RapportEvenement;
import ma.zyn.app.dao.criteria.core.rapport.RapportEvenementCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface RapportEvenementCollaboratorService {



    List<RapportEvenement> findByPortCode(String code);
    List<RapportEvenement> findByPortId(Long id);
    int deleteByPortId(Long id);
    int deleteByPortCode(String code);
    long countByPortCode(String code);
    List<RapportEvenement> findByTypeEvenementCode(String code);
    List<RapportEvenement> findByTypeEvenementId(Long id);
    int deleteByTypeEvenementId(Long id);
    int deleteByTypeEvenementCode(String code);
    long countByTypeEvenementCode(String code);
    List<RapportEvenement> findByCollaboratorId(Long id);
    int deleteByCollaboratorId(Long id);
    long countByCollaboratorEmail(String email);
    List<RapportEvenement> findByVesselCode(String code);
    List<RapportEvenement> findByVesselId(Long id);
    int deleteByVesselId(Long id);
    int deleteByVesselCode(String code);
    long countByVesselCode(String code);
    List<RapportEvenement> findByTypeRapportEvenementCode(String code);
    List<RapportEvenement> findByTypeRapportEvenementId(Long id);
    int deleteByTypeRapportEvenementId(Long id);
    int deleteByTypeRapportEvenementCode(String code);
    long countByTypeRapportEvenementCode(String code);
    List<RapportEvenement> findBySecteurCode(String code);
    List<RapportEvenement> findBySecteurId(Long id);
    int deleteBySecteurId(Long id);
    int deleteBySecteurCode(String code);
    long countBySecteurCode(String code);
    List<RapportEvenement> findByJournalAmelioarationId(Long id);
    int deleteByJournalAmelioarationId(Long id);
    long countByJournalAmelioarationId(Long id);




	RapportEvenement create(RapportEvenement t);

    RapportEvenement update(RapportEvenement t);

    List<RapportEvenement> update(List<RapportEvenement> ts,boolean createIfNotExist);

    RapportEvenement findById(Long id);

    RapportEvenement findOrSave(RapportEvenement t);

    RapportEvenement findByReferenceEntity(RapportEvenement t);

    RapportEvenement findWithAssociatedLists(Long id);

    List<RapportEvenement> findAllOptimized();

    List<RapportEvenement> findAll();

    List<RapportEvenement> findByCriteria(RapportEvenementCriteria criteria);

    List<RapportEvenement> findPaginatedByCriteria(RapportEvenementCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(RapportEvenementCriteria criteria);

    List<RapportEvenement> delete(List<RapportEvenement> ts);

    boolean deleteById(Long id);

    List<List<RapportEvenement>> getToBeSavedAndToBeDeleted(List<RapportEvenement> oldList, List<RapportEvenement> newList);

}
