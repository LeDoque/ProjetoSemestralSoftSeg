package controller;

import view.MenuView;

import java.util.Scanner;
import model.Financeiro;
import view.FinanceiroView;

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
                while (this.escolha != 9){
                    this.escolha = this.mv.menuUser();
                    if (this.escolha == 1){
                        this.gerenciarFinancas();
                    } else if (this.escolha == 2) {

                    } else if (this.escolha == 9) {
                        this.mv.sair();
                    } else {
                        this.mv.escolhaInvalida();
                    }
                }
            } else if (this.escolha == 2) {
                this.ccl = new CadastroController();
            }else if (this.escolha == 9) {
                this.mv.sair();
            } else {
                this.mv.escolhaInvalida();
            }
        }

    }
    private void gerenciarFinancas() {
        FinanceiroController fcl = new FinanceiroController(new FinanceiroView(scanner));
        fcl.gerenciarFinancas();
    }

}
