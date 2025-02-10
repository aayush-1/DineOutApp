package lld.projects.model;

import lld.projects.Enums.City;
import lld.projects.Enums.Cuisine;
import lld.projects.Enums.RestaurantType;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    int restaurantId;
    String restaurantName;
    String address;
    City city;
    private List<Cuisine> cuisines;
    private double costForTwo;
    private RestaurantType type;
    List<Table> tables = new ArrayList<>();
    List<Slot> slots = new ArrayList<>();
    List<Booking> bookings;

    public Restaurant(int restaurantId, String restaurantName, String address, City city,
                      List<Cuisine> cuisines, double costForTwo, RestaurantType type) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.address = address;
        this.city = city;
        this.cuisines = cuisines;
        this.costForTwo = costForTwo;
        this.type = type;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Cuisine> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<Cuisine> cuisines) {
        this.cuisines = cuisines;
    }

    public double getCostForTwo() {
        return costForTwo;
    }

    public void setCostForTwo(double costForTwo) {
        this.costForTwo = costForTwo;
    }

    public RestaurantType getType() {
        return type;
    }

    public void setType(RestaurantType type) {
        this.type = type;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public void addTable(Table table) {
        this.tables.add(table);
    }

    public void removeTable(Table table) {
        this.tables.remove(table);
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    public void addSlot(Slot slot) {
        this.slots.add(slot);
    }

    public void removeSlot(Slot slot) {
        this.slots.remove(slot);
    }

    public void addBookings(Booking booking) {
        this.bookings.add(booking);
    }

    public void removeBookings(Booking booking) {
        this.bookings.remove(booking);
    }
}
