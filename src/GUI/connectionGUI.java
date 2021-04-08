package GUI;

import Contexts.*;
import Outils.Conversation;
import connexion.ClientTCP;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class connectionGUI extends Application implements IconnectionGUI{
    public VBox mainPane;
    public Stage primaryStage;

    // Données pour la connection
    public String unNomServeur = "localhost";
    public int unNumero = 6666;
    public ClientTCP monClientTCP;

    public void start(Stage primaryStage) throws Exception{
        try {
            this.primaryStage = primaryStage;
            mainPane = new VBox();
            mainPane.getStyleClass().add("Connection");
            this.primaryStage.setTitle("EnstarDesktop");
            Scene myScene  = new Scene(mainPane, 300, 400);

            this.primaryStage.setScene(myScene);
            this.primaryStage.setResizable(true);
            this.primaryStage.show();

            monClientTCP = new ClientTCP(unNomServeur, unNumero);
            monClientTCP.connecterAuServeur();

            this.primaryStage.setOnCloseRequest(event -> {
                        QuitterContext quitterContext = new QuitterContext();
                        try {
                            monClientTCP.transmettreContext(quitterContext);
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        System.exit(0);});

            Connection();
        } catch (Exception e ) {e.printStackTrace();}
    }

    public void Connection() throws Exception {
        mainPane = new VBox();

        //TextFields
        TextField nomUtilisateurTextField = new TextField();
        nomUtilisateurTextField.setPromptText("Nom d'utilisateur");
        nomUtilisateurTextField.setMinSize(250, 40);

        PasswordField motDePasseTextField = new PasswordField();
        motDePasseTextField.setPromptText("Mot de passe");
        motDePasseTextField.setMinSize(250, 40);

        //Error labels
        Label errorUsername = new Label("");
        errorUsername.setAlignment(Pos.CENTER_LEFT);
        Label errorPassword = new Label("");
        errorPassword.setAlignment(Pos.CENTER_LEFT);

        //TextField area
        VBox areaText = new VBox();
        areaText.getChildren().addAll(nomUtilisateurTextField, errorUsername, motDePasseTextField, errorPassword);
        VBox.setMargin(areaText, new Insets(10, 10, 10, 10));
        areaText.setSpacing(10);
        areaText.setAlignment(Pos.BOTTOM_CENTER);

        //Boutons
        Button bouttonConnection = new Button("Connection");
        bouttonConnection.setMinSize(150, 40);
        bouttonConnection.setOnAction(actionevent -> {
            //creating Login Context
            String login = nomUtilisateurTextField.getText();
            String pass = motDePasseTextField.getText();
            LoginContext myLoginContext = new LoginContext(login, pass);
            try {
                IContext context = monClientTCP.transmettreContext(myLoginContext);
                String etat = context.getEtat();

                if (etat.equals("Connexion validee")){
                    listConversations(context);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        Button bouttonNouveauCompte = new Button("Nouveau compte");
        bouttonNouveauCompte.setMinSize(150, 40);
        bouttonNouveauCompte.setOnAction(actionEvent -> {
            try {
                CreationCompte();
            } catch (Exception e) {e.printStackTrace();}
        });

        //Button Area
        VBox areaButton = new VBox();
        areaButton.getChildren().addAll(bouttonConnection,bouttonNouveauCompte);
        VBox.setMargin(areaButton, new Insets(10,10,10,10));
        areaButton.setSpacing(30);
        areaButton.setAlignment(Pos.CENTER);

        //textfield keypress events
        nomUtilisateurTextField.setOnKeyPressed((e -> {
            if (e.getCode() == KeyCode.ENTER) {
                motDePasseTextField.requestFocus();}
        }));
        motDePasseTextField.setOnKeyPressed((e -> {
            if (e.getCode() == KeyCode.ENTER) {
                bouttonConnection.fire();}
        }));

        //Creating main Pane
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setSpacing(30);
        mainPane.getChildren().addAll(areaText,areaButton);
        Scene myScene = new Scene(mainPane, 300, 400);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }
    public void CreationCompte() throws Exception {
        mainPane = new VBox();

        // Textfields
        TextField nomUtilisateurTextField = new TextField();
        nomUtilisateurTextField.setPromptText("Entrez votre nom d'utilisateur");
        nomUtilisateurTextField.setMinSize(250, 40);

        PasswordField motDePasseTextField = new PasswordField();
        motDePasseTextField.setPromptText("Votre mot de passe");
        motDePasseTextField.setMinSize(250, 40);
        PasswordField verificationMotdePasseTextField = new PasswordField();
        verificationMotdePasseTextField.setPromptText("Vérification mot de passe");
        verificationMotdePasseTextField.setMinSize(250, 40);

        //Error Labels
        Label errorNomUtilisateur = new Label("");
        errorNomUtilisateur.setAlignment(Pos.CENTER_LEFT);
        Label errorMotdePasse = new Label("");
        errorMotdePasse.setAlignment(Pos.CENTER_LEFT);
        Label errorMotdePasseVerifier = new Label("");
        errorMotdePasseVerifier.setAlignment(Pos.CENTER_LEFT);

        //TextField area
        VBox areaText = new VBox();
        areaText.getChildren().addAll(nomUtilisateurTextField,errorNomUtilisateur,motDePasseTextField,errorMotdePasse,verificationMotdePasseTextField,errorMotdePasseVerifier);
        VBox.setMargin(areaText, new Insets(10,10,10,10));
        areaText.setSpacing(5);
        areaText.setAlignment(Pos.BOTTOM_CENTER);

        //Buttons
        Button bouttonCreation = new Button("Créer un nouveau compte");
        Button bouttonConnection = new Button("Vous avez déjà un compte");
        bouttonCreation.setMinSize(150,40);
        bouttonConnection.setMinSize(150,40);
        bouttonCreation.setOnAction(actionEvent -> {
            String login = nomUtilisateurTextField.getText();
            String pass = motDePasseTextField.getText();
            String verifPass = verificationMotdePasseTextField.getText();
            CreationCompteContext compteContext = new CreationCompteContext(login, pass, verifPass);
            try {
                monClientTCP.transmettreContext(compteContext);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        bouttonConnection.setOnAction(actionevent -> {
            try {Connection();
            } catch (Exception e) {e.printStackTrace();}
        });

        VBox areaButton = new VBox();
        areaButton.getChildren().addAll(bouttonCreation,bouttonConnection);
        VBox.setMargin(areaButton, new Insets(10,10,10,10));
        areaButton.setSpacing(30);
        areaButton.setAlignment(Pos.CENTER);

        //Creating main Pane
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setSpacing(30);
        mainPane.getChildren().addAll(areaText,areaButton);
        Scene myScene = new Scene(mainPane, 300, 400);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    public void listConversations(IContext context) {
        // IHM n°2 après connexion - Présentation des conversations sélectionnables

        mainPane = new VBox();

        // Contexte pour récupérer la Liste des conversations liées au compte
        LoginContext loginContext = (LoginContext) context;

        // Text
        Text text = new Text(10,30,"Sélectionner une conversation");
        text.setFont(new Font(15));
        text.setFill(Color.BLUE);

        Pane paneText = new Pane();
        paneText.getChildren().setAll(text);

        // Bouton nouvelle conversation
        Button newConvButton = new Button("Nouvelle Conversation");
        newConvButton.setMinSize(150, 40);
        newConvButton.setTextFill(Color.BLUE);
        newConvButton.setOnAction(actionevent -> {
            // TODO on a besoiin des fields de nom de convo, users et message de début
            // TODO : coté serveur nouvelle convo
        });

        // Boutons Conversations
        ArrayList<Button> buttonList = new ArrayList<Button>();
        for (Conversation convo : loginContext.compte.getListConvos()) {
            Button newButtonConvo = new Button(convo.getName());
            newButtonConvo.setMinSize(150, 40);
            newButtonConvo.setOnAction(actionevent -> {
                AffichageConvoContext context1 = new AffichageConvoContext(convo.getName(), loginContext.compte);
                try {
                    context1 = (AffichageConvoContext) monClientTCP.transmettreContext(context1);
                    conversation(context1);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
            buttonList.add(newButtonConvo);
        }
        VBox areaNewConvButton = new VBox();
        areaNewConvButton.getChildren().addAll(newConvButton);
        VBox.setMargin(areaNewConvButton, new Insets(10,10,10,10));
        areaNewConvButton.setAlignment(Pos.TOP_LEFT);

        VBox areaButton = new VBox();
        areaButton.getChildren().addAll(buttonList);
        VBox.setMargin(areaButton, new Insets(10,10,10,10));
        //areaButton.setSpacing(30);
        areaButton.setAlignment(Pos.TOP_LEFT);

        //Creating main Pane
        mainPane.setAlignment(Pos.TOP_LEFT);
        //mainPane.setSpacing(30);
        mainPane.getChildren().addAll(paneText,areaNewConvButton,areaButton);
        Scene myScene = new Scene(mainPane, 300, 400);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    public void conversation(IContext context) {
        // IHM n°3 - Affichage conversation

        mainPane = new VBox();

        context = (AffichageConvoContext) context;
        // TextArea Messagerie
        TextArea textsMessagerie = new TextArea();
        for (String mess : ((AffichageConvoContext) context).getConversation().getLogs()) {
             textsMessagerie.setText(mess+"\n");

        }
        textsMessagerie.setStyle("-fx-font-size: 12 px");

        // Textfield Entrer Message
        TextField entrerMessage = new TextField();
        entrerMessage.setPromptText("Ecrire votre message ici ...");
        entrerMessage.setMinSize(150, 40);

        // Bouton Envoie Message
        Button envoieMessageButton = new Button();
        Image imageEnvoieMessage = new Image(getClass().getResourceAsStream("envoieMessage.png"));
        envoieMessageButton.setGraphic(new ImageView(imageEnvoieMessage));
        envoieMessageButton.setOnAction(actionevent -> {
            String message = entrerMessage.getText();
            // todo : context mp uypload
        });

        //HBox zone écriture message
        HBox areaMessagerie = new HBox();
        areaMessagerie.getChildren().addAll(entrerMessage,envoieMessageButton);
        VBox.setMargin(areaMessagerie, new Insets(10,10,10,10));
        //areaButton.setSpacing(30);
        areaMessagerie.setAlignment(Pos.CENTER);

        //Creating main Pane
        mainPane.setAlignment(Pos.CENTER);
        //mainPane.setSpacing(30);
        mainPane.getChildren().addAll(textsMessagerie,areaMessagerie);
        Scene myScene = new Scene(mainPane, 600, 600);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
