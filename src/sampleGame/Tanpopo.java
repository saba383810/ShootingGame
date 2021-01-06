package sampleGame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.nio.file.Paths;


public class Tanpopo extends ImageView {

    static Image tanpopoImage = new
            Image(Paths.get("sampleGameImages/tanpopo.png").toUri().toString());
    static Image flowerImg = new
            Image(Paths.get("sampleGameImages/flower.png").toUri().toString());
    Timeline timeline;
    boolean[] isGetKeyCode;
    //ｘ座標
    int x=500;
    int y=400;
    //コンストラクタ
    Tanpopo(){
        super(tanpopoImage);
        setTranslateY(y);
        setTranslateX(x);

        timeline = new Timeline(new KeyFrame(Duration.millis(10), event->{run();}));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    //moveメソッド ※矢印キー、スペースキーを調べて、タンポポを動かす
    public void run(){
        //keycode取得
        isGetKeyCode = Main.getKeyCodePress();
        System.out.println(isGetKeyCode[0]);
        if(isGetKeyCode[0]==true) y-=5;
        if(isGetKeyCode[1]==true) y+=5;
        if(isGetKeyCode[2]==true) x+=5;
        if(isGetKeyCode[3]==true) x-=5;

//        if(key==KeyCode.RIGHT&&x<770) x+=5;
//        if(key==KeyCode.LEFT&&x>-80) x-=5;
//        if(key==KeyCode.UP&&y>-770) y-=5;
//        if(key==KeyCode.DOWN&&y<400) y+=5;

        setTranslateX(x);
        setTranslateY(y);
    }
    public void changeImage( ){
        if(getImage()==flowerImg) setImage(tanpopoImage);
        else setImage(flowerImg);

    }
}
