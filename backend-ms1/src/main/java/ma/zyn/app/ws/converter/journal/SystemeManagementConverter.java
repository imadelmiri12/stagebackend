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
import ma.zyn.app.bean.core.journal.SystemeManagement;
import ma.zyn.app.ws.dto.journal.SystemeManagementDto;

@Component
public class SystemeManagementConverter {



    public SystemeManagement toItem(SystemeManagementDto dto) {
        if (dto == null) {
            return null;
        } else {
        SystemeManagement item = new SystemeManagement();
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


    public SystemeManagementDto toDto(SystemeManagement item) {
        if (item == null) {
            return null;
        } else {
            SystemeManagementDto dto = new SystemeManagementDto();
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


	
    public List<SystemeManagement> toItem(List<SystemeManagementDto> dtos) {
        List<SystemeManagement> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (SystemeManagementDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<SystemeManagementDto> toDto(List<SystemeManagement> items) {
        List<SystemeManagementDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (SystemeManagement item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(SystemeManagementDto dto, SystemeManagement t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<SystemeManagement> copy(List<SystemeManagementDto> dtos) {
        List<SystemeManagement> result = new ArrayList<>();
        if (dtos != null) {
            for (SystemeManagementDto dto : dtos) {
                SystemeManagement instance = new SystemeManagement();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
