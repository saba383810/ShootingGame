package shooting;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.nio.file.Paths;

public class Cursor extends ImageView {
    static Image cursorImage = new Image(Paths.get("InvadersImage/cursor.png").toUri().toString());

    static PlayClip cursorSE = new PlayClip("InvadersMusic/cursorSE.wav");
    int cursorSEnum;
    Timeline timeline;
    int selectNum;

    public Cursor(){
        super(cursorImage);
        timeline = new Timeline(new KeyFrame(Duration.millis(50), event->{run();}));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void run(){
        selectNum = StartScreen.getSelectNum();
        if(selectNum==0){
            setTranslateX(15);
            setTranslateY(280);
            if(cursorSEnum!=0){
                cursorSE.reset();
                cursorSE.play();
            }
             cursorSEnum=0;
        }else if(selectNum==1){
            setTranslateX(15);
            setTranslateY(380);
            if(cursorSEnum!=1){
                cursorSE.reset();
                cursorSE.play();
            }
            cursorSEnum=1;

        }else if(selectNum==2){
            setTranslateX(15);
            setTranslateY(480);
            if(cursorSEnum!=2){
                cursorSE.reset();
                cursorSE.play();
            }
            cursorSEnum=2;

        }else{
            setTranslateX(15);
            setTranslateY(580);
            if(cursorSEnum!=3) {
                cursorSE.reset();
                cursorSE.play();
            }
            cursorSEnum=3;

        }

    }


}
