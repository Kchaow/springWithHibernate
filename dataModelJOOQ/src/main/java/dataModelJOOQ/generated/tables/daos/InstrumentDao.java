/*
 * This file is generated by jOOQ.
 */
package dataModelJOOQ.generated.tables.daos;


import dataModelJOOQ.generated.tables.Instrument;
import dataModelJOOQ.generated.tables.records.InstrumentRecord;

import java.util.List;
import java.util.Optional;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class InstrumentDao extends DAOImpl<InstrumentRecord, dataModelJOOQ.generated.tables.pojos.Instrument, Integer> {

    /**
     * Create a new InstrumentDao without any configuration
     */
    public InstrumentDao() {
        super(Instrument.INSTRUMENT, dataModelJOOQ.generated.tables.pojos.Instrument.class);
    }

    /**
     * Create a new InstrumentDao with an attached configuration
     */
    public InstrumentDao(Configuration configuration) {
        super(Instrument.INSTRUMENT, dataModelJOOQ.generated.tables.pojos.Instrument.class, configuration);
    }

    @Override
    public Integer getId(dataModelJOOQ.generated.tables.pojos.Instrument object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<dataModelJOOQ.generated.tables.pojos.Instrument> fetchRangeOfId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Instrument.INSTRUMENT.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<dataModelJOOQ.generated.tables.pojos.Instrument> fetchById(Integer... values) {
        return fetch(Instrument.INSTRUMENT.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public dataModelJOOQ.generated.tables.pojos.Instrument fetchOneById(Integer value) {
        return fetchOne(Instrument.INSTRUMENT.ID, value);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public Optional<dataModelJOOQ.generated.tables.pojos.Instrument> fetchOptionalById(Integer value) {
        return fetchOptional(Instrument.INSTRUMENT.ID, value);
    }
}
