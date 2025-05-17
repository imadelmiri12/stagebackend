package ma.zyn.app.service.facade.collaborator.commun;

import java.util.List;
import ma.zyn.app.bean.core.commun.TypeVessel;
import ma.zyn.app.dao.criteria.core.commun.TypeVesselCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface TypeVesselCollaboratorService {







	TypeVessel create(TypeVessel t);

    TypeVessel update(TypeVessel t);

    List<TypeVessel> update(List<TypeVessel> ts,boolean createIfNotExist);

    TypeVessel findById(Long id);

    TypeVessel findOrSave(TypeVessel t);

    TypeVessel findByReferenceEntity(TypeVessel t);

    TypeVessel findWithAssociatedLists(Long id);

    List<TypeVessel> findAllOptimized();

    List<TypeVessel> findAll();

    List<TypeVessel> findByCriteria(TypeVesselCriteria criteria);

    List<TypeVessel> findPaginatedByCriteria(TypeVesselCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TypeVesselCriteria criteria);

    List<TypeVessel> delete(List<TypeVessel> ts);

    boolean deleteById(Long id);

    List<List<TypeVessel>> getToBeSavedAndToBeDeleted(List<TypeVessel> oldList, List<TypeVessel> newList);

}
