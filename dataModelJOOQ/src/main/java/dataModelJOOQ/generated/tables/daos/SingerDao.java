/*
 * This file is generated by jOOQ.
 */
package dataModelJOOQ.generated.tables.daos;


import dataModelJOOQ.generated.tables.Singer;
import dataModelJOOQ.generated.tables.records.SingerRecord;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class SingerDao extends DAOImpl<SingerRecord, dataModelJOOQ.generated.tables.pojos.Singer, Integer> {

    /**
     * Create a new SingerDao without any configuration
     */
    public SingerDao() {
        super(Singer.SINGER, dataModelJOOQ.generated.tables.pojos.Singer.class);
    }

    /**
     * Create a new SingerDao with an attached configuration
     */
    public SingerDao(Configuration configuration) {
        super(Singer.SINGER, dataModelJOOQ.generated.tables.pojos.Singer.class, configuration);
    }

    @Override
    public Integer getId(dataModelJOOQ.generated.tables.pojos.Singer object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<dataModelJOOQ.generated.tables.pojos.Singer> fetchRangeOfId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Singer.SINGER.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<dataModelJOOQ.generated.tables.pojos.Singer> fetchById(Integer... values) {
        return fetch(Singer.SINGER.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public dataModelJOOQ.generated.tables.pojos.Singer fetchOneById(Integer value) {
        return fetchOne(Singer.SINGER.ID, value);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public Optional<dataModelJOOQ.generated.tables.pojos.Singer> fetchOptionalById(Integer value) {
        return fetchOptional(Singer.SINGER.ID, value);
    }

    /**
     * Fetch records that have <code>first_name BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<dataModelJOOQ.generated.tables.pojos.Singer> fetchRangeOfFirstName(String lowerInclusive, String upperInclusive) {
        return fetchRange(Singer.SINGER.FIRST_NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>first_name IN (values)</code>
     */
    public List<dataModelJOOQ.generated.tables.pojos.Singer> fetchByFirstName(String... values) {
        return fetch(Singer.SINGER.FIRST_NAME, values);
    }

    /**
     * Fetch records that have <code>last_name BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<dataModelJOOQ.generated.tables.pojos.Singer> fetchRangeOfLastName(String lowerInclusive, String upperInclusive) {
        return fetchRange(Singer.SINGER.LAST_NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>last_name IN (values)</code>
     */
    public List<dataModelJOOQ.generated.tables.pojos.Singer> fetchByLastName(String... values) {
        return fetch(Singer.SINGER.LAST_NAME, values);
    }

    /**
     * Fetch records that have <code>birth_date BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<dataModelJOOQ.generated.tables.pojos.Singer> fetchRangeOfBirthDate(LocalDate lowerInclusive, LocalDate upperInclusive) {
        return fetchRange(Singer.SINGER.BIRTH_DATE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>birth_date IN (values)</code>
     */
    public List<dataModelJOOQ.generated.tables.pojos.Singer> fetchByBirthDate(LocalDate... values) {
        return fetch(Singer.SINGER.BIRTH_DATE, values);
    }

    /**
     * Fetch records that have <code>version BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<dataModelJOOQ.generated.tables.pojos.Singer> fetchRangeOfVersion(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Singer.SINGER.VERSION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>version IN (values)</code>
     */
    public List<dataModelJOOQ.generated.tables.pojos.Singer> fetchByVersion(Integer... values) {
        return fetch(Singer.SINGER.VERSION, values);
    }
}
