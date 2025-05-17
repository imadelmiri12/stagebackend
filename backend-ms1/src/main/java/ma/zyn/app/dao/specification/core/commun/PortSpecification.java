package  ma.zyn.app.dao.specification.core.commun;

import ma.zyn.app.dao.criteria.core.commun.PortCriteria;
import ma.zyn.app.bean.core.commun.Port;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class PortSpecification extends  AbstractSpecification<PortCriteria, Port>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateInt("indexation", criteria.getIndexation(), criteria.getIndexationMin(), criteria.getIndexationMax());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("label", criteria.getLabel(),criteria.getLabelLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
        addPredicate("color", criteria.getColor(),criteria.getColorLike());
    }

    public PortSpecification(PortCriteria criteria) {
        super(criteria);
    }

    public PortSpecification(PortCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
