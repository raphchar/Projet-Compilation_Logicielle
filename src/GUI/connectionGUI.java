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

public class connectionGUI extends Application {
    public VBox mainPane;
    public Stage primaryStage;

    @Override
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

            this.primaryStage.setOnCloseRequest(event -> {
                System.exit(0);}
                );
            IdentifiantConnection();
        } catch (Exception e ) {e.printStackTrace();}
    }

    public void IdentifiantConnection() throws Exception {
        mainPane = new VBox();
        mainPane.getStyleClass().add("IdentifiantConnection");

        //TextFields
        TextField nomUtilisateurTextField = new TextField();
        nomUtilisateurTextField.getStyleClass().add("nomUtilisateurTextField");
        nomUtilisateurTextField.setPromptText("Nom d'utilisateur");
        nomUtilisateurTextField.setMinSize(250, 40);
        PasswordField motDePasseTextField = new PasswordField();
        motDePasseTextField.getStyleClass().add("motDePasseTextField");
        motDePasseTextField.setPromptText("Mot de passe");
        motDePasseTextField.setMinSize(250, 40);

        //Error labels
        Label errorUsername = new Label("");
        errorUsername.getStyleClass().add("labelError");
        errorUsername.setAlignment(Pos.CENTER_LEFT);
        Label errorPassword = new Label("");
        errorPassword.getStyleClass().add("labelError");
        errorPassword.setAlignment(Pos.CENTER_LEFT);

        //TextField area
        VBox areaText = new VBox();
        areaText.getChildren().addAll(nomUtilisateurTextField, errorUsername, motDePasseTextField, errorPassword);
        VBox.setMargin(areaText, new Insets(10, 10, 10, 10));
        areaText.setSpacing(10);
        areaText.setAlignment(Pos.BOTTOM_CENTER);

        //--BUTTONS--
        Button bouttonConnection = new Button("Connection");
        bouttonConnection.setMinSize(150, 40);
        bouttonConnection.getStyleClass().add("buttype1");

        Button bouttonNouveauCompte = new Button("Nouveau compte");
        bouttonNouveauCompte.setMinSize(150, 40);
        bouttonNouveauCompte.getStyleClass().add("buttype2");

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

    public static void main(String[] args) {
        launch(args);
    }
}
