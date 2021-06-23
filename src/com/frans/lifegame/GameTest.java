package com.frans.lifegame;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.frans.lifegame.Cell.LifeStatus;

public class GameTest {

	    private static Game g= new Game(10,10,1000);
		@Before
		public void setUp() throws Exception {
			  g.init();
		}
	@Test
		public void testGame() {
			Game g2 = new Game(10,10,1000);
			assertNotSame(g,g2);
		}
	@Test
		public void testInit() { 
		  for(int i = 0;i<10;i++) 
		  {	  
			  for(int j=0 ; j<10 ;j++) 
			  {assertEquals(LifeStatus.DEATH,g.getCellByXY(i, j).getStatus());}
		  }	  
		}

	@Test
	public void testGetNeighbor() {
	  //现在全部细胞全部是死亡的，故任意一个细胞的周围存活细胞数应为0
	    for(int i = 0;i<10;i++) 
		  {	  
			  for(int j=0 ; j<10 ;j++) 
			  {assertEquals(0,g.getNeighbor(i, j));}
		  }	  
		}
	@Test
	public void testrandomInit()
	{
		//当输入的probability为101，则所有的随机数都应该比probability小，故全部的细胞都应当被激活。
		g.randomInit(101);
		for(int i = 0;i<10;i++) 
		  {	  
			  for(int j=0 ; j<10 ;j++) 
			  {assertEquals(LifeStatus.SURVIVAL,g.getCellByXY(i, j).getStatus());}
		  }	  
		}
}
