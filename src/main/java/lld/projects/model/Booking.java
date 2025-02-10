package lld.projects.model;

import java.time.LocalDate;

public class Booking {
    int bookingId;
    User user;
    Restaurant restaurant;
    Slot slot;
    Table table;
    int numberOfPeople;
    LocalDate bookingDate;

    public Booking(int bookingId, User user, Restaurant restaurant, Slot slot, Table table, int numberOfPeople, LocalDate bookingDate) {
        this.bookingId = bookingId;
        this.user = user;
        this.restaurant = restaurant;
        this.slot = slot;
        this.table = table;
        this.numberOfPeople = numberOfPeople;
        this.bookingDate = bookingDate;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }
}
