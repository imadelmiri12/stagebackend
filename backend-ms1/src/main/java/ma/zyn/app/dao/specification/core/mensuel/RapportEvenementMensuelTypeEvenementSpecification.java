package  ma.zyn.app.dao.specification.core.mensuel;

import ma.zyn.app.dao.criteria.core.mensuel.RapportEvenementMensuelTypeEvenementCriteria;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelTypeEvenement;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class RapportEvenementMensuelTypeEvenementSpecification extends  AbstractSpecification<RapportEvenementMensuelTypeEvenementCriteria, RapportEvenementMensuelTypeEvenement>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateBigDecimal("total", criteria.getTotal(), criteria.getTotalMin(), criteria.getTotalMax());
        addPredicateBigDecimal("totalCumul", criteria.getTotalCumul(), criteria.getTotalCumulMin(), criteria.getTotalCumulMax());
        addPredicateBigDecimal("indicateurGlobalCumul", criteria.getIndicateurGlobalCumul(), criteria.getIndicateurGlobalCumulMin(), criteria.getIndicateurGlobalCumulMax());
        addPredicateFk("rapportEvenementMensuel","id", criteria.getRapportEvenementMensuel()==null?null:criteria.getRapportEvenementMensuel().getId());
        addPredicateFk("rapportEvenementMensuel","id", criteria.getRapportEvenementMensuels());
        addPredicateFk("rapportEvenementMensuel","code", criteria.getRapportEvenementMensuel()==null?null:criteria.getRapportEvenementMensuel().getCode());
        addPredicateFk("typeEvenement","id", criteria.getTypeEvenement()==null?null:criteria.getTypeEvenement().getId());
        addPredicateFk("typeEvenement","id", criteria.getTypeEvenements());
        addPredicateFk("typeEvenement","code", criteria.getTypeEvenement()==null?null:criteria.getTypeEvenement().getCode());
    }

    public RapportEvenementMensuelTypeEvenementSpecification(RapportEvenementMensuelTypeEvenementCriteria criteria) {
        super(criteria);
    }

    public RapportEvenementMensuelTypeEvenementSpecification(RapportEvenementMensuelTypeEvenementCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
