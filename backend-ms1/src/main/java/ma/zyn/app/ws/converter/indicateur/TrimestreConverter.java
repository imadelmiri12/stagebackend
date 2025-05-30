package  ma.zyn.app.ws.converter.indicateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.indicateur.Trimestre;
import ma.zyn.app.ws.dto.indicateur.TrimestreDto;

@Component
public class TrimestreConverter {



    public Trimestre toItem(TrimestreDto dto) {
        if (dto == null) {
            return null;
        } else {
        Trimestre item = new Trimestre();
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


    public TrimestreDto toDto(Trimestre item) {
        if (item == null) {
            return null;
        } else {
            TrimestreDto dto = new TrimestreDto();
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


	
    public List<Trimestre> toItem(List<TrimestreDto> dtos) {
        List<Trimestre> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TrimestreDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TrimestreDto> toDto(List<Trimestre> items) {
        List<TrimestreDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Trimestre item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TrimestreDto dto, Trimestre t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Trimestre> copy(List<TrimestreDto> dtos) {
        List<Trimestre> result = new ArrayList<>();
        if (dtos != null) {
            for (TrimestreDto dto : dtos) {
                Trimestre instance = new Trimestre();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
