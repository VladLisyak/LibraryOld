package ua.nure.lisyak.SummaryTask4.service;

import ua.nure.lisyak.SummaryTask4.entity.Interpretation;

/**
 * Service for working with translations.
 */
public interface InterpretationService {

    /**
     * Gets {@link Interpretation} entity by id.
     *
     * @param id id of {@link Interpretation}
     * @return found {@link Interpretation} entity
     */
    Interpretation getById(int id);

    /**
     * Adds a new {@link Interpretation}.
     *
     * @param interp {@link Interpretation} to be added
     * @return added {@link Interpretation}
     */
    Interpretation add(Interpretation interp);

    /**
     * Updates an existing {@link Interpretation}.
     *
     * @param interp {@link Interpretation} to be updated.
     */
    void update(Interpretation interp);

}
