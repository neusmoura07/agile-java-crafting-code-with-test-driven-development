package sis.util;

import java.util.*;
import junit.framework.*;
import sis.studentinfo.DateUtil;

public class ListUtilTest extends TestCase {
    public void testPad() {
        final int count = 5;
        List<Date> list = new ArrayList<Date>();
        final Date element = new Date();
        ListUtil.pad(list, element, count);
        assertEquals(count, list.size());
        for (int i = 0; i < count; i++)
            assertEquals("unexpected element at " + i,
                    element, list.get(i));
    }

    public void testWildcardCapture() {
        List<String> names = new ArrayList<String>();
        names.add("alpha");
        names.add("beta");
        inPlaceReverse(names);
        assertEquals("beta", names.get(0));
        assertEquals("alpha", names.get(1));
    }

    static void inPlaceReverse(List<?> list) {
        int size = list.size();
        for (int i = 0; i < size / 2; i++)
            swap(list, i, size - 1 - i);
    }

    private static <T> void swap(List<T> list, int i, int opposite) {
        T temp = list.get(i);
        list.set(i, list.get(opposite));
        list.set(opposite, temp);
    }

    public void testFilter() {
        MultiHashMap<String, java.sql.Date> meetings =
                new MultiHashMap<String, java.sql.Date>();
        meetings.put("iteration start", createSqlDate(2005, 9, 12));
        meetings.put("iteration start", createSqlDate(2005, 9, 26));
        meetings.put("VP blather", createSqlDate(2005, 9, 12));
        meetings.put("brown bags", createSqlDate(2005, 9, 14));

        MultiHashMap<String, java.util.Date> mondayMeetings =
                new MultiHashMap<String, java.util.Date>();
        MultiHashMap.filter(mondayMeetings, meetings,
                new MultiHashMap.Filter<java.util.Date>() {
                    public boolean apply(java.util.Date date) {
                        return isMonday(date);
                    }
                });

        assertEquals(2, mondayMeetings.size());
        assertEquals(2, mondayMeetings.get("iteration start").size());
        assertNull(mondayMeetings.get("brown bags"));
        assertEquals(1, mondayMeetings.get("VP blather").size());
    }

    private boolean isMonday(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;}


    private java.sql.Date createSqlDate(int year, int month, int day) {
        java.util.Date date = DateUtil.createDate(year, month, day);
        return new java.sql.Date(date.getTime());
    }

}