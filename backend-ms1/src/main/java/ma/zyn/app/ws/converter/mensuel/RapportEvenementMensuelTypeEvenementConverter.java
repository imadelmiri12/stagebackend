package  ma.zyn.app.ws.converter.mensuel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.mensuel.RapportEvenementMensuelConverter;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuel;
import ma.zyn.app.ws.converter.commun.TypeEvenementConverter;
import ma.zyn.app.bean.core.commun.TypeEvenement;

import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuel;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelTypeEvenement;
import ma.zyn.app.ws.dto.mensuel.RapportEvenementMensuelTypeEvenementDto;

@Component
public class RapportEvenementMensuelTypeEvenementConverter {

    @Autowired
    private RapportEvenementMensuelConverter rapportEvenementMensuelConverter ;
    @Autowired
    private TypeEvenementConverter typeEvenementConverter ;
    private boolean rapportEvenementMensuel;
    private boolean typeEvenement;

    public  RapportEvenementMensuelTypeEvenementConverter() {
        initObject(true);
    }

    public RapportEvenementMensuelTypeEvenement toItem(RapportEvenementMensuelTypeEvenementDto dto) {
        if (dto == null) {
            return null;
        } else {
        RapportEvenementMensuelTypeEvenement item = new RapportEvenementMensuelTypeEvenement();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getTotal()))
                item.setTotal(dto.getTotal());
            if(StringUtil.isNotEmpty(dto.getTotalCumul()))
                item.setTotalCumul(dto.getTotalCumul());
            if(StringUtil.isNotEmpty(dto.getIndicateurGlobalCumul()))
                item.setIndicateurGlobalCumul(dto.getIndicateurGlobalCumul());
            if(dto.getRapportEvenementMensuel() != null && dto.getRapportEvenementMensuel().getId() != null){
                item.setRapportEvenementMensuel(new RapportEvenementMensuel());
                item.getRapportEvenementMensuel().setId(dto.getRapportEvenementMensuel().getId());
                item.getRapportEvenementMensuel().setCode(dto.getRapportEvenementMensuel().getCode());
            }

            if(this.typeEvenement && dto.getTypeEvenement()!=null)
                item.setTypeEvenement(typeEvenementConverter.toItem(dto.getTypeEvenement())) ;




        return item;
        }
    }


    public RapportEvenementMensuelTypeEvenementDto toDto(RapportEvenementMensuelTypeEvenement item) {
        if (item == null) {
            return null;
        } else {
            RapportEvenementMensuelTypeEvenementDto dto = new RapportEvenementMensuelTypeEvenementDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getTotal()))
                dto.setTotal(item.getTotal());
            if(StringUtil.isNotEmpty(item.getTotalCumul()))
                dto.setTotalCumul(item.getTotalCumul());
            if(StringUtil.isNotEmpty(item.getIndicateurGlobalCumul()))
                dto.setIndicateurGlobalCumul(item.getIndicateurGlobalCumul());
            if(this.rapportEvenementMensuel && item.getRapportEvenementMensuel()!=null) {
                dto.setRapportEvenementMensuel(rapportEvenementMensuelConverter.toDto(item.getRapportEvenementMensuel())) ;

            }
            if(this.typeEvenement && item.getTypeEvenement()!=null) {
                dto.setTypeEvenement(typeEvenementConverter.toDto(item.getTypeEvenement())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.rapportEvenementMensuel = value;
        this.typeEvenement = value;
    }
	
    public List<RapportEvenementMensuelTypeEvenement> toItem(List<RapportEvenementMensuelTypeEvenementDto> dtos) {
        List<RapportEvenementMensuelTypeEvenement> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (RapportEvenementMensuelTypeEvenementDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<RapportEvenementMensuelTypeEvenementDto> toDto(List<RapportEvenementMensuelTypeEvenement> items) {
        List<RapportEvenementMensuelTypeEvenementDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (RapportEvenementMensuelTypeEvenement item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(RapportEvenementMensuelTypeEvenementDto dto, RapportEvenementMensuelTypeEvenement t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getRapportEvenementMensuel() == null  && dto.getRapportEvenementMensuel() != null){
            t.setRapportEvenementMensuel(new RapportEvenementMensuel());
        }else if (t.getRapportEvenementMensuel() != null  && dto.getRapportEvenementMensuel() != null){
            t.setRapportEvenementMensuel(null);
            t.setRapportEvenementMensuel(new RapportEvenementMensuel());
        }
        if(t.getTypeEvenement() == null  && dto.getTypeEvenement() != null){
            t.setTypeEvenement(new TypeEvenement());
        }else if (t.getTypeEvenement() != null  && dto.getTypeEvenement() != null){
            t.setTypeEvenement(null);
            t.setTypeEvenement(new TypeEvenement());
        }
        if (dto.getRapportEvenementMensuel() != null)
        rapportEvenementMensuelConverter.copy(dto.getRapportEvenementMensuel(), t.getRapportEvenementMensuel());
        if (dto.getTypeEvenement() != null)
        typeEvenementConverter.copy(dto.getTypeEvenement(), t.getTypeEvenement());
    }

    public List<RapportEvenementMensuelTypeEvenement> copy(List<RapportEvenementMensuelTypeEvenementDto> dtos) {
        List<RapportEvenementMensuelTypeEvenement> result = new ArrayList<>();
        if (dtos != null) {
            for (RapportEvenementMensuelTypeEvenementDto dto : dtos) {
                RapportEvenementMensuelTypeEvenement instance = new RapportEvenementMensuelTypeEvenement();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public RapportEvenementMensuelConverter getRapportEvenementMensuelConverter(){
        return this.rapportEvenementMensuelConverter;
    }
    public void setRapportEvenementMensuelConverter(RapportEvenementMensuelConverter rapportEvenementMensuelConverter ){
        this.rapportEvenementMensuelConverter = rapportEvenementMensuelConverter;
    }
    public TypeEvenementConverter getTypeEvenementConverter(){
        return this.typeEvenementConverter;
    }
    public void setTypeEvenementConverter(TypeEvenementConverter typeEvenementConverter ){
        this.typeEvenementConverter = typeEvenementConverter;
    }
    public boolean  isRapportEvenementMensuel(){
        return this.rapportEvenementMensuel;
    }
    public void  setRapportEvenementMensuel(boolean rapportEvenementMensuel){
        this.rapportEvenementMensuel = rapportEvenementMensuel;
    }
    public boolean  isTypeEvenement(){
        return this.typeEvenement;
    }
    public void  setTypeEvenement(boolean typeEvenement){
        this.typeEvenement = typeEvenement;
    }
}
