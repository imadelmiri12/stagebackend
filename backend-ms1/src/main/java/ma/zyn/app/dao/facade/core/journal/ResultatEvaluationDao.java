package ma.zyn.app.dao.facade.core.journal;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.journal.ResultatEvaluation;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.journal.ResultatEvaluation;
import java.util.List;


@Repository
public interface ResultatEvaluationDao extends AbstractRepository<ResultatEvaluation,Long>  {
    ResultatEvaluation findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW ResultatEvaluation(item.id,item.label) FROM ResultatEvaluation item")
    List<ResultatEvaluation> findAllOptimized();

}
