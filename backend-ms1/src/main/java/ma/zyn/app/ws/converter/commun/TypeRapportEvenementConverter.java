package  ma.zyn.app.ws.converter.commun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.commun.TypeRapportEvenement;
import ma.zyn.app.ws.dto.commun.TypeRapportEvenementDto;

@Component
public class TypeRapportEvenementConverter {



    public TypeRapportEvenement toItem(TypeRapportEvenementDto dto) {
        if (dto == null) {
            return null;
        } else {
        TypeRapportEvenement item = new TypeRapportEvenement();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getIndexation()))
                item.setIndexation(dto.getIndexation());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLabel()))
                item.setLabel(dto.getLabel());
            if(StringUtil.isNotEmpty(dto.getStyle()))
                item.setStyle(dto.getStyle());
            if(StringUtil.isNotEmpty(dto.getColor()))
                item.setColor(dto.getColor());



        return item;
        }
    }


    public TypeRapportEvenementDto toDto(TypeRapportEvenement item) {
        if (item == null) {
            return null;
        } else {
            TypeRapportEvenementDto dto = new TypeRapportEvenementDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getIndexation()))
                dto.setIndexation(item.getIndexation());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLabel()))
                dto.setLabel(item.getLabel());
            if(StringUtil.isNotEmpty(item.getStyle()))
                dto.setStyle(item.getStyle());
            if(StringUtil.isNotEmpty(item.getColor()))
                dto.setColor(item.getColor());


        return dto;
        }
    }


	
    public List<TypeRapportEvenement> toItem(List<TypeRapportEvenementDto> dtos) {
        List<TypeRapportEvenement> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TypeRapportEvenementDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TypeRapportEvenementDto> toDto(List<TypeRapportEvenement> items) {
        List<TypeRapportEvenementDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (TypeRapportEvenement item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TypeRapportEvenementDto dto, TypeRapportEvenement t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<TypeRapportEvenement> copy(List<TypeRapportEvenementDto> dtos) {
        List<TypeRapportEvenement> result = new ArrayList<>();
        if (dtos != null) {
            for (TypeRapportEvenementDto dto : dtos) {
                TypeRapportEvenement instance = new TypeRapportEvenement();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
