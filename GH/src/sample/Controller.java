package sample;


import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Event;
import model.GControls;

import java.util.ArrayList;


public class Controller {
    public Label label;
    public Circle lightCircle;
    public Circle waterCircle;
    public Circle thermCircle;
    public GControls gc;

    public void initialize(){
        gc = new GControls();
        //timeServer = new TimeServer();
        // componentColor= new ComponentColor(timeServer, circle);
    }

    public void powerUpButton(ActionEvent actionEvent) {
      //  gc.addEvent(gc.new Bell(9000));
        Event[] eventList = {
                gc.new ThermostatNight(0),
                gc.new LightOn(2000),
                gc.new LightOff(4000),
                gc.new WaterOn(6000),
                gc.new WaterOff(8000),
                gc.new ThermostatDay(14000)
        };
        gc.addEvent(gc.new ThermostatNight(0));
        gc.addEvent(gc.new LightOn(2000));
        gc.addEvent(gc.new LightOff(4000));
        //gc.run();
      {
            while (gc.eventList.size() > 0)
                // Make a copy so you're not modifying the list
                // while you're selecting the elements in it:
                for (Event e : new ArrayList<Event>(gc.eventList))
                    if (e.ready()) {
                        System.out.println(e);
                        label.setText(String.valueOf(e));
                        //lightCircle.setFill(Color.RED);
                        if (e instanceof GControls.LightOn){
                            thermCircle.setFill(Color.GREEN);
                        }
                        if (e instanceof GControls.LightOff){
                        lightCircle.setFill(Color.RED);
                    }

                        e.action();
                        gc.eventList.remove(e);
                    }
        }
    }

    public void onClick(ActionEvent actionEvent) {
        lightCircle.setFill(Color.RED);
    }
}
