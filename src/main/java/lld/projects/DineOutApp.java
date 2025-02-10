package lld.projects;

import lld.projects.Enums.City;
import lld.projects.Enums.Cuisine;
import lld.projects.Enums.RestaurantType;
import lld.projects.controller.BookingService;
import lld.projects.controller.RestaurantController;
import lld.projects.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DineOutApp {
    RestaurantController restaurantController;
    BookingService bookingService;

    DineOutApp() {
        restaurantController = new RestaurantController();
        bookingService = new BookingService();
        intialize();
    }

    private void intialize() {
        createRestaurants();
    }

    private void createRestaurants() {
        Restaurant hyderabadRestaurant1 = new Restaurant(1, "Restaurant1, Hyderabad", "Address 1", City.Hyderabad,
                List.of(Cuisine.ITALIAN, Cuisine.MEXICAN, Cuisine.JAPANESE), 500, RestaurantType.VEG);

        Restaurant hyderabadRestaurant2 = new Restaurant(2, "Restaurant2", "Address 2", City.Hyderabad,
                List.of(Cuisine.INDIAN, Cuisine.CHINESE), 600, RestaurantType.NON_VEG);

        Restaurant hyderabadRestaurant3 = new Restaurant(3, "Restaurant3", "Address 3", City.Hyderabad,
                List.of(Cuisine.JAPANESE, Cuisine.ITALIAN, Cuisine.INDIAN), 700, RestaurantType.BOTH);

        addSlotsAndTablesForRestaurant(hyderabadRestaurant1);
        addSlotsAndTablesForRestaurant(hyderabadRestaurant2);
        addSlotsAndTablesForRestaurant(hyderabadRestaurant3);

        restaurantController.registerRestaurant(hyderabadRestaurant1, City.Hyderabad);
        restaurantController.registerRestaurant(hyderabadRestaurant2, City.Hyderabad);
        restaurantController.registerRestaurant(hyderabadRestaurant3, City.Hyderabad);

        // Add restaurants in Bangalore
        Restaurant bangaloreRestaurant1 = new Restaurant(4, "Restaurant1", "Address 1", City.Bangalore,
                List.of(Cuisine.ITALIAN, Cuisine.MEXICAN, Cuisine.JAPANESE), 500, RestaurantType.VEG);

        Restaurant bangaloreRestaurant2 = new Restaurant(5, "Restaurant2", "Address 2", City.Bangalore,
                List.of(Cuisine.INDIAN, Cuisine.CHINESE), 600, RestaurantType.NON_VEG);

        Restaurant bangaloreRestaurant3 = new Restaurant(6, "Restaurant3", "Address 3", City.Bangalore,
                List.of(Cuisine.JAPANESE, Cuisine.ITALIAN, Cuisine.INDIAN), 700, RestaurantType.BOTH);

        addSlotsAndTablesForRestaurant(bangaloreRestaurant1);
        addSlotsAndTablesForRestaurant(bangaloreRestaurant2);
        addSlotsAndTablesForRestaurant(bangaloreRestaurant3);

        restaurantController.registerRestaurant(bangaloreRestaurant1, City.Bangalore);
        restaurantController.registerRestaurant(bangaloreRestaurant2, City.Bangalore);
        restaurantController.registerRestaurant(bangaloreRestaurant3, City.Bangalore);
    }

    private void addSlotsAndTablesForRestaurant(Restaurant restaurant) {
        List<Slot> slots = new ArrayList<>();
        LocalDateTime startTime = LocalDateTime.now().withHour(11).withMinute(0).withSecond(0); // Start at 11 AM

        for (int i = 0; i < 10; i++) { // Create 10 slots
            slots.add(new Slot(i + 1, startTime.plusHours(i)));
        }

        List<Table> tables = new ArrayList<>();

        for (int i = 0; i < 10; i++) { // Create 10 slots
            tables.add(new Table(i + 1));
        }

        restaurant.setSlots(slots);
        restaurant.setTables(tables);
    }


    public void registerRestaurant(Restaurant restaurant) {
        restaurantController.registerRestaurant(restaurant, restaurant.getCity());
    }

    public List<Restaurant> searchRestaurant(City city,List<Cuisine> cuisines, String maxCostForTwo, RestaurantType type) {
        RestaurantSearchCriteria.Builder criteria = new RestaurantSearchCriteria.Builder();

        if (city != null) {
            criteria.setCity(city);
        }
        if (cuisines != null && !cuisines.isEmpty()) {
            criteria.setCuisines(cuisines);
        }
        if (maxCostForTwo != null) {
            criteria.setMaxCostForTwo(Double.parseDouble(maxCostForTwo));
        }
        if (type != null) {
            criteria.setType(type);
        }
        return restaurantController.searchRestaurants(criteria.build());
    }

    public Restaurant getRestaurant(String restaurantName) {
        return restaurantController.getRestaurantByName(restaurantName);
    }

    public List<Slot> getFreeSlots(Restaurant restaurant, LocalDate date) {
        return bookingService.availableSlots(restaurant, date);
    }

    public boolean bookRestaurant(Restaurant restaurant, User user, Slot slot,int people, LocalDate bookingDate) {
        return bookingService.bookTable(user, restaurant, slot, people, bookingDate);
    }

    public List<Booking> getBookingsForUser(User user) {
        return bookingService.getBookingsForUser(user);
    }

}
