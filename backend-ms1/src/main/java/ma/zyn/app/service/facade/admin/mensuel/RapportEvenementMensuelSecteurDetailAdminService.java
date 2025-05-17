package ma.zyn.app.service.facade.admin.mensuel;

import java.util.List;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelSecteurDetail;
import ma.zyn.app.dao.criteria.core.mensuel.RapportEvenementMensuelSecteurDetailCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface RapportEvenementMensuelSecteurDetailAdminService {



    List<RapportEvenementMensuelSecteurDetail> findByRapportEvenementMensuelSecteurId(Long id);
    int deleteByRapportEvenementMensuelSecteurId(Long id);
    long countByRapportEvenementMensuelSecteurId(Long id);
    List<RapportEvenementMensuelSecteurDetail> findByTypeEvenementCode(String code);
    List<RapportEvenementMensuelSecteurDetail> findByTypeEvenementId(Long id);
    int deleteByTypeEvenementId(Long id);
    int deleteByTypeEvenementCode(String code);
    long countByTypeEvenementCode(String code);




	RapportEvenementMensuelSecteurDetail create(RapportEvenementMensuelSecteurDetail t);

    RapportEvenementMensuelSecteurDetail update(RapportEvenementMensuelSecteurDetail t);

    List<RapportEvenementMensuelSecteurDetail> update(List<RapportEvenementMensuelSecteurDetail> ts,boolean createIfNotExist);

    RapportEvenementMensuelSecteurDetail findById(Long id);

    RapportEvenementMensuelSecteurDetail findOrSave(RapportEvenementMensuelSecteurDetail t);

    RapportEvenementMensuelSecteurDetail findByReferenceEntity(RapportEvenementMensuelSecteurDetail t);

    RapportEvenementMensuelSecteurDetail findWithAssociatedLists(Long id);

    List<RapportEvenementMensuelSecteurDetail> findAllOptimized();

    List<RapportEvenementMensuelSecteurDetail> findAll();

    List<RapportEvenementMensuelSecteurDetail> findByCriteria(RapportEvenementMensuelSecteurDetailCriteria criteria);

    List<RapportEvenementMensuelSecteurDetail> findPaginatedByCriteria(RapportEvenementMensuelSecteurDetailCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(RapportEvenementMensuelSecteurDetailCriteria criteria);

    List<RapportEvenementMensuelSecteurDetail> delete(List<RapportEvenementMensuelSecteurDetail> ts);

    boolean deleteById(Long id);

    List<List<RapportEvenementMensuelSecteurDetail>> getToBeSavedAndToBeDeleted(List<RapportEvenementMensuelSecteurDetail> oldList, List<RapportEvenementMensuelSecteurDetail> newList);

}
