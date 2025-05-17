package  ma.zyn.app.dao.specification.core.rapport;

import ma.zyn.app.dao.criteria.core.rapport.RapportEvenementCriteria;
import ma.zyn.app.bean.core.rapport.RapportEvenement;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class RapportEvenementSpecification extends  AbstractSpecification<RapportEvenementCriteria, RapportEvenement>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("dateEvenement", criteria.getDateEvenement(), criteria.getDateEvenementFrom(), criteria.getDateEvenementTo());
        addPredicate("dateSoumission", criteria.getDateSoumission(), criteria.getDateSoumissionFrom(), criteria.getDateSoumissionTo());
        addPredicateInt("mois", criteria.getMois(), criteria.getMoisMin(), criteria.getMoisMax());
        addPredicateInt("annee", criteria.getAnnee(), criteria.getAnneeMin(), criteria.getAnneeMax());
        addPredicateBigDecimal("windDirection", criteria.getWindDirection(), criteria.getWindDirectionMin(), criteria.getWindDirectionMax());
        addPredicateBigDecimal("windForce", criteria.getWindForce(), criteria.getWindForceMin(), criteria.getWindForceMax());
        addPredicateBigDecimal("currentDirection", criteria.getCurrentDirection(), criteria.getCurrentDirectionMin(), criteria.getCurrentDirectionMax());
        addPredicateBigDecimal("currentForce", criteria.getCurrentForce(), criteria.getCurrentForceMin(), criteria.getCurrentForceMax());
        addPredicateBigDecimal("swellHeigth", criteria.getSwellHeigth(), criteria.getSwellHeigthMin(), criteria.getSwellHeigthMax());
        addPredicateBigDecimal("swellDirection", criteria.getSwellDirection(), criteria.getSwellDirectionMin(), criteria.getSwellDirectionMax());
        addPredicateBool("fonde", criteria.getFonde());
        addPredicateFk("port","id", criteria.getPort()==null?null:criteria.getPort().getId());
        addPredicateFk("port","id", criteria.getPorts());
        addPredicateFk("port","code", criteria.getPort()==null?null:criteria.getPort().getCode());
        addPredicateFk("typeEvenement","id", criteria.getTypeEvenement()==null?null:criteria.getTypeEvenement().getId());
        addPredicateFk("typeEvenement","id", criteria.getTypeEvenements());
        addPredicateFk("typeEvenement","code", criteria.getTypeEvenement()==null?null:criteria.getTypeEvenement().getCode());
        addPredicateFk("collaborator","id", criteria.getCollaborator()==null?null:criteria.getCollaborator().getId());
        addPredicateFk("collaborator","id", criteria.getCollaborators());
        addPredicateFk("collaborator","email", criteria.getCollaborator()==null?null:criteria.getCollaborator().getEmail());
        addPredicateFk("vessel","id", criteria.getVessel()==null?null:criteria.getVessel().getId());
        addPredicateFk("vessel","id", criteria.getVessels());
        addPredicateFk("vessel","code", criteria.getVessel()==null?null:criteria.getVessel().getCode());
        addPredicateFk("typeRapportEvenement","id", criteria.getTypeRapportEvenement()==null?null:criteria.getTypeRapportEvenement().getId());
        addPredicateFk("typeRapportEvenement","id", criteria.getTypeRapportEvenements());
        addPredicateFk("typeRapportEvenement","code", criteria.getTypeRapportEvenement()==null?null:criteria.getTypeRapportEvenement().getCode());
        addPredicateFk("secteur","id", criteria.getSecteur()==null?null:criteria.getSecteur().getId());
        addPredicateFk("secteur","id", criteria.getSecteurs());
        addPredicateFk("secteur","code", criteria.getSecteur()==null?null:criteria.getSecteur().getCode());
        addPredicateFk("journalAmelioaration","id", criteria.getJournalAmelioaration()==null?null:criteria.getJournalAmelioaration().getId());
        addPredicateFk("journalAmelioaration","id", criteria.getJournalAmelioarations());
    }

    public RapportEvenementSpecification(RapportEvenementCriteria criteria) {
        super(criteria);
    }

    public RapportEvenementSpecification(RapportEvenementCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
