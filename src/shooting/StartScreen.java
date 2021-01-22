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
    Pane root;

    public static int selectNum;

    static Image backgroundImg = new Image(Paths.get("InvadersImage/BlackBack.png").toUri().toString());
    static Image startLogoImage = new Image(Paths.get("InvadersImage/ShootingGame.png").toUri().toString());
    static Image startImage = new Image(Paths.get("InvadersImage/GameStart.png").toUri().toString());
    static Image howToPlayImage = new Image(Paths.get("InvadersImage/HowToPlay.png").toUri().toString());
    static Image scoreImage = new Image(Paths.get("InvadersImage/Score.png").toUri().toString());
    static Image exitImage = new Image(Paths.get("InvadersImage/Exit.png").toUri().toString());
    //static PlayClip pc = new PlayClip(Paths.get("InvadersMusic/startScene.wav").toUri().toString());

    //--------main----------
    public static void main (String[] args){ launch(); }

    //-------start-------------
    public void start(Stage stage){


        startScreen =stage;
        startScreen.setTitle("シューティングゲーム(仮)");
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

        Cursor cursor = new Cursor();

        root.getChildren().addAll(startLogo,start,howToPlay,score,exit,cursor);

        //背景
        BackgroundImage bimg = new BackgroundImage(backgroundImg, null, null, null, null);
        Background bg1 = new Background(bimg);
        root.setBackground(bg1);

        Scene scene = new Scene(root);

        scene.setOnKeyPressed(this::keyPress);

        startScreen.setScene(scene);

        //×ボタンで、プログラム終了
        startScreen.setOnCloseRequest(event -> System.exit(0));
        //pc.play();
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
                    startScreen.close();
                    main = new Main(this.startScreen);
                    break;
                case 1: //遊び方
                    startScreen.close();
                    howToPlayScreen = new HowToPlayScreen(this.startScreen);
                    break;
                case 2:
                    /*----------------
                        Score 表示
                    ----------------*/
                    break;
                case 3:
                    //ゲーム終了
                    System.exit(0);
                    break;
            }
        }
    }
    public static int getSelectNum(){
        return selectNum;
    }
}

