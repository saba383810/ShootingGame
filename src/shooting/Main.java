package shooting;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.nio.file.Paths;
import java.util.ArrayList;

public class Main extends Stage {

    static Image backgroundImg1 = new Image(Paths.get("InvadersImage/UI/Stage1Background.jpg").toUri().toString());
    static Image stageUIImg= new Image(Paths.get("InvadersImage/UI/stageUI1.png").toUri().toString());
    static PlayClip boss2BGM = new PlayClip("InvadersMusic/boss2.wav");
    static PlayClip stage2BGM = new PlayClip("InvadersMusic/stage2.wav");
    static boolean[] isGetKeyCode = new boolean[6];
    public static Player player;
    public static Label lb;
    public static Stage startScreen;
    public static Pane root;
    public static int screenMinX =0;
    public static int screenMinY=0;
    public static int screenMaxX =590;
    public static int screenMaxY=660;
    public static int score;
    public static Health hpGage;
    public static BossHealth bossHealth;
    public static ArrayList<ImageView> bulletList = new ArrayList<>();
    public static ArrayList<ImageView> enemyBulletList = new ArrayList<>();
    public static ArrayList<Enemy> enemyList = new ArrayList<>();

    public static GameOver gameOver;
    public static EnemyManagement eneMane;

    ImageView stageUI;
    Boss boss;
    boolean isBoss = false;


    public Main(Stage stage)  {
        startScreen = stage;
        score =0;
        //Stage設定、タイトル、大きさ
        setTitle("Stage1");
        setWidth(900);
        setHeight(720);

        stageUI = new ImageView(stageUIImg);
        stageUI.setTranslateX(620);
        stageUI.setTranslateY(0);

        player = new Player(this);
        hpGage=new Health();

        eneMane=new EnemyManagement(this);

        lb = new Label(String.valueOf(score));
        lb.setFont(Font.font(30));
        Color c = Color.web("bed7de",1.0);
        lb.setTextFill(c);
        lb.setTextFill(Color.WHITE);
        lb.setTranslateX(680);
        lb.setTranslateY(100);

        root = new Pane();
        root.getChildren().addAll(player,stageUI,lb,hpGage);

        //背景
        BackgroundImage bImg = new BackgroundImage(backgroundImg1, null, null, null, null);
        Background bg1 = new Background(bImg);
        root.setBackground(bg1);

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(this::keyPress);
        scene.setOnKeyReleased(this::keyRelease);

        setScene(scene);

        //×ボタンで、プログラム終了
        setOnCloseRequest(event -> System.exit(0));
        stage2BGM.clip.loop(10);
        stage2BGM.reset();
        stage2BGM.play();
        show();
    }

    public void keyPress(KeyEvent event) {

        if (event.getCode() == KeyCode.UP) isGetKeyCode[0] = true;
        if (event.getCode() == KeyCode.DOWN) isGetKeyCode[1] = true;
        if (event.getCode() == KeyCode.RIGHT) isGetKeyCode[2] = true;
        if (event.getCode() == KeyCode.LEFT) isGetKeyCode[3] = true;
        if (event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.Z) isGetKeyCode[4] = true;
        if (event.getCode() == KeyCode.SHIFT) isGetKeyCode[5] = true;
        if (event.getCode() == KeyCode.A)gameOver();


    }
    public void keyRelease(KeyEvent event){
        if (event.getCode() == KeyCode.UP) isGetKeyCode[0] = false;
        if (event.getCode() == KeyCode.DOWN)isGetKeyCode[1] = false;
        if (event.getCode() == KeyCode.RIGHT) isGetKeyCode[2] = false;
        if (event.getCode() == KeyCode.LEFT) isGetKeyCode[3] = false;
        if (event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.Z) isGetKeyCode[4] = false;
        if (event.getCode() == KeyCode.SHIFT) isGetKeyCode[5] = false;
    }

    //現在trueになってるキーコードをreturn
    public boolean[] getKeyCodePress(){ return isGetKeyCode; }
    //キャラクターのたま表示
    public void shot(){
        Bullet bullet = new Bullet((int)player.getTranslateX(),(int)player.getTranslateY());
        root.getChildren().add(bullet);
        bulletList.add(bullet);
    }
    public void enemyShot(int charaX,int charaY,String character){
        EnemyBullet enemyBullet = new EnemyBullet(charaX, charaY,character);
        root.getChildren().add(enemyBullet);
        enemyBulletList.add(enemyBullet);
    }
    //敵のアクション番号に応じて敵を生成
    public void addEnemy(int actNum){
        Enemy enemy = new Enemy(actNum,this);
        root.getChildren().add(enemy);
        enemyList.add(enemy);
    }
    public void addBoss(){
        boss = new Boss(this);
        root.getChildren().add(boss);
        isBoss=true;


    }
    public void addBossHealth(){
        bossHealth = new BossHealth();
        root.getChildren().add(bossHealth);
    }

    //取得したスコア分スコアを増加
    public void addScore(int addScore){
        score+=addScore;
        lb.setText(String.valueOf(score));
    }
    public void changeHP(int playerHP){ hpGage.checkHP(playerHP);}
    public void changeBossHP(int bossHP){bossHealth.checkBossHP(bossHP);}
    public  ArrayList getBulletList(){return bulletList;}
    public  ArrayList getEnemyBulletList(){return enemyBulletList;}

    public void gameOver(){
        for(Enemy enemy :enemyList){
            enemy.enemyStop();
        }
        if(isBoss) boss.bossStop();
        this.close();
        player.playerStop();
        stage2BGM.stop();
        boss2BGM.stop();
        eneMane.enemyManagementStop();
        gameOver = new GameOver(startScreen,score,false);
    }
    public void stageClear(){
        for(Enemy enemy :enemyList){
            enemy.enemyStop();
        }
        if(isBoss) boss.bossStop();
        this.close();
        eneMane.enemyManagementStop();
        player.playerStop();
        stage2BGM.stop();
        boss2BGM.stop();
        gameOver = new GameOver(startScreen,score,true);

    }
    public void changeBGM(){
        stage2BGM.stop();
        boss2BGM.reset();
        boss2BGM.play();
    }
}
