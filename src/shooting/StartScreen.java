package shooting;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class StartScreen extends Application {
    Stage startScreen;
    Stage main;
    Stage howToPlayScreen;
    Stage scoreScreen;
    Pane root;

    public static int selectNum;
    static Image backgroundImg = new Image(Paths.get("InvadersImage/UI/BlackBack.png").toUri().toString());
    static Image startLogoImage = new Image(Paths.get("InvadersImage/UI/ShootingGame.png").toUri().toString());
    static Image startImage = new Image(Paths.get("InvadersImage/UI/GameStart.png").toUri().toString());
    static Image howToPlayImage = new Image(Paths.get("InvadersImage/UI/HowToPlay.png").toUri().toString());
    static Image scoreImage = new Image(Paths.get("InvadersImage/UI/Score.png").toUri().toString());
    static Image exitImage = new Image(Paths.get("InvadersImage/UI/Exit.png").toUri().toString());
    static Image bossImage = new Image(Paths.get("InvadersImage/Character/Boss1.png").toUri().toString(),200,200,false,false);
    static PlayClip cursorDec = new PlayClip("InvadersMusic/cursorSE.wav");
    static PlayClip startSceneBGM = new PlayClip("InvadersMusic/startScene.wav");

    //--------main----------
    public static void main (String[] args){ launch(); }

    //-------start-------------
    public void start(Stage stage){


        startScreen =stage;
        startScreen.setTitle("シューティングゲーム");
        startScreen.setWidth(900);
        startScreen.setHeight(720);

        root = new Pane();
        ImageView startLogo = new ImageView(startLogoImage);
        startLogo.setTranslateX(130);
        startLogo.setTranslateY(80);
        ImageView start = new ImageView(startImage);
        start.setTranslateX(110);
        start.setTranslateY(280);
        ImageView howToPlay = new ImageView(howToPlayImage);
        howToPlay.setTranslateX(100);
        howToPlay.setTranslateY(380);
        ImageView score = new ImageView(scoreImage);
        score.setTranslateX(100);
        score.setTranslateY(480);
        ImageView exit = new ImageView(exitImage);
        exit.setTranslateX(100);
        exit.setTranslateY(570);
        ImageView boss1 = new ImageView(bossImage);
        boss1.setTranslateX(600);
        boss1.setTranslateY(400);

        Cursor cursor = new Cursor(0);

        root.getChildren().addAll(startLogo,start,howToPlay,score,exit,cursor,boss1);

        //背景
        BackgroundImage bimg = new BackgroundImage(backgroundImg, null, null, null, null);
        Background bg1 = new Background(bimg);
        root.setBackground(bg1);

        Scene scene = new Scene(root);

        scene.setOnKeyPressed(this::keyPress);

        startScreen.setScene(scene);

        //×ボタンで、プログラム終了
        startScreen.setOnCloseRequest(event -> System.exit(0));

        startSceneBGM.clip.loop(10);
        startSceneBGM.play();
        startScreen.show();
    }
    public void keyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.UP&&selectNum>0) {
            selectNum--;
        }
        if (event.getCode() == KeyCode.DOWN&&selectNum<3) {
            selectNum++;
        }

        if(event.getCode()== KeyCode.SPACE||event.getCode() == KeyCode.Z||event.getCode()==KeyCode.ENTER){
            switch (selectNum){
                case 0:
                    //GameStart!!
                    cursorDec.reset();
                    cursorDec.play();
                    startSceneBGM.stop();
                    startScreen.close();
                    main = new Main(this.startScreen);
                    break;
                case 1: //遊び方
                    cursorDec.reset();
                    cursorDec.play();
                    startScreen.close();
                    howToPlayScreen = new HowToPlayScreen(this.startScreen);
                    break;
                case 2:
                    //Score画面
                    cursorDec.reset();
                    cursorDec.play();
                    startScreen.close();
                    scoreScreen = new ScoreScreen(this.startScreen);
                    break;
                case 3:
                    //ゲーム終了
                    cursorDec.reset();
                    cursorDec.play();
                    System.exit(0);
                    break;
            }
        }
    }
    public static int getSelectNum(){
        return selectNum;
    }
    public static void musicStart() {
        startSceneBGM.reset();
        startSceneBGM.play();
    }
}

