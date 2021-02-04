package shooting;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.nio.file.Paths;

public class EnemyBullet extends ImageView {
    int x;
    int y;
    static Image bullet1Image = new Image(Paths.get("InvadersImage/Bullet/bullet1.png").toUri().toString());
    static Image bullet2Image = new Image(Paths.get("InvadersImage/Bullet/bullet2.png").toUri().toString());
    static Image bullet3Image = new Image(Paths.get("InvadersImage/Bullet/bullet3.png").toUri().toString());
    static Image bullet5Image = new Image(Paths.get("InvadersImage/Bullet/bullet5.png").toUri().toString());
    static PlayClip pc = new PlayClip("InvadersMusic/EnemyBulletSE.wav");
    String character;
    Timeline timeline;
    public static int bulletCnt=0;
    private int bulletCntRemainder;

    public EnemyBullet(int charaX ,int charaY,String character) {
        this.character = character;
        x = charaX + 7;
        y = charaY;
        setTranslateX(x);
        setTranslateY(y);
        bulletCnt++;
        if(character == "Enemy1")setImage(bullet2Image);
        else if(character == "Enemy2")setImage(bullet3Image);
        else if(character == "Boss1"){
            setImage(bullet2Image);
            x = charaX + 15;
            y = charaY+10;
        }else if(character == "Boss2"){
            System.out.println(bulletCnt%4);
            if(bulletCnt%4==0) {
                setImage(bullet1Image);
            }else if(bulletCnt%5==1){
                setImage(bullet2Image);
            }else if(bulletCnt%5==2){
                setImage(bullet3Image);
            }else if(bulletCnt%5==3) {
                setImage(bullet5Image);
            }
            x = charaX + 15;
            y = charaY+10;
            bulletCntRemainder = bulletCnt%12;
        }
        if(character!="Boss2") {
            pc.reset();
            pc.play();
        }
        timeline = new Timeline(new KeyFrame(Duration.millis(10), event->{run();}));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void run(){
        if (Main.screenMaxX+10>=x&&Main.screenMinX<=x&&Main.screenMaxY+10>=y&&Main.screenMinY<=y) {
            switch (character) {
                case "Enemy1":
                    setImage(bullet2Image);
                    setTranslateX(x+=3);
                    setTranslateY(y+=4);
                    break;
                case "Enemy2":
                    setImage(bullet3Image);
                    setTranslateX(x-=3);
                    setTranslateY(y+=4);
                    break;
                case "Boss1":
                    setImage(bullet2Image);
                    setTranslateY(y+=3);
                    break;
                //最終弾幕(回転弾幕)
                case "Boss2":
                    switch (bulletCntRemainder){
                        case 0:
                            setTranslateY(y-=2);
                            break;
                        case 1:
                            setTranslateX(x+=1);
                            setTranslateY(y-=2);
                            break;
                        case 2:
                            setTranslateX(x+=2);
                            setTranslateY(y-=1);
                            break;
                        case 3:
                            setTranslateX(x+=2);
                            break;
                        case 4:
                            setTranslateX(x+=2);
                            setTranslateY(y+=1);
                            break;
                        case 5:
                            setTranslateX(x+=1);
                            setTranslateY(y+=2);
                            break;
                        case 6:
                            setTranslateY(y+=2);
                            break;
                        case 7:
                            setTranslateX(x-=1);
                            setTranslateY(y+=2);
                            break;
                        case 8:
                            setTranslateX(x-=2);
                            setTranslateY(y+=1);
                            break;
                        case 9:
                            setTranslateX(x-=2);
                            break;
                        case 10:
                            setTranslateX(x-=2);
                            setTranslateY(y-=1);
                            break;
                        case 11:
                            setTranslateX(x-=1);
                            setTranslateY(y-=2);
                            break;
                    }
            }
        }
        else {
            setImage(null);
            timeline.stop();
        }
    }
}
