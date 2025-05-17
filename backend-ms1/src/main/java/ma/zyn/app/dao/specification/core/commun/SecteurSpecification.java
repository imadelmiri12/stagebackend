package  ma.zyn.app.dao.specification.core.commun;

import ma.zyn.app.dao.criteria.core.commun.SecteurCriteria;
import ma.zyn.app.bean.core.commun.Secteur;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class SecteurSpecification extends  AbstractSpecification<SecteurCriteria, Secteur>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateInt("indexation", criteria.getIndexation(), criteria.getIndexationMin(), criteria.getIndexationMax());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("label", criteria.getLabel(),criteria.getLabelLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
        addPredicate("color", criteria.getColor(),criteria.getColorLike());
    }

    public SecteurSpecification(SecteurCriteria criteria) {
        super(criteria);
    }

    public SecteurSpecification(SecteurCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
