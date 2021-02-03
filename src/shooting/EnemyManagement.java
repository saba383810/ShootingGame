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
        else if (stageRound==4&&System.currentTimeMillis()-time>500){
            Main.addEnemy(2);
            time= System.currentTimeMillis();
        }
        else if (stageRound==5&&System.currentTimeMillis()-time>500){
            Main.addEnemy(3);
            time= System.currentTimeMillis();
        }
        else if (stageRound==6&&System.currentTimeMillis()-time>500){
            Main.addBoss();
            stageRound=7;
        }else

        //ゲーム開始時からの時間でゲームラウンドを設定。
        roundManageTime= System.currentTimeMillis()-startTime;
        if(roundManageTime>4600&&roundManageTime<8000)stageRound=1;
        else if(roundManageTime>10000&&roundManageTime<13000)stageRound=2;
        else if(roundManageTime>15000&&roundManageTime<22000) stageRound =3;
        else if(roundManageTime>26000&&roundManageTime<30000) stageRound = 4;
        else if(roundManageTime>31000&&roundManageTime<38000) stageRound = 5;
        else if(stageRound==5&&roundManageTime>38000&&roundManageTime<40000) stageRound = 6;
    }
    public static void enemyManagementStop(){
        timeline.stop();
    }
}
