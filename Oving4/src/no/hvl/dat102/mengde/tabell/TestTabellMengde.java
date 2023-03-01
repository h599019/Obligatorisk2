package no.hvl.dat102.mengde.tabell;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import no.hvl.dat102.exception.EmptyCollectionException;
import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.tabell.TabellMengde;

public class TestTabellMengde {
    private MengdeADT<Integer> m1;
    private MengdeADT<Integer> m2;
    private MengdeADT<Integer> begge;

    private Integer e1 = 1;
    private Integer e2 = 2;
    private Integer e3 = 3;
    private Integer e4 = 4;
    private Integer e5 = 5;

    @Before
    public final void setup() {
        m1 = new TabellMengde<Integer>();

    }

    @Test
    public final void leggTil() {
        m1.leggTil(e1);
        assertEquals(1, m1.antall());
    }

    @Test
    public final void fjern() {
        m1.leggTil(e4);
        m1.leggTil(e5);

        m1.fjern(e4);
        m1.fjern(e5);

        assertEquals(0, m1.antall());
    }

    @Test(expected = EmptyCollectionException.class)
    public final void fjernFraTomTabell() throws EmptyCollectionException {
        m1.fjern(e1);
    }

    @Test
    public final void leggTilFjern() {
        m1.leggTil(e1);
        m1.leggTil(e2);
        m1.leggTil(e3);
        try {
            assertEquals(e2, m1.fjern(e2));
            assertEquals(e3, m1.fjern(e3));
            assertTrue(m1.inneholder(e1));
        } catch (EmptyCollectionException e) {
            fail("Mengde feilet uventet" + e.getMessage());
        }
    }

    @Test
    public final void fjernTilfeldig() {
        m1.leggTil(e2);
        m1.leggTil(e1);
        m1.leggTil(e3);
        m1.leggTil(e4);
        m1.leggTil(e5);
        try {
            assertFalse(m1.inneholder(m1.fjernTilfeldig()));
            assertEquals(4, m1.antall());
        } catch (EmptyCollectionException e) {
            fail("Mengde feilet uventet" + e.getMessage());
        }
    }

    /**
     * Tester utvid() metoden (standardkonstruktøren oppretter en tabell med 100 plasser)
     */
    @Test
    public final void utvidUtvider() {
        for(int i = 0; i < 110; i++) {
            m1.leggTil(e1 + i);
        }
        assertEquals(110, m1.antall());
    }

    @Test
    public final void erLik() {
        m2 = new TabellMengde<Integer>();
        m2.leggTil(e1);
        m2.leggTil(e2);
        m2.leggTil(e3);
        m2.leggTil(e4);
        m2.leggTil(e5);

        m1.leggTil(e1);
        m1.leggTil(e2);
        m1.leggTil(e3);
        m1.leggTil(e4);
        m1.leggTil(e5);

        assertTrue(m1.equals(m2));
    }

    @Test
    public final void tabellUnion() {
        m2 = new TabellMengde<Integer>();
        begge = new TabellMengde<Integer>();

        m1.leggTil(e1);
        m1.leggTil(e4);
        m1.leggTil(e5);

        m2.leggTil(e1);
        m2.leggTil(e2);
        m2.leggTil(e3);
        m2.leggTil(e4);

        begge = m1.union(m2);

        assertTrue(begge.inneholder(e1));
        assertTrue(begge.inneholder(e2));
        assertTrue(begge.inneholder(e3));
        assertTrue(begge.inneholder(e4));
        assertTrue(begge.inneholder(e5));
    }

    @Test
    public final void tabellDifferens() {
        m2 = new TabellMengde<Integer>();
        begge = new TabellMengde<Integer>();

        m1.leggTil(e1);
        m1.leggTil(e4);
        m1.leggTil(e5);

        m2.leggTil(e1);
        m2.leggTil(e2);
        m2.leggTil(e3);
        m2.leggTil(e4);

        begge = m1.differens(m2);

        assertFalse(begge.inneholder(e1));
        assertFalse(begge.inneholder(e2));
        assertFalse(begge.inneholder(e3));
        assertFalse(begge.inneholder(e4));
        assertTrue(begge.inneholder(e5));
    }

    @Test
    public final void tabellSnitt() {
        m2 = new TabellMengde<Integer>();
        begge = new TabellMengde<Integer>();

        m1.leggTil(e1);
        m1.leggTil(e4);
        m1.leggTil(e5);

        m2.leggTil(e1);
        m2.leggTil(e2);
        m2.leggTil(e3);
        m2.leggTil(e4);

        begge = m1.snitt(m2);


        assertFalse(begge.inneholder(e2));
        assertFalse(begge.inneholder(e3));
        assertFalse(begge.inneholder(e5));
        assertTrue(begge.inneholder(e1));
        assertTrue(begge.inneholder(e4));
    }
}
