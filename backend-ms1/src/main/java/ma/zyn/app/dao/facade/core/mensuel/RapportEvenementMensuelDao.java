package ma.zyn.app.dao.facade.core.mensuel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuel;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuel;
import java.util.List;


@Repository
public interface RapportEvenementMensuelDao extends AbstractRepository<RapportEvenementMensuel,Long>  {
    RapportEvenementMensuel findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW RapportEvenementMensuel(item.id,item.code) FROM RapportEvenementMensuel item")
    List<RapportEvenementMensuel> findAllOptimized();

    RapportEvenementMensuel findByMoisAndAnnee(Integer mois, Integer annee);
}
