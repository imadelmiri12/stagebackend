package ma.zyn.app.service.facade.collaborator.mensuel;

import java.util.List;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelTypeEvenement;
import ma.zyn.app.dao.criteria.core.mensuel.RapportEvenementMensuelTypeEvenementCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface RapportEvenementMensuelTypeEvenementCollaboratorService {



    List<RapportEvenementMensuelTypeEvenement> findByRapportEvenementMensuelId(Long id);
    int deleteByRapportEvenementMensuelId(Long id);
    long countByRapportEvenementMensuelCode(String code);
    List<RapportEvenementMensuelTypeEvenement> findByTypeEvenementCode(String code);
    List<RapportEvenementMensuelTypeEvenement> findByTypeEvenementId(Long id);
    int deleteByTypeEvenementId(Long id);
    int deleteByTypeEvenementCode(String code);
    long countByTypeEvenementCode(String code);




	RapportEvenementMensuelTypeEvenement create(RapportEvenementMensuelTypeEvenement t);

    RapportEvenementMensuelTypeEvenement update(RapportEvenementMensuelTypeEvenement t);

    List<RapportEvenementMensuelTypeEvenement> update(List<RapportEvenementMensuelTypeEvenement> ts,boolean createIfNotExist);

    RapportEvenementMensuelTypeEvenement findById(Long id);

    RapportEvenementMensuelTypeEvenement findOrSave(RapportEvenementMensuelTypeEvenement t);

    RapportEvenementMensuelTypeEvenement findByReferenceEntity(RapportEvenementMensuelTypeEvenement t);

    RapportEvenementMensuelTypeEvenement findWithAssociatedLists(Long id);

    List<RapportEvenementMensuelTypeEvenement> findAllOptimized();

    List<RapportEvenementMensuelTypeEvenement> findAll();

    List<RapportEvenementMensuelTypeEvenement> findByCriteria(RapportEvenementMensuelTypeEvenementCriteria criteria);

    List<RapportEvenementMensuelTypeEvenement> findPaginatedByCriteria(RapportEvenementMensuelTypeEvenementCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(RapportEvenementMensuelTypeEvenementCriteria criteria);

    List<RapportEvenementMensuelTypeEvenement> delete(List<RapportEvenementMensuelTypeEvenement> ts);

    boolean deleteById(Long id);

    List<List<RapportEvenementMensuelTypeEvenement>> getToBeSavedAndToBeDeleted(List<RapportEvenementMensuelTypeEvenement> oldList, List<RapportEvenementMensuelTypeEvenement> newList);

}
