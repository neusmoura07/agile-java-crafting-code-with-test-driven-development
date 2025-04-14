package sis.report;
import junit.framework.*;
import sis.studentinfo.*;

public class ReportCardTest extends TestCase {
    private ReportCard card;

    protected void setUp() {
        card = new ReportCard();
    }

    public void testMessage() {
        assertEquals(ReportCard.A_MESSAGE, card.getMessage(Student.Grade.A));
        assertEquals(ReportCard.B_MESSAGE, card.getMessage(Student.Grade.B));
        assertEquals(ReportCard.C_MESSAGE, card.getMessage(Student.Grade.C));
        assertEquals(ReportCard.D_MESSAGE, card.getMessage(Student.Grade.D));
        assertEquals(ReportCard.F_MESSAGE, card.getMessage(Student.Grade.F));
    }
}
