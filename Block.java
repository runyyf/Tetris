package Tetris;

/**
 * Created by runyyf on 16/2/17.
 */
public class Block implements Runnable{
    static int row ;  // now the falling block's row
    static int column ;

    static int blockType ;
    /*1=o o o o   2=o    3= o o  4=o o o  5=   o  6=  o    7 =   o
    /               o       o o      o       o o      o o      o o o
    /               o                          o      o
    /               o
    */
    public GameCanvas getGameScr() {
        return gameScr;
    }

    GameCanvas gameScr ;
    static boolean blockStatus = false;


    Block(GameCanvas scr){
        this.gameScr = scr;
        blockType = (int)(Math.random()*5+1);
        //blockType = 1;
    }

    public static boolean isBlockStatus() {
        return blockStatus;
    }

    public synchronized void display(int type,int row,int column,int state){

        if (row>=gameScr.getRowNum() || column>=gameScr.getColumnNum()){
            return;
        }

        int i = 0 ;
        switch (type){
            case 1:
                for (i=0;i<4;i++){
                    gameScr.drawUnit(row,column+i,state);
                }
                break;
            case 2:
                for (i=0;i<4;i++){
                    gameScr.drawUnit(row-i,column,state);
                }
                break;
            case 3:
                gameScr.drawUnit(row,column,state);
                gameScr.drawUnit(row,column+1,state);
                gameScr.drawUnit(row-1,column,state);
                gameScr.drawUnit(row-1,column+1,state);
                break;
            case 4:
                gameScr.drawUnit(row,column,state);
                gameScr.drawUnit(row,column+1,state);
                gameScr.drawUnit(row,column+2,state);
                gameScr.drawUnit(row-1,column+1,state);
                break;
            case 5:
                gameScr.drawUnit(row,column+1,state);
                gameScr.drawUnit(row-1,column,state);
                gameScr.drawUnit(row-1,column+1,state);
                gameScr.drawUnit(row-2,column+1,state);
                break;
            default:
                System.out.println("there is no case = "+type);
                break;
        }


    }

    public boolean isBlockDown(int type,int row,int column){
        switch (type){
            case 1:
                if (row-1<0)
                    return false;
                if (gameScr.canvasArray[row-1][column] != 0||gameScr.canvasArray[row-1][column+1] != 0||
                        gameScr.canvasArray[row-1][column+2] != 0||gameScr.canvasArray[row-1][column+3] != 0){
                    return false;
                }
                break;
            case 2:
                if (row-4<0)
                    return false;
                if (gameScr.canvasArray[row-4][column] != 0){
                    return false;
                }
                break;
            case 3:
                if (row-2<0)
                    return false;
                if (gameScr.canvasArray[row-2][column] != 0||gameScr.canvasArray[row-2][column+1] != 0){
                    return false;
                }
                break;
            case 4:
                if (row-2<0)
                    return false;
                if (gameScr.canvasArray[row-1][column] != 0|| gameScr.canvasArray[row-1][column+2] != 0 ||
                        gameScr.canvasArray[row-2][column+1] != 0){
                    return false;
                }
                break;
            case 5:
                if (row-3<0)
                    return false;
                if (gameScr.canvasArray[row-2][column] != 0||gameScr.canvasArray[row-3][column+1] != 0){
                    return false;
                }
                break;
            default:
                break;
        }

        return true;
    }

    public synchronized void moveBlockLeft(){
        boolean result = false;
        if (column-1 <0 || blockStatus==false )
            return;
        switch (blockType){
            case 1 :
                if (GameCanvas.canvasArray[row][column-1] == 0)
                    result = true;
                break;
            case 2:
                if (GameCanvas.canvasArray[row][column-1]==0 && GameCanvas.canvasArray[row+1][column-1]==0 &&
                        GameCanvas.canvasArray[row+2][column-1]==0&&GameCanvas.canvasArray[row+3][column-1]==0){
                    result = true;
                }
                break;
            case 3:
                if (GameCanvas.canvasArray[row][column-1]==0 && GameCanvas.canvasArray[row+1][column-1]==0){
                    result = true;
                }
                break;
            case 4:
                if (GameCanvas.canvasArray[row][column-1]==0 && GameCanvas.canvasArray[row-1][column-1]==0){
                    result = true;
                }
                break;
            case 5:
                if (GameCanvas.canvasArray[row][column]==0 && GameCanvas.canvasArray[row-1][column-1]==0&&
                        GameCanvas.canvasArray[row-2][column]==0){
                    result = true;
                }
                break;
            default:
                break;
        }

        if (result == true){
            display(blockType,row,column,0);
            display(blockType,row,column-1,1);
            column--;
        }

    }

    public synchronized void moveBlockRight(){
        boolean result = false;
        if (blockStatus == false)
            return;
        switch (blockType){
            case 1 :
                if (column+4>gameScr.getColumnNum()-1)
                    return;
                if (GameCanvas.canvasArray[row][column+4] == 0){
                    result = true;
                }
                break;
            case 2:
                if (column+1>gameScr.getColumnNum()-1)
                    return;
                if (GameCanvas.canvasArray[row][column+1]==0 && GameCanvas.canvasArray[row-1][column+1]==0&&
                        GameCanvas.canvasArray[row-2][column+1]==0&&GameCanvas.canvasArray[row-3][column+1]==0){
                    result = true;
                }
                break;
            case 3:
                if (column+2>gameScr.getColumnNum()-1)
                    return;
                if (GameCanvas.canvasArray[row][column+2]==0 && GameCanvas.canvasArray[row-1][column+2]==0) {
                    result = true;
                }
                break;
            case 4:
                if (column+3>gameScr.getColumnNum()-1)
                    return;
                if (GameCanvas.canvasArray[row][column+3]==0 && GameCanvas.canvasArray[row-1][column+2]==0){
                    result = true;
                }
                break;
            case 5:
                if(column+2>gameScr.getColumnNum()-1)
                    return;
                if (GameCanvas.canvasArray[row][column+2]==0 && GameCanvas.canvasArray[row-1][column+2]==0&&
                        GameCanvas.canvasArray[row-2][column+2]==0){
                    result = true;
                }
                break;
            default:
                break;
        }

        if (result == true){
            display(blockType,row,column,0);
            display(blockType,row,column+1,1);
            column++;
        }

    }


    public void run(){
        row = 15;
        column = 4;
        blockStatus = true;
        while (true) {

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {

            }
            row--;

            display(blockType,row+1,column,0);
            display(blockType,row  ,column,1);

            if (isBlockDown(blockType,row,column) == false){
                display(blockType,row,column,2);
                break;
            }

        }

        blockStatus = false;
    }


}
