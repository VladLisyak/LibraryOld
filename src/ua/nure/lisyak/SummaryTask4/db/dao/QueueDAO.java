package ua.nure.lisyak.SummaryTask4.db.dao;

import java.util.List;

import ua.nure.lisyak.SummaryTask4.entity.Queue;
import ua.nure.lisyak.SummaryTask4.entity.User;

public interface QueueDAO extends CRUDDAO<Queue>{

	List<Queue> getByBookId(int Id);
}
