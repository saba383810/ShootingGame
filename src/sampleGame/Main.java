package sampleGame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.nio.file.Paths;
import java.util.ArrayList;

public class Main extends Application {
    //共通的に使う変数はここに書く
    Tanpopo tanpopo;
    Taiyou sun;
    Bee bee;
    static Image backgroundImg = new Image(Paths.get("sampleGameImages/background.png").toUri().toString());
    static boolean[] isGetKeyCode = new boolean[5];
     /*
        --isGetKeyCode---
        0:上やじるし
        1:下矢印
        2:右矢印
        3:左矢印
        4:space
         */

    @Override
    public void start(Stage stage)
    {
        //Stage設定、タイトル、大きさ
        stage.setTitle("矢印キーを押してごらん");
        stage.setWidth(1000);
        stage.setHeight(700);

        tanpopo = new Tanpopo();
        sun = new Taiyou();
        bee = new Bee();
        bee.addObserver(sun);
        Pane root = new Pane();
        root.getChildren().addAll(sun,bee,tanpopo);

        BackgroundImage bimg = new BackgroundImage(backgroundImg, null, null, null, null);
        Background bg1 = new Background(bimg);
        root.setBackground(bg1);

        Scene scene = new Scene(root);

        scene.setOnKeyPressed(this::keyPress);
        scene.setOnKeyReleased(this::keyRelease);

        stage.setScene(scene);
        //×ボタンで、プログラム終了

        stage.setOnCloseRequest(event -> System.exit(0));

        stage.show();
    }

    // キーが押された時の処理
    public void keyPress(KeyEvent event)
    {
//        if (event.getCode() == KeyCode.RIGHT) {//右→
//            tanpopo.move(KeyCode.RIGHT);
//        }
//        if (event.getCode() == KeyCode.LEFT) {//hidari
//            tanpopo.move(KeyCode.LEFT);
//        }
//        if (event.getCode() == KeyCode.UP) {//ue
//            tanpopo.move(KeyCode.UP);
//        }
//        if (event.getCode() == KeyCode.DOWN) {//sita
//            tanpopo.move(KeyCode.DOWN);
//        }
//        if(event.getCode() == KeyCode.SPACE) {//supe-su
//            bee.move(tanpopo.x);
//        }
        //同時処理
        if (event.getCode() == KeyCode.UP)isGetKeyCode[0] = true;
        if (event.getCode() == KeyCode.DOWN)isGetKeyCode[1] = true;
        if (event.getCode() == KeyCode.RIGHT) isGetKeyCode[2] = true;
        if (event.getCode() == KeyCode.LEFT) isGetKeyCode[3] = true;
        if (event.getCode() == KeyCode.SPACE) isGetKeyCode[4] = true;
    }

    public void keyRelease(KeyEvent event){
        if (event.getCode() == KeyCode.UP)isGetKeyCode[0] = false;
        if (event.getCode() == KeyCode.DOWN)isGetKeyCode[1] = false;
        if (event.getCode() == KeyCode.RIGHT) isGetKeyCode[2] = false;
        if (event.getCode() == KeyCode.LEFT) isGetKeyCode[3] = false;
        if (event.getCode() == KeyCode.SPACE) isGetKeyCode[4] = false;
    }
    public static boolean[] getKeyCodePress(){
        return isGetKeyCode;
    }

    public static void main (String[] args){
        System.out.println("画像を表示して、矢印キーで動かしてみよう");

        launch();
    }
}
