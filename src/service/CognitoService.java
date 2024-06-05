package service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.*;

import java.util.HashMap;
import java.util.Map;

import config.Config;

public class CognitoService {

    private static final String USER_POOL_ID;
    private static final String CLIENT_ID;
    private AWSCognitoIdentityProvider cognitoClient;

    static {
        Config config = new Config();
        USER_POOL_ID = config.getProperty("COGNITO_USER_POOL_ID");
        CLIENT_ID = config.getProperty("COGNITO_CLIENT_ID");
    }

    public CognitoService() {
        this.cognitoClient = AWSCognitoIdentityProviderClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_2)
                .build();
    }

    public void registrarUsuario(String email, String senha) throws Exception {
        SignUpRequest signUpRequest = new SignUpRequest()
                .withClientId(CLIENT_ID)
                .withUsername(email)
                .withPassword(senha);

        SignUpResult signUpResult = cognitoClient.signUp(signUpRequest);
        System.out.println("Usuário registrado: " + signUpResult.getUserSub());
    }

    public void autenticarUsuario(String email, String senha) throws Exception {
        Map<String, String> authParams = new HashMap<>();
        authParams.put("USERNAME", email);
        authParams.put("PASSWORD", senha);

        InitiateAuthRequest authRequest = new InitiateAuthRequest()
                .withAuthFlow(AuthFlowType.USER_PASSWORD_AUTH)
                .withAuthParameters(authParams)
                .withClientId(CLIENT_ID);

        InitiateAuthResult authResponse = cognitoClient.initiateAuth(authRequest);
        AuthenticationResultType authResult = authResponse.getAuthenticationResult();

        if (authResult != null) {
            System.out.println("Autenticação bem-sucedida: " + authResult.getIdToken());
        } else {
            System.out.println("Falha na autenticação");
        }
    }
}
