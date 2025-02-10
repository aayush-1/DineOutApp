package lld.projects;

import lld.projects.Enums.City;
import lld.projects.Enums.Cuisine;
import lld.projects.Enums.RestaurantType;
import lld.projects.model.Booking;
import lld.projects.model.Restaurant;
import lld.projects.model.Slot;
import lld.projects.model.User;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DineOutApp dineOutApp = new DineOutApp();

        User user = new User(1, "Aayush", "123456");
        User user2 = new User(2, "Abhay", "123456");

        System.out.println("Restaurants According to search criteria: ");
        List<Restaurant> restaurants = dineOutApp.searchRestaurant(City.Hyderabad, List.of(Cuisine.ITALIAN), "600.56", RestaurantType.VEG);
        restaurants.forEach(restaurant -> {
            System.out.println("Restaurant: " + restaurant.getRestaurantName());
            System.out.println("Address: " + restaurant.getAddress());
            System.out.println("City: " + restaurant.getCity());
            System.out.println("Cuisine: " + restaurant.getCuisines());
            System.out.println("Cost for two: " + restaurant.getCostForTwo());
            System.out.println("Type: " + restaurant.getType());
        });

        System.out.println("\n\n\nRestaurants in Hyderabad: ");
        restaurants = dineOutApp.searchRestaurant(City.Hyderabad, null, null, null);
        restaurants.forEach(restaurant -> {
            System.out.println("Restaurant: " + restaurant.getRestaurantName());
            System.out.println("Address: " + restaurant.getAddress());
            System.out.println("City: " + restaurant.getCity());
            System.out.println("Cuisine: " + restaurant.getCuisines());
            System.out.println("Cost for two: " + restaurant.getCostForTwo());
            System.out.println("Type: " + restaurant.getType());
        });

        List<Slot> slots = dineOutApp.getFreeSlots(restaurants.get(0), LocalDate.now());

        //print all slots available for today
        slots.forEach(slot -> {
            System.out.println("Slot: " + slot.getSlotId() + "  slotStartTime: " + slot.getSlotStartTime());
        });

        dineOutApp.bookRestaurant(restaurants.get(1), user, slots.get(6), 4, LocalDate.now());
        dineOutApp.bookRestaurant(restaurants.get(1), user, slots.get(6), 4, LocalDate.now());
        dineOutApp.bookRestaurant(restaurants.get(1), user2, slots.get(6), 4, LocalDate.now());


        dineOutApp.bookRestaurant(restaurants.get(0), user, slots.get(4), 4, LocalDate.now().plusDays(2));

        List<Booking> bookings = dineOutApp.getBookingsForUser(user);

        System.out.println("\n \nBookings done by the user: " + user.getUserName());
        bookings.forEach(booking -> {
            System.out.println("**********************************");
            System.out.println("Booking: " + booking.getBookingId());
            System.out.println("Restaurant: " + booking.getRestaurant().getRestaurantName());
            System.out.println("Slot: " + booking.getSlot().getSlotStartTime());
            System.out.println("Table: " + booking.getTable().getTableId());
            System.out.println("**********************************");
        });

        bookings = dineOutApp.getBookingsForUser(user2);

        System.out.println("\n \nBookings done by the user: " + user2.getUserName());
        bookings.forEach(booking -> {
            System.out.println("**********************************");
            System.out.println("Booking: " + booking.getBookingId());
            System.out.println("Restaurant: " + booking.getRestaurant().getRestaurantName());
            System.out.println("Slot: " + booking.getSlot().getSlotStartTime());
            System.out.println("Table: " + booking.getTable().getTableId());
            System.out.println("**********************************");
        });

    }
}