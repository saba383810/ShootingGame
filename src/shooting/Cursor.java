package shooting;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.nio.file.Paths;

public class Cursor extends ImageView {
    static Image cursorImage = new Image(Paths.get("InvadersImage/UI/cursor.png").toUri().toString());

    static PlayClip cursorSE = new PlayClip("InvadersMusic/cursorSE.wav");
    int cursorSEnum;
    Timeline timeline;
    int selectNum;
    int stageNum;

    public Cursor(int stageNum){
        super(cursorImage);
        this.stageNum = stageNum;
        if(stageNum==0) setTranslateX(15);
        else setTranslateX(550);
        timeline = new Timeline(new KeyFrame(Duration.millis(30), event->{run();}));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void run() {
        if (stageNum == 0) selectNum = StartScreen.getSelectNum();
        else selectNum = GameOver.getSelectNum();

        if (selectNum == 0) {
            if (stageNum == 0) setTranslateY(280);//StartScreen
            else setTranslateY(340);//GameOver

            if (cursorSEnum != 0) {
                cursorSE.reset();
                cursorSE.play();
            }
            cursorSEnum = 0;
        } else if (selectNum == 1) {
            if (stageNum == 0) setTranslateY(380);
            else setTranslateY(440);

            if (cursorSEnum != 1) {
                cursorSE.reset();
                cursorSE.play();
            }
            cursorSEnum = 1;

        } else if (selectNum == 2) {
            if (stageNum == 0) setTranslateY(480);
            else setTranslateY(540);

            if (cursorSEnum != 2) {
                cursorSE.reset();
                cursorSE.play();
            }
            cursorSEnum = 2;

        } else {
            setTranslateY(580);
            if (cursorSEnum != 3) {
                cursorSE.reset();
                cursorSE.play();
            }
            cursorSEnum = 3;
        }
    }
}
