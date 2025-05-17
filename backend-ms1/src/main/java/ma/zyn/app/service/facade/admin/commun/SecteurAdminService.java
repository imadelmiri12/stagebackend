package ma.zyn.app.service.facade.admin.commun;

import java.util.List;
import ma.zyn.app.bean.core.commun.Secteur;
import ma.zyn.app.dao.criteria.core.commun.SecteurCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface SecteurAdminService {







	Secteur create(Secteur t);

    Secteur update(Secteur t);

    List<Secteur> update(List<Secteur> ts,boolean createIfNotExist);

    Secteur findById(Long id);

    Secteur findOrSave(Secteur t);

    Secteur findByReferenceEntity(Secteur t);

    Secteur findWithAssociatedLists(Long id);

    List<Secteur> findAllOptimized();

    List<Secteur> findAll();

    List<Secteur> findByCriteria(SecteurCriteria criteria);

    List<Secteur> findPaginatedByCriteria(SecteurCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(SecteurCriteria criteria);

    List<Secteur> delete(List<Secteur> ts);

    boolean deleteById(Long id);

    List<List<Secteur>> getToBeSavedAndToBeDeleted(List<Secteur> oldList, List<Secteur> newList);

}
