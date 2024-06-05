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
        try {
            SignUpRequest signUpRequest = new SignUpRequest()
                    .withClientId(CLIENT_ID)
                    .withUsername(email)
                    .withPassword(senha);

            cognitoClient.signUp(signUpRequest);
            System.out.println("Usuário registrado com sucesso.");
        } catch (InvalidParameterException e) {
            throw new Exception("O email fornecido é inválido. Por favor, insira um email válido.");
        } catch (UsernameExistsException e) {
            throw new Exception("Este email já está registrado. Por favor, utilize outro email.");
        } catch (Exception e) {
            throw new Exception("Erro ao registrar usuário: " + e.getMessage(), e);
        }
    }

    public void autenticarUsuario(String email, String senha) throws Exception {
        try {
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
                System.out.println("Autenticação bem-sucedida.");
            } else {
                throw new Exception("Falha na autenticação.");
            }
        } catch (NotAuthorizedException e) {
            throw new Exception("Credenciais inválidas. Por favor, verifique seu email e senha.");
        } catch (UserNotFoundException e) {
            throw new Exception("Usuário não encontrado. Por favor, registre-se antes de tentar autenticar.");
        } catch (Exception e) {
            throw new Exception("Erro ao autenticar usuário: " + e.getMessage(), e);
        }
    }
}
