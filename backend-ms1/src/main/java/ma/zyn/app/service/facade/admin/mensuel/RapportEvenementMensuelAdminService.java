package ma.zyn.app.service.facade.admin.mensuel;

import java.util.List;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuel;
import ma.zyn.app.dao.criteria.core.mensuel.RapportEvenementMensuelCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface RapportEvenementMensuelAdminService {







	RapportEvenementMensuel create(RapportEvenementMensuel t);

    RapportEvenementMensuel update(RapportEvenementMensuel t);

    List<RapportEvenementMensuel> update(List<RapportEvenementMensuel> ts,boolean createIfNotExist);

    RapportEvenementMensuel findById(Long id);

    RapportEvenementMensuel findOrSave(RapportEvenementMensuel t);

    RapportEvenementMensuel findByReferenceEntity(RapportEvenementMensuel t);

    RapportEvenementMensuel findWithAssociatedLists(Long id);

    List<RapportEvenementMensuel> findAllOptimized();

    List<RapportEvenementMensuel> findAll();

    List<RapportEvenementMensuel> findByCriteria(RapportEvenementMensuelCriteria criteria);

    List<RapportEvenementMensuel> findPaginatedByCriteria(RapportEvenementMensuelCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(RapportEvenementMensuelCriteria criteria);

    List<RapportEvenementMensuel> delete(List<RapportEvenementMensuel> ts);

    boolean deleteById(Long id);

    List<List<RapportEvenementMensuel>> getToBeSavedAndToBeDeleted(List<RapportEvenementMensuel> oldList, List<RapportEvenementMensuel> newList);

    RapportEvenementMensuel findByMoisAndAnnee(Integer mois, Integer annee);
}
