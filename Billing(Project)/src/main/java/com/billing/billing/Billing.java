package com.billing.billing;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Billing extends Application {

    // create sign_in and create_account buttons
    private Button create_btn;
    Stage login_window;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        login_window = stage;
        // set the logo
        Image icon = new Image("Logo.jpg");
        login_window.getIcons().add(icon);
        // give the title
        login_window.setTitle("GeekBasis \"Login\"");
        // what is going to happen when x is pressed?
        login_window.setOnCloseRequest(e-> {
            e.consume(); // consumes the close event
            closeProgram();
        });
        // adjust dimensions
        login_window.setWidth(1100);
        login_window.setHeight(680);

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
        big_lbl.setFont(Font.font("Century Gothic", FontWeight.BOLD, FontPosture.REGULAR, 37));
        box_lbl1.getChildren().add(big_lbl);
        // setting the margin
        box_lbl1.setMargin(big_lbl, new Insets(10, 10, 25, 15));
        box_lbl1.setStyle("-fx-text-fill: green;");

        big_text.add(box_lbl1, 0, 1);

        // content for name and surname
        GridPane name_surname_field = new GridPane();

        // name input
        TextField name_input = new TextField();
        name_input.setPromptText("Name");
        name_input.setPrefHeight(30);
        name_input.setStyle("-fx-opacity: 0.7;");
        name_surname_field.add(name_input, 0, 0);

        // last name input
        TextField l_name_input = new TextField();
        l_name_input.setPromptText("Last Name");
        l_name_input.setPrefHeight(30);
        l_name_input.setStyle("-fx-opacity: 0.7;");
        name_surname_field.add(l_name_input, 1, 0);

        // setting margins for input fields
        name_surname_field.setMargin(name_input, new Insets(0,10,10,0));
        name_surname_field.setMargin(l_name_input, new Insets(0,0,10,10));

        // content for passwords
        VBox em_pass_field = new VBox();

        // email_input
        TextField email_input = new TextField();
        email_input.setPromptText("Email");
        email_input.setPrefHeight(30);
        email_input.setStyle("-fx-opacity: 0.7;");

        // password input
        PasswordField pass_field = new PasswordField();
        pass_field.setPromptText("Password");
        pass_field.setPrefHeight(30);
        pass_field.setStyle("-fx-opacity: 0.7;");
        em_pass_field.getChildren().addAll(email_input, pass_field);

        // setting margins for input fields
        em_pass_field.setMargin(email_input, new Insets(0,0,10,0));
        em_pass_field.setMargin(pass_field, new Insets(0,0,10,0));

        // for the create account button
        GridPane btn_fld = new GridPane();

        // Create Account button
        create_btn = new Button("Create Account");
        create_btn.setOnAction(e -> System.out.println("Creating"));
        create_btn.setStyle("-fx-opacity: 0.9;");
        // event handling for the create button
        create_btn.setOnAction(e -> {
            isProper(name_input, name_input.getText());
            isProper(l_name_input, l_name_input.getText());
        });

        btn_fld.add(create_btn, 0,0);

        // setting a margin for create button
        btn_fld.setMargin(create_btn, new Insets(5,0,0,110));

        // login content
        GridPane login_content = new GridPane();
        login_content.add(big_text, 0, 0);
        login_content.add(name_surname_field, 0, 1);
        login_content.add(em_pass_field, 0, 2);
        login_content.add(btn_fld, 0, 3);

        login_content.setAlignment(Pos.CENTER);
        main_pane.setCenter(login_content);

        // stage options
        login_window.setResizable(false);

        // a main scene containing 2 scenes with logo and login field
        Scene main_scene = new Scene(main_pane);

        // display the stage with the scene
        login_window.setScene(main_scene);
        login_window.show();
    }

    // a method used when closing the app
    private void closeProgram(){
        Boolean answer = ConfirmBox.display("Exit", "Do you want to exit?");
        if(answer) {
            login_window.close();
            System.out.println("Exited at " + java.time.LocalTime.now());
        }
    }

    // for checking for a valid input
    private boolean isProper(TextField text_input_field, String message){
            try {
                if  (text_input_field.getText()==null
                        || text_input_field.getText().equals("")
                        || text_input_field.getText().equals("Invalid input")) {
                }
                else{
                    int input = Integer.parseInt(text_input_field.getText());
                    System.out.println("Error: " + message + " is not a text!");
                }
                text_input_field.clear();
                text_input_field.setPromptText("Invalid input");
                text_input_field.setStyle("-fx-border-color: red;-fx-font-color: red;");
                return false;
            }catch (NumberFormatException e){
                text_input_field.setStyle("-fx-border-color: none; -fx-opacity: 0.7; -fx-font-color: black;");
                return true;
            }
    }
}

