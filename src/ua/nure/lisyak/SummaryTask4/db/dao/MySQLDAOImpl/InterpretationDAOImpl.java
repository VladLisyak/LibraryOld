package ua.nure.lisyak.SummaryTask4.db.dao.MySQLDAOImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import ua.nure.lisyak.SummaryTask4.db.dao.InterpretationDAO;
import ua.nure.lisyak.SummaryTask4.db.parser.Parser;
import ua.nure.lisyak.SummaryTask4.entity.Interpretation;


/**
 *  DAO for {@link Interpretation}s.
 */
public class InterpretationDAOImpl extends CRUDDAOImpl<Interpretation> implements InterpretationDAO{
	
	private static final String SELECT_BY_ID = "SELECT * FROM translations WHERE id = ?";
	private static final String SELECT_ALL = "SELECT * FROM translations";
	private static final String SELECT_ALL_BY_IDS = "SELECT * FROM translations WHERE id IN {in}";
	private static final String INSERT = "INSERT INTO translations(en, ru) VALUES(?, ?)";
	private static final String UPDATE = "UPDATE translations SET en=?, ru=? WHERE id = ?";
	private static final String DELETE_BY_ID = "DELETE FROM translation WHERE id = ?";


    private Parser<Interpretation> parser = new Parser<>(Interpretation.class);

    @Override
    public Interpretation add(Interpretation transl) {
        return add(transl, INSERT);
    }

    @Override
    public void update(Interpretation interp) {
        update(interp, UPDATE);
    }

    @Override
    public void delete(int id) {
        deleteById(DELETE_BY_ID, id);
    }

    @Override
    public Interpretation getById(int id){
        return getById(id, SELECT_BY_ID, parser);
    }

    @Override
    public List<Interpretation> getAll() {
        return getAll(SELECT_ALL, parser);
    }

    @Override
    public List<Interpretation> getAll(List<Integer> ids) {
        return getAll(ids, SELECT_ALL_BY_IDS, parser);
    }

    @Override
    protected void prepareForIns(Interpretation interp, PreparedStatement pst) throws SQLException {
        List<String> values = interp.values();
        for (int i = 0; i < values.size(); i++) {
            pst.setString(i + 1, values.get(i));
        }
    }

    @Override
    protected void prepareForUpd(Interpretation interp, PreparedStatement pst) throws SQLException {
        List<String> values = interp.values();
        int i = 0;
        for (; i < values.size(); i++) {
            pst.setString(i + 1, values.get(i));
        }
        pst.setInt(i + 1, interp.getId());
    }

}
