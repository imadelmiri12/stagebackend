package  ma.zyn.app.dao.specification.core.journal;

import ma.zyn.app.dao.criteria.core.journal.OrigineEcrateCriteria;
import ma.zyn.app.bean.core.journal.OrigineEcrate;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class OrigineEcrateSpecification extends  AbstractSpecification<OrigineEcrateCriteria, OrigineEcrate>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("label", criteria.getLabel(),criteria.getLabelLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public OrigineEcrateSpecification(OrigineEcrateCriteria criteria) {
        super(criteria);
    }

    public OrigineEcrateSpecification(OrigineEcrateCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
