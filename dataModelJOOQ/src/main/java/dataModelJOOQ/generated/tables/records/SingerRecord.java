/*
 * This file is generated by jOOQ.
 */
package dataModelJOOQ.generated.tables.records;


import dataModelJOOQ.generated.tables.Singer;

import java.time.LocalDate;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class SingerRecord extends UpdatableRecordImpl<SingerRecord> implements Record5<Integer, String, String, LocalDate, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.singer.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.singer.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.singer.first_name</code>.
     */
    public void setFirstName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.singer.first_name</code>.
     */
    public String getFirstName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.singer.last_name</code>.
     */
    public void setLastName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.singer.last_name</code>.
     */
    public String getLastName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.singer.birth_date</code>.
     */
    public void setBirthDate(LocalDate value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.singer.birth_date</code>.
     */
    public LocalDate getBirthDate() {
        return (LocalDate) get(3);
    }

    /**
     * Setter for <code>public.singer.version</code>.
     */
    public void setVersion(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.singer.version</code>.
     */
    public Integer getVersion() {
        return (Integer) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<Integer, String, String, LocalDate, Integer> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<Integer, String, String, LocalDate, Integer> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Singer.SINGER.ID;
    }

    @Override
    public Field<String> field2() {
        return Singer.SINGER.FIRST_NAME;
    }

    @Override
    public Field<String> field3() {
        return Singer.SINGER.LAST_NAME;
    }

    @Override
    public Field<LocalDate> field4() {
        return Singer.SINGER.BIRTH_DATE;
    }

    @Override
    public Field<Integer> field5() {
        return Singer.SINGER.VERSION;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getFirstName();
    }

    @Override
    public String component3() {
        return getLastName();
    }

    @Override
    public LocalDate component4() {
        return getBirthDate();
    }

    @Override
    public Integer component5() {
        return getVersion();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getFirstName();
    }

    @Override
    public String value3() {
        return getLastName();
    }

    @Override
    public LocalDate value4() {
        return getBirthDate();
    }

    @Override
    public Integer value5() {
        return getVersion();
    }

    @Override
    public SingerRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public SingerRecord value2(String value) {
        setFirstName(value);
        return this;
    }

    @Override
    public SingerRecord value3(String value) {
        setLastName(value);
        return this;
    }

    @Override
    public SingerRecord value4(LocalDate value) {
        setBirthDate(value);
        return this;
    }

    @Override
    public SingerRecord value5(Integer value) {
        setVersion(value);
        return this;
    }

    @Override
    public SingerRecord values(Integer value1, String value2, String value3, LocalDate value4, Integer value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SingerRecord
     */
    public SingerRecord() {
        super(Singer.SINGER);
    }

    /**
     * Create a detached, initialised SingerRecord
     */
    public SingerRecord(Integer id, String firstName, String lastName, LocalDate birthDate, Integer version) {
        super(Singer.SINGER);

        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
        setVersion(version);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised SingerRecord
     */
    public SingerRecord(dataModelJOOQ.generated.tables.pojos.Singer value) {
        super(Singer.SINGER);

        if (value != null) {
            setId(value.getId());
            setFirstName(value.getFirstName());
            setLastName(value.getLastName());
            setBirthDate(value.getBirthDate());
            setVersion(value.getVersion());
            resetChangedOnNotNull();
        }
    }
}
