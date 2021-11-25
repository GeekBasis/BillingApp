package com.billing.billing;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

class ConfirmBox {

    static boolean answer;

    public static boolean display(String title, String message){
        Stage window = new Stage();
        // set the logo
        Image icon = new Image("Logo.jpg");
        window.getIcons().add(icon);

        // does not allow us interacting with the main window
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        // the main question
        Label lbl = new Label();
        lbl.setText(message);
        lbl.setStyle("-fx-font-family: 'Century Gothic'; -fx-font-size: 17px;");

        // choices of buttons
        Button yesButton = new Button("Yes");
        yesButton.setOnAction(e-> {
            answer = true;
            window.close();
        });

        Button noButton = new Button("No");
        noButton.setOnAction(e->{
            answer = false;
            window.close();
        });

        // the layout for the confirmBox components
        HBox btn_layout = new HBox();
        btn_layout.setMargin(yesButton,new Insets(5,10,10,10));
        btn_layout.setMargin(noButton, new Insets(5,10,10,10));
        btn_layout.getChildren().addAll(yesButton, noButton);
        btn_layout.setAlignment(Pos.CENTER);

        // main content of the confirm box
        VBox confirm_content = new VBox();
        confirm_content.setMargin(lbl, new Insets(5,0,5,35));
        confirm_content.getChildren().addAll(lbl, btn_layout);

        // confirmBox's scene
        Scene confirm_scene = new Scene(confirm_content);
        window.setScene(confirm_scene);

        // waits until the window of the confirmBox is closed
        window.showAndWait();

        return answer;

    }

}