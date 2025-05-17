package  ma.zyn.app.ws.dto.actor;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import ma.zyn.app.zynerator.security.bean.Role;
import java.util.Collection;
import ma.zyn.app.zynerator.security.ws.dto.UserDto;


import ma.zyn.app.ws.dto.commun.TypeCollaboratorDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class CollaboratorDto  extends UserDto {

    private String description  ;

    private TypeCollaboratorDto typeCollaborator ;



    private Collection<Role> roles;
    public CollaboratorDto(){
        super();
    }




    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }










    public TypeCollaboratorDto getTypeCollaborator(){
        return this.typeCollaborator;
    }

    public void setTypeCollaborator(TypeCollaboratorDto typeCollaborator){
        this.typeCollaborator = typeCollaborator;
    }







    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
