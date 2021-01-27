package shooting;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Paths;

public class Health extends ImageView {

    static Image HP0 = new Image(Paths.get("InvadersImage/HP/HP0.png").toUri().toString());
    static Image HP1 = new Image(Paths.get("InvadersImage/HP/HP1.png").toUri().toString());
    static Image HP2 = new Image(Paths.get("InvadersImage/HP/HP2.png").toUri().toString());
    static Image HP3 = new Image(Paths.get("InvadersImage/HP/HP3.png").toUri().toString());
    static Image HP4 = new Image(Paths.get("InvadersImage/HP/HP4.png").toUri().toString());

    private int x=650;
    private int y=200;


    public Health(){
        super(HP4);
        setTranslateX(x);
        setTranslateY(y);

    }
    public void changeHPImage(int playerHP){
        if(playerHP==0)      setImage(HP0);
        else if(playerHP==1) setImage(HP1);
        else if(playerHP==2) setImage(HP2);
        else if(playerHP==3) setImage(HP3);
        else if(playerHP==4) setImage(HP4);

    }


}
