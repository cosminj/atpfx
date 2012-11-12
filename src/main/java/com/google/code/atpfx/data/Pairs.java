package com.google.code.atpfx.data;

import com.google.code.atpfx.GlobalProperties;
import com.google.code.db.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Pairs {

    private static int NUMBER_OF_PAIRS_TO_TEST = 1;
    private static HashMap<String, Long> pairsIDs = new HashMap<String, Long>();
    private static HashMap<Long, String> pairsValues = new HashMap<Long, String>();

    private static List<Pair> pairs;

    public Pairs() {
        if (GlobalProperties.isPersistenceEnabled()) {
            initDBPairs(HibernateUtil.getSessionFactory().getCurrentSession());
        }
    }

    // init pairs from program, faster
    static {
        pairs = Arrays.asList(
                new Pair(1, "EUR/USD"),
                new Pair(2, "USD/JPY"),
                new Pair(3, "GBP/USD"),
                new Pair(4, "USD/CHF"),
                new Pair(5, "EUR/CHF"),
                new Pair(6, "AUD/USD"),
                new Pair(7, "USD/CAD"),
                new Pair(8, "NZD/USD"),
                new Pair(9, "EUR/GBP"),
                new Pair(10, "EUR/JPY"),
                new Pair(11, "GBP/JPY"),
                new Pair(12, "CHF/JPY"),
                new Pair(13, "GBP/CHF"),
                new Pair(14, "EUR/AUD"),
                new Pair(15, "EUR/CAD"),
                new Pair(16, "AUD/CAD"),
                new Pair(17, "AUD/JPY"),
                new Pair(18, "CAD/JPY"),
                new Pair(19, "NZD/JPY"),
                new Pair(20, "GBP/CAD"),
                new Pair(21, "GBP/NZD"),
                new Pair(22, "GBP/AUD"),
                new Pair(23, "AUD/NZD"),
                new Pair(24, "AUD/CHF"),
                new Pair(25, "EUR/NZD"),
                new Pair(26, "USD/ZAR"),
                new Pair(27, "USD/SGD"),
                new Pair(28, "USD/HKD"),
                new Pair(29, "USD/TRY"),
                new Pair(30, "EUR/TRY")
        );
    }

    // populate the lists only once, from database
    @SuppressWarnings("unchecked")
    private void initDBPairs(Session session) {

        if (0 == pairs.size()) {
            session.beginTransaction();
            pairs = new ArrayList<Pair>(session.createQuery("from Pair").list());
            for (Pair p : pairs) {
                pairsIDs.put(p.getPairName(), p.getPairId());
                pairsValues.put(p.getPairId(), p.getPairName());
            }
            session.getTransaction().commit();
        }
    }

    public static Pair getPair(String name) {
        for (Pair pair1 : pairs) {
            if (pair1.getPairName().toUpperCase().equals(name.toUpperCase())) {
                return pair1;
            }
        }
        return null;
    }

    public static Pair getPair(long id) {
        for (Pair pair : pairs) {
            if (pair.getPairId() == id) {
                return pair;
            }
        }
        return null;
    }

    public static ArrayList<Pair> getPairs() {
        ArrayList<Pair> temp = new ArrayList<Pair>();
        for (int j = 0; j < NUMBER_OF_PAIRS_TO_TEST; j++)
            temp.add(pairs.get(j));
        return temp;//return pairs
    }

    public static List<Pair> getAllPairs() {
        return pairs;
    }
}
