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

    @FXML
    private ImageView arrowImg;

    private int x = 25;

    private int y = 41;

    ImageView[][] img = new ImageView[x][y];

    String[][] map = new String[x*8+1][y*8+1]; //100 //164

    private String directionInter = "right";


//    int changeX = 0;
//    int changeY = 0;

    int playerPositionX = 99;//-1
    int playerPositionY = 163;//-1

    int tempPlayerPositionX;//-1
    int tempPlayerPositionY;//-1

    //12,20----13,21

    FileInputStream grasss,playerr,playerOverGrasss, autumnTreee, fruitTreee, normalTreee,grassWXx,arroww;
    Image grass,player,playerOverGrass, autumnTree, fruitTree, normalTree,grassWX,arrow;

    public HelloController(){

        try {
            grasss = new FileInputStream("src/main/resources/grass.PNG");
            playerr = new FileInputStream("src/main/resources/player.png");
            playerOverGrasss = new FileInputStream("src/main/resources/playerOverGrass.jpg");
            autumnTreee = new FileInputStream("src/main/resources/Trees/grassAutumnTree.png");
            fruitTreee = new FileInputStream("src/main/resources/Trees/grassFruitTree.png");
            normalTreee = new FileInputStream("src/main/resources/Trees/grassTree.png");
            grassWXx = new FileInputStream("src/main/resources/grassWX.jpg");
            arroww = new FileInputStream("src/main/resources/arrow.png");

            grass = new Image(grasss);
            player = new Image(playerr);
            playerOverGrass = new Image(playerOverGrasss);
            autumnTree = new Image(autumnTreee);
            fruitTree = new Image(fruitTreee);
            normalTree = new Image(normalTreee);
            grassWX = new Image(grassWXx);
            arrow = new Image(arroww);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void onClick(){
        arrowImg.setImage(arrow);
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
                if(i ==0||i == map.length - 1||j ==0||j == map[0].length - 1){
                    map[i][j] = "grassWX";
                }
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
                else if(map[playerPositionX-12+i][playerPositionY-20+j].equals("grassWX")){
                    img[i][j].setImage(grassWX); //steve
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
        tempPlayerPositionX = playerPositionX;
        tempPlayerPositionY = playerPositionY;
//        img[12][20].setImage(normalTree);
//        img[12][18].setImage(fruitTree);
//        img[12][16].setImage(autumnTree);
    }

    private void updateScreenEdge(){
        System.out.println("hey");
        for(int i = 0; i< img.length; i++){
            for (int j = 0; j < img[0].length; j++) {
                if(map[tempPlayerPositionX-12+i][tempPlayerPositionY-20+j].equals("grass")){
                    img[i][j].setImage(grass);
                }
                else if(map[tempPlayerPositionX-12+i][tempPlayerPositionY-20+j].equals("grassWX")){
                    img[i][j].setImage(grassWX); //steve
                }
                else if(map[tempPlayerPositionX-12+i][tempPlayerPositionY-20+j].equals("playerOverGrass")){
                    img[i][j].setImage(playerOverGrass); //steve
                }
                else if(map[tempPlayerPositionX-12+i][tempPlayerPositionY-20+j].equals("normalTree")){
                    img[i][j].setImage(normalTree);
                }
                else if(map[tempPlayerPositionX-12+i][tempPlayerPositionY-20+j].equals("fruitTree")){
                    img[i][j].setImage(fruitTree);
                }
                else if(map[tempPlayerPositionX-12+i][tempPlayerPositionY-20+j].equals("autumnTree")){
                    img[i][j].setImage(autumnTree);
                }
                else if(map[tempPlayerPositionX-12+i][tempPlayerPositionY-20+j].equals("null")){
                    img[i][j].setImage(null);
                }
            }
        }

    }

//    public void onClick2() {
//        map[playerPositionX][playerPositionY] = "grass";
//        playerPositionX -=2;
//        playerPositionY -= 5;
//        map[playerPositionX][playerPositionY] = "playerOverGrass";
//        updateScreen();
//    }

    public void onKeyPressed(KeyEvent keyEvent) {
//        System.out.println(keyEvent.getText());
        if(keyEvent.getText().equalsIgnoreCase("w")){
            movePlayer("x",-1);
        }
        else if(keyEvent.getText().equalsIgnoreCase("a")){
            movePlayer("y",-1);
        }
        else if(keyEvent.getText().equalsIgnoreCase("s")){
            movePlayer("x",1);
        }
        else if(keyEvent.getText().equalsIgnoreCase("d")){
            movePlayer("y",1);
        }else if(keyEvent.getText().equalsIgnoreCase("e")){
            interact();
        }
        else if(keyEvent.getText().equalsIgnoreCase("i")){
            directionInter = "up";
            arrowImg.setImage(arrow);
            arrowImg.setRotate(270);
        }
        else if(keyEvent.getText().equalsIgnoreCase("j")){
            directionInter = "left";
            arrowImg.setImage(arrow);
            arrowImg.setRotate(180);
        }
        else if(keyEvent.getText().equalsIgnoreCase("k")){
            directionInter = "down";
            arrowImg.setImage(arrow);
            arrowImg.setRotate(90);
        }
        else if(keyEvent.getText().equalsIgnoreCase("l")){
            directionInter = "right";
            arrowImg.setImage(arrow);
            arrowImg.setRotate(0);
        }
        System.out.println("X: " + playerPositionX + " Y: " + playerPositionY);
        if(playerPositionY<20||playerPositionY>308||playerPositionX<12||playerPositionX>188){
            if (!(playerPositionY<20||playerPositionY>308)){
               tempPlayerPositionY = playerPositionY;
            }
            if(!(playerPositionX<12||playerPositionX>188)){
                tempPlayerPositionX = playerPositionX;
            }
            updateScreenEdge();
        }else {
            updateScreen();
        }
    }

    private void interact() {
        int directionChange = 0;
        if(directionInter.equals("up")){
            directionChange = -1;
        }else if(directionInter.equals("down")){
            directionChange = 1;
        }else if(directionInter.equals("right")){
            directionChange = 1;
        }else if(directionInter.equals("left")){
            directionChange = -1;
        }
        if(directionInter.equals("up") || directionInter.equals("down")){
            switch (map[playerPositionX + directionChange][playerPositionY]) {
                case "normalTree":
                    map[playerPositionX + directionChange][playerPositionY] = "grass";
                    break;
                case "fruitTree":
                    map[playerPositionX + directionChange][playerPositionY] = "grass";
                    break;
                case "autumnTree":
                    map[playerPositionX + directionChange][playerPositionY] = "grass";
                    break;
            }

        }else{
            switch (map[playerPositionX][playerPositionY + directionChange]) {
                case "normalTree":
                    map[playerPositionX ][playerPositionY+ directionChange] = "grass";
                    break;
                case "fruitTree":
                    map[playerPositionX ][playerPositionY+ directionChange] = "grass";
                    break;
                case "autumnTree":
                    map[playerPositionX][playerPositionY+ directionChange] = "grass";
                    break;
            }
        }



    }

    public void movePlayer(String dirStr,int dirNum){
        if(dirStr.equals("x")){
            if(map[playerPositionX+dirNum][playerPositionY].equals("grass")) {
                map[playerPositionX][playerPositionY] = "grass";
                playerPositionX += dirNum;
                map[playerPositionX][playerPositionY] = "playerOverGrass";
            }
        } else if (dirStr.equals("y")) {
            if(map[playerPositionX][playerPositionY+dirNum].equals("grass")) {
                map[playerPositionX][playerPositionY] = "grass";
                playerPositionY += dirNum;
                map[playerPositionX][playerPositionY] = "playerOverGrass";
            }
        }
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