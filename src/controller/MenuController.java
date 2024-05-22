package controller;

import view.MenuView;

public class MenuController {

    private CadastroController ccl;
    private MenuView mv;
    private int escolha;

    public MenuController() {
        this.mv = new MenuView();

        while(escolha != 9){
            this.escolha = this.mv.MenuLogin();

            if(this.escolha == 1){

            } else if (this.escolha == 2) {
                this.ccl = new CadastroController();
            } else {
                this.mv.escolhaInvalida();
            }
        }

    }
}
