package com.javadevjournal.restexample.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HumanTest {

	@Mock
	private Place place;

	@InjectMocks
	private Human human;

	@Test
	void catchAnotherHuman() {
		var anotherHuman = mock(Human.class);
		var anotherPlace = new Place("TestPlace");
		when(anotherHuman.getPlace()).thenReturn(anotherPlace).thenReturn(place);
		human.catchAnotherHuman(anotherHuman);
		assertEquals(0, human.getCouchHumans().size());
		human.catchAnotherHuman(anotherHuman);
		assertEquals(1, human.getCouchHumans().size());
		assertEquals(anotherHuman, human.getCouchHumans().get(0));
	}

	@Test
	void moveToAnotherPlace() {
		var portal = mock(Portal.class);
		when(portal.enter(human))
				.thenReturn(true)
				.thenReturn(false)
				.thenReturn(true)
				.thenReturn(true);
		var res = human.moveToAnotherPlace(portal);
		assertTrue(res);
		res = human.moveToAnotherPlace(portal);
		assertFalse(res);
		var anotherHuman = mock(Human.class);
		when(anotherHuman.getPlace()).thenReturn(place);
		human.catchAnotherHuman(anotherHuman);
		when(portal.enter(anotherHuman))
				.thenReturn(true)
				.thenReturn(false);
		res = human.moveToAnotherPlace(portal);
		assertTrue(res);

		Exception exception = new Exception();
		try {
			human.moveToAnotherPlace(portal);
		} catch (Exception t) {
			exception = t;
		}
		assertEquals(RuntimeException.class, exception.getClass());
		assertEquals("Разрыв пространства - времени", exception.getMessage());
	}

	@Test
	void changePlace() {
		var place2 = new Place("Test");
		human.changePlace(place2);
		assertEquals(place2, human.getPlace());
		Exception exception = new Exception();
		try {
			human.changePlace(null);
		} catch (Exception t) {
			exception = t;
		}
		assertEquals(RuntimeException.class, exception.getClass());
		assertEquals("Разрыв пространства - времени", exception.getMessage());
	}

	@Test
	void doSomeShit() {
		var someShit = "shit!!";
		human.doSomeShit(someShit);
		// Тут нечего тестировать!!!
	}
}