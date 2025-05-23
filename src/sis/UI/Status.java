package sis.UI;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class Status {
    private final JLabel statusBar;
    private final Map<JTextField,String> infoMap = new HashMap<>();

    public Status(JLabel statusBar) {
        this.statusBar = statusBar;
    }

    /**
     * Registra um campo e seu texto de ajuda
     */
    public void addText(final JTextField field, final String info) {
        infoMap.put(field, info);
        field.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                statusBar.setText(info);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                statusBar.setText(" ");
            }
        });
    }

    /** método auxiliar para testes, se necessário */
    public String getText() {
        return statusBar.getText();
    }
}
