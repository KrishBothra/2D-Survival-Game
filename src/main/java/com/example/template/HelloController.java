package com.example.template;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HelloController {
    @FXML
    private Label lbl;
    @FXML
    private GridPane gPane;
    @FXML
    private AnchorPane anchor;

    @FXML
    private TextField txtField;

    private int x = 25;

    private int y = 41;

    ImageView[][] img = new ImageView[x][y];

    String[][] map = new String[x*8+1][y*8+1]; //100 //164


//    int changeX = 0;
//    int changeY = 0;

    int playerPositionX = 99;//-1
    int playerPositionY = 163;//-1

    //12,20----13,21

    FileInputStream grasss,playerr,playerOverGrasss, autumnTreee, fruitTreee, normalTreee;
    Image grass,player,playerOverGrass, autumnTree, fruitTree, normalTree;

    public HelloController(){

        try {
            grasss = new FileInputStream("src/main/resources/grass.PNG");
            playerr = new FileInputStream("src/main/resources/player.png");
            playerOverGrasss = new FileInputStream("src/main/resources/playerOverGrass.jpg");
            autumnTreee = new FileInputStream("src/main/resources/Trees/grassAutumnTree.png");
            fruitTreee = new FileInputStream("src/main/resources/Trees/grassFruitTree.png");
            normalTreee = new FileInputStream("src/main/resources/Trees/grassTree.png");

            grass = new Image(grasss);
            player = new Image(playerr);
            playerOverGrass = new Image(playerOverGrasss);
            autumnTree = new Image(autumnTreee);
            fruitTree = new Image(fruitTreee);
            normalTree = new Image(normalTreee);
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
        map[99][163] = "playerOverGrass";
//        map[30][160] = "normalTree";
        createBiomes();


        gPane.setGridLinesVisible(true);

        updateScreen();





    }

    private void updateScreen(){
        for(int i = 0; i< img.length; i++){
            for (int j = 0; j < img[0].length; j++) {
                if(map[playerPositionX-12+i][playerPositionY-20+j].equals("grass")){
                    img[i][j].setImage(grass);
                }
                else if(map[playerPositionX-12+i][playerPositionY-20+j].equals("playerOverGrass")){
                    img[i][j].setImage(playerOverGrass); //steve
                }
                else if(map[playerPositionX-12+i][playerPositionY-20+j].equals("normalTree")){
                    img[i][j].setImage(normalTree); //steve
                }
                else if(map[playerPositionX-12+i][playerPositionY-20+j].equals("fruitTree")){
                    img[i][j].setImage(fruitTree); //steve
                }
                else if(map[playerPositionX-12+i][playerPositionY-20+j].equals("autumnTree")){
                    img[i][j].setImage(autumnTree); //steve
                }
                else if(map[playerPositionX-12+i][playerPositionY-20+j].equals("null")){
                    img[i][j].setImage(null); //steve
                }
            }
        }
//        img[12][20].setImage(normalTree);
//        img[12][18].setImage(fruitTree);
//        img[12][16].setImage(autumnTree);


    }


    public void onClick2() {
        map[playerPositionX][playerPositionY] = "grass";
        playerPositionX -=2;
        playerPositionY -= 5;
        map[playerPositionX][playerPositionY] = "playerOverGrass";
        updateScreen();
    }

    public void onKeyPressed(KeyEvent keyEvent) {
//        System.out.println(keyEvent.getText());
        if(keyEvent.getText().equalsIgnoreCase("w")){
            map[playerPositionX][playerPositionY] = "grass";
            playerPositionX -=1;
            map[playerPositionX][playerPositionY] = "playerOverGrass";
        }
        else if(keyEvent.getText().equalsIgnoreCase("a")){
            map[playerPositionX][playerPositionY] = "grass";
            playerPositionY -= 1;
            map[playerPositionX][playerPositionY] = "playerOverGrass";
        }
        else if(keyEvent.getText().equalsIgnoreCase("s")){
            map[playerPositionX][playerPositionY] = "grass";
            playerPositionX +=1;
            map[playerPositionX][playerPositionY] = "playerOverGrass";
        }
        else if(keyEvent.getText().equalsIgnoreCase("d")){
            map[playerPositionX][playerPositionY] = "grass";
            playerPositionY += 1;
            map[playerPositionX][playerPositionY] = "playerOverGrass";
        }
        updateScreen();
        System.out.println("X: " + playerPositionX + " Y: " + playerPositionY);
    }


    private void createBiomes(){
        for (int i = 10; i < 40; i++) {
            for (int j = 20; j < 50; j++) {
                int random = (int)(Math.random()*8);
                if(random == 0){
                    map[i][j] = "normalTree";
                }
            }
        }
        for (int i = 60; i < 90; i++) {
            for (int j = 70; j < 120; j++) {
                int random = (int)(Math.random()*8);
                if(random == 0){
                    map[i][j] = "fruitTree";
                }
            }
        }
        for (int i = 10; i < 40; i++) {
            for (int j = 70; j < 120; j++) {
                int random = (int)(Math.random()*8);
                if(random == 0){
                    map[i][j] = "autumnTree";
                }
            }
        }

    }
}