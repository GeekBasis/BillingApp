package com.billing.billing;

import java.sql.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
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
    Scene login_scene, main_scene;

    @Override
    public void start(Stage stage) {

        // main pane
        BorderPane main_pane = new BorderPane();

        // login content
        GridPane login_content = new GridPane();

        // MySQL set up
        Connection connect = null;
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/billing", "xxxx", "xxxxxx");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        Statement query = null;
        try {
            assert connect != null;
            query = connect.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

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

        main_app.setLeft(addVBox());
        main_app.setCenter(addGridPane1());


        Scene main_app_scene = new Scene(main_app);

        // adding keyboard events to the main app scene
        main_app_scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                if (closeProgram()) {
                    main_app_window.close();
                }
            }
        });

        main_app_window.setScene(main_app_scene);

        // Login Scene content
        BorderPane main_pane_login = new BorderPane();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15, 15, 15, 15));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        // login back image
        Image back_image_login = new Image("login_back.jpg", 1200, 780, false, false);
        BackgroundImage bImg_login = new BackgroundImage(back_image_login,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background b_ground_login = new Background(bImg_login);

        main_pane_login.setBackground(b_ground_login);

        Image login_image = new Image("Logo_login.png", 100, 100, false, false);
        ImageView imageView_login = new ImageView(login_image);

        //Header Text
        Text login_page = new Text("Login Page");
        GridPane.setConstraints(login_page, 0, 0);
        login_page.setStyle("-fx-font-size: 30pt; ");

        //Name Label
        Label  nameLabel = new Label("Username: ");
        GridPane.setConstraints(nameLabel, 1, 1);
        nameLabel.setStyle("-fx-font-size: 12pt");

        //Name Input
        TextField nameInput = new TextField();
        nameInput.setPromptText("Username");
        GridPane.setConstraints(nameInput, 2, 1);
        nameInput.setStyle("-fx-font-size: 12pt");

        //Password Label
        Label passwordLabel = new Label("Password: ");
        GridPane.setConstraints(passwordLabel, 1, 2);
        passwordLabel.setStyle("-fx-font-size: 12pt");

        //Password Input
        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("Password");
        GridPane.setConstraints(passwordInput,  2, 2);
        passwordInput.setStyle("-fx-font-size: 12pt;");

        // sign in button
        Button login_button = new Button("Sign In");
        Statement finalQuery1 = query;
        login_button.setOnAction(e->
        {
            isProper(nameInput);
            isProperPassword(passwordInput);
            if (isProper(nameInput) && isProperPassword(passwordInput)){

                ////////// SQL //////////////
                String selectStatement = "select Name, Password from users where Name= '"+nameInput.getText()+"' and Password= '"+passwordInput.getText()+"'";
                ResultSet bool;
                try {
                    assert finalQuery1 != null;
                    bool = finalQuery1.executeQuery(selectStatement);
                    if(!bool.next()){
                        nameInput.clear();
                        passwordInput.clear();
                        nameInput.setStyle("-fx-border-color: red;-fx-font-color: red;");
                        nameInput.setPromptText("Wrong data entered");
                        passwordInput.setStyle("-fx-border-color: red;-fx-font-color: red;");
                        passwordInput.setPromptText("Wrong data entered");
                    }
                    else{
                        nameInput.setStyle("-fx-border-color: none;");
                        nameInput.setPromptText("Name");
                        passwordInput.setStyle("-fx-border-color: none;");
                        passwordInput.setPromptText("Password");
                        nameInput.clear();
                        passwordInput.clear();
                        main_window.close();
                        main_app_window.show();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }

        });
        Button create_acc_btn = new Button("Sign Up");
        // add an action to the sign-up button
        create_acc_btn.setOnAction(e->{

            main_window.close();
            main_window.setScene(main_scene);
            nameInput.setStyle("-fx-border-color: none;");
            nameInput.setPromptText("Name");
            passwordInput.setStyle("-fx-border-color: none;");
            passwordInput.setPromptText("Password");
            // Clear fields of name and lastname
            name_input.setStyle("-fx-border-color: none; -fx-opacity: 0.7; -fx-font-color: black;");
            name_input.setPromptText("Name");
            l_name_input.setStyle("-fx-border-color: none; -fx-opacity: 0.7; -fx-font-color: black;");
            name_input.setPromptText("Last Name");
            cleaner(); // cleans the inputted fields
            main_window.show();
            main_window.setTitle("GeekBasis \"Sign Up\"");


        });

        // A layout for buttons
        HBox btn_layout = new HBox(5);
        btn_layout.getChildren().addAll(login_button, create_acc_btn);

        GridPane.setConstraints(btn_layout, 2, 3);
        login_button.setStyle("-fx-font-size: 12pt");
        create_acc_btn.setStyle("-fx-font-size: 12pt");
        grid.getChildren().addAll(login_page, nameLabel, nameInput, passwordLabel, passwordInput, btn_layout);

        main_pane_login.setCenter(grid);
        main_pane_login.setBottom(imageView_login);
        BorderPane.setAlignment(imageView_login, Pos.CENTER); //Alligning everything to the center
        BorderPane.setAlignment(grid, Pos.CENTER); //Alligning everything to the center

        // the scene of the login page
        login_scene = new Scene(main_pane_login, 1200, 700);

        // Main window of the registration and login
        main_window = stage;
        // set the logo for the registration window
        main_window.getIcons().add(icon);

        // give the title
        main_window.setTitle("GeekBasis \"Sign Up\"");

        // what is going to happen when x is pressed?
        main_window.setOnCloseRequest(e-> {
            e.consume(); // consumes the close event
            if(closeProgram()) main_window.close();
        });

        // what is going to happen when x is pressed? in the main app
        main_app_window.setOnCloseRequest(e-> {
            e.consume(); // consumes the close event
            if(closeProgram()) main_app_window.close();
        });

        // adjust dimensions
        main_window.setWidth(1100);
        main_window.setHeight(680);

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
        Image sign_in_image = new Image("Logo_login.png", 100, 100, false, false);
        ImageView imageView = new ImageView(sign_in_image);

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
        Statement finalQuery = query;
        create_btn.setOnAction(e -> {
            isProper(name_input);
            isProper(l_name_input);
            isProperEmail(email_input);
            isProperPassword(pass_field);
            if(isProper(name_input)
                    &&
                    isProper(l_name_input)
                    &&
                    isProperEmail(email_input)
                    &&
                    isProperPassword(pass_field)){
                String insertStatement = "insert into users values ('"+name_input.getText()+"','"+l_name_input.getText()+"','"+email_input.getText()+"', '"+pass_field.getText()+"')";
                try {
                    assert finalQuery != null;
                    finalQuery.executeUpdate(insertStatement);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                main_window.close();
                main_window.setScene(login_scene);
                main_window.show();
            }
        });

        // Login Button
        Button login_btn = new Button("Sign In");
        login_btn.setOnAction(e -> {
            main_window.close();
            main_window.setScene(login_scene);
            main_window.setTitle("GeekBasis \"Sign In\"");
            main_window.show();
        });

        btn_fld.add(create_btn, 0,0);
        btn_fld.add(login_btn, 1, 0);

        // setting a margin for create button
        GridPane.setMargin(create_btn, new Insets(5,10,10,90));
        GridPane.setMargin(login_btn, new Insets(5,0,10,10));

        login_content.add(big_text, 0, 0);
        login_content.add(name_surname_field, 0, 1);
        login_content.add(em_pass_field, 0, 2);
        login_content.add(btn_fld, 0, 3);

        login_content.setAlignment(Pos.CENTER);
        main_pane.setCenter(login_content);

        // stage options
        main_window.setResizable(false);

        // a main scene containing 2 scenes with logo and login field
        main_scene = new Scene(main_pane);

        // adding keyboard events to the main scene
        main_scene.setOnKeyPressed(event -> {
            switch (event.getCode()){
                case ESCAPE: if(closeProgram()){main_window.close();} break;
                case ENTER: {
                    isProper(name_input);
                    isProper(l_name_input);
                    isProper(email_input);
                    isProperPassword(pass_field);
                    if(isProper(name_input)
                            &&
                            isProper(l_name_input)
                            &&
                            isProper(email_input)
                            &&
                            isProperPassword(pass_field)){
                        main_window.setScene(login_scene);
                    }
                } break;
            }
        });

        // adding keyboard events to the login scene
        login_scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                if (closeProgram()) {
                    main_window.close();
                }
            }
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
    private boolean isProper(TextField text_input_field){
        try {
            if  (text_input_field.getText()==null
                    || text_input_field.getText().equals("")
                    || text_input_field.getText().equals("Invalid input")) {
                text_input_field.setStyle("-fx-border-color: red;-fx-font-color: red;");
            }
            else{
                Integer.parseInt(text_input_field.getText());
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

    // for checking for a valid input of email
    private boolean isProperEmail(TextField email_input_field){
        if  (email_input_field.getText()==null
                || email_input_field.getText().equals("")
                || email_input_field.getText().equals("Invalid input")) {
            email_input_field.setStyle("-fx-border-color: red;-fx-font-color: red;");
            email_input_field.clear();
            email_input_field.setPromptText("Invalid input");
            return false;
        }
        else {
            email_input_field.setStyle("-fx-border-color: none; -fx-opacity: 0.7; -fx-font-color: black;");
            return true;
        }
    }

    // for checking for a valid input of password
    private boolean isProperPassword(PasswordField password_input_field){
            if  (password_input_field.getText()==null
                    || password_input_field.getText().equals("")
                    || password_input_field.getText().equals("Invalid input")) {
                password_input_field.setStyle("-fx-border-color: red;-fx-font-color: red;");
                password_input_field.clear();
                password_input_field.setPromptText("Invalid input");
                return false;
            }
            else {
                password_input_field.setStyle("-fx-border-color: none; -fx-opacity: 0.7; -fx-font-color: black;");
                return true;
            }
    }

    private void payProgram() {
        BillPayBox.PayDisplay();
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
                new Hyperlink("Profile"),
                new Hyperlink("Pay"),
                new Hyperlink("New Account"),
                new Hyperlink("Log Out")};

        // hyperlinks in the main app
        options[0].setOnAction(e->options[0].setVisited(false));
        options[1].setOnAction(e-> {
            payProgram();
            options[1].setVisited(false);
        });
        //create new account option
        options[2].setOnAction(e->{
            main_app_window.close();
            cleaner(); // cleans the input fields
            main_window.close();
            main_window.setScene(main_scene);
            main_window.show();
            options[2].setVisited(false);
        });
        //exit option
        options[3].setOnAction(e->{
            main_app_window.close();
            main_window.setScene(login_scene);
            main_window.show();
            options[3].setVisited(false);
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

        // Left label in column 1 (bottom), row 3
        Text goodsPercent = new Text("Goods");
        goodsPercent.setFont(Font.font("Century Gothic", FontWeight.BOLD, FontPosture.REGULAR,19));

        GridPane.setValignment(goodsPercent, VPos.BOTTOM);
        grid.add(goodsPercent, 0, 2);


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
