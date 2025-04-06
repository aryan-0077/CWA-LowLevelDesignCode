package CoreClasses;


import java.time.Instant;
import java.util.Date;

public class SeatLock {

    private Seat seat; // The specific seat that is locked.
    private Show show; // The show for which the seat is locked.
    private Integer timeoutInSeconds; // The duration for which the lock is valid, in seconds.
    private Date lockTime; // The timestamp when the lock was acquired.
    private User lockedBy; // Identifier of the user or process that holds the lock.

    public SeatLock(Seat seat, Show show, Integer timeoutInSeconds, Date date, User user) {
        this.seat = seat;
        this.show = show;
        this.timeoutInSeconds = timeoutInSeconds;
        this.lockTime = date;
        this.lockedBy = user;
    }

    public boolean isLockExpired() {
        final Instant lockInstant = lockTime.toInstant().plusSeconds(timeoutInSeconds);
        final Instant currentInstant = new Date().toInstant();
        return lockInstant.isBefore(currentInstant);
    }

    public Seat getSeat() {
        return seat;
    }
    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Show getShow() {
        return show;
    }
    public void setShow(Show show) {
        this.show = show;
    }
    public Integer getTimeoutInSeconds() {
        return timeoutInSeconds;
    }

    public void setTimeoutInSeconds(Integer timeoutInSeconds) {
        this.timeoutInSeconds = timeoutInSeconds;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    public User getLockedBy() {
        return lockedBy;
    }

    public void setLockedBy(User lockedBy) {
        this.lockedBy = lockedBy;
    }
}
