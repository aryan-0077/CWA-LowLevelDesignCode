package ConcreteLockProviders;

import CoreClasses.Seat;
import CoreClasses.SeatLock;
import CoreClasses.Show;
import CoreClasses.User;
import Interfaces.ISeatLockProvider;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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
       synchronized (locks) {
           for (Seat seat : seats) {
               if (isSeatLocked(show, seat)) {
                   throw new Exception("Can't lock an already locked seat");
               }
           }
       }

        // All seats are available, proceed to lock each one
        for (Seat seat : seats) {
            lockSeat(show, seat, user, lockTimeout);
        }
    }

    @Override
    public void unlockSeats(final Show show, final List<Seat> seats, final User  user) {
        for (Seat seat: seats) {
            if (validateLock(show, seat, user)) {
                unlockSeat(show, seat); // Remove lock if it belongs to the user
            }
        }
    }

    @Override
    public boolean validateLock(final Show show, final Seat seat, final User user) {
        return isSeatLocked(show, seat)
                && locks.get(show).get(seat).getLockedBy().equals(user);
    }

    @Override

    public List<Seat> getLockedSeats(final Show show) {
        if (!locks.containsKey(show)) {
            return Collections.unmodifiableList(new ArrayList<>()); // No locks for this show
        }


        final List<Seat> lockedSeats = new ArrayList<>();
        for (Seat seat : locks.get(show).keySet()) {
            if (isSeatLocked(show, seat)) {
                lockedSeats.add(seat);
            }
        }
        return lockedSeats;
    }

    private void unlockSeat(final Show show, final Seat seat) {
        if (!locks.containsKey(show)) return;
        locks.get(show).remove(seat); // Remove seat from the lock map
    }

    private void lockSeat(final Show show, final Seat seat, final User user, final Integer timeoutInSeconds) {
        if (!locks.containsKey(show)) {
            locks.put(show, new HashMap<>());
        }
        final SeatLock lock = new SeatLock(seat, show, timeoutInSeconds, new Date(), user);
        locks.get(show).put(seat, lock);
    }


    private boolean isSeatLocked(final Show show, final Seat seat) {
        return locks.containsKey(show)
                && locks.get(show).containsKey(seat)
                && !locks.get(show).get(seat).isLockExpired();
    }
}
