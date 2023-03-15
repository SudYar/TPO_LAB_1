package com.javadevjournal.restexample.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Human {

	private String post;
	private Place place;
	private boolean gender;
	private final List<Human> couchHumans = new ArrayList<>();

	public boolean moveToAnotherPlace(Portal portal) {
		if (portal == null) {
			throw new RuntimeException("Разрыв пространства - времени");
		}
		System.out.println(this.getPost() + " входит в " + portal.getName());
		if (portal.enter(this)) {
			for (Human human : this.getCouchHumans()) {
				if (!portal.enter(human)) {
					throw new RuntimeException("Разрыв пространства - времени");
				}
			}
			return true;
		}
		System.out.println("Не удалось переместиться");
		return false;
	}

	public void changePlace(Place place) {
		if (place == null) {
			throw new RuntimeException("Разрыв пространства - времени");
		}
		this.place = place;
		System.out.println(this.getPost() + " перемещается в " + place.getNameSpace());
	}

	public void catchAnotherHuman(Human human) {
		if (human.getPlace() == this.getPlace()) {
			System.out.println(this.getPost() + " схватил " + human.getPost());
			couchHumans.add(human);
		} else {
			System.out.println(this.getPost() + " не может схватить " + human.getPost());
		}
	}

	public void doSomeShit(String someShit) {
		System.out.println(this.getPost() + " " + someShit);
	}
}
