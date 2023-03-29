package com.example.template;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HelloController {
    @FXML
    private Label lbl;
    @FXML
    private GridPane gPane;

    @FXML
    private TextField txtField;

    ImageView[][] img = new ImageView[4][4];
    Image grass;

    FileInputStream grasss;

    public HelloController(){

        try {
            grasss = new FileInputStream("src/main/resources/grass.PNG");

            grass = new Image(grasss);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void onClick(){

        for(int i = 0; i<img.length; i++){
            for (int j = 0; j < img[0].length; j++) {
                img[i][j] = new ImageView();
                img[i][j].setImage(grass);
                img[i][j].setFitHeight(125);
                img[i][j].setFitWidth(125);
                img[i][j].setRotate(90);
                gPane.add(img[i][j], j, i);
            }
        }


       

    }

    private void doStuff(){
        System.out.println("this is where stuff happens");
    }


    private void potato(){
        System.out.println("potato");
    }

    private void tomato(){
        System.out.println("tomato");
    }

}