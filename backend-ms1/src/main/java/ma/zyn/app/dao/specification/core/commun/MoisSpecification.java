package  ma.zyn.app.dao.specification.core.commun;

import ma.zyn.app.dao.criteria.core.commun.MoisCriteria;
import ma.zyn.app.bean.core.commun.Mois;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class MoisSpecification extends  AbstractSpecification<MoisCriteria, Mois>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("label", criteria.getLabel(),criteria.getLabelLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
        addPredicateInt("numero", criteria.getNumero(), criteria.getNumeroMin(), criteria.getNumeroMax());
        addPredicateFk("trimestre","id", criteria.getTrimestre()==null?null:criteria.getTrimestre().getId());
        addPredicateFk("trimestre","id", criteria.getTrimestres());
        addPredicateFk("trimestre","code", criteria.getTrimestre()==null?null:criteria.getTrimestre().getCode());
    }

    public MoisSpecification(MoisCriteria criteria) {
        super(criteria);
    }

    public MoisSpecification(MoisCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
