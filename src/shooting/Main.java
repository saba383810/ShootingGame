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
    static boolean[] isGetKeyCode = new boolean[6];
    public static Player player = new Player();
    public static Label lb;
    public static Stage startScreen;
    public static Pane root;
    public static int screenMinX =0;
    public static int screenMinY=0;
    public static int screenMaxX =590;
    public static int screenMaxY=660;
    public static int score=0;
    public static Health hpGage;
    public static ArrayList<ImageView> bulletList = new ArrayList<>();
    public static ArrayList<ImageView> enemyBulletList = new ArrayList<>();

    public GameOver gameOver;
    public static EnemyManagement eneMane;

    ImageView stageUI;


    public Main(Stage stage)  {
        startScreen = stage;

        //Stage設定、タイトル、大きさ
        setTitle("シューティングゲーム(仮)");
        setWidth(900);
        setHeight(720);

        stageUI = new ImageView(stageUIImg);
        stageUI.setTranslateX(620);
        stageUI.setTranslateY(0);

        player = new Player();
        hpGage=new Health();

        eneMane=new EnemyManagement();

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
        BackgroundImage bimg = new BackgroundImage(backgroundImg1, null, null, null, null);
        Background bg1 = new Background(bimg);
        root.setBackground(bg1);

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(this::keyPress);
        scene.setOnKeyReleased(this::keyRelease);

        setScene(scene);

        //×ボタンで、プログラム終了
        setOnCloseRequest(event -> System.exit(0));

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
    public static boolean[] getKeyCodePress(){ return isGetKeyCode; }

    //キャラクターのたま表示
    public static void shot(int charaX,int charaY,String character){
        Bullet bullet = new Bullet(charaX, charaY,character);
        root.getChildren().add(bullet);
        bulletList.add(bullet);
    }
    public static void enemyShot(int charaX,int charaY,String character){
        EnemyBullet enemyBullet = new EnemyBullet(charaX, charaY,character);
        root.getChildren().add(enemyBullet);
        enemyBulletList.add(enemyBullet);
    }
    //敵のアクション番号に応じて敵を生成
    public static void addEnemy(int actNum){
        Enemy enemy = new Enemy(actNum);
        root.getChildren().add(enemy);
    }
    //取得したスコア分スコアを増加
    public static void addScore(int addScore){
        score+=addScore;
        lb.setText(String.valueOf(score));
    }
    public static void changeHP(int playerHP){ hpGage.checkHP(playerHP);}
    public static ArrayList getBulletList(){return bulletList;}
    public static ArrayList getEnemyBulletList(){return enemyBulletList;}

    public void gameOver(){
        this.close();
        Player.playerStop();
        EnemyManagement.enemyManagementStop();
        gameOver = new GameOver(startScreen,score);
    }
}
