package shooting;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class EnemyManagement {
    public static Timeline timeline;
    long time;
    long startTime;
    long roundManageTime;
    int stageRound=0;

    public EnemyManagement(){
        time = System.currentTimeMillis();
        startTime = System.currentTimeMillis();
        timeline = new Timeline(new KeyFrame(Duration.millis(50), event->{run();}));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    void run(){
        //ゲームラウンドに応じて敵を出撃じゃ！^^
        if(stageRound==1 && System.currentTimeMillis()-time>500){
            Main.addEnemy(0);
            time=System.currentTimeMillis();
        }else if(stageRound==2 && System.currentTimeMillis()-time>500){
            Main.addEnemy(1);
            time=System.currentTimeMillis();
        }
        else if(stageRound==3 && System.currentTimeMillis()-time>500){
            Main.addEnemy(0);
            Main.addEnemy(1);
            time= System.currentTimeMillis();
        }
        else if (stageRound==4&&System.currentTimeMillis()-time>250){
            Main.addEnemy(0);
            time= System.currentTimeMillis();
        }
        else if (stageRound==5&&System.currentTimeMillis()-time>250){
            Main.addEnemy(1);
            time= System.currentTimeMillis();
        }

        //ゲーム開始時からの時間でゲームラウンドを設定。
        roundManageTime= System.currentTimeMillis()-startTime;
        if(roundManageTime>3600&&roundManageTime<8000)stageRound=1;
        else if(roundManageTime>10000&&roundManageTime<14000)stageRound=2;
        else if(roundManageTime>16000&&roundManageTime<22000) stageRound =3;
        else if(roundManageTime>24000&&roundManageTime<32000) stageRound = 4;
        else if(roundManageTime>34000&&roundManageTime<42000) stageRound = 5;
        else stageRound=0;
    }
    public static void enemyManagementStop(){
        timeline.stop();
    }
}
