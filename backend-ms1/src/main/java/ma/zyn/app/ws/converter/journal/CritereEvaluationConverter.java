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
import ma.zyn.app.bean.core.journal.CritereEvaluation;
import ma.zyn.app.ws.dto.journal.CritereEvaluationDto;

@Component
public class CritereEvaluationConverter {



    public CritereEvaluation toItem(CritereEvaluationDto dto) {
        if (dto == null) {
            return null;
        } else {
        CritereEvaluation item = new CritereEvaluation();
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


    public CritereEvaluationDto toDto(CritereEvaluation item) {
        if (item == null) {
            return null;
        } else {
            CritereEvaluationDto dto = new CritereEvaluationDto();
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


	
    public List<CritereEvaluation> toItem(List<CritereEvaluationDto> dtos) {
        List<CritereEvaluation> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CritereEvaluationDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CritereEvaluationDto> toDto(List<CritereEvaluation> items) {
        List<CritereEvaluationDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (CritereEvaluation item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CritereEvaluationDto dto, CritereEvaluation t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<CritereEvaluation> copy(List<CritereEvaluationDto> dtos) {
        List<CritereEvaluation> result = new ArrayList<>();
        if (dtos != null) {
            for (CritereEvaluationDto dto : dtos) {
                CritereEvaluation instance = new CritereEvaluation();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
