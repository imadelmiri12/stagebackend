package  ma.zyn.app.dao.specification.core.journal;

import ma.zyn.app.dao.criteria.core.journal.SystemeManagementCriteria;
import ma.zyn.app.bean.core.journal.SystemeManagement;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class SystemeManagementSpecification extends  AbstractSpecification<SystemeManagementCriteria, SystemeManagement>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("label", criteria.getLabel(),criteria.getLabelLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public SystemeManagementSpecification(SystemeManagementCriteria criteria) {
        super(criteria);
    }

    public SystemeManagementSpecification(SystemeManagementCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
