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
import ma.zyn.app.bean.core.journal.ResultatEvaluation;
import ma.zyn.app.ws.dto.journal.ResultatEvaluationDto;

@Component
public class ResultatEvaluationConverter {



    public ResultatEvaluation toItem(ResultatEvaluationDto dto) {
        if (dto == null) {
            return null;
        } else {
        ResultatEvaluation item = new ResultatEvaluation();
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


    public ResultatEvaluationDto toDto(ResultatEvaluation item) {
        if (item == null) {
            return null;
        } else {
            ResultatEvaluationDto dto = new ResultatEvaluationDto();
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


	
    public List<ResultatEvaluation> toItem(List<ResultatEvaluationDto> dtos) {
        List<ResultatEvaluation> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ResultatEvaluationDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ResultatEvaluationDto> toDto(List<ResultatEvaluation> items) {
        List<ResultatEvaluationDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ResultatEvaluation item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ResultatEvaluationDto dto, ResultatEvaluation t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<ResultatEvaluation> copy(List<ResultatEvaluationDto> dtos) {
        List<ResultatEvaluation> result = new ArrayList<>();
        if (dtos != null) {
            for (ResultatEvaluationDto dto : dtos) {
                ResultatEvaluation instance = new ResultatEvaluation();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
