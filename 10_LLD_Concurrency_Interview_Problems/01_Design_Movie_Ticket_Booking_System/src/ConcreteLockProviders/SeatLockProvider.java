package ConcreteLockProviders;

import CoreClasses.Seat;
import CoreClasses.SeatLock;
import CoreClasses.Show;
import CoreClasses.User;
import Interfaces.ISeatLockProvider;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SeatLockProvider implements ISeatLockProvider {

    // Timeout in seconds after which a lock expires
    private final Integer lockTimeout;

    // Stores the locks per show and seat
    private final Map<Show, Map<Seat, SeatLock>> locks;

    // Constructor to initialize lock store and timeout
    public SeatLockProvider(Integer lockTimeout) {
        this.locks = new ConcurrentHashMap<>();
        this.lockTimeout = lockTimeout;
    }

    @Override
    public void lockSeats(final Show show, final List<Seat> seats, final User user) throws Exception {
        // Get or initialize the seat lock map for this show
        Map<Seat, SeatLock> seatLocks = locks.computeIfAbsent(show, s -> new ConcurrentHashMap<>());
        synchronized (seatLocks) {
            // Check if any of the requested seats is already locked and the lock is still valid
            for (Seat seat : seats) {
                if (seatLocks.containsKey(seat)) {
                    SeatLock existingLock = seatLocks.get(seat);
                    if (!existingLock.isLockExpired()) {
                        throw new Exception("Seat " + seat.getSeatId() + " is already locked.");
                    }
                }
            }
            // All seats available; lock them together
            Date now = new Date();
            for (Seat seat : seats) {
                SeatLock lock = new SeatLock(seat, show, lockTimeout, now, user);
                seatLocks.put(seat, lock);
            }
        }
    }

    @Override
    public void unlockSeats(final Show show, final List<Seat> seats, final User user) {
        Map<Seat, SeatLock> seatLocks = locks.get(show);
        if (seatLocks == null) return;
        synchronized (seatLocks) {
            for (Seat seat : seats) {
                SeatLock lock = seatLocks.get(seat);
                if (lock != null && lock.getLockedBy().equals(user)) {
                    seatLocks.remove(seat);
                }
            }
        }
    }

    @Override
    public boolean validateLock(final Show show, final Seat seat, final User user) {
        Map<Seat, SeatLock> seatLocks = locks.get(show);
        if (seatLocks == null) return false;
        synchronized (seatLocks) {
            SeatLock lock = seatLocks.get(seat);
            return lock != null && !lock.isLockExpired() && lock.getLockedBy().equals(user);
        }
    }

    @Override
    public List<Seat> getLockedSeats(final Show show) {
        Map<Seat, SeatLock> seatLocks = locks.get(show);
        if (seatLocks == null) {
            return Collections.emptyList();
        }
        synchronized (seatLocks) {
            return seatLocks.entrySet().stream()
                    .filter(entry -> !entry.getValue().isLockExpired())
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        }
    }
}
