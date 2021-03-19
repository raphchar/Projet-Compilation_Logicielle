package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import launchPattern.Serveur;

public class connectionGUI extends Application implements IconnectionGUI{
    public VBox mainPane;
    public Stage primaryStage;
    // Données pour la connection
    public String unNomServeur = "localhost";
    public int unNumero = 6666;
    public Serveur monServeur;

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

            monServeur = new Serveur(unNomServeur, unNumero);
            monServeur.connectionAuServeur();

            this.primaryStage.setOnCloseRequest(event -> {
                System.exit(0);}
                );
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
        bouttonConnection.setOnAction(actionevent -> {
            try {Connection();} catch (Exception e) {e.printStackTrace();}
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
    public static void main(String[] args) {
        launch(args);
    }
}
