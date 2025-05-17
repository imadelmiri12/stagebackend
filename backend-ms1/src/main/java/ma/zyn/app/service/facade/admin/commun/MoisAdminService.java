package ma.zyn.app.service.facade.admin.commun;

import java.util.List;
import ma.zyn.app.bean.core.commun.Mois;
import ma.zyn.app.dao.criteria.core.commun.MoisCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface MoisAdminService {



    List<Mois> findByTrimestreCode(String code);
    List<Mois> findByTrimestreId(Long id);
    int deleteByTrimestreId(Long id);
    int deleteByTrimestreCode(String code);
    long countByTrimestreCode(String code);




	Mois create(Mois t);

    Mois update(Mois t);

    List<Mois> update(List<Mois> ts,boolean createIfNotExist);

    Mois findById(Long id);

    Mois findOrSave(Mois t);

    Mois findByReferenceEntity(Mois t);

    Mois findWithAssociatedLists(Long id);

    List<Mois> findAllOptimized();

    List<Mois> findAll();

    List<Mois> findByCriteria(MoisCriteria criteria);

    List<Mois> findPaginatedByCriteria(MoisCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(MoisCriteria criteria);

    List<Mois> delete(List<Mois> ts);

    boolean deleteById(Long id);

    List<List<Mois>> getToBeSavedAndToBeDeleted(List<Mois> oldList, List<Mois> newList);

}
