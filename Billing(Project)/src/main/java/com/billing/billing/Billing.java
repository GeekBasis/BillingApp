package com.billing.billing;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Billing extends Application {

    Stage main_window, main_app_window;
    TextField name_input, l_name_input, email_input;
    PasswordField pass_field;
    Scene login_scene;

    @Override
    public void start(Stage stage) {

        // prepare a logo
        Image icon = new Image("Logo.jpg");

        // Main App Stage
        main_app_window = new Stage();

        // set the logo for the main app window
        main_app_window.getIcons().add(icon);

        // give the title
        main_app_window.setTitle("GeekBasis");

        // adjust dimensions
        main_app_window.setWidth(1100);
        main_app_window.setHeight(680);


        // Main App Scene Content
        BorderPane main_app = new BorderPane();

        // Label of the signIn scene
        Label hello_label = new Label(" HELLO\n");

        // change positioning
        BorderPane.setAlignment(hello_label, Pos.CENTER);
        hello_label.setFont(Font.font("Century Gothic", FontWeight.BOLD, FontPosture.REGULAR, 45));

        main_app.setLeft(addVBox());
        main_app.setCenter(addGridPane1());
        main_app.setRight(addGridPane2());


        Scene main_app_scene = new Scene(main_app);

        // adding keyboard events to the main app scene
        main_app_scene.setOnKeyPressed(new EventHandler<KeyEvent>(){

            @Override
            public void handle(KeyEvent event){
                switch (event.getCode()){
                    case ESCAPE: if(closeProgram()){main_app_window.close();} break;
                }
            }
        });

        main_app_window.setScene(main_app_scene);


        // Login Scene content
        VBox content = new VBox();
        Label say_hi = new Label("Say Hi");
        Button create_acc_btn = new Button("Sign Up");
        content.getChildren().addAll(say_hi, create_acc_btn);
        login_scene = new Scene(content);

	    // Main window of the registration and login
        main_window = stage;
        // set the logo for the registration window
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
        name_input = new TextField();
        name_input.setPromptText("Name");
        name_input.setPrefHeight(30);
        name_input.setStyle("-fx-opacity: 0.7;");
        name_surname_field.add(name_input, 0, 0);

        // last name input
        l_name_input = new TextField();
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
        email_input = new TextField();
        email_input.setPromptText("Email");
        email_input.setPrefHeight(30);
        email_input.setStyle("-fx-opacity: 0.7;");

        // password input
        pass_field = new PasswordField();
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
        create_btn.setStyle("-fx-opacity: 0.9;");
        // event handling for the create button
        create_btn.setOnAction(e -> {
            isProper(name_input, name_input.getText());
            isProper(l_name_input, l_name_input.getText());
            if(isProper(name_input, name_input.getText())
                    &&
                    isProper(l_name_input, l_name_input.getText())){
                    main_window.setScene(login_scene);
            }
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

        // adding keyboard events to the main scene
        main_scene.setOnKeyPressed(new EventHandler<KeyEvent>(){

            @Override
            public void handle(KeyEvent event){
                switch (event.getCode()){
                    case ESCAPE: if(closeProgram()){main_window.close();} break;
                    case ENTER: {
                        isProper(name_input, name_input.getText());
                        isProper(l_name_input, l_name_input.getText());
                        if(isProper(name_input, name_input.getText())
                                &&
                                isProper(l_name_input, l_name_input.getText())){
                            main_window.setScene(login_scene);
                        }
                    } break;
                }
            }
        });

        // adding keyboard events to the login scene
        login_scene.setOnKeyPressed(new EventHandler<KeyEvent>(){

            @Override
            public void handle(KeyEvent event){
                switch (event.getCode()){
                    case ESCAPE: if(closeProgram()){main_window.close();} break;
                }
            }
        });

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
            cleaner(); // cleans the inputted fields
            main_window.setScene(main_scene);

        });

        // change the cursor
        main_scene.setCursor(Cursor.HAND);

        // display the stage with the scene
        main_window.setScene(main_scene);
        main_window.show();

    }

    // a method used when closing the app
    private boolean closeProgram(){
        boolean answer = ConfirmBox.display("Exit", "Do you want to exit?");
        if(answer) {
            System.out.println("Exited at " + java.time.LocalTime.now());
            return true;
        }
        return false;
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

    public VBox addVBox() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(20));
        vbox.setSpacing(8);
        vbox.setStyle("-fx-background-color:  #2d333b;");

        Text title = new Text("Data");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setStyle("-fx-font-color:#adbac7;");
        vbox.getChildren().add(title);

        Hyperlink[] options = new Hyperlink[]{
                new Hyperlink("MyProfile"),
                new Hyperlink("Settings"),
                new Hyperlink("Create New Account"),
                new Hyperlink("Log Out")};

        // hyperlinks in the main app
        options[0].setOnAction(e->options[0].setVisited(false));
        options[1].setOnAction(e->options[1].setVisited(false));
        //create new account option
        options[2].setOnAction(e->{
            main_app_window.close();
            cleaner(); // cleans the input fields
            main_window.show();
            options[2].setVisited(false);
        });
        //exit option
        options[3].setOnAction(e->{
            main_app_window.close();
            main_window.setScene(login_scene);
            main_window.show();
        });

        for (int i = 0; i < 4; i++) {
            int num = i;
            VBox.setMargin(options[i], new Insets(0, 0, 0, 16));
            vbox.getChildren().add(options[i]);
            options[i].setOnMouseMoved(e->options[num].setStyle("-fx-font-color:white;"));
        }
        return vbox;
    }


    public GridPane addGridPane1() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        grid.setStyle("-fx-background-color:  #39166c;");


        // Category in column 2, row 1
        Text category = new Text("Sales:");
        category.setFont(Font.font("Century Gothic", FontWeight.BOLD, FontPosture.REGULAR,22));
        grid.add(category, 1, 0);

        // Title in column 3, row 1
        Text chartTitle = new Text("Current Year");
        chartTitle.setFont(Font.font("Century Gothic", FontWeight.BOLD, FontPosture.REGULAR,22));
        grid.add(chartTitle, 2, 0);

        // Subtitle in columns 2-3, row 2
        Text chartSubtitle = new Text("Goods and Services");
        chartSubtitle.setFont(Font.font("Century Gothic", FontWeight.BOLD, FontPosture.REGULAR,25));

        grid.add(chartSubtitle, 1, 1, 2, 1);

        // House icon in column 1, rows 1-2


        // Left label in column 1 (bottom), row 3
        Text goodsPercent = new Text("Goods");
        goodsPercent.setFont(Font.font("Century Gothic", FontWeight.BOLD, FontPosture.REGULAR,19));

        GridPane.setValignment(goodsPercent, VPos.BOTTOM);
        grid.add(goodsPercent, 0, 2);

        // Chart in columns 2-3, row 3


        // Right label in column 4 (top), row 3
        Text servicesPercent = new Text("Bills");
        GridPane.setValignment(servicesPercent, VPos.TOP);
        servicesPercent.setFont(Font.font("Century Gothic", FontWeight.BOLD, FontPosture.REGULAR,22));

        grid.add(servicesPercent, 3, 2);

        return grid;
    }

    public GridPane addGridPane2() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        grid.setStyle("-fx-background-color:  #04416c;");


        // Category in column 2, row 1
        Text category = new Text("Sales:");
        category.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.REGULAR,22));
        grid.add(category, 1, 0);

        // Title in column 3, row 1
        Text chartTitle = new Text("Current Year");
        chartTitle.setFont(Font.font("Century Gothic", FontWeight.BOLD, FontPosture.REGULAR,22));
        grid.add(chartTitle, 2, 0);

        // Subtitle in columns 2-3, row 2
        Text chartSubtitle = new Text("Goods and Services");
        chartSubtitle.setFont(Font.font("Century Gothic", FontWeight.BOLD, FontPosture.REGULAR,25));

        grid.add(chartSubtitle, 1, 1, 2, 1);

        // House icon in column 1, rows 1-2


        // Left label in column 1 (bottom), row 3
        Text goodsPercent = new Text("Goods");
        goodsPercent.setFont(Font.font("Century Gothic", FontWeight.BOLD, FontPosture.REGULAR,19));

        GridPane.setValignment(goodsPercent, VPos.BOTTOM);
        grid.add(goodsPercent, 0, 2);

        // Chart in columns 2-3, row 3


        // Right label in column 4 (top), row 3
        Text servicesPercent = new Text("Bills");
        GridPane.setValignment(servicesPercent, VPos.TOP);
        servicesPercent.setFont(Font.font("Century Gothic", FontWeight.BOLD, FontPosture.REGULAR,22));

        grid.add(servicesPercent, 3, 2);

        return grid;
    }

    // cleans text and password input fields when called
    public void cleaner(){
        name_input.clear();
        l_name_input.clear();
        email_input.clear();
        pass_field.clear();
    }


    public static void main(String[] args) {
        launch();
    }
}
