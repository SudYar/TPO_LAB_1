package com.javadevjournal.restexample.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class Portal {

	private Place firstPlace;
	private Place secondPlace;
	private boolean isOpen;
	private String name;

	public boolean enter(Human human) {
		if (!isOpen()) {
			System.out.println("Для " + human.getPost() + " " + getName() + " закрыто!");
			return false;
		}
		if (human == null) {
			throw new RuntimeException("Разрыв пространства - времени");
		}
		var humanPlace = human.getPlace();
		if (humanPlace == getFirstPlace()) {
			human.changePlace(secondPlace);
			return true;
		}
		if (humanPlace == getSecondPlace()) {
			human.changePlace(firstPlace);
			return true;
		}
		return false;
	}

	public void close() {
		if (isOpen()) {
			isOpen = false;
			System.out.println(getName() + " закрылось");
		} else {
			System.out.println(getName() + " уже закрыто");
		}
	}

	public void open() {
		if (!isOpen()) {
			isOpen = true;
			System.out.println(getName() + " открылось");
		} else {
			System.out.println(getName() + " уже открыто");
		}
	}
}
