package ma.zyn.app.bean.core.journal;


import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.bean.core.actor.Collaborator;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "journal_amelioaration")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="journal_amelioaration_seq",sequenceName="journal_amelioaration_seq",allocationSize=1, initialValue = 1)
public class JournalAmelioaration  extends BaseEntity     {




    private LocalDateTime date ;

    private Integer annee = 0;

    private LocalDateTime dateButoir ;

    private LocalDateTime dateRealisation ;

    private LocalDateTime dateEvaluation ;

    private LocalDateTime dateCloture ;

    private BigDecimal avancement = BigDecimal.ZERO;

    private String descriptionEcrat;

    private String descriptionAction;

    private String commentaire;

    private String causeSuppose;

    private SystemeManagement systemeManagement ;
    private CategorieEcrate categorieEcrate ;
    private OrigineEcrate origineEcrate ;
    private TypeAction typeAction ;
    private Collaborator collaborator ;
    private CritereEvaluation critereEvaluation ;
    private ResultatEvaluation resultatEvaluation ;


    public JournalAmelioaration(){
        super();
    }

    public JournalAmelioaration(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="journal_amelioaration_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public LocalDateTime getDate(){
        return this.date;
    }
    public void setDate(LocalDateTime date){
        this.date = date;
    }
    public Integer getAnnee(){
        return this.annee;
    }
    public void setAnnee(Integer annee){
        this.annee = annee;
    }
    public LocalDateTime getDateButoir(){
        return this.dateButoir;
    }
    public void setDateButoir(LocalDateTime dateButoir){
        this.dateButoir = dateButoir;
    }
    public LocalDateTime getDateRealisation(){
        return this.dateRealisation;
    }
    public void setDateRealisation(LocalDateTime dateRealisation){
        this.dateRealisation = dateRealisation;
    }
    public LocalDateTime getDateEvaluation(){
        return this.dateEvaluation;
    }
    public void setDateEvaluation(LocalDateTime dateEvaluation){
        this.dateEvaluation = dateEvaluation;
    }
    public LocalDateTime getDateCloture(){
        return this.dateCloture;
    }
    public void setDateCloture(LocalDateTime dateCloture){
        this.dateCloture = dateCloture;
    }
    public BigDecimal getAvancement(){
        return this.avancement;
    }
    public void setAvancement(BigDecimal avancement){
        this.avancement = avancement;
    }
      @Column(columnDefinition="TEXT")
    public String getDescriptionEcrat(){
        return this.descriptionEcrat;
    }
    public void setDescriptionEcrat(String descriptionEcrat){
        this.descriptionEcrat = descriptionEcrat;
    }
      @Column(columnDefinition="TEXT")
    public String getDescriptionAction(){
        return this.descriptionAction;
    }
    public void setDescriptionAction(String descriptionAction){
        this.descriptionAction = descriptionAction;
    }
      @Column(columnDefinition="TEXT")
    public String getCommentaire(){
        return this.commentaire;
    }
    public void setCommentaire(String commentaire){
        this.commentaire = commentaire;
    }
      @Column(columnDefinition="TEXT")
    public String getCauseSuppose(){
        return this.causeSuppose;
    }
    public void setCauseSuppose(String causeSuppose){
        this.causeSuppose = causeSuppose;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "systeme_management")
    public SystemeManagement getSystemeManagement(){
        return this.systemeManagement;
    }
    public void setSystemeManagement(SystemeManagement systemeManagement){
        this.systemeManagement = systemeManagement;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categorie_ecrate")
    public CategorieEcrate getCategorieEcrate(){
        return this.categorieEcrate;
    }
    public void setCategorieEcrate(CategorieEcrate categorieEcrate){
        this.categorieEcrate = categorieEcrate;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origine_ecrate")
    public OrigineEcrate getOrigineEcrate(){
        return this.origineEcrate;
    }
    public void setOrigineEcrate(OrigineEcrate origineEcrate){
        this.origineEcrate = origineEcrate;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_action")
    public TypeAction getTypeAction(){
        return this.typeAction;
    }
    public void setTypeAction(TypeAction typeAction){
        this.typeAction = typeAction;
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
    @JoinColumn(name = "critere_evaluation")
    public CritereEvaluation getCritereEvaluation(){
        return this.critereEvaluation;
    }
    public void setCritereEvaluation(CritereEvaluation critereEvaluation){
        this.critereEvaluation = critereEvaluation;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resultat_evaluation")
    public ResultatEvaluation getResultatEvaluation(){
        return this.resultatEvaluation;
    }
    public void setResultatEvaluation(ResultatEvaluation resultatEvaluation){
        this.resultatEvaluation = resultatEvaluation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JournalAmelioaration journalAmelioaration = (JournalAmelioaration) o;
        return id != null && id.equals(journalAmelioaration.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

