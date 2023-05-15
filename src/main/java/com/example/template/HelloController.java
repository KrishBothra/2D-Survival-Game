package com.example.template;


import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
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
    ////////TRADES:
    //Drill
    //Obsidian
    //Steak
    //Porkchops
    private ArrayList<String> otherTrades = new ArrayList<>();

    private String tradeSelected;

    @FXML
    private Label lbl, coordsLabel, dayNightLbl;
    @FXML
    private GridPane gPane, inventoryPane, hotbarG, craftingPane,nightPane;

    @FXML
    private Button startB,respawnB;
    @FXML
    private AnchorPane anchor;

    @FXML
    private TextField txtField;
    @FXML
    private ImageView arrowImg,deathScreenImg,hitScreenImg;

    @FXML
    private ProgressBar miningBar, healthBar, hungerBar,dayNightBar,overHealthBar,swingBar, fuelBar, smeltingBar,witherHealth;

    @FXML
    private Rectangle slot1,slot2,slot3,slot4,slot5;

    private int x = 25;

    private int y = 41;

    private long miningTime = System.nanoTime();
    private long eatingTime = System.nanoTime();
    private long burningTime;
    private long smeltingTime;
    private long amountToSmelt, amountToBurn;

    int placeBossX = 0;
    int placeBossY = 0;
    boolean placedBoss = false;
    long placeBossTime =System.nanoTime();
    private long inLavaTime = System.nanoTime();

    private long mobSpawnTimeCave = System.nanoTime();

    private long drillTime = System.nanoTime();

    private boolean isDrilling =false;

    private double drillAmt = 3;

    private boolean inCave = false;

    ImageView[][] img = new ImageView[x][y];

    private Armor tempHelmet, tempChestplate, tempLeggings, tempBoots;

    double bossCount = 4;


    private ImageView[][] hotbarImg = new ImageView[5][1];

    String[][] map = new String[x * 8 + 1][y * 8 + 1]; //100 //164
    String[][] mapBackground = new String[x * 8 + 1][y * 8 + 1]; //100 //164

    ImageView[][] mapNight = new ImageView[x][y]; //100 //164
    String[][] mapNightS = new String[x * 8 + 1][y * 8 + 1];

    String[][] mapNightCave = new String[x * 8 + 1][y * 8 + 1];

    String[][] mapCave = new String[x * 8 + 1][y * 8 + 1]; //100 //164

    String[][] mapNether  = new String[x ][y ]; //100 //164

    String[][] mapBackgroundNether  = new String[x ][y ]; //100 //164

    String[][] mapNightNether  = new String[x ][y ]; //100 //164


    private String[][] mapBackgroundCave = new String[x * 8 + 1][y * 8 + 1];
    private ArrayList<String> biomeNameList = new ArrayList<>();
    private String directionInter = "right";
    private ArrayList<Biome> biomeArrayList = new ArrayList<>();
    private ArrayList<mineObjects> mineObjectsOnMap = new ArrayList<>();
    private ArrayList<mineObjects> mineObjectsOnMapCave = new ArrayList<>();

    private ArrayList<mineObjects> mineObjectsOnMapNether = new ArrayList<>();

    private inventoryItems[] hotbar = new inventoryItems[5];
    private inventoryItems[][] inventoryA = new inventoryItems[6][12];

    private boolean furnaceShowing;

    private inventoryItems[][] craftingA = new inventoryItems[6][12];

    private inventoryItems inventorySelected = new inventoryItems("empty");
    private ImageView[][] inventoryImg = new ImageView[6][12];

    private int tradeTier, tradeProtection, tradeDurability, tradeDamage, tradeHungerGain;
    private String tradeType;

    private int invSelectedRow = -1;
    private int invSelectedCol = -1;

    private inventoryItems equipped;
    private int selected = 0;
    private boolean tradingShowing;

    private ArrayList<Animals> animalsOnMap = new ArrayList<>();
    private ArrayList<mobsNoCreeper> mobsNoCreepersOnMap = new ArrayList<>();
    private ArrayList<Villagers> villagersOnMap = new ArrayList<>();


    private Label[][] inventoryLabels = new Label[4][5];


    @FXML
    private Label tradingLabel,furnaceTop, furnaceBottom, one1, one2, one3, one4, one5, two1, two2, two3, two4, two5, three1, three2, three3, three4, three5, four1, four2, four3, four4, four5, one1c, one2c, one3cv, two1c, two2c, two3cv, three1cv, three2cv, three3cv, result;



    private boolean fruitQuest, normalQuest, autumnQuest, stoneQuest, waterQuest;

    long swingTime = System.nanoTime();
//    int changeX = 0;
//    int changeY = 0;

    int playerPositionX = 99;//-1
    int playerPositionY = 163;//-1

    double totalHealth = 100;
    int tradeSelectedIndex;
    double tempHealth = 100;

    String typeTradeSelected;

    double totalHunger = 100;
    double tempHunger = 65;

    private String currentSmelting = "";

    private boolean burningFuel = false;
    private boolean smelting = false;

    private boolean inNether = false;

    int dayTime = 120;
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

    ArrayList<Wither> wither = new ArrayList<>();
    long witherMoveTime;
    long witherHeadMoveTime;

    int miningX;
    int miningY;
    mineObjects tempMine = null;

    inventoryItems randomWitherDrop;


    //12,20----13,21
//    MediaPlayer mediaPlayer;
//    Media sound;

    FileInputStream grasss, playerr, playerOverGrasss, playerOverStonee, autumnTreee, fruitTreee, normalTreee, grassWXx, arroww, stonee, rockk, diamondOree, rubyOree, goldOree, waterr, chestWaterr, mailboxGrasss, mailboxStonee
            , grayBackk, blackBackk, yellowBackk, rubyInvv,goldIngotInvv,diamondInvv, normalWoodd,normalWooddInv,autumnWooddInv,fruitWooddInv,appleeInv,cobblestoneInvv,woodAxeInvv,autumnWoodd,jungleWoodd,
            sheepp, normalPlankkInv, fruitPlankkInv, autumnPlankkInv,fruitPlankk,autumnPlankk,normalPlankk, craftingTableeInv, craftingTablee, stickkInv, woodPickaxeeInv, woodSworddInv, boattInv, boatt
            ,rawMuttonInvv,coww,pigg,rawPorkInvv,rawBeefInvv, furnaceeInv, furnacee, stoneSworddInv, rubySworddInv, goldSworddInv, diamondSworddInv, stoneAxeeInv, rubyAxeeInv, goldAxeeInv, diamondAxeeInv, stonePickaxeeInv
            ,rubyPickaxeeInv, goldPickaxeeInv, diamondPickaxeeInv, woodHelmettInv, woodChestplateeInv, woodLeggingssInv, woodBootssInv, rubyHelmettInv, rubyChestplateeInv, rubyLeggingssInv, rubyBootssInv, goldHelmettInv
            ,goldChestplateeInv, goldLeggingssInv, goldBootssInv, diamondHelmettInv, diamondChestplateeInv, diamondLeggingssInv, diamondBootssInv,villagerr,zombieOverGrasss,zombieOverStonee,rottenFleshh, coalOree
            ,rubyOreeInv, coallInv, spiderOverGrasss, spiderOverStonee, goldOreInvv,creeperOverGrasss,creeperOverStonee,deathScreenn, cookedPorkkInv, cookedMuttonnInv, cookedBeeffInv
            ,hitScreenn,nightTimee,torchInvv,torchOverStonee,torchOverGrasss,lightt,stoneWXX, obsidiannInv, flinttInv, bossSoullInv, drilllInv, obsidiann,flintAndSteelInvv,netherPortalImgg,netherrackk,playerOverNetherrackk
            ,netherrackWXx,lavaa,playerInLavaa,witherHeadd,witherOverLavaa,witherOverNetherrackk;

    Image grass, player, playerOverGrass, playerOverStone, autumnTree, fruitTree, normalTree, grassWX, arrow, stone, rock, diamondOre, rubyOre, goldOre, water, chestWater, mailboxGrass, mailboxStone
            , grayBack, blackBack, yellowBack, rubyInv,goldIngotInv,diamondInv, normalWood,normalWoodInv,autumnWoodInv,fruitWoodInv,appleInv,cobbelstoneInv,woodAxeInv,autumnWood,fruitWood
            ,sheep, normalPlankInv, fruitPlankInv, autumnPlankInv,fruitPlank,autumnPlank,normalPlank, craftingTableInv, craftingTable, stickInv, woodPickaxeInv, woodSwordInv, boatInv, boat,
            rawMuttonInv,cow,pig,rawPorkInv,rawBeefInv, furnaceInv, furnace, stoneSwordInv, rubySwordInv, goldSwordInv, diamondSwordInv, stoneAxeInv, rubyAxeInv, goldAxeInv, diamondAxeInv, stonePickaxeInv, rubyPickaxeInv
            ,goldPickaxeInv, diamondPickaxeInv, woodHelmetInv, woodChestplateInv, woodLeggingsInv, woodBootsInv, rubyHelmetInv, rubyChestplateInv, rubyLeggingsInv, rubyBootsInv, goldHelmetInv, goldChestplateInv,
            goldLeggingsInv, goldBootsInv, diamondHelmetInv, diamondChestplateInv, diamondLeggingsInv, diamondBootsInv,villager,zombieOverGrass,zombieOverStone,rottenFlesh, coalOre, rubyOreInv, coalInv, spiderOverGrass, spiderOverStone
            ,creeperOverGrass,creeperOverStone,deathScreen,goldOreInv,cookedPorkInv, cookedMuttonInv, cookedBeefInv,hitScreen,nightTimeI,torchInv,torchOverStone,torchOverGrass,light,stoneWX, obsidian, obsidianInv
            , drillInv, flintInv, bossSoulInv,flintAndSteelInv,netherPortalImg,netherrack,playerOverNetherrack,netherrackWX,lava,playerInLava,witherHeadI,witherOverLava,witherOverNetherrack;
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
    private ArrayList<Creepers> creepersOnMap = new ArrayList<>();
    private boolean playerHit = false;
    private long playerHitTime = System.nanoTime();
    private boolean moveAble = true;

    private long moveTime;
    private int OWplayerPositionY =0;
    private int OWplayerPositionX = 0;
    private boolean inLava = false;


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
            rubyOreeInv = new FileInputStream("src/main/resources/InventoryItems/rubyOre.png");
            coalOree = new FileInputStream("src/main/resources/coalOre.png");
            coallInv = new FileInputStream("src/main/resources/InventoryItems/coal.png");
            spiderOverStonee = new FileInputStream("src/main/resources/Animals/spiderOverStone.png");
            spiderOverGrasss = new FileInputStream("src/main/resources/Animals/spiderOverGrass.png");
            creeperOverGrasss = new FileInputStream("src/main/resources/Animals/creeperOverGrass.png");
            creeperOverStonee = new FileInputStream("src/main/resources/Animals/creeperOverStone.png");
            deathScreenn = new FileInputStream("src/main/resources/deathScreen.png");
            goldOreInvv = new FileInputStream("src/main/resources/InventoryItems/goldOre.png");
            cookedPorkkInv = new FileInputStream("src/main/resources/InventoryItems/cookedPork.png");
            cookedBeeffInv = new FileInputStream("src/main/resources/InventoryItems/cookedBeef.png");
            cookedMuttonnInv = new FileInputStream("src/main/resources/InventoryItems/coookedMutton.png");
            hitScreenn = new FileInputStream("src/main/resources/hitScreen.jpg");
            nightTimee = new FileInputStream("src/main/resources/blackBack.png");
            torchInvv = new FileInputStream("src/main/resources/InventoryItems/torchInv.png");
            torchOverGrasss = new FileInputStream("src/main/resources/torchOverGrass.png");
            torchOverStonee = new FileInputStream("src/main/resources/torchOverStone.png");
            lightt = new FileInputStream("src/main/resources/nightTime.jpg");
            stoneWXX = new FileInputStream("src/main/resources/stoneWX.png");
            flinttInv = new FileInputStream("src/main/resources/InventoryItems/flint.png");
            obsidiannInv = new FileInputStream("src/main/resources/InventoryItems/obsidian.png");
            bossSoullInv = new FileInputStream("src/main/resources/InventoryItems/soul.png");
            drilllInv = new FileInputStream("src/main/resources/InventoryItems/drill.png");
            obsidiann = new FileInputStream("src/main/resources/obsidian.jpg");
            flintAndSteelInvv = new FileInputStream("src/main/resources/InventoryItems/flintAndSteelInv.png");
            netherPortalImgg = new FileInputStream("src/main/resources/netherPortalImg.png");
            netherrackk = new FileInputStream("src/main/resources/netherrack.png");
            playerOverNetherrackk = new FileInputStream("src/main/resources/playerOverNetherrack.png");
            netherrackWXx = new FileInputStream("src/main/resources/netherrackWX.png");
            lavaa = new FileInputStream("src/main/resources/lava.jpeg");
            playerInLavaa = new FileInputStream("src/main/resources/playerInLava.png");
            witherHeadd = new FileInputStream("src/main/resources/witherHead.png");
            witherOverLavaa = new FileInputStream("src/main/resources/witherOverLava.png");
            witherOverNetherrackk = new FileInputStream("src/main/resources/witherOverNetherrack.png");

            witherHeadI = new Image(witherHeadd);
            witherOverLava = new Image(witherOverLavaa);
            witherOverNetherrack = new Image(witherOverNetherrackk);
            lava = new Image(lavaa);
            playerInLava = new Image(playerInLavaa);
            netherrackWX = new Image(netherrackWXx);
            netherrack = new Image(netherrackk);
            playerOverNetherrack = new Image(playerOverNetherrackk);
            flintAndSteelInv = new Image(flintAndSteelInvv);
            netherPortalImg = new Image(netherPortalImgg);
            stoneWX = new Image(stoneWXX);
            torchOverStone = new Image(torchOverStonee);
            torchOverGrass = new Image(torchOverGrasss);
            torchInv = new Image(torchInvv);
            nightTimeI = new Image(nightTimee);
            light = new Image(lightt);
            hitScreen = new Image(hitScreenn);
            deathScreen = new Image(deathScreenn);
            spiderOverGrass = new Image(spiderOverGrasss);
            spiderOverStone = new Image(spiderOverStonee);
            creeperOverGrass = new Image(creeperOverGrasss);
            creeperOverStone = new Image(creeperOverStonee);
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
            rubyOreInv = new Image(rubyOreeInv);
            coalInv = new Image(coallInv);
            goldOreInv = new Image(goldOreInvv);
            cookedMuttonInv = new Image(cookedMuttonnInv);
            cookedPorkInv = new Image(cookedPorkkInv);
            cookedBeefInv = new Image(cookedBeeffInv);
            flintInv = new Image(flinttInv);
            drillInv = new Image(drilllInv);
            bossSoulInv = new Image(bossSoullInv);
            obsidianInv = new Image(obsidiannInv);
            obsidian = new Image(obsidiann);
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
        creepersOnMap.add(new Creepers("creeperOverGrass",30,new inventoryItems("flint"),(int)(Math.random()*15),0.75,1,  99,160));
        startB.setDisable(true);
        healthBar.setProgress(tempHealth/totalHealth);
        healthBar.setStyle(" -fx-accent: #FF0000; ");
        hungerBar.setProgress(tempHunger/totalHunger);
        hungerBar.setStyle(" -fx-accent: #987554; ");
        dayNightBar.setStyle(" -fx-accent: orange; ");
        hitScreenImg.setImage(hitScreen);
        witherHealth.setStyle("-fx-accent: purple;");

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
                tradingLabel.setVisible(false);
            }
        }
        fuelBar.setVisible(false);
        smeltingBar.setVisible(false);




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

        EventHandler z = new EventHandler<MouseEvent>()
        {

            @Override
            public void handle(MouseEvent t) {
                int row = GridPane.getRowIndex(((ImageView) t.getSource()));
                int col = GridPane.getColumnIndex(((ImageView) t.getSource()));
                if (!tradingShowing || (row != 1 || ((col != 9||inventoryImg[1][9].getOpacity()==1) && (col != 10||inventoryImg[1][10].getOpacity()==1) && (col != 11||inventoryImg[1][11].getOpacity()==1)))) {
                    if (t.getButton().equals(MouseButton.PRIMARY) && !clickedS) {
                        if (invSelectedCol != -1 && invSelectedRow != -1) {
                            System.out.println("hey");
                            if (inventoryA[row][col].getName().equals("empty") || inventoryA[row][col].getName().equals("trade")) {
                                if (inventoryImg[row][col].getImage().equals(grayBack)) {
                                    if (furnaceShowing || tradingShowing) {
                                        System.out.println("hey2");
                                        inventoryA[row][col] = inventoryA[invSelectedRow][invSelectedCol];
                                        inventoryA[invSelectedRow][invSelectedCol] = new inventoryItems("empty");
                                        invSelectedCol = -1;
                                        invSelectedRow = -1;
                                        updateScreen();
                                        clickedP = false;
                                    } else if (row == 1 && col == 7 && inventoryA[invSelectedRow][invSelectedCol].getName().endsWith("Helmet") ||
                                            row == 2 && col == 7 && inventoryA[invSelectedRow][invSelectedCol].getName().endsWith("Chestplate") ||
                                            row == 3 && col == 7 && inventoryA[invSelectedRow][invSelectedCol].getName().endsWith("Leggings") ||
                                            row == 4 && col == 7 && inventoryA[invSelectedRow][invSelectedCol].getName().endsWith("Boots") || col != 7) {
                                        if (row != 4 || col != 9) {
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
                                if (row != invSelectedRow || col != invSelectedCol) {
                                    System.out.println("hey5");
                                    inventoryA[row][col].changeAmount(inventoryA[invSelectedRow][invSelectedCol].getAmount());
                                    inventoryA[invSelectedRow][invSelectedCol] = new inventoryItems("empty");
                                    invSelectedCol = -1;
                                    invSelectedRow = -1;
                                    updateScreen();
                                }
                            }
                        } else if (!inventoryA[row][col].getName().equals("empty") && !inventoryA[row][col].getName().equals("nothing")) {
                            System.out.println("hey3");
                            invSelectedRow = row;
                            invSelectedCol = col;
                            clickedP = true;
                            System.out.println(inventoryA[row][col].getAmount());
                        }
                    } else if (t.getButton().equals(MouseButton.SECONDARY) && !clickedP) {
                        if (invSelectedCol != -1 && invSelectedRow != -1) {
                            System.out.println("hey");
                            if (inventoryA[row][col].getName().equals("empty")) {
                                if (inventoryImg[row][col].getImage().equals(grayBack)) {
                                    System.out.println("hey2");
                                    inventoryA[row][col] = new inventoryItems(inventoryA[invSelectedRow][invSelectedCol].getName(), amountChange);
                                    inventoryA[invSelectedRow][invSelectedCol].changeAmount(-(amountChange));
                                    invSelectedCol = -1;
                                    invSelectedRow = -1;
                                    updateScreen();
                                    clickedS = false;
                                }
                            }
                        } else if (!inventoryA[row][col].getName().equals("empty") && !inventoryA[row][col].getName().equals("nothing")) {
                            if (inventoryA[row][col].getAmount() > 1) {
                                amountChange = inventoryA[row][col].getAmount() / 2;
                                System.out.println("hey3");
                                invSelectedRow = row;
                                invSelectedCol = col;
                                clickedS = true;
                            }
                        }
                    }
                }

                if (!furnaceShowing) {
                    checkCrafts();
                }
                if (row == 4 && col == 9) {
                    if (!inventoryA[4][9].getName().equals("empty")) {
                        if (!craftingShowing) {
                            for (int i = 1; i < 3; i++) {
                                for (int j = 9; j < 11; j++) {
                                    if (inventoryA[i][j].getAmount() > 1) {
                                        inventoryA[i][j].setAmount(inventoryA[i][j].getAmount() - 1);
                                    } else {
                                        inventoryA[i][j] = new inventoryItems("empty");
                                    }
                                }
                            }
                        } else {
                            for (int i = 0; i < 3; i++) {
                                for (int j = 9; j < 12; j++) {
                                    if (inventoryA[i][j].getAmount() > 1) {
                                        inventoryA[i][j].setAmount(inventoryA[i][j].getAmount() - 1);
                                    } else {
                                        inventoryA[i][j] = new inventoryItems("empty");
                                    }
                                }
                            }
                        }
                    }
                }

                if (tradingShowing) {
                    if (row == 1 || (col == 9 || col == 10 || col == 11)) {
                        if (inventoryImg[row][col].getImage() == flintInv) {
                            inventoryA[2][11] = new inventoryItems("empty");
                            inventoryA[2][9] = new inventoryItems("trade");
                            inventoryA[2][10] = new inventoryItems("trade");
                            inventoryImg[2][9].setImage(rubyInv);
                            inventoryA[2][9].setAmountNeeded(15);
                            inventoryImg[2][10].setImage(stickInv);
                            inventoryA[2][10].setAmountNeeded(10);
                            inventoryImg[2][9].setOpacity(.3);
                            inventoryImg[2][10].setOpacity(.3);
                            inventoryImg[2][11].setImage(grayBack);
                            inventoryImg[2][11].setOpacity(1);

                            typeTradeSelected = "inventoryItems";
                            tradeSelected = "flint";
                            if (col == 9) {
                                tradeSelectedIndex = 1;
                            }
                            if (col == 10) {
                                tradeSelectedIndex = 2;
                            }
                            if (col == 11) {
                                tradeSelectedIndex = 3;
                            }
                        } else if (inventoryImg[row][col].getImage() == bossSoulInv) {
                            inventoryA[2][9] = new inventoryItems("trade");
                            inventoryA[2][10] = new inventoryItems("trade");
                            inventoryA[2][11] = new inventoryItems("trade");
                            inventoryImg[2][9].setImage(diamondInv);
                            inventoryA[2][9].setAmountNeeded(5);
                            inventoryImg[2][10].setImage(goldIngotInv);
                            inventoryA[2][10].setAmountNeeded(10);
                            inventoryImg[2][11].setImage(rubyInv);
                            inventoryA[2][11].setAmountNeeded(15);
                            inventoryImg[2][9].setOpacity(.3);
                            inventoryImg[2][10].setOpacity(.3);
                            inventoryImg[2][11].setOpacity(.3);

                            typeTradeSelected = "inventoryItems";
                            tradeSelected = "bossSoul";
                            if (col == 9) {
                                tradeSelectedIndex = 1;
                            }
                            if (col == 10) {
                                tradeSelectedIndex = 2;
                            }
                            if (col == 11) {
                                tradeSelectedIndex = 3;
                            }
                        }else if (inventoryImg[row][col].getImage() == drillInv) {
                            inventoryA[2][9] = new inventoryItems("trade");
                            inventoryA[2][10] = new inventoryItems("trade");
                            inventoryA[2][11] = new inventoryItems("trade");
                            inventoryImg[2][9].setImage(woodPickaxeInv);
                            inventoryA[2][9].setAmountNeeded(1);
                            inventoryImg[2][10].setImage(woodAxeInv);
                            inventoryA[2][10].setAmountNeeded(1);
                            inventoryImg[2][11].setImage(woodSwordInv);
                            inventoryA[2][11].setAmountNeeded(1);
                            inventoryImg[2][9].setOpacity(.3);
                            inventoryImg[2][10].setOpacity(.3);
                            inventoryImg[2][11].setOpacity(.3);

                            typeTradeSelected = "inventoryItems";
                            tradeSelected = "drill";
                            if (col == 9) {
                                tradeSelectedIndex = 1;
                            }
                            if (col == 10) {
                                tradeSelectedIndex = 2;
                            }
                            if (col == 11) {
                                tradeSelectedIndex = 3;
                            }
                        }else if (inventoryImg[row][col].getImage() == obsidianInv) {
                            inventoryA[2][11] = new inventoryItems("empty");
                            inventoryA[2][10] = new inventoryItems("empty");
                            inventoryA[2][9] = new inventoryItems("trade");
                            inventoryImg[2][9].setImage(diamondInv);
                            inventoryA[2][9].setAmountNeeded(1);
                            inventoryImg[2][9].setOpacity(.3);
                            inventoryImg[2][10].setImage(grayBack);
                            inventoryImg[2][10].setOpacity(1);
                            inventoryImg[2][11].setImage(grayBack);
                            inventoryImg[2][11].setOpacity(1);

                            typeTradeSelected = "resource";
                            tradeSelected = "obsidian";
                            tradeType = "pickaxe";
                            if (col == 9) {
                                tradeSelectedIndex = 1;
                            }
                            if (col == 10) {
                                tradeSelectedIndex = 2;
                            }
                            if (col == 11) {
                                tradeSelectedIndex = 3;
                            }
                        }else if (inventoryImg[row][col].getImage() == goldHelmetInv) {
                            inventoryA[2][11] = new inventoryItems("empty");
                            inventoryA[2][9] = new inventoryItems("trade");
                            inventoryA[2][10] = new inventoryItems("trade");
                            inventoryImg[2][9].setImage(rubyInv);
                            inventoryA[2][9].setAmountNeeded(10);
                            inventoryImg[2][10].setImage(normalPlankInv);
                            inventoryA[2][10].setAmountNeeded(10);
                            inventoryImg[2][9].setOpacity(.3);
                            inventoryImg[2][10].setOpacity(.3);
                            inventoryImg[2][11].setImage(grayBack);
                            inventoryImg[2][11].setOpacity(1);

                            typeTradeSelected = "armor";
                            tradeSelected = "goldHelmet";
                            tradeTier = 3;
                            tradeProtection = 5;
                            tradeDurability = 13;
                            if (col == 9) {
                                tradeSelectedIndex = 1;
                            }
                            if (col == 10) {
                                tradeSelectedIndex = 2;
                            }
                            if (col == 11) {
                                tradeSelectedIndex = 3;
                            }
                        }else if (inventoryImg[row][col].getImage() == rubyAxeInv) {
                            inventoryA[2][11] = new inventoryItems("empty");
                            inventoryA[2][9] = new inventoryItems("trade");
                            inventoryA[2][10] = new inventoryItems("trade");
                            inventoryImg[2][9].setImage(woodPickaxeInv);
                            inventoryA[2][9].setAmountNeeded(1);
                            inventoryImg[2][10].setImage(stonePickaxeInv);
                            inventoryA[2][10].setAmountNeeded(1);
                            inventoryImg[2][9].setOpacity(.3);
                            inventoryImg[2][10].setOpacity(.3);
                            inventoryImg[2][11].setImage(grayBack);
                            inventoryImg[2][11].setOpacity(1);

                            typeTradeSelected = "tool";
                            tradeSelected = "rubyAxe";
                            tradeTier = 3;
                            tradeType = "axe";
                            tradeDamage = 3;
                            tradeDurability = 40;
                            if (col == 9) {
                                tradeSelectedIndex = 1;
                            }
                            if (col == 10) {
                                tradeSelectedIndex = 2;
                            }
                            if (col == 11) {
                                tradeSelectedIndex = 3;
                            }
                        }else if (inventoryImg[row][col].getImage() == woodChestplateInv) {
                            inventoryA[2][11] = new inventoryItems("empty");
                            inventoryA[2][9] = new inventoryItems("trade");
                            inventoryA[2][10] = new inventoryItems("trade");
                            inventoryImg[2][9].setImage(woodAxeInv);
                            inventoryA[2][9].setAmountNeeded(1);
                            inventoryImg[2][10].setImage(woodPickaxeInv);
                            inventoryA[2][10].setAmountNeeded(1);
                            inventoryImg[2][9].setOpacity(.3);
                            inventoryImg[2][10].setOpacity(.3);
                            inventoryImg[2][11].setImage(grayBack);
                            inventoryImg[2][11].setOpacity(1);

                            typeTradeSelected = "armor";
                            tradeSelected = "woodChestplate";
                            tradeTier = 1;
                            tradeProtection = 10;
                            tradeDurability = 25;
                            if (col == 9) {
                                tradeSelectedIndex = 1;
                            }
                            if (col == 10) {
                                tradeSelectedIndex = 2;
                            }
                            if (col == 11) {
                                tradeSelectedIndex = 3;
                            }
                        }else if (inventoryImg[row][col].getImage() == stoneSwordInv) {
                            inventoryA[2][11] = new inventoryItems("empty");
                            inventoryA[2][9] = new inventoryItems("trade");
                            inventoryA[2][10] = new inventoryItems("trade");
                            inventoryImg[2][9].setImage(woodBootsInv);
                            inventoryA[2][9].setAmountNeeded(1);
                            inventoryImg[2][10].setImage(woodHelmetInv);
                            inventoryA[2][10].setAmountNeeded(1);
                            inventoryImg[2][9].setOpacity(.3);
                            inventoryImg[2][10].setOpacity(.3);
                            inventoryImg[2][11].setImage(grayBack);
                            inventoryImg[2][11].setOpacity(1);

                            typeTradeSelected = "tool";
                            tradeSelected = "stoneSword";
                            if (col == 9) {
                                tradeSelectedIndex = 1;
                            }
                            if (col == 10) {
                                tradeSelectedIndex = 2;
                            }
                            if (col == 11) {
                                tradeSelectedIndex = 3;
                            }
                        }else if (inventoryImg[row][col].getImage() == appleInv) {
                            inventoryA[2][11] = new inventoryItems("empty");
                            inventoryA[2][10] = new inventoryItems("empty");
                            inventoryA[2][9] = new inventoryItems("trade");
                            inventoryImg[2][9].setImage(rubyInv);
                            inventoryA[2][9].setAmountNeeded(10);
                            inventoryImg[2][9].setOpacity(.3);
                            inventoryImg[2][10].setImage(grayBack);
                            inventoryImg[2][10].setOpacity(1);
                            inventoryImg[2][11].setImage(grayBack);
                            inventoryImg[2][11].setOpacity(1);

                            typeTradeSelected = "food";
                            tradeSelected = "apples";
                            tradeHungerGain = 15;
                            if (col == 9) {
                                tradeSelectedIndex = 1;
                            }
                            if (col == 10) {
                                tradeSelectedIndex = 2;
                            }
                            if (col == 11) {
                                tradeSelectedIndex = 3;
                            }
                        }else if (inventoryImg[row][col].getImage() == woodBootsInv) {
                            inventoryA[2][11] = new inventoryItems("empty");
                            inventoryA[2][10] = new inventoryItems("empty");
                            inventoryA[2][9] = new inventoryItems("trade");
                            inventoryImg[2][9].setImage(stonePickaxeInv);
                            inventoryA[2][9].setAmountNeeded(1);
                            inventoryImg[2][9].setOpacity(.3);
                            inventoryImg[2][10].setImage(grayBack);
                            inventoryImg[2][10].setOpacity(1);
                            inventoryImg[2][11].setImage(grayBack);
                            inventoryImg[2][11].setOpacity(1);

                            typeTradeSelected = "armor";
                            tradeSelected = "woodBoots";
                            tradeTier = 1;
                            tradeProtection = 5;
                            tradeDurability = 15;
                            if (col == 9) {
                                tradeSelectedIndex = 1;
                            }
                            if (col == 10) {
                                tradeSelectedIndex = 2;
                            }
                            if (col == 11) {
                                tradeSelectedIndex = 3;
                            }
                        }else if (inventoryImg[row][col].getImage() == rubyChestplateInv) {
                            inventoryA[2][11] = new inventoryItems("empty");
                            inventoryA[2][9] = new inventoryItems("trade");
                            inventoryA[2][10] = new inventoryItems("trade");
                            inventoryImg[2][9].setImage(goldChestplateInv);
                            inventoryA[2][9].setAmountNeeded(1);
                            inventoryImg[2][10].setImage(goldBootsInv);
                            inventoryA[2][9].setAmountNeeded(1);
                            inventoryA[2][10].setAmountNeeded(1);
                            inventoryImg[2][9].setOpacity(.3);
                            inventoryImg[2][10].setOpacity(.3);
                            inventoryImg[2][11].setImage(grayBack);
                            inventoryImg[2][11].setOpacity(1);

                            typeTradeSelected = "armor";
                            tradeSelected = "rubyChestplate";
                            tradeTier = 2;
                            tradeProtection = 10;
                            tradeDurability = 30;
                            if (col == 9) {
                                tradeSelectedIndex = 1;
                            }
                            if (col == 10) {
                                tradeSelectedIndex = 2;
                            }
                            if (col == 11) {
                                tradeSelectedIndex = 3;
                            }
                        }else if (inventoryImg[row][col].getImage() == diamondLeggingsInv) {
                            inventoryA[2][11] = new inventoryItems("empty");
                            inventoryA[2][10] = new inventoryItems("empty");
                            inventoryA[2][9] = new inventoryItems("trade");
                            inventoryImg[2][9].setImage(rubyInv);
                            inventoryA[2][9].setAmountNeeded(25);
                            inventoryImg[2][9].setOpacity(.3);
                            inventoryImg[2][10].setImage(grayBack);
                            inventoryImg[2][10].setOpacity(1);
                            inventoryImg[2][11].setImage(grayBack);
                            inventoryImg[2][11].setOpacity(1);

                            typeTradeSelected = "armor";
                            tradeSelected = "diamondLeggings";
                            tradeTier = 4;
                            tradeProtection = 10;
                            tradeDurability = 40;
                            if (col == 9) {
                                tradeSelectedIndex = 1;
                            }
                            if (col == 10) {
                                tradeSelectedIndex = 2;
                            }
                            if (col == 11) {
                                tradeSelectedIndex = 3;
                            }
                        }else if (inventoryImg[row][col].getImage() == goldSwordInv) {
                            inventoryA[2][11] = new inventoryItems("empty");
                            inventoryA[2][10] = new inventoryItems("empty");
                            inventoryA[2][9] = new inventoryItems("trade");
                            inventoryImg[2][9].setImage(rubyInv);
                            inventoryA[2][9].setAmountNeeded(3);
                            inventoryImg[2][9].setOpacity(.3);
                            inventoryImg[2][10].setImage(grayBack);
                            inventoryImg[2][10].setOpacity(1);
                            inventoryImg[2][11].setImage(grayBack);
                            inventoryImg[2][11].setOpacity(1);

                            typeTradeSelected = "tool";
                            tradeSelected = "goldSword";
                            tradeTier = 4;
                            tradeType = "sword";
                            tradeDamage = 4;
                            tradeDurability = 50;
                            if (col == 9) {
                                tradeSelectedIndex = 1;
                            }
                            if (col == 10) {
                                tradeSelectedIndex = 2;
                            }
                            if (col == 11) {
                                tradeSelectedIndex = 3;
                            }
                        }else if (inventoryImg[row][col].getImage() == rawPorkInv) {
                            inventoryA[2][11] = new inventoryItems("empty");
                            inventoryA[2][10] = new inventoryItems("empty");
                            inventoryA[2][9] = new inventoryItems("trade");
                            inventoryImg[2][9].setImage(normalPlankInv);
                            inventoryA[2][9].setAmountNeeded(6);
                            inventoryImg[2][9].setOpacity(.3);
                            inventoryImg[2][10].setImage(grayBack);
                            inventoryImg[2][10].setOpacity(1);
                            inventoryImg[2][11].setImage(grayBack);
                            inventoryImg[2][11].setOpacity(1);
                            typeTradeSelected = "food";
                            tradeSelected = "rawPork";
                            tradeHungerGain = 15;
                            if (col == 9) {
                                tradeSelectedIndex = 1;
                            }
                            if (col == 10) {
                                tradeSelectedIndex = 2;
                            }
                            if (col == 11) {
                                tradeSelectedIndex = 3;
                            }
                        }else if (inventoryImg[row][col].getImage() == cookedBeefInv) {
                            inventoryA[2][10] = new inventoryItems("empty");
                            inventoryImg[2][11].setImage(grayBack);
                            inventoryImg[2][11].setOpacity(1);
                            inventoryA[2][11] = new inventoryItems("empty");
                            inventoryA[2][9] = new inventoryItems("trade");
                            inventoryImg[2][9].setImage(goldIngotInv);
                            inventoryA[2][9].setAmountNeeded(1);
                            inventoryImg[2][9].setOpacity(.3);
                            inventoryImg[2][10].setImage(grayBack);
                            inventoryImg[2][10].setOpacity(1);
                            typeTradeSelected = "food";
                            tradeSelected = "cookedBeef";
                            tradeHungerGain = 25;
                            if (col == 9) {
                                tradeSelectedIndex = 1;
                            }
                            if (col == 10) {
                                tradeSelectedIndex = 2;
                            }
                            if (col == 11) {
                                tradeSelectedIndex = 3;
                            }
                        }
                    }

                    System.out.println("general" + inventoryA[row][col].getAmount());
                    System.out.println("have: " + inventoryA[1][7].getAmount());
                    System.out.println("needed: " + inventoryA[2][9].getAmountNeeded());
                    if (inventoryImg[1][7].getImage() == inventoryImg[2][9].getImage() && inventoryA[1][7].getAmount() >= inventoryA[2][9].getAmountNeeded()) {
                        inventoryImg[2][9].setOpacity(1);
                    } else if (!(inventoryImg[2][9].getImage() == grayBack) && tradingShowing) {
                        inventoryImg[2][9].setOpacity(.3);
                    }
                    if (inventoryImg[2][7].getImage() == inventoryImg[2][10].getImage() && inventoryA[2][7].getAmount() >= inventoryA[2][10].getAmountNeeded()) {
                        inventoryImg[2][10].setOpacity(1);
                    } else if (!(inventoryImg[2][10].getImage() == grayBack) && tradingShowing) {
                        inventoryImg[2][10].setOpacity(.3);
                    }
                    if (inventoryImg[3][7].getImage() == inventoryImg[2][11].getImage() && inventoryA[3][7].getAmount() >= inventoryA[2][11].getAmountNeeded()) {
                        inventoryImg[2][11].setOpacity(1);
                    } else if (!(inventoryImg[2][11].getImage() == grayBack) && tradingShowing) {
                        inventoryImg[2][11].setOpacity(.3);
                    }

                    if (inventoryImg[2][9].getImage()!=grayBack && inventoryImg[2][9].getOpacity() == 1 && inventoryImg[2][10].getOpacity() == 1 && inventoryImg[2][11].getOpacity() == 1) {
                        if (tradeSelectedIndex == 1) {
                            switch (typeTradeSelected) {
                                case "inventoryItems" -> inventoryA[1][9] = new inventoryItems(tradeSelected);
                                case "resource" -> inventoryA[1][9] = new Resources(tradeSelected, tradeType);
                                case "tool" -> inventoryA[1][9] = new Tools(tradeSelected, tradeTier, tradeType, tradeDamage, tradeDurability);
                                case "armor" -> inventoryA[1][9] = new Armor(tradeSelected, tradeTier, tradeProtection, tradeDurability);
                                case "food" -> inventoryA[1][9] = new Food(tradeSelected, tradeHungerGain);
                            }
                            inventoryImg[1][9].setOpacity(1);
                            inventoryA[1][9].setAmount(1);
                        } else if (tradeSelectedIndex == 2) {
                            switch (typeTradeSelected) {
                                case "inventoryItems" -> inventoryA[1][10] = new inventoryItems(tradeSelected);
                                case "resource" -> inventoryA[1][10] = new Resources(tradeSelected, tradeType);
                                case "tool" -> inventoryA[1][10] = new Tools(tradeSelected, tradeTier, tradeType, tradeDamage, tradeDurability);
                                case "armor" -> inventoryA[1][10] = new Armor(tradeSelected, tradeTier, tradeProtection, tradeDurability);
                                case "food" -> inventoryA[1][10] = new Food(tradeSelected, tradeHungerGain);
                            }
                            inventoryImg[1][10].setOpacity(1);
                            inventoryA[1][10].setAmount(1);
                        } else if (tradeSelectedIndex == 3) {
                            switch (typeTradeSelected) {
                                case "inventoryItems" -> inventoryA[1][11] = new inventoryItems(tradeSelected);
                                case "resource" -> inventoryA[1][11] = new Resources(tradeSelected, tradeType);
                                case "tool" -> inventoryA[1][11] = new Tools(tradeSelected, tradeTier, tradeType, tradeDamage, tradeDurability);
                                case "armor" -> inventoryA[1][11] = new Armor(tradeSelected, tradeTier, tradeProtection, tradeDurability);
                                case "food" -> inventoryA[1][11] = new Food(tradeSelected, tradeHungerGain);
                            }
                            inventoryA[1][11].setAmount(1);
                            inventoryImg[1][11].setOpacity(1);
                        }
                    }else{
                        if(tradeSelectedIndex == 1){
                            inventoryImg[1][9].setOpacity(.7);
                        }else if(tradeSelectedIndex == 2){
                            inventoryImg[1][10].setOpacity(.7);
                        }else{
                            inventoryImg[1][11].setOpacity(.7);
                        }
                    }


                    if(row ==1&&(col==9||col==10||col==11)){
                        if(inventoryImg[row][col].getOpacity()==1&&inventoryImg[row][col].getImage()!=grayBack){
                            inventoryA[1][7].setAmount(inventoryA[1][7].getAmount()-inventoryA[2][9].getAmountNeeded());
                            if(inventoryImg[2][7].getImage()!=grayBack){
                                inventoryA[2][7].setAmount(inventoryA[2][7].getAmount()-inventoryA[2][10].getAmountNeeded());
                            }
                            if(inventoryImg[3][7].getImage()!=grayBack){
                                inventoryA[3][7].setAmount(inventoryA[3][7].getAmount()-inventoryA[2][11].getAmountNeeded());
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

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = "grass";
                if (i == 0 || i == map.length - 1 || j == 0 || j == map[0].length - 1) {
                    map[i][j] = "grassWX";
                }
            }
        }

        for (int i = 0; i < mapCave.length; i++) {
            for (int j = 0; j < mapCave[0].length; j++) {
                mapCave[i][j] = "stone";
                if (i == 0 || i == mapCave.length - 1 || j == 0 || j == mapCave[0].length - 1) {
                    mapCave[i][j] = "stoneWX";
                }
            }
        }

        for (int i = 0; i < mapNether.length; i++) {
            for (int j = 0; j < mapNether[0].length; j++) {
                mapNether[i][j] = "netherrack";
                mapBackgroundNether[i][j] = "netherrack";
                if (i == 0 || i == mapNether.length - 1 || j == 0 || j == mapNether[0].length - 1) {
                    mapNether[i][j] = "netherrackWX";
                }
            }
        }

        mapNether[1][1] = "obsidian";
        mapNether[1][2] = "obsidian";
        mapNether[1][3] = "obsidian";
        mapNether[2][1] = "obsidian";
        mapNether[2][3] = "obsidian";
        mapNether[2][2] = "netherPortal";

        for (int i = 0; i < mapBackgroundCave.length; i++) {
            for (int j = 0; j < mapBackgroundCave[0].length; j++) {
                mapBackgroundCave[i][j] = "stone";
            }
        }

        for (int i = 0; i < mapNight.length; i++) {
            for (int j = 0; j < mapNight[0].length; j++) {
                mapNight[i][j] = new ImageView();
                mapNight[i][j].setImage(nightTimeI);
                mapNight[i][j].setFitHeight(40);
                mapNight[i][j].setFitWidth(40);
//                mapNight[i][j].setOpacity(0.5);
                nightPane.add(mapNight[i][j], j, i);
            }
        }

        for (int i = 0; i < mapNightS.length; i++) {
            for (int j = 0; j < mapNightS[0].length; j++) {
                mapNightS[i][j] = "light";
            }
        }

        for (int i = 0; i < mapNightCave.length; i++) {
            for (int j = 0; j < mapNightCave[0].length; j++) {
                mapNightCave[i][j] = "night";
            }
        }

        for (int i = 0; i < mapNightNether.length; i++) {
            for (int j = 0; j < mapNightNether[0].length; j++) {
                mapNightNether[i][j] = "light";
            }
        }


//        img[12][20].setImage(null);
//        map[98][160] = "null";
        map[99][163] = "playerOverGrass";
//        map[30][160] = "normalTree";
        createBiomes();
        createCaveBiomes();
        createLava();


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

//        inventoryA[4][1] = new Tools("woodAxe",1,"axe",3,20);
        inventoryA[4][2] = new Resources("craftingTable", "axe");
        inventoryA[4][3] = new Tools("diamondPickaxe",5,"pickaxe",15,20);
//        inventoryA[1][1] = new Resources("cobblestone", "pickaxe");
//        inventoryA[1][1].setAmount(99);
//        inventoryA[1][4] = new inventoryItems("coal");
//        inventoryA[1][4].setAmount(99);
        inventoryA[2][4] = new Resources("torch","axe");
        inventoryA[2][4].setAmount(99);
        inventoryA[1][2] = new Resources("normalWood", "axe");
        inventoryA[1][2].setAmount(99);
//        inventoryA[1][3] = new Resources("autumnWood", "axe");
//        inventoryA[1][3].setAmount(99);
//        inventoryA[2][1] = new Resources("fruitWood", "axe");
//        inventoryA[2][1].setAmount(99);
        inventoryA[2][2] = new Food("cookedBeef",25);
        inventoryA[2][2].setAmount(99);
        inventoryA[3][4] = new inventoryItems("ruby");
        inventoryA[3][4].setAmount(99);
        inventoryA[2][3] = new inventoryItems("goldIngot");
        inventoryA[2][3].setAmount(99);
//        inventoryA[3][1] = new inventoryItems("diamond");
//        inventoryA[3][1].setAmount(99);
        inventoryA[3][2] = new inventoryItems("stick");
        inventoryA[3][2].setAmount(99);
//        inventoryA[4][4] = new Resources("furnace", "pickaxe");
//        inventoryA[4][4].setAmount(99);
//        inventoryA[4][5] = new Resources("goldOre","pickaxe");
//        inventoryA[4][5].setAmount(99);
        inventoryA[3][5] = new inventoryItems("drill");
        inventoryA[3][1] = new inventoryItems("flintAndSteel");
        inventoryA[2][5] = new Resources("obsidian","pickaxe");
        inventoryA[2][5].setAmount(99);
        inventoryA[1][5] = new inventoryItems("diamond");
        inventoryA[1][5].setAmount(99);
        inventoryA[4][5] = new Resources("bossSoul");
        inventoryA[4][5].setAmount(2);
        otherTrades.add("goldHelmet");
        otherTrades.add("woodChestplate");
        otherTrades.add("rubyAxe");
        otherTrades.add("stoneSword");
        otherTrades.add("apple");
        otherTrades.add("woodBoots");
        otherTrades.add("rubyChestplate");
        otherTrades.add("diamondLeggings");
        otherTrades.add("goldSword");
        otherTrades.add("rawPork");
        otherTrades.add("cookedBeef");




        for(Villagers villager: villagersOnMap){
            int random = (int)(Math.random()*2)+1;
            switch (villager.getBiome()) {
                case "normal" -> {
                    villager.addTrades("obsidian");
                    villager.addTrades(otherTrades.get((int) (Math.random() * 11)));
                    if (random == 2) {
                        boolean valid = false;
                        while (!valid) {
                            int randomNum2 = (int) (Math.random() * 11);
                            if (!(otherTrades.get(randomNum2).equals(villager.getTrades().get(1)))) {
                                villager.addTrades(otherTrades.get(randomNum2));
                                valid = true;
                            }
                        }
                    }
                }
                case "autumn" -> {
                    villager.addTrades("flint");
                    villager.addTrades("bossSoul");
                    if (random == 2) {
                        villager.addTrades(otherTrades.get((int) (Math.random() * 11)));
                    }
                }
                case "fruit" -> {
                    villager.addTrades("drill");
                    villager.addTrades(otherTrades.get((int) (Math.random() * 11)));
                    if (random == 2) {
                        boolean valid = false;
                        while (!valid) {
                            int randomNum2 = (int) (Math.random() * 11) ;
                            if (!(otherTrades.get(randomNum2).equals(villager.getTrades().get(1)))){
                                villager.addTrades(otherTrades.get(randomNum2));
                                valid = true;
                            }
                        }
                    }
                }
            }
        }

        updateScreen();
        start();
        //for the change
//        mapCave[playerPositionX][playerPositionY] = "playerOverStone";
//        inCave = true;

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
            if(inNether) {
                for (int i = 0; i < img.length; i++) {
                    for (int j = 0; j < img[0].length; j++) {
                        if (mapNether[i][j].equals("grass")) {
                            img[i][j].setImage(grass);
                        } else if (mapNether[i][j].equals("grassWX")) {
                            img[i][j].setImage(grassWX); //steve
                        } else if (mapNether[i][j].equals("playerOverGrass")) {
                            img[i][j].setImage(playerOverGrass); //steve
                        } else if (mapNether[i][j].equals("normalTree")) {
                            img[i][j].setImage(normalTree);
                        } else if (mapNether[i][j].equals("fruitTree")) {
                            img[i][j].setImage(fruitTree);
                        } else if (mapNether[i][j].equals("autumnTree")) {
                            img[i][j].setImage(autumnTree);
                        } else if (mapNether[i][j].equals("stone")) {
                            img[i][j].setImage(stone);
                        } else if (mapNether[i][j].equals("playerOverStone")) {
                            img[i][j].setImage(playerOverStone);
                        } else if (mapNether[i][j].equals("rock")) {
                            img[i][j].setImage(rock);
                        } else if (mapNether[i][j].equals("goldOre")) {
                            img[i][j].setImage(goldOre);
                        } else if (mapNether[i][j].equals("rubyOre")) {
                            img[i][j].setImage(rubyOre);
                        } else if (mapNether[i][j].equals("diamondOre")) {
                            img[i][j].setImage(diamondOre);
                        } else if (mapNether[i][j].equals("water")) {
                            img[i][j].setImage(water);
                        } else if (mapNether[i][j].equals("null")) {
                            img[i][j].setImage(null);
                        } else if (mapNether[i][j].equals("normalWood")) {
                            img[i][j].setImage(normalWood);
                        } else if (mapNether[i][j].equals("autumnWood")) {
                            img[i][j].setImage(autumnWood);
                        } else if (mapNether[i][j].equals("fruitWood")) {
                            img[i][j].setImage(fruitWood);
                        } else if (mapNether[i][j].equals("sheep")) {
                            img[i][j].setImage(sheep);
                        } else if (mapNether[i][j].equals("fruitPlank")) {
                            img[i][j].setImage(fruitPlank);
                        } else if (mapNether[i][j].equals("normalPlank")) {
                            img[i][j].setImage(normalPlank);
                        } else if (mapNether[i][j].equals("autumnPlank")) {
                            img[i][j].setImage(autumnPlank);
                        } else if (mapNether[i][j].equals("craftingTable")) {
                            img[i][j].setImage(craftingTable);
                        } else if (mapNether[i][j].equals("boat")) {
                            img[i][j].setImage(boat);
                        } else if (mapNether[i][j].equals("cow")) {
                            img[i][j].setImage(cow);
                        } else if (mapNether[i][j].equals("pig")) {
                            img[i][j].setImage(pig);
                        } else if (mapNether[i][j].equals("furnace")) {
                            img[i][j].setImage(furnace);
                        } else if (mapNether[i][j].equals("zombieOverGrass")) {
                            img[i][j].setImage(zombieOverGrass);
                        } else if (mapNether[i][j].equals("zombieOverStone")) {
                            img[i][j].setImage(zombieOverStone);
                        } else if (mapNether[i][j].equals("coalOre")) {
                            img[i][j].setImage(coalOre);
                        } else if (mapNether[i][j].equals("spiderOverStone")) {
                            img[i][j].setImage(spiderOverStone);
                        } else if (mapNether[i][j].equals("spiderOverGrass")) {
                            img[i][j].setImage(spiderOverGrass);
                        } else if (mapNether[i][j].equals("creeperOverStone")) {
                            img[i][j].setImage(creeperOverStone);
                        } else if (mapNether[i][j].equals("creeperOverGrass")) {
                            img[i][j].setImage(creeperOverGrass);
                        } else if (mapNether[i][j].startsWith("torch")) {
                            //torch over netherrack
                        } else if (mapNether[i][j].equals("villager")) {
                            img[i][j].setImage(villager);
                        } else if (mapNether[i][j].equals("obsidian")) {
                            img[i][j].setImage(obsidian);
                        } else if (mapNether[i][j].equals("netherPortal")) {
                            img[i][j].setImage(netherPortalImg);
                        }else if (mapNether[i][j].equals("netherrack")) {
                            img[i][j].setImage(netherrack);
                        }else if (mapNether[i][j].equals("netherrackWX")) {
                            img[i][j].setImage(netherrackWX);
                        }else if (mapNether[i][j].equals("playerOverNetherrack")) {
                            img[i][j].setImage(playerOverNetherrack);
                        }else if (mapNether[i][j].equals("lava")) {
                            img[i][j].setImage(lava);
                        }else if (mapNether[i][j].equals("playerInLava")) {
                            img[i][j].setImage(playerInLava);
                        }else if (mapNether[i][j].equals("witherOverNetherrack")) {
                            img[i][j].setImage(witherOverNetherrack);
                        }else if (mapNether[i][j].equals("witherOverLava")) {
                            img[i][j].setImage(witherOverLava);
                        }else if (mapNether[i][j].equals("witherHead")) {
                            img[i][j].setImage(witherHeadI);
                        }

                        if (mapNightNether[i][j].equals("light")) {
                            mapNight[i][j].setOpacity(0.0);
                        } else if (mapNightNether[i][j].equals("night")) {
                            mapNight[i][j].setOpacity(0.5);
                        }
                    }
                }
            } else if (!inCave) {
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
                        } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("stone")) {
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
                        } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("autumnWood")) {
                            img[i][j].setImage(autumnWood);
                        } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("fruitWood")) {
                            img[i][j].setImage(fruitWood);
                        } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("sheep")) {
                            img[i][j].setImage(sheep);
                        } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("fruitPlank")) {
                            img[i][j].setImage(fruitPlank);
                        } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("normalPlank")) {
                            img[i][j].setImage(normalPlank);
                        } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("autumnPlank")) {
                            img[i][j].setImage(autumnPlank);
                        } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("craftingTable")) {
                            img[i][j].setImage(craftingTable);
                        } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("boat")) {
                            img[i][j].setImage(boat);
                        } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("cow")) {
                            img[i][j].setImage(cow);
                        } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("pig")) {
                            img[i][j].setImage(pig);
                        } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("furnace")) {
                            img[i][j].setImage(furnace);
                        } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("zombieOverGrass")) {
                            img[i][j].setImage(zombieOverGrass);
                        } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("zombieOverStone")) {
                            img[i][j].setImage(zombieOverStone);
                        } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("coalOre")) {
                            img[i][j].setImage(coalOre);
                        } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("spiderOverStone")) {
                            img[i][j].setImage(spiderOverStone);
                        } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("spiderOverGrass")) {
                            img[i][j].setImage(spiderOverGrass);
                        } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("creeperOverStone")) {
                            img[i][j].setImage(creeperOverStone);
                        } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("creeperOverGrass")) {
                            img[i][j].setImage(creeperOverGrass);
                        } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].startsWith("torch")) {
                            if (mapBackground[tempPlayerPositionX - 12 + j][tempPlayerPositionY - 20 + j].equals("grass") || mapBackground[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("normal") || mapBackground[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("fruit") || mapBackground[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("autumn")) {
                                img[i][j].setImage(torchOverGrass);
                            } else {
                                img[i][j].setImage(torchOverStone);
                            }
                        } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("villager")) {
                            img[i][j].setImage(villager);
                        } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("obsidian")) {
                            img[i][j].setImage(obsidian);
                        } else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("netherPortal")) {
                            img[i][j].setImage(netherPortalImg);
                        }

                        if (mapNightS[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("light")) {
                            mapNight[i][j].setOpacity(0.0);
                        } else if (mapNightS[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("night")) {
                            mapNight[i][j].setOpacity(0.5);
                            if (mapBackground[tempPlayerPositionX - 12 + i][j ].equals("grass") || mapBackground[tempPlayerPositionX - 12 + i][j ].equals("normal") || mapBackground[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("fruit") || mapBackground[tempPlayerPositionX - 12 + i][j ].equals("autumn")) {
                                img[i][j].setImage(grass);
                            } else {
                                img[i][j].setImage(stone);
                            }
                        }
                        if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("playerOverStone")) {
                            img[i][j].setImage(playerOverStone);
                        }else if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("playerOverGrass")) {
                            img[i][j].setImage(playerOverGrass); //steve
                        }
                    }
                }
            } else{
                for (int i = 0; i < img.length; i++) {
                    for (int j = 0; j < img[0].length; j++) {
                        if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("grass")) {
                            img[i][j].setImage(grass);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("grassWX")) {
                            img[i][j].setImage(grassWX); //steve
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("playerOverGrass")) {
                            img[i][j].setImage(playerOverGrass); //steve
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("normalTree")) {
                            img[i][j].setImage(normalTree);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("fruitTree")) {
                            img[i][j].setImage(fruitTree);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("autumnTree")) {
                            img[i][j].setImage(autumnTree);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("stone")) {
                            img[i][j].setImage(stone);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("playerOverStone")) {
                            img[i][j].setImage(playerOverStone);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("rock")) {
                            img[i][j].setImage(rock);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("goldOre")) {
                            img[i][j].setImage(goldOre);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("rubyOre")) {
                            img[i][j].setImage(rubyOre);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("diamondOre")) {
                            img[i][j].setImage(diamondOre);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("water")) {
                            img[i][j].setImage(water);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("null")) {
                            img[i][j].setImage(null);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("normalWood")) {
                            img[i][j].setImage(normalWood);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("autumnWood")) {
                            img[i][j].setImage(autumnWood);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("fruitWood")) {
                            img[i][j].setImage(fruitWood);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("sheep")) {
                            img[i][j].setImage(sheep);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("fruitPlank")) {
                            img[i][j].setImage(fruitPlank);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("normalPlank")) {
                            img[i][j].setImage(normalPlank);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("autumnPlank")) {
                            img[i][j].setImage(autumnPlank);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("craftingTable")) {
                            img[i][j].setImage(craftingTable);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("boat")) {
                            img[i][j].setImage(boat);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("cow")) {
                            img[i][j].setImage(cow);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("pig")) {
                            img[i][j].setImage(pig);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("furnace")) {
                            img[i][j].setImage(furnace);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("zombieOverGrass")) {
                            img[i][j].setImage(zombieOverGrass);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("zombieOverStone")) {
                            img[i][j].setImage(zombieOverStone);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("coalOre")) {
                            img[i][j].setImage(coalOre);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("spiderOverStone")) {
                            img[i][j].setImage(spiderOverStone);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("spiderOverGrass")) {
                            img[i][j].setImage(spiderOverGrass);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("creeperOverStone")) {
                            img[i][j].setImage(creeperOverStone);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("creeperOverGrass")) {
                            img[i][j].setImage(creeperOverGrass);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].startsWith("torch")) {
                            if (mapBackgroundCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("grass") || mapBackgroundCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("normal") || mapBackgroundCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("fruit") || mapBackgroundCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("autumn")) {
                                img[i][j].setImage(torchOverGrass);
                            } else {
                                img[i][j].setImage(torchOverStone);
                            }
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("villager")) {
                            img[i][j].setImage(villager);
                        } else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("stoneWX")) {
                            img[i][j].setImage(stoneWX);
                        }else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("obsidian")) {
                            img[i][j].setImage(obsidian);
                        }else if (mapCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("netherPortal")) {
                            img[i][j].setImage(netherPortalImg);
                        }

                        if (mapNightCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("light")) {
                            mapNight[i][j].setOpacity(0.5);
                        } else if (mapNightCave[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("night")) {
                            mapNight[i][j].setOpacity(0.8);
                            img[i][j].setImage(stone);
                        }

                        if (map[tempPlayerPositionX - 12 + i][tempPlayerPositionY - 20 + j].equals("playerOverStone")) {
                            img[i][j].setImage(playerOverStone);
                        }
                    }
                }

            }
        } else {
            //UPDATE SCREEN
            if(inNether) {
                for (int i = 0; i < img.length; i++) {
                    for (int j = 0; j < img[0].length; j++) {
                        if (mapNether[i][j].equals("grass")) {
                            img[i][j].setImage(grass);
                        } else if (mapNether[i][j].equals("grassWX")) {
                            img[i][j].setImage(grassWX); //steve
                        } else if (mapNether[i][j].equals("playerOverGrass")) {
                            img[i][j].setImage(playerOverGrass); //steve
                        } else if (mapNether[i][j].equals("normalTree")) {
                            img[i][j].setImage(normalTree); //steve
                        } else if (mapNether[i][j].equals("fruitTree")) {
                            img[i][j].setImage(fruitTree); //steve
                        } else if (mapNether[i][j].equals("autumnTree")) {
                            img[i][j].setImage(autumnTree); //steve
                        } else if (mapNether[i][j].equals("stone")) {
                            img[i][j].setImage(stone);
                        } else if (mapNether[i][j].equals("playerOverStone")) {
                            img[i][j].setImage(playerOverStone);
                        } else if (mapNether[i][j].equals("rock")) {
                            img[i][j].setImage(rock);
                        } else if (mapNether[i][j].equals("rubyOre")) {
                            img[i][j].setImage(rubyOre);
                        } else if (mapNether[i][j].equals("diamondOre")) {
                            img[i][j].setImage(diamondOre);
                        } else if (mapNether[i][j].equals("goldOre")) {
                            img[i][j].setImage(goldOre);
                        } else if (mapNether[i][j].equals("water")) {
                            img[i][j].setImage(water);
                        } else if (mapNether[i][j].equals("fruitQuest")) {
                            img[i][j].setImage(mailboxGrass);
                        } else if (mapNether[i][j].equals("autumnQuest")) {
                            img[i][j].setImage(mailboxGrass);
                        } else if (mapNether[i][j].equals("normalQuest")) {
                            img[i][j].setImage(mailboxGrass);
                        } else if (mapNether[i][j].equals("stoneQuest")) {
                            img[i][j].setImage(mailboxStone);
                        } else if (mapNether[i][j].equals("waterQuest")) {
                            img[i][j].setImage(chestWater);
                        } else if (mapNether[i][j].equals("null")) {
                            img[i][j].setImage(null); //steve
                        } else if (mapNether[i][j].equals("normalWood")) {
                            img[i][j].setImage(normalWood); //steve
                        } else if (mapNether[i][j].equals("autumnWood")) {
                            img[i][j].setImage(autumnWood); //steve
                        } else if (mapNether[i][j].equals("fruitWood")) {
                            img[i][j].setImage(fruitWood); //steve
                        } else if (mapNether[i][j].equals("sheep")) {
                            img[i][j].setImage(sheep); //steve
                        } else if (mapNether[i][j].equals("fruitPlank")) {
                            img[i][j].setImage(fruitPlank); //steve
                        } else if (mapNether[i][j].equals("normalPlank")) {
                            img[i][j].setImage(normalPlank); //steve
                        } else if (mapNether[i][j].equals("autumnPlank")) {
                            img[i][j].setImage(autumnPlank); //steve
                        } else if (mapNether[i][j].equals("craftingTable")) {
                            img[i][j].setImage(craftingTable); //steve
                        } else if (mapNether[i][j].equals("boat")) {
                            img[i][j].setImage(boat); //steve
                        } else if (mapNether[i][j].equals("cow")) {
                            img[i][j].setImage(cow); //steve
                        } else if (mapNether[i][j].equals("pig")) {
                            img[i][j].setImage(pig); //steve
                        } else if (mapNether[i][j].equals("furnace")) {
                            img[i][j].setImage(furnace); //steve
                        } else if (mapNether[i][j].equals("zombieOverGrass")) {
                            img[i][j].setImage(zombieOverGrass); //steve
                        } else if (mapNether[i][j].equals("zombieOverStone")) {
                            img[i][j].setImage(zombieOverStone); //steve
                        } else if (mapNether[i][j].equals("coalOre")) {
                            img[i][j].setImage(coalOre); //steve
                        } else if (mapNether[i][j].equals("spiderOverGrass")) {
                            img[i][j].setImage(spiderOverGrass); //steve
                        } else if (mapNether[i][j].equals("spiderOverStone")) {
                            img[i][j].setImage(spiderOverStone); //steve
                        } else if (mapNether[i][j].equals("creeperOverGrass")) {
                            img[i][j].setImage(creeperOverGrass); //steve
                        } else if (mapNether[i][j].equals("creeperOverStone")) {
                            img[i][j].setImage(creeperOverStone); //steve
                        } else if (mapNether[i][j].startsWith("torch")) {
                            //torch over netherrack
                        } else if (mapNether[i][j].equals("villager")) {
                            img[i][j].setImage(villager); //steve
                        }else if (mapNether[i][j].equals("obsidian")) {
                            img[i][j].setImage(obsidian); //steve
                        }else if (mapNether[i][j].equals("netherPortal")) {
                            img[i][j].setImage(netherPortalImg); //steve
                        }else if (mapNether[i][j].equals("netherrack")) {
                            img[i][j].setImage(netherrack); //steve
                        }else if (mapNether[i][j].equals("netherrackWX")) {
                            img[i][j].setImage(netherrackWX); //steve
                        }else if (mapNether[i][j].equals("playerOverNetherrack")) {
                            img[i][j].setImage(playerOverNetherrack); //steve
                        }else if (mapNether[i][j].equals("lava")) {
                            img[i][j].setImage(lava);
                        }else if (mapNether[i][j].equals("playerInLava")) {
                            img[i][j].setImage(playerInLava);
                        }else if (mapNether[i][j].equals("witherOverNetherrack")) {
                            img[i][j].setImage(witherOverNetherrack);
                        }else if (mapNether[i][j].equals("witherOverLava")) {
                            img[i][j].setImage(witherOverLava);
                        }else if (mapNether[i][j].equals("witherHead")) {
                            img[i][j].setImage(witherHeadI);
                        }

                        if (mapNightNether[i][j].equals("light")) {
                            mapNight[i][j].setOpacity(0.0); //steve
                        } else if (mapNightNether[i][j].equals("night")) {
                            mapNight[i][j].setOpacity(0.5); //steve
                        }
                    }
                }
            }else if (!inCave) {
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
                        } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("normalWood")) {
                            img[i][j].setImage(normalWood); //steve
                        } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("autumnWood")) {
                            img[i][j].setImage(autumnWood); //steve
                        } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("fruitWood")) {
                            img[i][j].setImage(fruitWood); //steve
                        } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("sheep")) {
                            img[i][j].setImage(sheep); //steve
                        } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("fruitPlank")) {
                            img[i][j].setImage(fruitPlank); //steve
                        } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("normalPlank")) {
                            img[i][j].setImage(normalPlank); //steve
                        } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("autumnPlank")) {
                            img[i][j].setImage(autumnPlank); //steve
                        } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("craftingTable")) {
                            img[i][j].setImage(craftingTable); //steve
                        } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("boat")) {
                            img[i][j].setImage(boat); //steve
                        } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("cow")) {
                            img[i][j].setImage(cow); //steve
                        } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("pig")) {
                            img[i][j].setImage(pig); //steve
                        } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("furnace")) {
                            img[i][j].setImage(furnace); //steve
                        } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("zombieOverGrass")) {
                            img[i][j].setImage(zombieOverGrass); //steve
                        } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("zombieOverStone")) {
                            img[i][j].setImage(zombieOverStone); //steve
                        } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("coalOre")) {
                            img[i][j].setImage(coalOre); //steve
                        } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("spiderOverGrass")) {
                            img[i][j].setImage(spiderOverGrass); //steve
                        } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("spiderOverStone")) {
                            img[i][j].setImage(spiderOverStone); //steve
                        } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("creeperOverGrass")) {
                            img[i][j].setImage(creeperOverGrass); //steve
                        } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("creeperOverStone")) {
                            img[i][j].setImage(creeperOverStone); //steve
                        } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].startsWith("torch")) {
                            if (mapBackground[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("grass") || mapBackground[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("normal") || mapBackground[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("fruit") || mapBackground[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("autumn")) {
                                img[i][j].setImage(torchOverGrass);
                            } else {
                                img[i][j].setImage(torchOverStone);
                            }
                        } else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("villager")) {
                            img[i][j].setImage(villager); //steve
                        }else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("obsidian")) {
                            img[i][j].setImage(obsidian); //steve
                        }else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("netherPortal")) {
                            img[i][j].setImage(netherPortalImg); //steve
                        }

                        if (mapNightS[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("light")) {
                            mapNight[i][j].setOpacity(0.0); //steve
                        } else if (mapNightS[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("night")) {
                            mapNight[i][j].setOpacity(0.5);
                            if (mapBackground[playerPositionX - 12 + i][j ].equals("grass") || mapBackground[playerPositionX - 12 + i][j ].equals("normal") || mapBackground[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("fruit") || mapBackground[playerPositionX - 12 + i][j ].equals("autumn")) {
                                img[i][j].setImage(grass);
                            } else {
                                img[i][j].setImage(stone);
                            }
                        }
                        if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("playerOverStone")) {
                            img[i][j].setImage(playerOverStone);
                        }else if (map[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("playerOverGrass")) {
                            img[i][j].setImage(playerOverGrass); //steve
                        }
                        
                    }
                }
            } else{
                for (int i = 0; i < img.length; i++) {
                    for (int j = 0; j < img[0].length; j++) {
                        if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("grass")) {
                            img[i][j].setImage(grass);
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("grassWX")) {
                            img[i][j].setImage(grassWX); //steve
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("playerOverGrass")) {
                            img[i][j].setImage(playerOverGrass); //steve
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("normalTree")) {
                            img[i][j].setImage(normalTree); //steve
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("fruitTree")) {
                            img[i][j].setImage(fruitTree); //steve
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("autumnTree")) {
                            img[i][j].setImage(autumnTree); //steve
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("stone")) {
                            img[i][j].setImage(stone);
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("playerOverStone")) {
                            img[i][j].setImage(playerOverStone);
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("rock")) {
                            img[i][j].setImage(rock);
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("rubyOre")) {
                            img[i][j].setImage(rubyOre);
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("diamondOre")) {
                            img[i][j].setImage(diamondOre);
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("goldOre")) {
                            img[i][j].setImage(goldOre);
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("water")) {
                            img[i][j].setImage(water);
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("fruitQuest")) {
                            img[i][j].setImage(mailboxGrass);
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("autumnQuest")) {
                            img[i][j].setImage(mailboxGrass);
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("normalQuest")) {
                            img[i][j].setImage(mailboxGrass);
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("stoneQuest")) {
                            img[i][j].setImage(mailboxStone);
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("waterQuest")) {
                            img[i][j].setImage(chestWater);
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("null")) {
                            img[i][j].setImage(null); //steve
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("normalWood")) {
                            img[i][j].setImage(normalWood); //steve
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("autumnWood")) {
                            img[i][j].setImage(autumnWood); //steve
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("fruitWood")) {
                            img[i][j].setImage(fruitWood); //steve
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("sheep")) {
                            img[i][j].setImage(sheep); //steve
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("fruitPlank")) {
                            img[i][j].setImage(fruitPlank); //steve
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("normalPlank")) {
                            img[i][j].setImage(normalPlank); //steve
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("autumnPlank")) {
                            img[i][j].setImage(autumnPlank); //steve
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("craftingTable")) {
                            img[i][j].setImage(craftingTable); //steve
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("boat")) {
                            img[i][j].setImage(boat); //steve
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("cow")) {
                            img[i][j].setImage(cow); //steve
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("pig")) {
                            img[i][j].setImage(pig); //steve
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("furnace")) {
                            img[i][j].setImage(furnace); //steve
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("zombieOverGrass")) {
                            img[i][j].setImage(zombieOverGrass); //steve
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("zombieOverStone")) {
                            img[i][j].setImage(zombieOverStone); //steve
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("coalOre")) {
                            img[i][j].setImage(coalOre); //steve
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("spiderOverGrass")) {
                            img[i][j].setImage(spiderOverGrass); //steve
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("spiderOverStone")) {
                            img[i][j].setImage(spiderOverStone); //steve
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("creeperOverGrass")) {
                            img[i][j].setImage(creeperOverGrass); //steve
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("creeperOverStone")) {
                            img[i][j].setImage(creeperOverStone); //steve
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].startsWith("torch")) {
                            if (mapBackgroundCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("grass") || mapBackgroundCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("normal") || mapBackgroundCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("fruit") || mapBackgroundCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("autumn")) {
                                img[i][j].setImage(torchOverGrass);
                            } else {
                                img[i][j].setImage(torchOverStone);
                            }
                        } else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("villager")) {
                            img[i][j].setImage(villager); //steve
                        }else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("stoneWX")) {
                            img[i][j].setImage(stoneWX); //steve
                        }else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("obsidian")) {
                            img[i][j].setImage(obsidian); //steve
                        }else if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("netherPortal")) {
                            img[i][j].setImage(netherPortalImg); //steve
                        }





                        if (mapNightCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("light")) {
                            mapNight[i][j].setOpacity(0.5); //steve
                        } else if (mapNightCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("night")) {
                            mapNight[i][j].setOpacity(0.8); //steve
                            img[i][j].setImage(stone);
                        }

                        if (mapCave[playerPositionX - 12 + i][playerPositionY - 20 + j].equals("playerOverStone")) {
                            img[i][j].setImage(playerOverStone);
                        }
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
        if(inventoryA[2][7].getAmount()!=0&&inventoryA[2][7].getAmount()!=1){
            tradingLabel.setText(Integer.toString(inventoryA[2][7].getAmount()));
        }else{
            tradingLabel.setText("");
        }
        //System.out.println(inventoryA[4][selected].getName());
        for (int i = 1; i < 6; i++) {
            hotbar[i-1]=inventoryA[4][i];
        }

        for (int i = 0; i < hotbar.length; i++) {
            switch (hotbar[i].getName()) {
                case "normalWood" -> hotbarImg[i][0].setImage(normalWoodInv);
                case "autumnWood" -> hotbarImg[i][0].setImage(autumnWoodInv);
                case "fruitWood" -> hotbarImg[i][0].setImage(fruitWoodInv);
                case "apples" -> hotbarImg[i][0].setImage(appleInv);
                case "diamond" -> hotbarImg[i][0].setImage(diamondInv);
                case "goldIngot" -> hotbarImg[i][0].setImage(goldIngotInv);
                case "ruby" -> hotbarImg[i][0].setImage(rubyInv);
                case "cobblestone" -> hotbarImg[i][0].setImage(cobbelstoneInv);
                case "woodAxe" -> hotbarImg[i][0].setImage(woodAxeInv);
                case "normalPlank" -> hotbarImg[i][0].setImage(normalPlankInv);
                case "autumnPlank" -> hotbarImg[i][0].setImage(autumnPlankInv);
                case "fruitPlank" -> hotbarImg[i][0].setImage(fruitPlankInv);
                case "craftingTable" -> hotbarImg[i][0].setImage(craftingTableInv);
                case "boat" -> hotbarImg[i][0].setImage(boatInv);
                case "stick" -> hotbarImg[i][0].setImage(stickInv);
                case "woodSword" -> hotbarImg[i][0].setImage(woodSwordInv);
                case "woodPickaxe" -> hotbarImg[i][0].setImage(woodPickaxeInv);
                case "rawMutton" -> hotbarImg[i][0].setImage(rawMuttonInv);
                case "rawPork" -> hotbarImg[i][0].setImage(rawPorkInv);
                case "rawBeef" -> hotbarImg[i][0].setImage(rawBeefInv);
                case "furnace" -> hotbarImg[i][0].setImage(furnaceInv);
                case "stoneSword" -> hotbarImg[i][0].setImage(stoneSwordInv);
                case "rubySword" -> hotbarImg[i][0].setImage(rubySwordInv);
                case "goldSword" -> hotbarImg[i][0].setImage(goldSwordInv);
                case "diamondSword" -> hotbarImg[i][0].setImage(diamondSwordInv);
                case "stoneAxe" -> hotbarImg[i][0].setImage(stoneAxeInv);
                case "rubyAxe" -> hotbarImg[i][0].setImage(rubyAxeInv);
                case "goldAxe" -> hotbarImg[i][0].setImage(goldAxeInv);
                case "diamondAxe" -> hotbarImg[i][0].setImage(diamondAxeInv);
                case "stonePickaxe" -> hotbarImg[i][0].setImage(stonePickaxeInv);
                case "rubyPickaxe" -> hotbarImg[i][0].setImage(rubyPickaxeInv);
                case "goldPickaxe" -> hotbarImg[i][0].setImage(goldPickaxeInv);
                case "diamondPickaxe" -> hotbarImg[i][0].setImage(diamondPickaxeInv);
                case "woodHelmet" -> hotbarImg[i][0].setImage(woodHelmetInv);
                case "woodChestplate" -> hotbarImg[i][0].setImage(woodChestplateInv);
                case "woodLeggings" -> hotbarImg[i][0].setImage(woodLeggingsInv);
                case "woodBoots" -> hotbarImg[i][0].setImage(woodBootsInv);
                case "rubyHelmet" -> hotbarImg[i][0].setImage(rubyHelmetInv);
                case "rubyChestplate" -> hotbarImg[i][0].setImage(rubyChestplateInv);
                case "rubyLeggings" -> hotbarImg[i][0].setImage(rubyLeggingsInv);
                case "rubyBoots" -> hotbarImg[i][0].setImage(rubyBootsInv);
                case "goldHelmet" -> hotbarImg[i][0].setImage(goldHelmetInv);
                case "goldChestplate" -> hotbarImg[i][0].setImage(goldChestplateInv);
                case "goldLeggings" -> hotbarImg[i][0].setImage(goldLeggingsInv);
                case "goldBoots" -> hotbarImg[i][0].setImage(goldBootsInv);
                case "diamondHelmet" -> hotbarImg[i][0].setImage(diamondHelmetInv);
                case "diamondChestplate" -> hotbarImg[i][0].setImage(diamondChestplateInv);
                case "diamondLeggings" -> hotbarImg[i][0].setImage(diamondLeggingsInv);
                case "diamondBoots" -> hotbarImg[i][0].setImage(diamondBootsInv);
                case "rottenFlesh" -> hotbarImg[i][0].setImage(rottenFlesh);
                case "rubyOre" -> hotbarImg[i][0].setImage(rubyOreInv);
                case "coal" -> hotbarImg[i][0].setImage(coalInv);
                case "goldOre" -> hotbarImg[i][0].setImage(goldOreInv);
                case "cookedPork" -> hotbarImg[i][0].setImage(cookedPorkInv);
                case "cookedBeef" -> hotbarImg[i][0].setImage(cookedBeefInv);
                case "cookedMutton" -> hotbarImg[i][0].setImage(cookedMuttonInv);
                case "torch" -> hotbarImg[i][0].setImage(torchInv);
                case "flint" -> hotbarImg[i][0].setImage(flintInv);
                case "obsidian" -> hotbarImg[i][0].setImage(obsidianInv);
                case "drill" -> hotbarImg[i][0].setImage(drillInv);
                case "bossSoul" -> hotbarImg[i][0].setImage(bossSoulInv);
                case "flintAndSteel" -> hotbarImg[i][0].setImage(flintAndSteelInv);


                case "empty" -> hotbarImg[i][0].setImage(grayBack);
            }
        }

        for (int i = 0; i <inventoryA.length; i++) {
            for (int j = 0; j <inventoryA[0].length; j++) {
                switch (inventoryA[i][j].getName()) {
                    case "normalWood" -> inventoryImg[i][j].setImage(normalWoodInv);
                    case "autumnWood" -> inventoryImg[i][j].setImage(autumnWoodInv);
                    case "fruitWood" -> inventoryImg[i][j].setImage(fruitWoodInv);
                    case "apples" -> inventoryImg[i][j].setImage(appleInv);
                    case "diamond" -> inventoryImg[i][j].setImage(diamondInv);
                    case "goldIngot" -> inventoryImg[i][j].setImage(goldIngotInv);
                    case "ruby" -> inventoryImg[i][j].setImage(rubyInv);
                    case "cobblestone" -> inventoryImg[i][j].setImage(cobbelstoneInv);
                    case "woodAxe" -> inventoryImg[i][j].setImage(woodAxeInv);
                    case "normalPlank" -> inventoryImg[i][j].setImage(normalPlankInv);
                    case "autumnPlank" -> inventoryImg[i][j].setImage(autumnPlankInv);
                    case "fruitPlank" -> inventoryImg[i][j].setImage(fruitPlankInv);
                    case "craftingTable" -> inventoryImg[i][j].setImage(craftingTableInv);
                    case "stick" -> inventoryImg[i][j].setImage(stickInv);
                    case "boat" -> inventoryImg[i][j].setImage(boatInv);
                    case "woodSword" -> inventoryImg[i][j].setImage(woodSwordInv);
                    case "woodPickaxe" -> inventoryImg[i][j].setImage(woodPickaxeInv);
                    case "rawMutton" -> inventoryImg[i][j].setImage(rawMuttonInv);
                    case "rawBeef" -> inventoryImg[i][j].setImage(rawBeefInv);
                    case "rawPork" -> inventoryImg[i][j].setImage(rawPorkInv);
                    case "furnace" -> inventoryImg[i][j].setImage(furnaceInv);
                    case "stoneSword" -> inventoryImg[i][j].setImage(stoneSwordInv);
                    case "rubySword" -> inventoryImg[i][j].setImage(rubySwordInv);
                    case "goldSword" -> inventoryImg[i][j].setImage(goldSwordInv);
                    case "diamondSword" -> inventoryImg[i][j].setImage(diamondSwordInv);
                    case "stoneAxe" -> inventoryImg[i][j].setImage(stoneAxeInv);
                    case "rubyAxe" -> inventoryImg[i][j].setImage(rubyAxeInv);
                    case "goldAxe" -> inventoryImg[i][j].setImage(goldAxeInv);
                    case "diamondAxe" -> inventoryImg[i][j].setImage(diamondAxeInv);
                    case "stonePickaxe" -> inventoryImg[i][j].setImage(stonePickaxeInv);
                    case "rubyPickaxe" -> inventoryImg[i][j].setImage(rubyPickaxeInv);
                    case "goldPickaxe" -> inventoryImg[i][j].setImage(goldPickaxeInv);
                    case "diamondPickaxe" -> inventoryImg[i][j].setImage(diamondPickaxeInv);
                    case "woodHelmet" -> inventoryImg[i][j].setImage(woodHelmetInv);
                    case "woodChestplate" -> inventoryImg[i][j].setImage(woodChestplateInv);
                    case "woodLeggings" -> inventoryImg[i][j].setImage(woodLeggingsInv);
                    case "woodBoots" -> inventoryImg[i][j].setImage(woodBootsInv);
                    case "rubyHelmet" -> inventoryImg[i][j].setImage(rubyHelmetInv);
                    case "rubyChestplate" -> inventoryImg[i][j].setImage(rubyChestplateInv);
                    case "rubyLeggings" -> inventoryImg[i][j].setImage(rubyLeggingsInv);
                    case "rubyBoots" -> inventoryImg[i][j].setImage(rubyBootsInv);
                    case "goldHelmet" -> inventoryImg[i][j].setImage(goldHelmetInv);
                    case "goldChestplate" -> inventoryImg[i][j].setImage(goldChestplateInv);
                    case "goldLeggings" -> inventoryImg[i][j].setImage(goldLeggingsInv);
                    case "goldBoots" -> inventoryImg[i][j].setImage(goldBootsInv);
                    case "diamondHelmet" -> inventoryImg[i][j].setImage(diamondHelmetInv);
                    case "diamondChestplate" -> inventoryImg[i][j].setImage(diamondChestplateInv);
                    case "diamondLeggings" -> inventoryImg[i][j].setImage(diamondLeggingsInv);
                    case "diamondBoots" -> inventoryImg[i][j].setImage(diamondBootsInv);
                    case "rottenFlesh" -> inventoryImg[i][j].setImage(rottenFlesh);
                    case "rubyOre" -> inventoryImg[i][j].setImage(rubyOreInv);
                    case "coal" -> inventoryImg[i][j].setImage(coalInv);
                    case "goldOre" -> inventoryImg[i][j].setImage(goldOreInv);
                    case "cookedBeef" -> inventoryImg[i][j].setImage(cookedBeefInv);
                    case "cookedPork" -> inventoryImg[i][j].setImage(cookedPorkInv);
                    case "cookedMutton" -> inventoryImg[i][j].setImage(cookedMuttonInv);
                    case "torch" -> inventoryImg[i][j].setImage(torchInv);
                    case "flint" -> inventoryImg[i][j].setImage(flintInv);
                    case "bossSoul" -> inventoryImg[i][j].setImage(bossSoulInv);
                    case "obsidian" -> inventoryImg[i][j].setImage(obsidianInv);
                    case "drill" -> inventoryImg[i][j].setImage(drillInv);
                    case "flintAndSteel" -> inventoryImg[i][j].setImage(flintAndSteelInv);



                    case "empty" -> inventoryImg[i][j].setImage(grayBack);
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


            ///TORCHES
            else if(tl.getName().equals("coal")&&tr.getName().equals("empty")&&bl.getName().equals("stick")&&br.getName().equals("empty")){
                inventoryA[4][9] = new Resources("torch","axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("coal")&&bl.getName().equals("empty")&&br.getName().equals("stick")){
                inventoryA[4][9] = new Resources("torch","axe");
                inventoryA[4][9].setAmount(4);
            }
            //flint and steel
            else if(tl.getName().equals("ruby")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("flint")){
                inventoryA[4][9] = new inventoryItems("flintAndSteel");
            }


            else{
                if(!tradingShowing){
                    inventoryA[4][9] = new inventoryItems("empty");
                }
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
                inventoryA[4][9] = new Tools("woodSword",1, "sword", 4, 20);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("normalPlank")&&tr.getName().equals("empty")&&bl.getName().equals("stick")&&br.getName().equals("empty")&&tlc.getName().equals("normalPlank")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("woodSword",1, "sword", 4, 20);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("normalPlank")&&mrc.getName().equals("normalPlank")&&brc.getName().equals("stick")){
                inventoryA[4][9] = new Tools("woodSword",1, "sword", 4, 20);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("empty")&&tr.getName().equals("autumnPlank")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("empty")&&tmc.getName().equals("autumnPlank")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("woodSword",1, "sword", 4, 20);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("autumnPlank")&&tr.getName().equals("empty")&&bl.getName().equals("stick")&&br.getName().equals("empty")&&tlc.getName().equals("autumnPlank")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("woodSword",1, "sword", 4, 20);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("autumnPlank")&&mrc.getName().equals("autumnPlank")&&brc.getName().equals("stick")){
                inventoryA[4][9] = new Tools("woodSword",1, "sword", 4, 20);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("empty")&&tr.getName().equals("fruitPlank")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("empty")&&tmc.getName().equals("fruitPlank")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("woodSword",1, "sword", 4, 20);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("fruitPlank")&&tr.getName().equals("empty")&&bl.getName().equals("stick")&&br.getName().equals("empty")&&tlc.getName().equals("fruitPlank")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("woodSword",1, "sword", 4, 20);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("fruitPlank")&&mrc.getName().equals("fruitPlank")&&brc.getName().equals("stick")){
                inventoryA[4][9] = new Tools("woodSword",1, "sword", 4, 20);
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
                inventoryA[4][9] = new Tools("rubySword",3, "sword", 4, 40);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("ruby")&&tr.getName().equals("empty")&&bl.getName().equals("stick")&&br.getName().equals("empty")&&tlc.getName().equals("ruby")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("rubySword",3, "sword", 4, 40);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("ruby")&&mrc.getName().equals("ruby")&&brc.getName().equals("stick")){
                inventoryA[4][9] = new Tools("rubySword",3, "sword", 4, 40);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("empty")&&tr.getName().equals("goldIngot")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("empty")&&tmc.getName().equals("goldIngot")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("goldSword",4, "sword", 4, 50);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("goldIngot")&&tr.getName().equals("empty")&&bl.getName().equals("stick")&&br.getName().equals("empty")&&tlc.getName().equals("goldIngot")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("goldSword",4, "sword", 4, 50);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("goldIngot")&&mrc.getName().equals("goldIngot")&&brc.getName().equals("stick")){
                inventoryA[4][9] = new Tools("goldSword",4, "sword", 4, 50);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("empty")&&tr.getName().equals("diamond")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("empty")&&tmc.getName().equals("diamond")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("diamondSword",5, "sword", 4, 60);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("diamond")&&tr.getName().equals("empty")&&bl.getName().equals("stick")&&br.getName().equals("empty")&&tlc.getName().equals("diamond")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("diamondSword",5, "sword", 4, 60);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("diamond")&&mrc.getName().equals("diamond")&&brc.getName().equals("stick")){
                inventoryA[4][9] = new Tools("diamondSword",5, "sword", 4, 60);
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
                inventoryA[4][9] = new Tools("stoneAxe",2, "axe", 3, 30);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("empty")&&tmc.getName().equals("cobblestone")&&trc.getName().equals("cobblestone")&&mrc.getName().equals("cobblestone")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("stoneAxe",2, "axe", 3, 30);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("ruby")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("ruby")&&tmc.getName().equals("ruby")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("rubyAxe",3, "axe", 3, 40);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("empty")&&tmc.getName().equals("ruby")&&trc.getName().equals("ruby")&&mrc.getName().equals("ruby")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("rubyAxe",3, "axe", 3, 40);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("goldIngot")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("goldIngot")&&tmc.getName().equals("goldIngot")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("goldAxe",4, "axe", 3, 50);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("empty")&&tmc.getName().equals("goldIngot")&&trc.getName().equals("goldIngot")&&mrc.getName().equals("goldIngot")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("goldAxe",4, "axe", 3, 50);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("diamond")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("diamond")&&tmc.getName().equals("diamond")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("diamondAxe",5, "axe", 3, 60);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("empty")&&tmc.getName().equals("diamond")&&trc.getName().equals("diamond")&&mrc.getName().equals("diamond")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("diamondAxe",5, "axe", 3, 60);
                inventoryA[4][9].setAmount(1);
            }


            //////////////////////PICKAXES
            else if(tl.getName().equals("empty")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("normalPlank")&&tmc.getName().equals("normalPlank")&&trc.getName().equals("normalPlank")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")) {
                inventoryA[4][9] = new Tools("woodPickaxe", 1, "pickaxe", 2, 20);
                inventoryA[4][9].setAmount(1);
            } else if(tl.getName().equals("empty")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("autumnPlank")&&tmc.getName().equals("autumnPlank")&&trc.getName().equals("autumnPlank")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("woodPickaxe",1, "pickaxe", 2, 20);
                inventoryA[4][9].setAmount(1);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("fruitPlank")&&tmc.getName().equals("fruitPlank")&&trc.getName().equals("fruitPlank")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("woodPickaxe",1, "pickaxe", 2, 20);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("empty")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("cobblestone")&&tmc.getName().equals("cobblestone")&&trc.getName().equals("cobblestone")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("stonePickaxe",2, "pickaxe", 2, 30);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("empty")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("ruby")&&tmc.getName().equals("ruby")&&trc.getName().equals("ruby")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("rubyPickaxe",3, "pickaxe", 2, 40);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("empty")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("goldIngot")&&tmc.getName().equals("goldIngot")&&trc.getName().equals("goldIngot")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("goldPickaxe",4, "pickaxe", 2, 50);
                inventoryA[4][9].setAmount(1);
            }
            else if(tl.getName().equals("empty")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("diamond")&&tmc.getName().equals("diamond")&&trc.getName().equals("diamond")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Tools("diamondPickaxe",5, "pickaxe", 2, 60);
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

            ////////////////////TORCHES
            else if(tl.getName().equals("coal")&&tr.getName().equals("empty")&&bl.getName().equals("stick")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("torch", "axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("stick")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("coal")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("torch", "axe");
                inventoryA[4][9].setAmount(4);
            } else if(tl.getName().equals("empty")&&tr.getName().equals("coal")&&bl.getName().equals("empty")&&br.getName().equals("stick")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("torch", "axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("stick")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("coal")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("torch", "axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("coal")&&brc.getName().equals("stick")){
                inventoryA[4][9] = new Resources("torch", "axe");
                inventoryA[4][9].setAmount(4);
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("coal")&&mrc.getName().equals("stick")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new Resources("torch", "axe");
                inventoryA[4][9].setAmount(4);
            }

            //flint and steel

            else if(tl.getName().equals("ruby")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("flint")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new inventoryItems("flintAndSteel");
            }else if(tl.getName().equals("empty")&&tr.getName().equals("ruby")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("flint")){
                inventoryA[4][9] = new inventoryItems("flintAndSteel");
            }else if(tl.getName().equals("empty")&&tr.getName().equals("flint")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("ruby")&&tmc.getName().equals("empty")&&trc.getName().equals("empty")&&mrc.getName().equals("empty")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new inventoryItems("flintAndSteel");
            }else if(tl.getName().equals("empty")&&tr.getName().equals("empty")&&bl.getName().equals("empty")&&br.getName().equals("empty")&&tlc.getName().equals("empty")&&tmc.getName().equals("ruby")&&trc.getName().equals("empty")&&mrc.getName().equals("flint")&&brc.getName().equals("empty")){
                inventoryA[4][9] = new inventoryItems("flintAndSteel");
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
                if (inventoryShowing) {
                    gPane.setVisible(true);
                    hotbarG.setVisible(true);
                    inventoryPane.setVisible(false);
                    inventoryShowing = false;
                    furnaceShowing = false;
                    smeltingBar.setVisible(false);
                    fuelBar.setVisible(false);
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
                    smeltingBar.setVisible(true);
                    fuelBar.setVisible(true);

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

            else if("villager".equals(map[playerPositionX+directionChange][playerPositionY])){
                if (inventoryShowing) {
                    gPane.setVisible(true);
                    hotbarG.setVisible(true);
                    inventoryPane.setVisible(false);
                    inventoryShowing = false;
                    tradingShowing = false;
                    for (int i = 0; i < inventoryLabels.length; i++) {
                        for (int j = 0; j < inventoryLabels[0].length; j++) {
                            inventoryLabels[i][j].setVisible(false);
                            furnaceTop.setVisible(false);
                            furnaceBottom.setVisible(false);
                            tradingLabel.setVisible(false);
                            two1c.setVisible(false);
                            two2c.setVisible(false);
                            two3cv.setVisible(false);
                        }
                    }

                    inventoryImg[4][9].setImage(grayBack);
                    inventoryImg[4][7].setImage(grayBack);
                    inventoryImg[1][9].setImage(grayBack);
                    inventoryImg[1][10].setImage(grayBack);
                    inventoryImg[2][11].setImage(blackBack);
                    inventoryImg[1][11].setImage(blackBack);

                    inventoryImg[1][9].setOpacity(1);
                    inventoryImg[1][10].setOpacity(1);
                    inventoryImg[1][11].setOpacity(1);
                    inventoryImg[2][9].setOpacity(1);
                    inventoryImg[2][10].setOpacity(1);
                    inventoryImg[2][11].setOpacity(1);

                    inventoryA[4][9] = new inventoryItems("empty");
                    inventoryA[4][7] = new inventoryItems("empty");
                    inventoryA[1][9] = new inventoryItems("empty");
                    inventoryA[1][10] = new inventoryItems("empty");
                    inventoryA[2][11] = new inventoryItems("nothing");
                    inventoryA[1][11] = new inventoryItems("nothing");
                    inventoryA[2][9] = new inventoryItems("empty");
                    inventoryA[2][10] = new inventoryItems("empty");

                    if(tempHelmet!=null){
                        inventoryA[1][7] = tempHelmet;
                    }
                    if(tempChestplate!=null){
                        inventoryA[2][7] = tempChestplate;
                    }
                    if(tempLeggings!=null){
                        inventoryA[3][7] = tempLeggings;
                    }
                    if(tempBoots!=null){
                        inventoryA[4][7] = tempBoots;
                    }






                }else{
                    gPane.setVisible(false);
                    hotbarG.setVisible(false);
                    inventoryPane.setVisible(true);
                    inventoryShowing = true;
                    tradingShowing = true;

                    for (int i = 0; i < inventoryLabels.length; i++) {
                        for (int j = 0; j < inventoryLabels[0].length; j++) {
                            inventoryLabels[i][j].setVisible(true);
                            furnaceTop.setVisible(true);
                            furnaceBottom.setVisible(true);
                            tradingLabel.setVisible(true);
                            two1c.setVisible(true);
                            two2c.setVisible(true);
                            two3cv.setVisible(true);
                        }
                    }

                    if(!inventoryA[1][7].getName().equals("empty")){
                        tempHelmet = (Armor) inventoryA[1][7];
                    }
                    if(!inventoryA[2][7].getName().equals("empty")) {
                        tempChestplate = (Armor) inventoryA[2][7];
                    }
                    if(!inventoryA[3][7].getName().equals("empty")) {
                        tempLeggings = (Armor) inventoryA[3][7];
                    }
                    if(!inventoryA[4][7].getName().equals("empty")) {
                        tempBoots = (Armor) inventoryA[4][7];
                    }

                    inventoryA[1][7] = new inventoryItems("empty");
                    inventoryA[2][7] = new inventoryItems("empty");
                    inventoryA[3][7] = new inventoryItems("empty");
                    inventoryA[4][7] = new inventoryItems("empty");



                    inventoryImg[4][9].setImage(blackBack);
                    inventoryImg[4][7].setImage(blackBack);
                    inventoryImg[2][11].setImage(grayBack);
                    inventoryImg[1][11].setImage(grayBack);


                    inventoryA[4][9] = new inventoryItems("nothing");
                    inventoryA[4][7] = new inventoryItems("nothing");
                    inventoryA[2][11] = new inventoryItems("empty");
                    inventoryA[1][11] = new inventoryItems("empty");

                    Villagers tempVillager = null;
                    for(Villagers villager: villagersOnMap){
                        if(villager.getX()==playerPositionX+directionChange&&villager.getY()==playerPositionY){
                            tempVillager = villager;
                        }
                    }
                    assert tempVillager != null;
                    for (int i = 0; i < 3; i++) {
                        if(i<2||tempVillager.getTrades().size()>2){
                            switch (tempVillager.getTrades().get(i)) {
                                case "flint" -> inventoryImg[1][i + 9].setImage(flintInv);
                                case "obsidian" -> inventoryImg[1][i + 9].setImage(obsidianInv);
                                case "drill" -> inventoryImg[1][i + 9].setImage(drillInv);
                                case "bossSoul" -> inventoryImg[1][i + 9].setImage(bossSoulInv);
                                case "goldHelmet" -> inventoryImg[1][i + 9].setImage(goldHelmetInv);
                                case "rubyAxe" -> inventoryImg[1][i + 9].setImage(rubyAxeInv);
                                case "stoneSword" -> inventoryImg[1][i + 9].setImage(stoneSwordInv);
                                case "ruby" -> inventoryImg[1][i + 9].setImage(rubyInv);
                                case "apple" -> inventoryImg[1][i + 9].setImage(appleInv);
                                case "woodBoots" -> inventoryImg[1][i + 9].setImage(woodBootsInv);
                                case "rubyChestplate" -> inventoryImg[1][i + 9].setImage(rubyChestplateInv);
                                case "diamondLeggings" -> inventoryImg[1][i + 9].setImage(diamondLeggingsInv);
                                case "goldSword" -> inventoryImg[1][i + 9].setImage(goldSwordInv);
                                case "rawPork" -> inventoryImg[1][i + 9].setImage(rawPorkInv);
                                case "cookedBeef" -> inventoryImg[1][i + 9].setImage(cookedBeefInv);
                            }
                            inventoryA[1][i+9] = new inventoryItems("trade");
                            inventoryImg[1][i+9].setOpacity(.7);
                        }
                    }
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
                    smeltingBar.setVisible(false);
                    fuelBar.setVisible(false);

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
                    smeltingBar.setVisible(true);
                    fuelBar.setVisible(true);

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

            else if("villager".equals(map[playerPositionX][playerPositionY+directionChange])){
                if (inventoryShowing) {
                    gPane.setVisible(true);
                    hotbarG.setVisible(true);
                    inventoryPane.setVisible(false);
                    inventoryShowing = false;
                    tradingShowing = false;
                    for (int i = 0; i < inventoryLabels.length; i++) {
                        for (int j = 0; j < inventoryLabels[0].length; j++) {
                            inventoryLabels[i][j].setVisible(false);
                            furnaceTop.setVisible(false);
                            furnaceBottom.setVisible(false);
                            tradingLabel.setVisible(false);
                            two1c.setVisible(false);
                            two2c.setVisible(false);
                            two3cv.setVisible(false);
                        }
                    }

                    inventoryImg[4][9].setImage(grayBack);
                    inventoryImg[4][7].setImage(grayBack);
                    inventoryImg[1][9].setImage(grayBack);
                    inventoryImg[1][10].setImage(grayBack);
                    inventoryImg[2][11].setImage(blackBack);
                    inventoryImg[1][11].setImage(blackBack);

                    inventoryImg[1][9].setOpacity(1);
                    inventoryImg[1][10].setOpacity(1);
                    inventoryImg[1][11].setOpacity(1);
                    inventoryImg[2][9].setOpacity(1);
                    inventoryImg[2][10].setOpacity(1);
                    inventoryImg[2][11].setOpacity(1);

                    inventoryA[4][9] = new inventoryItems("empty");
                    inventoryA[4][7] = new inventoryItems("empty");
                    inventoryA[1][9] = new inventoryItems("empty");
                    inventoryA[1][10] = new inventoryItems("empty");
                    inventoryA[2][11] = new inventoryItems("nothing");
                    inventoryA[1][11] = new inventoryItems("nothing");
                    inventoryA[2][9] = new inventoryItems("empty");
                    inventoryA[2][10] = new inventoryItems("empty");


                    if(tempHelmet!=null){
                        inventoryA[1][7] = tempHelmet;
                    }
                    if(tempChestplate!=null){
                        inventoryA[2][7] = tempChestplate;
                    }
                    if(tempLeggings!=null){
                        inventoryA[3][7] = tempLeggings;
                    }
                    if(tempBoots!=null){
                        inventoryA[4][7] = tempBoots;
                    }

                }else{
                    gPane.setVisible(false);
                    hotbarG.setVisible(false);
                    inventoryPane.setVisible(true);
                    inventoryShowing = true;
                    tradingShowing = true;

                    for (int i = 0; i < inventoryLabels.length; i++) {
                        for (int j = 0; j < inventoryLabels[0].length; j++) {
                            inventoryLabels[i][j].setVisible(true);
                            furnaceTop.setVisible(true);
                            furnaceBottom.setVisible(true);
                            tradingLabel.setVisible(true);
                            two1c.setVisible(true);
                            two2c.setVisible(true);
                            two3cv.setVisible(true);
                        }
                    }

                    if(!inventoryA[1][7].getName().equals("empty")){
                        tempHelmet = (Armor) inventoryA[1][7];
                    }
                    if(!inventoryA[2][7].getName().equals("empty")) {
                        tempChestplate = (Armor) inventoryA[2][7];
                    }
                    if(!inventoryA[3][7].getName().equals("empty")) {
                        tempLeggings = (Armor) inventoryA[3][7];
                    }
                    if(!inventoryA[4][7].getName().equals("empty")) {
                        tempBoots = (Armor) inventoryA[4][7];
                    }

                    inventoryA[1][7] = new inventoryItems("empty");
                    inventoryA[2][7] = new inventoryItems("empty");
                    inventoryA[3][7] = new inventoryItems("empty");
                    inventoryA[4][7] = new inventoryItems("empty");


                    inventoryImg[4][9].setImage(blackBack);
                    inventoryImg[4][7].setImage(blackBack);
                    inventoryImg[2][11].setImage(grayBack);
                    inventoryImg[1][11].setImage(grayBack);


                    inventoryA[4][9] = new inventoryItems("nothing");
                    inventoryA[4][7] = new inventoryItems("nothing");
                    inventoryA[2][11] = new inventoryItems("empty");
                    inventoryA[1][11] = new inventoryItems("empty");

                    Villagers tempVillager = null;
                    for(Villagers villager: villagersOnMap){
                        if(villager.getX()==playerPositionX&&villager.getY()==playerPositionY+directionChange){
                            tempVillager = villager;
                        }
                    }
                    assert tempVillager != null;
                    for (int i = 0; i < 3; i++) {
                        if(i<2||tempVillager.getTrades().size()>2) {
                            switch (tempVillager.getTrades().get(i)) {
                                case "flint" -> inventoryImg[1][i + 9].setImage(flintInv);
                                case "obsidian" -> inventoryImg[1][i + 9].setImage(obsidianInv);
                                case "drill" -> inventoryImg[1][i + 9].setImage(drillInv);
                                case "bossSoul" -> inventoryImg[1][i + 9].setImage(bossSoulInv);
                                case "goldHelmet" -> inventoryImg[1][i + 9].setImage(goldHelmetInv);
                                case "rubyAxe" -> inventoryImg[1][i + 9].setImage(rubyAxeInv);
                                case "stoneSword" -> inventoryImg[1][i + 9].setImage(stoneSwordInv);
                                case "ruby" -> inventoryImg[1][i + 9].setImage(rubyInv);
                                case "apple" -> inventoryImg[1][i + 9].setImage(appleInv);
                                case "woodBoots" -> inventoryImg[1][i + 9].setImage(woodBootsInv);
                                case "rubyChestplate" -> inventoryImg[1][i + 9].setImage(rubyChestplateInv);
                                case "diamondLeggings" -> inventoryImg[1][i + 9].setImage(diamondLeggingsInv);
                                case "goldSword" -> inventoryImg[1][i + 9].setImage(goldSwordInv);
                                case "rawPork" -> inventoryImg[1][i + 9].setImage(rawPorkInv);
                                case "cookedBeef" -> inventoryImg[1][i + 9].setImage(cookedBeefInv);
                            }
                            inventoryA[1][i + 9] = new inventoryItems("trade");
                            inventoryImg[1][i + 9].setOpacity(.7);
                        }
                    }
                }
            }
        }



        }

    private void interactTwoCave(){
        int directionChange = switch (directionInter) {
            case "up" -> -1;
            case "down" -> 1;
            case "right" -> 1;
            case "left" -> -1;
            default -> 0;
        };
        if (directionInter.equals("up") || directionInter.equals("down")) {
            if ("craftingTable".equals(mapCave[playerPositionX + directionChange][playerPositionY])) {
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


            else if("furnace".equals(mapCave[playerPositionX+directionChange][playerPositionY])){
                if (inventoryShowing) {
                    gPane.setVisible(true);
                    hotbarG.setVisible(true);
                    inventoryPane.setVisible(false);
                    inventoryShowing = false;
                    furnaceShowing = false;
                    smeltingBar.setVisible(false);
                    fuelBar.setVisible(false);
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
                    smeltingBar.setVisible(true);
                    fuelBar.setVisible(true);

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

            else if("villager".equals(mapCave[playerPositionX+directionChange][playerPositionY])){
                if (inventoryShowing) {
                    gPane.setVisible(true);
                    hotbarG.setVisible(true);
                    inventoryPane.setVisible(false);
                    inventoryShowing = false;
                    tradingShowing = false;
                    for (int i = 0; i < inventoryLabels.length; i++) {
                        for (int j = 0; j < inventoryLabels[0].length; j++) {
                            inventoryLabels[i][j].setVisible(false);
                            furnaceTop.setVisible(false);
                            furnaceBottom.setVisible(false);
                            tradingLabel.setVisible(false);
                            two1c.setVisible(false);
                            two2c.setVisible(false);
                            two3cv.setVisible(false);
                        }
                    }

                    inventoryImg[1][9].setImage(grayBack);
                    inventoryImg[1][10].setImage(grayBack);
                    inventoryImg[4][9].setImage(grayBack);
                    inventoryImg[4][7].setImage(grayBack);
                    inventoryImg[2][11].setImage(blackBack);

                    inventoryA[1][9] = new inventoryItems("empty");
                    inventoryA[1][10] = new inventoryItems("empty");
                    inventoryA[4][9] = new inventoryItems("empty");
                    inventoryA[4][7] = new inventoryItems("empty");
                    inventoryA[2][11] = new inventoryItems("nothing");

                }else{
                    gPane.setVisible(false);
                    hotbarG.setVisible(false);
                    inventoryPane.setVisible(true);
                    inventoryShowing = true;
                    tradingShowing = true;

                    for (int i = 0; i < inventoryLabels.length; i++) {
                        for (int j = 0; j < inventoryLabels[0].length; j++) {
                            inventoryLabels[i][j].setVisible(true);
                            furnaceTop.setVisible(true);
                            furnaceBottom.setVisible(true);
                            tradingLabel.setVisible(true);
                            two1c.setVisible(true);
                            two2c.setVisible(true);
                            two3cv.setVisible(true);
                        }
                    }

                    inventoryImg[1][9].setImage(blackBack);
                    inventoryImg[1][10].setImage(blackBack);
                    inventoryImg[4][9].setImage(blackBack);
                    inventoryImg[4][7].setImage(blackBack);
                    inventoryImg[2][11].setImage(grayBack);

                    inventoryA[1][9] = new inventoryItems("nothing");
                    inventoryA[1][10] = new inventoryItems("nothing");
                    inventoryA[4][9] = new inventoryItems("nothing");
                    inventoryA[4][7] = new inventoryItems("nothing");
                    inventoryA[2][11] = new inventoryItems("empty");
                }
            }


        }
        else{
            if("craftingTable".equals(mapCave[playerPositionX][playerPositionY + directionChange])) {
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
            else if("furnace".equals(mapCave[playerPositionX][playerPositionY + directionChange])){

                if (inventoryShowing) {
                    gPane.setVisible(true);
                    hotbarG.setVisible(true);
                    inventoryPane.setVisible(false);
                    inventoryShowing = false;
                    furnaceShowing = false;
                    smeltingBar.setVisible(false);
                    fuelBar.setVisible(false);

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
                    smeltingBar.setVisible(true);
                    fuelBar.setVisible(true);

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

            else if("villager".equals(mapCave[playerPositionX][playerPositionY+directionChange])){
                if (inventoryShowing) {
                    gPane.setVisible(true);
                    hotbarG.setVisible(true);
                    inventoryPane.setVisible(false);
                    inventoryShowing = false;
                    tradingShowing = false;
                    for (int i = 0; i < inventoryLabels.length; i++) {
                        for (int j = 0; j < inventoryLabels[0].length; j++) {
                            inventoryLabels[i][j].setVisible(false);
                            furnaceTop.setVisible(false);
                            furnaceBottom.setVisible(false);
                            tradingLabel.setVisible(false);
                            two1c.setVisible(false);
                            two2c.setVisible(false);
                            two3cv.setVisible(false);
                        }
                    }

                    inventoryImg[1][9].setImage(grayBack);
                    inventoryImg[1][10].setImage(grayBack);
                    inventoryImg[4][9].setImage(grayBack);
                    inventoryImg[4][7].setImage(grayBack);
                    inventoryImg[2][11].setImage(blackBack);

                    inventoryA[1][9] = new inventoryItems("empty");
                    inventoryA[1][10] = new inventoryItems("empty");
                    inventoryA[4][9] = new inventoryItems("empty");
                    inventoryA[4][7] = new inventoryItems("empty");
                    inventoryA[2][11] = new inventoryItems("nothing");

                }else{
                    gPane.setVisible(false);
                    hotbarG.setVisible(false);
                    inventoryPane.setVisible(true);
                    inventoryShowing = true;
                    tradingShowing = true;

                    for (int i = 0; i < inventoryLabels.length; i++) {
                        for (int j = 0; j < inventoryLabels[0].length; j++) {
                            inventoryLabels[i][j].setVisible(true);
                            furnaceTop.setVisible(true);
                            furnaceBottom.setVisible(true);
                            tradingLabel.setVisible(true);
                            two1c.setVisible(true);
                            two2c.setVisible(true);
                            two3cv.setVisible(true);
                        }
                    }


                    inventoryImg[1][9].setImage(blackBack);
                    inventoryImg[1][10].setImage(blackBack);
                    inventoryImg[4][9].setImage(blackBack);
                    inventoryImg[4][7].setImage(blackBack);
                    inventoryImg[2][11].setImage(grayBack);

                    inventoryA[1][9] = new inventoryItems("nothing");
                    inventoryA[1][10] = new inventoryItems("nothing");
                    inventoryA[4][9] = new inventoryItems("nothing");
                    inventoryA[4][7] = new inventoryItems("nothing");
                    inventoryA[2][11] = new inventoryItems("empty");
                }
            }
        }



    }

    private void interactTwoNether(){
        int directionChange = switch (directionInter) {
            case "up" -> -1;
            case "down" -> 1;
            case "right" -> 1;
            case "left" -> -1;
            default -> 0;
        };
        if (directionInter.equals("up") || directionInter.equals("down")) {
            if ("craftingTable".equals(mapNether[playerPositionX + directionChange][playerPositionY])) {
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


            else if("furnace".equals(mapNether[playerPositionX+directionChange][playerPositionY])){
                if (inventoryShowing) {
                    gPane.setVisible(true);
                    hotbarG.setVisible(true);
                    inventoryPane.setVisible(false);
                    inventoryShowing = false;
                    furnaceShowing = false;
                    smeltingBar.setVisible(false);
                    fuelBar.setVisible(false);
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
                    smeltingBar.setVisible(true);
                    fuelBar.setVisible(true);

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

            else if("villager".equals(mapNether[playerPositionX+directionChange][playerPositionY])){
                if (inventoryShowing) {
                    gPane.setVisible(true);
                    hotbarG.setVisible(true);
                    inventoryPane.setVisible(false);
                    inventoryShowing = false;
                    tradingShowing = false;
                    for (int i = 0; i < inventoryLabels.length; i++) {
                        for (int j = 0; j < inventoryLabels[0].length; j++) {
                            inventoryLabels[i][j].setVisible(false);
                            furnaceTop.setVisible(false);
                            furnaceBottom.setVisible(false);
                            tradingLabel.setVisible(false);
                            two1c.setVisible(false);
                            two2c.setVisible(false);
                            two3cv.setVisible(false);
                        }
                    }

                    inventoryImg[1][9].setImage(grayBack);
                    inventoryImg[1][10].setImage(grayBack);
                    inventoryImg[4][9].setImage(grayBack);
                    inventoryImg[4][7].setImage(grayBack);
                    inventoryImg[2][11].setImage(blackBack);

                    inventoryA[1][9] = new inventoryItems("empty");
                    inventoryA[1][10] = new inventoryItems("empty");
                    inventoryA[4][9] = new inventoryItems("empty");
                    inventoryA[4][7] = new inventoryItems("empty");
                    inventoryA[2][11] = new inventoryItems("nothing");

                }else{
                    gPane.setVisible(false);
                    hotbarG.setVisible(false);
                    inventoryPane.setVisible(true);
                    inventoryShowing = true;
                    tradingShowing = true;

                    for (int i = 0; i < inventoryLabels.length; i++) {
                        for (int j = 0; j < inventoryLabels[0].length; j++) {
                            inventoryLabels[i][j].setVisible(true);
                            furnaceTop.setVisible(true);
                            furnaceBottom.setVisible(true);
                            tradingLabel.setVisible(true);
                            two1c.setVisible(true);
                            two2c.setVisible(true);
                            two3cv.setVisible(true);
                        }
                    }

                    inventoryImg[1][9].setImage(blackBack);
                    inventoryImg[1][10].setImage(blackBack);
                    inventoryImg[4][9].setImage(blackBack);
                    inventoryImg[4][7].setImage(blackBack);
                    inventoryImg[2][11].setImage(grayBack);

                    inventoryA[1][9] = new inventoryItems("nothing");
                    inventoryA[1][10] = new inventoryItems("nothing");
                    inventoryA[4][9] = new inventoryItems("nothing");
                    inventoryA[4][7] = new inventoryItems("nothing");
                    inventoryA[2][11] = new inventoryItems("empty");
                }
            }


        }
        else{
            if("craftingTable".equals(mapNether[playerPositionX][playerPositionY + directionChange])) {
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
            else if("furnace".equals(mapNether[playerPositionX][playerPositionY + directionChange])){

                if (inventoryShowing) {
                    gPane.setVisible(true);
                    hotbarG.setVisible(true);
                    inventoryPane.setVisible(false);
                    inventoryShowing = false;
                    furnaceShowing = false;
                    smeltingBar.setVisible(false);
                    fuelBar.setVisible(false);

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
                    smeltingBar.setVisible(true);
                    fuelBar.setVisible(true);

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

            else if("villager".equals(mapNether[playerPositionX][playerPositionY+directionChange])){
                if (inventoryShowing) {
                    gPane.setVisible(true);
                    hotbarG.setVisible(true);
                    inventoryPane.setVisible(false);
                    inventoryShowing = false;
                    tradingShowing = false;
                    for (int i = 0; i < inventoryLabels.length; i++) {
                        for (int j = 0; j < inventoryLabels[0].length; j++) {
                            inventoryLabels[i][j].setVisible(false);
                            furnaceTop.setVisible(false);
                            furnaceBottom.setVisible(false);
                            tradingLabel.setVisible(false);
                            two1c.setVisible(false);
                            two2c.setVisible(false);
                            two3cv.setVisible(false);
                        }
                    }

                    inventoryImg[1][9].setImage(grayBack);
                    inventoryImg[1][10].setImage(grayBack);
                    inventoryImg[4][9].setImage(grayBack);
                    inventoryImg[4][7].setImage(grayBack);
                    inventoryImg[2][11].setImage(blackBack);

                    inventoryA[1][9] = new inventoryItems("empty");
                    inventoryA[1][10] = new inventoryItems("empty");
                    inventoryA[4][9] = new inventoryItems("empty");
                    inventoryA[4][7] = new inventoryItems("empty");
                    inventoryA[2][11] = new inventoryItems("nothing");

                }else{
                    gPane.setVisible(false);
                    hotbarG.setVisible(false);
                    inventoryPane.setVisible(true);
                    inventoryShowing = true;
                    tradingShowing = true;

                    for (int i = 0; i < inventoryLabels.length; i++) {
                        for (int j = 0; j < inventoryLabels[0].length; j++) {
                            inventoryLabels[i][j].setVisible(true);
                            furnaceTop.setVisible(true);
                            furnaceBottom.setVisible(true);
                            tradingLabel.setVisible(true);
                            two1c.setVisible(true);
                            two2c.setVisible(true);
                            two3cv.setVisible(true);
                        }
                    }


                    inventoryImg[1][9].setImage(blackBack);
                    inventoryImg[1][10].setImage(blackBack);
                    inventoryImg[4][9].setImage(blackBack);
                    inventoryImg[4][7].setImage(blackBack);
                    inventoryImg[2][11].setImage(grayBack);

                    inventoryA[1][9] = new inventoryItems("nothing");
                    inventoryA[1][10] = new inventoryItems("nothing");
                    inventoryA[4][9] = new inventoryItems("nothing");
                    inventoryA[4][7] = new inventoryItems("nothing");
                    inventoryA[2][11] = new inventoryItems("empty");
                }
            }
        }



    }


    public void onKeyPressed(KeyEvent keyEvent) {
        if(!inNether) {
            coordsLabel.setText("X: " + playerPositionX + " Y: " + playerPositionY);
        }else{
            coordsLabel.setText("Wither Health");
        }
        if(!miningObject&&!eatingFood&&!isDrilling) {
            if (!inventoryShowing) {
                if (keyEvent.getText().equalsIgnoreCase("w")) {
                    if(inCave) {
                        movePlayer("x", -1,mapCave);
                    } else if (inNether) {
                        movePlayer("x", -1,mapNether);
                    } else{
                        movePlayer("x", -1,map);
                    }
                } else if (keyEvent.getText().equalsIgnoreCase("a")) {
                    if(inCave) {
                        movePlayer("y", -1,mapCave);
                    }else if (inNether) {
                        movePlayer("y", -1,mapNether);
                    }else{
                        movePlayer("y", -1,map);
                    }
                } else if (keyEvent.getText().equalsIgnoreCase("s")) {
                    if(inCave) {
                        movePlayer("x", 1,mapCave);
                    }else if (inNether) {
                        movePlayer("x", 1,mapNether);
                    }else{
                        movePlayer("x", 1,map);
                    }
                } else if (keyEvent.getText().equalsIgnoreCase("d")) {
                    if(inCave) {
                        movePlayer("y", 1,mapCave);
                    }else if (inNether) {
                        movePlayer("y", 1,mapNether);
                    }else{
                        movePlayer("y", 1,map);
                    }
                } else if (keyEvent.getText().equalsIgnoreCase("e")) {
                    if(inCave) {
                        interactCave();
                    }else if (inNether) {
                        System.out.println("hey");
                        interactNether();
                    }else{
                        interact();
                    }
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
                if(inCave) {
                    interactTwoCave();
                }else if(inNether){
                    interactTwoNether();
                }
                else{
                    interactTwo();
                }
            }
            if(!craftingShowing&&!furnaceShowing&&!tradingShowing) {
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
            if(!placedBoss) {
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
                } else if (keyEvent.getText().equalsIgnoreCase("6")) {
//                    wither.add(new Wither("witherOverNetherrack", 100, new inventoryItems("rottenFlesh"), 3, 10, 10, 15));
                }
            }

        }
        updateScreen();

    }

    public void start() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                //3,7 is fuel        1,7 is smelted        2,9 is result

                fuelBar.setProgress(amountToBurn * 1000000000.0 / (double) ((now - burningTime) * 10));
                smeltingBar.setProgress(amountToSmelt * 1000000000.0 / (double) ((now - smeltingTime) * 10));
                if ((inventoryA[3][7].getName().equals("coal") || inventoryA[3][7].getName().equals("autumnWood") || inventoryA[3][7].getName().equals("fruitWood") || inventoryA[3][7].getName().equals("normalWood") || inventoryA[3][7].getName().equals("stick")) && (inventoryA[1][7].getName().equals("goldOre") || inventoryA[1][7].getName().equals("rubyOre") || inventoryA[1][7].getName().equals("rawPork") || inventoryA[1][7].getName().equals("rawBeef") || inventoryA[1][7].getName().equals("rawMutton"))) {
                    if (!burningFuel) {
                        if (inventoryA[3][7].getName().equals("coal")) {
                            burningTime = System.nanoTime();
                            burningFuel = true;
                            amountToBurn = 10;
                        }
                        if (inventoryA[3][7].getName().equals("autumnWood")) {
                            burningTime = System.nanoTime();
                            burningFuel = true;
                            amountToBurn = 5;
                        }
                        if (inventoryA[3][7].getName().equals("fruitWood")) {
                            burningTime = System.nanoTime();
                            burningFuel = true;
                            amountToBurn = 5;
                        }
                        if (inventoryA[3][7].getName().equals("normalWood")) {
                            burningTime = System.nanoTime();
                            burningFuel = true;
                            amountToBurn = 5;
                        }
                        if (inventoryA[3][7].getName().equals("stick")) {
                            burningTime = System.nanoTime();
                            burningFuel = true;
                            amountToBurn = 2;
                        }
                    }
                    if (!smelting) {
                        if (inventoryA[1][7].getName().equals("goldOre")) {
                            smeltingTime = System.nanoTime();
                            amountToSmelt = 5;
                            smelting = true;
                            currentSmelting = "gold";
                        }
                        if (inventoryA[1][7].getName().equals("rubyOre")) {
                            smeltingTime = System.nanoTime();
                            amountToSmelt = 5;
                            smelting = true;
                            currentSmelting = "ruby";
                        }
                        if (inventoryA[1][7].getName().equals("rawPork")) {
                            smeltingTime = System.nanoTime();
                            amountToSmelt = 3;
                            smelting = true;
                            currentSmelting = "pork";
                        }
                        if (inventoryA[1][7].getName().equals("rawBeef")) {
                            smeltingTime = System.nanoTime();
                            amountToSmelt = 4;
                            smelting = true;
                            currentSmelting = "beef";
                        }
                        if (inventoryA[1][7].getName().equals("rawMutton")) {
                            smeltingTime = System.nanoTime();
                            amountToSmelt = 3;
                            smelting = true;
                            currentSmelting = "mutton";
                        }

                    }
                }
                if (smelting) {
                    if (now - smeltingTime > 1000000000.0 * amountToSmelt) {
                        if (inventoryA[2][9].getName().equals("empty")) {
                            if (currentSmelting.equals("gold")) {
                                inventoryA[2][9] = new inventoryItems("goldIngot");
                                inventoryA[2][9].setAmount(1);
                            }
                            if (currentSmelting.equals("ruby")) {
                                inventoryA[2][9] = new inventoryItems("ruby");
                                inventoryA[2][9].setAmount(1);
                            }
                            if (currentSmelting.equals("pork")) {
                                inventoryA[2][9] = new Food("cookedPork", 20);
                                inventoryA[2][9].setAmount(1);
                            }
                            if (currentSmelting.equals("mutton")) {
                                inventoryA[2][9] = new Food("cookedMutton", 15);
                                inventoryA[2][9].setAmount(1);
                            }
                            if (currentSmelting.equals("beef")) {
                                inventoryA[2][9] = new Food("cookedBeef", 25);
                                inventoryA[2][9].setAmount(1);
                            }
                        } else {
                            inventoryA[2][9].changeAmount(1);
                        }
                        if (inventoryA[1][7].getAmount() == 1) {
                            inventoryA[1][7] = new inventoryItems("empty");
                        } else {
                            inventoryA[1][7].changeAmount(-1);
                        }
                        currentSmelting = "";
                        smelting = false;
                    }
                }
                if (burningFuel) {
                    if (now - burningTime > 1000000000.0 * amountToBurn) {
                        burningFuel = false;
                        if(inventoryA[3][7].getAmount()==1){
                            inventoryA[3][7] = new inventoryItems("empty");
                        }else{
                            inventoryA[3][7].changeAmount(-1);
                        }
                    }
                }
                if (miningObject) {
                    //1000000000.0

                    if (now - miningTime > 1000000000.0 / toolBoost) {
                        miningTime = System.nanoTime();
                        tempMineTime--;
                        miningBar.setProgress((double) tempMineTime / tempMine.getMineTime());
                        if (tempMineTime < 1) {
                            toolBoost = 1;
                            ///////TEMPORARY not anymore?
                            if (!inCave) {
                                if (mapBackground[miningX][miningY].equals("grass") || mapBackground[miningX][miningY].equals("normal") || mapBackground[miningX][miningY].equals("fruit") || mapBackground[miningX][miningY].equals("autumn")) {
                                    map[miningX][miningY] = "grass";
                                } else {
                                    System.out.println(tempMine.getName());
                                    map[miningX][miningY] = "stone";
                                }
                            } else {
                                mapCave[miningX][miningY] = "stone";
                            }
                            miningObject = false;
                            miningBar.setVisible(false);
                            firstMine = false;
                            if (!inCave) {
                                mineObjectsOnMap.remove(tempMine);
                            } else if (inNether) {
                                mineObjectsOnMapNether.remove(tempMine);
                            } else {
                                mineObjectsOnMapCave.remove(tempMine);
                            }
                            breakB = false;
                            for (int i = 4; i >= 1; i--) {
                                for (int j = 1; j <= 5; j++) {
                                    if (inventoryA[i][j].getName().equals(tempMine.getResourceDrop().getName())) {
                                        System.out.println("hi");
                                        inventoryA[i][j].changeAmount(tempMine.getAmountDrop());
                                        if (tempMine.getAmountDropSecond() != 0) {
                                            for (int m = 4; m >= 1; m--) {
                                                for (int k = 1; k <= 5; k++) {
                                                    if (inventoryA[m][k].getName().equals(tempMine.getResourceDropSecond().getName())) {
                                                        System.out.println("hey");
                                                        inventoryA[m][k].changeAmount(tempMine.getAmountDropSecond());
                                                        breakB = true;
                                                        break;
                                                    }
                                                }


                                                if (breakB) {
                                                    break;
                                                }
                                            }

                                            for (int m = 4; m >= 1; m--) {
                                                if (breakB) {
                                                    break;
                                                }
                                                for (int k = 1; k <= 5; k++) {
                                                    if (inventoryA[m][k].getName().equals("empty")) {
                                                        inventoryA[m][k] = tempMine.getResourceDropSecond();
                                                        inventoryA[m][k].setAmount(tempMine.getAmountDropSecond());
                                                        breakB = true;
                                                        break;
                                                    }
                                                }

                                                if (breakB) {
                                                    break;
                                                }
                                            }
                                        }
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
                                        inventoryA[i][j] = tempMine.getResourceDrop();
                                        inventoryA[i][j].setAmount(tempMine.getAmountDrop());
                                        if (tempMine.getAmountDropSecond() != 0) {
                                            for (int m = 4; m >= 1; m--) {
                                                for (int k = 1; k <= 5; k++) {
                                                    if (inventoryA[m][k].getName().equals(tempMine.getResourceDropSecond().getName())) {
                                                        System.out.println("hi");
                                                        inventoryA[m][k].changeAmount(tempMine.getAmountDropSecond());
                                                        breakB = true;
                                                        break;
                                                    }
                                                }
                                                if (breakB) {
                                                    break;
                                                }
                                            }


                                            for (int m = 4; m >= 1; m--) {
                                                if (breakB) {
                                                    break;
                                                }
                                                for (int k = 1; k <= 5; k++) {
                                                    if (inventoryA[m][k].getName().equals("empty")) {
                                                        inventoryA[m][k] = tempMine.getResourceDropSecond();
                                                        inventoryA[m][k].setAmount(tempMine.getAmountDropSecond());
                                                        breakB = true;
                                                        break;
                                                    }
                                                }
                                                if (breakB) {
                                                    break;
                                                }
                                            }
                                        }
                                        breakB = true;
                                        break;
                                    }
                                }
                                if (breakB) {
                                    breakB = false;
                                    break;
                                }
                            }
                            updateScreen();
                        }

                    }


                }

                if (!miningObject) {
                    for (int i = 0; i < inventoryA.length; i++) {
                        for (int j = 0; j < inventoryA[0].length; j++) {
                            if (inventoryA[i][j].getTier() > 0) {
                                if (inventoryA[i][j].getDurability() <= 0) {
                                    inventoryA[i][j] = new inventoryItems("empty");
                                }
                            }
                        }
                    }
                }

                for (int i = 1; i <= 4; i++) {
                    if (inventoryA[i][7].getDurability() <= 0) {
                        if (!tradingShowing && !furnaceShowing) {
                            inventoryA[i][7] = new inventoryItems("empty");
                        }
                    }
                }


                if (inLava) {
                    if (now - inLavaTime > 1000000000.0) {
                        tempHealth -= 10 - (10 * (tempOverHealth * 0.005833333333));
                        healthBar.setProgress(tempHealth / totalHealth);
                        inLavaTime = System.nanoTime();
                        for (int i = 1; i <= 4; i++) {
                            inventoryA[i][7].changeDurability(-1);
                        }
                        playerHit = true;
                        hitScreenImg.setVisible(true);
                        playerHitTime = System.nanoTime();
                    }
                }

                if (placedBoss) {
                    if (now - placeBossTime > 1000000000.0) {
                        bossCount--;
                        miningBar.setProgress(bossCount / 4);
                        placeBossTime = System.nanoTime();
                        if (bossCount <= 0) {
                            miningBar.setVisible(false);
                            bossCount = 4;
                            placedBoss = false;
                            randomWitherDropF();
                            if (mapBackgroundNether[placeBossX][placeBossY].equals("netherrack")) {
                                wither.add(new Wither("witherOverNetherrack", 100, randomWitherDrop, 10, placeBossX, placeBossY));
                            } else {
                                wither.add(new Wither("witherOverLava", 100, randomWitherDrop, 10, placeBossX, placeBossY));
                            }
                            witherHealth.setVisible(true);
                            witherHealth.setProgress(wither.get(0).getHealth()/100);
                            if (equipped.getAmount() > 1) {
                                equipped.changeAmount(-1);
                            } else {
                                inventoryA[4][selected + 1] = new inventoryItems("empty");
                                System.out.println(inventoryA[4][selected + 1].getName());
                                updateScreen();
                                System.out.println(hotbar[selected].getName());
                                System.out.println(equipped.getName());
                            }
                        }
                    }
                }

                if (eatingFood) {
                    if (now - eatingTime > 1000000000.0 / 2) {
                        eatingTime = System.nanoTime();
                        eatingCount--;
                        miningBar.setProgress((double) eatingCount / 6);

                        if (eatingCount <= 0) {
                            eatingCount = 6;
                            miningBar.setVisible(false);
                            eatingFood = false;

                            tempHunger += equipped.getHungerGain();
                            if (tempHunger > 100) {
                                tempHunger = 100;
                            }
                            if (equipped.getAmount() > 1) {
                                equipped.changeAmount(-1);
                            } else {
                                inventoryA[4][selected + 1] = new inventoryItems("empty");
                                System.out.println(inventoryA[4][selected + 1].getName());
                                updateScreen();
                                System.out.println(hotbar[selected].getName());
                                System.out.println(equipped.getName());
                            }
                            hungerBar.setProgress(tempHunger / totalHunger);
                        }
                    }
                }

                if (animalsOnMap.size() > 0) {
                    for (Animals animal : animalsOnMap) {
                        if (now - animal.getStartTime() > 1000000000.0 * 1.5) {
                            if (animal.getMovementTime() < 0) {
                                animal.changeLoc(map);
                                animal.resetStartTime();
                            } else {
                                animal.changeMovementTime(-1);
                            }


                        }
                    }
                    updateScreen();
                }

                if (villagersOnMap.size() > 0) {
                    for (Villagers villager : villagersOnMap) {
                        if (now - villager.getStartTime() > 1000000000.0 * 1.5) {
                            if (villager.getMovementTime() < 0) {
                                if (!tradingShowing) {
                                    villager.changeLoc(map, mapBackground);
                                }
                                villager.resetStartTime();
                            } else {
                                villager.changeMovementTime(-1);
                            }


                        }
                    }
                    updateScreen();
                }
                if (inNether) {
                    if (wither.size() > 0) {
                        for (Wither w : wither) {
                            if (now - w.getStartTime() > 1000000000.0) {
                                if (w.changeLoc(mapNether, playerPositionX, playerPositionY, tempHealth, tempOverHealth)) {
                                    tempHealth = w.getPlayerHealth();
                                    healthBar.setProgress(tempHealth / totalHealth);
                                    playerHit = true;
                                    playerHitTime = System.nanoTime();
                                    if (!craftingShowing && !furnaceShowing && !tradingShowing && !inventoryShowing) {
                                        hitScreenImg.setVisible(true);
                                    }
                                    for (int i = 1; i <= 4; i++) {
                                        inventoryA[i][7].changeDurability(-1);
                                    }
                                }
                                w.resetStartTime();
                            }
                            if (w.getWitherHeads().size() > 0) {
                                for (witherHead wHead : w.getWitherHeads()) {
                                    if (now - wHead.getStartTime() > 1000000000.0 * 0.1) {
                                        String witherHeadMove = wHead.changeLoc(mapNether, mapBackgroundNether, tempHealth, tempOverHealth);
                                        if (witherHeadMove.equals("hit")) {
                                            tempHealth = wHead.getPlayerHealth();
                                            healthBar.setProgress(tempHealth / totalHealth);
                                            playerHit = true;
                                            playerHitTime = System.nanoTime();
                                            if (!craftingShowing && !furnaceShowing && !tradingShowing && !inventoryShowing) {
                                                hitScreenImg.setVisible(true);
                                            }
                                            for (int i = 1; i <= 4; i++) {
                                                inventoryA[i][7].changeDurability(-1);
                                            }
                                            if (mapBackgroundNether[wHead.getX()][wHead.getY()].equals("netherrack")) {
                                                mapNether[wHead.getX()][wHead.getY()] = "netherrack";
                                            } else {
                                                mapNether[wHead.getX()][wHead.getY()] = "lava";
                                            }
                                            w.getWitherHeads().remove(wHead);
                                            break;
                                        } else if (witherHeadMove.equals("stuck")) {
                                            mapNether[wHead.getX()][wHead.getY()] = "netherrack";
                                            w.getWitherHeads().remove(wHead);
                                            break;
                                        }
                                        wHead.resetStartTime();
                                    }
                                }
                            }
                        }
                    }
                }


                if (isDrilling) {
                    if (now - drillTime > 1000000000.0) {
                        drillAmt--;
                        drillTime = System.nanoTime();
                        miningBar.setProgress(drillAmt / 3);
                        if (drillAmt <= 0) {
                            miningBar.setVisible(false);
                            isDrilling = false;
                            drillAmt = 3;
                            if (inCave) {
                                mapCave[playerPositionX][playerPositionY] = "stone";
                                while (true) {
                                    System.out.println("1");
                                    int ranX = (int) (Math.random() * 31) + playerPositionX - 15;
                                    int ranY = (int) (Math.random() * 31) + playerPositionY - 15;
                                    if (map[ranX][ranY].equals("grass")) {
                                        map[ranX][ranY] = "playerOverGrass";
                                        playerPositionY = ranY;
                                        playerPositionX = ranX;
                                        break;
                                    } else if (map[ranX][ranY].equals("stone")) {
                                        map[ranX][ranY] = "playerOverStone";
                                        playerPositionY = ranY;
                                        playerPositionX = ranX;
                                        break;
                                    }
                                }

                                for (int i = 0; i < map.length; i++) {
                                    for (int j = 0; j < mapCave[0].length; j++) {
                                        if (mapCave[i][j].startsWith("zombie") || mapCave[i][j].startsWith("spider") || mapCave[i][j].startsWith("creeper")) {
                                            mapCave[i][j] = "stone";
                                        }
                                    }
                                }

                                mobsNoCreepersOnMap.clear();
                                creepersOnMap.clear();
                                inCave = false;
                            } else {
                                if (mapBackground[playerPositionX][playerPositionY].equals("grass") || mapBackground[playerPositionX][playerPositionY].equals("normal") || mapBackground[playerPositionX][playerPositionY].equals("fruit") || mapBackground[playerPositionX][playerPositionY].equals("autumn")) {
                                    map[playerPositionX][playerPositionY] = "grass";
                                } else {
                                    map[playerPositionX][playerPositionY] = "stone";
                                }
                                while (true) {
                                    System.out.println("1");
                                    int ranX = (int) (Math.random() * 31) + playerPositionX - 15;
                                    int ranY = (int) (Math.random() * 31) + playerPositionY - 15;
                                    if (mapCave[ranX][ranY].equals("stone")) {
                                        mapCave[ranX][ranY] = "playerOverStone";
                                        playerPositionY = ranY;
                                        playerPositionX = ranX;
                                        break;
                                    }
                                }
                                for (int i = 0; i < map.length; i++) {
                                    for (int j = 0; j < map[0].length; j++) {
                                        if (map[i][j].startsWith("zombie") || map[i][j].startsWith("spider") || map[i][j].startsWith("creeper")) {
                                            if (mapBackground[i][j].equals("grass") || mapBackground[i][j].equals("normal") || mapBackground[i][j].equals("fruit") || mapBackground[i][j].equals("autumn")) {
                                                map[i][j] = "grass";
                                            } else {
                                                map[i][j] = "stone";
                                            }
                                        }
                                    }
                                }

                                mobsNoCreepersOnMap.clear();
                                creepersOnMap.clear();
                                inCave = true;
                            }
                        }
                    }
                }


                if (day) {
                    for (int i = 0; i < mapNightS.length; i++) {
                        for (int j = 0; j < mapNightS[0].length; j++) {
                            mapNightS[i][j] = "light";
                        }
                    }
                    if (now - dayNightTime > 1000000000.0) {
                        if (mobsNoCreepersOnMap.size() > 0) {
                            for (mobsNoCreeper mobs : mobsNoCreepersOnMap) {
                                if (mobs.getName().startsWith("zombie")) {
                                    mobs.changeHealth(-5);
                                }
                            }
                        }
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
                } else {
//                    if(!craftingShowing&&!furnaceShowing&&!inventoryShowing){
//                        for (int i = 0; i < mapNight.length; i++) {
//                            for (int j = 0; j < mapNight[0].length; j++) {
//                                mapNight[i][j].setOpacity(0.5);
//                            }
//                        }
//                    }else{
//                        for (int i = 0; i < mapNight.length; i++) {
//                            for (int j = 0; j < mapNight[0].length; j++) {
//                                mapNight[i][j].setOpacity(0.0);
//                            }
//                        }
//                    }
                    for (int i = 0; i < mapNightS.length; i++) {
                        for (int j = 0; j < mapNightS[0].length; j++) {
                            mapNightS[i][j] = "night";
                        }
                    }

                    if (now - dayNightTime > 1000000000.0) {

                        dayNightTime = System.nanoTime();
                        nightTime--;
                        dayNightBar.setProgress((double) nightTime / totalNightTime);
                        if (!inCave && !inNether) {
                            spawnMob();
                        }
                        if (nightTime <= 0) {
                            dayNightLbl.setText("Day Time");
                            day = true;
                            dayNightBar.setStyle(" -fx-accent: orange; ");
                            nightTime = totalNightTime;
                        }
                    }
                }

                if (mobsNoCreepersOnMap.size() > 0) {
                    for (mobsNoCreeper mobs : mobsNoCreepersOnMap) {
                        if (now - mobs.getStartTime() > 1000000000.0 * mobs.getSpeed()) {
                            if (mobs.getMovementTime() < 0) {
                                if ((mobs.getName().startsWith("spider") && !day) || mobs.getName().startsWith("zombie") || mobs.isAttacked() || (mobs.getName().startsWith("spider") && inCave)) {
                                    if (!inCave) {
                                        if (mobs.changeLoc(map, mapBackground, playerPositionX, playerPositionY, tempHealth, tempOverHealth)) {
                                            tempHealth = mobs.getPlayerHealth();
                                            healthBar.setProgress(tempHealth / totalHealth);
                                            playerHit = true;
                                            playerHitTime = System.nanoTime();
                                            if (!craftingShowing && !furnaceShowing && !tradingShowing && !inventoryShowing) {
                                                hitScreenImg.setVisible(true);
                                            }
                                            for (int i = 1; i <= 4; i++) {
                                                inventoryA[i][7].changeDurability(-1);
                                            }
                                        }
                                    } else {
                                        if (mobs.changeLoc(mapCave, mapBackgroundCave, playerPositionX, playerPositionY, tempHealth, tempOverHealth)) {
                                            tempHealth = mobs.getPlayerHealth();
                                            healthBar.setProgress(tempHealth / totalHealth);
                                            playerHit = true;
                                            playerHitTime = System.nanoTime();
                                            if (!craftingShowing && !furnaceShowing && !tradingShowing && !inventoryShowing) {
                                                hitScreenImg.setVisible(true);
                                            }
                                            for (int i = 1; i <= 4; i++) {
                                                inventoryA[i][7].changeDurability(-1);
                                            }
                                        }
                                    }
                                    mobs.resetStartTime();
                                    System.out.println("jhey");
                                } else {
                                    mobs.changeLocSpiderDay(map, mapBackground);
//                                    tempHealth = mobs.getPlayerHealth();
//                                    healthBar.setProgress(tempHealth / totalHealth);
                                    mobs.resetStartTime();
                                    System.out.println("eosugheoihEGHI");
                                }
                            } else {
                                mobs.changeMovementTime(-1);
                            }


                        }
                    }
                    updateScreen();
                }


                if (mobsNoCreepersOnMap.size() > 0) {
                    for (mobsNoCreeper mobs : mobsNoCreepersOnMap) {
                        if (mobs.getHealth() <= 0) {
                            mobsNoCreepersOnMap.remove(mobs);
                            if (!inCave) {
                                if (mapBackground[mobs.getX()][mobs.getY()].equals("grass") || mapBackground[mobs.getX()][mobs.getY()].equals("normal") || mapBackground[mobs.getX()][mobs.getY()].equals("fruit") || mapBackground[mobs.getX()][mobs.getY()].equals("autumn")) {
                                    map[mobs.getX()][mobs.getY()] = "grass";
                                } else {
                                    map[mobs.getX()][mobs.getY()] = "stone";
                                }
                            } else {
                                mapCave[mobs.getX()][mobs.getY()] = "stone";
                            }
                            break;
                        }
                    }
                }

                if (now - regenTime > 1000000000.0 * 2.5) {
                    regenTime = System.nanoTime();
                    if (tempHunger >= 85) {
                        tempHealth += 5;
                        if (tempHealth > 100) {
                            tempHealth = 100;
                        }
                        healthBar.setProgress(tempHealth / totalHealth);
                    }
                }

                if (swing) {
                    if (now - swingTime > 1000000000.0 * 0.2) {
                        swingCount--;
                        swingBar.setProgress(swingCount / 4);
                        swingTime = System.nanoTime();
                        if (swingCount <= 0) {
                            swing = false;
                            swingCount = 3;
                            swingBar.setVisible(false);
                        }
                    }
                }

                if (creepersOnMap.size() > 0) {
                    for (Creepers creeperL : creepersOnMap) {
                        if (now - creeperL.getStartTime() > 1000000000.0 * creeperL.getSpeed()) {
                            if (creeperL.getMovementTime() < 0) {
                                if (!inCave) {
                                    if (creeperL.changeLoc(map, mapBackground, playerPositionX, playerPositionY, tempHealth, tempOverHealth)) {
                                        tempHealth = creeperL.getPlayerHealth();
                                        healthBar.setProgress(tempHealth / totalHealth);
                                        playerHit = true;
                                        playerHitTime = System.nanoTime();
                                        if (!craftingShowing && !furnaceShowing && !tradingShowing && !inventoryShowing) {
                                            hitScreenImg.setVisible(true);
                                        }
                                        for (int i = 1; i <= 4; i++) {
                                            inventoryA[i][7].changeDurability(-1);
                                        }
                                    }
                                } else {
                                    if (creeperL.changeLoc(mapCave, mapBackgroundCave, playerPositionX, playerPositionY, tempHealth, tempOverHealth)) {
                                        tempHealth = creeperL.getPlayerHealth();
                                        healthBar.setProgress(tempHealth / totalHealth);
                                        playerHit = true;
                                        playerHitTime = System.nanoTime();
                                        if (!craftingShowing && !furnaceShowing && !tradingShowing && !inventoryShowing) {
                                            hitScreenImg.setVisible(true);
                                        }
                                        for (int i = 1; i <= 4; i++) {
                                            inventoryA[i][7].changeDurability(-1);
                                        }
                                    }
                                }
                                creeperL.resetStartTime();
                                System.out.println("jhey");
                                if (creeperL.isBlownUp()) {
                                    for (int i = creeperL.getX() - 1; i <= creeperL.getX() + 1; i++) {
                                        for (int j = creeperL.getY() - 1; j <= creeperL.getY() + 1; j++) {
//                                            System.out.println(map[i][j]);
                                            if (!inCave) {
                                                if ((!map[i][j].equals("grassWX") || !map[i][j].equals("water")) && !map[i][j].startsWith("player")) {
                                                    if (mapBackground[i][j].equals("grass") || mapBackground[i][j].equals("normal") || mapBackground[i][j].equals("fruit") || mapBackground[i][j].equals("autumn")) {
                                                        map[i][j] = "grass";
                                                    } else {
                                                        map[i][j] = "stone";
                                                    }
                                                }
                                            } else {
                                                mapCave[i][j] = "stone";
                                            }
                                            for (int k = mineObjectsOnMap.size() - 1; k >= 0; k--) {
                                                if (mineObjectsOnMap.get(k).getX() == i && mineObjectsOnMap.get(k).getY() == j) {
                                                    mineObjectsOnMap.remove(mineObjectsOnMap.get(k));
                                                }
                                            }

                                            for (int k = creepersOnMap.size() - 1; k >= 0; k--) {
                                                if (creeperL.getX() != i || creeperL.getY() != j) {
                                                    if (creepersOnMap.get(k).getX() == i && creepersOnMap.get(k).getY() == j) {
                                                        creepersOnMap.remove(creepersOnMap.get(k));
                                                    }
                                                }

                                            }

                                            for (int k = mobsNoCreepersOnMap.size() - 1; k >= 0; k--) {
                                                if (mobsNoCreepersOnMap.get(k).getX() == i && mobsNoCreepersOnMap.get(k).getY() == j) {
                                                    mobsNoCreepersOnMap.remove(mobsNoCreepersOnMap.get(k));
                                                }
                                            }

                                        }
                                    }
                                    creepersOnMap.remove(creeperL);
                                    break;
                                }

                            } else {
                                creeperL.changeMovementTime(-1);
                            }
                        }
                    }
                }


                for (int i = 0; i < mapNightS.length; i++) {
                    for (int j = 0; j < mapNightS[0].length; j++) {
                        nightPane.setVisible(!craftingShowing && !furnaceShowing && !tradingShowing && !inventoryShowing);
                    }
                }


                if (tempHealth <= 0) {
                    deathScreenImg.setImage(deathScreen);
                    deathScreenImg.setVisible(true);
                    respawnB.setVisible(true);
                }

                for (int i = 1; i < map.length - 1; i++) {
                    for (int j = 1; j < map[0].length - 1; j++) {
                        if (map[i][j].startsWith("torch")) {
                            for (int k = i - 2; k <= i + 2; k++) {
                                for (int m = j - 2; m <= j + 2; m++) {
                                    mapNightS[k][m] = "light";
                                }
                            }
                            updateScreen();
                        }
                    }
                }

                for (int i = 0; i < mapNightCave.length; i++) {
                    for (int j = 0; j < mapNightCave[0].length; j++) {
                        mapNightCave[i][j] = "night";
                    }
                }

                for (int i = 1; i < mapCave.length - 1; i++) {
                    for (int j = 1; j < mapCave[0].length - 1; j++) {
                        if (mapCave[i][j].startsWith("torch")) {
                            for (int k = i - 2; k <= i + 2; k++) {
                                for (int m = j - 2; m <= j + 2; m++) {
                                    mapNightCave[k][m] = "light";
                                }
                            }

                        }
                    }
                }
                if (!inNether){
                    for (int k = playerPositionX - 2; k <= playerPositionX + 2; k++) {
                        for (int m = playerPositionY - 2; m <= playerPositionY + 2; m++) {
                            mapNightCave[k][m] = "light";
                            mapNightS[k][m] = "light";
                        }
                    }
                }
                updateScreen();
                

                if(inCave) {
                    
                    if (now - mobSpawnTimeCave > 1000000000.0) {
                        mobSpawnTimeCave = System.nanoTime();
                        int ranNum = (int) (Math.random() * 10);
                        if (ranNum == 0) {
                            while (true) {
                                System.out.println("1");
                                int ranX = (int) (Math.random() * 31) + playerPositionX - 15;
                                int ranY = (int) (Math.random() * 31) + playerPositionY - 15;
                                int ranMob = (int) (Math.random() * 3);
                                if (mapNightCave[ranX][ranY].equals("night")) {
                                    System.out.println("2");
                                    if (ranMob == 0) {
                                       if (mapCave[ranX][ranY].equals("stone")) {
                                            mobsNoCreepersOnMap.add(new mobsNoCreeper("spiderOverStone", 30, new Food("rottenFlesh", 5), (int) (Math.random() * 15), 1.25, (int) (Math.random() * 3) + 1, 10, ranX, ranY));
                                            break;
                                       }
                                    } else if (ranMob == 1) {
                                        if (mapCave[ranX][ranY].equals("stone")) {
                                            mobsNoCreepersOnMap.add(new mobsNoCreeper("zombieOverStone", 30, new Food("rottenFlesh", 5), (int) (Math.random() * 15), 1.25, (int) (Math.random() * 3) + 1, 10, ranX, ranY));
                                            break;
                                        }
                                    } else {
                                       if (mapCave[ranX][ranY].equals("stone")) {
                                            creepersOnMap.add(new Creepers("creeperOverStone", 30, new inventoryItems("flint"), (int) (Math.random() * 15), 0.75, 1, ranX, ranY));
                                            break;
                                       }
                                    }
                                }
                            }
                        }
                    }
                }

                if(!moveAble){
                    if(now - moveTime>1000000000.0 * 0.25){
                        moveAble = true;
                    }
                }

                if(playerHit){
                    if(now-playerHitTime>1000000000.0 * 0.25){
                        playerHit = false;
                        hitScreenImg.setVisible(false);
                    }
                }
            }
        }.start();
    }

    private void randomWitherDropF() {
        int randNum = (int)(Math.random()*7);
        if(randNum == 0){
            randomWitherDrop = new Tools("diamondSword",5, "sword", 4, 60);
        } else if (randNum == 1) {
            randomWitherDrop = new Tools("diamondAxe",5, "axe", 3, 60);
        }else if (randNum == 2) {
            randomWitherDrop = new Tools("diamondPickaxe",5, "pickaxe", 2, 60);
        }else if (randNum == 3) {
            randomWitherDrop = new Armor("diamondHelmet",4,5,30);
        }else if (randNum == 4) {
            randomWitherDrop = new Armor("diamondLeggings",4,10,40);
        }else if (randNum == 5) {
            randomWitherDrop = new Armor("diamondChestplate",4,10,40);
        }else if (randNum == 6) {
            randomWitherDrop = new Armor("diamondBoots",4,5,30);
        }
    }

    private void spawnMob() {
        int ranNum = (int)(Math.random()*5);
        if(ranNum==0){
            while(true) {
                int ranX = (int) (Math.random() * 31) + playerPositionX - 15;
                int ranY = (int) (Math.random() * 31) + playerPositionY - 15;
                int ranMob = (int) (Math.random() * 3);
                if (mapNightS[ranX][ranY].equals("night")) {
                    if (ranMob == 0) {
                        if (map[ranX][ranY].equals("grass")) {
                            mobsNoCreepersOnMap.add(new mobsNoCreeper("spiderOverGrass", 30, new Food("rottenFlesh", 5), (int) (Math.random() * 15), 1.25, (int) (Math.random() * 3) + 1, 10, ranX, ranY));
                            break;
                        } else if (map[ranX][ranY].equals("stone")) {
                            mobsNoCreepersOnMap.add(new mobsNoCreeper("spiderOverStone", 30, new Food("rottenFlesh", 5), (int) (Math.random() * 15), 1.25, (int) (Math.random() * 3) + 1, 10, ranX, ranY));
                            break;
                        }
                    } else if (ranMob == 1) {
                        if (map[ranX][ranY].equals("grass")) {
                            mobsNoCreepersOnMap.add(new mobsNoCreeper("zombieOverGrass", 30, new Food("rottenFlesh", 5), (int) (Math.random() * 15), 1.25, (int) (Math.random() * 3) + 1, 10, ranX, ranY));
                            break;
                        } else if (map[ranX][ranY].equals("stone")) {
                            mobsNoCreepersOnMap.add(new mobsNoCreeper("zombieOverStone", 30, new Food("rottenFlesh", 5), (int) (Math.random() * 15), 1.25, (int) (Math.random() * 3) + 1, 10, ranX, ranY));
                            break;
                        }
                    } else {
                        if (map[ranX][ranY].equals("grass")) {
                            creepersOnMap.add(new Creepers("creeperOverGrass", 30, new inventoryItems("flint"), (int) (Math.random() * 15), 0.75, 1, ranX, ranY));
                            break;
                        } else if (map[ranX][ranY].equals("stone")) {
                            creepersOnMap.add(new Creepers("creeperOverStone", 30, new inventoryItems("flint"), (int) (Math.random() * 15), 0.75, 1, ranX, ranY));
                            break;
                        }
                    }
                }
            }
        }
    }

    public void respawnF(ActionEvent event) {
        deathScreenImg.setVisible(false);
        respawnB.setVisible(false);
        tempHealth = 10;
        tempHunger = 100;
        healthBar.setProgress(tempHealth/totalHealth);
        healthBar.setStyle(" -fx-accent: #FF0000; ");
        hungerBar.setProgress(tempHunger/totalHunger);
        hungerBar.setStyle(" -fx-accent: #987554; ");


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


        gPane.setVisible(true);
        hotbarG.setVisible(true);
        inventoryPane.setVisible(false);
        inventoryShowing = false;
        furnaceShowing = false;
        smeltingBar.setVisible(false);
        fuelBar.setVisible(false);
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



        gPane.setVisible(true);
        hotbarG.setVisible(true);
        inventoryPane.setVisible(false);
        inventoryShowing = false;
        tradingShowing = false;
        for (int i = 0; i < inventoryLabels.length; i++) {
            for (int j = 0; j < inventoryLabels[0].length; j++) {
                inventoryLabels[i][j].setVisible(false);
                furnaceTop.setVisible(false);
                furnaceBottom.setVisible(false);
                tradingLabel.setVisible(false);
                two1c.setVisible(false);
                two2c.setVisible(false);
                two3cv.setVisible(false);
            }
        }

        inventoryImg[4][9].setImage(grayBack);
        inventoryImg[4][7].setImage(grayBack);
        inventoryImg[1][9].setImage(grayBack);
        inventoryImg[1][10].setImage(grayBack);
        inventoryImg[2][11].setImage(blackBack);
        inventoryImg[1][11].setImage(blackBack);

        inventoryImg[1][9].setOpacity(1);
        inventoryImg[1][10].setOpacity(1);
        inventoryImg[1][11].setOpacity(1);
        inventoryImg[2][9].setOpacity(1);
        inventoryImg[2][10].setOpacity(1);
        inventoryImg[2][11].setOpacity(1);

        inventoryA[4][9] = new inventoryItems("empty");
        inventoryA[4][7] = new inventoryItems("empty");
        inventoryA[1][9] = new inventoryItems("empty");
        inventoryA[1][10] = new inventoryItems("empty");
        inventoryA[2][11] = new inventoryItems("nothing");
        inventoryA[1][11] = new inventoryItems("nothing");
        inventoryA[2][9] = new inventoryItems("empty");
        inventoryA[2][10] = new inventoryItems("empty");

        for (int i = 0; i < inventoryA.length; i++) {
            for (int j = 0; j < 5; j++) {
                if(!inventoryA[i][j].getName().equals("empty")&&!inventoryA[i][j].getName().equals("nothing")){
                    int ranNum = (int) (Math.random()*8);
                    if(ranNum==0){
                        System.out.println("ELimanate");
                        inventoryA[i][j] = new inventoryItems("empty");
                    }
                }
            }
        }

        while(true) {
            int ranX;
            int ranY;
            if(inNether) {
                ranX = (int) (Math.random() * 31) + OWplayerPositionX - 15;
                ranY = (int) (Math.random() * 31) + OWplayerPositionY - 15;
            }else{
                ranX = (int) (Math.random() * 31) + playerPositionX - 15;
                ranY = (int) (Math.random() * 31) + playerPositionY - 15;
            }
            if(inCave){
                mapCave[playerPositionX][playerPositionY] = "stone";
            }
            if (inNether) {
                if(mapNether[playerPositionX][playerPositionY].endsWith("Netherrack")){
                    mapNether[playerPositionX][playerPositionY] = "netherrack";
                }else{
                    mapNether[playerPositionX][playerPositionY] = "lava";
                }
            }
            inCave = false;
            inNether = false;
            inLava = false;
            if (map[ranX][ranY].equals("grass")) {
                if (mapBackground[playerPositionX][playerPositionY].equals("grass") || mapBackground[playerPositionX][playerPositionY].equals("normal") || mapBackground[playerPositionX][playerPositionY].equals("fruit") || mapBackground[playerPositionX][playerPositionY].equals("autumn")) {
                    map[playerPositionX][playerPositionY] = "grass";
                } else {
                    map[playerPositionX][playerPositionY] = "stone";
                }
                playerPositionX = ranX;
                playerPositionY = ranY;
                map[playerPositionX][playerPositionY] = "playerOverGrass";
                break;
            }
        }




        updateScreen();

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
                case "coalOre":
                case "torch":
                case "obsidian":


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
                    System.out.println(equipped.getType());
                    if(equipped.getTier()>0){
                        System.out.println(tempMine.getType());
                        if(tempMine.getType().equals(equipped.getType())){
                            toolBoost = equipped.getTier() +(equipped.getTier()*0.6);
                            equipped.changeDurability(-1);
                            System.out.println(toolBoost);
                        }
                    }
//                    map[playerPositionX + directionChange][playerPositionY] = "grass";
                    break;
                case "grass", "stone":
                    if(!equipped.getName().equals("empty")&&equipped.isPlaceable()){
                        int mineTime;
                        String type;

                        if (equipped.getType().equals("pickaxe")) {
                               mineTime = (int) (Math.random() * 6) + 10;
                               type = "pickaxe";
                            } else {
                            mineTime = (int) (Math.random() * 5) + 5;
                            type = "axe";
                        }

                        map[playerPositionX + directionChange][playerPositionY] = equipped.getName();
                        System.out.println(map[playerPositionX + directionChange][playerPositionY]);
                        mineObjectsOnMap.add(new mineObjects(equipped.getName(), equipped.getType(), mineTime, new Resources(equipped.getName(), type), 1, playerPositionX + directionChange, playerPositionY));
                        if (equipped.getAmount() > 1) {
                            equipped.changeAmount(-1);
                        } else {
                            inventoryA[4][selected + 1] = new inventoryItems("empty");
                            System.out.println(inventoryA[4][selected + 1].getName());
                            updateScreen();
                            System.out.println(hotbar[selected].getName());
                            System.out.println(equipped.getName());
                        }
                          }
                    else if (equipped.isEatable()) {
                        eatingFood = true;
                        miningBar.setVisible(true);
                        miningBar.setProgress(1.0);
                    }else if(equipped.getName().equals("flintAndSteel")){
                        int row = playerPositionX + directionChange;
                        int col = playerPositionY;
                        System.out.println("hey");
                        if(map[row][col-1].equals("obsidian")&&map[row][col+1].equals("obsidian")&&map[row-1][col+1].equals("obsidian")&&map[row-1][col-1].equals("obsidian")&&map[row-1][col].equals("obsidian")){
                            //UP
                            map[row][col] = "netherPortal";

                        }else if(map[row][col-1].equals("obsidian")&&map[row][col+1].equals("obsidian")&&map[row+1][col+1].equals("obsidian")&&map[row+1][col-1].equals("obsidian")&&map[row+1][col].equals("obsidian")){
                            //DOWN
                            map[row][col] = "netherPortal";

                        }



                    }else if (equipped.getName().equals("drill")) {
                        isDrilling = true;
                        miningBar.setVisible(true);
                        miningBar.setProgress(1.0);
                    }
                    // 1,1 1,2 1,3 2,1 2,3 2,2 3,2
                    break;
                case "sheep","cow","pig","villager":
                    if(!swing) {
                        swing = true;
                        swingTime = System.nanoTime();
                        swingBar.setVisible(true);
                        swingBar.setProgress(swingCount/4);
                        int damage = 1;
                        if (equipped.getName().endsWith("Axe") || equipped.getName().endsWith("Pickaxe") || equipped.getName().endsWith("Sword")) {
                            damage = equipped.getDamage();
                            equipped.changeDurability(-1);
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

                        for (Villagers villager : villagersOnMap) {
                            if (villager.getX() == playerPositionX + directionChange && villager.getY() == playerPositionY) {

                                villager.changeHealth(-(damage));
                                if (villager.getHealth() <= 0) {
                                    map[playerPositionX][playerPositionY + directionChange] = "grass";
                                    breakB = false;
                                    villagersOnMap.remove(villager);
                                    break;
                                } else {
                                    villager.changeLoc(map, mapBackground);
                                    updateScreen();
                                }
                            }
                        }
                    }
                    break;
                case "zombieOverGrass","zombieOverStone","spiderOverGrass","spiderOverStone","creeperOverGrass","creeperOverStone":
                    if(!swing) {
                        swing = true;
                        swingTime = System.nanoTime();
                        swingBar.setVisible(true);
                        swingBar.setProgress(swingCount/4);
                        int damage = 1;
                        if (equipped.getName().endsWith("Axe") || equipped.getName().endsWith("Pickaxe") || equipped.getName().endsWith("Sword")) {
                            damage = equipped.getDamage();
                            equipped.changeDurability(-1);
                        }
                        for (mobsNoCreeper mobs : mobsNoCreepersOnMap) {
                            if (mobs.getX() == playerPositionX + directionChange && mobs.getY() == playerPositionY) {
                                mobs.changeAttacked(true);
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

                        for(Creepers creeper: creepersOnMap){
                            if (creeper.getX() == playerPositionX + directionChange && creeper.getY() == playerPositionY) {
                                creeper.changeAttacked(true);
                                creeper.changeHealth(-(damage));
                                if (creeper.getHealth() <= 0) {
                                    if (mapBackground[playerPositionX + directionChange][playerPositionY].equals("grass") || mapBackground[playerPositionX + directionChange][playerPositionY].equals("normal") || mapBackground[playerPositionX + directionChange][playerPositionY].equals("fruit") || mapBackground[playerPositionX + directionChange][playerPositionY].equals("autumn")) {
                                        map[playerPositionX + directionChange][playerPositionY] = "grass";
                                    } else {
                                        map[playerPositionX + directionChange][playerPositionY] = "stone";
                                    }
                                    breakB = false;
                                    for (int i = 4; i >= 1; i--) {
                                        for (int j = 1; j <= 5; j++) {
                                            if (inventoryA[i][j].getName().equals(creeper.getResourceDrop().getName())) {
                                                System.out.println("hi");
                                                inventoryA[i][j].changeAmount(creeper.getAmountDrop());
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
                                                inventoryA[i][j] = creeper.getResourceDrop();
                                                inventoryA[i][j].setAmount(creeper.getAmountDrop());
                                                breakB = true;
                                                break;
                                            }
                                        }
                                        if (breakB) {
                                            breakB = false;
                                            break;
                                        }
                                    }


                                    creepersOnMap.remove(creeper);
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
                case "coalOre":
                case "torch":
                case "obsidian":



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
                            equipped.changeDurability(-1);
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
                    }else if (equipped.getName().equals("flintAndSteel")){
                        int row = playerPositionX;
                        int col = playerPositionY + directionChange;


                        if(map[row+1][col].equals("obsidian")&&map[row][col+1].equals("obsidian")&&map[row-1][col+1].equals("obsidian")&&map[row+1][col+1].equals("obsidian")&&map[row-1][col].equals("obsidian")){
                            //RIGHT
                            map[row][col] = "netherPortal";

                        }else if(map[row+1][col].equals("obsidian")&&map[row][col-1].equals("obsidian")&&map[row-1][col-1].equals("obsidian")&&map[row+1][col-1].equals("obsidian")&&map[row-1][col].equals("obsidian")){
                            //left
                            map[row][col] = "netherPortal";

                        }
                    } else if (equipped.getName().equals("drill")) {
                        isDrilling = true;
                        miningBar.setVisible(true);
                        miningBar.setProgress(1.0);

                    }
                    break;
                case "sheep","cow","pig","villager":
                    if(!swing) {
                        int damage = 1;
                        swing = true;
                        swingTime = System.nanoTime();
                        swingBar.setVisible(true);
                        swingBar.setProgress(swingCount/4);
                        if (equipped.getName().endsWith("Axe") || equipped.getName().endsWith("Pickaxe") || equipped.getName().endsWith("Sword")) {
                            damage = equipped.getDamage();
                            equipped.changeDurability(-1);
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

                        for (Villagers villager : villagersOnMap) {
                            if (villager.getX() == playerPositionX && villager.getY() == playerPositionY + directionChange) {

                                villager.changeHealth(-(damage));
                                if (villager.getHealth() <= 0) {
                                    map[playerPositionX][playerPositionY + directionChange] = "grass";
                                    breakB = false;
                                    villagersOnMap.remove(villager);
                                    break;
                                } else {
                                    villager.changeLoc(map, mapBackground);
                                    updateScreen();
                                }
                            }
                        }
                    }
                case "zombieOverGrass","zombieOverStone","spiderOverGrass","spiderOverStone","creeperOverGrass","creeperOverStone":
                    if(!swing) {
                        int damage = 1;
                        swing = true;
                        swingTime = System.nanoTime();
                        swingBar.setVisible(true);
                        swingBar.setProgress(swingCount/4);
                        if (equipped.getName().endsWith("Axe") || equipped.getName().endsWith("Pickaxe") || equipped.getName().endsWith("Sword")) {
                            damage = equipped.getDamage();
                            equipped.changeDurability(-1);
                        }
                        for (mobsNoCreeper mobs : mobsNoCreepersOnMap) {
                            if (mobs.getX() == playerPositionX && mobs.getY() == playerPositionY + directionChange) {
                                mobs.changeAttacked(true);
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

                        for(Creepers creeper: creepersOnMap){
                            if (creeper.getX() == playerPositionX && creeper.getY() == playerPositionY + directionChange) {
                                creeper.changeAttacked(true);
                                creeper.changeHealth(-(damage));
                                if (creeper.getHealth() <= 0) {
                                    if (mapBackground[playerPositionX][playerPositionY + directionChange].equals("grass") || mapBackground[playerPositionX][playerPositionY + directionChange].equals("normal") || mapBackground[playerPositionX][playerPositionY + directionChange].equals("fruit") || mapBackground[playerPositionX][playerPositionY + directionChange].equals("autumn")) {
                                        map[playerPositionX][playerPositionY + directionChange] = "grass";
                                    } else {
                                        map[playerPositionX][playerPositionY + directionChange] = "stone";
                                    }
                                    breakB = false;
                                    for (int i = 4; i >= 1; i--) {
                                        for (int j = 1; j <= 5; j++) {
                                            if (inventoryA[i][j].getName().equals(creeper.getResourceDrop().getName())) {
                                                System.out.println("hi");
                                                inventoryA[i][j].changeAmount(creeper.getAmountDrop());
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
                                                inventoryA[i][j] = creeper.getResourceDrop();
                                                inventoryA[i][j].setAmount(creeper.getAmountDrop());
                                                breakB = true;
                                                break;
                                            }
                                        }
                                        if (breakB) {
                                            breakB = false;
                                            break;
                                        }
                                    }


                                    creepersOnMap.remove(creeper);
                                    break;
                                }
                            }
                        }
                    }
                    break;

            }
        }


    }

    private void interactCave() {
        int directionChange = switch (directionInter) {
            case "up" -> -1;
            case "down" -> 1;
            case "right" -> 1;
            case "left" -> -1;
            default -> 0;
        };
        if (directionInter.equals("up") || directionInter.equals("down")) {
            switch (mapCave[playerPositionX + directionChange][playerPositionY]) {
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
                case "coalOre":
                case "torch":
                case "obsidian":


                    miningObject = true;
                    miningX = playerPositionX + directionChange;
                    miningY = playerPositionY;
                    miningBar.setVisible(true);
                    for (mineObjects m : mineObjectsOnMapCave) {
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
                    System.out.println(equipped.getType());
                    if(equipped.getTier()>0){
                        System.out.println(tempMine.getType());
                        if(tempMine.getType().equals(equipped.getType())){
                            toolBoost = equipped.getTier() +(equipped.getTier()*0.6);
                            equipped.changeDurability(-1);
                            System.out.println(toolBoost);
                        }
                    }
//                    mapCave[playerPositionX + directionChange][playerPositionY] = "grass";
                    break;
                case "grass", "stone":
                    if(!equipped.getName().equals("empty")&&equipped.isPlaceable()){
                        int mineTime;
                        String type;

                        if (equipped.getType().equals("pickaxe")) {
                            mineTime = (int) (Math.random() * 6) + 10;
                            type = "pickaxe";
                        } else {
                            mineTime = (int) (Math.random() * 5) + 5;
                            type = "axe";
                        }

                        mapCave[playerPositionX + directionChange][playerPositionY] = equipped.getName();
                        System.out.println(mapCave[playerPositionX + directionChange][playerPositionY]);
                        mineObjectsOnMapCave.add(new mineObjects(equipped.getName(), equipped.getType(), mineTime, new Resources(equipped.getName(), type), 1, playerPositionX + directionChange, playerPositionY));
                        if (equipped.getAmount() > 1) {
                            equipped.changeAmount(-1);
                        } else {
                            inventoryA[4][selected + 1] = new inventoryItems("empty");
                            System.out.println(inventoryA[4][selected + 1].getName());
                            updateScreen();
                            System.out.println(hotbar[selected].getName());
                            System.out.println(equipped.getName());
                        }
                    }
                    else if (equipped.isEatable()) {
                        eatingFood = true;
                        miningBar.setVisible(true);
                        miningBar.setProgress(1.0);
                    }else if (equipped.getName().equals("drill")) {
                        isDrilling = true;
                        miningBar.setVisible(true);
                        miningBar.setProgress(1.0);

                    }
                    break;
                case "sheep","cow","pig","villager":
                    if(!swing) {
                        swing = true;
                        swingTime = System.nanoTime();
                        swingBar.setVisible(true);
                        swingBar.setProgress(swingCount/4);
                        int damage = 1;
                        if (equipped.getName().endsWith("Axe") || equipped.getName().endsWith("Pickaxe") || equipped.getName().endsWith("Sword")) {
                            damage = equipped.getDamage();
                            equipped.changeDurability(-1);
                        }
                        for (Animals animal : animalsOnMap) {
                            if (animal.getX() == playerPositionX + directionChange && animal.getY() == playerPositionY) {
                                animal.changeHealth(-(damage));
                                if (animal.getHealth() <= 0) {
                                    mapCave[playerPositionX + directionChange][playerPositionY] = "grass";
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

                        for (Villagers villager : villagersOnMap) {
                            if (villager.getX() == playerPositionX + directionChange && villager.getY() == playerPositionY) {

                                villager.changeHealth(-(damage));
                                if (villager.getHealth() <= 0) {
                                    mapCave[playerPositionX][playerPositionY + directionChange] = "grass";
                                    breakB = false;
                                    villagersOnMap.remove(villager);
                                    break;
                                } else {
                                    villager.changeLoc(map, mapBackground);
                                    updateScreen();
                                }
                            }
                        }
                    }
                    break;
                case "zombieOverGrass","zombieOverStone","spiderOverGrass","spiderOverStone","creeperOverGrass","creeperOverStone":
                    if(!swing) {
                        swing = true;
                        swingTime = System.nanoTime();
                        swingBar.setVisible(true);
                        swingBar.setProgress(swingCount/4);
                        int damage = 1;
                        if (equipped.getName().endsWith("Axe") || equipped.getName().endsWith("Pickaxe") || equipped.getName().endsWith("Sword")) {
                            damage = equipped.getDamage();
                            equipped.changeDurability(-1);
                        }
                        for (mobsNoCreeper mobs : mobsNoCreepersOnMap) {
                            if (mobs.getX() == playerPositionX + directionChange && mobs.getY() == playerPositionY) {
                                mobs.changeAttacked(true);
                                mobs.changeHealth(-(damage));
                                if (mobs.getHealth() <= 0) {
                                    if (mapBackgroundCave[playerPositionX + directionChange][playerPositionY].equals("grass") || mapBackgroundCave[playerPositionX + directionChange][playerPositionY].equals("normal") || mapBackgroundCave[playerPositionX + directionChange][playerPositionY].equals("fruit") || mapBackgroundCave[playerPositionX + directionChange][playerPositionY].equals("autumn")) {
                                        mapCave[playerPositionX + directionChange][playerPositionY] = "grass";
                                    } else {
                                        mapCave[playerPositionX + directionChange][playerPositionY] = "stone";
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

                        for(Creepers creeper: creepersOnMap){
                            if (creeper.getX() == playerPositionX + directionChange && creeper.getY() == playerPositionY) {
                                creeper.changeAttacked(true);
                                creeper.changeHealth(-(damage));
                                if (creeper.getHealth() <= 0) {
                                    if (mapBackgroundCave[playerPositionX + directionChange][playerPositionY].equals("grass") || mapBackgroundCave[playerPositionX + directionChange][playerPositionY].equals("normal") || mapBackgroundCave[playerPositionX + directionChange][playerPositionY].equals("fruit") || mapBackgroundCave[playerPositionX + directionChange][playerPositionY].equals("autumn")) {
                                        mapCave[playerPositionX + directionChange][playerPositionY] = "grass";
                                    } else {
                                        mapCave[playerPositionX + directionChange][playerPositionY] = "stone";
                                    }
                                    breakB = false;
                                    for (int i = 4; i >= 1; i--) {
                                        for (int j = 1; j <= 5; j++) {
                                            if (inventoryA[i][j].getName().equals(creeper.getResourceDrop().getName())) {
                                                System.out.println("hi");
                                                inventoryA[i][j].changeAmount(creeper.getAmountDrop());
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
                                                inventoryA[i][j] = creeper.getResourceDrop();
                                                inventoryA[i][j].setAmount(creeper.getAmountDrop());
                                                breakB = true;
                                                break;
                                            }
                                        }
                                        if (breakB) {
                                            breakB = false;
                                            break;
                                        }
                                    }


                                    creepersOnMap.remove(creeper);
                                    break;
                                }
                            }
                        }
                    }


                    break;
            }

        } else {
            switch (mapCave[playerPositionX][playerPositionY + directionChange]) {
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
                case "coalOre":
                case "torch":
                case "obsidian":



                    miningObject = true;
                    miningX = playerPositionX;
                    miningY = playerPositionY + directionChange;
                    miningBar.setVisible(true);
                    for (mineObjects m : mineObjectsOnMapCave) {
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
                            equipped.changeDurability(-1);
                            System.out.println(toolBoost);
                        }
                    }
//                    mapCave[playerPositionX][playerPositionY + directionChange] = "grass";
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

                        mapCave[playerPositionX][playerPositionY + directionChange] = equipped.getName();
                        System.out.println(mapCave[playerPositionX][playerPositionY + directionChange]);
                        mineObjectsOnMapCave.add(new mineObjects(equipped.getName(),equipped.getType(), mineTime, new Resources(equipped.getName(),type), 1, playerPositionX, playerPositionY + directionChange));
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
                    } else if (equipped.getName().equals("drill")) {
                        isDrilling = true;
                        miningBar.setVisible(true);
                        miningBar.setProgress(1.0);

                    }
                    break;
                case "sheep","cow","pig","villager":
                    if(!swing) {
                        int damage = 1;
                        swing = true;
                        swingTime = System.nanoTime();
                        swingBar.setVisible(true);
                        swingBar.setProgress(swingCount/4);
                        if (equipped.getName().endsWith("Axe") || equipped.getName().endsWith("Pickaxe") || equipped.getName().endsWith("Sword")) {
                            damage = equipped.getDamage();
                            equipped.changeDurability(-1);
                        }
                        for (Animals animal : animalsOnMap) {
                            if (animal.getX() == playerPositionX && animal.getY() == playerPositionY + directionChange) {

                                animal.changeHealth(-(damage));
                                if (animal.getHealth() <= 0) {
                                    mapCave[playerPositionX][playerPositionY + directionChange] = "grass";
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

                        for (Villagers villager : villagersOnMap) {
                            if (villager.getX() == playerPositionX && villager.getY() == playerPositionY + directionChange) {

                                villager.changeHealth(-(damage));
                                if (villager.getHealth() <= 0) {
                                    mapCave[playerPositionX][playerPositionY + directionChange] = "grass";
                                    breakB = false;
                                    villagersOnMap.remove(villager);
                                    break;
                                } else {
                                    villager.changeLoc(map, mapBackground);
                                    updateScreen();
                                }
                            }
                        }
                    }
                case "zombieOverGrass","zombieOverStone","spiderOverGrass","spiderOverStone","creeperOverGrass","creeperOverStone":
                    if(!swing) {
                        int damage = 1;
                        swing = true;
                        swingTime = System.nanoTime();
                        swingBar.setVisible(true);
                        swingBar.setProgress(swingCount/4);
                        if (equipped.getName().endsWith("Axe") || equipped.getName().endsWith("Pickaxe") || equipped.getName().endsWith("Sword")) {
                            damage = equipped.getDamage();
                            equipped.changeDurability(-1);
                        }
                        for (mobsNoCreeper mobs : mobsNoCreepersOnMap) {
                            if (mobs.getX() == playerPositionX && mobs.getY() == playerPositionY + directionChange) {
                                mobs.changeAttacked(true);
                                mobs.changeHealth(-(damage));
                                if (mobs.getHealth() <= 0) {
                                    if (mapBackgroundCave[playerPositionX][playerPositionY + directionChange].equals("grass") || mapBackgroundCave[playerPositionX][playerPositionY + directionChange].equals("normal") || mapBackgroundCave[playerPositionX][playerPositionY + directionChange].equals("fruit") || mapBackgroundCave[playerPositionX][playerPositionY + directionChange].equals("autumn")) {
                                        mapCave[playerPositionX][playerPositionY + directionChange] = "grass";
                                    } else {
                                        mapCave[playerPositionX][playerPositionY + directionChange] = "stone";
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

                        for(Creepers creeper: creepersOnMap){
                            if (creeper.getX() == playerPositionX && creeper.getY() == playerPositionY + directionChange) {
                                creeper.changeAttacked(true);
                                creeper.changeHealth(-(damage));
                                if (creeper.getHealth() <= 0) {
                                    if (mapBackgroundCave[playerPositionX][playerPositionY + directionChange].equals("grass") || mapBackgroundCave[playerPositionX][playerPositionY + directionChange].equals("normal") || mapBackgroundCave[playerPositionX][playerPositionY + directionChange].equals("fruit") || mapBackgroundCave[playerPositionX][playerPositionY + directionChange].equals("autumn")) {
                                        mapCave[playerPositionX][playerPositionY + directionChange] = "grass";
                                    } else {
                                        mapCave[playerPositionX][playerPositionY + directionChange] = "stone";
                                    }
                                    breakB = false;
                                    for (int i = 4; i >= 1; i--) {
                                        for (int j = 1; j <= 5; j++) {
                                            if (inventoryA[i][j].getName().equals(creeper.getResourceDrop().getName())) {
                                                System.out.println("hi");
                                                inventoryA[i][j].changeAmount(creeper.getAmountDrop());
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
                                                inventoryA[i][j] = creeper.getResourceDrop();
                                                inventoryA[i][j].setAmount(creeper.getAmountDrop());
                                                breakB = true;
                                                break;
                                            }
                                        }
                                        if (breakB) {
                                            breakB = false;
                                            break;
                                        }
                                    }


                                    creepersOnMap.remove(creeper);
                                    break;
                                }
                            }
                        }
                    }
                    break;

            }
        }


    }

    private void interactNether() {
        int directionChange = switch (directionInter) {
            case "up" -> -1;
            case "down" -> 1;
            case "right" -> 1;
            case "left" -> -1;
            default -> 0;
        };
        if (directionInter.equals("up") || directionInter.equals("down")) {
            switch (mapNether[playerPositionX + directionChange][playerPositionY]) {
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
                case "coalOre":
                case "torch":
                case "obsidian":


                    miningObject = true;
                    miningX = playerPositionX + directionChange;
                    miningY = playerPositionY;
                    miningBar.setVisible(true);
                    for (mineObjects m : mineObjectsOnMapCave) {
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
                    System.out.println(equipped.getType());
                    if(equipped.getTier()>0){
                        System.out.println(tempMine.getType());
                        if(tempMine.getType().equals(equipped.getType())){
                            toolBoost = equipped.getTier() +(equipped.getTier()*0.6);
                            equipped.changeDurability(-1);
                            System.out.println(toolBoost);
                        }
                    }
//                    mapNether[playerPositionX + directionChange][playerPositionY] = "grass";
                    break;
                case "netherrack","lava":
                    if(!equipped.getName().equals("empty")&&equipped.isPlaceable()&&(playerPositionX+directionChange!=3||playerPositionY!=2)&&!equipped.getName().equals("bossSoul")){
                        int mineTime;
                        String type;

                        if (equipped.getType().equals("pickaxe")) {
                            mineTime = (int) (Math.random() * 6) + 10;
                            type = "pickaxe";
                        } else {
                            mineTime = (int) (Math.random() * 5) + 5;
                            type = "axe";
                        }

                        mapNether[playerPositionX + directionChange][playerPositionY] = equipped.getName();
                        System.out.println(mapNether[playerPositionX + directionChange][playerPositionY]);
                        mineObjectsOnMapCave.add(new mineObjects(equipped.getName(), equipped.getType(), mineTime, new Resources(equipped.getName(), type), 1, playerPositionX + directionChange, playerPositionY));
                        if (equipped.getAmount() > 1) {
                            equipped.changeAmount(-1);
                        } else {
                            inventoryA[4][selected + 1] = new inventoryItems("empty");
                            System.out.println(inventoryA[4][selected + 1].getName());
                            updateScreen();
                            System.out.println(hotbar[selected].getName());
                            System.out.println(equipped.getName());
                        }
                    }
                    else if (equipped.isEatable()) {
                        eatingFood = true;
                        miningBar.setVisible(true);
                        miningBar.setProgress(1.0);
                    }else if (equipped.getName().equals("bossSoul")&&wither.size()==0) {
                        if(mapNether[playerPositionX+directionChange][playerPositionY].equals("netherrack")){
                            placeBossX = playerPositionX + directionChange;
                            placeBossY = playerPositionY;
                            placedBoss = true;
                            placeBossTime = System.nanoTime();
                            miningBar.setVisible(true);
                            miningBar.setProgress(1.0);

                        }
                    }
                    break;
                case "witherOverNetherrack","witherOverLava":
                    if(!swing) {
                        swing = true;
                        swingTime = System.nanoTime();
                        swingBar.setVisible(true);
                        swingBar.setProgress(swingCount/4);
                        int damage = 1;
                        if (equipped.getName().endsWith("Axe") || equipped.getName().endsWith("Pickaxe") || equipped.getName().endsWith("Sword")) {
                            damage = equipped.getDamage();
                            equipped.changeDurability(-1);
                        }
                        for (Wither w : wither) {
                            if (w.getX() == playerPositionX + directionChange && w.getY() == playerPositionY) {
                                w.changeHealth(-(damage));
                                witherHealth.setProgress(w.getHealth()/100);
                                System.out.println("HEALTH "+ w.getHealth());
                                if (w.getHealth() <= 0) {
                                    witherHealth.setVisible(false);
                                    if (mapBackgroundNether[playerPositionX + directionChange][playerPositionY].equals("netherrack")) {
                                        mapNether[playerPositionX + directionChange][playerPositionY] = "netherrack";
                                    } else {
                                        mapNether[playerPositionX + directionChange][playerPositionY] = "lava";
                                    }
                                    breakB = false;

                                    for (int i = 4; i >= 1; i--) {
                                        if (breakB) {
                                            breakB = false;
                                            break;
                                        }
                                        for (int j = 1; j <= 5; j++) {
                                            if (inventoryA[i][j].getName().equals("empty")) {
                                                inventoryA[i][j] = w.getResourceDrop();
                                                breakB = true;
                                                break;
                                            }
                                        }
                                        if (breakB) {
                                            breakB = false;
                                            break;
                                        }
                                    }


                                    wither.remove(w);
                                    break;
                                }
                            }
                        }

                    }


                    break;
            }

        } else {
            switch (mapNether[playerPositionX][playerPositionY + directionChange]) {
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
                case "coalOre":
                case "torch":
                case "obsidian":



                    miningObject = true;
                    miningX = playerPositionX;
                    miningY = playerPositionY + directionChange;
                    miningBar.setVisible(true);
                    for (mineObjects m : mineObjectsOnMapNether) {
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
                            equipped.changeDurability(-1);
                            System.out.println(toolBoost);
                        }
                    }
//                    mapNether[playerPositionX][playerPositionY + directionChange] = "grass";
                    break;
                case "netherrack","lava":
                    if(!equipped.getName().equals("empty")&&equipped.isPlaceable()&&(playerPositionX!=3||playerPositionY+directionChange!=2)&&!equipped.getName().equals("bossSoul")){
                        int mineTime;
                        String type;
                        if(equipped.getType().equals("pickaxe")){
                            mineTime = (int) (Math.random() * 6) + 10;
                            type = "pickaxe";
                        }else{
                            mineTime = (int) (Math.random() * 5) + 5;
                            type = "axe";
                        }

                        mapNether[playerPositionX][playerPositionY + directionChange] = equipped.getName();
                        System.out.println(mapNether[playerPositionX][playerPositionY + directionChange]);
                        mineObjectsOnMapNether.add(new mineObjects(equipped.getName(),equipped.getType(), mineTime, new Resources(equipped.getName(),type), 1, playerPositionX, playerPositionY + directionChange));
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
                    }else if (equipped.getName().equals("bossSoul")&&wither.size()==0) {
                        if(mapNether[playerPositionX][playerPositionY+directionChange].equals("netherrack")){
                            placeBossX = playerPositionX;
                            placeBossY = playerPositionY+directionChange;
                            placedBoss = true;
                            placeBossTime = System.nanoTime();
                            miningBar.setVisible(true);
                            miningBar.setProgress(1.0);

                        }
                    }
                    break;
                case "witherOverNetherrack","witherOverLava":
                    if(!swing) {
                        swing = true;
                        swingTime = System.nanoTime();
                        swingBar.setVisible(true);
                        swingBar.setProgress(swingCount/4);
                        int damage = 1;
                        if (equipped.getName().endsWith("Axe") || equipped.getName().endsWith("Pickaxe") || equipped.getName().endsWith("Sword")) {
                            damage = equipped.getDamage();
                            equipped.changeDurability(-1);
                        }
                        for (Wither w : wither) {
                            if (w.getX() == playerPositionX && w.getY() == playerPositionY + directionChange) {
                                w.changeHealth(-(damage));
                                witherHealth.setProgress(w.getHealth()/100);
                                System.out.println("HEALTH "+ w.getHealth());
                                if (w.getHealth() <= 0) {
                                    witherHealth.setVisible(false);
                                    if (mapBackgroundNether[playerPositionX][playerPositionY + directionChange].equals("netherrack")) {
                                        mapNether[playerPositionX][playerPositionY + directionChange] = "netherrack";
                                    } else {
                                        mapNether[playerPositionX][playerPositionY + directionChange] = "lava";
                                    }
                                    breakB = false;

                                    for (int i = 4; i >= 1; i--) {
                                        if (breakB) {
                                            breakB = false;
                                            break;
                                        }
                                        for (int j = 1; j <= 5; j++) {
                                            if (inventoryA[i][j].getName().equals("empty")) {
                                                inventoryA[i][j] = w.getResourceDrop();
                                                breakB = true;
                                                break;
                                            }
                                        }
                                        if (breakB) {
                                            breakB = false;
                                            break;
                                        }
                                    }


                                    wither.remove(w);
                                    break;
                                }
                            }
                        }

                    }
                    break;

            }
        }


    }

    public void movePlayer(String dirStr, int dirNum, String[][] mapP) {
        if(moveAble) {
            inLava = false;
            if (dirStr.equals("x")) {
                if (mapP[playerPositionX + dirNum][playerPositionY].equals("grass")) {
                    if (mapP[playerPositionX][playerPositionY].equals("playerOverGrass")) {
                        mapP[playerPositionX][playerPositionY] = "grass";
                    } else if (mapP[playerPositionX][playerPositionY].equals("playerOverStone")) {
                        mapP[playerPositionX][playerPositionY] = "stone";
                    }
                    playerPositionX += dirNum;
                    mapP[playerPositionX][playerPositionY] = "playerOverGrass";
                } else if (mapP[playerPositionX + dirNum][playerPositionY].equals("stone")) {
                    if (mapP[playerPositionX][playerPositionY].equals("playerOverGrass")) {
                        mapP[playerPositionX][playerPositionY] = "grass";
                    } else if (mapP[playerPositionX][playerPositionY].equals("playerOverStone")) {
                        mapP[playerPositionX][playerPositionY] = "stone";
                    }
                    playerPositionX += dirNum;
                    mapP[playerPositionX][playerPositionY] = "playerOverStone";

                }else if (mapP[playerPositionX + dirNum][playerPositionY].equals("netherrack")) {
                    if(mapP[playerPositionX][playerPositionY].endsWith("Netherrack")){
                        mapP[playerPositionX][playerPositionY] = "netherrack";
                    }else{
                        mapP[playerPositionX][playerPositionY] = "lava";
                    }
                    playerPositionX += dirNum;
                    mapP[playerPositionX][playerPositionY] = "playerOverNetherrack";
                }else if (mapP[playerPositionX + dirNum][playerPositionY].equals("netherPortal")) {
                    if(!inNether) {
                        enterNether();
                    }else{
                        exitNether();
                    }
                }else if (mapP[playerPositionX + dirNum][playerPositionY].equals("lava")) {
                    if(mapP[playerPositionX][playerPositionY].endsWith("Netherrack")){
                        mapP[playerPositionX][playerPositionY] = "netherrack";
                    }else{
                        mapP[playerPositionX][playerPositionY] = "lava";
                    }
                    playerPositionX += dirNum;
                    mapP[playerPositionX][playerPositionY] = "playerInLava";
                    inLava = true;
                    moveAble = false;
                    moveTime = System.nanoTime();
                }
            } else if (dirStr.equals("y")) {
                if (mapP[playerPositionX][playerPositionY + dirNum].equals("grass")) {
                    if (mapP[playerPositionX][playerPositionY].equals("playerOverGrass")) {
                        mapP[playerPositionX][playerPositionY] = "grass";
                    } else if (mapP[playerPositionX][playerPositionY].equals("playerOverStone")) {
                        mapP[playerPositionX][playerPositionY] = "stone";
                    }
                    playerPositionY += dirNum;
                    mapP[playerPositionX][playerPositionY] = "playerOverGrass";
                } else if (mapP[playerPositionX][playerPositionY + dirNum].equals("stone")) {
                    if (mapP[playerPositionX][playerPositionY].equals("playerOverGrass")) {
                        mapP[playerPositionX][playerPositionY] = "grass";
                    } else if (mapP[playerPositionX][playerPositionY].equals("playerOverStone")) {
                        mapP[playerPositionX][playerPositionY] = "stone";
                    }
                    playerPositionY += dirNum;
                    mapP[playerPositionX][playerPositionY] = "playerOverStone";
                }else if (mapP[playerPositionX][playerPositionY + dirNum].equals("netherrack")) {
                    if(mapP[playerPositionX][playerPositionY].endsWith("Netherrack")){
                        mapP[playerPositionX][playerPositionY] = "netherrack";
                    }else{
                        mapP[playerPositionX][playerPositionY] = "lava";
                    }
                    playerPositionY += dirNum;
                    mapP[playerPositionX][playerPositionY] = "playerOverNetherrack";
                }else if (mapP[playerPositionX][playerPositionY + dirNum].equals("netherPortal")) {
                    if(!inNether) {
                        enterNether();
                    }else{
                        exitNether();
                    }
                }else if (mapP[playerPositionX][playerPositionY + dirNum].equals("lava")) {
                    if(mapP[playerPositionX][playerPositionY].endsWith("Netherrack")){
                        mapP[playerPositionX][playerPositionY] = "netherrack";
                    }else{
                        mapP[playerPositionX][playerPositionY] = "lava";
                    }
                    playerPositionY += dirNum;
                    mapP[playerPositionX][playerPositionY] = "playerInLava";
                    inLava = true;
                    moveAble = false;
                    moveTime = System.nanoTime();
                }
            }
            tempHunger -= 0.1;
            hungerBar.setProgress(tempHunger / totalHunger);
            if(tempHunger<=60){
                moveAble = false;
                moveTime = System.nanoTime();
            }
        }
    }

    private void exitNether() {

        mapNether[playerPositionX][playerPositionY] = "netherrack";


//                int ranX = (int) (Math.random() * 31) + playerPositionX - 15;
//                int ranY = (int) (Math.random() * 31) + playerPositionY - 15;




        while (true) {
            System.out.println("1");
            int ranX = (int) (Math.random() * 31) + OWplayerPositionX - 15;
            int ranY = (int) (Math.random() * 31) + OWplayerPositionY - 15;
            if (map[ranX][ranY].equals("grass")) {
                map[ranX][ranY] = "playerOverGrass";
                playerPositionY = ranY;
                playerPositionX = ranX;
                break;
            }else if (map[ranX][ranY].equals("stone")) {
                map[ranX][ranY] = "playerOverStone";
                playerPositionY = ranY;
                playerPositionX = ranX;
                break;
            }
        }



//        for (int i = 0; i <map.length; i++) {
//            for (int j = 0; j < map[0].length; j++) {
//                if(map[i][j].startsWith("zombie")||map[i][j].startsWith("spider")||map[i][j].startsWith("creeper")){
//                    if (mapBackground[i][j ].equals("grass") || mapBackground[i][j ].equals("normal") || mapBackground[i][j].equals("fruit") || mapBackground[i][j ].equals("autumn")) {
//                        map[i][j] = "grass";
//                    } else {
//                        map[i][j] = "stone";
//                    }
//                }
//            }
//        }


        inNether = false;

    }

    private void enterNether() {
            //NORMAL MAP
            if (mapBackground[playerPositionX][playerPositionY ].equals("grass") || mapBackground[playerPositionX][playerPositionY ].equals("normal") || mapBackground[playerPositionX][playerPositionY].equals("fruit") || mapBackground[playerPositionX][playerPositionY ].equals("autumn")) {
                map[playerPositionX][playerPositionY] = "grass";
            } else {
                map[playerPositionX][playerPositionY] = "stone";
            }
            System.out.println("1");
//                int ranX = (int) (Math.random() * 31) + playerPositionX - 15;
//                int ranY = (int) (Math.random() * 31) + playerPositionY - 15;


            mapNether[3][2] = "playerOverNetherrack";
            OWplayerPositionY = playerPositionY;
            OWplayerPositionX = playerPositionX;
            playerPositionY = 2;
            playerPositionX = 3;



            for (int i = 0; i <map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if(map[i][j].startsWith("zombie")||map[i][j].startsWith("spider")||map[i][j].startsWith("creeper")){
                        if (mapBackground[i][j ].equals("grass") || mapBackground[i][j ].equals("normal") || mapBackground[i][j].equals("fruit") || mapBackground[i][j ].equals("autumn")) {
                            map[i][j] = "grass";
                        } else {
                            map[i][j] = "stone";
                        }
                    }
                }
            }

            mobsNoCreepersOnMap.clear();
            creepersOnMap.clear();
            inNether = true;

    }


    private void createLava(){
        for (int i = 0; i < mapNether.length; i++) {
            for (int j = 0; j < mapNether[0].length; j++) {
                int ranNum = (int)(Math.random()*10);
                if(ranNum == 0){
                    if(mapNether[i][j].equals("netherrack")&&(i!=3||j!=2)) {
                        mapNether[i][j] = "lava";
                        mapBackgroundNether[i][j] = "lava";
                    }
                }
            }
        }
    }
    private void createCaveBiomes(){
        int startX = 0;
        int startY = 0;
        int lengthX = 0;
        int lengthY = 0;
        int randNum;
        boolean valid;

//        for (int i = 0; i < mapBackground.length; i++) {
//            for (int j = 0; j < mapBackground[0].length; j++) {
//                mapCaveBackground[i][j] = "stone";
//            }
//        }
        for (int p = 0; p < 15; p++) {
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
                        if (!mapCave[i][j].equals("stone")) {
                            valid = false;
                            break;
                        }
                    }
                }
            }
            int x = (int)(Math.random()*(lengthX))+startX;
            int y = (int)(Math.random()*(lengthY))+startY;

//            int v1x = (int)(Math.random()*(lengthX))+startX;
//            int v1y = (int)(Math.random()*(lengthY))+startY;
//            int v2x = (int)(Math.random()*(lengthX))+startX;
//            int v2y = (int)(Math.random()*(lengthY))+startY;

            int mineralRand;
            biomeArrayList.add(new Biome(startX, startY, startX + lengthX, startY + lengthY, 1));
            if(!stoneQuest){
                mapCave[x][y] = "stoneQuest";
                stoneQuest = true;
            }
            for (int i = startX; i < startX + lengthX; i++) {
                for (int j = startY; j < startY + lengthY; j++) {
                    mineralRand = (int) (Math.random() * 70);
                    if (mineralRand < 4) {
                        mapCave[i][j] = "rock";
                        mineObjectsOnMapCave.add(new mineObjects("rock","pickaxe", (int) (Math.random() * 5) + 10, new Resources("cobblestone","pickaxe"), (int) (Math.random() * 3) + 2, i, j));

                    } else if (mineralRand < 6) {
                        mapCave[i][j] = "goldOre";
                        mineObjectsOnMapCave.add(new mineObjects("goldOre","pickaxe", (int) (Math.random() * 5) + 15, new Resources("goldOre","pickaxe"), (int) (Math.random() * 2) + 1, i, j));

                    } else if (mineralRand < 8) {
                        mapCave[i][j] = "diamondOre";
                        mineObjectsOnMapCave.add(new mineObjects("diamondOre","pickaxe", (int) (Math.random() * 5) + 20, new inventoryItems("diamond"), 1, i, j));

                    } else if (mineralRand < 11) {
                        mapCave[i][j] = "rubyOre";
                        mineObjectsOnMapCave.add(new mineObjects("rubyOre","pickaxe", (int) (Math.random() * 5) + 15, new Resources("rubyOre","pickaxe"), (int) (Math.random() * 2) + 1, i, j));

                    }else if (mineralRand < 15) {
                        mapCave[i][j] = "coalOre";
                        mineObjectsOnMapCave.add(new mineObjects("coalOre","pickaxe", (int) (Math.random() * 5) + 15, new inventoryItems("coal"), (int) (Math.random() * 2) + 1, i, j));

                    }
                }
            }
        }

        for (Biome biome : biomeArrayList) {
            System.out.println(biome.getSx() + " " + biome.getSy() + " " + biome.getEx() + " " + biome.getEy());
        }
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

            int v1x = (int)(Math.random()*(lengthX))+startX;
            int v1y = (int)(Math.random()*(lengthY))+startY;
            int v2x = (int)(Math.random()*(lengthX))+startX;
            int v2y = (int)(Math.random()*(lengthY))+startY;

            switch (biomeNameList.get(randNum)) {
                case "normalTree" -> {
                    System.out.println("normal");
                    biomeArrayList.add(new Biome(startX, startY, startX + lengthX, startY + lengthY, 0));
                    if(!normalQuest){
                        map[x][y] = "normalQuest";
                        normalQuest = true;
                    }
                    villagersOnMap.add(new Villagers("villager", 15, (Math.random()*15), v1x, v1y, "normal"));
                    villagersOnMap.add(new Villagers("villager", 15, (Math.random()*15), v2x, v2y, "normal"));
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
                    villagersOnMap.add(new Villagers("villager", 15, (Math.random()*15), v1x, v1y, "fruit"));
                    villagersOnMap.add(new Villagers("villager", 15, (Math.random()*15), v2x, v2y, "fruit"));
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
                    villagersOnMap.add(new Villagers("villager", 15, (Math.random()*15), v1x, v1y, "autumn"));
                    villagersOnMap.add(new Villagers("villager", 15, (Math.random()*15), v2x, v2y, "autumn"));
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

                            mineralRand = (int) (Math.random() * 70);
                            if (i == startX || j == startY || i == startX + lengthX - 1 || j == startY + lengthY - 1) {

                                if ((int) (Math.random() * 2) == 0) {
                                    mapBackground[i][j] = "stone";
                                    map[i][j] = "stone";
                                }
                            } else {
                                if (map[i][j].equals("grass")) {
                                    mapBackground[i][j] = "stone";
                                    if (mineralRand < 7) {
                                        map[i][j] = "rock";
                                        mineObjectsOnMap.add(new mineObjects("rock","pickaxe", (int) (Math.random() * 5) + 10, new Resources("cobblestone","pickaxe"), (int) (Math.random() * 3) + 2, i, j));
                                    }
                                    else {
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