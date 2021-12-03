package com.billing.billing;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BillPayBox {

    static boolean answer;
    static boolean pay;

    public static void PayDisplay() {

        //GridPane for the components of pay window
        GridPane gridPane = new GridPane();

        // paying window
        Stage window = new Stage();

        // set the logo
        Image icon = new Image("Logo.jpg");
        window.getIcons().add(icon);

        // does not allow us interacting with the main window
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Payment Info");
        window.setMinWidth(235);
        window.setMinHeight(90);

        // main content of the confirm-box
        // the layout for the confirmBox components
        VBox confirm_pay_layout = new VBox(7);

        // pay window components
        Label lbl = new Label();
        lbl.setText("Successfully payed");
        lbl.setStyle("-fx-font-family: 'Century Gothic'; -fx-font-size: 17px;");
        Button yesButton = new Button("OK");

        confirm_pay_layout.getChildren().addAll(lbl, yesButton);
        confirm_pay_layout.setAlignment(Pos.CENTER);

        Scene confirm_scene = new Scene(confirm_pay_layout);
        window.setScene(confirm_scene);

        // pay window properties
        Stage bill_pay = new Stage();
        bill_pay.getIcons().add(icon);
        bill_pay.setHeight(300);
        bill_pay.setWidth(240);
        bill_pay.setResizable(false);

        // does not allow us interacting with the main window
        bill_pay.initModality(Modality.APPLICATION_MODAL);
        bill_pay.setTitle("GeekBasis \"Pay\"");

        TextField your_account = new TextField();
        your_account.setPromptText("Account № ");

        TextField money = new TextField();
        money.setPromptText("Amount ");

        // pay window components
        Button btn_pay = new Button("Pay");

        // for choosing where to pay
        ComboBox comboBox = new ComboBox();

        comboBox.setPromptText("Where?");

        comboBox.getItems().add("Internet");
        comboBox.getItems().add("Loan");
        comboBox.getItems().add("Mobile Operators");
        comboBox.getItems().add("Utility");

        HBox bill_begin = new HBox(comboBox);
        HBox.setMargin(bill_begin, new Insets(10, 10, 25, 15));

        Font font = Font.font("Century Gothic", FontWeight.BOLD, 24);
        btn_pay.setFont(font);

        HBox money_amount = new HBox(money);

        Label label1 = new Label("Payment");
        BorderPane.setAlignment(label1, Pos.CENTER);
        label1.setFont(Font.font("Century Gothic", FontWeight.BOLD, FontPosture.REGULAR, 28));

        TextField name_specific = new TextField();
        name_specific.setPromptText("Specifically to?");

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(label1, 1, 0);
        // gridPane.add(label2, 0, 1);
        gridPane.add(bill_begin, 1, 1);
        // gridPane.add(label3, 0, 2);
        gridPane.add(your_account, 1, 2);
        // gridPane.add(label3, 0, 3);
        gridPane.add(name_specific, 1, 3);
        //  gridPane.add(label4, 0, 4);
        gridPane.add(money_amount, 1, 4);
        gridPane.add(btn_pay, 1, 6);
        GridPane.setMargin(btn_pay, new Insets(0, 0, 0, 30));
        gridPane.setAlignment(Pos.CENTER);

        //to feel pay button pressed
        btn_pay.setOnAction(e -> {
            isNotEmpty(your_account);
            isNotEmpty(money);
            isNotEmpty(name_specific);

            if (isNotEmpty(your_account) && isNotEmpty(money)){
                pay = true;
            }
        });

        // when yes button is pressed
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
            bill_pay.close();
        });

        VBox types = new VBox(gridPane);
        Scene billPay = new Scene(types, 300, 120);
        bill_pay.setScene(billPay);
        bill_pay.showAndWait();
    }

    private static boolean isNotEmpty(TextField text_input_field) {
        if (text_input_field.getText() == null
                || text_input_field.getText().equals("")
                || text_input_field.getText().equals("Invalid input")) {
            text_input_field.setStyle("-fx-border-color: red;");
            text_input_field.setPromptText("Invalid input");
            return false;
        } else {
            text_input_field.setStyle("-fx-border-color: none; -fx-opacity: 0.7;");
            return true;
        }

    }
}

