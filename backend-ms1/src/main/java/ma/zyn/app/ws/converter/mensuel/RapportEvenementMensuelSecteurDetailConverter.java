package  ma.zyn.app.ws.converter.mensuel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.commun.TypeEvenementConverter;
import ma.zyn.app.bean.core.commun.TypeEvenement;
import ma.zyn.app.ws.converter.mensuel.RapportEvenementMensuelSecteurConverter;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelSecteur;

import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelSecteur;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelSecteurDetail;
import ma.zyn.app.ws.dto.mensuel.RapportEvenementMensuelSecteurDetailDto;

@Component
public class RapportEvenementMensuelSecteurDetailConverter {

    @Autowired
    private TypeEvenementConverter typeEvenementConverter ;
    @Autowired
    private RapportEvenementMensuelSecteurConverter rapportEvenementMensuelSecteurConverter ;
    private boolean rapportEvenementMensuelSecteur;
    private boolean typeEvenement;

    public  RapportEvenementMensuelSecteurDetailConverter() {
        initObject(true);
    }

    public RapportEvenementMensuelSecteurDetail toItem(RapportEvenementMensuelSecteurDetailDto dto) {
        if (dto == null) {
            return null;
        } else {
        RapportEvenementMensuelSecteurDetail item = new RapportEvenementMensuelSecteurDetail();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getTotal()))
                item.setTotal(dto.getTotal());
            if(StringUtil.isNotEmpty(dto.getIndicateurGlobal()))
                item.setIndicateurGlobal(dto.getIndicateurGlobal());
            if(dto.getRapportEvenementMensuelSecteur() != null && dto.getRapportEvenementMensuelSecteur().getId() != null){
                item.setRapportEvenementMensuelSecteur(new RapportEvenementMensuelSecteur());
                item.getRapportEvenementMensuelSecteur().setId(dto.getRapportEvenementMensuelSecteur().getId());
                item.getRapportEvenementMensuelSecteur().setId(dto.getRapportEvenementMensuelSecteur().getId());
            }

            if(this.typeEvenement && dto.getTypeEvenement()!=null)
                item.setTypeEvenement(typeEvenementConverter.toItem(dto.getTypeEvenement())) ;




        return item;
        }
    }


    public RapportEvenementMensuelSecteurDetailDto toDto(RapportEvenementMensuelSecteurDetail item) {
        if (item == null) {
            return null;
        } else {
            RapportEvenementMensuelSecteurDetailDto dto = new RapportEvenementMensuelSecteurDetailDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getTotal()))
                dto.setTotal(item.getTotal());
            if(StringUtil.isNotEmpty(item.getIndicateurGlobal()))
                dto.setIndicateurGlobal(item.getIndicateurGlobal());
            if(this.rapportEvenementMensuelSecteur && item.getRapportEvenementMensuelSecteur()!=null) {
                dto.setRapportEvenementMensuelSecteur(rapportEvenementMensuelSecteurConverter.toDto(item.getRapportEvenementMensuelSecteur())) ;

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
        this.rapportEvenementMensuelSecteur = value;
        this.typeEvenement = value;
    }
	
    public List<RapportEvenementMensuelSecteurDetail> toItem(List<RapportEvenementMensuelSecteurDetailDto> dtos) {
        List<RapportEvenementMensuelSecteurDetail> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (RapportEvenementMensuelSecteurDetailDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<RapportEvenementMensuelSecteurDetailDto> toDto(List<RapportEvenementMensuelSecteurDetail> items) {
        List<RapportEvenementMensuelSecteurDetailDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (RapportEvenementMensuelSecteurDetail item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(RapportEvenementMensuelSecteurDetailDto dto, RapportEvenementMensuelSecteurDetail t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getRapportEvenementMensuelSecteur() == null  && dto.getRapportEvenementMensuelSecteur() != null){
            t.setRapportEvenementMensuelSecteur(new RapportEvenementMensuelSecteur());
        }else if (t.getRapportEvenementMensuelSecteur() != null  && dto.getRapportEvenementMensuelSecteur() != null){
            t.setRapportEvenementMensuelSecteur(null);
            t.setRapportEvenementMensuelSecteur(new RapportEvenementMensuelSecteur());
        }
        if(t.getTypeEvenement() == null  && dto.getTypeEvenement() != null){
            t.setTypeEvenement(new TypeEvenement());
        }else if (t.getTypeEvenement() != null  && dto.getTypeEvenement() != null){
            t.setTypeEvenement(null);
            t.setTypeEvenement(new TypeEvenement());
        }
        if (dto.getRapportEvenementMensuelSecteur() != null)
        rapportEvenementMensuelSecteurConverter.copy(dto.getRapportEvenementMensuelSecteur(), t.getRapportEvenementMensuelSecteur());
        if (dto.getTypeEvenement() != null)
        typeEvenementConverter.copy(dto.getTypeEvenement(), t.getTypeEvenement());
    }

    public List<RapportEvenementMensuelSecteurDetail> copy(List<RapportEvenementMensuelSecteurDetailDto> dtos) {
        List<RapportEvenementMensuelSecteurDetail> result = new ArrayList<>();
        if (dtos != null) {
            for (RapportEvenementMensuelSecteurDetailDto dto : dtos) {
                RapportEvenementMensuelSecteurDetail instance = new RapportEvenementMensuelSecteurDetail();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public TypeEvenementConverter getTypeEvenementConverter(){
        return this.typeEvenementConverter;
    }
    public void setTypeEvenementConverter(TypeEvenementConverter typeEvenementConverter ){
        this.typeEvenementConverter = typeEvenementConverter;
    }
    public RapportEvenementMensuelSecteurConverter getRapportEvenementMensuelSecteurConverter(){
        return this.rapportEvenementMensuelSecteurConverter;
    }
    public void setRapportEvenementMensuelSecteurConverter(RapportEvenementMensuelSecteurConverter rapportEvenementMensuelSecteurConverter ){
        this.rapportEvenementMensuelSecteurConverter = rapportEvenementMensuelSecteurConverter;
    }
    public boolean  isRapportEvenementMensuelSecteur(){
        return this.rapportEvenementMensuelSecteur;
    }
    public void  setRapportEvenementMensuelSecteur(boolean rapportEvenementMensuelSecteur){
        this.rapportEvenementMensuelSecteur = rapportEvenementMensuelSecteur;
    }
    public boolean  isTypeEvenement(){
        return this.typeEvenement;
    }
    public void  setTypeEvenement(boolean typeEvenement){
        this.typeEvenement = typeEvenement;
    }
}
