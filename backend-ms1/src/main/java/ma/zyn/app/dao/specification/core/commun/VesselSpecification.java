package  ma.zyn.app.dao.specification.core.commun;

import ma.zyn.app.dao.criteria.core.commun.VesselCriteria;
import ma.zyn.app.bean.core.commun.Vessel;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class VesselSpecification extends  AbstractSpecification<VesselCriteria, Vessel>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("label", criteria.getLabel(),criteria.getLabelLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
        addPredicateBigDecimal("loa", criteria.getLoa(), criteria.getLoaMin(), criteria.getLoaMax());
        addPredicateBigDecimal("draft", criteria.getDraft(), criteria.getDraftMin(), criteria.getDraftMax());
        addPredicateBigDecimal("airDraft", criteria.getAirDraft(), criteria.getAirDraftMin(), criteria.getAirDraftMax());
    }

    public VesselSpecification(VesselCriteria criteria) {
        super(criteria);
    }

    public VesselSpecification(VesselCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
