package  ma.zyn.app.ws.converter.mensuel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zyn.app.zynerator.util.ListUtil;

import ma.zyn.app.ws.converter.mensuel.RapportEvenementMensuelSecteurDetailConverter;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelSecteurDetail;
import ma.zyn.app.ws.converter.commun.SecteurConverter;
import ma.zyn.app.bean.core.commun.Secteur;
import ma.zyn.app.ws.converter.mensuel.RapportEvenementMensuelTypeEvenementConverter;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelTypeEvenement;
import ma.zyn.app.ws.converter.commun.TypeEvenementConverter;
import ma.zyn.app.bean.core.commun.TypeEvenement;
import ma.zyn.app.ws.converter.mensuel.RapportEvenementMensuelSecteurConverter;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelSecteur;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuel;
import ma.zyn.app.ws.dto.mensuel.RapportEvenementMensuelDto;

@Component
public class RapportEvenementMensuelConverter {

    @Autowired
    private RapportEvenementMensuelSecteurDetailConverter rapportEvenementMensuelSecteurDetailConverter ;
    @Autowired
    private SecteurConverter secteurConverter ;
    @Autowired
    private RapportEvenementMensuelTypeEvenementConverter rapportEvenementMensuelTypeEvenementConverter ;
    @Autowired
    private TypeEvenementConverter typeEvenementConverter ;
    @Autowired
    private RapportEvenementMensuelSecteurConverter rapportEvenementMensuelSecteurConverter ;
    private boolean rapportEvenementMensuelSecteurs;
    private boolean rapportEvenementMensuelTypeEvenements;

    public  RapportEvenementMensuelConverter() {
        initList(true);
    }

    public RapportEvenementMensuel toItem(RapportEvenementMensuelDto dto) {
        if (dto == null) {
            return null;
        } else {
        RapportEvenementMensuel item = new RapportEvenementMensuel();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getDateEmission()))
                item.setDateEmission(DateUtil.stringEnToDate(dto.getDateEmission()));
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getMois()))
                item.setMois(dto.getMois());
            if(StringUtil.isNotEmpty(dto.getAnnee()))
                item.setAnnee(dto.getAnnee());
            if(StringUtil.isNotEmpty(dto.getNombreMouvement()))
                item.setNombreMouvement(dto.getNombreMouvement());
            if(StringUtil.isNotEmpty(dto.getTotal()))
                item.setTotal(dto.getTotal());
            if(StringUtil.isNotEmpty(dto.getIndicateurGlobal()))
                item.setIndicateurGlobal(dto.getIndicateurGlobal());
            if(StringUtil.isNotEmpty(dto.getNombreMouvementCumul()))
                item.setNombreMouvementCumul(dto.getNombreMouvementCumul());
            if(StringUtil.isNotEmpty(dto.getTotalCumul()))
                item.setTotalCumul(dto.getTotalCumul());
            if(StringUtil.isNotEmpty(dto.getIndicateurGlobalCumul()))
                item.setIndicateurGlobalCumul(dto.getIndicateurGlobalCumul());

            if(this.rapportEvenementMensuelSecteurs && ListUtil.isNotEmpty(dto.getRapportEvenementMensuelSecteurs()))
                item.setRapportEvenementMensuelSecteurs(rapportEvenementMensuelSecteurConverter.toItem(dto.getRapportEvenementMensuelSecteurs()));
            if(this.rapportEvenementMensuelTypeEvenements && ListUtil.isNotEmpty(dto.getRapportEvenementMensuelTypeEvenements()))
                item.setRapportEvenementMensuelTypeEvenements(rapportEvenementMensuelTypeEvenementConverter.toItem(dto.getRapportEvenementMensuelTypeEvenements()));


        return item;
        }
    }


    public RapportEvenementMensuelDto toDto(RapportEvenementMensuel item) {
        if (item == null) {
            return null;
        } else {
            RapportEvenementMensuelDto dto = new RapportEvenementMensuelDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(item.getDateEmission()!=null)
                dto.setDateEmission(DateUtil.dateTimeToString(item.getDateEmission()));
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getMois()))
                dto.setMois(item.getMois());
            if(StringUtil.isNotEmpty(item.getAnnee()))
                dto.setAnnee(item.getAnnee());
            if(StringUtil.isNotEmpty(item.getNombreMouvement()))
                dto.setNombreMouvement(item.getNombreMouvement());
            if(StringUtil.isNotEmpty(item.getTotal()))
                dto.setTotal(item.getTotal());
            if(StringUtil.isNotEmpty(item.getIndicateurGlobal()))
                dto.setIndicateurGlobal(item.getIndicateurGlobal());
            if(StringUtil.isNotEmpty(item.getNombreMouvementCumul()))
                dto.setNombreMouvementCumul(item.getNombreMouvementCumul());
            if(StringUtil.isNotEmpty(item.getTotalCumul()))
                dto.setTotalCumul(item.getTotalCumul());
            if(StringUtil.isNotEmpty(item.getIndicateurGlobalCumul()))
                dto.setIndicateurGlobalCumul(item.getIndicateurGlobalCumul());
        if(this.rapportEvenementMensuelSecteurs && ListUtil.isNotEmpty(item.getRapportEvenementMensuelSecteurs())){
            rapportEvenementMensuelSecteurConverter.init(true);
            rapportEvenementMensuelSecteurConverter.setRapportEvenementMensuel(false);
            dto.setRapportEvenementMensuelSecteurs(rapportEvenementMensuelSecteurConverter.toDto(item.getRapportEvenementMensuelSecteurs()));
            rapportEvenementMensuelSecteurConverter.setRapportEvenementMensuel(true);

        }
        if(this.rapportEvenementMensuelTypeEvenements && ListUtil.isNotEmpty(item.getRapportEvenementMensuelTypeEvenements())){
            rapportEvenementMensuelTypeEvenementConverter.init(true);
            rapportEvenementMensuelTypeEvenementConverter.setRapportEvenementMensuel(false);
            dto.setRapportEvenementMensuelTypeEvenements(rapportEvenementMensuelTypeEvenementConverter.toDto(item.getRapportEvenementMensuelTypeEvenements()));
            rapportEvenementMensuelTypeEvenementConverter.setRapportEvenementMensuel(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.rapportEvenementMensuelSecteurs = value;
        this.rapportEvenementMensuelTypeEvenements = value;
    }
	
    public List<RapportEvenementMensuel> toItem(List<RapportEvenementMensuelDto> dtos) {
        List<RapportEvenementMensuel> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (RapportEvenementMensuelDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<RapportEvenementMensuelDto> toDto(List<RapportEvenementMensuel> items) {
        List<RapportEvenementMensuelDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (RapportEvenementMensuel item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(RapportEvenementMensuelDto dto, RapportEvenementMensuel t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getRapportEvenementMensuelSecteurs() != null)
            t.setRapportEvenementMensuelSecteurs(rapportEvenementMensuelSecteurConverter.copy(dto.getRapportEvenementMensuelSecteurs()));
        if (dto.getRapportEvenementMensuelTypeEvenements() != null)
            t.setRapportEvenementMensuelTypeEvenements(rapportEvenementMensuelTypeEvenementConverter.copy(dto.getRapportEvenementMensuelTypeEvenements()));
    }

    public List<RapportEvenementMensuel> copy(List<RapportEvenementMensuelDto> dtos) {
        List<RapportEvenementMensuel> result = new ArrayList<>();
        if (dtos != null) {
            for (RapportEvenementMensuelDto dto : dtos) {
                RapportEvenementMensuel instance = new RapportEvenementMensuel();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
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
    public RapportEvenementMensuelTypeEvenementConverter getRapportEvenementMensuelTypeEvenementConverter(){
        return this.rapportEvenementMensuelTypeEvenementConverter;
    }
    public void setRapportEvenementMensuelTypeEvenementConverter(RapportEvenementMensuelTypeEvenementConverter rapportEvenementMensuelTypeEvenementConverter ){
        this.rapportEvenementMensuelTypeEvenementConverter = rapportEvenementMensuelTypeEvenementConverter;
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
    public boolean  isRapportEvenementMensuelSecteurs(){
        return this.rapportEvenementMensuelSecteurs ;
    }
    public void  setRapportEvenementMensuelSecteurs(boolean rapportEvenementMensuelSecteurs ){
        this.rapportEvenementMensuelSecteurs  = rapportEvenementMensuelSecteurs ;
    }
    public boolean  isRapportEvenementMensuelTypeEvenements(){
        return this.rapportEvenementMensuelTypeEvenements ;
    }
    public void  setRapportEvenementMensuelTypeEvenements(boolean rapportEvenementMensuelTypeEvenements ){
        this.rapportEvenementMensuelTypeEvenements  = rapportEvenementMensuelTypeEvenements ;
    }
}
