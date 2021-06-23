package com.frans.lifegame;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.frans.lifegame.Cell.LifeStatus;

public class CellTest {
   private Cell c1 = new Cell(10,10);
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCell() {
		Cell c2 = new Cell(10,10);
		assertNotSame(c1,c2);
	}
	@Test 
	public void testevolve() {
	 c1.setStatus(LifeStatus.SURVIVAL);
	 c1.setNextStatus(LifeStatus.SURVIVAL);
	 assertEquals(false,c1.evolve());   //c1的状态未改变，故应该返回的false
	 
	 c1.setStatus(LifeStatus.DEATH);
	 c1.setNextStatus(LifeStatus.SURVIVAL);
	 assertEquals(true,c1.evolve());   //c1的状态未改变，故应该返回的TRUE
}
}
