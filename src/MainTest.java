import chess.pieces.Bishop;
import junit.framework.TestCase;
import java.util.*;

public class MainTest extends TestCase {
    private Main main;

    public void setUp() {
        main = new Main();
    }

    public void testCalculateFactorialWhile() {
        int result10 = Main.calculateFactorialWhile(10);
        int result0 = Main.calculateFactorialWhile(0);
        int result1 = Main.calculateFactorialWhile(1);
        assertEquals(3628800,result10);
        assertEquals(1,result0);
        assertEquals(1, result1);

    }

    public void testCalculateFactorialFor() {
        int result10 = Main.calculateFactorialFor(10);
        int result0 = Main.calculateFactorialFor(0);
        int result1 = Main.calculateFactorialFor(1);
        assertEquals(3628800,result10);
        assertEquals(1,result0);
        assertEquals(1, result1);
    }

    public void testCalculateFactorialDoWhile() {
        int result10 = Main.calculateFactorialDo(10);
        int result0 = Main.calculateFactorialDo(0);
        int result1 = Main.calculateFactorialDo(1);
        assertEquals(3628800,result10);
        assertEquals(1,result0);
        assertEquals(1, result1);
    }

    public void testContinue() {
        assertEquals("1 2 3 4 5* 6 7 8 9 10* 11 12", Main.returnString(12));
    }

    public void testSplitString() {
        String input = "1 2 3 4 5* 6 7";
        Vector tokens = Main.splitString(input);
        assertEquals("Número de tokens incorreto", 7, tokens.size());
        assertEquals("Token 0 incorreto", "1", tokens.get(0));
        assertEquals("Token 1 incorreto", "2", tokens.get(1));
        assertEquals("Token 2 incorreto", "3", tokens.get(2));
        assertEquals("Token 3 incorreto", "4", tokens.get(3));
        assertEquals("Token 4 incorreto", "5*", tokens.get(4));
        assertEquals("Token 5 incorreto", "6", tokens.get(5));
        assertEquals("Token 6 incorreto", "7", tokens.get(6));
    }

    public void testSplitAndRecreate() {
        String input = "1 2 3 4 5* 6 7";
        Vector tokens = Main.splitString(input);
        String result = Main.recreateString(tokens);
        assertEquals("Processo de split e recreate falhou", input, result);
    }

    public void testBlowsUp() {
        try {
            main.blowsUp();
            fail("Esperava RuntimeException, mas nenhuma exceção foi lançada.");
        }
        catch (RuntimeException e) {
            assertEquals("Somebody should catch this!", e.getMessage());
        }

    }

    public void testRethrows() {
        try {
            main.rethrows();
        }
        catch (SimpleException e) {

        }
        catch (RuntimeException expectedException) {
            Throwable cause = expectedException.getCause();
            assertEquals(RuntimeException.class, cause.getClass());
        }

    }

    public void testExceptionOrder1() {
        try {
            main.blowsUp();
            main.rethrows();
            fail("no exception");
        } catch (SimpleException yours) {
            fail("caught wrong exception");
        } catch (RuntimeException success) {
        }
    }


    public void testExceptionOrder2() {
        try {
            main.rethrows();
            main.blowsUp();
            fail("no exception");
        } catch (SimpleException success) {
        } catch (RuntimeException failure) {
            fail("caught wrong exception");
        }
    }
    /*
    public void testExceptionOrder3() {
        try {
            main.blowsUp();
            main.rethrows();
            fail("no exception");
        } catch (RuntimeException success) {
        } catch (SimpleException yours) {
            fail("caught wrong exception");
        }
    }
    */

    /*
    public void testExceptionOrder4() {
        try {
            main.blowsUp();
            main.rethrows();
            fail("no exception");
        } catch (RuntimeException fail) {
            fail("exception unacceptable");
        } catch (SimpleException yours) {
            fail("caught wrong exception");
        } finally {
            return;
        }
    }
    */


    public void testExceptionOrder5() {
        try {
            main.blowsUp();
            main.rethrows();
            fail("no exception");
        } catch (SimpleException yours) {
            fail("caught wrong exception");
        } catch (RuntimeException success) {
        }
    }

    public void testExceptionOrder6() {
        try {
            main.rethrows();
            main.blowsUp();
            fail("no exception");
        } catch (SimpleException yours) {
            fail("caught wrong exception");
        } catch (RuntimeException success) {
        }
    }

    /*
    public void testExceptionOrder7() {
        try {
            main.rethrows();
            main.blowsUp();
            fail("no exception");
        } catch (SimpleException success) {
        } catch (RuntimeException fail) {
            fail("caught wrong exception");
        }
    }
    */

    public void testErrorException1() {
        try {
            throw new RuntimeException("fail");
        } catch (Exception success) {

        }
    }

    public void testErrorException2() {
        try {
            new Dyer();
        } catch (Exception success) {
        }
    }

    /*
    public void testErrorException3() {
        try {
            new Dyer();
        } catch (Error success) {
        }
    }
     */

    public void testErrorException4() {
        try {
            new Dyer();
        }
        catch(Throwable success) {

        }
    }

    /*
    public void testErrorException5() {
        try {
            new Dyer();
        }
        catch (Throwable fail) {
            fail("caught exception in wrong place");
        }
        catch (Error success) {

        }
    }
     */

    public void testErrorException6() {
        try {
            new Dyer();
        } catch (Error fail) {
            fail("caught exception in wrong place");
        } catch (Throwable success) {
        }
    }

    public void testErrorException7() {
        try {
            new Dyer();
        }
        catch (Error fail) {
            fail("caught exception in wrong place");
        }
        catch (Throwable success) {
        }
        finally {
            return;
        }
    }

    public void testWithProblems() {
        try {
            doSomething();
            fail("no exception");
        } catch (Exception success) {}
    }

    void doSomething() throws Exception {
        throw new Exception("blah");
    }
}
