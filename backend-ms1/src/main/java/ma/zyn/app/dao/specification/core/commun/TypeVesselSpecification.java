package  ma.zyn.app.dao.specification.core.commun;

import ma.zyn.app.dao.criteria.core.commun.TypeVesselCriteria;
import ma.zyn.app.bean.core.commun.TypeVessel;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class TypeVesselSpecification extends  AbstractSpecification<TypeVesselCriteria, TypeVessel>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateInt("indexation", criteria.getIndexation(), criteria.getIndexationMin(), criteria.getIndexationMax());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("label", criteria.getLabel(),criteria.getLabelLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
        addPredicate("color", criteria.getColor(),criteria.getColorLike());
    }

    public TypeVesselSpecification(TypeVesselCriteria criteria) {
        super(criteria);
    }

    public TypeVesselSpecification(TypeVesselCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
