package  ma.zyn.app.dao.specification.core.commun;

import ma.zyn.app.dao.criteria.core.commun.TypeCollaboratorCriteria;
import ma.zyn.app.bean.core.commun.TypeCollaborator;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class TypeCollaboratorSpecification extends  AbstractSpecification<TypeCollaboratorCriteria, TypeCollaborator>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateInt("indexation", criteria.getIndexation(), criteria.getIndexationMin(), criteria.getIndexationMax());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("label", criteria.getLabel(),criteria.getLabelLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
        addPredicate("color", criteria.getColor(),criteria.getColorLike());
    }

    public TypeCollaboratorSpecification(TypeCollaboratorCriteria criteria) {
        super(criteria);
    }

    public TypeCollaboratorSpecification(TypeCollaboratorCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
