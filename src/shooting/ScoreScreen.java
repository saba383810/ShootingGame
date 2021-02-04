package shooting;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class ScoreScreen extends Stage{
    static Image ScoreBackImage = new Image(Paths.get("InvadersImage/UI/ScoreScreen.png").toUri().toString());
    static PlayClip se = new PlayClip("InvadersMusic/cursorSE.wav");
    static PlayClip delete = new PlayClip("InvadersMusic/delete.wav");
    public  Stage startScreen;
    public  Pane root;
    Label highScoreLabel;
    String highScore;

    public ScoreScreen(Stage startScreen){
        this.startScreen = startScreen;

        setTitle("スコア画面");
        setWidth(900);
        setHeight(720);

        root = new Pane();
        try {
            File file = new File("src/shooting/highScore.txt");
            FileReader filereader = new FileReader(file);
            int ch = filereader.read();
            highScore= String.valueOf((char)ch);
            while(ch != -1){
                ch = filereader.read();
                highScore +=String.valueOf((char)ch);
            }
            filereader.close();
        }catch (IOException e){
            System.out.println(e);
        }finally { highScore = highScore.substring(0, highScore.length() - 1); }

        highScoreLabel = new Label(highScore);
        highScoreLabel.setFont(Font.font(80));
        Color c = Color.web("bed7de",1.0);
        highScoreLabel.setTextFill(c);
        highScoreLabel.setTranslateX(350);
        highScoreLabel.setTranslateY(140);

        BackgroundImage bImg = new BackgroundImage(ScoreBackImage, null, null, null, null);
        Background bg1 = new Background(bImg);
        root.setBackground(bg1);
        root.getChildren().add(highScoreLabel);

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(this::keyPress);
        setScene(scene);
        setOnCloseRequest(event -> System.exit(0));

        show();
    }
    public void keyPress(KeyEvent event) {
        if(event.getCode()== KeyCode.SPACE||event.getCode() == KeyCode.Z||event.getCode()==KeyCode.ENTER) {
            se.reset();
            se.play();
            this.close();
            startScreen.show();
        }else if(event.getCode()==KeyCode.Q){
            delete.reset();
            delete.play();
            try {
                File file = new File("src/shooting/highScore.txt");
                FileWriter filewriter = new FileWriter(file);
                filewriter.write(String.valueOf(0));
                filewriter.close();
            } catch (IOException e) {
                System.out.println(e);
            }finally {
                highScoreLabel.setText("0");
            }
        }
    }
}
