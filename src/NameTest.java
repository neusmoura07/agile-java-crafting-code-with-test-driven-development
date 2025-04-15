import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Set;

public class NameTest extends TestCase {

    public void testEqualsWithSameValue() {
        Name name1 = new Name("Foo");
        Name name2 = new Name("Foo");

        assertTrue("Os objetos com o mesmo valor devem ser iguais", name1.equals(name2));
        assertTrue("Os objetos com o mesmo valor devem ser iguais", name2.equals(name1));
    }

    public void testNotEqualsWithDifferentValue() {
        Name name1 = new Name("Foo");
        Name name2 = new Name("Bar");

        // Objetos com valores diferentes não devem ser iguais.
        assertFalse("Objetos com valores diferentes não devem ser iguais", name1.equals(name2));
        assertFalse("Objetos com valores diferentes não devem ser iguais", name2.equals(name1));
    }

    public void testEqualsWithNull() {
        Name name = new Name("Foo");
        // A comparação com null deve retornar false.
        assertFalse("Comparação com null deve retornar false", name.equals(null));
    }

    public void testEqualsWithDifferentType() {
        Name name = new Name("Foo");
        String another = "Foo";
        // Mesmo que a string tenha o mesmo conteúdo, um objeto Name NÃO deve ser considerado igual a um objeto String.
        assertFalse("Comparação com objeto de tipo diferente deve retornar false", name.equals(another));
    }

    public void testSetDoesNotContainNewFooInstance() {
        Set<Name> names = new HashSet<>();
        names.add(new Name("Foo"));

        assertFalse("O set não deve conter a nova instância new Name(\"Foo\")", names.contains(new Name("Foo")));
    }

    public void testSetContainsSameFooReference() {
        Set<Name> names = new HashSet<>();
        Name foo = new Name("Foo");
        names.add(foo);

        assertTrue("O set deve conter a instância referenciada por foo", names.contains(foo));
    }
}
