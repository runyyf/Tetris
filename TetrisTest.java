package Tetris;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;

/**
 * Created by runyyf on 16/2/14.
 */
public class TetrisTest implements Runnable{
    JFrame jFrame;
    public static TextField scoreField, levelField;
    public static int level = 1,score = 0;
    GameCanvas leftScr = new GameCanvas();

    public static void main(String[] args){
        TetrisTest test = new TetrisTest();
        test.showFrame();

        new Thread(test).start();
    }

    public void showFrame(){
        jFrame = new JFrame("俄罗斯方块 v1.0  ");

        //init windows
        jFrame.setSize(620,490);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocation(350,100);
        jFrame.setLayout(new GridLayout(1,2));

        //加入到主窗口
        //GameCanvas leftScr = new GameCanvas();
        jFrame.add(leftScr);

        Panel rightScr = new Panel();
        rightScr.setLayout(new GridLayout(2, 1, 0, 30));
        rightScr.setSize(120, 500);
        jFrame.add(rightScr);

        //加入到右侧上方信息,上方信息显示
        MyPanel informationPanel = new MyPanel();
        informationPanel.setLayout(new GridLayout(4,1,0,5));
        informationPanel.setSize(120,300);
        rightScr.add(informationPanel);

        Label score = new Label("score :",Label.LEFT);
        Label level = new Label("level :",Label.LEFT);
        scoreField = new TextField(8);
        levelField = new TextField(8);

        scoreField.setEditable(false);
        levelField.setEditable(false);

        score.setSize(new Dimension(20,60));
        scoreField.setSize(new Dimension(20,60));
        level.setSize(new Dimension(20,60));
        levelField.setSize(new Dimension(20,60));

        informationPanel.add(score);
        informationPanel.add(scoreField);
        informationPanel.add(level);
        informationPanel.add(levelField);

        //右侧下方按钮
        MyPanel buttonPanel = new MyPanel();
        buttonPanel.setLayout(new GridLayout(5,1,0,5));
        rightScr.add(buttonPanel);

        Button playButton = new Button("game start");
        playButton.setSize(70,200);

        Button levelUpButton = new Button("level up ");
        levelUpButton.setSize(70,200);

        Button levelDownButton = new Button("level down");
        levelDownButton.setSize(70,200);

        Button pauseButton  = new Button("game pause");
        pauseButton.setSize(70,200);

        Button quitButton = new Button("game over");
        quitButton.setSize(70,200);

        buttonPanel.add(playButton);
        buttonPanel.add(levelUpButton);
        buttonPanel.add(levelDownButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(quitButton);

        leftScr.requestFocus();
        jFrame.setVisible(true);
    }

    public void run(){
        while (true){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (Block.isBlockStatus() == false){
                new Thread(new Block(leftScr)).start();
            }
        }

    }
}
