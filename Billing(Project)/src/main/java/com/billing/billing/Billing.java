package com.billing.billing;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Billing extends Application {

    // create sign_in and create_account buttons
    private Button create_btn, sign_in;
    Scene signIn_scene;

    @Override
    public void start(Stage stage) throws IOException {

        // Creating a signIn scene and that's layout
        BorderPane signIn = new BorderPane();

        // Back Image for the signIn scene
        Image signIn_back_image = new Image("signIn_back_img.jpg", 1200, 780, false, false);
        BackgroundImage signIn_bImg = new BackgroundImage(signIn_back_image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background signIn_b_ground = new Background(signIn_bImg);
        signIn.setBackground(signIn_b_ground);

        // Label of the signIn scene
        Label hello_label = new Label("Hello \nYou are in the Sign-In Scene");

        // change positioning
        BorderPane.setAlignment(hello_label, Pos.CENTER);
        // change the font and the position
        hello_label.setFont(Font.font("Century Gothic", FontWeight.BOLD, FontPosture.REGULAR, 45));
        signIn.setTop(hello_label);

        signIn_scene = new Scene(signIn);

        // set the logo
        Image icon = new Image("Logo.jpg");
        stage.getIcons().add(icon);
        // give the title
        stage.setTitle("GeekBasis");
        // adjust dimensions
        stage.setWidth(1100);
        stage.setHeight(680);

        // main pane
        BorderPane main_pane = new BorderPane();

        // main background image
        Image back_image = new Image("back_img.jpg", 1200, 780, false, false);
        BackgroundImage bImg = new BackgroundImage(back_image,
                                                    BackgroundRepeat.NO_REPEAT,
                                                    BackgroundRepeat.NO_REPEAT,
                                                    BackgroundPosition.DEFAULT,
                                                    BackgroundSize.DEFAULT);
        Background b_ground = new Background(bImg);
        main_pane.setBackground(b_ground);

        // image for the logo field
        Image login_image = new Image("Logo_login.png", 100, 100, false, false);
        ImageView imageView = new ImageView(login_image);

        // putting the image in the center
        BorderPane.setAlignment(imageView, Pos.CENTER);

        // Pane of the logo field containing the image
        main_pane.setBottom(imageView);

        // content for the label of the login field
        GridPane big_text = new GridPane();

        HBox box_lbl1 = new HBox();

        // big text
        Text big_lbl = new Text("Create Account");
        big_lbl.setFont(Font.font("Century Gothic", FontWeight.BOLD, FontPosture.REGULAR, 35));
        box_lbl1.getChildren().add(big_lbl);

        big_text.add(box_lbl1, 0, 1);

        // content for name and surname
        GridPane name_surname_field = new GridPane();

        // name input
        TextField name_input = new TextField("Name");
        name_surname_field.add(name_input, 0, 0);

        // last name input
        TextField l_name_input = new TextField("Last Name");
        name_surname_field.add(l_name_input, 1, 0);

        // content for passwords
        VBox em_pass_field = new VBox();

        // email_input
        TextField email_input = new TextField("Email");

        // password input
        PasswordField pass_field = new PasswordField();
        pass_field.setPromptText("Password");
        em_pass_field.getChildren().addAll(email_input, pass_field);

        // for the create account button
        GridPane btn_fld = new GridPane();

        // Create Account button
        create_btn = new Button("Create Account");
        create_btn.setOnAction(e -> System.out.println("Creating"));
        btn_fld.add(create_btn, 0,0);

        // Sign in button
        sign_in = new Button("Sign In");
        // set action handling
        sign_in.setOnAction(e -> stage.setScene(signIn_scene));
        btn_fld.add(sign_in, 1, 0);

        // login content
        GridPane login_content = new GridPane();
        login_content.add(big_text, 0, 0);
        login_content.add(name_surname_field, 0, 1);
        login_content.add(em_pass_field, 0, 2);
        login_content.add(btn_fld, 0, 3);


        login_content.setAlignment(Pos.CENTER);
        main_pane.setCenter(login_content);

        // stage options
        stage.setResizable(false);

        // a main scene containing 2 scenes with logo and login field
        Scene main_scene = new Scene(main_pane);

        // display the stage with the scene
        stage.setScene(main_scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}