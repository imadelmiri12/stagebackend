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
import ma.zyn.app.bean.core.commun.TypeVessel;
import ma.zyn.app.ws.dto.commun.TypeVesselDto;

@Component
public class TypeVesselConverter {



    public TypeVessel toItem(TypeVesselDto dto) {
        if (dto == null) {
            return null;
        } else {
        TypeVessel item = new TypeVessel();
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


    public TypeVesselDto toDto(TypeVessel item) {
        if (item == null) {
            return null;
        } else {
            TypeVesselDto dto = new TypeVesselDto();
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


	
    public List<TypeVessel> toItem(List<TypeVesselDto> dtos) {
        List<TypeVessel> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TypeVesselDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TypeVesselDto> toDto(List<TypeVessel> items) {
        List<TypeVesselDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (TypeVessel item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TypeVesselDto dto, TypeVessel t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<TypeVessel> copy(List<TypeVesselDto> dtos) {
        List<TypeVessel> result = new ArrayList<>();
        if (dtos != null) {
            for (TypeVesselDto dto : dtos) {
                TypeVessel instance = new TypeVessel();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
