package ma.zyn.app.service.impl.collaborator.journal;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.journal.JournalAmelioaration;
import ma.zyn.app.dao.criteria.core.journal.JournalAmelioarationCriteria;
import ma.zyn.app.dao.facade.core.journal.JournalAmelioarationDao;
import ma.zyn.app.dao.specification.core.journal.JournalAmelioarationSpecification;
import ma.zyn.app.service.facade.collaborator.journal.JournalAmelioarationCollaboratorService;
import ma.zyn.app.zynerator.service.AbstractServiceImpl;
import static ma.zyn.app.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zyn.app.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zyn.app.service.facade.collaborator.journal.TypeActionCollaboratorService ;
import ma.zyn.app.bean.core.journal.TypeAction ;
import ma.zyn.app.service.facade.collaborator.actor.CollaboratorCollaboratorService ;
import ma.zyn.app.bean.core.actor.Collaborator ;
import ma.zyn.app.service.facade.collaborator.journal.ResultatEvaluationCollaboratorService ;
import ma.zyn.app.bean.core.journal.ResultatEvaluation ;
import ma.zyn.app.service.facade.collaborator.journal.SystemeManagementCollaboratorService ;
import ma.zyn.app.bean.core.journal.SystemeManagement ;
import ma.zyn.app.service.facade.collaborator.journal.CritereEvaluationCollaboratorService ;
import ma.zyn.app.bean.core.journal.CritereEvaluation ;
import ma.zyn.app.service.facade.collaborator.journal.CategorieEcrateCollaboratorService ;
import ma.zyn.app.bean.core.journal.CategorieEcrate ;
import ma.zyn.app.service.facade.collaborator.journal.OrigineEcrateCollaboratorService ;
import ma.zyn.app.bean.core.journal.OrigineEcrate ;

import java.util.List;
@Service
public class JournalAmelioarationCollaboratorServiceImpl implements JournalAmelioarationCollaboratorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public JournalAmelioaration update(JournalAmelioaration t) {
        JournalAmelioaration loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{JournalAmelioaration.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public JournalAmelioaration findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public JournalAmelioaration findOrSave(JournalAmelioaration t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            JournalAmelioaration result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<JournalAmelioaration> findAll() {
        return dao.findAll();
    }

    public List<JournalAmelioaration> findByCriteria(JournalAmelioarationCriteria criteria) {
        List<JournalAmelioaration> content = null;
        if (criteria != null) {
            JournalAmelioarationSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private JournalAmelioarationSpecification constructSpecification(JournalAmelioarationCriteria criteria) {
        JournalAmelioarationSpecification mySpecification =  (JournalAmelioarationSpecification) RefelexivityUtil.constructObjectUsingOneParam(JournalAmelioarationSpecification.class, criteria);
        return mySpecification;
    }

    public List<JournalAmelioaration> findPaginatedByCriteria(JournalAmelioarationCriteria criteria, int page, int pageSize, String order, String sortField) {
        JournalAmelioarationSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(JournalAmelioarationCriteria criteria) {
        JournalAmelioarationSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<JournalAmelioaration> findBySystemeManagementCode(String code){
        return dao.findBySystemeManagementCode(code);
    }
    public List<JournalAmelioaration> findBySystemeManagementId(Long id){
        return dao.findBySystemeManagementId(id);
    }
    public int deleteBySystemeManagementCode(String code){
        return dao.deleteBySystemeManagementCode(code);
    }
    public int deleteBySystemeManagementId(Long id){
        return dao.deleteBySystemeManagementId(id);
    }
    public long countBySystemeManagementCode(String code){
        return dao.countBySystemeManagementCode(code);
    }
    public List<JournalAmelioaration> findByCategorieEcrateCode(String code){
        return dao.findByCategorieEcrateCode(code);
    }
    public List<JournalAmelioaration> findByCategorieEcrateId(Long id){
        return dao.findByCategorieEcrateId(id);
    }
    public int deleteByCategorieEcrateCode(String code){
        return dao.deleteByCategorieEcrateCode(code);
    }
    public int deleteByCategorieEcrateId(Long id){
        return dao.deleteByCategorieEcrateId(id);
    }
    public long countByCategorieEcrateCode(String code){
        return dao.countByCategorieEcrateCode(code);
    }
    public List<JournalAmelioaration> findByOrigineEcrateCode(String code){
        return dao.findByOrigineEcrateCode(code);
    }
    public List<JournalAmelioaration> findByOrigineEcrateId(Long id){
        return dao.findByOrigineEcrateId(id);
    }
    public int deleteByOrigineEcrateCode(String code){
        return dao.deleteByOrigineEcrateCode(code);
    }
    public int deleteByOrigineEcrateId(Long id){
        return dao.deleteByOrigineEcrateId(id);
    }
    public long countByOrigineEcrateCode(String code){
        return dao.countByOrigineEcrateCode(code);
    }
    public List<JournalAmelioaration> findByTypeActionCode(String code){
        return dao.findByTypeActionCode(code);
    }
    public List<JournalAmelioaration> findByTypeActionId(Long id){
        return dao.findByTypeActionId(id);
    }
    public int deleteByTypeActionCode(String code){
        return dao.deleteByTypeActionCode(code);
    }
    public int deleteByTypeActionId(Long id){
        return dao.deleteByTypeActionId(id);
    }
    public long countByTypeActionCode(String code){
        return dao.countByTypeActionCode(code);
    }
    public List<JournalAmelioaration> findByCollaboratorId(Long id){
        return dao.findByCollaboratorId(id);
    }
    public int deleteByCollaboratorId(Long id){
        return dao.deleteByCollaboratorId(id);
    }
    public long countByCollaboratorEmail(String email){
        return dao.countByCollaboratorEmail(email);
    }
    public List<JournalAmelioaration> findByCritereEvaluationCode(String code){
        return dao.findByCritereEvaluationCode(code);
    }
    public List<JournalAmelioaration> findByCritereEvaluationId(Long id){
        return dao.findByCritereEvaluationId(id);
    }
    public int deleteByCritereEvaluationCode(String code){
        return dao.deleteByCritereEvaluationCode(code);
    }
    public int deleteByCritereEvaluationId(Long id){
        return dao.deleteByCritereEvaluationId(id);
    }
    public long countByCritereEvaluationCode(String code){
        return dao.countByCritereEvaluationCode(code);
    }
    public List<JournalAmelioaration> findByResultatEvaluationCode(String code){
        return dao.findByResultatEvaluationCode(code);
    }
    public List<JournalAmelioaration> findByResultatEvaluationId(Long id){
        return dao.findByResultatEvaluationId(id);
    }
    public int deleteByResultatEvaluationCode(String code){
        return dao.deleteByResultatEvaluationCode(code);
    }
    public int deleteByResultatEvaluationId(Long id){
        return dao.deleteByResultatEvaluationId(id);
    }
    public long countByResultatEvaluationCode(String code){
        return dao.countByResultatEvaluationCode(code);
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
    public List<JournalAmelioaration> delete(List<JournalAmelioaration> list) {
		List<JournalAmelioaration> result = new ArrayList();
        if (list != null) {
            for (JournalAmelioaration t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}else{
                    dao.deleteById(t.getId());
                }
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public JournalAmelioaration create(JournalAmelioaration t) {
        JournalAmelioaration loaded = findByReferenceEntity(t);
        JournalAmelioaration saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public JournalAmelioaration findWithAssociatedLists(Long id){
        JournalAmelioaration result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<JournalAmelioaration> update(List<JournalAmelioaration> ts, boolean createIfNotExist) {
        List<JournalAmelioaration> result = new ArrayList<>();
        if (ts != null) {
            for (JournalAmelioaration t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    JournalAmelioaration loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, JournalAmelioaration t, JournalAmelioaration loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public JournalAmelioaration findByReferenceEntity(JournalAmelioaration t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(JournalAmelioaration t){
        if( t != null) {
            t.setCollaborator(collaboratorService.findOrSave(t.getCollaborator()));
        }
    }



    public List<JournalAmelioaration> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<JournalAmelioaration>> getToBeSavedAndToBeDeleted(List<JournalAmelioaration> oldList, List<JournalAmelioaration> newList) {
        List<List<JournalAmelioaration>> result = new ArrayList<>();
        List<JournalAmelioaration> resultDelete = new ArrayList<>();
        List<JournalAmelioaration> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<JournalAmelioaration> oldList, List<JournalAmelioaration> newList, List<JournalAmelioaration> resultUpdateOrSave, List<JournalAmelioaration> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                JournalAmelioaration myOld = oldList.get(i);
                JournalAmelioaration t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                JournalAmelioaration myNew = newList.get(i);
                JournalAmelioaration t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    @Autowired
    private TypeActionCollaboratorService typeActionService ;
    @Autowired
    private CollaboratorCollaboratorService collaboratorService ;
    @Autowired
    private ResultatEvaluationCollaboratorService resultatEvaluationService ;
    @Autowired
    private SystemeManagementCollaboratorService systemeManagementService ;
    @Autowired
    private CritereEvaluationCollaboratorService critereEvaluationService ;
    @Autowired
    private CategorieEcrateCollaboratorService categorieEcrateService ;
    @Autowired
    private OrigineEcrateCollaboratorService origineEcrateService ;

    public JournalAmelioarationCollaboratorServiceImpl(JournalAmelioarationDao dao) {
        this.dao = dao;
    }

    private JournalAmelioarationDao dao;
}
