package  ma.zyn.app.dao.specification.core.mensuel;

import ma.zyn.app.dao.criteria.core.mensuel.RapportEvenementMensuelCriteria;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuel;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class RapportEvenementMensuelSpecification extends  AbstractSpecification<RapportEvenementMensuelCriteria, RapportEvenementMensuel>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("dateEmission", criteria.getDateEmission(), criteria.getDateEmissionFrom(), criteria.getDateEmissionTo());
        addPredicateInt("mois", criteria.getMois(), criteria.getMoisMin(), criteria.getMoisMax());
        addPredicateInt("annee", criteria.getAnnee(), criteria.getAnneeMin(), criteria.getAnneeMax());
        addPredicateInt("nombreMouvement", criteria.getNombreMouvement(), criteria.getNombreMouvementMin(), criteria.getNombreMouvementMax());
        addPredicateBigDecimal("total", criteria.getTotal(), criteria.getTotalMin(), criteria.getTotalMax());
        addPredicateBigDecimal("indicateurGlobal", criteria.getIndicateurGlobal(), criteria.getIndicateurGlobalMin(), criteria.getIndicateurGlobalMax());
        addPredicateInt("nombreMouvementCumul", criteria.getNombreMouvementCumul(), criteria.getNombreMouvementCumulMin(), criteria.getNombreMouvementCumulMax());
        addPredicateBigDecimal("totalCumul", criteria.getTotalCumul(), criteria.getTotalCumulMin(), criteria.getTotalCumulMax());
        addPredicateBigDecimal("indicateurGlobalCumul", criteria.getIndicateurGlobalCumul(), criteria.getIndicateurGlobalCumulMin(), criteria.getIndicateurGlobalCumulMax());
    }

    public RapportEvenementMensuelSpecification(RapportEvenementMensuelCriteria criteria) {
        super(criteria);
    }

    public RapportEvenementMensuelSpecification(RapportEvenementMensuelCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
