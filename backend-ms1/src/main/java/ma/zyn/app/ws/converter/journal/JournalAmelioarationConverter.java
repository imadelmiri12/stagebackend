package  ma.zyn.app.ws.converter.journal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.journal.TypeActionConverter;
import ma.zyn.app.bean.core.journal.TypeAction;
import ma.zyn.app.ws.converter.actor.CollaboratorConverter;
import ma.zyn.app.bean.core.actor.Collaborator;
import ma.zyn.app.ws.converter.journal.ResultatEvaluationConverter;
import ma.zyn.app.bean.core.journal.ResultatEvaluation;
import ma.zyn.app.ws.converter.journal.SystemeManagementConverter;
import ma.zyn.app.bean.core.journal.SystemeManagement;
import ma.zyn.app.ws.converter.journal.CritereEvaluationConverter;
import ma.zyn.app.bean.core.journal.CritereEvaluation;
import ma.zyn.app.ws.converter.journal.CategorieEcrateConverter;
import ma.zyn.app.bean.core.journal.CategorieEcrate;
import ma.zyn.app.ws.converter.journal.OrigineEcrateConverter;
import ma.zyn.app.bean.core.journal.OrigineEcrate;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.journal.JournalAmelioaration;
import ma.zyn.app.ws.dto.journal.JournalAmelioarationDto;

@Component
public class JournalAmelioarationConverter {

    @Autowired
    private TypeActionConverter typeActionConverter ;
    @Autowired
    private CollaboratorConverter collaboratorConverter ;
    @Autowired
    private ResultatEvaluationConverter resultatEvaluationConverter ;
    @Autowired
    private SystemeManagementConverter systemeManagementConverter ;
    @Autowired
    private CritereEvaluationConverter critereEvaluationConverter ;
    @Autowired
    private CategorieEcrateConverter categorieEcrateConverter ;
    @Autowired
    private OrigineEcrateConverter origineEcrateConverter ;
    private boolean systemeManagement;
    private boolean categorieEcrate;
    private boolean origineEcrate;
    private boolean typeAction;
    private boolean collaborator;
    private boolean critereEvaluation;
    private boolean resultatEvaluation;

    public  JournalAmelioarationConverter() {
        initObject(true);
    }

    public JournalAmelioaration toItem(JournalAmelioarationDto dto) {
        if (dto == null) {
            return null;
        } else {
        JournalAmelioaration item = new JournalAmelioaration();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getDate()))
                item.setDate(DateUtil.stringEnToDate(dto.getDate()));
            if(StringUtil.isNotEmpty(dto.getAnnee()))
                item.setAnnee(dto.getAnnee());
            if(StringUtil.isNotEmpty(dto.getDateButoir()))
                item.setDateButoir(DateUtil.stringEnToDate(dto.getDateButoir()));
            if(StringUtil.isNotEmpty(dto.getDateRealisation()))
                item.setDateRealisation(DateUtil.stringEnToDate(dto.getDateRealisation()));
            if(StringUtil.isNotEmpty(dto.getDateEvaluation()))
                item.setDateEvaluation(DateUtil.stringEnToDate(dto.getDateEvaluation()));
            if(StringUtil.isNotEmpty(dto.getDateCloture()))
                item.setDateCloture(DateUtil.stringEnToDate(dto.getDateCloture()));
            if(StringUtil.isNotEmpty(dto.getAvancement()))
                item.setAvancement(dto.getAvancement());
            if(StringUtil.isNotEmpty(dto.getDescriptionEcrat()))
                item.setDescriptionEcrat(dto.getDescriptionEcrat());
            if(StringUtil.isNotEmpty(dto.getDescriptionAction()))
                item.setDescriptionAction(dto.getDescriptionAction());
            if(StringUtil.isNotEmpty(dto.getCommentaire()))
                item.setCommentaire(dto.getCommentaire());
            if(StringUtil.isNotEmpty(dto.getCauseSuppose()))
                item.setCauseSuppose(dto.getCauseSuppose());
            if(this.systemeManagement && dto.getSystemeManagement()!=null)
                item.setSystemeManagement(systemeManagementConverter.toItem(dto.getSystemeManagement())) ;

            if(this.categorieEcrate && dto.getCategorieEcrate()!=null)
                item.setCategorieEcrate(categorieEcrateConverter.toItem(dto.getCategorieEcrate())) ;

            if(this.origineEcrate && dto.getOrigineEcrate()!=null)
                item.setOrigineEcrate(origineEcrateConverter.toItem(dto.getOrigineEcrate())) ;

            if(this.typeAction && dto.getTypeAction()!=null)
                item.setTypeAction(typeActionConverter.toItem(dto.getTypeAction())) ;

            if(this.collaborator && dto.getCollaborator()!=null)
                item.setCollaborator(collaboratorConverter.toItem(dto.getCollaborator())) ;

            if(this.critereEvaluation && dto.getCritereEvaluation()!=null)
                item.setCritereEvaluation(critereEvaluationConverter.toItem(dto.getCritereEvaluation())) ;

            if(this.resultatEvaluation && dto.getResultatEvaluation()!=null)
                item.setResultatEvaluation(resultatEvaluationConverter.toItem(dto.getResultatEvaluation())) ;




        return item;
        }
    }


    public JournalAmelioarationDto toDto(JournalAmelioaration item) {
        if (item == null) {
            return null;
        } else {
            JournalAmelioarationDto dto = new JournalAmelioarationDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(item.getDate()!=null)
                dto.setDate(DateUtil.dateTimeToString(item.getDate()));
            if(StringUtil.isNotEmpty(item.getAnnee()))
                dto.setAnnee(item.getAnnee());
            if(item.getDateButoir()!=null)
                dto.setDateButoir(DateUtil.dateTimeToString(item.getDateButoir()));
            if(item.getDateRealisation()!=null)
                dto.setDateRealisation(DateUtil.dateTimeToString(item.getDateRealisation()));
            if(item.getDateEvaluation()!=null)
                dto.setDateEvaluation(DateUtil.dateTimeToString(item.getDateEvaluation()));
            if(item.getDateCloture()!=null)
                dto.setDateCloture(DateUtil.dateTimeToString(item.getDateCloture()));
            if(StringUtil.isNotEmpty(item.getAvancement()))
                dto.setAvancement(item.getAvancement());
            if(StringUtil.isNotEmpty(item.getDescriptionEcrat()))
                dto.setDescriptionEcrat(item.getDescriptionEcrat());
            if(StringUtil.isNotEmpty(item.getDescriptionAction()))
                dto.setDescriptionAction(item.getDescriptionAction());
            if(StringUtil.isNotEmpty(item.getCommentaire()))
                dto.setCommentaire(item.getCommentaire());
            if(StringUtil.isNotEmpty(item.getCauseSuppose()))
                dto.setCauseSuppose(item.getCauseSuppose());
            if(this.systemeManagement && item.getSystemeManagement()!=null) {
                dto.setSystemeManagement(systemeManagementConverter.toDto(item.getSystemeManagement())) ;

            }
            if(this.categorieEcrate && item.getCategorieEcrate()!=null) {
                dto.setCategorieEcrate(categorieEcrateConverter.toDto(item.getCategorieEcrate())) ;

            }
            if(this.origineEcrate && item.getOrigineEcrate()!=null) {
                dto.setOrigineEcrate(origineEcrateConverter.toDto(item.getOrigineEcrate())) ;

            }
            if(this.typeAction && item.getTypeAction()!=null) {
                dto.setTypeAction(typeActionConverter.toDto(item.getTypeAction())) ;

            }
            if(this.collaborator && item.getCollaborator()!=null) {
                dto.setCollaborator(collaboratorConverter.toDto(item.getCollaborator())) ;

            }
            if(this.critereEvaluation && item.getCritereEvaluation()!=null) {
                dto.setCritereEvaluation(critereEvaluationConverter.toDto(item.getCritereEvaluation())) ;

            }
            if(this.resultatEvaluation && item.getResultatEvaluation()!=null) {
                dto.setResultatEvaluation(resultatEvaluationConverter.toDto(item.getResultatEvaluation())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.systemeManagement = value;
        this.categorieEcrate = value;
        this.origineEcrate = value;
        this.typeAction = value;
        this.collaborator = value;
        this.critereEvaluation = value;
        this.resultatEvaluation = value;
    }
	
    public List<JournalAmelioaration> toItem(List<JournalAmelioarationDto> dtos) {
        List<JournalAmelioaration> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (JournalAmelioarationDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<JournalAmelioarationDto> toDto(List<JournalAmelioaration> items) {
        List<JournalAmelioarationDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (JournalAmelioaration item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(JournalAmelioarationDto dto, JournalAmelioaration t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getSystemeManagement() == null  && dto.getSystemeManagement() != null){
            t.setSystemeManagement(new SystemeManagement());
        }else if (t.getSystemeManagement() != null  && dto.getSystemeManagement() != null){
            t.setSystemeManagement(null);
            t.setSystemeManagement(new SystemeManagement());
        }
        if(t.getCategorieEcrate() == null  && dto.getCategorieEcrate() != null){
            t.setCategorieEcrate(new CategorieEcrate());
        }else if (t.getCategorieEcrate() != null  && dto.getCategorieEcrate() != null){
            t.setCategorieEcrate(null);
            t.setCategorieEcrate(new CategorieEcrate());
        }
        if(t.getOrigineEcrate() == null  && dto.getOrigineEcrate() != null){
            t.setOrigineEcrate(new OrigineEcrate());
        }else if (t.getOrigineEcrate() != null  && dto.getOrigineEcrate() != null){
            t.setOrigineEcrate(null);
            t.setOrigineEcrate(new OrigineEcrate());
        }
        if(t.getTypeAction() == null  && dto.getTypeAction() != null){
            t.setTypeAction(new TypeAction());
        }else if (t.getTypeAction() != null  && dto.getTypeAction() != null){
            t.setTypeAction(null);
            t.setTypeAction(new TypeAction());
        }
        if(t.getCollaborator() == null  && dto.getCollaborator() != null){
            t.setCollaborator(new Collaborator());
        }else if (t.getCollaborator() != null  && dto.getCollaborator() != null){
            t.setCollaborator(null);
            t.setCollaborator(new Collaborator());
        }
        if(t.getCritereEvaluation() == null  && dto.getCritereEvaluation() != null){
            t.setCritereEvaluation(new CritereEvaluation());
        }else if (t.getCritereEvaluation() != null  && dto.getCritereEvaluation() != null){
            t.setCritereEvaluation(null);
            t.setCritereEvaluation(new CritereEvaluation());
        }
        if(t.getResultatEvaluation() == null  && dto.getResultatEvaluation() != null){
            t.setResultatEvaluation(new ResultatEvaluation());
        }else if (t.getResultatEvaluation() != null  && dto.getResultatEvaluation() != null){
            t.setResultatEvaluation(null);
            t.setResultatEvaluation(new ResultatEvaluation());
        }
        if (dto.getSystemeManagement() != null)
        systemeManagementConverter.copy(dto.getSystemeManagement(), t.getSystemeManagement());
        if (dto.getCategorieEcrate() != null)
        categorieEcrateConverter.copy(dto.getCategorieEcrate(), t.getCategorieEcrate());
        if (dto.getOrigineEcrate() != null)
        origineEcrateConverter.copy(dto.getOrigineEcrate(), t.getOrigineEcrate());
        if (dto.getTypeAction() != null)
        typeActionConverter.copy(dto.getTypeAction(), t.getTypeAction());
        if (dto.getCollaborator() != null)
        collaboratorConverter.copy(dto.getCollaborator(), t.getCollaborator());
        if (dto.getCritereEvaluation() != null)
        critereEvaluationConverter.copy(dto.getCritereEvaluation(), t.getCritereEvaluation());
        if (dto.getResultatEvaluation() != null)
        resultatEvaluationConverter.copy(dto.getResultatEvaluation(), t.getResultatEvaluation());
    }

    public List<JournalAmelioaration> copy(List<JournalAmelioarationDto> dtos) {
        List<JournalAmelioaration> result = new ArrayList<>();
        if (dtos != null) {
            for (JournalAmelioarationDto dto : dtos) {
                JournalAmelioaration instance = new JournalAmelioaration();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public TypeActionConverter getTypeActionConverter(){
        return this.typeActionConverter;
    }
    public void setTypeActionConverter(TypeActionConverter typeActionConverter ){
        this.typeActionConverter = typeActionConverter;
    }
    public CollaboratorConverter getCollaboratorConverter(){
        return this.collaboratorConverter;
    }
    public void setCollaboratorConverter(CollaboratorConverter collaboratorConverter ){
        this.collaboratorConverter = collaboratorConverter;
    }
    public ResultatEvaluationConverter getResultatEvaluationConverter(){
        return this.resultatEvaluationConverter;
    }
    public void setResultatEvaluationConverter(ResultatEvaluationConverter resultatEvaluationConverter ){
        this.resultatEvaluationConverter = resultatEvaluationConverter;
    }
    public SystemeManagementConverter getSystemeManagementConverter(){
        return this.systemeManagementConverter;
    }
    public void setSystemeManagementConverter(SystemeManagementConverter systemeManagementConverter ){
        this.systemeManagementConverter = systemeManagementConverter;
    }
    public CritereEvaluationConverter getCritereEvaluationConverter(){
        return this.critereEvaluationConverter;
    }
    public void setCritereEvaluationConverter(CritereEvaluationConverter critereEvaluationConverter ){
        this.critereEvaluationConverter = critereEvaluationConverter;
    }
    public CategorieEcrateConverter getCategorieEcrateConverter(){
        return this.categorieEcrateConverter;
    }
    public void setCategorieEcrateConverter(CategorieEcrateConverter categorieEcrateConverter ){
        this.categorieEcrateConverter = categorieEcrateConverter;
    }
    public OrigineEcrateConverter getOrigineEcrateConverter(){
        return this.origineEcrateConverter;
    }
    public void setOrigineEcrateConverter(OrigineEcrateConverter origineEcrateConverter ){
        this.origineEcrateConverter = origineEcrateConverter;
    }
    public boolean  isSystemeManagement(){
        return this.systemeManagement;
    }
    public void  setSystemeManagement(boolean systemeManagement){
        this.systemeManagement = systemeManagement;
    }
    public boolean  isCategorieEcrate(){
        return this.categorieEcrate;
    }
    public void  setCategorieEcrate(boolean categorieEcrate){
        this.categorieEcrate = categorieEcrate;
    }
    public boolean  isOrigineEcrate(){
        return this.origineEcrate;
    }
    public void  setOrigineEcrate(boolean origineEcrate){
        this.origineEcrate = origineEcrate;
    }
    public boolean  isTypeAction(){
        return this.typeAction;
    }
    public void  setTypeAction(boolean typeAction){
        this.typeAction = typeAction;
    }
    public boolean  isCollaborator(){
        return this.collaborator;
    }
    public void  setCollaborator(boolean collaborator){
        this.collaborator = collaborator;
    }
    public boolean  isCritereEvaluation(){
        return this.critereEvaluation;
    }
    public void  setCritereEvaluation(boolean critereEvaluation){
        this.critereEvaluation = critereEvaluation;
    }
    public boolean  isResultatEvaluation(){
        return this.resultatEvaluation;
    }
    public void  setResultatEvaluation(boolean resultatEvaluation){
        this.resultatEvaluation = resultatEvaluation;
    }
}
