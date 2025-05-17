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
import ma.zyn.app.bean.core.journal.CategorieEcrate;
import ma.zyn.app.ws.dto.journal.CategorieEcrateDto;

@Component
public class CategorieEcrateConverter {



    public CategorieEcrate toItem(CategorieEcrateDto dto) {
        if (dto == null) {
            return null;
        } else {
        CategorieEcrate item = new CategorieEcrate();
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


    public CategorieEcrateDto toDto(CategorieEcrate item) {
        if (item == null) {
            return null;
        } else {
            CategorieEcrateDto dto = new CategorieEcrateDto();
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


	
    public List<CategorieEcrate> toItem(List<CategorieEcrateDto> dtos) {
        List<CategorieEcrate> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CategorieEcrateDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CategorieEcrateDto> toDto(List<CategorieEcrate> items) {
        List<CategorieEcrateDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (CategorieEcrate item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CategorieEcrateDto dto, CategorieEcrate t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<CategorieEcrate> copy(List<CategorieEcrateDto> dtos) {
        List<CategorieEcrate> result = new ArrayList<>();
        if (dtos != null) {
            for (CategorieEcrateDto dto : dtos) {
                CategorieEcrate instance = new CategorieEcrate();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
