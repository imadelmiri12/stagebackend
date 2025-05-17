package  ma.zyn.app.dao.specification.core.mensuel;

import ma.zyn.app.dao.criteria.core.mensuel.RapportEvenementMensuelSecteurCriteria;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelSecteur;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class RapportEvenementMensuelSecteurSpecification extends  AbstractSpecification<RapportEvenementMensuelSecteurCriteria, RapportEvenementMensuelSecteur>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateBigDecimal("total", criteria.getTotal(), criteria.getTotalMin(), criteria.getTotalMax());
        addPredicateBigDecimal("indicateurGlobal", criteria.getIndicateurGlobal(), criteria.getIndicateurGlobalMin(), criteria.getIndicateurGlobalMax());
        addPredicateBigDecimal("totalCumul", criteria.getTotalCumul(), criteria.getTotalCumulMin(), criteria.getTotalCumulMax());
        addPredicateBigDecimal("indicateurGlobalCumul", criteria.getIndicateurGlobalCumul(), criteria.getIndicateurGlobalCumulMin(), criteria.getIndicateurGlobalCumulMax());
        addPredicateFk("rapportEvenementMensuel","id", criteria.getRapportEvenementMensuel()==null?null:criteria.getRapportEvenementMensuel().getId());
        addPredicateFk("rapportEvenementMensuel","id", criteria.getRapportEvenementMensuels());
        addPredicateFk("rapportEvenementMensuel","code", criteria.getRapportEvenementMensuel()==null?null:criteria.getRapportEvenementMensuel().getCode());
        addPredicateFk("secteur","id", criteria.getSecteur()==null?null:criteria.getSecteur().getId());
        addPredicateFk("secteur","id", criteria.getSecteurs());
        addPredicateFk("secteur","code", criteria.getSecteur()==null?null:criteria.getSecteur().getCode());
    }

    public RapportEvenementMensuelSecteurSpecification(RapportEvenementMensuelSecteurCriteria criteria) {
        super(criteria);
    }

    public RapportEvenementMensuelSecteurSpecification(RapportEvenementMensuelSecteurCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
