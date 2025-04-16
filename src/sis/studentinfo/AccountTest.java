package sis.studentinfo;

import java.math.BigDecimal;
import junit.framework.*;

public class AccountTest extends TestCase {
    public void testtransactions() {
        Account account = new Account();
        account.credit(new BigDecimal("0.10"));
        account.credit(new BigDecimal("11.00"));
        assertEquals(new BigDecimal("11.10"), account.getBalance());
    }

    public void testTransactionAverage() {
        Account account = new Account();
        account.credit(new BigDecimal("0.10"));
        account.credit(new BigDecimal("11.00"));
        account.credit(new BigDecimal("2.99"));
        assertEquals(new BigDecimal("4.70"), account.transactionAverage());
    }

    public void testFlags() {
        Student student = new Student("a");
        student.set(
                Student.Flag.ON_CAMPUS,
                Student.Flag.TAX_EXEMPT,
                Student.Flag.MINOR
        );

        assertTrue(student.isOn(Student.Flag.ON_CAMPUS));
        assertTrue(student.isOn(Student.Flag.TAX_EXEMPT));
        assertTrue(student.isOn(Student.Flag.MINOR));

        assertFalse(student.isOff(Student.Flag.ON_CAMPUS));
        assertTrue(student.isOff(Student.Flag.TROUBLEMAKER));

        student.unset(Student.Flag.ON_CAMPUS);

        assertTrue(student.isOff(Student.Flag.ON_CAMPUS));
        assertTrue(student.isOn(Student.Flag.TAX_EXEMPT));
        assertTrue(student.isOn(Student.Flag.MINOR));
    }

}
