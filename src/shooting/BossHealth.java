package shooting;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Paths;

public class BossHealth extends ImageView {

    static Image HP0 = new Image(Paths.get("InvadersImage/HP/BossHP0.png").toUri().toString());
    static Image HP1 = new Image(Paths.get("InvadersImage/HP/BossHP1.png").toUri().toString());
    static Image HP2 = new Image(Paths.get("InvadersImage/HP/BossHP2.png").toUri().toString());
    static Image HP3 = new Image(Paths.get("InvadersImage/HP/BossHP3.png").toUri().toString());
    static Image HP4 = new Image(Paths.get("InvadersImage/HP/BossHP4.png").toUri().toString());
    static Image HP5 = new Image(Paths.get("InvadersImage/HP/BossHP5.png").toUri().toString());


    public BossHealth(){
        super(HP5);
        int x = 20;
        int y = 10;
        setTranslateX(x);
        setTranslateY(y);
    }
    public void checkBossHP(int bossHP){
        if(bossHP<0) {
            setImage(HP0);
        }
        else if(bossHP/40==0) setImage(HP1);
        else if(bossHP/40==1) setImage(HP2);
        else if(bossHP/40==2) setImage(HP3);
        else if(bossHP/40==3) setImage(HP4);
        else if(bossHP/40==4) setImage(HP5);
    }
}
