package  ma.zyn.app.dao.specification.core.journal;

import ma.zyn.app.dao.criteria.core.journal.ResultatEvaluationCriteria;
import ma.zyn.app.bean.core.journal.ResultatEvaluation;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ResultatEvaluationSpecification extends  AbstractSpecification<ResultatEvaluationCriteria, ResultatEvaluation>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("label", criteria.getLabel(),criteria.getLabelLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public ResultatEvaluationSpecification(ResultatEvaluationCriteria criteria) {
        super(criteria);
    }

    public ResultatEvaluationSpecification(ResultatEvaluationCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
