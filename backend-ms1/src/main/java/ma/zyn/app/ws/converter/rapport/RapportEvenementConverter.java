package  ma.zyn.app.ws.converter.rapport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.actor.CollaboratorConverter;
import ma.zyn.app.bean.core.actor.Collaborator;
import ma.zyn.app.ws.converter.commun.TypeRapportEvenementConverter;
import ma.zyn.app.bean.core.commun.TypeRapportEvenement;
import ma.zyn.app.ws.converter.commun.VesselConverter;
import ma.zyn.app.bean.core.commun.Vessel;
import ma.zyn.app.ws.converter.commun.PortConverter;
import ma.zyn.app.bean.core.commun.Port;
import ma.zyn.app.ws.converter.commun.SecteurConverter;
import ma.zyn.app.bean.core.commun.Secteur;
import ma.zyn.app.ws.converter.commun.TypeEvenementConverter;
import ma.zyn.app.bean.core.commun.TypeEvenement;
import ma.zyn.app.ws.converter.journal.JournalAmelioarationConverter;
import ma.zyn.app.bean.core.journal.JournalAmelioaration;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.rapport.RapportEvenement;
import ma.zyn.app.ws.dto.rapport.RapportEvenementDto;

@Component
public class RapportEvenementConverter {

    @Autowired
    private CollaboratorConverter collaboratorConverter ;
    @Autowired
    private TypeRapportEvenementConverter typeRapportEvenementConverter ;
    @Autowired
    private VesselConverter vesselConverter ;
    @Autowired
    private PortConverter portConverter ;
    @Autowired
    private SecteurConverter secteurConverter ;
    @Autowired
    private TypeEvenementConverter typeEvenementConverter ;
    @Autowired
    private JournalAmelioarationConverter journalAmelioarationConverter ;
    private boolean port;
    private boolean typeEvenement;
    private boolean collaborator;
    private boolean vessel;
    private boolean typeRapportEvenement;
    private boolean secteur;
    private boolean journalAmelioaration;

    public  RapportEvenementConverter() {
        initObject(true);
    }

    public RapportEvenement toItem(RapportEvenementDto dto) {
        if (dto == null) {
            return null;
        } else {
        RapportEvenement item = new RapportEvenement();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getDateEvenement()))
                item.setDateEvenement(DateUtil.stringEnToDate(dto.getDateEvenement()));
            if(StringUtil.isNotEmpty(dto.getDateSoumission()))
                item.setDateSoumission(DateUtil.stringEnToDate(dto.getDateSoumission()));
            if(StringUtil.isNotEmpty(dto.getAttachments()))
                item.setAttachments(dto.getAttachments());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getRecommendation()))
                item.setRecommendation(dto.getRecommendation());
            if(StringUtil.isNotEmpty(dto.getDirectivePmjChef()))
                item.setDirectivePmjChef(dto.getDirectivePmjChef());
            if(StringUtil.isNotEmpty(dto.getMois()))
                item.setMois(dto.getMois());
            if(StringUtil.isNotEmpty(dto.getAnnee()))
                item.setAnnee(dto.getAnnee());
            if(StringUtil.isNotEmpty(dto.getWindDirection()))
                item.setWindDirection(dto.getWindDirection());
            if(StringUtil.isNotEmpty(dto.getWindForce()))
                item.setWindForce(dto.getWindForce());
            if(StringUtil.isNotEmpty(dto.getCurrentDirection()))
                item.setCurrentDirection(dto.getCurrentDirection());
            if(StringUtil.isNotEmpty(dto.getCurrentForce()))
                item.setCurrentForce(dto.getCurrentForce());
            if(StringUtil.isNotEmpty(dto.getSwellHeigth()))
                item.setSwellHeigth(dto.getSwellHeigth());
            if(StringUtil.isNotEmpty(dto.getSwellDirection()))
                item.setSwellDirection(dto.getSwellDirection());
            if(dto.getFonde() != null)
                item.setFonde(dto.getFonde());
            if(this.port && dto.getPort()!=null)
                item.setPort(portConverter.toItem(dto.getPort())) ;

            if(this.typeEvenement && dto.getTypeEvenement()!=null)
                item.setTypeEvenement(typeEvenementConverter.toItem(dto.getTypeEvenement())) ;

            if(this.collaborator && dto.getCollaborator()!=null)
                item.setCollaborator(collaboratorConverter.toItem(dto.getCollaborator())) ;

            if(this.vessel && dto.getVessel()!=null)
                item.setVessel(vesselConverter.toItem(dto.getVessel())) ;

            if(this.typeRapportEvenement && dto.getTypeRapportEvenement()!=null)
                item.setTypeRapportEvenement(typeRapportEvenementConverter.toItem(dto.getTypeRapportEvenement())) ;

            if(this.secteur && dto.getSecteur()!=null)
                item.setSecteur(secteurConverter.toItem(dto.getSecteur())) ;

            if(this.journalAmelioaration && dto.getJournalAmelioaration()!=null)
                item.setJournalAmelioaration(journalAmelioarationConverter.toItem(dto.getJournalAmelioaration())) ;




        return item;
        }
    }


    public RapportEvenementDto toDto(RapportEvenement item) {
        if (item == null) {
            return null;
        } else {
            RapportEvenementDto dto = new RapportEvenementDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(item.getDateEvenement()!=null)
                dto.setDateEvenement(DateUtil.dateTimeToString(item.getDateEvenement()));
            if(item.getDateSoumission()!=null)
                dto.setDateSoumission(DateUtil.dateTimeToString(item.getDateSoumission()));
            if(StringUtil.isNotEmpty(item.getAttachments()))
                dto.setAttachments(item.getAttachments());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getRecommendation()))
                dto.setRecommendation(item.getRecommendation());
            if(StringUtil.isNotEmpty(item.getDirectivePmjChef()))
                dto.setDirectivePmjChef(item.getDirectivePmjChef());
            if(StringUtil.isNotEmpty(item.getMois()))
                dto.setMois(item.getMois());
            if(StringUtil.isNotEmpty(item.getAnnee()))
                dto.setAnnee(item.getAnnee());
            if(StringUtil.isNotEmpty(item.getWindDirection()))
                dto.setWindDirection(item.getWindDirection());
            if(StringUtil.isNotEmpty(item.getWindForce()))
                dto.setWindForce(item.getWindForce());
            if(StringUtil.isNotEmpty(item.getCurrentDirection()))
                dto.setCurrentDirection(item.getCurrentDirection());
            if(StringUtil.isNotEmpty(item.getCurrentForce()))
                dto.setCurrentForce(item.getCurrentForce());
            if(StringUtil.isNotEmpty(item.getSwellHeigth()))
                dto.setSwellHeigth(item.getSwellHeigth());
            if(StringUtil.isNotEmpty(item.getSwellDirection()))
                dto.setSwellDirection(item.getSwellDirection());
                dto.setFonde(item.getFonde());
            if(this.port && item.getPort()!=null) {
                dto.setPort(portConverter.toDto(item.getPort())) ;

            }
            if(this.typeEvenement && item.getTypeEvenement()!=null) {
                dto.setTypeEvenement(typeEvenementConverter.toDto(item.getTypeEvenement())) ;

            }
            if(this.collaborator && item.getCollaborator()!=null) {
                dto.setCollaborator(collaboratorConverter.toDto(item.getCollaborator())) ;

            }
            if(this.vessel && item.getVessel()!=null) {
                dto.setVessel(vesselConverter.toDto(item.getVessel())) ;

            }
            if(this.typeRapportEvenement && item.getTypeRapportEvenement()!=null) {
                dto.setTypeRapportEvenement(typeRapportEvenementConverter.toDto(item.getTypeRapportEvenement())) ;

            }
            if(this.secteur && item.getSecteur()!=null) {
                dto.setSecteur(secteurConverter.toDto(item.getSecteur())) ;

            }
            if(this.journalAmelioaration && item.getJournalAmelioaration()!=null) {
                dto.setJournalAmelioaration(journalAmelioarationConverter.toDto(item.getJournalAmelioaration())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.port = value;
        this.typeEvenement = value;
        this.collaborator = value;
        this.vessel = value;
        this.typeRapportEvenement = value;
        this.secteur = value;
        this.journalAmelioaration = value;
    }
	
    public List<RapportEvenement> toItem(List<RapportEvenementDto> dtos) {
        List<RapportEvenement> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (RapportEvenementDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<RapportEvenementDto> toDto(List<RapportEvenement> items) {
        List<RapportEvenementDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (RapportEvenement item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(RapportEvenementDto dto, RapportEvenement t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getPort() == null  && dto.getPort() != null){
            t.setPort(new Port());
        }else if (t.getPort() != null  && dto.getPort() != null){
            t.setPort(null);
            t.setPort(new Port());
        }
        if(t.getTypeEvenement() == null  && dto.getTypeEvenement() != null){
            t.setTypeEvenement(new TypeEvenement());
        }else if (t.getTypeEvenement() != null  && dto.getTypeEvenement() != null){
            t.setTypeEvenement(null);
            t.setTypeEvenement(new TypeEvenement());
        }
        if(t.getCollaborator() == null  && dto.getCollaborator() != null){
            t.setCollaborator(new Collaborator());
        }else if (t.getCollaborator() != null  && dto.getCollaborator() != null){
            t.setCollaborator(null);
            t.setCollaborator(new Collaborator());
        }
        if(t.getVessel() == null  && dto.getVessel() != null){
            t.setVessel(new Vessel());
        }else if (t.getVessel() != null  && dto.getVessel() != null){
            t.setVessel(null);
            t.setVessel(new Vessel());
        }
        if(t.getTypeRapportEvenement() == null  && dto.getTypeRapportEvenement() != null){
            t.setTypeRapportEvenement(new TypeRapportEvenement());
        }else if (t.getTypeRapportEvenement() != null  && dto.getTypeRapportEvenement() != null){
            t.setTypeRapportEvenement(null);
            t.setTypeRapportEvenement(new TypeRapportEvenement());
        }
        if(t.getSecteur() == null  && dto.getSecteur() != null){
            t.setSecteur(new Secteur());
        }else if (t.getSecteur() != null  && dto.getSecteur() != null){
            t.setSecteur(null);
            t.setSecteur(new Secteur());
        }
        if(t.getJournalAmelioaration() == null  && dto.getJournalAmelioaration() != null){
            t.setJournalAmelioaration(new JournalAmelioaration());
        }else if (t.getJournalAmelioaration() != null  && dto.getJournalAmelioaration() != null){
            t.setJournalAmelioaration(null);
            t.setJournalAmelioaration(new JournalAmelioaration());
        }
        if (dto.getPort() != null)
        portConverter.copy(dto.getPort(), t.getPort());
        if (dto.getTypeEvenement() != null)
        typeEvenementConverter.copy(dto.getTypeEvenement(), t.getTypeEvenement());
        if (dto.getCollaborator() != null)
        collaboratorConverter.copy(dto.getCollaborator(), t.getCollaborator());
        if (dto.getVessel() != null)
        vesselConverter.copy(dto.getVessel(), t.getVessel());
        if (dto.getTypeRapportEvenement() != null)
        typeRapportEvenementConverter.copy(dto.getTypeRapportEvenement(), t.getTypeRapportEvenement());
        if (dto.getSecteur() != null)
        secteurConverter.copy(dto.getSecteur(), t.getSecteur());
        if (dto.getJournalAmelioaration() != null)
        journalAmelioarationConverter.copy(dto.getJournalAmelioaration(), t.getJournalAmelioaration());
    }

    public List<RapportEvenement> copy(List<RapportEvenementDto> dtos) {
        List<RapportEvenement> result = new ArrayList<>();
        if (dtos != null) {
            for (RapportEvenementDto dto : dtos) {
                RapportEvenement instance = new RapportEvenement();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public CollaboratorConverter getCollaboratorConverter(){
        return this.collaboratorConverter;
    }
    public void setCollaboratorConverter(CollaboratorConverter collaboratorConverter ){
        this.collaboratorConverter = collaboratorConverter;
    }
    public TypeRapportEvenementConverter getTypeRapportEvenementConverter(){
        return this.typeRapportEvenementConverter;
    }
    public void setTypeRapportEvenementConverter(TypeRapportEvenementConverter typeRapportEvenementConverter ){
        this.typeRapportEvenementConverter = typeRapportEvenementConverter;
    }
    public VesselConverter getVesselConverter(){
        return this.vesselConverter;
    }
    public void setVesselConverter(VesselConverter vesselConverter ){
        this.vesselConverter = vesselConverter;
    }
    public PortConverter getPortConverter(){
        return this.portConverter;
    }
    public void setPortConverter(PortConverter portConverter ){
        this.portConverter = portConverter;
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
    public JournalAmelioarationConverter getJournalAmelioarationConverter(){
        return this.journalAmelioarationConverter;
    }
    public void setJournalAmelioarationConverter(JournalAmelioarationConverter journalAmelioarationConverter ){
        this.journalAmelioarationConverter = journalAmelioarationConverter;
    }
    public boolean  isPort(){
        return this.port;
    }
    public void  setPort(boolean port){
        this.port = port;
    }
    public boolean  isTypeEvenement(){
        return this.typeEvenement;
    }
    public void  setTypeEvenement(boolean typeEvenement){
        this.typeEvenement = typeEvenement;
    }
    public boolean  isCollaborator(){
        return this.collaborator;
    }
    public void  setCollaborator(boolean collaborator){
        this.collaborator = collaborator;
    }
    public boolean  isVessel(){
        return this.vessel;
    }
    public void  setVessel(boolean vessel){
        this.vessel = vessel;
    }
    public boolean  isTypeRapportEvenement(){
        return this.typeRapportEvenement;
    }
    public void  setTypeRapportEvenement(boolean typeRapportEvenement){
        this.typeRapportEvenement = typeRapportEvenement;
    }
    public boolean  isSecteur(){
        return this.secteur;
    }
    public void  setSecteur(boolean secteur){
        this.secteur = secteur;
    }
    public boolean  isJournalAmelioaration(){
        return this.journalAmelioaration;
    }
    public void  setJournalAmelioaration(boolean journalAmelioaration){
        this.journalAmelioaration = journalAmelioaration;
    }
}
