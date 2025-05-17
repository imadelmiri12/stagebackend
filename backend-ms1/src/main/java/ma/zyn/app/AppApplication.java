package ma.zyn.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.openfeign.EnableFeignClients;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;


import ma.zyn.app.bean.core.actor.Collaborator;
import ma.zyn.app.service.facade.admin.actor.CollaboratorAdminService;
import ma.zyn.app.zynerator.security.bean.*;
import ma.zyn.app.zynerator.security.common.AuthoritiesConstants;
import ma.zyn.app.zynerator.security.service.facade.*;

import ma.zyn.app.bean.core.journal.SystemeManagement;
import ma.zyn.app.service.facade.admin.journal.SystemeManagementAdminService;
import ma.zyn.app.bean.core.commun.Mois;
import ma.zyn.app.service.facade.admin.commun.MoisAdminService;
import ma.zyn.app.bean.core.commun.Port;
import ma.zyn.app.service.facade.admin.commun.PortAdminService;
import ma.zyn.app.bean.core.commun.TypeVessel;
import ma.zyn.app.service.facade.admin.commun.TypeVesselAdminService;
import ma.zyn.app.bean.core.commun.TypeEvenement;
import ma.zyn.app.service.facade.admin.commun.TypeEvenementAdminService;
import ma.zyn.app.bean.core.journal.TypeAction;
import ma.zyn.app.service.facade.admin.journal.TypeActionAdminService;
import ma.zyn.app.bean.core.commun.Vessel;
import ma.zyn.app.service.facade.admin.commun.VesselAdminService;
import ma.zyn.app.bean.core.journal.OrigineEcrate;
import ma.zyn.app.service.facade.admin.journal.OrigineEcrateAdminService;
import ma.zyn.app.bean.core.commun.Secteur;
import ma.zyn.app.service.facade.admin.commun.SecteurAdminService;
import ma.zyn.app.bean.core.commun.TypeCollaborator;
import ma.zyn.app.service.facade.admin.commun.TypeCollaboratorAdminService;
import ma.zyn.app.bean.core.journal.CritereEvaluation;
import ma.zyn.app.service.facade.admin.journal.CritereEvaluationAdminService;
import ma.zyn.app.bean.core.journal.ResultatEvaluation;
import ma.zyn.app.service.facade.admin.journal.ResultatEvaluationAdminService;
import ma.zyn.app.bean.core.journal.CategorieEcrate;
import ma.zyn.app.service.facade.admin.journal.CategorieEcrateAdminService;
import ma.zyn.app.bean.core.indicateur.Trimestre;
import ma.zyn.app.service.facade.admin.indicateur.TrimestreAdminService;
import ma.zyn.app.bean.core.commun.TypeRapportEvenement;
import ma.zyn.app.service.facade.admin.commun.TypeRapportEvenementAdminService;

import ma.zyn.app.zynerator.security.bean.User;
import ma.zyn.app.zynerator.security.bean.Role;

@SpringBootApplication
//@EnableFeignClients
public class AppApplication {
    public static ConfigurableApplicationContext ctx;


    //state: primary success info secondary warning danger contrast
    //_STATE(Pending=warning,Rejeted=danger,Validated=success)
    public static void main(String[] args) {
        ctx=SpringApplication.run(AppApplication.class, args);
    }


    @Bean
    ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    public static ConfigurableApplicationContext getCtx() {
        return ctx;
    }

    @Bean
    public CommandLineRunner demo(UserService userService, RoleService roleService, ModelPermissionService modelPermissionService, ActionPermissionService actionPermissionService, ModelPermissionUserService modelPermissionUserService , CollaboratorAdminService collaboratorService) {
    return (args) -> {
        if(true){

            createSystemeManagement();
            createMois();
            createPort();
            createTypeVessel();
            createTypeEvenement();
            createTypeAction();
            createVessel();
            createOrigineEcrate();
            createSecteur();
            createTypeCollaborator();
            createCritereEvaluation();
            createResultatEvaluation();
            createCategorieEcrate();
            createTrimestre();
            createTypeRapportEvenement();

        /*
        List<ModelPermission> modelPermissions = new ArrayList<>();
        addPermission(modelPermissions);
        modelPermissions.forEach(e -> modelPermissionService.create(e));
        List<ActionPermission> actionPermissions = new ArrayList<>();
        addActionPermission(actionPermissions);
        actionPermissions.forEach(e -> actionPermissionService.create(e));
        */

		// User Admin
        User userForAdmin = new User("admin");
		userForAdmin.setPassword("123");
		// Role Admin
		Role roleForAdmin = new Role();
		roleForAdmin.setAuthority(AuthoritiesConstants.ADMIN);
		roleForAdmin.setCreatedAt(LocalDateTime.now());
		Role roleForAdminSaved = roleService.create(roleForAdmin);
		RoleUser roleUserForAdmin = new RoleUser();
		roleUserForAdmin.setRole(roleForAdminSaved);
		if (userForAdmin.getRoleUsers() == null)
			userForAdmin.setRoleUsers(new ArrayList<>());

		userForAdmin.getRoleUsers().add(roleUserForAdmin);


        userForAdmin.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        userService.create(userForAdmin);

		// User Collaborator
        Collaborator userForCollaborator = new Collaborator("collaborator");
		userForCollaborator.setPassword("123");
		// Role Collaborator
		Role roleForCollaborator = new Role();
		roleForCollaborator.setAuthority(AuthoritiesConstants.COLLABORATOR);
		roleForCollaborator.setCreatedAt(LocalDateTime.now());
		Role roleForCollaboratorSaved = roleService.create(roleForCollaborator);
		RoleUser roleUserForCollaborator = new RoleUser();
		roleUserForCollaborator.setRole(roleForCollaboratorSaved);
		if (userForCollaborator.getRoleUsers() == null)
			userForCollaborator.setRoleUsers(new ArrayList<>());

		userForCollaborator.getRoleUsers().add(roleUserForCollaborator);


        userForCollaborator.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        collaboratorService.create(userForCollaborator);

            }
        };
    }



    private void createSystemeManagement(){
            SystemeManagement itemDanger = new SystemeManagement();
            itemDanger.setStyle("danger");
            itemDanger.setLabel("QSE");
            itemDanger.setCode("QSE");
            systemeManagementService.create(itemDanger);
            SystemeManagement itemInfo = new SystemeManagement();
            itemInfo.setStyle("info");
            itemInfo.setLabel("SE");
            itemInfo.setCode("SE");
            systemeManagementService.create(itemInfo);
            SystemeManagement itemSuccess = new SystemeManagement();
            itemSuccess.setStyle("success");
            itemSuccess.setLabel("S");
            itemSuccess.setCode("S");
            systemeManagementService.create(itemSuccess);
            SystemeManagement itemWarning = new SystemeManagement();
            itemWarning.setStyle("warning");
            itemWarning.setLabel("QE");
            itemWarning.setCode("QE");
            systemeManagementService.create(itemWarning);

    }
    private void createMois(){
            Mois itemWarning = new Mois();
            itemWarning.setStyle("warning");
            itemWarning.setLabel("Mars");
            itemWarning.setCode("Mars");
            moisService.create(itemWarning);
            Mois itemDanger2 = new Mois();
            itemDanger2.setStyle("danger2");
            itemDanger2.setLabel("Aout");
            itemDanger2.setCode("Aout");
            moisService.create(itemDanger2);
            Mois itemSuccess3 = new Mois();
            itemSuccess3.setStyle("success3");
            itemSuccess3.setLabel("Septembre");
            itemSuccess3.setCode("Septembre");
            moisService.create(itemSuccess3);
            Mois itemInfo2 = new Mois();
            itemInfo2.setStyle("info2");
            itemInfo2.setLabel("Juin");
            itemInfo2.setCode("Juin");
            moisService.create(itemInfo2);
            Mois itemWarning2 = new Mois();
            itemWarning2.setStyle("warning2");
            itemWarning2.setLabel("Juillet");
            itemWarning2.setCode("Juillet");
            moisService.create(itemWarning2);
            Mois itemSuccess2 = new Mois();
            itemSuccess2.setStyle("success2");
            itemSuccess2.setLabel("Mai");
            itemSuccess2.setCode("Mai");
            moisService.create(itemSuccess2);
            Mois itemInfo3 = new Mois();
            itemInfo3.setStyle("info3");
            itemInfo3.setLabel("Octobre");
            itemInfo3.setCode("Octobre");
            moisService.create(itemInfo3);
            Mois itemWarning3 = new Mois();
            itemWarning3.setStyle("warning3");
            itemWarning3.setLabel("Novembre");
            itemWarning3.setCode("Novembre");
            moisService.create(itemWarning3);
            Mois itemInfo = new Mois();
            itemInfo.setStyle("info");
            itemInfo.setLabel("Fevrier");
            itemInfo.setCode("Fevrier");
            moisService.create(itemInfo);
            Mois itemDanger = new Mois();
            itemDanger.setStyle("danger");
            itemDanger.setLabel("Avril");
            itemDanger.setCode("Avril");
            moisService.create(itemDanger);
            Mois itemDanger3 = new Mois();
            itemDanger3.setStyle("danger3");
            itemDanger3.setLabel("Decembre");
            itemDanger3.setCode("Decembre");
            moisService.create(itemDanger3);
            Mois itemSuccess = new Mois();
            itemSuccess.setStyle("success");
            itemSuccess.setLabel("Janvier");
            itemSuccess.setCode("Janvier");
            moisService.create(itemSuccess);

    }
    private void createPort(){
            Port itemDanger = new Port();
            itemDanger.setStyle("danger");
            itemDanger.setLabel("PPR");
            itemDanger.setCode("PPR");
            portService.create(itemDanger);
            Port itemSuccess = new Port();
            itemSuccess.setStyle("success");
            itemSuccess.setLabel("TM2");
            itemSuccess.setCode("TM2");
            portService.create(itemSuccess);
            Port itemWarning = new Port();
            itemWarning.setStyle("warning");
            itemWarning.setLabel("TM1");
            itemWarning.setCode("TM1");
            portService.create(itemWarning);

    }
    private void createTypeVessel(){
            TypeVessel itemSuccess = new TypeVessel();
            itemSuccess.setStyle("success");
            itemSuccess.setLabel("Ferry");
            itemSuccess.setCode("Ferry");
            typeVesselService.create(itemSuccess);
            TypeVessel itemWarning = new TypeVessel();
            itemWarning.setStyle("warning");
            itemWarning.setLabel("Container");
            itemWarning.setCode("Container");
            typeVesselService.create(itemWarning);
            TypeVessel itemDanger = new TypeVessel();
            itemDanger.setStyle("danger");
            itemDanger.setLabel("Tanker");
            itemDanger.setCode("Tanker");
            typeVesselService.create(itemDanger);

    }
    private void createTypeEvenement(){
            TypeEvenement itemDanger = new TypeEvenement();
            itemDanger.setStyle("danger");
            itemDanger.setLabel("Accident");
            itemDanger.setCode("Accident");
            typeEvenementService.create(itemDanger);
            TypeEvenement itemWarning = new TypeEvenement();
            itemWarning.setStyle("warning");
            itemWarning.setLabel("Anomaly");
            itemWarning.setCode("Anomaly");
            typeEvenementService.create(itemWarning);
            TypeEvenement itemSuccess = new TypeEvenement();
            itemSuccess.setStyle("success");
            itemSuccess.setLabel("Emergency");
            itemSuccess.setCode("Emergency");
            typeEvenementService.create(itemSuccess);

    }
    private void createTypeAction(){
            TypeAction itemSuccess = new TypeAction();
            itemSuccess.setStyle("success");
            itemSuccess.setLabel("Préventive");
            itemSuccess.setCode("Préventive");
            typeActionService.create(itemSuccess);
            TypeAction itemWarning = new TypeAction();
            itemWarning.setStyle("warning");
            itemWarning.setLabel("Corrective");
            itemWarning.setCode("Corrective");
            typeActionService.create(itemWarning);
            TypeAction itemDanger = new TypeAction();
            itemDanger.setStyle("danger");
            itemDanger.setLabel("Amélioration");
            itemDanger.setCode("Amélioration");
            typeActionService.create(itemDanger);

    }
    private void createVessel(){
            Vessel itemDanger = new Vessel();
            itemDanger.setStyle("danger");
            itemDanger.setLabel("EMERALD");
            itemDanger.setCode("EMERALD");
            vesselService.create(itemDanger);
            Vessel itemWarning = new Vessel();
            itemWarning.setStyle("warning");
            itemWarning.setLabel("MERSK LABERA");
            itemWarning.setCode("MERSK LABERA");
            vesselService.create(itemWarning);
            Vessel itemSuccess = new Vessel();
            itemSuccess.setStyle("success");
            itemSuccess.setLabel("MSC TESSA");
            itemSuccess.setCode("MSC TESSA");
            vesselService.create(itemSuccess);

    }
    private void createOrigineEcrate(){
            OrigineEcrate itemWarning = new OrigineEcrate();
            itemWarning.setStyle("warning");
            itemWarning.setLabel("Analyse des données");
            itemWarning.setCode("Analyse des données");
            origineEcrateService.create(itemWarning);
            OrigineEcrate itemSuccess = new OrigineEcrate();
            itemSuccess.setStyle("success");
            itemSuccess.setLabel("Gestion des Informations du TMMS");
            itemSuccess.setCode("Gestion des Informations du TMMS");
            origineEcrateService.create(itemSuccess);
            OrigineEcrate itemSuccess2 = new OrigineEcrate();
            itemSuccess2.setStyle("success2");
            itemSuccess2.setLabel("Fiche AEMAC");
            itemSuccess2.setCode("Fiche AEMAC");
            origineEcrateService.create(itemSuccess2);
            OrigineEcrate itemWarning2 = new OrigineEcrate();
            itemWarning2.setStyle("warning2");
            itemWarning2.setLabel("Réclamation client");
            itemWarning2.setCode("Réclamation client");
            origineEcrateService.create(itemWarning2);
            OrigineEcrate itemDanger = new OrigineEcrate();
            itemDanger.setStyle("danger");
            itemDanger.setLabel("Constat");
            itemDanger.setCode("Constat");
            origineEcrateService.create(itemDanger);
            OrigineEcrate itemInfo2 = new OrigineEcrate();
            itemInfo2.setStyle("info2");
            itemInfo2.setLabel("Rapport du pilote");
            itemInfo2.setCode("Rapport du pilote");
            origineEcrateService.create(itemInfo2);
            OrigineEcrate itemInfo = new OrigineEcrate();
            itemInfo.setStyle("info");
            itemInfo.setLabel("Audit interne QSE");
            itemInfo.setCode("Audit interne QSE");
            origineEcrateService.create(itemInfo);

    }
    private void createSecteur(){
            Secteur itemSuccess = new Secteur();
            itemSuccess.setStyle("success");
            itemSuccess.setLabel("Lamanage");
            itemSuccess.setCode("Lamanage");
            secteurService.create(itemSuccess);
            Secteur itemWarning = new Secteur();
            itemWarning.setStyle("warning");
            itemWarning.setLabel("Pilotage");
            itemWarning.setCode("Pilotage");
            secteurService.create(itemWarning);
            Secteur itemDanger = new Secteur();
            itemDanger.setStyle("danger");
            itemDanger.setLabel("Remorquage");
            itemDanger.setCode("Remorquage");
            secteurService.create(itemDanger);

    }
    private void createTypeCollaborator(){
            TypeCollaborator itemWarning = new TypeCollaborator();
            itemWarning.setStyle("warning");
            itemWarning.setLabel("Pilot");
            itemWarning.setCode("Pilot");
            typeCollaboratorService.create(itemWarning);
            TypeCollaborator itemDanger = new TypeCollaborator();
            itemDanger.setStyle("danger");
            itemDanger.setLabel("VTS");
            itemDanger.setCode("VTS");
            typeCollaboratorService.create(itemDanger);
            TypeCollaborator itemSuccess = new TypeCollaborator();
            itemSuccess.setStyle("success");
            itemSuccess.setLabel("Captain");
            itemSuccess.setCode("Captain");
            typeCollaboratorService.create(itemSuccess);

    }
    private void createCritereEvaluation(){
            CritereEvaluation itemSuccess2 = new CritereEvaluation();
            itemSuccess2.setStyle("success2");
            itemSuccess2.setLabel("Procédure appliquée");
            itemSuccess2.setCode("Procédure appliquée");
            critereEvaluationService.create(itemSuccess2);
            CritereEvaluation itemInfo4 = new CritereEvaluation();
            itemInfo4.setStyle("info4");
            itemInfo4.setLabel("Participation de tous les pilotes");
            itemInfo4.setCode("Participation de tous les pilotes");
            critereEvaluationService.create(itemInfo4);
            CritereEvaluation itemWarning = new CritereEvaluation();
            itemWarning.setStyle("warning");
            itemWarning.setLabel("Réalisation et fiabilité de alignement");
            itemWarning.setCode("Réalisation et fiabilité de alignement");
            critereEvaluationService.create(itemWarning);
            CritereEvaluation itemDanger3 = new CritereEvaluation();
            itemDanger3.setStyle("danger3");
            itemDanger3.setLabel("Mise en place une échelle de pilote conforme");
            itemDanger3.setCode("Mise en place une échelle de pilote conforme");
            critereEvaluationService.create(itemDanger3);
            CritereEvaluation itemWarning2 = new CritereEvaluation();
            itemWarning2.setStyle("warning2");
            itemWarning2.setLabel("Action couverte par le dernier RQ Formation");
            itemWarning2.setCode("Action couverte par le dernier RQ Formation");
            critereEvaluationService.create(itemWarning2);
            CritereEvaluation itemSuccess = new CritereEvaluation();
            itemSuccess.setStyle("success");
            itemSuccess.setLabel("Surveillance de la réalisation des Exercices de sécurité");
            itemSuccess.setCode("Surveillance de la réalisation des Exercices de sécurité");
            critereEvaluationService.create(itemSuccess);
            CritereEvaluation itemDanger = new CritereEvaluation();
            itemDanger.setStyle("danger");
            itemDanger.setLabel("Réalisation et enregistrement des exercices 2D à la vigie");
            itemDanger.setCode("Réalisation et enregistrement des exercices 2D à la vigie");
            critereEvaluationService.create(itemDanger);
            CritereEvaluation itemWarning3 = new CritereEvaluation();
            itemWarning3.setStyle("warning3");
            itemWarning3.setLabel("suivi du plan de passage par le VTS");
            itemWarning3.setCode("suivi du plan de passage par le VTS");
            critereEvaluationService.create(itemWarning3);
            CritereEvaluation itemSuccess4 = new CritereEvaluation();
            itemSuccess4.setStyle("success4");
            itemSuccess4.setLabel("correction de écart");
            itemSuccess4.setCode("correction de écart");
            critereEvaluationService.create(itemSuccess4);
            CritereEvaluation itemWarning4 = new CritereEvaluation();
            itemWarning4.setStyle("warning4");
            itemWarning4.setLabel("Exécution de la note");
            itemWarning4.setCode("Exécution de la note");
            critereEvaluationService.create(itemWarning4);
            CritereEvaluation itemSuccess3 = new CritereEvaluation();
            itemSuccess3.setStyle("success3");
            itemSuccess3.setLabel("Suivi de état des remorques");
            itemSuccess3.setCode("Suivi de état des remorques");
            critereEvaluationService.create(itemSuccess3);
            CritereEvaluation itemDanger4  = new CritereEvaluation();
            itemDanger4 .setStyle("danger4 ");
            itemDanger4 .setLabel("Non reproduction une Emergency similaire");
            itemDanger4 .setCode("Non reproduction une Emergency similaire");
            critereEvaluationService.create(itemDanger4 );
            CritereEvaluation itemDanger2 = new CritereEvaluation();
            itemDanger2.setStyle("danger2");
            itemDanger2.setLabel("Vérification par le VTS et les Dispatchers pilotage");
            itemDanger2.setCode("Vérification par le VTS et les Dispatchers pilotage");
            critereEvaluationService.create(itemDanger2);
            CritereEvaluation itemInfo = new CritereEvaluation();
            itemInfo.setStyle("info");
            itemInfo.setLabel("Convention validée par le DG et signée avec ISEM");
            itemInfo.setCode("Convention validée par le DG et signée avec ISEM");
            critereEvaluationService.create(itemInfo);
            CritereEvaluation itemInfo3 = new CritereEvaluation();
            itemInfo3.setStyle("info3");
            itemInfo3.setLabel("Suivi de état des échelles de pilote du MV EOS");
            itemInfo3.setCode("Suivi de état des échelles de pilote du MV EOS");
            critereEvaluationService.create(itemInfo3);
            CritereEvaluation itemInfo2 = new CritereEvaluation();
            itemInfo2.setStyle("info2");
            itemInfo2.setLabel(" Enregistrements sont utilisées");
            itemInfo2.setCode(" Enregistrements sont utilisées");
            critereEvaluationService.create(itemInfo2);

    }
    private void createResultatEvaluation(){
            ResultatEvaluation itemSuccess = new ResultatEvaluation();
            itemSuccess.setStyle("success");
            itemSuccess.setLabel("Efficace");
            itemSuccess.setCode("Efficace");
            resultatEvaluationService.create(itemSuccess);

    }
    private void createCategorieEcrate(){
            CategorieEcrate itemWarning = new CategorieEcrate();
            itemWarning.setStyle("warning");
            itemWarning.setLabel("Améliorationaly");
            itemWarning.setCode("Améliorationaly");
            categorieEcrateService.create(itemWarning);
            CategorieEcrate itemDanger = new CategorieEcrate();
            itemDanger.setStyle("danger");
            itemDanger.setLabel("Remarque");
            itemDanger.setCode("Remarque");
            categorieEcrateService.create(itemDanger);
            CategorieEcrate itemSuccess = new CategorieEcrate();
            itemSuccess.setStyle("success");
            itemSuccess.setLabel("Observation");
            itemSuccess.setCode("Observation");
            categorieEcrateService.create(itemSuccess);
            CategorieEcrate itemWarning2 = new CategorieEcrate();
            itemWarning2.setStyle("warning2");
            itemWarning2.setLabel("Constat");
            itemWarning2.setCode("Constat");
            categorieEcrateService.create(itemWarning2);
            CategorieEcrate itemInfo = new CategorieEcrate();
            itemInfo.setStyle("info");
            itemInfo.setLabel("Non conformité");
            itemInfo.setCode("Non conformité");
            categorieEcrateService.create(itemInfo);

    }
    private void createTrimestre(){
            Trimestre itemDanger = new Trimestre();
            itemDanger.setStyle("danger");
            itemDanger.setLabel("T4");
            itemDanger.setCode("T4");
            trimestreService.create(itemDanger);
            Trimestre itemSuccess = new Trimestre();
            itemSuccess.setStyle("success");
            itemSuccess.setLabel("T1");
            itemSuccess.setCode("T1");
            trimestreService.create(itemSuccess);
            Trimestre itemInfo = new Trimestre();
            itemInfo.setStyle("info");
            itemInfo.setLabel("T2");
            itemInfo.setCode("T2");
            trimestreService.create(itemInfo);
            Trimestre itemWarning = new Trimestre();
            itemWarning.setStyle("warning");
            itemWarning.setLabel("T3");
            itemWarning.setCode("T3");
            trimestreService.create(itemWarning);

    }
    private void createTypeRapportEvenement(){
            TypeRapportEvenement itemWarning = new TypeRapportEvenement();
            itemWarning.setStyle("warning");
            itemWarning.setLabel("New");
            itemWarning.setCode("New");
            typeRapportEvenementService.create(itemWarning);
            TypeRapportEvenement itemDanger = new TypeRapportEvenement();
            itemDanger.setStyle("danger");
            itemDanger.setLabel("Validated");
            itemDanger.setCode("Validated");
            typeRapportEvenementService.create(itemDanger);
            TypeRapportEvenement itemSuccess = new TypeRapportEvenement();
            itemSuccess.setStyle("success");
            itemSuccess.setLabel("Pending");
            itemSuccess.setCode("Pending");
            typeRapportEvenementService.create(itemSuccess);

    }

    private static String fakeString(String attributeName, int i) {
        return attributeName + i;
    }

    private static Long fakeLong(String attributeName, int i) {
        return  10L * i;
    }
    private static Integer fakeInteger(String attributeName, int i) {
        return  10 * i;
    }

    private static Double fakeDouble(String attributeName, int i) {
        return 10D * i;
    }

    private static BigDecimal fakeBigDecimal(String attributeName, int i) {
        return  BigDecimal.valueOf(i*1L*10);
    }

    private static Boolean fakeBoolean(String attributeName, int i) {
        return i % 2 == 0 ? true : false;
    }
    private static LocalDateTime fakeLocalDateTime(String attributeName, int i) {
        return LocalDateTime.now().plusDays(i);
    }


    private static void addPermission(List<ModelPermission> modelPermissions) {
        modelPermissions.add(new ModelPermission("JournalAmelioaration"));
        modelPermissions.add(new ModelPermission("SystemeManagement"));
        modelPermissions.add(new ModelPermission("RapportEvenement"));
        modelPermissions.add(new ModelPermission("Mois"));
        modelPermissions.add(new ModelPermission("Port"));
        modelPermissions.add(new ModelPermission("RapportEvenementMensuelSecteur"));
        modelPermissions.add(new ModelPermission("TypeVessel"));
        modelPermissions.add(new ModelPermission("TypeEvenement"));
        modelPermissions.add(new ModelPermission("TypeAction"));
        modelPermissions.add(new ModelPermission("Vessel"));
        modelPermissions.add(new ModelPermission("Collaborator"));
        modelPermissions.add(new ModelPermission("OrigineEcrate"));
        modelPermissions.add(new ModelPermission("Secteur"));
        modelPermissions.add(new ModelPermission("TypeCollaborator"));
        modelPermissions.add(new ModelPermission("RapportEvenementMensuelSecteurDetail"));
        modelPermissions.add(new ModelPermission("CritereEvaluation"));
        modelPermissions.add(new ModelPermission("ResultatEvaluation"));
        modelPermissions.add(new ModelPermission("RapportEvenementMensuel"));
        modelPermissions.add(new ModelPermission("CategorieEcrate"));
        modelPermissions.add(new ModelPermission("Trimestre"));
        modelPermissions.add(new ModelPermission("TypeRapportEvenement"));
        modelPermissions.add(new ModelPermission("RapportEvenementMensuelTypeEvenement"));
        modelPermissions.add(new ModelPermission("User"));
        modelPermissions.add(new ModelPermission("ModelPermission"));
        modelPermissions.add(new ModelPermission("ActionPermission"));
    }

    private static void addActionPermission(List<ActionPermission> actionPermissions) {
        actionPermissions.add(new ActionPermission("list"));
        actionPermissions.add(new ActionPermission("create"));
        actionPermissions.add(new ActionPermission("delete"));
        actionPermissions.add(new ActionPermission("edit"));
        actionPermissions.add(new ActionPermission("view"));
        actionPermissions.add(new ActionPermission("duplicate"));
    }


    @Autowired
    SystemeManagementAdminService systemeManagementService;
    @Autowired
    MoisAdminService moisService;
    @Autowired
    PortAdminService portService;
    @Autowired
    TypeVesselAdminService typeVesselService;
    @Autowired
    TypeEvenementAdminService typeEvenementService;
    @Autowired
    TypeActionAdminService typeActionService;
    @Autowired
    VesselAdminService vesselService;
    @Autowired
    OrigineEcrateAdminService origineEcrateService;
    @Autowired
    SecteurAdminService secteurService;
    @Autowired
    TypeCollaboratorAdminService typeCollaboratorService;
    @Autowired
    CritereEvaluationAdminService critereEvaluationService;
    @Autowired
    ResultatEvaluationAdminService resultatEvaluationService;
    @Autowired
    CategorieEcrateAdminService categorieEcrateService;
    @Autowired
    TrimestreAdminService trimestreService;
    @Autowired
    TypeRapportEvenementAdminService typeRapportEvenementService;
}


