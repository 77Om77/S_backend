package com.snookerbooking.service.impl;

import com.snookerbooking.dto.BookingDTO;
import com.snookerbooking.entity.Booking;
import com.snookerbooking.exception.ResourceNotFoundException;
import com.snookerbooking.repository.BookingRepository;
import com.snookerbooking.service.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public BookingDTO createBooking(BookingDTO dto) {
        Booking booking = new Booking();
        booking.setUserId(dto.getUserId());
        booking.setSnookerTableId(dto.getSnookerTableId());
        booking.setBookingDate(dto.getBookingDate());
        booking.setTimeSlot(dto.getTimeSlot());

        booking.setStatus("PENDING");

        Booking saved = bookingRepository.save(booking);
        dto.setId(saved.getId());
        return dto;
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookingDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
        return toDTO(booking);

    }

    @Override
    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
        bookingRepository.delete(booking);
    }

    private BookingDTO toDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setUserId(booking.getUserId());
        dto.setSnookerTableId(booking.getSnookerTableId());
        dto.setBookingDate(booking.getBookingDate());
        dto.setTimeSlot(booking.getTimeSlot());
        dto.setStatus(booking.getStatus()); // ✅ REQUIRED
        return dto;
    }

    @Override
    public BookingDTO updateStatus(Long id, String status) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        booking.setStatus(status);
        Booking saved = bookingRepository.save(booking);

        return toDTO(saved);
    }

    @Override
    public List<BookingDTO> getBookingsForOwner(Long ownerId) {
        return bookingRepository.findByOwnerId(ownerId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

}
