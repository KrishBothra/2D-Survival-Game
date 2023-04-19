package com.example.template;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class HelloController {
    @FXML
    private Label lbl, coordsLabel;
    @FXML
    private GridPane gPane, inventoryPane;
    @FXML
    private AnchorPane anchor;

    @FXML
    private TextField txtField;

    @FXML
    private ImageView arrowImg;

    @FXML
    private ProgressBar miningBar;

    private int x = 25;

    private int y = 41;

    private long miningTime = System.nanoTime();

    ImageView[][] img = new ImageView[x][y];

    private ImageView[][] inventoryImg = new ImageView[6][12];

    String[][] map = new String[x * 8 + 1][y * 8 + 1]; //100 //164

    private ArrayList<String> biomeNameList = new ArrayList<>();
    private String directionInter = "right";
    private ArrayList<Biome> biomeArrayList = new ArrayList<>();
    private ArrayList<mineObjects> mineObjectsOnMap = new ArrayList<>();

    private Resources[] hotbar = new Resources[5];

    private boolean fruitQuest, normalQuest, autumnQuest, stoneQuest, waterQuest;


//    int changeX = 0;
//    int changeY = 0;

    int playerPositionX = 99;//-1
    int playerPositionY = 163;//-1

    boolean first = false;

    int tempPlayerPositionX;//-1
    int tempPlayerPositionY;//-1

    int miningX;
    int miningY;
    mineObjects tempMine = null;
    //12,20----13,21
    MediaPlayer mediaPlayer;
    Media sound;

    FileInputStream grasss, playerr, playerOverGrasss, playerOverStonee, autumnTreee, fruitTreee, normalTreee, grassWXx, arroww, stonee, rockk, diamondd, rubyy, goldd, waterr, chestWaterr, mailboxGrasss, mailboxStonee
            , grayBackk, blackBackk, yellowBackk;
    Image grass, player, playerOverGrass, playerOverStone, autumnTree, fruitTree, normalTree, grassWX, arrow, stone, rock, diamond, ruby, gold, water, chestWater, mailboxGrass, mailboxStone
            , grayBack, blackBack, yellowBack;
    private boolean miningObject = false;
    private int tempMineTime;

    public HelloController() {
        fruitQuest = false;
        normalQuest = false;
        autumnQuest = false;
        stoneQuest = false;
        waterQuest = false;

        try {
            grasss = new FileInputStream("src/main/resources/grass.PNG");
            playerr = new FileInputStream("src/main/resources/player.png");
            playerOverGrasss = new FileInputStream("src/main/resources/playerOverGrass.jpg");
            playerOverStonee = new FileInputStream("src/main/resources/playerOverStone.png");
            autumnTreee = new FileInputStream("src/main/resources/Trees/grassAutumnTree.png");
            fruitTreee = new FileInputStream("src/main/resources/Trees/grassFruitTree.png");
            normalTreee = new FileInputStream("src/main/resources/Trees/grassTree.png");
            grassWXx = new FileInputStream("src/main/resources/grassWX.jpg");
            arroww = new FileInputStream("src/main/resources/arrow.png");
            stonee = new FileInputStream("src/main/resources/stone.jpg");
            rockk = new FileInputStream("src/main/resources/rock.png");
            diamondd = new FileInputStream("src/main/resources/diamond.png");
            rubyy = new FileInputStream("src/main/resources/ruby.png");
            goldd = new FileInputStream("src/main/resources/gold.png");
            waterr = new FileInputStream("src/main/resources/water.jpg");
            chestWaterr = new FileInputStream("src/main/resources/chestWater.png");
            mailboxGrasss = new FileInputStream("src/main/resources/mailboxGrass.png");
            mailboxStonee = new FileInputStream("src/main/resources/mailboxStone.png");
            grayBackk= new FileInputStream("src/main/resources/grayBack.png");
            blackBackk= new FileInputStream("src/main/resources/blackBack.png");
            yellowBackk= new FileInputStream("src/main/resources/yellowBack.png");

//            sound = new Media(new File("src/main/resources/goofy2.mp3").toURI().toString());
//            mediaPlayer = new MediaPlayer(sound);


            grass = new Image(grasss);
            player = new Image(playerr);
            playerOverGrass = new Image(playerOverGrasss);
            playerOverStone = new Image(playerOverStonee);
            autumnTree = new Image(autumnTreee);
            fruitTree = new Image(fruitTreee);
            normalTree = new Image(normalTreee);
            grassWX = new Image(grassWXx);
            arrow = new Image(arroww);
            stone = new Image(stonee);
            rock = new Image(rockk);
            diamond = new Image(diamondd);
            ruby = new Image(rubyy);
            gold = new Image(goldd);
            water = new Image(waterr);
            chestWater = new Image(chestWaterr);
            mailboxGrass = new Image(mailboxGrasss);
            mailboxStone = new Image(mailboxStonee);
            grayBack = new Image(grayBackk);
            blackBack = new Image(blackBackk);
            yellowBack = new Image(yellowBackk);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onClick() {
//        mediaPlayer.play();
        biomeNameList.add("fruitTree");
        biomeNameList.add("normalTree");
        biomeNameList.add("autumnTree");
        biomeNameList.add("stone");
        biomeNameList.add("water");
        hotbar[0] = new Resources("diamond");

        arrowImg.setImage(arrow);
        gPane.setVisible(false);

        for (int i = 0; i < inventoryImg.length; i++) {
            for (int j = 0; j < inventoryImg[0].length; j++) {
                inventoryImg[i][j] = new ImageView();
                if(i==0||j==0||i==inventoryImg.length-1||j==inventoryImg[0].length-1){
                    inventoryImg[i][j].setImage(blackBack);
                }else{
                    inventoryImg[i][j].setImage(grayBack);
                }

                inventoryImg[i][j].setFitHeight(80);
                inventoryImg[i][j].setFitWidth(80);
                inventoryPane.add(inventoryImg[i][j], j, i);

            }
        }

        inventoryImg[1][2].setImage(blackBack);
        inventoryImg[2][2].setImage(blackBack);
        inventoryImg[3][2].setImage(blackBack);
        inventoryImg[4][2].setImage(blackBack);
        inventoryImg[1][8].setImage(blackBack);
        inventoryImg[2][8].setImage(blackBack);
        inventoryImg[3][8].setImage(blackBack);
        inventoryImg[4][8].setImage(blackBack);
        inventoryImg[1][9].setImage(blackBack);
        inventoryImg[1][10].setImage(blackBack);
        inventoryImg[4][9].setImage(blackBack);
        inventoryImg[4][10].setImage(blackBack);

        inventoryImg[4][3].setImage(yellowBack);
        inventoryImg[4][4].setImage(yellowBack);
        inventoryImg[4][5].setImage(yellowBack);
        inventoryImg[4][6].setImage(yellowBack);
        inventoryImg[4][7].setImage(yellowBack);




        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[0].length; j++) {
                img[i][j] = new ImageView();
                img[i][j].setImage(grass);
                img[i][j].setFitHeight(40);
                img[i][j].setFitWidth(40);
                gPane.add(img[i][j], j, i);
            }
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = "grass";
                if (i == 0 || i == map.length - 1 || j == 0 || j == map[0].length - 1) {
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
        start();


    }

    private void updateScreen() {
        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[0].length; j++) {
                if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("grass")) {
                    img[i][j].setImage(grass);
                } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("grassWX")) {
                    img[i][j].setImage(grassWX); //steve
                } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("playerOverGrass")) {
                    img[i][j].setImage(playerOverGrass); //steve
                } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("normalTree")) {
                    img[i][j].setImage(normalTree); //steve
                } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("fruitTree")) {
                    img[i][j].setImage(fruitTree); //steve
                } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("autumnTree")) {
                    img[i][j].setImage(autumnTree); //steve
                } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("stone")) {
                    img[i][j].setImage(stone);
                } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("playerOverStone")) {
                    img[i][j].setImage(playerOverStone);
                } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("rock")) {
                    img[i][j].setImage(rock);
                } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("ruby")) {
                    img[i][j].setImage(ruby);
                } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("diamond")) {
                    img[i][j].setImage(diamond);
                } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("gold")) {
                    img[i][j].setImage(gold);
                } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("water")) {
                    img[i][j].setImage(water);
                } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("fruitQuest")) {
                    img[i][j].setImage(mailboxGrass);
                } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("autumnQuest")) {
                    img[i][j].setImage(mailboxGrass);
                } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("normalQuest")) {
                    img[i][j].setImage(mailboxGrass);
                } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("stoneQuest")) {
                    img[i][j].setImage(mailboxStone);
                } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("waterQuest")) {
                    img[i][j].setImage(chestWater);
                } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("null")) {
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

    private void updateScreenEdge() {
//        System.out.println("hey");
        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[0].length; j++) {
                if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("grass")) {
                    img[i][j].setImage(grass);
                } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("grassWX")) {
                    img[i][j].setImage(grassWX); //steve
                } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("playerOverGrass")) {
                    img[i][j].setImage(playerOverGrass); //steve
                } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("normalTree")) {
                    img[i][j].setImage(normalTree);
                } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("fruitTree")) {
                    img[i][j].setImage(fruitTree);
                } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("autumnTree")) {
                    img[i][j].setImage(autumnTree);
                }  else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("stone")) {
                        img[i][j].setImage(stone);
                } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("playerOverStone")) {
                    img[i][j].setImage(playerOverStone);
                } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("rock")) {
                    img[i][j].setImage(rock);
                } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("gold")) {
                    img[i][j].setImage(gold);
                } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("ruby")) {
                    img[i][j].setImage(ruby);
                } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("diamond")) {
                    img[i][j].setImage(diamond);
                } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("water")) {
                    img[i][j].setImage(water);
                } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("null")) {
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
        coordsLabel.setText("X: " + playerPositionX + "\nY: " + playerPositionY);
        if(!miningObject) {
            if (keyEvent.getText().equalsIgnoreCase("w")) {
                movePlayer("x", -1);
            } else if (keyEvent.getText().equalsIgnoreCase("a")) {
                movePlayer("y", -1);
            } else if (keyEvent.getText().equalsIgnoreCase("s")) {
                movePlayer("x", 1);
            } else if (keyEvent.getText().equalsIgnoreCase("d")) {
                movePlayer("y", 1);
            } else if (keyEvent.getText().equalsIgnoreCase("e")) {
                interact();
            } else if (keyEvent.getText().equalsIgnoreCase("i")) {
                directionInter = "up";
                arrowImg.setImage(arrow);
                arrowImg.setRotate(270);
            } else if (keyEvent.getText().equalsIgnoreCase("j")) {
                directionInter = "left";
                arrowImg.setImage(arrow);
                arrowImg.setRotate(180);
            } else if (keyEvent.getText().equalsIgnoreCase("k")) {
                directionInter = "down";
                arrowImg.setImage(arrow);
                arrowImg.setRotate(90);
            } else if (keyEvent.getText().equalsIgnoreCase("l")) {
                directionInter = "right";
                arrowImg.setImage(arrow);
                arrowImg.setRotate(0);
            }
        }
        if (playerPositionY < 20 || playerPositionY > 308 || playerPositionX < 12 || playerPositionX > 188) {
            if (!(playerPositionY < 20 || playerPositionY > 308)) {
                tempPlayerPositionY = playerPositionY;
            }
            if (!(playerPositionX < 12 || playerPositionX > 188)) {
                tempPlayerPositionX = playerPositionX;
            }
            updateScreenEdge();
        } else {
            updateScreen();
        }
    }

    public void start() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(miningObject){
                    //1000000000.0
                    if(!first) {
                        miningBar.setVisible(true);
                        for (mineObjects m : mineObjectsOnMap) {
                            if (miningX == m.getX() && miningY == m.getY()) {
                                tempMine = m;
                                System.out.println(m.getX());
                                System.out.println(m.getY());
                            }
                        }
                        tempMineTime = tempMine.getMineTime();
                        System.out.println("MINE TIME "+tempMineTime);
                        miningBar.setProgress(1.0);
                        first = true;
                    }
                    if(now-miningTime>1000000000.0){
                        miningTime = System.nanoTime();
                        tempMineTime--;
                        miningBar.setProgress((double) tempMineTime/tempMine.getMineTime());
                        if(tempMineTime < 1)   {
                            if(tempMine.getName().equals("autumnTree")||tempMine.getName().equals("normalTree")||tempMine.getName().equals("fruitTree")) {
                                map[miningX][miningY] = "grass";
                            }else{
                                map[miningX][miningY] = "stone";
                            }
                            miningObject = false;
                            miningBar.setVisible(false);
                            first = false;
                            mineObjectsOnMap.remove(tempMine);
                            updateScreen();
                        }
                    }


                }
            }
        }.start();
    }


    private void interact() {
        int directionChange = switch (directionInter) {
            case "up" -> -1;
            case "down" -> 1;
            case "right" -> 1;
            case "left" -> -1;
            default -> 0;
        };
        if (directionInter.equals("up") || directionInter.equals("down")) {
            switch (map[playerPositionX + directionChange][playerPositionY]) {
                case "normalTree":
                case "fruitTree":
                case "autumnTree":
                    miningObject = true;
                    miningX = playerPositionX + directionChange;
                    miningY = playerPositionY;
//                    map[playerPositionX + directionChange][playerPositionY] = "grass";
                    break;
                case "grass", "stone":
                    map[playerPositionX + directionChange][playerPositionY] = hotbar[0].getName();
                    break;
            }

        } else {
            switch (map[playerPositionX][playerPositionY + directionChange]) {
                case "normalTree":
                case "fruitTree":
                case "autumnTree":
                case "rock":
                case "gold":
                case "ruby":
                case "diamond":

                    miningObject = true;
                    miningX = playerPositionX;
                    miningY = playerPositionY + directionChange;
//                    map[playerPositionX][playerPositionY + directionChange] = "grass";
                    break;
                case "grass", "stone":
                    map[playerPositionX][playerPositionY + directionChange] = hotbar[0].getName();
                    mineObjectsOnMap.add(new mineObjects("diamond", (int) (Math.random() * 5) + 20, new Resources("diamondR"), 1, playerPositionX, playerPositionY + directionChange));
                    break;
            }
        }


    }

    public void movePlayer(String dirStr, int dirNum) {
        if (dirStr.equals("x")) {
            if (map[playerPositionX + dirNum][playerPositionY].equals("grass")) {
                if(map[playerPositionX][playerPositionY].equals("playerOverGrass")){
                    map[playerPositionX][playerPositionY] = "grass";
                }else if(map[playerPositionX][playerPositionY].equals("playerOverStone")){
                    map[playerPositionX][playerPositionY] = "stone";
                }
                playerPositionX += dirNum;
                map[playerPositionX][playerPositionY] = "playerOverGrass";
            }else if(map[playerPositionX + dirNum][playerPositionY].equals("stone")){
                if(map[playerPositionX][playerPositionY].equals("playerOverGrass")){
                    map[playerPositionX][playerPositionY] = "grass";
                }else if(map[playerPositionX][playerPositionY].equals("playerOverStone")){
                    map[playerPositionX][playerPositionY] = "stone";
                }
                playerPositionX += dirNum;
                map[playerPositionX][playerPositionY] = "playerOverStone";
            }
        } else if (dirStr.equals("y")) {
            if (map[playerPositionX][playerPositionY + dirNum].equals("grass")) {
                if(map[playerPositionX][playerPositionY].equals("playerOverGrass")){
                    map[playerPositionX][playerPositionY] = "grass";
                }else if(map[playerPositionX][playerPositionY].equals("playerOverStone")){
                    map[playerPositionX][playerPositionY] = "stone";
                }
                playerPositionY += dirNum;
                map[playerPositionX][playerPositionY] = "playerOverGrass";
            }
            else if (map[playerPositionX][playerPositionY + dirNum].equals("stone")) {
                if(map[playerPositionX][playerPositionY].equals("playerOverGrass")){
                    map[playerPositionX][playerPositionY] = "grass";
                }else if(map[playerPositionX][playerPositionY].equals("playerOverStone")){
                    map[playerPositionX][playerPositionY] = "stone";
                }
                playerPositionY += dirNum;
                map[playerPositionX][playerPositionY] = "playerOverStone";
            }
        }
    }

    private void createBiomes() {
        int startX = 0;
        int startY = 0;
        int lengthX = 0;
        int lengthY = 0;
        int randNum;
        boolean valid;
        for (int p = 0; p < 5; p++) {


            if (biomeNameList.size() == 0) {
                biomeNameList.add("fruitTree");
                biomeNameList.add("normalTree");
                biomeNameList.add("autumnTree");
                biomeNameList.add("stone");
                biomeNameList.add("water");
            }

            randNum = (int) (Math.random() * biomeNameList.size());
            valid = false;
            while(!valid){
                valid = true;
                lengthX = (int) (Math.random() * 9) + 25;
                lengthY = (int) (Math.random() * 15) + 25;
                startX = (int) (Math.random() * (199 - lengthX)) + 1;
                startY = (int) (Math.random() * (327 - lengthY)) + 1;
                for (int i = startX; i < startX + lengthX; i++) {
                    for (int j = startY; j < startY + lengthY; j++) {
                        if(!Objects.equals(biomeNameList.get(randNum), "stone")){
                            if (map[i][j].equals("stone")||map[i][j].equals("water")) {
                                valid = false;
                                System.out.println("is stone");
                                break;
                            }
                        }else if(biomeNameList.get(randNum).equals("stone")){
                            if (!map[i][j].equals("stone")&&!map[i][j].equals("grass")){
                                valid = false;
                                System.out.println("not stone");
                                break;
                            }else if(startX>=96&&startX+lengthX<=105&&startY>=160&&startY+lengthY<=165){
                                valid = false;
                                System.out.println("not stone");
                            }
                        } else if(biomeNameList.get(randNum).equals("water")){
                            if (!map[i][j].equals("water")&&!map[i][j].equals("grass")){
                                valid = false;
                                System.out.println("not water");
                                break;
                            }else if(startX>=96&&startX+lengthX<=105&&startY>=160&&startY+lengthY<=165){
                                valid = false;
                                System.out.println("not stone");
                            }
                        }
                    }
                }
            }
            int x = (int)(Math.random()*(lengthX))+startX;
            int y = (int)(Math.random()*(lengthY))+startY;

            switch (biomeNameList.get(randNum)) {
                case "normalTree" -> {
                    System.out.println("normal");
                    biomeArrayList.add(new Biome(startX, startY, startX + lengthX, startY + lengthY, 0));
                    if(!normalQuest){
                        map[x][y] = "normalQuest";
                        normalQuest = true;
                    }
                    biomeNameList.remove("normalTree");
                    for (int i = startX; i < startX + lengthX; i++) {
                        for (int j = startY; j < startY + lengthY; j++) {
                            int random = (int) (Math.random() * 8);
                            if (random == 0) {
                                if (map[i][j].equals("grass")) {
                                    mineObjectsOnMap.add(new mineObjects("normalTree", (int) (Math.random() * 5) + 5, new Resources("normalWood"), (int) (Math.random() * 2) + 3, i, j));
                                    map[i][j] = "normalTree";
                                }
                            }
                        }
                    }
                }
                case "fruitTree" -> {
                    System.out.println("fruit");
                    biomeArrayList.add(new Biome(startX, startY, startX + lengthX, startY + lengthY, 0));
                    if(!fruitQuest){
                        map[x][y] = "fruitQuest";
                        fruitQuest = true;
                    }
                    biomeNameList.remove("fruitTree");
                    for (int i = startX; i < startX + lengthX; i++) {
                        for (int j = startY; j < startY + lengthY; j++) {
                            int random = (int) (Math.random() * 8);
                            if (random == 0) {
                                if (map[i][j].equals("grass")) {
                                    mineObjectsOnMap.add(new mineObjects("fruitTree", (int) (Math.random() * 5) + 5, new Resources("fruitWood"), (int) (Math.random() * 2) + 3,new Resources("apples"),(int) (Math.random() * 3) + 1, i, j));
                                    map[i][j] = "fruitTree";
                                }
                            }
                        }
                    }
                }
                case "autumnTree" -> {
                    System.out.println("autumn");
                    biomeArrayList.add(new Biome(startX, startY, startX + lengthX, startY + lengthY, 0));
                    if(!autumnQuest){
                        map[x][y] = "autumnQuest";
                        autumnQuest = true;
                    }
                    biomeNameList.remove("autumnTree");
                    for (int i = startX; i < startX + lengthX; i++) {
                        for (int j = startY; j < startY + lengthY; j++) {
                            int random = (int) (Math.random() * 8);
                            if (random == 0) {
                                if (map[i][j].equals("grass")) {
                                    map[i][j] = "autumnTree";
                                    mineObjectsOnMap.add(new mineObjects("autumntree", (int) (Math.random() * 5) + 5, new Resources("autumnWood"), (int) (Math.random() * 2) + 3, i, j));

                                }
                            }
                        }
                    }
                }
                case "stone" -> {
                    System.out.println("stone");
                    int mineralRand;
                    biomeArrayList.add(new Biome(startX, startY, startX + lengthX, startY + lengthY, 1));
                    biomeNameList.remove("stone");
                    if(!stoneQuest){
                        map[x][y] = "stoneQuest";
                        stoneQuest = true;
                    }
                    for (int i = startX; i < startX + lengthX; i++) {
                        for (int j = startY; j < startY + lengthY; j++) {
                            mineralRand = (int) (Math.random() * 50);
                            if (i == startX || j == startY || i == startX + lengthX - 1 || j == startY + lengthY - 1) {

                                if ((int) (Math.random() * 2) == 0) {
                                    map[i][j] = "stone";
                                }
                            } else {
                                if (map[i][j].equals("grass")) {
                                    if (mineralRand < 4) {
                                        map[i][j] = "rock";
                                        mineObjectsOnMap.add(new mineObjects("rock", (int) (Math.random() * 5) + 10, new Resources("cobblestone"), (int) (Math.random() * 3) + 1, i, j));

                                    } else if (mineralRand < 6) {
                                        map[i][j] = "gold";
                                        mineObjectsOnMap.add(new mineObjects("gold", (int) (Math.random() * 5) + 15, new Resources("goldR"), (int) (Math.random() * 2) + 1, i, j));

                                    } else if (mineralRand < 8) {
                                        map[i][j] = "diamond";
                                        mineObjectsOnMap.add(new mineObjects("diamond", (int) (Math.random() * 5) + 20, new Resources("diamondR"), 1, i, j));

                                    } else if (mineralRand < 11) {
                                        map[i][j] = "ruby";
                                        mineObjectsOnMap.add(new mineObjects("ruby", (int) (Math.random() * 5) + 15, new Resources("rubyR"), (int) (Math.random() * 2) + 1, i, j));

                                    } else {
                                        map[i][j] = "stone";
                                    }
                                }
                            }
                        }
                    }
                }
                case "water" -> {
                    System.out.println("water");
                    biomeArrayList.add(new Biome(startX, startY, startX + lengthX, startY + lengthY, 2));
                    biomeNameList.remove("water");
                    if(!waterQuest){
                        map[x][y] = "waterQuest";
                        waterQuest = true;
                    }
                    for (int i = startX; i < startX + lengthX; i++) {
                        for (int j = startY; j < startY + lengthY; j++) {
                            if (i == startX || j == startY || i == startX + lengthX - 1 || j == startY + lengthY - 1) {
                                if ((int) (Math.random() * 2) == 0) {
                                    map[i][j] = "water";
                                }
                            } else {
                                if (map[i][j].equals("grass")) {
                                    map[i][j] = "water";
                                }
                            }
                        }
                    }
                }
            }
        }

        for (Biome biome : biomeArrayList) {
            System.out.println(biome.getSx() + " " + biome.getSy() + " " + biome.getEx() + " " + biome.getEy());
        }
    }
}