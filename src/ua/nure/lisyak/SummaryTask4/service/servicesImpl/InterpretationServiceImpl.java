package ua.nure.lisyak.SummaryTask4.service.servicesImpl;

import ua.nure.lisyak.SummaryTask4.db.dao.InterpretationDAO;
import ua.nure.lisyak.SummaryTask4.db.dao.MySQLDAOImpl.InterpretationDAOImpl;
import ua.nure.lisyak.SummaryTask4.entity.Interpretation;
import ua.nure.lisyak.SummaryTask4.service.InterpretationService;

/**
 * Provides implementation of {@link InterpretationService} 
 * interface for manipulating with {@link Interpretation} entities.
 */
public class InterpretationServiceImpl implements InterpretationService {

    private InterpretationDAO interpDAO = new InterpretationDAOImpl();

    @Override
    public Interpretation getById(int id) {
        return interpDAO.getById(id);
    }

    @Override
    public Interpretation add(Interpretation interp) {
        if (interp == null) {
            return null;
        }
        return interpDAO.add(interp);
    }

    @Override
    public void update(Interpretation interp) {
        interpDAO.update(interp);
    }
}
