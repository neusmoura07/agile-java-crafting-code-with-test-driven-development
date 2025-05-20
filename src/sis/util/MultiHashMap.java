package sis.util;

import sis.studentinfo.DateUtil;

import java.util.*;

public class MultiHashMap<K, V> {
    private Map<K, List<V>> map = new HashMap<K, List<V>>();


    public int size() {
        return map.size();
    }

    public void put(K key, V value) {
        List<V> values = map.get(key);
        if (values == null) {
            values = new ArrayList<V>();
            map.put(key, values);
        }
        values.add(value);
    }

    public List<V> get(K key) {
        return map.get(key);
    }

    protected Set<Map.Entry<K, List<V>>> entrySet() {
        return map.entrySet();
    }

    private boolean isMonday(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
    }

    private java.sql.Date createSqlDate(int year, int month, int day) {
        java.util.Date date = DateUtil.createDate(year, month, day);
        return new java.sql.Date(date.getTime());
    }

    public interface Filter<T> {
        boolean apply(T item);
    }
    public static <K,V> void filter(
            final MultiHashMap<K, ? super V> target,
            final MultiHashMap<K, V> source,
            final Filter<? super V> filter) {
        for (K key : source.keys()) {
            final List<V> values = source.get(key);
            for (V value : values)
                if (filter.apply(value))
                    target.put(key, value);
        }
    }

    public Set<K> keys() {
        return map.keySet();
    }



}
