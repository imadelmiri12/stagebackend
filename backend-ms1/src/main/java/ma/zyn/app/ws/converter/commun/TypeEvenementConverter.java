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
import ma.zyn.app.bean.core.commun.TypeEvenement;
import ma.zyn.app.ws.dto.commun.TypeEvenementDto;

@Component
public class TypeEvenementConverter {



    public TypeEvenement toItem(TypeEvenementDto dto) {
        if (dto == null) {
            return null;
        } else {
        TypeEvenement item = new TypeEvenement();
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


    public TypeEvenementDto toDto(TypeEvenement item) {
        if (item == null) {
            return null;
        } else {
            TypeEvenementDto dto = new TypeEvenementDto();
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


	
    public List<TypeEvenement> toItem(List<TypeEvenementDto> dtos) {
        List<TypeEvenement> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TypeEvenementDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TypeEvenementDto> toDto(List<TypeEvenement> items) {
        List<TypeEvenementDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (TypeEvenement item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TypeEvenementDto dto, TypeEvenement t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<TypeEvenement> copy(List<TypeEvenementDto> dtos) {
        List<TypeEvenement> result = new ArrayList<>();
        if (dtos != null) {
            for (TypeEvenementDto dto : dtos) {
                TypeEvenement instance = new TypeEvenement();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
