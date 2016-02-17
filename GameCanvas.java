package Tetris;

import java.awt.*;

/**
 * Created by runyyf on 16/2/15.
 */
public class GameCanvas extends Canvas implements Runnable{
    static int canvasArray[][];
    int rowNum ;
    int columnNum;
    int unitSize;

    GameCanvas(){
        rowNum = 15;
        columnNum = 12;
        canvasArray = new int [20][20];
        unitSize = 30;

    }


    public void paint(Graphics g) {
        for (int i = 0; i < rowNum; i++)
            for (int j = 0; j < columnNum; j++)
                drawUnit(i, j, canvasArray[i][j]);

    }

    public void drawUnit(int row, int col, int type) {
        canvasArray[row][col] = type;
        Graphics g = getGraphics();
        switch (type) { // 表示画方快的方法
            case 0:
                g.setColor(Color.black);
                break; // 以背景为颜色画
            case 1:
                g.setColor(Color.blue);
                break; // 画正在下落的方块
            case 2:
                g.setColor(Color.magenta);
                break; // 画已经落下的方法
        }
        g.fill3DRect(col * unitSize, getSize().height - (row + 1) * unitSize,
                unitSize, unitSize, true);

        //g.dispose();
    }

    public void run(){
        int row = 13;
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setUnitState(row,4,1);
            setUnitState(row-1,4,1);
            setUnitState(row-2,4,1);
            setUnitState(row-3,4,1);

            this.repaint();
            if (row>5){
                row--;
            }
        }
    }
    public void setUnitState(int row,int column,int state){
        //System.out.println(row+"   "+column+"   "+state);
        canvasArray[row][column] = state;
    }
}
