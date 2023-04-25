package com.example.template;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;

public class HelloController {
    @FXML
    private Label lbl, coordsLabel;
    @FXML
    private GridPane gPane, inventoryPane, hotbarG;
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

    
    private ImageView[][] hotbarImg = new ImageView[5][1];

    String[][] map = new String[x * 8 + 1][y * 8 + 1]; //100 //164

    private ArrayList<String> biomeNameList = new ArrayList<>();
    private String directionInter = "right";
    private ArrayList<Biome> biomeArrayList = new ArrayList<>();
    private ArrayList<mineObjects> mineObjectsOnMap = new ArrayList<>();

    private inventoryItems[] hotbar = new inventoryItems[5];
    private inventoryItems[][] inventoryA = new inventoryItems[6][12];

    private inventoryItems inventorySelected = new inventoryItems("empty");
    private ImageView[][] inventoryImg = new ImageView[6][12];
    
    private int invSelectedRow = -1;
    private int invSelectedCol = -1;

    private inventoryItems equipped;
    private int selected = 0;

    private Label[][] inventoryLabels = new Label[4][5];

    @FXML
    private Label one1, one2, one3, one4, one5, two1, two2, two3, two4, two5, three1, three2, three3, three4, three5, four1, four2, four3, four4, four5, one1c, one2c, two1c, two2c, result;

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
//    MediaPlayer mediaPlayer;
//    Media sound;

    FileInputStream grasss, playerr, playerOverGrasss, playerOverStonee, autumnTreee, fruitTreee, normalTreee, grassWXx, arroww, stonee, rockk, diamondOree, rubyOree, goldOree, waterr, chestWaterr, mailboxGrasss, mailboxStonee
            , grayBackk, blackBackk, yellowBackk, rubyInvv,goldIngotInvv,diamondInvv, normalWoodd,normalWooddInv,autumnWooddInv,fruitWooddInv,appleeInv,cobblestoneInvv,woodAxeInvv;
    Image grass, player, playerOverGrass, playerOverStone, autumnTree, fruitTree, normalTree, grassWX, arrow, stone, rock, diamondOre, rubyOre, goldOre, water, chestWater, mailboxGrass, mailboxStone
            , grayBack, blackBack, yellowBack, rubyInv,goldIngotInv,diamondInv, normalWood,normalWoodInv,autumnWoodInv,fruitWoodInv,appleInv,cobbelstoneInv,woodAxeInv;
    private boolean miningObject = false;
    private int tempMineTime;
    private boolean inventoryShowing = false;
    private boolean breakB = false;
    private boolean clickedS = false;
    int amountChange = 0;
    private boolean clickedP  = false;
    private double toolBoost =1;

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
            diamondOree = new FileInputStream("src/main/resources/diamondOre.jpg");
            rubyOree = new FileInputStream("src/main/resources/rubyOre.png");
            goldOree = new FileInputStream("src/main/resources/goldOre.jpg");
            waterr = new FileInputStream("src/main/resources/water.jpg");
            chestWaterr = new FileInputStream("src/main/resources/chestWater.png");
            mailboxGrasss = new FileInputStream("src/main/resources/mailboxGrass.png");
            mailboxStonee = new FileInputStream("src/main/resources/mailboxStone.png");
            grayBackk= new FileInputStream("src/main/resources/grayBack.png");
            blackBackk= new FileInputStream("src/main/resources/blackBack.png");
            yellowBackk= new FileInputStream("src/main/resources/yellowBack.png");
            rubyInvv = new FileInputStream("src/main/resources/rubyInv.png");
            goldIngotInvv = new FileInputStream("src/main/resources/InventoryItems/goldIngot.png");
            diamondInvv = new FileInputStream("src/main/resources/InventoryItems/diamond.png");
            normalWoodd = new FileInputStream("src/main/resources/normalWood.png");
            normalWooddInv = new FileInputStream("src/main/resources/InventoryItems/normalWood.png");
            fruitWooddInv = new FileInputStream("src/main/resources/InventoryItems/jungleWood.png");
            autumnWooddInv = new FileInputStream("src/main/resources/InventoryItems/acaciaWood.png");
            appleeInv = new FileInputStream("src/main/resources/InventoryItems/apple.png");
            cobblestoneInvv = new FileInputStream("src/main/resources/InventoryItems/cobblestone.png");
            woodAxeInvv = new FileInputStream("src/main/resources/InventoryItems/woodAxe.png");


//            sound = new Media(new File("src/main/resources/goofy2.mp3").toURI().toString());
//            mediaPlayer = new MediaPlayer(sound);
            woodAxeInv = new Image(woodAxeInvv);
            cobbelstoneInv = new Image(cobblestoneInvv);
            normalWood = new Image(normalWoodd);
            normalWoodInv = new Image(normalWooddInv);
            fruitWoodInv = new Image(fruitWooddInv);
            autumnWoodInv = new Image(autumnWooddInv);
            appleInv = new Image(appleeInv);
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
            diamondOre = new Image(diamondOree);
            rubyOre = new Image(rubyOree);
            goldOre = new Image(goldOree);
            water = new Image(waterr);
            chestWater = new Image(chestWaterr);
            mailboxGrass = new Image(mailboxGrasss);
            mailboxStone = new Image(mailboxStonee);
            grayBack = new Image(grayBackk);
            blackBack = new Image(blackBackk);
            yellowBack = new Image(yellowBackk);
            rubyInv = new Image(rubyInvv);
            diamondInv = new Image(diamondInvv  );
            goldIngotInv = new Image(goldIngotInvv);

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

        inventoryLabels[0][0] = one1;
        inventoryLabels[0][1] = one2;
        inventoryLabels[0][2] = one3;
        inventoryLabels[0][3] = one4;
        inventoryLabels[0][4] = one5;
        inventoryLabels[1][0] = two1;
        inventoryLabels[1][1] = two2;
        inventoryLabels[1][2] = two3;
        inventoryLabels[1][3] = two4;
        inventoryLabels[1][4] = two5;
        inventoryLabels[2][0] = three1;
        inventoryLabels[2][1] = three2;
        inventoryLabels[2][2] = three3;
        inventoryLabels[2][3] = three4;
        inventoryLabels[2][4] = three5;
        inventoryLabels[3][0] = four1;
        inventoryLabels[3][1] = four2;
        inventoryLabels[3][2] = four3;
        inventoryLabels[3][3] = four4;
        inventoryLabels[3][4] = four5;
        for (int i = 0; i<inventoryLabels.length; i++) {
            for (int j = 0; j < inventoryLabels[0].length; j++) {
                inventoryLabels[i][j].setVisible(false);
            }
        }



        gPane.setVisible(true);
        inventoryPane.setVisible(false);
        for (int i = 0; i < inventoryImg.length; i++) {

            for (int j = 0; j < inventoryImg[0].length; j++) {
                inventoryImg[i][j] = new ImageView();
                if(i==0||j==0||i==inventoryImg.length-1||j==inventoryImg[0].length-1){
                    inventoryImg[i][j].setImage(blackBack);
                }else{
                    inventoryImg[i][j].setImage(grayBack);
                }

                inventoryImg[i][j].setFitHeight(158);
                inventoryImg[i][j].setFitWidth(158);
                inventoryPane.add(inventoryImg[i][j], j, i);

            }
        }

        EventHandler z = new EventHandler<MouseEvent>()
        {

            @Override
            public void handle(MouseEvent t)
            {
                int row = GridPane.getRowIndex(((ImageView) t.getSource()));
                int col = GridPane.getColumnIndex(((ImageView) t.getSource()));
                if(t.getButton().equals(MouseButton.PRIMARY)&&!clickedS) {
                    if (invSelectedCol != -1 && invSelectedRow != -1) {
                        System.out.println("hey");
                        if (inventoryA[row][col].getName().equals("empty")) {
                            System.out.println("hey2");
                            inventoryA[row][col] = inventoryA[invSelectedRow][invSelectedCol];
                            inventoryA[invSelectedRow][invSelectedCol] = new inventoryItems("empty");
                            invSelectedCol = -1;
                            invSelectedRow = -1;
                            updateScreen();
                            clickedP = false;
                        } else if (inventoryA[row][col].getName().equals(inventoryA[invSelectedRow][invSelectedCol].getName())) {
                            System.out.println("hey5");
                            inventoryA[row][col].changeAmount(inventoryA[invSelectedRow][invSelectedCol].getAmount());
                            inventoryA[invSelectedRow][invSelectedCol] = new inventoryItems("empty");
                            invSelectedCol = -1;
                            invSelectedRow = -1;
                            updateScreen();
                        }
                    } else if (!inventoryA[row][col].getName().equals("empty")) {
                        System.out.println("hey3");
                        invSelectedRow = row;
                        invSelectedCol = col;
                        clickedP = true;
                        System.out.println(inventoryA[row][col].getAmount());
                    }
                }else if(t.getButton().equals(MouseButton.SECONDARY)&&!clickedP){
                    if (invSelectedCol != -1 && invSelectedRow != -1) {
                        System.out.println("hey");
                        if (inventoryA[row][col].getName().equals("empty")) {
                            System.out.println("hey2");
                            inventoryA[row][col] = new inventoryItems(inventoryA[invSelectedRow][invSelectedCol].getName(),amountChange);
                            inventoryA[invSelectedRow][invSelectedCol].changeAmount(-(amountChange));
                            invSelectedCol = -1;
                            invSelectedRow = -1;
                            updateScreen();
                            clickedS = false;
                        }
                    } else if (!inventoryA[row][col].getName().equals("empty")) {
                        if(inventoryA[row][col].getAmount()>1){
                            amountChange = inventoryA[row][col].getAmount()/2;
                            System.out.println("hey3");
                            invSelectedRow = row;
                            invSelectedCol = col;
                            clickedS = true;
                        }

                    }
                }

                

            }
        };

        for(int i=0; i<inventoryImg.length; i++){
            for(int j=0; j<inventoryImg[0].length;j++){
                inventoryImg[i][j].setOnMouseClicked(z);
            }
        }

        for (int i = 0; i < inventoryImg.length; i++) {
            for (int j = 0; j < inventoryImg[0].length; j++) {
                inventoryImg[i][j].setImage(blackBack);

            }
        }

        inventoryImg[1][1].setImage(grayBack);
        inventoryImg[2][1].setImage(grayBack);
        inventoryImg[3][1].setImage(grayBack);
        inventoryImg[4][1].setImage(grayBack);

        inventoryImg[1][9].setImage(grayBack);
        inventoryImg[2][9].setImage(grayBack);
        inventoryImg[1][10].setImage(grayBack);
        inventoryImg[2][10].setImage(grayBack);


        inventoryImg[4][9].setImage(grayBack);

        for (int i = 1; i <=4 ; i++) {
            for (int j = 3; j <=7 ; j++) {
                inventoryImg[i][j].setImage(grayBack);
            }
        }


//        inventoryImg[1][3].setImage(rubyInv);
//        inventoryA[1][3] = new inventoryItems("ruby",1);

        for (int i = 0; i < 5; i++) {
            hotbarImg[i][0] = new ImageView();
            hotbarImg[i][0].setImage(grayBack);
            hotbarImg[i][0].setFitHeight(170);
            hotbarImg[i][0].setFitWidth(170);
            hotbarG.add(hotbarImg[i][0],0,i);
        }
        arrowImg.setImage(arrow);

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
        for (int i = 0; i < hotbar.length; i++) {
            hotbar[i] = new inventoryItems("empty");
        }

        for (int i = 0; i < inventoryA.length; i++) {
            for (int j = 0; j < inventoryA[0].length; j++) {
                inventoryA[i][j] = new inventoryItems("empty");
            }

        }

        updateScreen();
        start();
        //for the change

        inventoryA[4][3] = new Tools("woodAxe",1,"axe");
    }



    private void updateScreen() {
        if (playerPositionY < 20 || playerPositionY > 308 || playerPositionX < 12 || playerPositionX > 188) {
            if (!(playerPositionY < 20 || playerPositionY > 308)) {
                tempPlayerPositionY = playerPositionY;
            }
            if (!(playerPositionX < 12 || playerPositionX > 188)) {
                tempPlayerPositionX = playerPositionX;
            }
            //UPDATE SCREEN EDGE
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
                    } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("goldOre")) {
                        img[i][j].setImage(goldOre);
                    } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("rubyOre")) {
                        img[i][j].setImage(rubyOre);
                    } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("diamondOre")) {
                        img[i][j].setImage(diamondOre);
                    } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("water")) {
                        img[i][j].setImage(water);
                    } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("null")) {
                        img[i][j].setImage(null);
                    } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("normalWood")) {
                        img[i][j].setImage(normalWood);
                    }
                }
            }
        } else {
            //UPDATE SCREEN
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
                    } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("rubyOre")) {
                        img[i][j].setImage(rubyOre);
                    } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("diamondOre")) {
                        img[i][j].setImage(diamondOre);
                    } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("goldOre")) {
                        img[i][j].setImage(goldOre);
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
                    }else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("normalWood")) {
                        img[i][j].setImage(normalWood); //steve
                    }
                }
            }
            tempPlayerPositionX = playerPositionX;
            tempPlayerPositionY = playerPositionY;
            for (int i = 0; i<inventoryLabels.length; i++) {
                for (int j = 0; j < inventoryLabels[0].length; j++) {
                    if(inventoryA[i+1][j+3].getAmount()!=0&&inventoryA[i+1][j+3].getAmount()!=1){
                        inventoryLabels[i][j].setText(Integer.toString(inventoryA[i+1][j+3].getAmount()));
                        one1c.setText(Integer.toString(inventoryA[1][9].getAmount()));
                        one2c.setText(Integer.toString(inventoryA[1][10].getAmount()));
                        two1c.setText(Integer.toString(inventoryA[2][9].getAmount()));
                        one2c.setText(Integer.toString(inventoryA[2][10].getAmount()));
                        result.setText(Integer.toString(inventoryA[4][9].getAmount()));
                    }else{
                        inventoryLabels[i][j].setText("");
                        one1c.setText("");
                        one2c.setText("");
                        two1c.setText("");
                        two2c.setText("");
                        result.setText("");
                    }
                }
            }
        }
        System.out.println(inventoryA[4][selected].getName());
        for (int i = 3; i < 8; i++) {
            hotbar[i-3]=inventoryA[4][i];
        }

        for (int i = 0; i < hotbar.length; i++) {
            if(hotbar[i].getName().equals("normalWood")){
                hotbarImg[i][0].setImage(normalWoodInv);
            }else if(hotbar[i].getName().equals("autumnWood")){
                hotbarImg[i][0].setImage(autumnWoodInv);
            }else if(hotbar[i].getName().equals("fruitWood")){
                hotbarImg[i][0].setImage(fruitWoodInv);
            } else if(hotbar[i].getName().equals("apples")){
                hotbarImg[i][0].setImage(appleInv);
            } else if(hotbar[i].getName().equals("diamond")){
                hotbarImg[i][0].setImage(diamondInv);
            } else if(hotbar[i].getName().equals("goldIngot")){
                hotbarImg[i][0].setImage(goldIngotInv);
            } else if(hotbar[i].getName().equals("ruby")){
                hotbarImg[i][0].setImage(rubyInv);
            }else if(hotbar[i].getName().equals("cobblestone")){
                hotbarImg[i][0].setImage(cobbelstoneInv);
            }else if(hotbar[i].getName().equals("woodAxe")){
                hotbarImg[i][0].setImage(woodAxeInv);
            }else if(hotbar[i].getName().equals("empty")){
                hotbarImg[i][0].setImage(grayBack);
            }
        }

        for (int i = 1; i <=4; i++) {
            for (int j = 3; j <=7; j++) {
                if(inventoryA[i][j].getName().equals("normalWood")){
                    inventoryImg[i][j].setImage(normalWoodInv);
                }else if(inventoryA[i][j].getName().equals("autumnWood")){
                    inventoryImg[i][j].setImage(autumnWoodInv);
                }else if(inventoryA[i][j].getName().equals("fruitWood")){
                    inventoryImg[i][j].setImage(fruitWoodInv);
                } else if(inventoryA[i][j].getName().equals("apples")){
                    inventoryImg[i][j].setImage(appleInv);
                } else if(inventoryA[i][j].getName().equals("diamond")){
                    inventoryImg[i][j].setImage(diamondInv);
                } else if(inventoryA[i][j].getName().equals("goldIngot")){
                    inventoryImg[i][j].setImage(goldIngotInv);
                } else if(inventoryA[i][j].getName().equals("ruby")){
                    inventoryImg[i][j].setImage(rubyInv);
                }else if(inventoryA[i][j].getName().equals("cobblestone")){
                    inventoryImg[i][j].setImage(cobbelstoneInv);
                }else if(inventoryA[i][j].getName().equals("woodAxe")){
                    inventoryImg[i][j].setImage(woodAxeInv);
                }else if(inventoryA[i][j].getName().equals("empty")){
                    inventoryImg[i][j].setImage(grayBack);
                }
            }
        }
        System.out.println(hotbar[selected].getName());
        equipped = hotbar[selected];
        System.out.println(equipped.getName());
        checkCrafts();
        //System.out.println(equipped.getName());
    }

//    public void onClick2() {
//        map[playerPositionX][playerPositionY] = "grass";
//        playerPositionX -=2;
//        playerPositionY -= 5;
//        map[playerPositionX][playerPositionY] = "playerOverGrass";
//        updateScreen();
//    }

    private void checkCrafts(){
        inventoryItems tl = inventoryA[1][9];
        inventoryItems tr = inventoryA[1][10];
        inventoryItems bl = inventoryA[2][9];
        inventoryItems br = inventoryA[2][10];
        if(tl.getName().equals("normalWood")&&tr.getName().equals("empty")&&bl.getName().equals("empty")||br.getName().equals("empty")){

        }
    }

    public void onKeyPressed(KeyEvent keyEvent) {
        coordsLabel.setText("X: " + playerPositionX + "\nY: " + playerPositionY);
        if(!miningObject) {
            if(!inventoryShowing) {
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
            if (keyEvent.getText().equalsIgnoreCase("q")) {
                if(inventoryShowing){
                    gPane.setVisible(true);
                    hotbarG.setVisible(true);
                    inventoryPane.setVisible(false);
                    inventoryShowing = false;
                    for (int i = 0; i<inventoryLabels.length; i++) {
                        for (int j = 0; j < inventoryLabels[0].length; j++) {
                            inventoryLabels[i][j].setVisible(false);
                            one1c.setVisible(false);
                            one2c.setVisible(false);
                            two1c.setVisible(false);
                            two2c.setVisible(false);
                            result.setVisible(false);
                        }
                    }
                }else{
                    gPane.setVisible(false);
                    hotbarG.setVisible(false);
                    inventoryPane.setVisible(true);
                    inventoryShowing = true;
                    for (int i = 0; i<inventoryLabels.length; i++) {
                        for (int j = 0; j < inventoryLabels[0].length; j++) {
                            inventoryLabels[i][j].setVisible(true);
                            one1c.setVisible(true);
                            one2c.setVisible(true);
                            two1c.setVisible(true);
                            two2c.setVisible(true);
                            result.setVisible(true);
                        }
                    }
                }
            }
            if (keyEvent.getText().equalsIgnoreCase("1")) {
                selected = 0;
            } else if (keyEvent.getText().equalsIgnoreCase("2")) {
                selected = 1;
            } else if (keyEvent.getText().equalsIgnoreCase("3")) {
                selected = 2;
            } else if (keyEvent.getText().equalsIgnoreCase("4")) {
                selected = 3;
            } else if (keyEvent.getText().equalsIgnoreCase("5")) {
                selected = 4;
            }

        }
        updateScreen();

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
                        System.out.println(equipped.getName());
                        System.out.println(equipped.getTier());
                        if(equipped.getTier()>0){
                            System.out.println(tempMine.getType());
                            if(tempMine.getType().equals(equipped.getType())){
                                toolBoost = equipped.getTier() +(equipped.getTier()*0.6);
                                System.out.println(toolBoost);
                            }
                        }
                    }
                    if(now-miningTime>1000000000.0/toolBoost){
                        miningTime = System.nanoTime();
                        tempMineTime--;
                        miningBar.setProgress((double) tempMineTime/tempMine.getMineTime());
                        if(tempMineTime < 1)   {
                            if(tempMine.getName().equals("autumnTree")||tempMine.getName().equals("normalTree")||tempMine.getName().equals("fruitTree")) {
                                map[miningX][miningY] = "grass";
                            }else{
                                System.out.println(tempMine.getName());
                                map[miningX][miningY] = "stone";
                            }
                            miningObject = false;
                            miningBar.setVisible(false);
                            first = false;
                            mineObjectsOnMap.remove(tempMine);
                            for (int i = 4; i >=1; i--) {
                                for (int j = 3; j <=7; j++) {
                                    if(inventoryA[i][j].getName().equals(tempMine.getResourceDrop().getName())){
                                        System.out.println("hi");
                                        inventoryA[i][j].changeAmount(tempMine.getAmountDrop());
                                        if(tempMine.getAmountDropSecond()!= 0){
                                            for (int m = 4; m >=1; m--) {
                                                for (int k = 3; k <=7; k++) {
                                                    if(inventoryA[m][k].getName().equals(tempMine.getResourceDropSecond().getName())){
                                                        System.out.println("hey");
                                                        inventoryA[m][k].changeAmount(tempMine.getAmountDropSecond());
                                                        breakB = true;
                                                        break;
                                                    }
                                                }



                                                if(breakB){
                                                    break;
                                                }
                                            }

                                            for (int m = 4; m >=1; m--) {
                                                if(breakB){
                                                    break;
                                                }
                                                for (int k = 3; k <=7; k++) {
                                                    if(inventoryA[m][k].getName().equals("empty")){
                                                        inventoryA[m][k] = tempMine.getResourceDropSecond();
                                                        inventoryA[m][k].setAmount(tempMine.getAmountDropSecond());
                                                        breakB = true;
                                                        break;
                                                    }
                                                }

                                                if(breakB){
                                                    break;
                                                }
                                            }
                                        }
                                        breakB = true;
                                        break;
                                    }
                                }
                                if(breakB){
                                    break;
                                }
                            }




                            for (int i = 4; i >=1; i--) {
                                if(breakB){
                                    breakB = false;
                                    break;
                                }
                                for (int j = 3; j <=7; j++) {
                                    if(inventoryA[i][j].getName().equals("empty")){
                                        inventoryA[i][j] = tempMine.getResourceDrop();
                                        inventoryA[i][j].setAmount(tempMine.getAmountDrop());
                                        if(tempMine.getAmountDropSecond()!= 0){
                                            for (int m = 4; m >=1; m--) {
                                                for (int k = 3; k <=7; k++) {
                                                    if(inventoryA[m][k].getName().equals(tempMine.getResourceDropSecond().getName())){
                                                        System.out.println("hi");
                                                        inventoryA[m][k].changeAmount(tempMine.getAmountDropSecond());
                                                        breakB = true;
                                                        break;
                                                    }
                                                }
                                                if(breakB){
                                                    break;
                                                }
                                            }


                                            for (int m = 4; m >=1; m--) {
                                                if(breakB){
                                                    break;
                                                }
                                                for (int k = 3; k <=7; k++) {
                                                    if(inventoryA[m][k].getName().equals("empty")){
                                                        inventoryA[m][k] = tempMine.getResourceDropSecond();
                                                        inventoryA[m][k].setAmount(tempMine.getAmountDropSecond());
                                                        breakB = true;
                                                        break;
                                                    }
                                                }
                                                if(breakB){
                                                    break;
                                                }
                                            }
                                        }
                                        breakB = true;
                                        break;
                                    }
                                }
                                if(breakB){
                                    breakB = false;
                                    break;
                                }
                            }
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
                case "rock":
                case "goldOre":
                case "rubyOre":
                case "diamondOre":
                case "normalWood":
                    miningObject = true;
                    miningX = playerPositionX + directionChange;
                    miningY = playerPositionY;
//                    map[playerPositionX + directionChange][playerPositionY] = "grass";
                    break;
                case "grass", "stone":
                    if(!equipped.getName().equals("empty")){
                        int mineTime;
                        if(equipped.getType().equals("pickaxe")){
                            mineTime = (int) (Math.random() * 5) + 15;
                        }else{
                            mineTime = (int) (Math.random() * 5) + 8;
                        }

                        map[playerPositionX+ directionChange][playerPositionY ] = equipped.getName();
                        mineObjectsOnMap.add(new mineObjects(equipped.getName(),equipped.getType(), mineTime, new Resources(equipped.getName()), 1, playerPositionX+ directionChange, playerPositionY ));
                        if(equipped.getAmount()>1){
                            equipped.changeAmount(-1);
                        }else{
                            inventoryA[4][selected+3] = new inventoryItems("empty");
                            System.out.println(inventoryA[4][selected+3].getName());
                            updateScreen();
                            System.out.println(hotbar[selected].getName());
                            System.out.println(equipped.getName());
                        }
                    }
                    break;
            }

        } else {
            switch (map[playerPositionX][playerPositionY + directionChange]) {
                case "normalTree":
                case "fruitTree":
                case "autumnTree":
                case "rock":
                case "goldOre":
                case "rubyOre":
                case "diamondOre":
                case "normalWood":


                    miningObject = true;
                    miningX = playerPositionX;
                    miningY = playerPositionY + directionChange;
//                    map[playerPositionX][playerPositionY + directionChange] = "grass";
                    break;
                case "grass", "stone":
                    if(!equipped.getName().equals("empty")){
                        int mineTime;
                        if(equipped.getType().equals("pickaxe")){
                            mineTime = (int) (Math.random() * 5) + 15;
                        }else{
                            mineTime = (int) (Math.random() * 5) + 8;
                        }

                        map[playerPositionX][playerPositionY + directionChange] = equipped.getName();
                        mineObjectsOnMap.add(new mineObjects(equipped.getName(),equipped.getType(), mineTime, new Resources(equipped.getName()), 1, playerPositionX, playerPositionY + directionChange));
                        if(equipped.getAmount()>1){
                            equipped.changeAmount(-1);
                        }else{
                            inventoryA[4][selected+3] = new inventoryItems("empty");
                            System.out.println(inventoryA[4][selected+3].getName());
                            updateScreen();
                            System.out.println(hotbar[selected].getName());
                            System.out.println(equipped.getName());
                        }
                    }
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
                                    mineObjectsOnMap.add(new mineObjects("normalTree","axe", (int) (Math.random() * 5) + 5, new Resources("normalWood","axe"), (int) (Math.random() * 2) + 3, i, j));
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
                                    mineObjectsOnMap.add(new mineObjects("fruitTree", "axe",(int) (Math.random() * 5) + 5, new Resources("fruitWood","axe"), (int) (Math.random() * 2) + 3,new Resources("apples"),(int) (Math.random() * 3) + 1, i, j));
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
                                    mineObjectsOnMap.add(new mineObjects("autumnTree","axe", (int) (Math.random() * 5) + 5, new Resources("autumnWood","axe"), (int) (Math.random() * 2) + 3, i, j));

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
                                        mineObjectsOnMap.add(new mineObjects("rock","pickaxe", (int) (Math.random() * 5) + 10, new Resources("cobblestone","pickaxe"), (int) (Math.random() * 3) + 1, i, j));

                                    } else if (mineralRand < 6) {
                                        map[i][j] = "goldOre";
                                        mineObjectsOnMap.add(new mineObjects("goldOre","pickaxe", (int) (Math.random() * 5) + 15, new Resources("goldIngot","pickaxe"), (int) (Math.random() * 2) + 1, i, j));

                                    } else if (mineralRand < 8) {
                                        map[i][j] = "diamondOre";
                                        mineObjectsOnMap.add(new mineObjects("diamondOre","pickaxe", (int) (Math.random() * 5) + 20, new Resources("diamond","pickaxe"), 1, i, j));

                                    } else if (mineralRand < 11) {
                                        map[i][j] = "rubyOre";
                                        mineObjectsOnMap.add(new mineObjects("rubyOre","pickaxe", (int) (Math.random() * 5) + 15, new Resources("ruby","pickaxe"), (int) (Math.random() * 2) + 1, i, j));

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