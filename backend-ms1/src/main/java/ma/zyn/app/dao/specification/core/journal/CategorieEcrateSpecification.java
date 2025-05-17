package  ma.zyn.app.dao.specification.core.journal;

import ma.zyn.app.dao.criteria.core.journal.CategorieEcrateCriteria;
import ma.zyn.app.bean.core.journal.CategorieEcrate;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class CategorieEcrateSpecification extends  AbstractSpecification<CategorieEcrateCriteria, CategorieEcrate>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("label", criteria.getLabel(),criteria.getLabelLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public CategorieEcrateSpecification(CategorieEcrateCriteria criteria) {
        super(criteria);
    }

    public CategorieEcrateSpecification(CategorieEcrateCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
