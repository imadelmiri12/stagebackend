package  ma.zyn.app.ws.converter.mensuel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zyn.app.zynerator.util.ListUtil;

import ma.zyn.app.ws.converter.mensuel.RapportEvenementMensuelConverter;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuel;
import ma.zyn.app.ws.converter.mensuel.RapportEvenementMensuelSecteurDetailConverter;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelSecteurDetail;
import ma.zyn.app.ws.converter.commun.SecteurConverter;
import ma.zyn.app.bean.core.commun.Secteur;
import ma.zyn.app.ws.converter.commun.TypeEvenementConverter;
import ma.zyn.app.bean.core.commun.TypeEvenement;

import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuel;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelSecteur;
import ma.zyn.app.ws.dto.mensuel.RapportEvenementMensuelSecteurDto;

@Component
public class RapportEvenementMensuelSecteurConverter {

    @Autowired
    private RapportEvenementMensuelConverter rapportEvenementMensuelConverter ;
    @Autowired
    private RapportEvenementMensuelSecteurDetailConverter rapportEvenementMensuelSecteurDetailConverter ;
    @Autowired
    private SecteurConverter secteurConverter ;
    @Autowired
    private TypeEvenementConverter typeEvenementConverter ;
    private boolean rapportEvenementMensuel;
    private boolean secteur;
    private boolean rapportEvenementMensuelSecteurDetails;

    public  RapportEvenementMensuelSecteurConverter() {
        init(true);
    }

    public RapportEvenementMensuelSecteur toItem(RapportEvenementMensuelSecteurDto dto) {
        if (dto == null) {
            return null;
        } else {
        RapportEvenementMensuelSecteur item = new RapportEvenementMensuelSecteur();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getTotal()))
                item.setTotal(dto.getTotal());
            if(StringUtil.isNotEmpty(dto.getIndicateurGlobal()))
                item.setIndicateurGlobal(dto.getIndicateurGlobal());
            if(StringUtil.isNotEmpty(dto.getTotalCumul()))
                item.setTotalCumul(dto.getTotalCumul());
            if(StringUtil.isNotEmpty(dto.getIndicateurGlobalCumul()))
                item.setIndicateurGlobalCumul(dto.getIndicateurGlobalCumul());
            if(dto.getRapportEvenementMensuel() != null && dto.getRapportEvenementMensuel().getId() != null){
                item.setRapportEvenementMensuel(new RapportEvenementMensuel());
                item.getRapportEvenementMensuel().setId(dto.getRapportEvenementMensuel().getId());
                item.getRapportEvenementMensuel().setCode(dto.getRapportEvenementMensuel().getCode());
            }

            if(this.secteur && dto.getSecteur()!=null)
                item.setSecteur(secteurConverter.toItem(dto.getSecteur())) ;


            if(this.rapportEvenementMensuelSecteurDetails && ListUtil.isNotEmpty(dto.getRapportEvenementMensuelSecteurDetails()))
                item.setRapportEvenementMensuelSecteurDetails(rapportEvenementMensuelSecteurDetailConverter.toItem(dto.getRapportEvenementMensuelSecteurDetails()));


        return item;
        }
    }


    public RapportEvenementMensuelSecteurDto toDto(RapportEvenementMensuelSecteur item) {
        if (item == null) {
            return null;
        } else {
            RapportEvenementMensuelSecteurDto dto = new RapportEvenementMensuelSecteurDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getTotal()))
                dto.setTotal(item.getTotal());
            if(StringUtil.isNotEmpty(item.getIndicateurGlobal()))
                dto.setIndicateurGlobal(item.getIndicateurGlobal());
            if(StringUtil.isNotEmpty(item.getTotalCumul()))
                dto.setTotalCumul(item.getTotalCumul());
            if(StringUtil.isNotEmpty(item.getIndicateurGlobalCumul()))
                dto.setIndicateurGlobalCumul(item.getIndicateurGlobalCumul());
            if(this.rapportEvenementMensuel && item.getRapportEvenementMensuel()!=null) {
                dto.setRapportEvenementMensuel(rapportEvenementMensuelConverter.toDto(item.getRapportEvenementMensuel())) ;

            }
            if(this.secteur && item.getSecteur()!=null) {
                dto.setSecteur(secteurConverter.toDto(item.getSecteur())) ;

            }
        if(this.rapportEvenementMensuelSecteurDetails && ListUtil.isNotEmpty(item.getRapportEvenementMensuelSecteurDetails())){
            rapportEvenementMensuelSecteurDetailConverter.init(true);
            rapportEvenementMensuelSecteurDetailConverter.setRapportEvenementMensuelSecteur(false);
            dto.setRapportEvenementMensuelSecteurDetails(rapportEvenementMensuelSecteurDetailConverter.toDto(item.getRapportEvenementMensuelSecteurDetails()));
            rapportEvenementMensuelSecteurDetailConverter.setRapportEvenementMensuelSecteur(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.rapportEvenementMensuelSecteurDetails = value;
    }
    public void initObject(boolean value) {
        this.rapportEvenementMensuel = value;
        this.secteur = value;
    }
	
    public List<RapportEvenementMensuelSecteur> toItem(List<RapportEvenementMensuelSecteurDto> dtos) {
        List<RapportEvenementMensuelSecteur> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (RapportEvenementMensuelSecteurDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<RapportEvenementMensuelSecteurDto> toDto(List<RapportEvenementMensuelSecteur> items) {
        List<RapportEvenementMensuelSecteurDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (RapportEvenementMensuelSecteur item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(RapportEvenementMensuelSecteurDto dto, RapportEvenementMensuelSecteur t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getRapportEvenementMensuel() == null  && dto.getRapportEvenementMensuel() != null){
            t.setRapportEvenementMensuel(new RapportEvenementMensuel());
        }else if (t.getRapportEvenementMensuel() != null  && dto.getRapportEvenementMensuel() != null){
            t.setRapportEvenementMensuel(null);
            t.setRapportEvenementMensuel(new RapportEvenementMensuel());
        }
        if(t.getSecteur() == null  && dto.getSecteur() != null){
            t.setSecteur(new Secteur());
        }else if (t.getSecteur() != null  && dto.getSecteur() != null){
            t.setSecteur(null);
            t.setSecteur(new Secteur());
        }
        if (dto.getRapportEvenementMensuel() != null)
        rapportEvenementMensuelConverter.copy(dto.getRapportEvenementMensuel(), t.getRapportEvenementMensuel());
        if (dto.getSecteur() != null)
        secteurConverter.copy(dto.getSecteur(), t.getSecteur());
        if (dto.getRapportEvenementMensuelSecteurDetails() != null)
            t.setRapportEvenementMensuelSecteurDetails(rapportEvenementMensuelSecteurDetailConverter.copy(dto.getRapportEvenementMensuelSecteurDetails()));
    }

    public List<RapportEvenementMensuelSecteur> copy(List<RapportEvenementMensuelSecteurDto> dtos) {
        List<RapportEvenementMensuelSecteur> result = new ArrayList<>();
        if (dtos != null) {
            for (RapportEvenementMensuelSecteurDto dto : dtos) {
                RapportEvenementMensuelSecteur instance = new RapportEvenementMensuelSecteur();
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
    public RapportEvenementMensuelSecteurDetailConverter getRapportEvenementMensuelSecteurDetailConverter(){
        return this.rapportEvenementMensuelSecteurDetailConverter;
    }
    public void setRapportEvenementMensuelSecteurDetailConverter(RapportEvenementMensuelSecteurDetailConverter rapportEvenementMensuelSecteurDetailConverter ){
        this.rapportEvenementMensuelSecteurDetailConverter = rapportEvenementMensuelSecteurDetailConverter;
    }
    public SecteurConverter getSecteurConverter(){
        return this.secteurConverter;
    }
    public void setSecteurConverter(SecteurConverter secteurConverter ){
        this.secteurConverter = secteurConverter;
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
    public boolean  isSecteur(){
        return this.secteur;
    }
    public void  setSecteur(boolean secteur){
        this.secteur = secteur;
    }
    public boolean  isRapportEvenementMensuelSecteurDetails(){
        return this.rapportEvenementMensuelSecteurDetails ;
    }
    public void  setRapportEvenementMensuelSecteurDetails(boolean rapportEvenementMensuelSecteurDetails ){
        this.rapportEvenementMensuelSecteurDetails  = rapportEvenementMensuelSecteurDetails ;
    }
}
