package  ma.zyn.app.ws.converter.actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.commun.TypeCollaboratorConverter;
import ma.zyn.app.bean.core.commun.TypeCollaborator;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.actor.Collaborator;
import ma.zyn.app.ws.dto.actor.CollaboratorDto;

@Component
public class CollaboratorConverter {

    @Autowired
    private TypeCollaboratorConverter typeCollaboratorConverter ;
    private boolean typeCollaborator;

    public  CollaboratorConverter() {
        initObject(true);
    }

    public Collaborator toItem(CollaboratorDto dto) {
        if (dto == null) {
            return null;
        } else {
        Collaborator item = new Collaborator();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            item.setAccountNonExpired(dto.getAccountNonExpired());
            if(StringUtil.isNotEmpty(dto.getUsername()))
                item.setUsername(dto.getUsername());
            item.setPasswordChanged(dto.getPasswordChanged());
            item.setAccountNonLocked(dto.getAccountNonLocked());
            if(StringUtil.isNotEmpty(dto.getPassword()))
                item.setPassword(dto.getPassword());
            if(StringUtil.isNotEmpty(dto.getEmail()))
                item.setEmail(dto.getEmail());
            item.setEnabled(dto.getEnabled());
            item.setCredentialsNonExpired(dto.getCredentialsNonExpired());
            if(this.typeCollaborator && dto.getTypeCollaborator()!=null)
                item.setTypeCollaborator(typeCollaboratorConverter.toItem(dto.getTypeCollaborator())) ;



            //item.setRoles(dto.getRoles());

        return item;
        }
    }


    public CollaboratorDto toDto(Collaborator item) {
        if (item == null) {
            return null;
        } else {
            CollaboratorDto dto = new CollaboratorDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getAccountNonExpired()))
                dto.setAccountNonExpired(item.getAccountNonExpired());
            if(StringUtil.isNotEmpty(item.getUsername()))
                dto.setUsername(item.getUsername());
            if(StringUtil.isNotEmpty(item.getPasswordChanged()))
                dto.setPasswordChanged(item.getPasswordChanged());
            if(StringUtil.isNotEmpty(item.getAccountNonLocked()))
                dto.setAccountNonLocked(item.getAccountNonLocked());
            if(StringUtil.isNotEmpty(item.getEmail()))
                dto.setEmail(item.getEmail());
            if(StringUtil.isNotEmpty(item.getEnabled()))
                dto.setEnabled(item.getEnabled());
            if(StringUtil.isNotEmpty(item.getCredentialsNonExpired()))
                dto.setCredentialsNonExpired(item.getCredentialsNonExpired());
            if(this.typeCollaborator && item.getTypeCollaborator()!=null) {
                dto.setTypeCollaborator(typeCollaboratorConverter.toDto(item.getTypeCollaborator())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.typeCollaborator = value;
    }
	
    public List<Collaborator> toItem(List<CollaboratorDto> dtos) {
        List<Collaborator> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CollaboratorDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CollaboratorDto> toDto(List<Collaborator> items) {
        List<CollaboratorDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Collaborator item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CollaboratorDto dto, Collaborator t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getTypeCollaborator() == null  && dto.getTypeCollaborator() != null){
            t.setTypeCollaborator(new TypeCollaborator());
        }else if (t.getTypeCollaborator() != null  && dto.getTypeCollaborator() != null){
            t.setTypeCollaborator(null);
            t.setTypeCollaborator(new TypeCollaborator());
        }
        if (dto.getTypeCollaborator() != null)
        typeCollaboratorConverter.copy(dto.getTypeCollaborator(), t.getTypeCollaborator());
    }

    public List<Collaborator> copy(List<CollaboratorDto> dtos) {
        List<Collaborator> result = new ArrayList<>();
        if (dtos != null) {
            for (CollaboratorDto dto : dtos) {
                Collaborator instance = new Collaborator();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public TypeCollaboratorConverter getTypeCollaboratorConverter(){
        return this.typeCollaboratorConverter;
    }
    public void setTypeCollaboratorConverter(TypeCollaboratorConverter typeCollaboratorConverter ){
        this.typeCollaboratorConverter = typeCollaboratorConverter;
    }
    public boolean  isTypeCollaborator(){
        return this.typeCollaborator;
    }
    public void  setTypeCollaborator(boolean typeCollaborator){
        this.typeCollaborator = typeCollaborator;
    }
}
