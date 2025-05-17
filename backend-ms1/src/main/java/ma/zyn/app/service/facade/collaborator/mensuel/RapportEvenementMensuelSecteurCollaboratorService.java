package ma.zyn.app.service.facade.collaborator.mensuel;

import java.util.List;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelSecteur;
import ma.zyn.app.dao.criteria.core.mensuel.RapportEvenementMensuelSecteurCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface RapportEvenementMensuelSecteurCollaboratorService {



    List<RapportEvenementMensuelSecteur> findByRapportEvenementMensuelId(Long id);
    int deleteByRapportEvenementMensuelId(Long id);
    long countByRapportEvenementMensuelCode(String code);
    List<RapportEvenementMensuelSecteur> findBySecteurCode(String code);
    List<RapportEvenementMensuelSecteur> findBySecteurId(Long id);
    int deleteBySecteurId(Long id);
    int deleteBySecteurCode(String code);
    long countBySecteurCode(String code);




	RapportEvenementMensuelSecteur create(RapportEvenementMensuelSecteur t);

    RapportEvenementMensuelSecteur update(RapportEvenementMensuelSecteur t);

    List<RapportEvenementMensuelSecteur> update(List<RapportEvenementMensuelSecteur> ts,boolean createIfNotExist);

    RapportEvenementMensuelSecteur findById(Long id);

    RapportEvenementMensuelSecteur findOrSave(RapportEvenementMensuelSecteur t);

    RapportEvenementMensuelSecteur findByReferenceEntity(RapportEvenementMensuelSecteur t);

    RapportEvenementMensuelSecteur findWithAssociatedLists(Long id);

    List<RapportEvenementMensuelSecteur> findAllOptimized();

    List<RapportEvenementMensuelSecteur> findAll();

    List<RapportEvenementMensuelSecteur> findByCriteria(RapportEvenementMensuelSecteurCriteria criteria);

    List<RapportEvenementMensuelSecteur> findPaginatedByCriteria(RapportEvenementMensuelSecteurCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(RapportEvenementMensuelSecteurCriteria criteria);

    List<RapportEvenementMensuelSecteur> delete(List<RapportEvenementMensuelSecteur> ts);

    boolean deleteById(Long id);

    List<List<RapportEvenementMensuelSecteur>> getToBeSavedAndToBeDeleted(List<RapportEvenementMensuelSecteur> oldList, List<RapportEvenementMensuelSecteur> newList);

}
