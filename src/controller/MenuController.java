package controller;

import view.MenuView;

import java.util.Scanner;

public class MenuController {

    private Scanner scanner;
    private CadastroController ccl;
    private LoginController lcl;
    private MenuView mv;
    private int escolha;

    public MenuController(Scanner scanner) {
        this.scanner = scanner;
        this.mv = new MenuView();
        this.lcl = new LoginController();

        while(escolha != 9){
            this.escolha = this.mv.menu();

            if(this.escolha == 1){
                lcl.realizarLogin(scanner);
            } else if (this.escolha == 2) {
                this.ccl = new CadastroController();
            } else {
                this.mv.escolhaInvalida();
            }
        }

    }
}
