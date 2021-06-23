package com.frans.lifegame;

public class Cell {
    private byte status;
    private byte nextStatus;
    private int x;  //坐标
    private int y;   //Y坐标
    
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 使细胞进化到下一个状态
     * @return 状态是否改变过
     */
    public boolean evolve() {
        if (status == nextStatus)
            return false;
        status = nextStatus;
        return true;
    }
    
    public byte getNextStatus() {
        return nextStatus;
    }

    public void setNextStatus(byte nextStatus) {
        this.nextStatus = nextStatus;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
    
    public byte getStatus() {
        return status;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    //枚举，生死
    //public enum LifeStatus{
   //     SURVIVAL,
  //      DEATH,
   // }
   
}

