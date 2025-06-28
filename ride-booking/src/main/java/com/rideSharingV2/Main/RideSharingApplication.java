package com.rideSharingV2.Main;

import com.rideSharingV2.model.Gender;
import com.rideSharingV2.model.Ride;
import com.rideSharingV2.model.SelectionStrategyType;
import com.rideSharingV2.service.Application;

public class RideSharingApplication {
    public static void main(String[] args) {
        Application app = new Application();

        app.add_user("Sayak", Gender.MALE,36);
        app.add_vehicle("Sayak","Dezire","BL-A451");

        app.add_user("Shashank",Gender.MALE,29);
        app.add_vehicle("Shashank","Bolero","T5-rr31");

        app.add_user("Nandini",Gender.FEMALE,33);

        app.add_user("Shipra",Gender.FEMALE,27);
        app.add_vehicle("Shipra","Polo","KA-77634");
        app.add_vehicle("Shipra","Activa","KA-12-3311");

        app.add_user("Rahul",Gender.MALE,25);
        app.add_vehicle("Rahul","XUV","KA-889-34");

        app.add_user("Gaurav",Gender.MALE,29);

        app.offer_ride("Sayak","Hyderabad",1,"Dezire","BL-A451","Bangalore");
        app.offer_ride("Shipra","Bangalore",2,"Polo","KA-77634","Hyderabad");
        app.offer_ride("Shipra","Bangalore",2,"Activa","KA-12-3311","Mysore");
        app.offer_ride("Rahul","Bangalore",5,"XUV","KA-889-34","Mumbai");

        Ride ride1 = app.select_ride("Nandini","Bangalore","Mysore",1, SelectionStrategyType.MOST_VACANT,"None");
        Ride ride2 = app.select_ride("Shipra","Hyderabad","Bangalore",1, SelectionStrategyType.PREFERRED,"Dezire");
        Ride ride3 = app.select_ride("Gaurav","Bangalore","Hyderabad",1, SelectionStrategyType.PREFERRED,"None");


        app.end_ride("Sayak","Hyderabad",1,"Dezire","BL-A451", "Bangalore");
        app.end_ride("Shipra","Bangalore",2,"Polo","KA-77634", "Hyderabad");
        app.end_ride("Shipra","Bangalore",2,"Activa","KA-12-3311", "Mysore");

        app.print_ride_status();
    }
}
