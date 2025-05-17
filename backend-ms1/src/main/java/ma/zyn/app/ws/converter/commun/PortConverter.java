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
import ma.zyn.app.bean.core.commun.Port;
import ma.zyn.app.ws.dto.commun.PortDto;

@Component
public class PortConverter {



    public Port toItem(PortDto dto) {
        if (dto == null) {
            return null;
        } else {
        Port item = new Port();
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


    public PortDto toDto(Port item) {
        if (item == null) {
            return null;
        } else {
            PortDto dto = new PortDto();
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


	
    public List<Port> toItem(List<PortDto> dtos) {
        List<Port> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (PortDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<PortDto> toDto(List<Port> items) {
        List<PortDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Port item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(PortDto dto, Port t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Port> copy(List<PortDto> dtos) {
        List<Port> result = new ArrayList<>();
        if (dtos != null) {
            for (PortDto dto : dtos) {
                Port instance = new Port();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
