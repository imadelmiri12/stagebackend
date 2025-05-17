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
import ma.zyn.app.bean.core.journal.OrigineEcrate;
import ma.zyn.app.ws.dto.journal.OrigineEcrateDto;

@Component
public class OrigineEcrateConverter {



    public OrigineEcrate toItem(OrigineEcrateDto dto) {
        if (dto == null) {
            return null;
        } else {
        OrigineEcrate item = new OrigineEcrate();
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


    public OrigineEcrateDto toDto(OrigineEcrate item) {
        if (item == null) {
            return null;
        } else {
            OrigineEcrateDto dto = new OrigineEcrateDto();
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


	
    public List<OrigineEcrate> toItem(List<OrigineEcrateDto> dtos) {
        List<OrigineEcrate> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (OrigineEcrateDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<OrigineEcrateDto> toDto(List<OrigineEcrate> items) {
        List<OrigineEcrateDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (OrigineEcrate item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(OrigineEcrateDto dto, OrigineEcrate t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<OrigineEcrate> copy(List<OrigineEcrateDto> dtos) {
        List<OrigineEcrate> result = new ArrayList<>();
        if (dtos != null) {
            for (OrigineEcrateDto dto : dtos) {
                OrigineEcrate instance = new OrigineEcrate();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
