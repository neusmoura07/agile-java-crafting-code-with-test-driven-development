package sis.UI;


import javax.swing.text.*;

public class ChainableFilter extends DocumentFilter {
    private ChainableFilter next;

    /** pega o próximo filtro na cadeia */
    public ChainableFilter getNext() {
        return next;
    }
    /** adiciona mais um filtro ao final da cadeia */
    public void setNext(ChainableFilter next) {
        if (this.next == null) this.next = next;
        else this.next.setNext(next);
    }

    // nos métodos abaixo você simplesmente delega ao next,
    // ou, se não existir next, faz bypass:
    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
            throws BadLocationException {
        if (next != null) next.insertString(fb, offset, text, attr);
        else fb.insertString(offset, text, attr);
    }
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attr)
            throws BadLocationException {
        if (next != null) next.replace(fb, offset, length, text, attr);
        else fb.replace(offset, length, text, attr);
    }
    @Override
    public void remove(FilterBypass fb, int offset, int length)
            throws BadLocationException {
        if (next != null) next.remove(fb, offset, length);
        else fb.remove(offset, length);
    }
}
