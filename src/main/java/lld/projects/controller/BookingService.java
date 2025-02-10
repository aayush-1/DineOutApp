package lld.projects.controller;

import lld.projects.model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingService {
    List<Booking> bookings = new ArrayList<>();
    private static final int MAX_DAYS_IN_FUTURE = 30;

    public boolean bookTable(User user, Restaurant restaurant, Slot slot, int numberOfPeople, LocalDate bookingDate) {
        if (bookingDate.isAfter(LocalDate.now().plusDays(MAX_DAYS_IN_FUTURE))) {
            System.out.println("Booking not allowed beyond " + MAX_DAYS_IN_FUTURE + " days.");
            return false;
        }

        for (Booking b : bookings) {
            if (b.getUser().equals(user) && b.getRestaurant().equals(restaurant) && b.getSlot().equals(slot) && b.getBookingDate().equals(bookingDate)) {
                System.out.println("Table already booked for this slot.");
                return false;
            }
        }

        // Book table

        Table table = findEmptyTable(restaurant, slot);
        if(table ==  null)
        {
            System.out.println("Booking failed");
            return false;
        }

        Booking newBooking = new Booking(bookings.size() + 1, user, restaurant, slot, table, numberOfPeople, bookingDate);
        bookings.add(newBooking);
        slot.addBookedTable(table);

        System.out.println("Booking successful: " + newBooking.getBookingId());
        return true;
    }

    private Table findEmptyTable(Restaurant restaurant, Slot slot) {
        for (Table table : restaurant.getTables()) {
            if (!slot.getBookedTables().contains(table)) {
                return table;
            }
        }
        return null;
    }

    public List<Booking> getBookingsForUser(User user) {
        List<Booking> userBookings = new ArrayList<>();
        for (Booking b : bookings) {
            if (b.getUser().equals(user)) {
                userBookings.add(b);
            }
        }
        return userBookings;
    }

    public List<Slot> availableSlots(Restaurant restaurant, LocalDate date) {
        List<Slot> availableSlots = new ArrayList<>();
        for (Slot slot : restaurant.getSlots()) {
            if (slot.getBookedTables().isEmpty() && slot.getSlotStartTime().isAfter(date.atStartOfDay())) {
                availableSlots.add(slot);
            }
        }
        return availableSlots;
    }
}