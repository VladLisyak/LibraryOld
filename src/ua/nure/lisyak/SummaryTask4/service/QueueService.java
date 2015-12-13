package ua.nure.lisyak.SummaryTask4.service;

import java.util.List;

import ua.nure.lisyak.SummaryTask4.entity.Queue;

public interface QueueService {
	Queue add(Queue q);
	void delete(Queue q);
	public List<Queue> getByBookId(int Id);
}
