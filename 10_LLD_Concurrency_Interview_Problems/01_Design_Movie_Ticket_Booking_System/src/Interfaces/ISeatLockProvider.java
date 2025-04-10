package Interfaces;

import CoreClasses.Seat;
import CoreClasses.Show;
import CoreClasses.User;

import java.util.List;

public interface ISeatLockProvider {
    void lockSeats(Show show, List<Seat> seat, User user) throws Exception;
    void unlockSeats(Show show, List<Seat> seat, User user);
    boolean validateLock(Show show, Seat seat, User user);
    List<Seat> getLockedSeats(Show show);

}