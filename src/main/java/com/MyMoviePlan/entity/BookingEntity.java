package com.MyMoviePlan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "bookings")
public class BookingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double amount;

    @Column(name = "total_seats")
    private int totalSeats;

    private boolean status;

    @Column(name = "date_of_booking")
    @Temporal(TemporalType.DATE)
    private Date dateOfBooking;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ElementCollection
    @CollectionTable(name = "booked_seats", joinColumns = @JoinColumn(name = "booking_id"))
    @Column(name = "seat_numbers")
    private List<String> seatNumbers;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(targetEntity = PaymentEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private PaymentEntity payment;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public BookingEntity(double amount, int totalSeats, boolean status, Date dateOfBooking, List<String> seatNumbers,
                         PaymentEntity payment, UserEntity user) {
        this.amount = amount;
        this.totalSeats = totalSeats;
        this.status = status;
        this.dateOfBooking = dateOfBooking;
        this.seatNumbers = seatNumbers;
        this.payment = payment;
        this.user = user;
    }

    public BookingEntity setId(int id) {
        this.id = id;
        return this;
    }

    public BookingEntity setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public BookingEntity setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
        return this;
    }

    public BookingEntity setStatus(boolean status) {
        this.status = status;
        return this;
    }

    public BookingEntity setDateOfBooking(Date dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
        return this;
    }

    public BookingEntity setSeatNumbers(List<String> seatNumbers) {
        this.seatNumbers = seatNumbers;
        return this;
    }

    public BookingEntity setPayment(PaymentEntity payment) {
        this.payment = payment;
        return this;
    }

    public BookingEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
