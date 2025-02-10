package lld.projects.model;

import lld.projects.Enums.City;
import lld.projects.Enums.Cuisine;
import lld.projects.Enums.RestaurantType;

import java.util.List;

public class RestaurantSearchCriteria {
    private City city;
    private String restaurantName;
    private List<Cuisine> cuisines;
    private Double maxCostForTwo;
    private RestaurantType type;

    private RestaurantSearchCriteria(Builder builder) {
        this.city = builder.city;
        this.restaurantName = builder.restaurantName;
        this.cuisines = builder.cuisines;
        this.maxCostForTwo = builder.maxCostForTwo;
        this.type = builder.type;
    }

    public static class Builder {
        private City city;
        private String restaurantName;
        private List<Cuisine> cuisines;
        private Double maxCostForTwo;
        private RestaurantType type;

        public Builder setCity(City city) {
            this.city = city;
            return this;
        }
        public Builder setRestaurantName(String restaurantName) {
            this.restaurantName = restaurantName;
            return this;
        }

        public Builder setCuisines(List<Cuisine> cuisines) {
            this.cuisines = cuisines;
            return this;
        }

        public Builder setMaxCostForTwo(Double maxCostForTwo) {
            this.maxCostForTwo = maxCostForTwo;
            return this;
        }

        public Builder setType(RestaurantType type) {
            this.type = type;
            return this;
        }

        public RestaurantSearchCriteria build() {
            return new RestaurantSearchCriteria(this);
        }
    }


    // Getters
    public City getCity() { return city; }
    public String getRestaurantName() { return restaurantName; }
    public List<Cuisine> getCuisines() { return cuisines; }
    public Double getMaxCostForTwo() { return maxCostForTwo; }
    public RestaurantType getType() { return type; }
}
