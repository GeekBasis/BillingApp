package com.billing.billing;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BillingController {

    public void signIn(ActionEvent e){
        System.out.println("Sign-In button has been pressed");
    }

    public void createAccount(ActionEvent e){
        System.out.println("Create-Account button has been pressed");
    }
}