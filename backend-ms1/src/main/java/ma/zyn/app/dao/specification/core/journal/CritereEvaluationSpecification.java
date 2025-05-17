package  ma.zyn.app.dao.specification.core.journal;

import ma.zyn.app.dao.criteria.core.journal.CritereEvaluationCriteria;
import ma.zyn.app.bean.core.journal.CritereEvaluation;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class CritereEvaluationSpecification extends  AbstractSpecification<CritereEvaluationCriteria, CritereEvaluation>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("label", criteria.getLabel(),criteria.getLabelLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public CritereEvaluationSpecification(CritereEvaluationCriteria criteria) {
        super(criteria);
    }

    public CritereEvaluationSpecification(CritereEvaluationCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
