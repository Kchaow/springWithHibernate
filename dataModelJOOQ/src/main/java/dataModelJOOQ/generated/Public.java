/*
 * This file is generated by jOOQ.
 */
package dataModelJOOQ.generated;


import dataModelJOOQ.generated.tables.Album;
import dataModelJOOQ.generated.tables.Instrument;
import dataModelJOOQ.generated.tables.Singer;
import dataModelJOOQ.generated.tables.SingerInstrument;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.album</code>.
     */
    public final Album ALBUM = Album.ALBUM;

    /**
     * The table <code>public.instrument</code>.
     */
    public final Instrument INSTRUMENT = Instrument.INSTRUMENT;

    /**
     * The table <code>public.singer</code>.
     */
    public final Singer SINGER = Singer.SINGER;

    /**
     * The table <code>public.singer_instrument</code>.
     */
    public final SingerInstrument SINGER_INSTRUMENT = SingerInstrument.SINGER_INSTRUMENT;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Album.ALBUM,
            Instrument.INSTRUMENT,
            Singer.SINGER,
            SingerInstrument.SINGER_INSTRUMENT
        );
    }
}