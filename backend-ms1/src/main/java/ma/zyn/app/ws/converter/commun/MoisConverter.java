package  ma.zyn.app.ws.converter.commun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.indicateur.TrimestreConverter;
import ma.zyn.app.bean.core.indicateur.Trimestre;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.commun.Mois;
import ma.zyn.app.ws.dto.commun.MoisDto;

@Component
public class MoisConverter {

    @Autowired
    private TrimestreConverter trimestreConverter ;
    private boolean trimestre;

    public  MoisConverter() {
        initObject(true);
    }

    public Mois toItem(MoisDto dto) {
        if (dto == null) {
            return null;
        } else {
        Mois item = new Mois();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLabel()))
                item.setLabel(dto.getLabel());
            if(StringUtil.isNotEmpty(dto.getStyle()))
                item.setStyle(dto.getStyle());
            if(StringUtil.isNotEmpty(dto.getNumero()))
                item.setNumero(dto.getNumero());
            if(this.trimestre && dto.getTrimestre()!=null)
                item.setTrimestre(trimestreConverter.toItem(dto.getTrimestre())) ;




        return item;
        }
    }


    public MoisDto toDto(Mois item) {
        if (item == null) {
            return null;
        } else {
            MoisDto dto = new MoisDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLabel()))
                dto.setLabel(item.getLabel());
            if(StringUtil.isNotEmpty(item.getStyle()))
                dto.setStyle(item.getStyle());
            if(StringUtil.isNotEmpty(item.getNumero()))
                dto.setNumero(item.getNumero());
            if(this.trimestre && item.getTrimestre()!=null) {
                dto.setTrimestre(trimestreConverter.toDto(item.getTrimestre())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.trimestre = value;
    }
	
    public List<Mois> toItem(List<MoisDto> dtos) {
        List<Mois> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (MoisDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<MoisDto> toDto(List<Mois> items) {
        List<MoisDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Mois item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(MoisDto dto, Mois t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getTrimestre() == null  && dto.getTrimestre() != null){
            t.setTrimestre(new Trimestre());
        }else if (t.getTrimestre() != null  && dto.getTrimestre() != null){
            t.setTrimestre(null);
            t.setTrimestre(new Trimestre());
        }
        if (dto.getTrimestre() != null)
        trimestreConverter.copy(dto.getTrimestre(), t.getTrimestre());
    }

    public List<Mois> copy(List<MoisDto> dtos) {
        List<Mois> result = new ArrayList<>();
        if (dtos != null) {
            for (MoisDto dto : dtos) {
                Mois instance = new Mois();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public TrimestreConverter getTrimestreConverter(){
        return this.trimestreConverter;
    }
    public void setTrimestreConverter(TrimestreConverter trimestreConverter ){
        this.trimestreConverter = trimestreConverter;
    }
    public boolean  isTrimestre(){
        return this.trimestre;
    }
    public void  setTrimestre(boolean trimestre){
        this.trimestre = trimestre;
    }
}
