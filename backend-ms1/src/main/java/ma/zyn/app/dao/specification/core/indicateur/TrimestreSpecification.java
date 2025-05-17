package  ma.zyn.app.dao.specification.core.indicateur;

import ma.zyn.app.dao.criteria.core.indicateur.TrimestreCriteria;
import ma.zyn.app.bean.core.indicateur.Trimestre;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class TrimestreSpecification extends  AbstractSpecification<TrimestreCriteria, Trimestre>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("label", criteria.getLabel(),criteria.getLabelLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public TrimestreSpecification(TrimestreCriteria criteria) {
        super(criteria);
    }

    public TrimestreSpecification(TrimestreCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
