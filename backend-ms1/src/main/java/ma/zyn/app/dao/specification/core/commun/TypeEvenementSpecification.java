package  ma.zyn.app.dao.specification.core.commun;

import ma.zyn.app.dao.criteria.core.commun.TypeEvenementCriteria;
import ma.zyn.app.bean.core.commun.TypeEvenement;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class TypeEvenementSpecification extends  AbstractSpecification<TypeEvenementCriteria, TypeEvenement>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateInt("indexation", criteria.getIndexation(), criteria.getIndexationMin(), criteria.getIndexationMax());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("label", criteria.getLabel(),criteria.getLabelLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
        addPredicate("color", criteria.getColor(),criteria.getColorLike());
    }

    public TypeEvenementSpecification(TypeEvenementCriteria criteria) {
        super(criteria);
    }

    public TypeEvenementSpecification(TypeEvenementCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
