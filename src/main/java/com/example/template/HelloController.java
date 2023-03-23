package com.example.template;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label lbl;

    @FXML
    private TextField txtField;

    @FXML
    private void onClick(){
        System.out.println("hello");
        System.out.println("i am at home");

        System.out.println("danny is at home");
    }

}