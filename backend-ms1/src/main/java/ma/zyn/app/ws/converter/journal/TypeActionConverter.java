package  ma.zyn.app.ws.converter.journal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.journal.TypeAction;
import ma.zyn.app.ws.dto.journal.TypeActionDto;

@Component
public class TypeActionConverter {



    public TypeAction toItem(TypeActionDto dto) {
        if (dto == null) {
            return null;
        } else {
        TypeAction item = new TypeAction();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLabel()))
                item.setLabel(dto.getLabel());
            if(StringUtil.isNotEmpty(dto.getStyle()))
                item.setStyle(dto.getStyle());



        return item;
        }
    }


    public TypeActionDto toDto(TypeAction item) {
        if (item == null) {
            return null;
        } else {
            TypeActionDto dto = new TypeActionDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLabel()))
                dto.setLabel(item.getLabel());
            if(StringUtil.isNotEmpty(item.getStyle()))
                dto.setStyle(item.getStyle());


        return dto;
        }
    }


	
    public List<TypeAction> toItem(List<TypeActionDto> dtos) {
        List<TypeAction> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TypeActionDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TypeActionDto> toDto(List<TypeAction> items) {
        List<TypeActionDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (TypeAction item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TypeActionDto dto, TypeAction t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<TypeAction> copy(List<TypeActionDto> dtos) {
        List<TypeAction> result = new ArrayList<>();
        if (dtos != null) {
            for (TypeActionDto dto : dtos) {
                TypeAction instance = new TypeAction();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
