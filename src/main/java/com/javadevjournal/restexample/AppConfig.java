package com.javadevjournal.restexample;

import com.javadevjournal.restexample.data.Human;
import com.javadevjournal.restexample.data.Place;
import com.javadevjournal.restexample.data.Portal;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AppConfig {

    @PostConstruct
    public void init(){
        var mostik = new Place("Капитанский мостик");
        var paluba = new Place("Палуба");
        var dver = new Portal(mostik, paluba, true, "Железная дверь");
        var kapitan = new Human("Капитан", mostik, true);
        var ohrannik = new Human("Охранник", mostik, true);
        var odin = new Human("Первый", mostik, true);
        var vtoroi = new Human("Второй", mostik, true);


        System.out.println("*************** Начало истории ****************");
        ohrannik.catchAnotherHuman(odin);
        ohrannik.catchAnotherHuman(vtoroi);
        ohrannik.doSomeShit("Почтительно поклонился спине " + kapitan.getPost() + "(а)");
        ohrannik.doSomeShit("Не обращал внимания на сопротивление " + odin.getPost() + " и " + vtoroi.getPost());
        ohrannik.moveToAnotherPlace(dver);
        dver.close();
        kapitan.doSomeShit("задумчиво промурлыкал что-то");
        kapitan.doSomeShit("полистал свою записную книжку со стихами");
        System.out.println("*************** Конец истории ****************");
    }
}
