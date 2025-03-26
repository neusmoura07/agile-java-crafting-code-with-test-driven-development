package chess.util;

import junit.framework.TestCase;

public class CharacterTest extends TestCase {


    public void testWhitespace() {
        assertTrue(Character.isWhitespace('\n'));  // Novo linha
        assertTrue(Character.isWhitespace('\t'));  // Tabulação
        assertTrue(Character.isWhitespace(' '));   // Espaço


        assertFalse(Character.isWhitespace('A')); // Letra maiúscula
    }


    public void testJavaIdentifierStart() {
        //Character.isJavaIdentifierStart(char) verifica se o caractere pode ser o primeiro caractere de um identificador
        assertTrue(Character.isJavaIdentifierStart('A')); // Letra maiúscula
        assertTrue(Character.isJavaIdentifierStart('a')); // Letra minúscula
        assertTrue(Character.isJavaIdentifierStart('_')); // Underscore
        assertTrue(Character.isJavaIdentifierStart('$')); // Cifão


        assertFalse(Character.isJavaIdentifierStart('1')); // Número
        assertFalse(Character.isJavaIdentifierStart('@')); // Caractere especial
    }


    public void testJavaIdentifierPart() {
        //Character.isJavaIdentifierStart(char) verifica se o caractere pode ser parte de um identificador
        assertTrue(Character.isJavaIdentifierPart('A')); // Letra maiúscula
        assertTrue(Character.isJavaIdentifierPart('a')); // Letra minúscula
        assertTrue(Character.isJavaIdentifierPart('1')); // Número
        assertTrue(Character.isJavaIdentifierPart('_')); // Underscore
        assertTrue(Character.isJavaIdentifierPart('$')); // Cifão

        assertFalse(Character.isJavaIdentifierPart('@')); // Caractere especial
        assertFalse(Character.isJavaIdentifierPart(' ')); // Espaço
    }
}
