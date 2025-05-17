package  ma.zyn.app.dao.specification.core.commun;

import ma.zyn.app.dao.criteria.core.commun.TypeRapportEvenementCriteria;
import ma.zyn.app.bean.core.commun.TypeRapportEvenement;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class TypeRapportEvenementSpecification extends  AbstractSpecification<TypeRapportEvenementCriteria, TypeRapportEvenement>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateInt("indexation", criteria.getIndexation(), criteria.getIndexationMin(), criteria.getIndexationMax());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("label", criteria.getLabel(),criteria.getLabelLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
        addPredicate("color", criteria.getColor(),criteria.getColorLike());
    }

    public TypeRapportEvenementSpecification(TypeRapportEvenementCriteria criteria) {
        super(criteria);
    }

    public TypeRapportEvenementSpecification(TypeRapportEvenementCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
