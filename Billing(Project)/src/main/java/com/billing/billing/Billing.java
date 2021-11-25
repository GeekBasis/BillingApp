package com.billing.billing;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class Billing extends Application {

    Stage main_window;


    @Override
    public void start(Stage stage) {

        // Login Scene content
        VBox content = new VBox();
        Label say_hi = new Label("Say Hi");
        Button create_acc_btn = new Button("Sign Up");
        content.getChildren().addAll(say_hi, create_acc_btn);
        Scene login_scene = new Scene(content);

	    // Main window of the registration and login
        main_window = stage;
     
        // set the logo
        Image icon = new Image("Logo.jpg");
        main_window.getIcons().add(icon);

        // give the title
        main_window.setTitle("GeekBasis \"Sign Up\"");

        // what is going to happen when x is pressed?
        main_window.setOnCloseRequest(e-> {
            e.consume(); // consumes the close event
            closeProgram();
        });

        // adjust dimensions
        main_window.setWidth(1100);
        main_window.setHeight(680);

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
        HBox.setMargin(big_lbl, new Insets(10, 10, 25, 15));
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
        GridPane.setMargin(name_input, new Insets(0,10,10,0));
        GridPane.setMargin(l_name_input, new Insets(0,0,10,10));

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
        VBox.setMargin(email_input, new Insets(0,0,10,0));
        VBox.setMargin(pass_field, new Insets(0,0,10,0));

        // for the create account button
        GridPane btn_fld = new GridPane();

        // Create Account button
        // create sign_in and create_account buttons
        Button create_btn = new Button("Sign Up");
        create_btn.setOnAction(e -> System.out.println("Creating"));
        create_btn.setStyle("-fx-opacity: 0.9;");
        // event handling for the create button
        create_btn.setOnAction(e -> {
            isProper(name_input, name_input.getText());
            isProper(l_name_input, l_name_input.getText());
        });
        create_btn.setStyle("-fx-opacity: 0.9;");
        // event handling for the create button
        create_btn.setOnAction(e -> {
            isProper(name_input, name_input.getText());
            isProper(l_name_input, l_name_input.getText());
        });

        // Login Button
        Button login_btn = new Button("Sign In");
        login_btn.setOnAction(e -> {
            main_window.setTitle("GeekBasis \"Sign In\"");
            main_window.setScene(login_scene);
        });

        btn_fld.add(create_btn, 0,0);
        btn_fld.add(login_btn, 1, 0);

        // setting a margin for create button
        GridPane.setMargin(create_btn, new Insets(5,10,10,90));
        GridPane.setMargin(login_btn, new Insets(5,0,10,10));

        // login content
        GridPane login_content = new GridPane();
        login_content.add(big_text, 0, 0);
        login_content.add(name_surname_field, 0, 1);
        login_content.add(em_pass_field, 0, 2);
        login_content.add(btn_fld, 0, 3);

        login_content.setAlignment(Pos.CENTER);
        main_pane.setCenter(login_content);

        // stage options
        main_window.setResizable(false);

        // a main scene containing 2 scenes with logo and login field
        Scene main_scene = new Scene(main_pane);

        // add an action to the sign-up button
        create_acc_btn.setOnAction(e->
        {
            main_window.setTitle("GeekBasis \"Sign Up\"");
            login_content.setPadding(new Insets(0, 280, 150, 0));

            // Clear fields of name and lastname
            name_input.setStyle("-fx-border-color: none; -fx-opacity: 0.7; -fx-font-color: black;");
            name_input.setPromptText("Name");
            l_name_input.setStyle("-fx-border-color: none; -fx-opacity: 0.7; -fx-font-color: black;");
            name_input.setPromptText("Last Name");
            main_window.setScene(main_scene);

        });

        // display the stage with the scene
        main_window.setScene(main_scene);
        main_window.show();

    }

    // a method used when closing the app
    private void closeProgram(){
        boolean answer = ConfirmBox.display("Exit", "Do you want to exit?");
        if(answer) {
            main_window.close();
            System.out.println("Exited at " + java.time.LocalTime.now());
        }
    }

    // for checking for a valid input
    private boolean isProper(TextField text_input_field, String message){
        try {
            if  (text_input_field.getText()==null
                    || text_input_field.getText().equals("")
                    || text_input_field.getText().equals("Invalid input")) {
                text_input_field.setStyle("-fx-border-color: red;-fx-font-color: red;");
            }
            else{
                Integer.parseInt(text_input_field.getText());
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

    public static void main(String[] args) {
        launch();
    }
}
