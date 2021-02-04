package shooting;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class HowToPlayScreen extends Stage{
    static Image HowToPlayImage = new Image(Paths.get("InvadersImage/UI/HowToPlayScreen.png").toUri().toString());
    static PlayClip se = new PlayClip("InvadersMusic/cursorSE.wav");
    public  Stage startScreen;
    public  Pane root;

    public HowToPlayScreen(Stage startScreen){
        this.startScreen = startScreen;

        //Stage設定、タイトル、大きさ
        setTitle("シューティングゲーム(仮)");
        setWidth(900);
        setHeight(720);

        root = new Pane();

        //背景
        BackgroundImage bImg = new BackgroundImage(HowToPlayImage, null, null, null, null);
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
        se.reset();
        se.play();
        this.close();
        startScreen.show();
    }
}
