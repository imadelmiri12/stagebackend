package ma.zyn.app.dao.facade.core.journal;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.journal.CritereEvaluation;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.journal.CritereEvaluation;
import java.util.List;


@Repository
public interface CritereEvaluationDao extends AbstractRepository<CritereEvaluation,Long>  {
    CritereEvaluation findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW CritereEvaluation(item.id,item.label) FROM CritereEvaluation item")
    List<CritereEvaluation> findAllOptimized();

}
