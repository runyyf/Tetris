package Tetris;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by runyyf on 16/2/15.
 */
public class GameCanvas extends Canvas implements KeyListener{
    static int canvasArray[][]= new int [20][20];
    Block blockKeyMode = new Block(this);

    private int rowNum ;
    private int columnNum;
    private int unitSize;

    GameCanvas(){
        rowNum = 15;
        columnNum = 10;
        unitSize = 30;

        //this.addKeyListener(this);
        //初始化数组
        for (int i = 0;i<20;i++){
            for (int j = 0;j<20;j++){
                canvasArray[i][j] = 0 ;
            }
        }

    }

    public int getRowNum(){
        return rowNum;
    }

    public int getColumnNum(){
        return columnNum;
    }

    public int getUnitSize(){
        return unitSize;
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

        canvasArray[row][col] = type;

        g.dispose();
    }

    public void setUnitState(int row,int column,int state){
        //System.out.println(row+"   "+column+"   "+state);
        canvasArray[row][column] = state;
    }


    public void keyPressed(KeyEvent e){
        int keyValue = e.getKeyCode();
        switch (keyValue){
            case KeyEvent.VK_LEFT:
                blockKeyMode.moveBlockLeft();
                break;
            case KeyEvent.VK_RIGHT:
                blockKeyMode.moveBlockRight();
                break;
            case KeyEvent.VK_UP:
                System.out.println("333333333333");
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("444444444444");
                break;
            default:
                break;

        }

    }

    public void keyReleased(KeyEvent event){

    }

    public void keyTyped(KeyEvent event){

    }
}
