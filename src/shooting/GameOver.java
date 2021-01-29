package shooting;

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

public class GameOver extends Stage{

    public static Stage startScreen;
    public static Stage mainStage;
    public static Pane root;
    public static int selectNum;
    static Image gameOverBack= new Image(Paths.get("InvadersImage/UI/gameOver.png").toUri().toString());
    static Image menuImg = new Image(Paths.get("InvadersImage/UI/menu.png").toUri().toString());
    static Image restartImg = new Image(Paths.get("InvadersImage/UI/restart.png").toUri().toString());
    static Image exitImg = new Image(Paths.get("InvadersImage/UI/Exit.png").toUri().toString());
    public GameOver(Stage startScreen,int score){
        this.startScreen = startScreen;

        //Stage設定、タイトル、大きさ
        setTitle("Stage1");
        setWidth(900);
        setHeight(720);

        Cursor cursor = new Cursor(1);

        ImageView menu = new ImageView(menuImg);
        menu.setTranslateX(650);
        menu.setTranslateY(320);

        ImageView restart = new ImageView(restartImg);
        restart.setTranslateX(650);
        restart.setTranslateY(420);
        ImageView exit = new ImageView(exitImg);
        exit.setTranslateX(650);
        exit.setTranslateY(520);

        root = new Pane();
        root.getChildren().addAll(cursor,menu,restart,exit);

        //背景
        BackgroundImage bImg = new BackgroundImage(gameOverBack, null, null, null, null);
        Background bg1 = new Background(bImg);
        root.setBackground(bg1);

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(this::keyPress);

        setScene(scene);

        //×ボタンで、プログラム終了
        setOnCloseRequest(event -> System.exit(0));

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
//                    cursorDec.reset();
//                    cursorDec.play();
                    //ゲームオーバー音楽ストップ
                    this.close();
                    mainStage = new Main(this.startScreen);
                    break;
                case 1:
                    //StartSceneに戻る
//                    cursorDec.reset();
//                    cursorDec.play();
                    this.close();
                    startScreen.show();
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
