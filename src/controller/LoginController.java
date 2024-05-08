package controller;

import view.LoginView;

public class LoginController {

    private CadastroController ccl;
    private LoginView lv;
    private int escolha;

    public LoginController() {
        this.lv = new LoginView();

        while(escolha != 9){
            this.escolha = this.lv.MenuLogin();

            if(this.escolha == 1){

            } else if (this.escolha == 2) {
                this.ccl = new CadastroController();
            } else {
                this.lv.escolhaInvalida();
            }
        }

    }
}
