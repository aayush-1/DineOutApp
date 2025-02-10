package lld.projects.controller;

import lld.projects.Enums.City;
import lld.projects.Enums.RestaurantType;
import lld.projects.model.Restaurant;
import lld.projects.model.RestaurantSearchCriteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RestaurantController {
    Map<City, List<Restaurant>> cityVsRestaurant;
    List<Restaurant> restaurants;

    public RestaurantController() {
        cityVsRestaurant = new HashMap<>();
        restaurants = new ArrayList<>();
    }

    public void registerRestaurant(Restaurant restaurant, City city) {
        restaurants.add(restaurant);
        List<Restaurant> restaurantsCity = cityVsRestaurant.getOrDefault(city, new ArrayList<>());
        restaurantsCity.add(restaurant);
        cityVsRestaurant.put(city, restaurantsCity);
    }

    public List<Restaurant> searchRestaurants(RestaurantSearchCriteria criteria) {
        return restaurants.stream()
                .filter(r -> criteria.getCity() == null || r.getCity().equals(criteria.getCity()))
                .filter(r -> criteria.getRestaurantName() == null || r.getRestaurantName().toLowerCase().contains(criteria.getRestaurantName().toLowerCase()))
                .filter(r -> criteria.getCuisines() == null || r.getCuisines().stream().anyMatch(criteria.getCuisines()::contains))
                .filter(r -> criteria.getMaxCostForTwo() == null || r.getCostForTwo() <= criteria.getMaxCostForTwo())
                .filter(r -> criteria.getType() == null || r.getType().equals(criteria.getType()))
                .collect(Collectors.toList());
    }

    public Restaurant getRestaurantByName(String restaurantName) {
        return restaurants.stream()
               .filter(r -> r.getRestaurantName().equals(restaurantName))
               .findFirst()
               .orElse(null);
    }

}
