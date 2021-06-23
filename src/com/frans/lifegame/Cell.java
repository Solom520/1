package com.frans.lifegame;

public class Cell {
    private byte status;
    private byte nextStatus;
    private int x;  //����
    private int y;   //Y����
    
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * ʹϸ����������һ��״̬
     * @return ״̬�Ƿ�ı��
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
    //ö�٣�����
    //public enum LifeStatus{
   //     SURVIVAL,
  //      DEATH,
   // }
   
}

