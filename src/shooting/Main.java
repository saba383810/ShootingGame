package shooting;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class Main extends Stage {

    static Image backgroundImg1 = new Image(Paths.get("InvadersImage/Stage1Background.jpg").toUri().toString());

    static boolean isGetKeyCode[] = new boolean[6];
    public static Player player = new Player();
    public static Stage startScreen;
    public static Pane root;
    public static int screenMinX =0;
    public static int screenMinY=0;
    public static int screenMaxX =1050;
    public static int screenMaxY=660;


    public Main(Stage stage)  {
        startScreen = stage;

        //Stage設定、タイトル、大きさ
        stage.setTitle("シューティングゲーム(仮)");
        stage.setWidth(1080);
        stage.setHeight(720);

        player = new Player();

        root = new Pane();
        root.getChildren().addAll(player);

        //背景
        BackgroundImage bimg = new BackgroundImage(backgroundImg1, null, null, null, null);
        Background bg1 = new Background(bimg);
        root.setBackground(bg1);

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event -> keyPress(event));
        scene.setOnKeyReleased(event->keyRelease(event));

        stage.setScene(scene);

        //×ボタンで、プログラム終了
        stage.setOnCloseRequest(event -> System.exit(0));

        stage.show();
    }

    public void keyPress(KeyEvent event) {

        if (event.getCode() == KeyCode.UP) isGetKeyCode[0] = true;
        if (event.getCode() == KeyCode.DOWN) isGetKeyCode[1] = true;
        if (event.getCode() == KeyCode.RIGHT) isGetKeyCode[2] = true;
        if (event.getCode() == KeyCode.LEFT) isGetKeyCode[3] = true;
        if (event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.Z) isGetKeyCode[4] = true;
        if (event.getCode() == KeyCode.SHIFT) isGetKeyCode[5] = true;

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


    public static void shot(){
        Bullet bullet = new Bullet(player.x, player.y);
        root.getChildren().add(bullet);
    }
}
