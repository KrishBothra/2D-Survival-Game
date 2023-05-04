package com.example.template;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;

public class HelloController {
    @FXML
    private Label lbl, coordsLabel, dayNightLbl;
    @FXML
    private GridPane gPane, inventoryPane, hotbarG, craftingPane;

    @FXML
    private Button startB;
    @FXML
    private AnchorPane anchor;

    @FXML
    private TextField txtField;
    @FXML
    private ImageView arrowImg;

    @FXML
    private ProgressBar miningBar, healthBar, hungerBar,dayNightBar,overHealthBar,swingBar;

    @FXML
    private Rectangle slot1,slot2,slot3,slot4,slot5;

    private int x = 25;

    private int y = 41;

    private long miningTime = System.nanoTime();
    private long eatingTime = System.nanoTime();

    ImageView[][] img = new ImageView[x][y];


    private ImageView[][] hotbarImg = new ImageView[5][1];

    String[][] map = new String[x * 8 + 1][y * 8 + 1]; //100 //164
    String[][] mapBackground = new String[x * 8 + 1][y * 8 + 1]; //100 //164


    private ArrayList<String> biomeNameList = new ArrayList<>();
    private String directionInter = "right";
    private ArrayList<Biome> biomeArrayList = new ArrayList<>();
    private ArrayList<mineObjects> mineObjectsOnMap = new ArrayList<>();

    private inventoryItems[] hotbar = new inventoryItems[5];
    private inventoryItems[][] inventoryA = new inventoryItems[6][12];

    private boolean furnaceShowing;

    private inventoryItems[][] craftingA = new inventoryItems[6][12];

    private inventoryItems inventorySelected = new inventoryItems("empty");
    private ImageView[][] inventoryImg = new ImageView[6][12];

    private int invSelectedRow = -1;
    private int invSelectedCol = -1;

    private inventoryItems equipped;
    private int selected = 0;

    private ArrayList<Animals> animalsOnMap = new ArrayList<>();
    private ArrayList<mobsNoCreeper> mobsNoCreepersOnMap = new ArrayList<>();


    private Label[][] inventoryLabels = new Label[4][5];


    @FXML
    private Label furnaceTop, furnaceBottom, one1, one2, one3, one4, one5, two1, two2, two3, two4, two5, three1, three2, three3, three4, three5, four1, four2, four3, four4, four5, one1c, one2c, one3cv, two1c, two2c, two3cv, three1cv, three2cv, three3cv, result;



    private boolean fruitQuest, normalQuest, autumnQuest, stoneQuest, waterQuest;

    long swingTime = System.nanoTime();
//    int changeX = 0;
//    int changeY = 0;

    int playerPositionX = 99;//-1
    int playerPositionY = 163;//-1

    double totalHealth = 100;
    double tempHealth = 100;

    double totalHunger = 100;
    double tempHunger = 50;

    int dayTime = 15;
    int nightTime = 60;

    boolean day = true;

    int totalDayTime = 120;
    int totalNightTime = 60;

    double totalOverHealth = 120;

    double tempOverHealth = 0;

    boolean firstMine = false;

    int tempPlayerPositionX;//-1
    int tempPlayerPositionY;//-1

    long regenTime = System.nanoTime();

    int miningX;
    int miningY;
    mineObjects tempMine = null;
    //12,20----13,21
//    MediaPlayer mediaPlayer;
//    Media sound;

    FileInputStream grasss, playerr, playerOverGrasss, playerOverStonee, autumnTreee, fruitTreee, normalTreee, grassWXx, arroww, stonee, rockk, diamondOree, rubyOree, goldOree, waterr, chestWaterr, mailboxGrasss, mailboxStonee
            , grayBackk, blackBackk, yellowBackk, rubyInvv,goldIngotInvv,diamondInvv, normalWoodd,normalWooddInv,autumnWooddInv,fruitWooddInv,appleeInv,cobblestoneInvv,woodAxeInvv,autumnWoodd,jungleWoodd,
            sheepp, normalPlankkInv, fruitPlankkInv, autumnPlankkInv,fruitPlankk,autumnPlankk,normalPlankk, craftingTableeInv, craftingTablee, stickkInv, woodPickaxeeInv, woodSworddInv, boattInv, boatt
            ,rawMuttonInvv,coww,pigg,rawPorkInvv,rawBeefInvv, furnaceeInv, furnacee, stoneSworddInv, rubySworddInv, goldSworddInv, diamondSworddInv, stoneAxeeInv, rubyAxeeInv, goldAxeeInv, diamondAxeeInv, stonePickaxeeInv
            ,rubyPickaxeeInv, goldPickaxeeInv, diamondPickaxeeInv, woodHelmettInv, woodChestplateeInv, woodLeggingssInv, woodBootssInv, rubyHelmettInv, rubyChestplateeInv, rubyLeggingssInv, rubyBootssInv, goldHelmettInv
            ,goldChestplateeInv, goldLeggingssInv, goldBootssInv, diamondHelmettInv, diamondChestplateeInv, diamondLeggingssInv, diamondBootssInv,villagerr,zombieOverGrasss,zombieOverStonee,rottenFleshh, coalOree;

    Image grass, player, playerOverGrass, playerOverStone, autumnTree, fruitTree, normalTree, grassWX, arrow, stone, rock, diamondOre, rubyOre, goldOre, water, chestWater, mailboxGrass, mailboxStone
            , grayBack, blackBack, yellowBack, rubyInv,goldIngotInv,diamondInv, normalWood,normalWoodInv,autumnWoodInv,fruitWoodInv,appleInv,cobbelstoneInv,woodAxeInv,autumnWood,fruitWood
            ,sheep, normalPlankInv, fruitPlankInv, autumnPlankInv,fruitPlank,autumnPlank,normalPlank, craftingTableInv, craftingTable, stickInv, woodPickaxeInv, woodSwordInv, boatInv, boat,
            rawMuttonInv,cow,pig,rawPorkInv,rawBeefInv, furnaceInv, furnace, stoneSwordInv, rubySwordInv, goldSwordInv, diamondSwordInv, stoneAxeInv, rubyAxeInv, goldAxeInv, diamondAxeInv, stonePickaxeInv, rubyPickaxeInv
            ,goldPickaxeInv, diamondPickaxeInv, woodHelmetInv, woodChestplateInv, woodLeggingsInv, woodBootsInv, rubyHelmetInv, rubyChestplateInv, rubyLeggingsInv, rubyBootsInv, goldHelmetInv, goldChestplateInv,
            goldLeggingsInv, goldBootsInv, diamondHelmetInv, diamondChestplateInv, diamondLeggingsInv, diamondBootsInv,villager,zombieOverGrass,zombieOverStone,rottenFlesh, coalOre;
    private boolean miningObject = false;
    private boolean eatingFood = false;
    private int tempMineTime;
    private boolean inventoryShowing = false;
    private boolean craftingShowing = false;
    private boolean breakB = false;
    private boolean clickedS = false;
    int amountChange = 0;
    private boolean clickedP  = false;
    private double toolBoost =1;
    private int eatingCount =6;
    private long dayNightTime = System.nanoTime();
    private int maxOverHealth;
    private boolean swing = false;
    private double swingCount = 3;

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
            autumnWoodd = new FileInputStream("src/main/resources/acaciaWood.png");
            jungleWoodd = new FileInputStream("src/main/resources/jungleWood.png");
            sheepp = new FileInputStream("src/main/resources/Animals/sheep.png");
            normalPlankkInv = new FileInputStream("src/main/resources/InventoryItems/oakPlank.png");
            autumnPlankkInv = new FileInputStream("src/main/resources/InventoryItems/acaiaPlank.png");
            fruitPlankkInv = new FileInputStream("src/main/resources/InventoryItems/junglePlank.png");
            fruitPlankk = new FileInputStream("src/main/resources/junglePlank.png");
            autumnPlankk = new FileInputStream("src/main/resources/acaciaPlank.jpg");
            normalPlankk = new FileInputStream("src/main/resources/oakPlank.png");
            villagerr = new FileInputStream("src/main/resources/Animals/villager.png");
            craftingTablee = new FileInputStream("src/main/resources/craftingTable.jpg");
            boatt = new FileInputStream("src/main/resources/boatOverWater.png");
            craftingTableeInv = new FileInputStream("src/main/resources/InventoryItems/craftingTable.png");
            woodPickaxeeInv = new FileInputStream("src/main/resources/InventoryItems/woodPickaxe.png");
            woodSworddInv = new FileInputStream("src/main/resources/InventoryItems/woodSword.png");
            stickkInv = new FileInputStream("src/main/resources/InventoryItems/stick.png");
            boattInv = new FileInputStream("src/main/resources/InventoryItems/boat.png");
            rawMuttonInvv = new FileInputStream("src/main/resources/InventoryItems/rawMutton.png");
            rawPorkInvv = new FileInputStream("src/main/resources/InventoryItems/rawPork.png");
            rawBeefInvv = new FileInputStream("src/main/resources/InventoryItems/rawBeef.png");
            coww = new FileInputStream("src/main/resources/Animals/cow.png");
            pigg = new FileInputStream("src/main/resources/Animals/pig.png");
            furnaceeInv = new FileInputStream("src/main/resources/InventoryItems/furnace.png");
            furnacee = new FileInputStream("src/main/resources/furnaceUnlit.png");
            stoneSworddInv = new FileInputStream("src/main/resources/InventoryItems/stoneSword.png");
            rubySworddInv = new FileInputStream("src/main/resources/InventoryItems/rubySword.png");
            goldSworddInv = new FileInputStream("src/main/resources/InventoryItems/goldSword.png");
            diamondSworddInv = new FileInputStream("src/main/resources/InventoryItems/diamondSword.png");
            stoneAxeeInv = new FileInputStream("src/main/resources/InventoryItems/stoneAxe.png");
            rubyAxeeInv = new FileInputStream("src/main/resources/InventoryItems/rubyAxe.png");
            goldAxeeInv = new FileInputStream("src/main/resources/InventoryItems/goldAxe.png");
            diamondAxeeInv = new FileInputStream("src/main/resources/InventoryItems/diamondAxe.png");
            stonePickaxeeInv = new FileInputStream("src/main/resources/InventoryItems/stonePickaxe.png");
            rubyPickaxeeInv = new FileInputStream("src/main/resources/InventoryItems/rubyPickaxe.png");
            goldPickaxeeInv = new FileInputStream("src/main/resources/InventoryItems/goldPickaxe.png");
            diamondPickaxeeInv = new FileInputStream("src/main/resources/InventoryItems/diamondPickaxe.png");
            woodHelmettInv = new FileInputStream("src/main/resources/InventoryItems/woodHelmet.png");
            woodChestplateeInv = new FileInputStream("src/main/resources/InventoryItems/woodChest.png");
            woodLeggingssInv = new FileInputStream("src/main/resources/InventoryItems/woodLeggings.png");
            woodBootssInv = new FileInputStream("src/main/resources/InventoryItems/woodBoots.png");
            rubyHelmettInv = new FileInputStream("src/main/resources/InventoryItems/rubyHelmet.png");
            rubyChestplateeInv = new FileInputStream("src/main/resources/InventoryItems/rubyChest.png");
            rubyLeggingssInv = new FileInputStream("src/main/resources/InventoryItems/rubyLeggings.png");
            rubyBootssInv = new FileInputStream("src/main/resources/InventoryItems/rubyBoots.png");
            goldHelmettInv = new FileInputStream("src/main/resources/InventoryItems/goldHelmet.png");
            goldChestplateeInv = new FileInputStream("src/main/resources/InventoryItems/goldChest.png");
            goldLeggingssInv = new FileInputStream("src/main/resources/InventoryItems/goldLeggings.png");
            goldBootssInv = new FileInputStream("src/main/resources/InventoryItems/goldBoots.png");
            diamondHelmettInv = new FileInputStream("src/main/resources/InventoryItems/diamondHelmet.png");
            diamondChestplateeInv = new FileInputStream("src/main/resources/InventoryItems/diamondChest.png");
            diamondLeggingssInv = new FileInputStream("src/main/resources/InventoryItems/diamondLeggings.png");
            diamondBootssInv = new FileInputStream("src/main/resources/InventoryItems/diamondBoots.png");
            zombieOverStonee = new FileInputStream("src/main/resources/Animals/zombieOverStone.png");
            zombieOverGrasss = new FileInputStream("src/main/resources/Animals/zombieOverGrass.png");
            rottenFleshh = new FileInputStream("src/main/resources/InventoryItems/rottenFlesh.png");
            coalOree = new FileInputStream("src/main/resources/coalOre.png");

            rottenFlesh = new Image(rottenFleshh);
            zombieOverGrass = new Image(zombieOverGrasss);
            zombieOverStone = new Image(zombieOverStonee);
            furnaceInv = new Image(furnaceeInv);
            furnace = new Image(furnacee);
            stoneSwordInv = new Image(stoneSworddInv);
            rubySwordInv = new Image(rubySworddInv);
            goldSwordInv = new Image(goldSworddInv);
            diamondSwordInv = new Image(diamondSworddInv);
            stoneAxeInv = new Image(stoneAxeeInv);
            rubyAxeInv = new Image(rubyAxeeInv);
            goldAxeInv = new Image(goldAxeeInv);
            diamondAxeInv = new Image(diamondAxeeInv);
            stonePickaxeInv = new Image(stonePickaxeeInv);
            rubyPickaxeInv = new Image(rubyPickaxeeInv);
            goldPickaxeInv = new Image(goldPickaxeeInv);
            diamondPickaxeInv = new Image(diamondPickaxeeInv);
            woodHelmetInv = new Image(woodHelmettInv);
            woodChestplateInv = new Image(woodChestplateeInv);
            woodLeggingsInv = new Image(woodLeggingssInv);
            woodBootsInv = new Image(woodBootssInv);
            rubyHelmetInv = new Image(rubyHelmettInv);
            rubyChestplateInv = new Image(rubyChestplateeInv);
            rubyLeggingsInv = new Image(rubyLeggingssInv);
            rubyBootsInv = new Image(rubyBootssInv);
            goldHelmetInv = new Image(goldHelmettInv);
            goldChestplateInv = new Image(goldChestplateeInv);
            goldLeggingsInv = new Image(goldLeggingssInv);
            goldBootsInv = new Image(goldBootssInv);
            diamondHelmetInv = new Image(diamondHelmettInv);
            diamondChestplateInv = new Image(diamondChestplateeInv);
            diamondLeggingsInv = new Image(diamondLeggingssInv);
            diamondBootsInv = new Image(diamondBootssInv);
            villager= new Image(villagerr);
            cow = new Image(coww);
            pig = new Image(pigg);
            rawMuttonInv = new Image(rawMuttonInvv);
            rawBeefInv = new Image(rawBeefInvv);
            rawPorkInv = new Image(rawPorkInvv);
            fruitPlank = new Image(fruitPlankk);
            autumnPlank = new Image(autumnPlankk);
            normalPlank = new Image(normalPlankk);
            sheep = new Image(sheepp);
            autumnWood = new Image(autumnWoodd);
            fruitWood = new Image(jungleWoodd);
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
            normalPlankInv = new Image(normalPlankkInv);
            autumnPlankInv = new Image(autumnPlankkInv);
            fruitPlankInv = new Image(fruitPlankkInv);
            coalOre = new Image(coalOree);

            craftingTableInv = new Image(craftingTableeInv);
            craftingTable = new Image(craftingTablee);
            stickInv = new Image(stickkInv);
            boatInv = new Image(boattInv);
            boat = new Image(boatt);
            woodSwordInv = new Image(woodSworddInv);
            woodPickaxeInv = new Image(woodPickaxeeInv);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void onClick() {
        System.out.println((false));
        mobsNoCreepersOnMap.add(new mobsNoCreeper("zombieOverGrass",30,new Food("rottenFlesh",5),(int)(Math.random()*15),1.25,(int) (Math.random()*3)+1,10,99,160));
        startB.setDisable(true);
        healthBar.setProgress(tempHealth/totalHealth);
        healthBar.setStyle(" -fx-accent: #FF0000; ");
        hungerBar.setProgress(tempHunger/totalHunger);
        hungerBar.setStyle(" -fx-accent: #987554; ");
        dayNightBar.setStyle(" -fx-accent: orange; ");
//        mediaPlayer.play();
        biomeNameList.add("fruitTree");
        biomeNameList.add("normalTree");
        biomeNameList.add("autumnTree");
        biomeNameList.add("stone");
        biomeNameList.add("water");
//        hotbar[0] = new Resources("diamond");
//        animalsOnMap.add(new Animals("sheep",15,new Food("rawMutton"),(int) (Math.random()*4),(int)(Math.random()*3)+1,100,163));

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
        for (int i = 0; i < inventoryLabels.length; i++) {
            for (int j = 0; j < inventoryLabels[0].length; j++) {
                inventoryLabels[i][j].setVisible(false);
                one1c.setVisible(false);
                one2c.setVisible(false);
                one3cv.setVisible(false);
                two1c.setVisible(false);
                two2c.setVisible(false);
                two3cv.setVisible(false);
                three1cv.setVisible(false);
                three2cv.setVisible(false);
                three3cv.setVisible(false);
                result.setVisible(false);
                furnaceTop.setVisible(false);
                furnaceBottom.setVisible(false);
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
                            if(inventoryImg[row][col].getImage().equals(grayBack)) {
                                if(furnaceShowing){
                                    System.out.println("hey2");
                                    inventoryA[row][col] = inventoryA[invSelectedRow][invSelectedCol];
                                    inventoryA[invSelectedRow][invSelectedCol] = new inventoryItems("empty");
                                    invSelectedCol = -1;
                                    invSelectedRow = -1;
                                    updateScreen();
                                    clickedP = false;
                                }
                                else if(row==1&&col==7&&inventoryA[invSelectedRow][invSelectedCol].getName().endsWith("Helmet")||
                                        row==2&&col==7&&inventoryA[invSelectedRow][invSelectedCol].getName().endsWith("Chestplate")||
                                        row==3&&col==7&&inventoryA[invSelectedRow][invSelectedCol].getName().endsWith("Leggings")||
                                        row==4&&col==7&&inventoryA[invSelectedRow][invSelectedCol].getName().endsWith("Boots")||col!=7) {
                                    if(row!=4||col!=7) {
                                        System.out.println("hey2");
                                        inventoryA[row][col] = inventoryA[invSelectedRow][invSelectedCol];
                                        inventoryA[invSelectedRow][invSelectedCol] = new inventoryItems("empty");
                                        invSelectedCol = -1;
                                        invSelectedRow = -1;
                                        updateScreen();
                                        clickedP = false;
                                    }
                                }
                            }
                        } else if (inventoryA[row][col].getName().equals(inventoryA[invSelectedRow][invSelectedCol].getName())) {
                            if(row!=invSelectedRow||col!=invSelectedCol) {
                                System.out.println("hey5");
                                inventoryA[row][col].changeAmount(inventoryA[invSelectedRow][invSelectedCol].getAmount());
                                inventoryA[invSelectedRow][invSelectedCol] = new inventoryItems("empty");
                                invSelectedCol = -1;
                                invSelectedRow = -1;
                                updateScreen();
                            }
                        }
                    } else if (!inventoryA[row][col].getName().equals("empty")&&!inventoryA[row][col].getName().equals("nothing")) {
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
                            if(inventoryImg[row][col].getImage().equals(grayBack)) {
                                System.out.println("hey2");
                                inventoryA[row][col] = new inventoryItems(inventoryA[invSelectedRow][invSelectedCol].getName(), amountChange);
                                inventoryA[invSelectedRow][invSelectedCol].changeAmount(-(amountChange));
                                invSelectedCol = -1;
                                invSelectedRow = -1;
                                updateScreen();
                                clickedS = false;
                            }
                        }
                    } else if (!inventoryA[row][col].getName().equals("empty")&&!inventoryA[row][col].getName().equals("nothing")) {
                        if(inventoryA[row][col].getAmount()>1){
                            amountChange = inventoryA[row][col].getAmount()/2;
                            System.out.println("hey3");
                            invSelectedRow = row;
                            invSelectedCol = col;
                            clickedS = true;
                        }

                    }
                }
                System.out.println("THE THING IN TOP RIGHT" + inventoryA[1][5].getName());

                if(!furnaceShowing){
                    checkCrafts();
                }
                if(row==4&&col==9){
                    if(!inventoryA[4][9].getName().equals("empty")){
                        if(!craftingShowing){
                            for (int i = 1; i < 3; i++) {
                                for (int j = 9; j < 11; j++) {
                                    if(inventoryA[i][j].getAmount()>1){
                                        inventoryA[i][j].setAmount(inventoryA[i][j].getAmount()-1);
                                    }else{
                                        inventoryA[i][j] = new inventoryItems("empty");
                                    }
                                }
                            }
                        }else{
                            for (int i = 0; i < 3; i++) {
                                for (int j = 9; j < 12; j++) {
                                    if(inventoryA[i][j].getAmount()>1){
                                        inventoryA[i][j].setAmount(inventoryA[i][j].getAmount()-1);
                                    }else{
                                        inventoryA[i][j] = new inventoryItems("empty");
                                    }
                                }
                            }
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

        inventoryImg[1][7].setImage(grayBack);
        inventoryImg[2][7].setImage(grayBack);
        inventoryImg[3][7].setImage(grayBack);
        inventoryImg[4][7].setImage(grayBack);

        inventoryImg[1][9].setImage(grayBack);
        inventoryImg[2][9].setImage(grayBack);
        inventoryImg[1][10].setImage(grayBack);
        inventoryImg[2][10].setImage(grayBack);


        inventoryImg[4][9].setImage(grayBack);

        for (int i = 1; i <=4 ; i++) {
            for (int j = 1; j <=5 ; j++) {
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
//        map[98][160] = "null";
        map[99][163] = "playerOverGrass";
//        map[30][160] = "normalTree";
        createBiomes();


        gPane.setGridLinesVisible(true);
        for (int i = 0; i < hotbar.length; i++) {
            hotbar[i] = new inventoryItems("empty");
        }

        for (int i = 0; i < inventoryA.length; i++) {
            for (int j = 0; j < inventoryA[0].length; j++) {
                if(!inventoryImg[i][j].getImage().equals(blackBack)) {
                    inventoryA[i][j] = new inventoryItems("empty");
                }else{
                    inventoryA[i][j] = new inventoryItems("nothing");
                }

            }

        }

        inventoryA[4][1] = new Tools("woodAxe",1,"axe",3,20);
        inventoryA[4][2] = new Resources("craftingTable", "axe");
        inventoryA[1][1] = new Resources("cobblestone", "pickaxe");
        inventoryA[1][1].setAmount(99);
        inventoryA[1][2] = new Resources("normalWood", "axe");
        inventoryA[1][2].setAmount(99);
        inventoryA[1][3] = new Resources("autumnWood", "axe");
        inventoryA[1][3].setAmount(99);
        inventoryA[2][1] = new Resources("fruitWood", "axe");
        inventoryA[2][1].setAmount(99);
        inventoryA[2][2] = new inventoryItems("ruby");
        inventoryA[2][2].setAmount(99);
        inventoryA[2][3] = new inventoryItems("goldIngot");
        inventoryA[2][3].setAmount(99);
        inventoryA[3][1] = new inventoryItems("diamond");
        inventoryA[3][1].setAmount(99);
        inventoryA[3][2] = new inventoryItems("stick");
        inventoryA[3][2].setAmount(99);

        updateScreen();
        start();
        //for the change


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
                    }else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("autumnWood")) {
                        img[i][j].setImage(autumnWood);
                    }else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("fruitWood")) {
                        img[i][j].setImage(fruitWood);
                    }else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("sheep")) {
                        img[i][j].setImage(sheep);
                    }else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("fruitPlank")) {
                        img[i][j].setImage(fruitPlank);
                    }else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("normalPlank")) {
                        img[i][j].setImage(normalPlank);
                    }else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("autumnPlank")) {
                        img[i][j].setImage(autumnPlank);
                    }else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("craftingTable")) {
                        img[i][j].setImage(craftingTable);
                    }else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("boat")) {
                        img[i][j].setImage(boat);
                    }else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("cow")) {
                        img[i][j].setImage(cow);
                    }else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("pig")) {
                        img[i][j].setImage(pig);
                    }else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("furnace")) {
                        img[i][j].setImage(furnace);
                    }else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("villager")) {
                        img[i][j].setImage(villager);
                    }else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("zombieOverGrass")) {
                        img[i][j].setImage(zombieOverGrass);
                    }else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("zombieOverStone")) {
                        img[i][j].setImage(zombieOverStone);
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
                    }else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("autumnWood")) {
                        img[i][j].setImage(autumnWood); //steve
                    }else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("fruitWood")) {
                        img[i][j].setImage(fruitWood); //steve
                    }else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("sheep")) {
                        img[i][j].setImage(sheep); //steve
                    }else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("fruitPlank")) {
                        img[i][j].setImage(fruitPlank); //steve
                    }else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("normalPlank")) {
                        img[i][j].setImage(normalPlank); //steve
                    }else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("autumnPlank")) {
                        img[i][j].setImage(autumnPlank); //steve
                    }else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("craftingTable")) {
                        img[i][j].setImage(craftingTable); //steve
                    }else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("boat")) {
                        img[i][j].setImage(boat); //steve
                    }else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("cow")) {
                        img[i][j].setImage(cow); //steve
                    }else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("pig")) {
                        img[i][j].setImage(pig); //steve
                    }else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("furnace")) {
                        img[i][j].setImage(furnace); //steve
                    }else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("villager")) {
                        img[i][j].setImage(villager); //steve
                    }else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("zombieOverGrass")) {
                        img[i][j].setImage(zombieOverGrass); //steve
                    }else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("zombieOverStone")) {
                        img[i][j].setImage(zombieOverStone); //steve
                    }
                }
            }
            tempPlayerPositionX = playerPositionX;
            tempPlayerPositionY = playerPositionY;





        }
        for (int i = 0; i<inventoryLabels.length; i++) {
            for (int j = 0; j < inventoryLabels[0].length; j++) {
                if(inventoryA[i+1][j+1].getAmount()!=0&&inventoryA[i+1][j+1].getAmount()!=1){
                    inventoryLabels[i][j].setText(Integer.toString(inventoryA[i+1][j+1].getAmount()));
                }else{
                    inventoryLabels[i][j].setText("");
                }
            }
        }

        if(inventoryA[2][9].getAmount()!=0&&inventoryA[2][9].getAmount()!=1){
            two1c.setText(Integer.toString(inventoryA[2][9].getAmount()));
        }else{
            two1c.setText("");
        }
        if(inventoryA[2][10].getAmount()!=0&&inventoryA[2][10].getAmount()!=1){
            two2c.setText(Integer.toString(inventoryA[2][10].getAmount()));
        }else{
            two2c.setText("");
        }
        if(inventoryA[1][9].getAmount()!=0&&inventoryA[1][9].getAmount()!=1){
            one1c.setText(Integer.toString(inventoryA[1][9].getAmount()));
        }else{
            one1c.setText("");
        }
        if(inventoryA[1][10].getAmount()!=0&&inventoryA[1][10].getAmount()!=1){
            one2c.setText(Integer.toString(inventoryA[1][10].getAmount()));
        }else{
            one2c.setText("");
        }
        if(inventoryA[4][9].getAmount()!=0&&inventoryA[4][9].getAmount()!=1){
            result.setText(Integer.toString(inventoryA[4][9].getAmount()));
        }else{
            result.setText("");
        }



        if(inventoryA[0][9].getAmount()!=0&&inventoryA[0][9].getAmount()!=1){
            three1cv.setText(Integer.toString(inventoryA[0][9].getAmount()));
        }else{
            three1cv.setText("");
        }
        if(inventoryA[0][10].getAmount()!=0&&inventoryA[0][10].getAmount()!=1){
            three2cv.setText(Integer.toString(inventoryA[0][10].getAmount()));
        }else{
            three2cv.setText("");
        }
        if(inventoryA[0][11].getAmount()!=0&&inventoryA[0][11].getAmount()!=1){
            three3cv.setText(Integer.toString(inventoryA[0][11].getAmount()));
        }else{
            three3cv.setText("");
        }
        if(inventoryA[1][11].getAmount()!=0&&inventoryA[1][11].getAmount()!=1){
            one3cv.setText(Integer.toString(inventoryA[1][11].getAmount()));
        }else{
            one3cv.setText("");
        }
        if(inventoryA[2][11].getAmount()!=0&&inventoryA[2][11].getAmount()!=1){
            two3cv.setText(Integer.toString(inventoryA[2][11].getAmount()));
        }else{
            two3cv.setText("");
        }

        if(inventoryA[1][7].getAmount()!=0&&inventoryA[1][7].getAmount()!=1){
            furnaceTop.setText(Integer.toString(inventoryA[1][7].getAmount()));
        }else{
            furnaceTop.setText("");
        }
        if(inventoryA[3][7].getAmount()!=0&&inventoryA[3][7].getAmount()!=1){
            furnaceBottom.setText(Integer.toString(inventoryA[3][7].getAmount()));
        }else{
            furnaceBottom.setText("");
        }
        //System.out.println(inventoryA[4][selected].getName());
        for (int i = 1; i < 6; i++) {
            hotbar[i-1]=inventoryA[4][i];
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
            }else if(hotbar[i].getName().equals("normalPlank")) {
                hotbarImg[i][0].setImage(normalPlankInv);
            } else if(hotbar[i].getName().equals("autumnPlank")) {
                hotbarImg[i][0].setImage(autumnPlankInv);
            }else if(hotbar[i].getName().equals("fruitPlank")) {
                hotbarImg[i][0].setImage(fruitPlankInv);
            }else if(hotbar[i].getName().equals("craftingTable")) {
                hotbarImg[i][0].setImage(craftingTableInv);
            }else if(hotbar[i].getName().equals("boat")) {
                hotbarImg[i][0].setImage(boatInv);
            }else if(hotbar[i].getName().equals("stick")) {
                hotbarImg[i][0].setImage(stickInv);
            }else if(hotbar[i].getName().equals("woodSword")) {
                hotbarImg[i][0].setImage(woodSwordInv);
            }else if(hotbar[i].getName().equals("woodPickaxe")) {
                hotbarImg[i][0].setImage(woodPickaxeInv);
            }else if(hotbar[i].getName().equals("rawMutton")) {
                hotbarImg[i][0].setImage(rawMuttonInv);
            }else if(hotbar[i].getName().equals("rawPork")) {
                hotbarImg[i][0].setImage(rawPorkInv);
            }else if(hotbar[i].getName().equals("rawBeef")) {
                hotbarImg[i][0].setImage(rawBeefInv);
            }else if(hotbar[i].getName().equals("furnace")) {
                hotbarImg[i][0].setImage(furnaceInv);
            }else if(hotbar[i].getName().equals("stoneSword")) {
                hotbarImg[i][0].setImage(stoneSwordInv);
            }else if(hotbar[i].getName().equals("rubySword")) {
                hotbarImg[i][0].setImage(rubySwordInv);
            }else if(hotbar[i].getName().equals("goldSword")) {
                hotbarImg[i][0].setImage(goldSwordInv);
            }else if(hotbar[i].getName().equals("diamondSword")) {
                hotbarImg[i][0].setImage(diamondSwordInv);
            }else if(hotbar[i].getName().equals("stoneAxe")) {
                hotbarImg[i][0].setImage(stoneAxeInv);
            }else if(hotbar[i].getName().equals("rubyAxe")) {
                hotbarImg[i][0].setImage(rubyAxeInv);
            }else if(hotbar[i].getName().equals("goldAxe")) {
                hotbarImg[i][0].setImage(goldAxeInv);
            }else if(hotbar[i].getName().equals("diamondAxe")) {
                hotbarImg[i][0].setImage(diamondAxeInv);
            }else if(hotbar[i].getName().equals("stonePickaxe")) {
                hotbarImg[i][0].setImage(stonePickaxeInv);
            }else if(hotbar[i].getName().equals("rubyPickaxe")) {
                hotbarImg[i][0].setImage(rubyPickaxeInv);
            }else if(hotbar[i].getName().equals("goldPickaxe")) {
                hotbarImg[i][0].setImage(goldPickaxeInv);
            }else if(hotbar[i].getName().equals("diamondPickaxe")) {
                hotbarImg[i][0].setImage(diamondPickaxeInv);
            }else if(hotbar[i].getName().equals("woodHelmet")) {
                hotbarImg[i][0].setImage(woodHelmetInv);
            }else if(hotbar[i].getName().equals("woodChestplate")) {
                hotbarImg[i][0].setImage(woodChestplateInv);
            }else if(hotbar[i].getName().equals("woodLeggings")) {
                hotbarImg[i][0].setImage(woodLeggingsInv);
            }else if(hotbar[i].getName().equals("woodBoots")) {
                hotbarImg[i][0].setImage(woodBootsInv);
            }else if(hotbar[i].getName().equals("rubyHelmet")) {
                hotbarImg[i][0].setImage(rubyHelmetInv);
            }else if(hotbar[i].getName().equals("rubyChestplate")) {
                hotbarImg[i][0].setImage(rubyChestplateInv);
            }else if(hotbar[i].getName().equals("rubyLeggings")) {
                hotbarImg[i][0].setImage(rubyLeggingsInv);
            }else if(hotbar[i].getName().equals("rubyBoots")) {
                hotbarImg[i][0].setImage(rubyBootsInv);
            }else if(hotbar[i].getName().equals("goldHelmet")) {
                hotbarImg[i][0].setImage(goldHelmetInv);
            }else if(hotbar[i].getName().equals("goldChestplate")) {
                hotbarImg[i][0].setImage(goldChestplateInv);
            }else if(hotbar[i].getName().equals("goldLeggings")) {
                hotbarImg[i][0].setImage(goldLeggingsInv);
            }else if(hotbar[i].getName().equals("goldBoots")) {
                hotbarImg[i][0].setImage(goldBootsInv);
            }else if(hotbar[i].getName().equals("diamondHelmet")) {
                hotbarImg[i][0].setImage(diamondHelmetInv);
            }else if(hotbar[i].getName().equals("diamondChestplate")) {
                hotbarImg[i][0].setImage(diamondChestplateInv);
            }else if(hotbar[i].getName().equals("diamondLeggings")) {
                hotbarImg[i][0].setImage(diamondLeggingsInv);
            }else if(hotbar[i].getName().equals("diamondBoots")) {
                hotbarImg[i][0].setImage(diamondBootsInv);
            }else if(hotbar[i].getName().equals("rottenFlesh")) {
                hotbarImg[i][0].setImage(rottenFlesh);
            }


            else if(hotbar[i].getName().equals("empty")){
                hotbarImg[i][0].setImage(grayBack);
            }
        }

        for (int i = 0; i <inventoryA.length; i++) {
            for (int j = 0; j <inventoryA[0].length; j++) {
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
                }else if(inventoryA[i][j].getName().equals("normalPlank")) {
                    inventoryImg[i][j].setImage(normalPlankInv);
                } else if(inventoryA[i][j].getName().equals("autumnPlank")) {
                    inventoryImg[i][j].setImage(autumnPlankInv);
                }else if(inventoryA[i][j].getName().equals("fruitPlank")) {
                    inventoryImg[i][j].setImage(fruitPlankInv);
                }else if(inventoryA[i][j].getName().equals("craftingTable")) {
                    inventoryImg[i][j].setImage(craftingTableInv);
                }else if(inventoryA[i][j].getName().equals("stick")) {
                    inventoryImg[i][j].setImage(stickInv);
                }else if(inventoryA[i][j].getName().equals("boat")) {
                    inventoryImg[i][j].setImage(boatInv);
                }else if(inventoryA[i][j].getName().equals("woodSword")) {
                    inventoryImg[i][j].setImage(woodSwordInv);
                }else if(inventoryA[i][j].getName().equals("woodPickaxe")) {
                    inventoryImg[i][j].setImage(woodPickaxeInv);
                }else if(inventoryA[i][j].getName().equals("rawMutton")) {
                    inventoryImg[i][j].setImage(rawMuttonInv);
                }else if(inventoryA[i][j].getName().equals("rawBeef")) {
                    inventoryImg[i][j].setImage(rawBeefInv);
                }else if(inventoryA[i][j].getName().equals("rawPork")) {
                    inventoryImg[i][j].setImage(rawPorkInv);
                }else if(inventoryA[i][j].getName().equals("furnace")) {
                    inventoryImg[i][j].setImage(furnaceInv);
                }else if(inventoryA[i][j].getName().equals("stoneSword")) {
                    inventoryImg[i][j].setImage(stoneSwordInv);
                }else if(inventoryA[i][j].getName().equals("rubySword")) {
                    inventoryImg[i][j].setImage(rubySwordInv);
                }else if(inventoryA[i][j].getName().equals("goldSword")) {
                    inventoryImg[i][j].setImage(goldSwordInv);
                }else if(inventoryA[i][j].getName().equals("diamondSword")) {
                    inventoryImg[i][j].setImage(diamondSwordInv);
                }else if(inventoryA[i][j].getName().equals("stoneAxe")) {
                    inventoryImg[i][j].setImage(stoneAxeInv);
                }else if(inventoryA[i][j].getName().equals("rubyAxe")) {
                    inventoryImg[i][j].setImage(rubyAxeInv);
                }else if(inventoryA[i][j].getName().equals("goldAxe")) {
                    inventoryImg[i][j].setImage(goldAxeInv);
                }else if(inventoryA[i][j].getName().equals("diamondAxe")) {
                    inventoryImg[i][j].setImage(diamondAxeInv);
                }else if(inventoryA[i][j].getName().equals("stonePickaxe")) {
                    inventoryImg[i][j].setImage(stonePickaxeInv);
                }else if(inventoryA[i][j].getName().equals("rubyPickaxe")) {
                    inventoryImg[i][j].setImage(rubyPickaxeInv);
                }else if(inventoryA[i][j].getName().equals("goldPickaxe")) {
                    inventoryImg[i][j].setImage(goldPickaxeInv);
                }else if(inventoryA[i][j].getName().equals("diamondPickaxe")) {
                    inventoryImg[i][j].setImage(diamondPickaxeInv);
                }else if(inventoryA[i][j].getName().equals("woodHelmet")) {
                    inventoryImg[i][j].setImage(woodHelmetInv);
                }else if(inventoryA[i][j].getName().equals("woodChestplate")) {
                    inventoryImg[i][j].setImage(woodChestplateInv);
                }else if(inventoryA[i][j].getName().equals("woodLeggings")) {
                    inventoryImg[i][j].setImage(woodLeggingsInv);
                }else if(inventoryA[i][j].getName().equals("woodBoots")) {
                    inventoryImg[i][j].setImage(woodBootsInv);
                }else if(inventoryA[i][j].getName().equals("rubyHelmet")) {
                    inventoryImg[i][j].setImage(rubyHelmetInv);
                }else if(inventoryA[i][j].getName().equals("rubyChestplate")) {
                    inventoryImg[i][j].setImage(rubyChestplateInv);
                }else if(inventoryA[i][j].getName().equals("rubyLeggings")) {
                    inventoryImg[i][j].setImage(rubyLeggingsInv);
                }else if(inventoryA[i][j].getName().equals("rubyBoots")) {
                    inventoryImg[i][j].setImage(rubyBootsInv);
                }else if(inventoryA[i][j].getName().equals("goldHelmet")) {
                    inventoryImg[i][j].setImage(goldHelmetInv);
                }else if(inventoryA[i][j].getName().equals("goldChestplate")) {
                    inventoryImg[i][j].setImage(goldChestplateInv);
                }else if(inventoryA[i][j].getName().equals("goldLeggings")) {
                    inventoryImg[i][j].setImage(goldLeggingsInv);
                }else if(inventoryA[i][j].getName().equals("goldBoots")) {
                    inventoryImg[i][j].setImage(goldBootsInv);
                }else if(inventoryA[i][j].getName().equals("diamondHelmet")) {
                    inventoryImg[i][j].setImage(diamondHelmetInv);
                }else if(inventoryA[i][j].getName().equals("diamondChestplate")) {
                    inventoryImg[i][j].setImage(diamondChestplateInv);
                }else if(inventoryA[i][j].getName().equals("diamondLeggings")) {
                    inventoryImg[i][j].setImage(diamondLeggingsInv);
                }else if(inventoryA[i][j].getName().equals("diamondBoots")) {
                    inventoryImg[i][j].setImage(diamondBootsInv);
                }else if(inventoryA[i][j].getName().equals("rottenFlesh")) {
                    inventoryImg[i][j].setImage(rottenFlesh);
                }



                else if(inventoryA[i][j].getName().equals("empty")){
                    inventoryImg[i][j].setImage(grayBack);
                }
            }
        }
        tempOverHealth = 0;
        for (int i = 1; i <= 4; i++) {
            tempOverHealth +=  inventoryA[i][7].getProtection();
        }

        overHealthBar.setProgress( tempOverHealth/totalOverHealth);
        equipped = hotbar[selected];


        //System.out.println(equipped.getName());
    }

//    public void onClick2() {
//        map[playerPositionX][playerPositionY] = "grass";
//        playerPositionX -=2;
//        playerPositionY -= 5;
//        map[playerPositionX][playerPositionY] = "playerOverGrass";
//        updateScreen();
//    }qr

    private void checkCrafts(){
        inventoryItems tl = inventoryA[1][9];
        inventoryItems tr = inventoryA[1][10];
        inventoryItems bl = inventoryA[2][9];
        inventoryItems br = inventoryA[2][10];

        inventoryItems tlc = inventoryA[0][9];
        inventoryItems tmc = inventoryA[0][10];
        inventoryItems trc = inventoryA[0][11];
        inventoryItems mrc = inventoryA[1][11];
        inventoryItems brc = inventoryA[2][11];


        if(!craftingShowing){
            /////////////////////NORMAL PLANKS
            if(tl.getName().equals("normalWood")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")){
                inventoryA[4][9] = new Resources("normalPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("normalWood")&&bl.getName().equals("empty")&&br.getName().equals("empty")){
                inventoryA[4][9] = new Resources("normalPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("normalWood")&&br.getName().equals("empty")){
                inventoryA[4][9] = new Resources("normalPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("normalWood")){
                inventoryA[4][9] = new Resources("normalPlank","axe");
                inventoryA[4][9].setAmount(4);
            }

            /////////////////////FRUIT PLANKS
            else if(tl.getName().equals("fruitWood")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")){
                inventoryA[4][9] = new Resources("fruitPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("fruitWood")&&bl.getName().equals("empty")&&br.getName().equals("empty")){
                inventoryA[4][9] = new Resources("fruitPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("fruitWood")&&br.getName().equals("empty")){
                inventoryA[4][9] = new Resources("fruitPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("fruitWood")){
                inventoryA[4][9] = new Resources("fruitPlank","axe");
                inventoryA[4][9].setAmount(4);
            }

            /////////////////////AUTUMN PLANKS
            else if(tl.getName().equals("autumnWood")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")){
                inventoryA[4][9] = new Resources("autumnPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("autumnWood")&&bl.getName().equals("empty")&&br.getName().equals("empty")){
                inventoryA[4][9] = new Resources("autumnPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("autumnWood")&&br.getName().equals("empty")){
                inventoryA[4][9] = new Resources("autumnPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("autumnWood")){
                inventoryA[4][9] = new Resources("autumnPlank","axe");
                inventoryA[4][9].setAmount(4);
            }

            /////////////////////STICKS
            else if(tl.getName().equals("autumnPlank")&&tr.getName().equals("empty")&&bl.getName().equals("autumnPlank")&&br.getName().equals("empty")){
                inventoryA[4][9] = new inventoryItems("stick");
                inventoryA[4][9].setAmount(4);
            } else if(tl.getName().equals("empty")&&tr.getName().equals("autumnPlank")&&bl.getName().equals("empty")&&br.getName().equals("autumnPlank")){
                inventoryA[4][9] = new inventoryItems("stick");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("normalPlank")&&tr.getName().equals("empty")&&bl.getName().equals("normalPlank")&&br.getName().equals("empty")){
                inventoryA[4][9] = new inventoryItems("stick");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("normalPlank")&&bl.getName().equals("empty")&&br.getName().equals("normalPlank")){
                inventoryA[4][9] = new inventoryItems("stick");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("fruitPlank")&&tr.getName().equals("empty")&&bl.getName().equals("fruitPlank")&&br.getName().equals("empty")){
                inventoryA[4][9] = new inventoryItems("stick");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("fruitPlank")&&bl.getName().equals("empty")&&br.getName().equals("fruitPlank")){
                inventoryA[4][9] = new inventoryItems("stick");
                inventoryA[4][9].setAmount(4);
            }

            /////////////////////CRAFTING TABLE
            else if(tl.getName().equals("fruitPlank")&&tr.getName().equals("fruitPlank")&&bl.getName().equals("fruitPlank")&&br.getName().equals("fruitPlank")){
                inventoryA[4][9] = new Resources("craftingTable","axe");
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("autumnPlank")&&tr.getName().equals("autumnPlank")&&bl.getName().equals("autumnPlank")&&br.getName().equals("autumnPlank")){
                inventoryA[4][9] = new Resources("craftingTable","axe");
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("normalPlank")&&tr.getName().equals("normalPlank")&&bl.getName().equals("normalPlank")&&br.getName().equals("normalPlank")){
                inventoryA[4][9] = new Resources("craftingTable","axe");
                inventoryA[4][9].setAmount(1);
            }



            else{
                inventoryA[4][9] = new inventoryItems("empty");
                inventoryA[4][9].setAmount(0);
            }
        }


        else{
            /////////////////////NORMAL PLANKS
            if(tl.getName().equals("normalWood")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("normalPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("normalWood")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("normalPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("normalWood")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("normalPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("normalWood")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("normalPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("normalWood")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("normalPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("normalWood")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("normalPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("normalWood")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("normalPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("normalWood")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("normalPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("normalWood")){
                inventoryA[4][9] = new Resources("normalPlank","axe");
                inventoryA[4][9].setAmount(4);
            }

            /////////////////////AUTUMN PLANKS
            else if(tl.getName().equals("autumnWood")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("autumnPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("autumnWood")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("autumnPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("autumnWood")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("autumnPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("autumnWood")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("autumnPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("autumnWood")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("autumnPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("autumnWood")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("autumnPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("autumnWood")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("autumnPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("autumnWood")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("autumnPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("autumnWood")){
                inventoryA[4][9] = new Resources("autumnPlank","axe");
                inventoryA[4][9].setAmount(4);
            }

            /////////////////////FRUIT PLANKS
            else if(tl.getName().equals("fruitWood")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("fruitPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("fruitWood")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("fruitPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("fruitWood")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("fruitPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("fruitWood")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("fruitPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("fruitWood")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("fruitPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("fruitWood")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("fruitPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("fruitWood")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("fruitPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("fruitWood")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("fruitPlank","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("fruitWood")){
                inventoryA[4][9] = new Resources("fruitPlank","axe");
                inventoryA[4][9].setAmount(4);
            }

            /////////////////////STICKS
            else if(tl.getName().equals("normalPlank")&&tr.getName().equals("empty")&&bl.getName().equals("normalPlank")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new inventoryItems("stick");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("normalPlank")&&bl.getName().equals("empty")&&br.getName().equals("normalPlank")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new inventoryItems("stick");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("normalPlank")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("normalPlank")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new inventoryItems("stick");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("normalPlank")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("normalPlank")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new inventoryItems("stick");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("normalPlank")&&mrc.getName().equals("normalPlank")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new inventoryItems("stick");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("normalPlank")&&brc.getName().equals("normalPlank")){
                inventoryA[4][9] = new inventoryItems("stick");
                inventoryA[4][9].setAmount(4);
            } else if(tl.getName().equals("autumnPlank")&&tr.getName().equals("empty")&&bl.getName().equals("autumnPlank")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new inventoryItems("stick");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("autumnPlank")&&bl.getName().equals("empty")&&br.getName().equals("autumnPlank")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new inventoryItems("stick");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("autumnPlank")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("autumnPlank")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new inventoryItems("stick");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("autumnPlank")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("autumnPlank")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new inventoryItems("stick");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("autumnPlank")&&mrc.getName().equals("autumnPlank")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new inventoryItems("stick");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("autumnPlank")&&brc.getName().equals("autumnPlank")){
                inventoryA[4][9] = new inventoryItems("stick");
                inventoryA[4][9].setAmount(4);
            } else if(tl.getName().equals("fruitPlank")&&tr.getName().equals("empty")&&bl.getName().equals("fruitPlank")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new inventoryItems("stick");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("fruitPlank")&&bl.getName().equals("empty")&&br.getName().equals("fruitPlank")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new inventoryItems("stick");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("fruitPlank")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("fruitPlank")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new inventoryItems("stick");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("fruitPlank")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("fruitPlank")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new inventoryItems("stick");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("fruitPlank")&&mrc.getName().equals("fruitPlank")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new inventoryItems("stick");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("fruitPlank")&&brc.getName().equals("fruitPlank")){
                inventoryA[4][9] = new inventoryItems("stick");
                inventoryA[4][9].setAmount(4);
            }

            ////////////////CRAFTING TABLE
            else if(tl.getName().equals("normalPlank")&&tr.getName().equals("normalPlank")&&bl.getName().equals("normalPlank")&&br.getName().equals("normalPlank")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("craftingTable","axe");
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("normalPlank")&&tr.getName().equals("normalPlank")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("normalPlank")&&tmc.getName().equals("normalPlank")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("craftingTable","axe");
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("normalPlank")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("normalPlank")&&trc.getName().equals("normalPlank")&&mrc.getName().equals("normalPlank")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("craftingTable","axe");
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("normalPlank")&&bl.getName().equals("empty")&&br.getName().equals("normalPlank")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("normalPlank")&&brc.getName().equals("normalPlank")){
                inventoryA[4][9] = new Resources("craftingTable","axe");
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("autumnPlank")&&tr.getName().equals("autumnPlank")&&bl.getName().equals("autumnPlank")&&br.getName().equals("autumnPlank")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("craftingTable","axe");
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("autumnPlank")&&tr.getName().equals("autumnPlank")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("autumnPlank")&&tmc.getName().equals("autumnPlank")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("craftingTable","axe");
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("autumnPlank")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("autumnPlank")&&trc.getName().equals("autumnPlank")&&mrc.getName().equals("autumnPlank")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("craftingTable","axe");
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("autumnPlank")&&bl.getName().equals("empty")&&br.getName().equals("autumnPlank")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("autumnPlank")&&brc.getName().equals("autumnPlank")){
                inventoryA[4][9] = new Resources("craftingTable","axe");
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("fruitPlank")&&tr.getName().equals("fruitPlank")&&bl.getName().equals("fruitPlank")&&br.getName().equals("fruitPlank")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("craftingTable","axe");
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("fruitPlank")&&tr.getName().equals("fruitPlank")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("fruitPlank")&&tmc.getName().equals("fruitPlank")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("craftingTable","axe");
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("fruitPlank")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("fruitPlank")&&trc.getName().equals("fruitPlank")&&mrc.getName().equals("fruitPlank")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("craftingTable","axe");
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("fruitPlank")&&bl.getName().equals("empty")&&br.getName().equals("fruitPlank")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("fruitPlank")&&brc.getName().equals("fruitPlank")){
                inventoryA[4][9] = new Resources("craftingTable","axe");
                inventoryA[4][9].setAmount(1);
            }

            ////////////////////BOAT
            else if(tl.getName().equals("normalPlank")&&tr.getName().equals("normalPlank")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("normalPlank")&&tmc.getName().equals("empty")&&trc.getName().equals("normalPlank")&&mrc.getName().equals("normalPlank")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("boat","axe");
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("normalPlank")&&tr.getName().equals("empty")&&bl.getName().equals("normalPlank")&&br.getName().equals("normalPlank")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("normalPlank")&&brc.getName().equals("normalPlank")){
                inventoryA[4][9] = new Resources("boat","axe");
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("autumnPlank")&&tr.getName().equals("autumnPlank")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("autumnPlank")&&tmc.getName().equals("empty")&&trc.getName().equals("autumnPlank")&&mrc.getName().equals("autumnPlank")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("boat","axe");
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("autumnPlank")&&tr.getName().equals("empty")&&bl.getName().equals("autumnPlank")&&br.getName().equals("autumnPlank")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("autumnPlank")&&brc.getName().equals("autumnPlank")){
                inventoryA[4][9] = new Resources("boat","axe");
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("fruitPlank")&&tr.getName().equals("fruitPlank")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("fruitPlank")&&tmc.getName().equals("empty")&&trc.getName().equals("fruitPlank")&&mrc.getName().equals("fruitPlank")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("boat","axe");
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("fruitPlank")&&tr.getName().equals("empty")&&bl.getName().equals("fruitPlank")&&br.getName().equals("fruitPlank")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("fruitPlank")&&brc.getName().equals("fruitPlank")){
                inventoryA[4][9] = new Resources("boat","axe");
                inventoryA[4][9].setAmount(1);
            }

            /////////FURNACE
            else if(tl.getName().equals("cobblestone")&&tr.getName().equals("empty")&&bl.getName().equals("cobblestone")&&br.getName().equals("cobblestone")&&tlc.getName().equals("cobblestone")&&tmc.getName().equals("cobblestone")&&trc.getName().equals("cobblestone")&&mrc.getName().equals("cobblestone")&&brc.getName().equals("cobblestone")){
                inventoryA[4][9] = new Resources("furnace","pickaxe");
                inventoryA[4][9].setAmount(1);
            }

            /////////SWORDS
            else if(tl.getName().equals("empty")&&tr.getName().equals("normalPlank")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("empty")&&tmc.getName().equals("normalPlank")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("woodSword",1, "sword", 3, 20);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("normalPlank")&&tr.getName().equals("empty")&&bl.getName().equals("stick")&&br.getName().equals("empty")&&tlc.getName().equals("normalPlank")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("woodSword",1, "sword", 3, 20);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("normalPlank")&&mrc.getName().equals("normalPlank")&&brc.getName().equals("stick")){
                inventoryA[4][9] = new Tools("woodSword",1, "sword", 3, 20);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("empty")&&tr.getName().equals("autumnPlank")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("empty")&&tmc.getName().equals("autumnPlank")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("woodSword",1, "sword", 3, 20);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("autumnPlank")&&tr.getName().equals("empty")&&bl.getName().equals("stick")&&br.getName().equals("empty")&&tlc.getName().equals("autumnPlank")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("woodSword",1, "sword", 3, 20);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("autumnPlank")&&mrc.getName().equals("autumnPlank")&&brc.getName().equals("stick")){
                inventoryA[4][9] = new Tools("woodSword",1, "sword", 3, 20);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("empty")&&tr.getName().equals("fruitPlank")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("empty")&&tmc.getName().equals("fruitPlank")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("woodSword",1, "sword", 3, 20);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("fruitPlank")&&tr.getName().equals("empty")&&bl.getName().equals("stick")&&br.getName().equals("empty")&&tlc.getName().equals("fruitPlank")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("woodSword",1, "sword", 3, 20);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("fruitPlank")&&mrc.getName().equals("fruitPlank")&&brc.getName().equals("stick")){
                inventoryA[4][9] = new Tools("woodSword",1, "sword", 3, 20);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("empty")&&tr.getName().equals("cobblestone")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("empty")&&tmc.getName().equals("cobblestone")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("stoneSword",2, "sword", 4, 30);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("cobblestone")&&tr.getName().equals("empty")&&bl.getName().equals("stick")&&br.getName().equals("empty")&&tlc.getName().equals("cobblestone")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("stoneSword",2, "sword", 4, 30);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("cobblestone")&&mrc.getName().equals("cobblestone")&&brc.getName().equals("stick")){
                inventoryA[4][9] = new Tools("stoneSword",2, "sword", 4, 30);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("empty")&&tr.getName().equals("ruby")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("empty")&&tmc.getName().equals("ruby")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("rubySword",3, "sword", 5, 40);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("ruby")&&tr.getName().equals("empty")&&bl.getName().equals("stick")&&br.getName().equals("empty")&&tlc.getName().equals("ruby")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("rubySword",3, "sword", 5, 40);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("ruby")&&mrc.getName().equals("ruby")&&brc.getName().equals("stick")){
                inventoryA[4][9] = new Tools("rubySword",3, "sword", 5, 40);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("empty")&&tr.getName().equals("goldIngot")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("empty")&&tmc.getName().equals("goldIngot")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("goldSword",4, "sword", 6, 50);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("goldIngot")&&tr.getName().equals("empty")&&bl.getName().equals("stick")&&br.getName().equals("empty")&&tlc.getName().equals("goldIngot")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("goldSword",4, "sword", 6, 50);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("goldIngot")&&mrc.getName().equals("goldIngot")&&brc.getName().equals("stick")){
                inventoryA[4][9] = new Tools("goldSword",4, "sword", 6, 50);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("empty")&&tr.getName().equals("diamond")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("empty")&&tmc.getName().equals("diamond")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("diamondSword",5, "sword", 7, 60);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("diamond")&&tr.getName().equals("empty")&&bl.getName().equals("stick")&&br.getName().equals("empty")&&tlc.getName().equals("diamond")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("diamondSword",5, "sword", 7, 60);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("diamond")&&mrc.getName().equals("diamond")&&brc.getName().equals("stick")){
                inventoryA[4][9] = new Tools("diamondSword",5, "sword", 7, 60);
                inventoryA[4][9].setAmount(1);
            }

            //////////////////////AXES

            else if(tl.getName().equals("normalPlank")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("normalPlank")&&tmc.getName().equals("normalPlank")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("woodAxe",1, "axe", 3, 20);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("empty")&&tmc.getName().equals("normalPlank")&&trc.getName().equals("normalPlank")&&mrc.getName().equals("normalPlank")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("woodAxe",1, "axe", 3, 20);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("autumnPlank")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("autumnPlank")&&tmc.getName().equals("autumnPlank")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("woodAxe",1, "axe", 3, 20);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("empty")&&tmc.getName().equals("autumnPlank")&&trc.getName().equals("autumnPlank")&&mrc.getName().equals("autumnPlank")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("woodAxe",1, "axe", 3, 20);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("fruitPlank")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("fruitPlank")&&tmc.getName().equals("fruitPlank")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("woodAxe",1, "axe", 3, 20);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("empty")&&tmc.getName().equals("fruitPlank")&&trc.getName().equals("fruitPlank")&&mrc.getName().equals("fruitPlank")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("woodAxe",1, "axe", 3, 20);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("cobblestone")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("cobblestone")&&tmc.getName().equals("cobblestone")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("stoneAxe",2, "axe", 4, 30);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("empty")&&tmc.getName().equals("cobblestone")&&trc.getName().equals("cobblestone")&&mrc.getName().equals("cobblestone")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("stoneAxe",2, "axe", 4, 30);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("ruby")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("ruby")&&tmc.getName().equals("ruby")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("rubyAxe",3, "axe", 5, 40);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("empty")&&tmc.getName().equals("ruby")&&trc.getName().equals("ruby")&&mrc.getName().equals("ruby")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("rubyAxe",3, "axe", 5, 40);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("goldIngot")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("goldIngot")&&tmc.getName().equals("goldIngot")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("goldAxe",4, "axe", 6, 50);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("empty")&&tmc.getName().equals("goldIngot")&&trc.getName().equals("goldIngot")&&mrc.getName().equals("goldIngot")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("goldAxe",4, "axe", 6, 50);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("diamond")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("diamond")&&tmc.getName().equals("diamond")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("diamondAxe",5, "axe", 7, 60);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("empty")&&tmc.getName().equals("diamond")&&trc.getName().equals("diamond")&&mrc.getName().equals("diamond")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("diamondAxe",5, "axe", 7, 60);
                inventoryA[4][9].setAmount(1);
            }


            //////////////////////PICKAXES
            else if(tl.getName().equals("empty")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("normalPlank")&&tmc.getName().equals("normalPlank")&&trc.getName().equals("normalPlank")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")) {
                inventoryA[4][9] = new Tools("woodPickaxe", 1, "pickaxe", 3, 20);
                inventoryA[4][9].setAmount(1);
            } else if(tl.getName().equals("empty")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("autumnPlank")&&tmc.getName().equals("autumnPlank")&&trc.getName().equals("autumnPlank")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("woodPickaxe",1, "pickaxe", 3, 20);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("fruitPlank")&&tmc.getName().equals("fruitPlank")&&trc.getName().equals("fruitPlank")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("woodPickaxe",1, "pickaxe", 3, 20);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("empty")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("cobblestone")&&tmc.getName().equals("cobblestone")&&trc.getName().equals("cobblestone")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("stonePickaxe",2, "pickaxe", 4, 30);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("empty")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("ruby")&&tmc.getName().equals("ruby")&&trc.getName().equals("ruby")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("rubyPickaxe",3, "pickaxe", 5, 40);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("empty")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("goldIngot")&&tmc.getName().equals("goldIngot")&&trc.getName().equals("goldIngot")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("goldPickaxe",4, "pickaxe", 6, 50);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("empty")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("diamond")&&tmc.getName().equals("diamond")&&trc.getName().equals("diamond")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("diamondPickaxe",5, "pickaxe", 7, 60);
                inventoryA[4][9].setAmount(1);
            }

            ///////////HELMETS
            else if(tl.getName().equals("normalPlank")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("normalPlank")&&tmc.getName().equals("normalPlank")&&trc.getName().equals("normalPlank")&&mrc.getName().equals("normalPlank")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Armor("woodHelmet",1,5,15);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("normalPlank")&&tr.getName().equals("normalPlank")&&bl.getName().equals("normalPlank")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("normalPlank")&&brc.getName().equals("normalPlank")){
                inventoryA[4][9] = new Armor("woodHelmet",1,5,15);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("autumnPlank")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("autumnPlank")&&tmc.getName().equals("autumnPlank")&&trc.getName().equals("autumnPlank")&&mrc.getName().equals("autumnPlank")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Armor("woodHelmet",1,5,15);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("autumnPlank")&&tr.getName().equals("autumnPlank")&&bl.getName().equals("autumnPlank")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("autumnPlank")&&brc.getName().equals("autumnPlank")){
                inventoryA[4][9] = new Armor("woodHelmet",1,5,15);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("fruitPlank")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("fruitPlank")&&tmc.getName().equals("fruitPlank")&&trc.getName().equals("fruitPlank")&&mrc.getName().equals("fruitPlank")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Armor("woodHelmet",1,5,15);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("fruitPlank")&&tr.getName().equals("fruitPlank")&&bl.getName().equals("fruitPlank")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("fruitPlank")&&brc.getName().equals("fruitPlank")){
                inventoryA[4][9] = new Armor("woodHelmet",1,5,15);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("ruby")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("ruby")&&tmc.getName().equals("ruby")&&trc.getName().equals("ruby")&&mrc.getName().equals("ruby")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Armor("rubyHelmet",2,5,20);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("ruby")&&tr.getName().equals("ruby")&&bl.getName().equals("ruby")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("ruby")&&brc.getName().equals("ruby")){
                inventoryA[4][9] = new Armor("rubyHelmet",2,5,20);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("goldIngot")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("goldIngot")&&tmc.getName().equals("goldIngot")&&trc.getName().equals("goldIngot")&&mrc.getName().equals("goldIngot")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Armor("goldHelmet",3,5,13);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("goldIngot")&&tr.getName().equals("goldIngot")&&bl.getName().equals("goldIngot")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("goldIngot")&&brc.getName().equals("goldIngot")){
                inventoryA[4][9] = new Armor("goldHelmet",3,5,13);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("diamond")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("diamond")&&tmc.getName().equals("diamond")&&trc.getName().equals("diamond")&&mrc.getName().equals("diamond")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Armor("diamondHelmet",4,5,30);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("diamond")&&tr.getName().equals("diamond")&&bl.getName().equals("diamond")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("diamond")&&brc.getName().equals("diamond")){
                inventoryA[4][9] = new Armor("diamondHelmet",4,5,30);
                inventoryA[4][9].setAmount(1);
            }

            ////////////CHESTPLATES
            else if(tl.getName().equals("normalPlank")&&tr.getName().equals("normalPlank")&&bl.getName().equals("normalPlank")&&br.getName().equals("normalPlank")&&tlc.getName().equals("normalPlank")&&tmc.getName().equals("empty")&&trc.getName().equals("normalPlank")&&mrc.getName().equals("normalPlank")&&brc.getName().equals("normalPlank")){
                inventoryA[4][9] = new Armor("woodChestplate",1,10,25);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("autumnPlank")&&tr.getName().equals("autumnPlank")&&bl.getName().equals("autumnPlank")&&br.getName().equals("autumnPlank")&&tlc.getName().equals("autumnPlank")&&tmc.getName().equals("empty")&&trc.getName().equals("autumnPlank")&&mrc.getName().equals("autumnPlank")&&brc.getName().equals("autumnPlank")){
                inventoryA[4][9] = new Armor("woodChestplate",1,10,25);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("fruitPlank")&&tr.getName().equals("fruitPlank")&&bl.getName().equals("fruitPlank")&&br.getName().equals("fruitPlank")&&tlc.getName().equals("fruitPlank")&&tmc.getName().equals("empty")&&trc.getName().equals("fruitPlank")&&mrc.getName().equals("fruitPlank")&&brc.getName().equals("fruitPlank")){
                inventoryA[4][9] = new Armor("woodChestplate",1,10,25);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("ruby")&&tr.getName().equals("ruby")&&bl.getName().equals("ruby")&&br.getName().equals("ruby")&&tlc.getName().equals("ruby")&&tmc.getName().equals("empty")&&trc.getName().equals("ruby")&&mrc.getName().equals("ruby")&&brc.getName().equals("ruby")){
                inventoryA[4][9] = new Armor("rubyChestplate",2,10,30);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("goldIngot")&&tr.getName().equals("goldIngot")&&bl.getName().equals("goldIngot")&&br.getName().equals("goldIngot")&&tlc.getName().equals("goldIngot")&&tmc.getName().equals("empty")&&trc.getName().equals("goldIngot")&&mrc.getName().equals("goldIngot")&&brc.getName().equals("goldIngot")){
                inventoryA[4][9] = new Armor("goldChestplate",3,10,18);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("diamond")&&tr.getName().equals("diamond")&&bl.getName().equals("diamond")&&br.getName().equals("diamond")&&tlc.getName().equals("diamond")&&tmc.getName().equals("empty")&&trc.getName().equals("diamond")&&mrc.getName().equals("diamond")&&brc.getName().equals("diamond")){
                inventoryA[4][9] = new Armor("diamondChestplate",4,10,40);
                inventoryA[4][9].setAmount(1);
            }

            ////////////LEGGINGS
            else if(tl.getName().equals("normalPlank")&&tr.getName().equals("empty")&&bl.getName().equals("normalPlank")&&br.getName().equals("empty")&&tlc.getName().equals("normalPlank")&&tmc.getName().equals("normalPlank")&&trc.getName().equals("normalPlank")&&mrc.getName().equals("normalPlank")&&brc.getName().equals("normalPlank")){
                inventoryA[4][9] = new Armor("woodLeggings",1,10,25);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("autumnPlank")&&tr.getName().equals("empty")&&bl.getName().equals("autumnPlank")&&br.getName().equals("empty")&&tlc.getName().equals("autumnPlank")&&tmc.getName().equals("autumnPlank")&&trc.getName().equals("autumnPlank")&&mrc.getName().equals("autumnPlank")&&brc.getName().equals("autumnPlank")){
                inventoryA[4][9] = new Armor("woodLeggings",1,10,25);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("fruitPlank")&&tr.getName().equals("empty")&&bl.getName().equals("fruitPlank")&&br.getName().equals("empty")&&tlc.getName().equals("fruitPlank")&&tmc.getName().equals("fruitPlank")&&trc.getName().equals("fruitPlank")&&mrc.getName().equals("fruitPlank")&&brc.getName().equals("fruitPlank")){
                inventoryA[4][9] = new Armor("woodLeggings",1,10,25);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("ruby")&&tr.getName().equals("empty")&&bl.getName().equals("ruby")&&br.getName().equals("empty")&&tlc.getName().equals("ruby")&&tmc.getName().equals("ruby")&&trc.getName().equals("ruby")&&mrc.getName().equals("ruby")&&brc.getName().equals("ruby")){
                inventoryA[4][9] = new Armor("rubyLeggings",2,10,30);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("goldIngot")&&tr.getName().equals("empty")&&bl.getName().equals("goldIngot")&&br.getName().equals("empty")&&tlc.getName().equals("goldIngot")&&tmc.getName().equals("goldIngot")&&trc.getName().equals("goldIngot")&&mrc.getName().equals("goldIngot")&&brc.getName().equals("goldIngot")){
                inventoryA[4][9] = new Armor("goldLeggings",3,10,18);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("diamond")&&tr.getName().equals("empty")&&bl.getName().equals("diamond")&&br.getName().equals("empty")&&tlc.getName().equals("diamond")&&tmc.getName().equals("diamond")&&trc.getName().equals("diamond")&&mrc.getName().equals("diamond")&&brc.getName().equals("diamond")){
                inventoryA[4][9] = new Armor("diamondLeggings",4,10,40);
                inventoryA[4][9].setAmount(1);
            }

            ///////////////BOOTS
            else if(tl.getName().equals("normalPlank")&&tr.getName().equals("empty")&&bl.getName().equals("normalPlank")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("normalPlank")&&brc.getName().equals("normalPlank")){
                inventoryA[4][9] = new Armor("woodBoots",1,5,15);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("normalPlank")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("normalPlank")&&tmc.getName().equals("empty")&&trc.getName().equals("normalPlank")&&mrc.getName().equals("normalPlank")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Armor("woodBoots",1,5,15);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("autumnPlank")&&tr.getName().equals("empty")&&bl.getName().equals("autumnPlank")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("autumnPlank")&&brc.getName().equals("autumnPlank")){
                inventoryA[4][9] = new Armor("woodBoots",1,5,15);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("autumnPlank")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("autumnPlank")&&tmc.getName().equals("empty")&&trc.getName().equals("autumnPlank")&&mrc.getName().equals("autumnPlank")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Armor("woodBoots",1,5,15);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("fruitPlank")&&tr.getName().equals("empty")&&bl.getName().equals("fruitPlank")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("fruitPlank")&&brc.getName().equals("fruitPlank")){
                inventoryA[4][9] = new Armor("woodBoots",1,5,15);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("fruitPlank")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("fruitPlank")&&tmc.getName().equals("empty")&&trc.getName().equals("fruitPlank")&&mrc.getName().equals("fruitPlank")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Armor("woodBoots",1,5,15);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("ruby")&&tr.getName().equals("empty")&&bl.getName().equals("ruby")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("ruby")&&brc.getName().equals("ruby")){
                inventoryA[4][9] = new Armor("rubyBoots",2,5,20);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("ruby")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("ruby")&&tmc.getName().equals("empty")&&trc.getName().equals("ruby")&&mrc.getName().equals("ruby")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Armor("rubyBoots",2,5,20);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("goldIngot")&&tr.getName().equals("empty")&&bl.getName().equals("goldIngot")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("goldIngot")&&brc.getName().equals("goldIngot")){
                inventoryA[4][9] = new Armor("goldBoots",3,5,13);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("goldIngot")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("goldIngot")&&tmc.getName().equals("empty")&&trc.getName().equals("goldIngot")&&mrc.getName().equals("goldIngot")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Armor("goldBoots",3,5,13);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("diamond")&&tr.getName().equals("empty")&&bl.getName().equals("diamond")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("diamond")&&brc.getName().equals("diamond")){
                inventoryA[4][9] = new Armor("diamondBoots",4,5,30);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("diamond")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("diamond")&&tmc.getName().equals("empty")&&trc.getName().equals("diamond")&&mrc.getName().equals("diamond")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Armor("diamondBoots",4,5,30);
                inventoryA[4][9].setAmount(1);
            }














            else{
                inventoryA[4][9] = new inventoryItems("empty");
                inventoryA[4][9].setAmount(0);
            }
        }






        updateScreen();
    }

    private void interactTwo(){
        int directionChange = switch (directionInter) {
            case "up" -> -1;
            case "down" -> 1;
            case "right" -> 1;
            case "left" -> -1;
            default -> 0;
        };
        if (directionInter.equals("up") || directionInter.equals("down")) {
            if ("craftingTable".equals(map[playerPositionX + directionChange][playerPositionY])) {
                if (inventoryShowing) {
                    gPane.setVisible(true);
                    hotbarG.setVisible(true);
                    inventoryPane.setVisible(false);
                    inventoryShowing = false;
                    craftingShowing = false;
                    for (int i = 0; i < inventoryLabels.length; i++) {
                        for (int j = 0; j < inventoryLabels[0].length; j++) {
                            inventoryLabels[i][j].setVisible(true);
                            inventoryLabels[i][j].setVisible(false);
                            one1c.setVisible(false);
                            one2c.setVisible(false);
                            one3cv.setVisible(false);
                            two1c.setVisible(false);
                            two2c.setVisible(false);
                            two3cv.setVisible(false);
                            three1cv.setVisible(false);
                            three2cv.setVisible(false);
                            three3cv.setVisible(false);
                            result.setVisible(false);
                        }
                    }

                    
                    inventoryImg[0][9].setImage(blackBack);
                    inventoryImg[0][10].setImage(blackBack);
                    inventoryImg[0][11].setImage(blackBack);
                    inventoryImg[1][11].setImage(blackBack);
                    inventoryImg[2][11].setImage(blackBack);

                    inventoryA[0][9] = new inventoryItems("nothing");
                    inventoryA[0][10]= new inventoryItems("nothing");
                    inventoryA[0][11]= new inventoryItems("nothing");
                    inventoryA[1][11]= new inventoryItems("nothing");
                    inventoryA[2][11]= new inventoryItems("nothing");
                } else {
                    gPane.setVisible(false);
                    hotbarG.setVisible(false);
                    inventoryPane.setVisible(true);
                    inventoryShowing = true;
                    craftingShowing = true;
                    for (int i = 0; i < inventoryLabels.length; i++) {
                        for (int j = 0; j < inventoryLabels[0].length; j++) {
                            inventoryLabels[i][j].setVisible(true);
                            one1c.setVisible(true);
                            one2c.setVisible(true);
                            one3cv.setVisible(true);
                            two1c.setVisible(true);
                            two2c.setVisible(true);
                            two3cv.setVisible(true);
                            three1cv.setVisible(true);
                            three2cv.setVisible(true);
                            three3cv.setVisible(true);
                            result.setVisible(true);
                        }
                    }

                    inventoryImg[0][9].setImage(grayBack);
                    inventoryImg[0][10].setImage(grayBack);
                    inventoryImg[0][11].setImage(grayBack);
                    inventoryImg[1][11].setImage(grayBack);
                    inventoryImg[2][11].setImage(grayBack);

                    inventoryA[0][9] = new inventoryItems("empty");
                    inventoryA[0][10]= new inventoryItems("empty");
                    inventoryA[0][11]= new inventoryItems("empty");
                    inventoryA[1][11]= new inventoryItems("empty");
                    inventoryA[2][11]= new inventoryItems("empty");
                }
            }


            else if("furnace".equals(map[playerPositionX+directionChange][playerPositionY])){
                System.out.println("FURNACEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
                if (inventoryShowing) {
                    gPane.setVisible(true);
                    hotbarG.setVisible(true);
                    inventoryPane.setVisible(false);
                    inventoryShowing = false;
                    furnaceShowing = false;
                    for (int i = 0; i < inventoryLabels.length; i++) {
                        for (int j = 0; j < inventoryLabels[0].length; j++) {
                            inventoryLabels[i][j].setVisible(false);
                            two1c.setVisible(false);
                            furnaceTop.setVisible(false);
                            furnaceBottom.setVisible(false);
                        }
                    }

                    inventoryImg[1][9].setImage(grayBack);
                    inventoryImg[1][10].setImage(grayBack);
                    inventoryImg[2][10].setImage(grayBack);
                    inventoryImg[4][9].setImage(grayBack);
                    inventoryImg[2][7].setImage(grayBack);
                    inventoryImg[4][7].setImage(grayBack);

                    inventoryA[1][9] = new inventoryItems("empty");
                    inventoryA[1][10] = new inventoryItems("empty");
                    inventoryA[2][10] = new inventoryItems("empty");
                    inventoryA[4][9] = new inventoryItems("empty");
                    inventoryA[2][7] = new inventoryItems("empty");
                    inventoryA[4][7] = new inventoryItems("empty");

                }else{
                    gPane.setVisible(false);
                    hotbarG.setVisible(false);
                    inventoryPane.setVisible(true);
                    inventoryShowing = true;
                    furnaceShowing=true;

                    for (int i = 0; i < inventoryLabels.length; i++) {
                        for (int j = 0; j < inventoryLabels[0].length; j++) {
                            inventoryLabels[i][j].setVisible(true);
                            two1c.setVisible(true);
                            furnaceTop.setVisible(true);
                            furnaceBottom.setVisible(true);
                        }
                    }


                    inventoryImg[1][9].setImage(blackBack);
                    inventoryImg[1][10].setImage(blackBack);
                    inventoryImg[2][10].setImage(blackBack);
                    inventoryImg[4][9].setImage(blackBack);
                    inventoryImg[2][7].setImage(blackBack);
                    inventoryImg[4][7].setImage(blackBack);

                    inventoryA[1][9] = new inventoryItems("nothing");
                    inventoryA[1][10] = new inventoryItems("nothing");
                    inventoryA[2][10] = new inventoryItems("nothing");
                    inventoryA[4][9] = new inventoryItems("nothing");
                    inventoryA[2][7] = new inventoryItems("nothing");
                    inventoryA[4][7] = new inventoryItems("nothing");
                }
            }

        }
        else{
            if("craftingTable".equals(map[playerPositionX][playerPositionY + directionChange])) {
                if (inventoryShowing) {
                    gPane.setVisible(true);
                    hotbarG.setVisible(true);
                    inventoryPane.setVisible(false);
                    inventoryShowing = false;
                    craftingShowing = false;


                    for (int i = 0; i < inventoryLabels.length; i++) {
                        for (int j = 0; j < inventoryLabels[0].length; j++) {
                            inventoryLabels[i][j].setVisible(true);
                            inventoryLabels[i][j].setVisible(false);
                            one1c.setVisible(false);
                            one2c.setVisible(false);
                            one3cv.setVisible(false);
                            two1c.setVisible(false);
                            two2c.setVisible(false);
                            two3cv.setVisible(false);
                            three1cv.setVisible(false);
                            three2cv.setVisible(false);
                            three3cv.setVisible(false);
                            result.setVisible(false);
                        }
                    }

                    inventoryImg[0][9].setImage(blackBack);
                    inventoryImg[0][10].setImage(blackBack);
                    inventoryImg[0][11].setImage(blackBack);
                    inventoryImg[1][11].setImage(blackBack);
                    inventoryImg[2][11].setImage(blackBack);

                    inventoryA[0][9] = new inventoryItems("nothing");
                    inventoryA[0][10]= new inventoryItems("nothing");
                    inventoryA[0][11]= new inventoryItems("nothing");
                    inventoryA[1][11]= new inventoryItems("nothing");
                    inventoryA[2][11]= new inventoryItems("nothing");
                } else {
                    gPane.setVisible(false);
                    hotbarG.setVisible(false);
                    inventoryPane.setVisible(true);
                    inventoryShowing = true;
                    craftingShowing = true;
                    for (int i = 0; i < inventoryLabels.length; i++) {
                        for (int j = 0; j < inventoryLabels[0].length; j++) {
                            inventoryLabels[i][j].setVisible(true);
                            one1c.setVisible(true);
                            one2c.setVisible(true);
                            one3cv.setVisible(true);
                            two1c.setVisible(true);
                            two2c.setVisible(true);
                            two3cv.setVisible(true);
                            three1cv.setVisible(true);
                            three2cv.setVisible(true);
                            three3cv.setVisible(true);
                            result.setVisible(true);
                        }
                    }

                    inventoryImg[0][9].setImage(grayBack);
                    inventoryImg[0][10].setImage(grayBack);
                    inventoryImg[0][11].setImage(grayBack);
                    inventoryImg[1][11].setImage(grayBack);
                    inventoryImg[2][11].setImage(grayBack);

                    inventoryA[0][9] = new inventoryItems("empty");
                    inventoryA[0][10]= new inventoryItems("empty");
                    inventoryA[0][11]= new inventoryItems("empty");
                    inventoryA[1][11]= new inventoryItems("empty");
                    inventoryA[2][11]= new inventoryItems("empty");
                }
            }
            else if("furnace".equals(map[playerPositionX][playerPositionY + directionChange])){

                if (inventoryShowing) {
                    gPane.setVisible(true);
                    hotbarG.setVisible(true);
                    inventoryPane.setVisible(false);
                    inventoryShowing = false;
                    furnaceShowing = false;

                    for (int i = 0; i < inventoryLabels.length; i++) {
                        for (int j = 0; j < inventoryLabels[0].length; j++) {
                            inventoryLabels[i][j].setVisible(false);
                            two1c.setVisible(false);
                            furnaceTop.setVisible(false);
                            furnaceBottom.setVisible(false);
                        }
                    }

                    inventoryImg[1][9].setImage(grayBack);
                    inventoryImg[1][10].setImage(grayBack);
                    inventoryImg[2][10].setImage(grayBack);
                    inventoryImg[4][9].setImage(grayBack);
                    inventoryImg[2][7].setImage(grayBack);
                    inventoryImg[4][7].setImage(grayBack);

                    inventoryA[1][9] = new inventoryItems("empty");
                    inventoryA[1][10] = new inventoryItems("empty");
                    inventoryA[2][10] = new inventoryItems("empty");
                    inventoryA[4][9] = new inventoryItems("empty");
                    inventoryA[2][7] = new inventoryItems("empty");
                    inventoryA[4][7] = new inventoryItems("empty");
                }else{
                    gPane.setVisible(false);
                    hotbarG.setVisible(false);
                    inventoryPane.setVisible(true);
                    inventoryShowing = true;
                    furnaceShowing = true;

                    for (int i = 0; i < inventoryLabels.length; i++) {
                        for (int j = 0; j < inventoryLabels[0].length; j++) {
                            inventoryLabels[i][j].setVisible(true);
                            two1c.setVisible(true);
                            furnaceTop.setVisible(true);
                            furnaceBottom.setVisible(true);
                        }
                    }

                    inventoryImg[1][9].setImage(blackBack);
                    inventoryImg[1][10].setImage(blackBack);
                    inventoryImg[2][10].setImage(blackBack);
                    inventoryImg[4][9].setImage(blackBack);
                    inventoryImg[2][7].setImage(blackBack);
                    inventoryImg[4][7].setImage(blackBack);

                    inventoryA[1][9] = new inventoryItems("nothing");
                    inventoryA[1][10] = new inventoryItems("nothing");
                    inventoryA[2][10] = new inventoryItems("nothing");
                    inventoryA[4][9] = new inventoryItems("nothing");
                    inventoryA[2][7] = new inventoryItems("nothing");
                    inventoryA[4][7] = new inventoryItems("nothing");
                }
            }
        }



        }


    public void onKeyPressed(KeyEvent keyEvent) {
        coordsLabel.setText("X: " + playerPositionX + "\nY: " + playerPositionY);
        if(!miningObject&&!eatingFood) {
            if (!inventoryShowing) {
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
            if (keyEvent.getText().equalsIgnoreCase("r")) {
                interactTwo();
            }
            if(!craftingShowing) {
                if (keyEvent.getText().equalsIgnoreCase("q")) {
                    if (inventoryShowing) {
                        gPane.setVisible(true);
                        hotbarG.setVisible(true);
                        inventoryPane.setVisible(false);
                        inventoryShowing = false;
                    for (int i = 0; i < inventoryLabels.length; i++) {
                        for (int j = 0; j < inventoryLabels[0].length; j++) {
                            inventoryLabels[i][j].setVisible(false);
                            one1c.setVisible(false);
                            one2c.setVisible(false);
                            two1c.setVisible(false);
                            two2c.setVisible(false);
                            result.setVisible(false);
                        }
                    }
                    
                    
                    } else {
                        gPane.setVisible(false);
                        hotbarG.setVisible(false);
                        inventoryPane.setVisible(true);
                        inventoryShowing = true;
                    for (int i = 0; i < inventoryLabels.length; i++) {
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
            }
            if (keyEvent.getText().equalsIgnoreCase("1")) {
                selected = 0;
                slot1.setVisible(true);
                slot2.setVisible(false);
                slot3.setVisible(false);
                slot4.setVisible(false);
                slot5.setVisible(false);
            } else if (keyEvent.getText().equalsIgnoreCase("2")) {
                selected = 1;
                slot1.setVisible(false);
                slot2.setVisible(true);
                slot3.setVisible(false);
                slot4.setVisible(false);
                slot5.setVisible(false);
            } else if (keyEvent.getText().equalsIgnoreCase("3")) {
                selected = 2;
                slot1.setVisible(false);
                slot2.setVisible(false);
                slot3.setVisible(true);
                slot4.setVisible(false);
                slot5.setVisible(false);
            } else if (keyEvent.getText().equalsIgnoreCase("4")) {
                selected = 3;
                slot1.setVisible(false);
                slot2.setVisible(false);
                slot3.setVisible(false);
                slot4.setVisible(true);
                slot5.setVisible(false);
            } else if (keyEvent.getText().equalsIgnoreCase("5")) {
                selected = 4;
                slot1.setVisible(false);
                slot2.setVisible(false);
                slot3.setVisible(false);
                slot4.setVisible(false);
                slot5.setVisible(true);
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

                    if(now-miningTime>1000000000.0/toolBoost){
                        miningTime = System.nanoTime();
                        tempMineTime--;
                        miningBar.setProgress((double) tempMineTime/tempMine.getMineTime());
                        if(tempMineTime < 1)   {
                            toolBoost =1 ;
                            ///////TEMPORARY not anymore?
                            if(mapBackground[miningX][miningY].equals("grass")||mapBackground[miningX][miningY].equals("normal")||mapBackground[miningX][miningY].equals("fruit")||mapBackground[miningX][miningY].equals("autumn")) {
                                map[miningX][miningY] = "grass";
                            }else{
                                System.out.println(tempMine.getName());
                                map[miningX][miningY] = "stone";
                            }
                            miningObject = false;
                            miningBar.setVisible(false);
                            firstMine = false;
                            mineObjectsOnMap.remove(tempMine);
                            breakB = false;
                            for (int i = 4; i >=1; i--) {
                                for (int j = 1; j <=5; j++) {
                                    if(inventoryA[i][j].getName().equals(tempMine.getResourceDrop().getName())){
                                        System.out.println("hi");
                                        inventoryA[i][j].changeAmount(tempMine.getAmountDrop());
                                        if(tempMine.getAmountDropSecond()!= 0){
                                            for (int m = 4; m >=1; m--) {
                                                for (int k = 1; k <=5; k++) {
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
                                                for (int k = 1; k <=5; k++) {
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
                                for (int j = 1; j <=5; j++) {
                                    if(inventoryA[i][j].getName().equals("empty")){
                                        inventoryA[i][j] = tempMine.getResourceDrop();
                                        inventoryA[i][j].setAmount(tempMine.getAmountDrop());
                                        if(tempMine.getAmountDropSecond()!= 0){
                                            for (int m = 4; m >=1; m--) {
                                                for (int k = 1; k <=5; k++) {
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
                                                for (int k = 1; k <=5; k++) {
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

                if(eatingFood){
                    if(now-eatingTime>1000000000.0/2){
                        eatingTime = System.nanoTime();
                        eatingCount--;
                        miningBar.setProgress((double) eatingCount/6);

                        if(eatingCount<=0){
                            eatingCount = 6;
                            miningBar.setVisible(false);
                            eatingFood = false;

                            tempHunger+= equipped.getHungerGain();
                            if(tempHunger>100){
                                tempHunger = 100;
                            }
                            if(equipped.getAmount()>1){
                                equipped.changeAmount(-1);
                            }else{
                                inventoryA[4][selected+1] = new inventoryItems("empty");
                                System.out.println(inventoryA[4][selected+1].getName());
                                updateScreen();
                                System.out.println(hotbar[selected].getName());
                                System.out.println(equipped.getName());
                            }
                            hungerBar.setProgress(tempHunger/totalHunger);
                        }
                    }
                }

                if(animalsOnMap.size()>0){
                    for(Animals animal:animalsOnMap){
                        if(now - animal.getStartTime() > 1000000000.0 * 1.5){
                            if(animal.getMovementTime()<0){
                                animal.changeLoc(map);
                                animal.resetStartTime();
                            }else{
                                animal.changeMovementTime(-1);
                            }


                        }
                    }
                    updateScreen();
                }



                if(day){
                    if(now-dayNightTime>1000000000.0) {
                        dayNightTime = System.nanoTime();
                        dayTime--;
                        dayNightBar.setProgress((double) dayTime / totalDayTime);
                        if (dayTime <= 0) {
                            day = false;
                            dayNightBar.setStyle(" -fx-accent: black; ");
                            dayNightLbl.setText("Night Time");
                            dayTime = totalDayTime;
                        }
                    }
                }else{
                    if(now-dayNightTime>1000000000.0) {
                        dayNightTime = System.nanoTime();
                        nightTime--;
                        dayNightBar.setProgress((double) nightTime / totalNightTime);
                        int ranNum = (int)(Math.random()*5);
                        if(ranNum==0){
                            while(true){
                                int ranX = (int)(Math.random()*31)+playerPositionX-15;
                                int ranY = (int)(Math.random()*31)+playerPositionY-15;
                                if(map[ranX][ranY].equals("grass")){
                                    mobsNoCreepersOnMap.add(new mobsNoCreeper("zombieOverGrass",30,new Food("rottenFlesh",5),(int)(Math.random()*15),1.25,(int) (Math.random()*3)+1,10,ranX,ranY));
                                    break;
                                } else if (map[ranX][ranY].equals("Stone")) {
                                    mobsNoCreepersOnMap.add(new mobsNoCreeper("zombieOverStone",30,new Food("rottenFlesh",5),(int)(Math.random()*15),1.25,(int) (Math.random()*3)+1,10,ranX,ranY));
                                    break;
                                }
                            }
                        }
                        if (nightTime <= 0) {
                            dayNightLbl.setText("Day Time");
                            day = true;
                            dayNightBar.setStyle(" -fx-accent: orange; ");
                            nightTime = totalNightTime;
                        }
                    }
                }

                if(mobsNoCreepersOnMap.size()>0){
                    for(mobsNoCreeper mobs:mobsNoCreepersOnMap){
                        if(now - mobs.getStartTime() > 1000000000.0 * mobs.getSpeed()){
                            if(mobs.getMovementTime()<0){
                                mobs.changeLoc(map,mapBackground,playerPositionX,playerPositionY,tempHealth,tempOverHealth);
                                tempHealth  = mobs.getPlayerHealth();
                                healthBar.setProgress(tempHealth/totalHealth);
                                mobs.resetStartTime();
                            }else{
                                mobs.changeMovementTime(-1);
                            }


                        }
                    }
                    updateScreen();
                }

                if(now - regenTime>1000000000.0 * 2.5){
                    regenTime = System.nanoTime();
                    if(tempHunger>=85){
                        tempHealth+= 5;
                        if(tempHealth>100){
                            tempHealth = 100;
                        }
                        healthBar.setProgress(tempHealth/totalHealth);
                    }
                }

                if(swing) {
                    if (now - swingTime > 1000000000.0 * 0.2) {
                        swingCount--;
                        swingBar.setProgress(swingCount/4);
                        swingTime = System.nanoTime();
                        if(swingCount<=0) {
                            swing = false;
                            swingCount = 3;
                            swingBar.setVisible(false);
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
                case "fruitWood":
                case "autumnWood":
                case "fruitPlank":
                case "normalPlank":
                case "autumnPlank":
                case "boat":
                case "craftingTable":
                case "furnace":


                    miningObject = true;
                    miningX = playerPositionX + directionChange;
                    miningY = playerPositionY;
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
//                    firstMine = true;
                    System.out.println(equipped.getName());
                    System.out.println(equipped.getTier());
                    if(equipped.getTier()>0){
                        System.out.println(tempMine.getType());
                        if(tempMine.getType().equals(equipped.getType())){
                            toolBoost = equipped.getTier() +(equipped.getTier()*0.6);
                            System.out.println(toolBoost);
                        }
                    }
//                    map[playerPositionX + directionChange][playerPositionY] = "grass";
                    break;
                case "grass", "stone":
                    if(!equipped.getName().equals("empty")&&equipped.isPlaceable()){
                        int mineTime;
                        String type;
                        if(equipped.getType().equals("pickaxe")){
                            mineTime = (int) (Math.random() * 6) + 10;
                            type = "pickaxe";
                        }else{
                            mineTime = (int) (Math.random() * 5) + 5;
                            type = "axe";
                        }

                        map[playerPositionX+ directionChange][playerPositionY ] = equipped.getName();
                        System.out.println(map[playerPositionX+ directionChange][playerPositionY ]);
                        mineObjectsOnMap.add(new mineObjects(equipped.getName(),equipped.getType(), mineTime, new Resources(equipped.getName(),type), 1, playerPositionX+ directionChange, playerPositionY ));
                        if(equipped.getAmount()>1){
                            equipped.changeAmount(-1);
                        }else{
                            inventoryA[4][selected+1] = new inventoryItems("empty");
                            System.out.println(inventoryA[4][selected+1].getName());
                            updateScreen();
                            System.out.println(hotbar[selected].getName());
                            System.out.println(equipped.getName());
                        }
                    } else if (equipped.isEatable()) {
                        eatingFood = true;
                        miningBar.setVisible(true);
                        miningBar.setProgress(1.0);
                    }
                    break;
                case "sheep","cow","pig":
                    if(!swing) {
                        swing = true;
                        swingTime = System.nanoTime();
                        swingBar.setVisible(true);
                        swingBar.setProgress(swingCount/4);
                        int damage = 1;
                        if (equipped.getName().endsWith("Axe") || equipped.getName().endsWith("Pickaxe") || equipped.getName().endsWith("Sword")) {
                            damage = equipped.getDamage();
                        }
                        for (Animals animal : animalsOnMap) {
                            if (animal.getX() == playerPositionX + directionChange && animal.getY() == playerPositionY) {
                                animal.changeHealth(-(damage));
                                if (animal.getHealth() <= 0) {
                                    map[playerPositionX + directionChange][playerPositionY] = "grass";
                                    breakB = false;
                                    for (int i = 4; i >= 1; i--) {
                                        for (int j = 1; j <= 5; j++) {
                                            if (inventoryA[i][j].getName().equals(animal.getResourceDrop().getName())) {
                                                System.out.println("hi");
                                                inventoryA[i][j].changeAmount(animal.getAmountDrop());
                                                breakB = true;
                                                break;
                                            }
                                        }
                                        if (breakB) {
                                            break;
                                        }
                                    }


                                    for (int i = 4; i >= 1; i--) {
                                        if (breakB) {
                                            breakB = false;
                                            break;
                                        }
                                        for (int j = 1; j <= 5; j++) {
                                            if (inventoryA[i][j].getName().equals("empty")) {
                                                inventoryA[i][j] = animal.getResourceDrop();
                                                inventoryA[i][j].setAmount(animal.getAmountDrop());
                                                breakB = true;
                                                break;
                                            }
                                        }
                                        if (breakB) {
                                            breakB = false;
                                            break;
                                        }
                                    }


                                    animalsOnMap.remove(animal);
                                    break;
                                } else {
                                    animal.changeLoc(map);
                                    updateScreen();
                                }
                            }
                        }
                    }
                    break;
                case "zombieOverGrass","zombieOverStone":
                    if(!swing) {
                        swing = true;
                        swingTime = System.nanoTime();
                        swingBar.setVisible(true);
                        swingBar.setProgress(swingCount/4);
                        int damage = 1;
                        if (equipped.getName().endsWith("Axe") || equipped.getName().endsWith("Pickaxe") || equipped.getName().endsWith("Sword")) {
                            damage = equipped.getDamage();
                        }
                        for (mobsNoCreeper mobs : mobsNoCreepersOnMap) {
                            if (mobs.getX() == playerPositionX + directionChange && mobs.getY() == playerPositionY) {
                                mobs.changeHealth(-(damage));
                                if (mobs.getHealth() <= 0) {
                                    if (mapBackground[playerPositionX + directionChange][playerPositionY].equals("grass") || mapBackground[playerPositionX + directionChange][playerPositionY].equals("normal") || mapBackground[playerPositionX + directionChange][playerPositionY].equals("fruit") || mapBackground[playerPositionX + directionChange][playerPositionY].equals("autumn")) {
                                        map[playerPositionX + directionChange][playerPositionY] = "grass";
                                    } else {
                                        map[playerPositionX + directionChange][playerPositionY] = "stone";
                                    }
                                    breakB = false;
                                    for (int i = 4; i >= 1; i--) {
                                        for (int j = 1; j <= 5; j++) {
                                            if (inventoryA[i][j].getName().equals(mobs.getResourceDrop().getName())) {
                                                System.out.println("hi");
                                                inventoryA[i][j].changeAmount(mobs.getAmountDrop());
                                                breakB = true;
                                                break;
                                            }
                                        }
                                        if (breakB) {
                                            break;
                                        }
                                    }


                                    for (int i = 4; i >= 1; i--) {
                                        if (breakB) {
                                            breakB = false;
                                            break;
                                        }
                                        for (int j = 1; j <= 5; j++) {
                                            if (inventoryA[i][j].getName().equals("empty")) {
                                                inventoryA[i][j] = mobs.getResourceDrop();
                                                inventoryA[i][j].setAmount(mobs.getAmountDrop());
                                                breakB = true;
                                                break;
                                            }
                                        }
                                        if (breakB) {
                                            breakB = false;
                                            break;
                                        }
                                    }


                                    mobsNoCreepersOnMap.remove(mobs);
                                    break;
                                }
                            }
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
                case "fruitWood":
                case "autumnWood":
                case "fruitPlank":
                case "normalPlank":
                case "autumnPlank":
                case "boat":
                case "craftingTable":
                case "furnace":


                    miningObject = true;
                    miningX = playerPositionX;
                    miningY = playerPositionY + directionChange;
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
//                    firstMine = true;
                    System.out.println(equipped.getName());
                    System.out.println(equipped.getTier());
                    if(equipped.getTier()>0){
                        System.out.println(tempMine.getType());
                        if(tempMine.getType().equals(equipped.getType())){
                            toolBoost = equipped.getTier() +(equipped.getTier()*0.6);
                            System.out.println(toolBoost);
                        }
                    }
//                    map[playerPositionX][playerPositionY + directionChange] = "grass";
                    break;
                case "grass", "stone":
                    if(!equipped.getName().equals("empty")&&equipped.isPlaceable()){
                        int mineTime;
                        String type;
                        if(equipped.getType().equals("pickaxe")){
                            mineTime = (int) (Math.random() * 6) + 10;
                            type = "pickaxe";
                        }else{
                            mineTime = (int) (Math.random() * 5) + 5;
                            type = "axe";
                        }

                        map[playerPositionX][playerPositionY + directionChange] = equipped.getName();
                        System.out.println(map[playerPositionX][playerPositionY + directionChange]);
                        mineObjectsOnMap.add(new mineObjects(equipped.getName(),equipped.getType(), mineTime, new Resources(equipped.getName(),type), 1, playerPositionX, playerPositionY + directionChange));
                        if(equipped.getAmount()>1){
                            equipped.changeAmount(-1);
                        }else{
                            inventoryA[4][selected+1] = new inventoryItems("empty");
                            System.out.println(inventoryA[4][selected+1].getName());
                            updateScreen();
                            System.out.println(hotbar[selected].getName());
                            System.out.println(equipped.getName());
                        }
                    }else if (equipped.isEatable()) {
                        eatingFood = true;
                        miningBar.setVisible(true);
                        miningBar.setProgress(1.0);
                    }
                    break;
                case "sheep","cow","pig":
                    if(!swing) {
                        int damage = 1;
                        swing = true;
                        swingTime = System.nanoTime();
                        swingBar.setVisible(true);
                        swingBar.setProgress(swingCount/4);
                        if (equipped.getName().endsWith("Axe") || equipped.getName().endsWith("Pickaxe") || equipped.getName().endsWith("Sword")) {
                            damage = equipped.getDamage();
                        }
                        for (Animals animal : animalsOnMap) {
                            if (animal.getX() == playerPositionX && animal.getY() == playerPositionY + directionChange) {

                                animal.changeHealth(-(damage));
                                if (animal.getHealth() <= 0) {
                                    map[playerPositionX][playerPositionY + directionChange] = "grass";
                                    breakB = false;
                                    for (int i = 4; i >= 1; i--) {
                                        for (int j = 1; j <= 5; j++) {
                                            if (inventoryA[i][j].getName().equals(animal.getResourceDrop().getName())) {
                                                System.out.println("hi");
                                                inventoryA[i][j].changeAmount(animal.getAmountDrop());
                                                breakB = true;
                                                break;
                                            }
                                        }
                                        if (breakB) {
                                            break;
                                        }
                                    }


                                    for (int i = 4; i >= 1; i--) {
                                        if (breakB) {
                                            breakB = false;
                                            break;
                                        }
                                        for (int j = 1; j <= 5; j++) {
                                            if (inventoryA[i][j].getName().equals("empty")) {
                                                inventoryA[i][j] = animal.getResourceDrop();
                                                inventoryA[i][j].setAmount(animal.getAmountDrop());
                                                breakB = true;
                                                break;
                                            }
                                        }
                                        if (breakB) {
                                            breakB = false;
                                            break;
                                        }
                                    }


                                    animalsOnMap.remove(animal);
                                    break;
                                } else {
                                    animal.changeLoc(map);
                                    updateScreen();
                                }
                            }
                        }
                    }
                case "zombieOverGrass","zombieOverStone":
                    if(!swing) {
                        int damage = 1;
                        swing = true;
                        swingTime = System.nanoTime();
                        swingBar.setVisible(true);
                        swingBar.setProgress(swingCount/4);
                        if (equipped.getName().endsWith("Axe") || equipped.getName().endsWith("Pickaxe") || equipped.getName().endsWith("Sword")) {
                            damage = equipped.getDamage();
                        }
                        for (mobsNoCreeper mobs : mobsNoCreepersOnMap) {
                            if (mobs.getX() == playerPositionX && mobs.getY() == playerPositionY + directionChange) {
                                mobs.changeHealth(-(damage));
                                if (mobs.getHealth() <= 0) {
                                    if (mapBackground[playerPositionX][playerPositionY + directionChange].equals("grass") || mapBackground[playerPositionX][playerPositionY + directionChange].equals("normal") || mapBackground[playerPositionX][playerPositionY + directionChange].equals("fruit") || mapBackground[playerPositionX][playerPositionY + directionChange].equals("autumn")) {
                                        map[playerPositionX][playerPositionY + directionChange] = "grass";
                                    } else {
                                        map[playerPositionX][playerPositionY + directionChange] = "stone";
                                    }
                                    breakB = false;
                                    for (int i = 4; i >= 1; i--) {
                                        for (int j = 1; j <= 5; j++) {
                                            if (inventoryA[i][j].getName().equals(mobs.getResourceDrop().getName())) {
                                                System.out.println("hi");
                                                inventoryA[i][j].changeAmount(mobs.getAmountDrop());
                                                breakB = true;
                                                break;
                                            }
                                        }
                                        if (breakB) {
                                            break;
                                        }
                                    }


                                    for (int i = 4; i >= 1; i--) {
                                        if (breakB) {
                                            breakB = false;
                                            break;
                                        }
                                        for (int j = 1; j <= 5; j++) {
                                            if (inventoryA[i][j].getName().equals("empty")) {
                                                inventoryA[i][j] = mobs.getResourceDrop();
                                                inventoryA[i][j].setAmount(mobs.getAmountDrop());
                                                breakB = true;
                                                break;
                                            }
                                        }
                                        if (breakB) {
                                            breakB = false;
                                            break;
                                        }
                                    }


                                    mobsNoCreepersOnMap.remove(mobs);
                                    break;
                                }
                            }
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
        tempHunger -=0.1;
        hungerBar.setProgress(tempHunger/totalHunger);
    }

    private void createBiomes() {
        int startX = 0;
        int startY = 0;
        int lengthX = 0;
        int lengthY = 0;
        int randNum;
        boolean valid;

        for (int i = 0; i < mapBackground.length; i++) {
            for (int j = 0; j < mapBackground[0].length; j++) {
                mapBackground[i][j] = "grass";
            }
        }
        for (int p = 0; p < 15; p++) {


            if (biomeNameList.size() == 0) {
                biomeNameList.add("fruitTree");
                biomeNameList.add("normalTree");
                biomeNameList.add("water");
                biomeNameList.add("autumnTree");
                biomeNameList.add("stone");
            }

            randNum = (int) (Math.random() * biomeNameList.size());
            valid = false;
            while(!valid){
                System.out.println(biomeNameList);
                valid = true;
                lengthX = (int) (Math.random() * 9) + 40;
                lengthY = (int) (Math.random() * 15) + 40;
                startX = (int) (Math.random() * (199 - lengthX)) + 1;
                startY = (int) (Math.random() * (327 - lengthY)) + 1;
                for (int i = startX; i < startX + lengthX; i++) {
                    for (int j = startY; j < startY + lengthY; j++) {
                        if(!Objects.equals(biomeNameList.get(randNum), "stone")&&!Objects.equals(biomeNameList.get(randNum), "water")){
                            if (!map[i][j].equals("grass")) {
                                valid = false;
                            }
                        }else if(biomeNameList.get(randNum).equals("stone")){
                            if (!map[i][j].equals("grass")){
                                valid = false;
                            }
                        }else if(biomeNameList.get(randNum).equals("water")){
                            if (!map[i][j].equals("grass")){
                                valid = false;
                                System.out.println("not grass when water");
                            }
                            if(i==99&&j==163){
                                valid = false;
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
                            mapBackground[i][j] = "normal";
                            int random = (int) (Math.random() * 600);
                            if (random <61) {
                                if (map[i][j].equals("grass")) {
                                    mineObjectsOnMap.add(new mineObjects("normalTree","axe", (int) (Math.random() * 5) + 5, new Resources("normalWood","axe"), (int) (Math.random() * 2) + 3, i, j));
                                    map[i][j] = "normalTree";
                                }
                            } else if (random<62) {
                                animalsOnMap.add(new Animals("cow",20,new Food("rawBeef",20), (Math.random()*15),(int)(Math.random()*3)+1,i,j));
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
                            mapBackground[i][j] = "fruit";
                            int random = (int) (Math.random() * 600);
                            if (random <61) {
                                if (map[i][j].equals("grass")) {
                                    mineObjectsOnMap.add(new mineObjects("fruitTree", "axe",(int) (Math.random() * 5) + 5, new Resources("fruitWood","axe"), (int) (Math.random() * 2) + 3,new Food("apples",15),(int) (Math.random() * 3) + 1, i, j));
                                    map[i][j] = "fruitTree";
                                }
                            }else if (random<62) {
                                animalsOnMap.add(new Animals("sheep",15,new Food("rawMutton",10),(Math.random()*15),(int)(Math.random()*3)+1,i,j));
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
                            mapBackground[i][j] = "autumn";
                            int random = (int) (Math.random() * 600);
                            if (random <61) {
                                if (map[i][j].equals("grass")) {
                                    map[i][j] = "autumnTree";
                                    mineObjectsOnMap.add(new mineObjects("autumnTree","axe", (int) (Math.random() * 5) + 5, new Resources("autumnWood","axe"), (int) (Math.random() * 2) + 3, i, j));

                                }
                            }else if (random<62) {
                                animalsOnMap.add(new Animals("pig",15,new Food("rawPork",15), (Math.random()*15),(int)(Math.random()*3)+1,i,j));
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
                                    mapBackground[i][j] = "stone";
                                    map[i][j] = "stone";
                                }
                            } else {
                                if (map[i][j].equals("grass")) {
                                    mapBackground[i][j] = "stone";
                                    if (mineralRand < 4) {
                                        map[i][j] = "rock";
                                        mineObjectsOnMap.add(new mineObjects("rock","pickaxe", (int) (Math.random() * 5) + 10, new Resources("cobblestone","pickaxe"), (int) (Math.random() * 3) + 2, i, j));

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