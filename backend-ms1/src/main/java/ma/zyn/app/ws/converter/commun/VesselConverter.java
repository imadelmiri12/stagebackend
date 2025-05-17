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
import ma.zyn.app.bean.core.commun.Vessel;
import ma.zyn.app.ws.dto.commun.VesselDto;

@Component
public class VesselConverter {



    public Vessel toItem(VesselDto dto) {
        if (dto == null) {
            return null;
        } else {
        Vessel item = new Vessel();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLabel()))
                item.setLabel(dto.getLabel());
            if(StringUtil.isNotEmpty(dto.getStyle()))
                item.setStyle(dto.getStyle());
            if(StringUtil.isNotEmpty(dto.getLoa()))
                item.setLoa(dto.getLoa());
            if(StringUtil.isNotEmpty(dto.getDraft()))
                item.setDraft(dto.getDraft());
            if(StringUtil.isNotEmpty(dto.getAirDraft()))
                item.setAirDraft(dto.getAirDraft());



        return item;
        }
    }


    public VesselDto toDto(Vessel item) {
        if (item == null) {
            return null;
        } else {
            VesselDto dto = new VesselDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLabel()))
                dto.setLabel(item.getLabel());
            if(StringUtil.isNotEmpty(item.getStyle()))
                dto.setStyle(item.getStyle());
            if(StringUtil.isNotEmpty(item.getLoa()))
                dto.setLoa(item.getLoa());
            if(StringUtil.isNotEmpty(item.getDraft()))
                dto.setDraft(item.getDraft());
            if(StringUtil.isNotEmpty(item.getAirDraft()))
                dto.setAirDraft(item.getAirDraft());


        return dto;
        }
    }


	
    public List<Vessel> toItem(List<VesselDto> dtos) {
        List<Vessel> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (VesselDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<VesselDto> toDto(List<Vessel> items) {
        List<VesselDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Vessel item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(VesselDto dto, Vessel t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Vessel> copy(List<VesselDto> dtos) {
        List<Vessel> result = new ArrayList<>();
        if (dtos != null) {
            for (VesselDto dto : dtos) {
                Vessel instance = new Vessel();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
