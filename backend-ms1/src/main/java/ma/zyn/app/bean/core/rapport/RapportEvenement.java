package ma.zyn.app.bean.core.rapport;


import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.bean.core.actor.Collaborator;
import ma.zyn.app.bean.core.commun.TypeRapportEvenement;
import ma.zyn.app.bean.core.commun.Vessel;
import ma.zyn.app.bean.core.commun.Port;
import ma.zyn.app.bean.core.commun.Secteur;
import ma.zyn.app.bean.core.commun.TypeEvenement;
import ma.zyn.app.bean.core.journal.JournalAmelioaration;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "rapport_evenement")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="rapport_evenement_seq",sequenceName="rapport_evenement_seq",allocationSize=1, initialValue = 1)
public class RapportEvenement  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    private LocalDateTime dateEvenement ;

    private LocalDateTime dateSoumission ;

    private String attachments;

    private String description;

    private String recommendation;

    private String directivePmjChef;

    private Integer mois = 0;

    private Integer annee = 0;

    private BigDecimal windDirection = BigDecimal.ZERO;

    private BigDecimal windForce = BigDecimal.ZERO;

    private BigDecimal currentDirection = BigDecimal.ZERO;

    private BigDecimal currentForce = BigDecimal.ZERO;

    private BigDecimal swellHeigth = BigDecimal.ZERO;

    private BigDecimal swellDirection = BigDecimal.ZERO;

    @Column(columnDefinition = "boolean default false")
    private Boolean fonde = false;

    private Port port ;
    private TypeEvenement typeEvenement ;
    private Collaborator collaborator ;
    private Vessel vessel ;
    private TypeRapportEvenement typeRapportEvenement ;
    private Secteur secteur ;
    private JournalAmelioaration journalAmelioaration ;


    public RapportEvenement(){
        super();
    }

    public RapportEvenement(Long id){
        this.id = id;
    }

    public RapportEvenement(Long id,String code){
        this.id = id;
        this.code = code ;
    }
    public RapportEvenement(String code){
        this.code = code ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="rapport_evenement_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public LocalDateTime getDateEvenement(){
        return this.dateEvenement;
    }
    public void setDateEvenement(LocalDateTime dateEvenement){
        this.dateEvenement = dateEvenement;
    }
    public LocalDateTime getDateSoumission(){
        return this.dateSoumission;
    }
    public void setDateSoumission(LocalDateTime dateSoumission){
        this.dateSoumission = dateSoumission;
    }
      @Column(columnDefinition="TEXT")
    public String getAttachments(){
        return this.attachments;
    }
    public void setAttachments(String attachments){
        this.attachments = attachments;
    }
      @Column(columnDefinition="TEXT")
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
      @Column(columnDefinition="TEXT")
    public String getRecommendation(){
        return this.recommendation;
    }
    public void setRecommendation(String recommendation){
        this.recommendation = recommendation;
    }
      @Column(columnDefinition="TEXT")
    public String getDirectivePmjChef(){
        return this.directivePmjChef;
    }
    public void setDirectivePmjChef(String directivePmjChef){
        this.directivePmjChef = directivePmjChef;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "port")
    public Port getPort(){
        return this.port;
    }
    public void setPort(Port port){
        this.port = port;
    }
    public Integer getMois(){
        return this.mois;
    }
    public void setMois(Integer mois){
        this.mois = mois;
    }
    public Integer getAnnee(){
        return this.annee;
    }
    public void setAnnee(Integer annee){
        this.annee = annee;
    }
    public BigDecimal getWindDirection(){
        return this.windDirection;
    }
    public void setWindDirection(BigDecimal windDirection){
        this.windDirection = windDirection;
    }
    public BigDecimal getWindForce(){
        return this.windForce;
    }
    public void setWindForce(BigDecimal windForce){
        this.windForce = windForce;
    }
    public BigDecimal getCurrentDirection(){
        return this.currentDirection;
    }
    public void setCurrentDirection(BigDecimal currentDirection){
        this.currentDirection = currentDirection;
    }
    public BigDecimal getCurrentForce(){
        return this.currentForce;
    }
    public void setCurrentForce(BigDecimal currentForce){
        this.currentForce = currentForce;
    }
    public BigDecimal getSwellHeigth(){
        return this.swellHeigth;
    }
    public void setSwellHeigth(BigDecimal swellHeigth){
        this.swellHeigth = swellHeigth;
    }
    public BigDecimal getSwellDirection(){
        return this.swellDirection;
    }
    public void setSwellDirection(BigDecimal swellDirection){
        this.swellDirection = swellDirection;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_evenement")
    public TypeEvenement getTypeEvenement(){
        return this.typeEvenement;
    }
    public void setTypeEvenement(TypeEvenement typeEvenement){
        this.typeEvenement = typeEvenement;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collaborator")
    public Collaborator getCollaborator(){
        return this.collaborator;
    }
    public void setCollaborator(Collaborator collaborator){
        this.collaborator = collaborator;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vessel")
    public Vessel getVessel(){
        return this.vessel;
    }
    public void setVessel(Vessel vessel){
        this.vessel = vessel;
    }
    public Boolean  getFonde(){
        return this.fonde;
    }
    public void setFonde(Boolean fonde){
        this.fonde = fonde;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_rapport_evenement")
    public TypeRapportEvenement getTypeRapportEvenement(){
        return this.typeRapportEvenement;
    }
    public void setTypeRapportEvenement(TypeRapportEvenement typeRapportEvenement){
        this.typeRapportEvenement = typeRapportEvenement;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "secteur")
    public Secteur getSecteur(){
        return this.secteur;
    }
    public void setSecteur(Secteur secteur){
        this.secteur = secteur;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "journal_amelioaration")
    public JournalAmelioaration getJournalAmelioaration(){
        return this.journalAmelioaration;
    }
    public void setJournalAmelioaration(JournalAmelioaration journalAmelioaration){
        this.journalAmelioaration = journalAmelioaration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RapportEvenement rapportEvenement = (RapportEvenement) o;
        return id != null && id.equals(rapportEvenement.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

