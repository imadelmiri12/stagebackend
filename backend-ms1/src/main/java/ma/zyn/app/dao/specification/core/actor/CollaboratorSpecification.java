package  ma.zyn.app.dao.specification.core.actor;

import ma.zyn.app.dao.criteria.core.actor.CollaboratorCriteria;
import ma.zyn.app.bean.core.actor.Collaborator;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class CollaboratorSpecification extends  AbstractSpecification<CollaboratorCriteria, Collaborator>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateBool("accountNonExpired", criteria.getAccountNonExpired());
        addPredicate("username", criteria.getUsername(),criteria.getUsernameLike());
        addPredicateBool("passwordChanged", criteria.getPasswordChanged());
        addPredicateBool("accountNonLocked", criteria.getAccountNonLocked());
        addPredicate("password", criteria.getPassword(),criteria.getPasswordLike());
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
        addPredicateBool("enabled", criteria.getEnabled());
        addPredicateBool("credentialsNonExpired", criteria.getCredentialsNonExpired());
        addPredicateFk("typeCollaborator","id", criteria.getTypeCollaborator()==null?null:criteria.getTypeCollaborator().getId());
        addPredicateFk("typeCollaborator","id", criteria.getTypeCollaborators());
        addPredicateFk("typeCollaborator","code", criteria.getTypeCollaborator()==null?null:criteria.getTypeCollaborator().getCode());
    }

    public CollaboratorSpecification(CollaboratorCriteria criteria) {
        super(criteria);
    }

    public CollaboratorSpecification(CollaboratorCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
