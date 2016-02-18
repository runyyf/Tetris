package Tetris;

/**
 * Created by runyyf on 16/2/17.
 */
public class Block implements Runnable{
    int blockType ;
    //1=长条
    GameCanvas gameScr ;
    static boolean blockStatus = false;


    Block(GameCanvas scr){
        this.gameScr = scr;
        blockType = 1;
    }

    public static boolean isBlockStatus() {
        return blockStatus;
    }

    public synchronized void display(int type,int row,int column,int state){

        if (row>gameScr.rowNum || column>gameScr.columnNum){
            return;
        }

        int i = 0 ;
        if (type == 1){
            for (i=0;i<4;i++){
                gameScr.drawUnit(row,column,state);
                gameScr.drawUnit(row,column+1,state);
                gameScr.drawUnit(row,column+2,state);
                gameScr.drawUnit(row,column+3,state);
            }
        }

    }

    public boolean isBlockDown(int type,int row,int column){
        if (row<= 0){
            return false;
        }

        if (type == 1){
            if (gameScr.canvasArray[row-1][column] != 0||gameScr.canvasArray[row-1][column+1] != 0||
                    gameScr.canvasArray[row-1][column+2] != 0||gameScr.canvasArray[row-1][column+3] != 0){
                return false;
            }
        }

        return true;
    }

    public void run(){
        int row = 14;
        blockStatus = true;
        while (true){

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {

            }

            display(1,row  ,4,1);
            display(1,row+1,4,0);

            if (isBlockDown(1,row,4) == false){
                display(1,row,4,2);
                break;
            }

            row--;
        }

        blockStatus = false;
    }


}
