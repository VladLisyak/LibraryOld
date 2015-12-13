package ua.nure.lisyak.SummaryTask4.service.servicesImpl;

import java.util.List;

import ua.nure.lisyak.SummaryTask4.db.dao.QueueDAO;
import ua.nure.lisyak.SummaryTask4.db.dao.MySQLDAOImpl.QueueDAOImpl;
import ua.nure.lisyak.SummaryTask4.entity.Queue;
import ua.nure.lisyak.SummaryTask4.service.QueueService;

public class QueueServiceImpl implements QueueService{

	private QueueDAO qdao = new QueueDAOImpl();
	
	@Override
	public Queue add(Queue q) {
		return qdao.add(q);
	}

	@Override
	public void delete(Queue q) {
		qdao.delete(q.getId());
	}

	@Override
	public List<Queue> getByBookId(int Id) {
		return qdao.getByBookId(Id);
	}

}
