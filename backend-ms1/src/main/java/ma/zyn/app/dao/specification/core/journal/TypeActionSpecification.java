package  ma.zyn.app.dao.specification.core.journal;

import ma.zyn.app.dao.criteria.core.journal.TypeActionCriteria;
import ma.zyn.app.bean.core.journal.TypeAction;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class TypeActionSpecification extends  AbstractSpecification<TypeActionCriteria, TypeAction>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("label", criteria.getLabel(),criteria.getLabelLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public TypeActionSpecification(TypeActionCriteria criteria) {
        super(criteria);
    }

    public TypeActionSpecification(TypeActionCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
