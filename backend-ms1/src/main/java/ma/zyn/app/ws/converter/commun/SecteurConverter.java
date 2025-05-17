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
import ma.zyn.app.bean.core.commun.Secteur;
import ma.zyn.app.ws.dto.commun.SecteurDto;

@Component
public class SecteurConverter {



    public Secteur toItem(SecteurDto dto) {
        if (dto == null) {
            return null;
        } else {
        Secteur item = new Secteur();
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


    public SecteurDto toDto(Secteur item) {
        if (item == null) {
            return null;
        } else {
            SecteurDto dto = new SecteurDto();
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


	
    public List<Secteur> toItem(List<SecteurDto> dtos) {
        List<Secteur> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (SecteurDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<SecteurDto> toDto(List<Secteur> items) {
        List<SecteurDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Secteur item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(SecteurDto dto, Secteur t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Secteur> copy(List<SecteurDto> dtos) {
        List<Secteur> result = new ArrayList<>();
        if (dtos != null) {
            for (SecteurDto dto : dtos) {
                Secteur instance = new Secteur();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
