package com.pufose.server;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProvaTest {
	private Prova p;
	@Before
	public void before() {
		p=new Prova();
	}
	@Test
	public void test() {
		assertEquals("Prova",p.getProva());
	}

}
