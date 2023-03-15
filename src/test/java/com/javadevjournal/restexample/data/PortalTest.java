package com.javadevjournal.restexample.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PortalTest {

	private final Place place1 = new Place("TestPlace1");
	private final Place place2 = new Place("TestPlace2");
	private final Place place3 = new Place("TestPlace3");;

	@InjectMocks
	private Portal portal = new Portal(place1, place2, true, "TestPortal");

	@Test
	void enter() {
		var exception = new Exception();
		try {
			portal.enter(null);
		} catch (Exception e) {
			exception = e;
		}
		assertEquals(RuntimeException.class, exception.getClass());
		assertEquals("Разрыв пространства - времени", exception.getMessage());
		var human = mock(Human.class);

		when(human.getPlace())
				.thenReturn(place1)
				.thenReturn(place2)
				.thenReturn(place3);
		doNothing().when(human).changePlace(place1);
		doNothing().when(human).changePlace(place2);
		var res = portal.enter(human);
		assertTrue(res);
		res = portal.enter(human);
		assertTrue(res);
		res = portal.enter(human);
		assertFalse(res);
		portal.close();
		res = portal.enter(human);
		assertFalse(res);
	}
}