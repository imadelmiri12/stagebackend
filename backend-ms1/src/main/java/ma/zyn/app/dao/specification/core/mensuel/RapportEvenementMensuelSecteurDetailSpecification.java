package  ma.zyn.app.dao.specification.core.mensuel;

import ma.zyn.app.dao.criteria.core.mensuel.RapportEvenementMensuelSecteurDetailCriteria;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelSecteurDetail;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class RapportEvenementMensuelSecteurDetailSpecification extends  AbstractSpecification<RapportEvenementMensuelSecteurDetailCriteria, RapportEvenementMensuelSecteurDetail>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateBigDecimal("total", criteria.getTotal(), criteria.getTotalMin(), criteria.getTotalMax());
        addPredicateBigDecimal("indicateurGlobal", criteria.getIndicateurGlobal(), criteria.getIndicateurGlobalMin(), criteria.getIndicateurGlobalMax());
        addPredicateFk("rapportEvenementMensuelSecteur","id", criteria.getRapportEvenementMensuelSecteur()==null?null:criteria.getRapportEvenementMensuelSecteur().getId());
        addPredicateFk("rapportEvenementMensuelSecteur","id", criteria.getRapportEvenementMensuelSecteurs());
        addPredicateFk("typeEvenement","id", criteria.getTypeEvenement()==null?null:criteria.getTypeEvenement().getId());
        addPredicateFk("typeEvenement","id", criteria.getTypeEvenements());
        addPredicateFk("typeEvenement","code", criteria.getTypeEvenement()==null?null:criteria.getTypeEvenement().getCode());
    }

    public RapportEvenementMensuelSecteurDetailSpecification(RapportEvenementMensuelSecteurDetailCriteria criteria) {
        super(criteria);
    }

    public RapportEvenementMensuelSecteurDetailSpecification(RapportEvenementMensuelSecteurDetailCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
