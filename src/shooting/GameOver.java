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

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GameOver extends Stage{

    public static Stage startScreen;
    public static Stage mainStage;
    public static Pane root;
    public static int selectNum;
    int score;
    String highScore;
    Label scoreLabel;
    Label highScoreLabel;
    static Image stageClearBack= new Image(Paths.get("InvadersImage/UI/StageClear.png").toUri().toString());
    static Image gameOverBack= new Image(Paths.get("InvadersImage/UI/gameOver.png").toUri().toString());
    static Image menuImg = new Image(Paths.get("InvadersImage/UI/menu.png").toUri().toString());
    static Image restartImg = new Image(Paths.get("InvadersImage/UI/restart.png").toUri().toString());
    static Image exitImg = new Image(Paths.get("InvadersImage/UI/Exit.png").toUri().toString());
    static Image newImg = new Image(Paths.get("InvadersImage/UI/new.png").toUri().toString());
    static PlayClip gameOverBGM = new PlayClip("InvadersMusic/gameOver.wav");

    public GameOver(Stage startScreen,int newScore,boolean isClear){
        this.startScreen = startScreen;
        this.score = newScore;
        ImageView newImgView = new ImageView(newImg);
        newImgView.setTranslateX(50);
        newImgView.setTranslateY(500);

        //highScore取得&書き込み
        try {
            File file = new File("src/shooting/highScore.txt");
            FileReader filereader = new FileReader(file);
            int ch = filereader.read();
            highScore= String.valueOf((char)ch);
            while(ch != -1){
                ch = filereader.read();
                highScore +=String.valueOf((char)ch);
            }
            filereader.close();
        }catch (IOException e){
            System.out.println(e);
        }finally { highScore = highScore.substring(0, highScore.length() - 1); }

        //ハイスコアよりスコアの方が大きかったら書き込む
        if(score>Integer.parseInt(highScore)) {
            try {
                File file = new File("src/shooting/highScore.txt");
                FileWriter filewriter = new FileWriter(file);
                System.out.println(String.valueOf(score));
                filewriter.write(String.valueOf(score));
                filewriter.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }else{
            newImgView.setImage(null);
        }

        //Stage設定
        setTitle("Stage1");
        setWidth(900);
        setHeight(720);

        Cursor cursor = new Cursor(1);


        ImageView restart = new ImageView(restartImg);
        restart.setTranslateX(650);
        restart.setTranslateY(320);

        ImageView menu = new ImageView(menuImg);
        menu.setTranslateX(650);
        menu.setTranslateY(420);

        ImageView exit = new ImageView(exitImg);
        exit.setTranslateX(650);
        exit.setTranslateY(520);

        scoreLabel = new Label(String.valueOf(score));
        scoreLabel.setFont(Font.font(50));
        Color c = Color.web("bed7de",1.0);
        scoreLabel.setTextFill(c);
        scoreLabel.setTextFill(Color.WHITE);;
        scoreLabel.setTranslateX(300);
        scoreLabel.setTranslateY(520);

        highScoreLabel = new Label(highScore);
        highScoreLabel.setFont(Font.font(50));
        highScoreLabel.setTextFill(c);
        highScoreLabel.setTextFill(Color.WHITE);;
        highScoreLabel.setTranslateX(300);
        highScoreLabel.setTranslateY(420);

        root = new Pane();
        root.getChildren().addAll(cursor,menu,restart,exit,scoreLabel,highScoreLabel,newImgView);

        //背景
        BackgroundImage bImg;

        if(isClear) bImg = new BackgroundImage(stageClearBack, null, null, null, null);
        else bImg = new BackgroundImage(gameOverBack, null, null, null, null);
        Background bg1 = new Background(bImg);
        root.setBackground(bg1);

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(this::keyPress);

        setScene(scene);

        //×ボタンで、プログラム終了
        setOnCloseRequest(event -> System.exit(0));
        gameOverBGM.clip.loop(10);
        gameOverBGM.reset();
        gameOverBGM.play();

        show();
    }

    public void keyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.UP&&selectNum>0) {
            selectNum--;
        }
        if (event.getCode() == KeyCode.DOWN&&selectNum<2) {
            selectNum++;
        }

        if(event.getCode()== KeyCode.SPACE||event.getCode() == KeyCode.Z||event.getCode()==KeyCode.ENTER){
            switch (selectNum){
                case 0:
                    //GameReStart!!
                    //ゲームオーバー音楽ストップ
                    this.close();
                    gameOverBGM.stop();
                    mainStage = new Main(this.startScreen);
                    break;
                case 1:
                    //StartSceneに戻る
                    this.close();
                    gameOverBGM.stop();
                    startScreen.show();
                    StartScreen.musicStart();
                    break;
                case 2:
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
