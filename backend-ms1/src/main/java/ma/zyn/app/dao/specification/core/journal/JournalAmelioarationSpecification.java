package  ma.zyn.app.dao.specification.core.journal;

import ma.zyn.app.dao.criteria.core.journal.JournalAmelioarationCriteria;
import ma.zyn.app.bean.core.journal.JournalAmelioaration;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class JournalAmelioarationSpecification extends  AbstractSpecification<JournalAmelioarationCriteria, JournalAmelioaration>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("date", criteria.getDate(), criteria.getDateFrom(), criteria.getDateTo());
        addPredicateInt("annee", criteria.getAnnee(), criteria.getAnneeMin(), criteria.getAnneeMax());
        addPredicate("dateButoir", criteria.getDateButoir(), criteria.getDateButoirFrom(), criteria.getDateButoirTo());
        addPredicate("dateRealisation", criteria.getDateRealisation(), criteria.getDateRealisationFrom(), criteria.getDateRealisationTo());
        addPredicate("dateEvaluation", criteria.getDateEvaluation(), criteria.getDateEvaluationFrom(), criteria.getDateEvaluationTo());
        addPredicate("dateCloture", criteria.getDateCloture(), criteria.getDateClotureFrom(), criteria.getDateClotureTo());
        addPredicateBigDecimal("avancement", criteria.getAvancement(), criteria.getAvancementMin(), criteria.getAvancementMax());
        addPredicateFk("systemeManagement","id", criteria.getSystemeManagement()==null?null:criteria.getSystemeManagement().getId());
        addPredicateFk("systemeManagement","id", criteria.getSystemeManagements());
        addPredicateFk("systemeManagement","code", criteria.getSystemeManagement()==null?null:criteria.getSystemeManagement().getCode());
        addPredicateFk("categorieEcrate","id", criteria.getCategorieEcrate()==null?null:criteria.getCategorieEcrate().getId());
        addPredicateFk("categorieEcrate","id", criteria.getCategorieEcrates());
        addPredicateFk("categorieEcrate","code", criteria.getCategorieEcrate()==null?null:criteria.getCategorieEcrate().getCode());
        addPredicateFk("origineEcrate","id", criteria.getOrigineEcrate()==null?null:criteria.getOrigineEcrate().getId());
        addPredicateFk("origineEcrate","id", criteria.getOrigineEcrates());
        addPredicateFk("origineEcrate","code", criteria.getOrigineEcrate()==null?null:criteria.getOrigineEcrate().getCode());
        addPredicateFk("typeAction","id", criteria.getTypeAction()==null?null:criteria.getTypeAction().getId());
        addPredicateFk("typeAction","id", criteria.getTypeActions());
        addPredicateFk("typeAction","code", criteria.getTypeAction()==null?null:criteria.getTypeAction().getCode());
        addPredicateFk("collaborator","id", criteria.getCollaborator()==null?null:criteria.getCollaborator().getId());
        addPredicateFk("collaborator","id", criteria.getCollaborators());
        addPredicateFk("collaborator","email", criteria.getCollaborator()==null?null:criteria.getCollaborator().getEmail());
        addPredicateFk("critereEvaluation","id", criteria.getCritereEvaluation()==null?null:criteria.getCritereEvaluation().getId());
        addPredicateFk("critereEvaluation","id", criteria.getCritereEvaluations());
        addPredicateFk("critereEvaluation","code", criteria.getCritereEvaluation()==null?null:criteria.getCritereEvaluation().getCode());
        addPredicateFk("resultatEvaluation","id", criteria.getResultatEvaluation()==null?null:criteria.getResultatEvaluation().getId());
        addPredicateFk("resultatEvaluation","id", criteria.getResultatEvaluations());
        addPredicateFk("resultatEvaluation","code", criteria.getResultatEvaluation()==null?null:criteria.getResultatEvaluation().getCode());
    }

    public JournalAmelioarationSpecification(JournalAmelioarationCriteria criteria) {
        super(criteria);
    }

    public JournalAmelioarationSpecification(JournalAmelioarationCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
