package ma.zyn.app.service.facade.admin.commun;

import java.util.List;
import ma.zyn.app.bean.core.commun.Vessel;
import ma.zyn.app.dao.criteria.core.commun.VesselCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface VesselAdminService {







	Vessel create(Vessel t);

    Vessel update(Vessel t);

    List<Vessel> update(List<Vessel> ts,boolean createIfNotExist);

    Vessel findById(Long id);

    Vessel findOrSave(Vessel t);

    Vessel findByReferenceEntity(Vessel t);

    Vessel findWithAssociatedLists(Long id);

    List<Vessel> findAllOptimized();

    List<Vessel> findAll();

    List<Vessel> findByCriteria(VesselCriteria criteria);

    List<Vessel> findPaginatedByCriteria(VesselCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(VesselCriteria criteria);

    List<Vessel> delete(List<Vessel> ts);

    boolean deleteById(Long id);

    List<List<Vessel>> getToBeSavedAndToBeDeleted(List<Vessel> oldList, List<Vessel> newList);

}
