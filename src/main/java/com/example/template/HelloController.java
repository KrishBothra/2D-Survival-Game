package com.example.template;

import javafx.event.ActionEvent;
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

    private int x = 25;

    private int y = 41;

    ImageView[][] img = new ImageView[x][y];

    String[][] map = new String[x*8+1][y*8+1]; //100 //164
    Image grass;

//    int changeX = 0;
//    int changeY = 0;

    int playerPositionX = 99;//-1
    int playerPositionY = 163;//-1

    //12,20----13,21

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
                img[i][j].setFitHeight(40);
                img[i][j].setFitWidth(40);
                gPane.add(img[i][j], j, i);
            }
        }

        for(int i = 0; i<map.length; i++){
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = "grass";
            }
        }

//        img[12][20].setImage(null);
        map[98][160] = "null";
        map[99][163] = "null";


        gPane.setGridLinesVisible(true);

        updateScreen();

       

    }

    private void updateScreen(){
        for(int i = 0; i< img.length; i++){
            for (int j = 0; j < img[0].length; j++) {
                if(map[playerPositionX-12+i][playerPositionY-20+j].equals("grass")){
                    img[i][j].setImage(grass);
                }
                else if(map[playerPositionX-12+i][playerPositionY-20+j].equals("null")){
                    img[i][j].setImage(null); //steve
                }
            }
        }


    }


    public void onClick2(ActionEvent event) {
        map[playerPositionX][playerPositionY] = "grass";
        playerPositionX -=2;
        playerPositionY -= 5;
        map[playerPositionX][playerPositionY] = "null";
        updateScreen();
    }
}