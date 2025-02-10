package lld.projects.controller;

import lld.projects.model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BookingService {
    List<Booking> bookings = new ArrayList<>();
    private static final int MAX_DAYS_IN_FUTURE = 30;


    private final Map<String, Object> locks = new ConcurrentHashMap<>();

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

        // Generate a unique lock key for (restaurant, slot, date)
        String lockKey = restaurant.getRestaurantId() + "-" + slot.getSlotId() + "-" + bookingDate;
        locks.putIfAbsent(lockKey, new Object());

        synchronized (locks.get(lockKey)) {
            Table table = findEmptyTable(restaurant, slot, bookingDate);
            if (table == null) {
                System.out.println("Booking failed - No table available");
                return false;
            }

            Booking newBooking = new Booking(bookings.size() + 1, user, restaurant, slot, table, numberOfPeople, bookingDate);
            bookings.add(newBooking);
            slot.addBookedTable(table);
            // print the booking details wirh restaurant anme and slot id and date and user name etc


            System.out.println("Booking successful: " + newBooking.getBookingId() + "  RestaurantName:" + newBooking.getRestaurant().getRestaurantName() + "  SlotID:" + newBooking.getSlot().getSlotId() + "  BookingDate:" + newBooking.getBookingDate() + "  UserName:" + newBooking.getUser().getUserName() );
            return true;
        }
    }

    private Table findEmptyTable(Restaurant restaurant, Slot slot, LocalDate date) {
        // Get all bookings for the given restaurant, slot, and date
        List<Table> bookedTables = bookings.stream()
                .filter(booking -> booking.getRestaurant().equals(restaurant)
                        && booking.getSlot().equals(slot)
                        && booking.getBookingDate().equals(date))
                .map(booking -> booking.getTable())
                .collect(Collectors.toList());

        // Find an unbooked table
        for (Table table : restaurant.getTables()) {
            if (!bookedTables.contains(table)) {
                return table; // Return the first available table
            }
        }

        return null; // No empty tables available
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
        // Ensure the date is within the allowed range
        LocalDate today = LocalDate.now();
        if (date.isBefore(today) || date.isAfter(today.plusDays(MAX_DAYS_IN_FUTURE))) {
            System.out.println("Booking not allowed beyond " + MAX_DAYS_IN_FUTURE + " days.");
            return new ArrayList<>();
        }

        // Fetch all slots for the restaurant
        List<Slot> allSlots = restaurant.getSlots();

        // Get all bookings for the restaurant on the given date
        List<Booking> bookingsForDate = bookings.stream()
                .filter(booking -> booking.getRestaurant().equals(restaurant) && booking.getBookingDate().equals(date))
                .collect(Collectors.toList());

        // Filter out slots where all tables are booked
        List<Slot> availableSlots = new ArrayList<>();
        for (Slot slot : allSlots) {
            long bookedTablesCount = bookingsForDate.stream()
                    .filter(booking -> booking.getSlot().equals(slot))
                    .map(booking -> booking.getTable())
                    .distinct()
                    .count();

            if (bookedTablesCount < restaurant.getTables().size()) {
                availableSlots.add(slot);
            }
        }

        return availableSlots;
    }
}