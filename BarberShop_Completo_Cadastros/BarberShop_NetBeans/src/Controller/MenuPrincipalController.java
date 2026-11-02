package Controller;

import View.Agenda;
import View.MenuPrincipal;

public class MenuPrincipalController {
    private final MenuPrincipal view;

    public MenuPrincipalController(MenuPrincipal view) { this.view = view; }

    public void navegarParaAgenda() {
        new Agenda().setVisible(true);
    }
}
