package MainRentalSystem;

import UtiltiyClasses.RentalStore;
import UtiltiyClasses.Reservation;
import UtiltiyClasses.ReservationManager;
import UtiltiyClasses.User;
import VehicleFactoryPattern.Vehicle;
import VehicleFactoryPattern.VehicleFactory;
import paymentStrategyPattern.PaymentProcessor;
import paymentStrategyPattern.PaymentStrategy;

import java.util.*;

public class RentalSystem {
    private static RentalSystem instance;
    private List<RentalStore> stores;
    private VehicleFactory vehicleFactory;
    private ReservationManager reservationManager;
    private PaymentProcessor paymentProcessor;
    private Map<Integer, User> users;
    private int nextUserId;


    private RentalSystem() {
        this.stores = new ArrayList<>();
        this.vehicleFactory = new VehicleFactory();
        this.reservationManager = new ReservationManager();
        this.paymentProcessor = new PaymentProcessor();
        this.users = new HashMap<>();
        this.nextUserId = 1;
    }


    public static synchronized RentalSystem getInstance() {
        if (instance == null) {
            instance = new RentalSystem();
        }
        return instance;
    }


    public void addStore(RentalStore store) {
        stores.add(store);
    }


    public RentalStore getStore(int storeId) {
        for (RentalStore store : stores) {
            if (store.getId() == storeId) {
                return store;
            }
        }
        return null;
    }


    public List<RentalStore> getStores() {
        return stores;
    }


    public User getUser(int userId) {
        return users.get(userId);
    }


    public Reservation createReservation(int userId, String vehicleRegistration,
                                         int pickupStoreId, int returnStoreId, Date startDate, Date endDate) {
        User user = users.get(userId);
        RentalStore pickupStore = getStore(pickupStoreId);
        RentalStore returnStore = getStore(returnStoreId);
        Vehicle vehicle = (pickupStore != null) ? pickupStore.getVehicle(vehicleRegistration): null;

        if (user != null && pickupStore != null && returnStore != null && vehicle != null) {
            return reservationManager.createReservation(
                    user, vehicle, pickupStore, returnStore, startDate, endDate);
        }
        return null;
    }

    public boolean processPayment(
            int reservationId, PaymentStrategy paymentStrategy) {
        Reservation reservation =
                reservationManager.getReservation(reservationId);
        if (reservation != null) {
            boolean result = paymentProcessor.processPayment(
                    reservation.getTotalAmount(), paymentStrategy);
            if (result) {
                reservationManager.confirmReservation(reservationId);
                return true;
            }
        }
        return false;
    }

    public void startRental(int reservationId) {
        reservationManager.startRental(reservationId);
    }

    public void completeRental(int reservationId) {
        reservationManager.completeRental(reservationId);
    }

    public void cancelReservation(int reservationId) {
        reservationManager.cancelReservation(reservationId);
    }

    public void registerUser(User user){
        int userID = user.getId();
        if(users.containsKey(userID)){
            System.out.println("User with id : " + userID + "Already exists in the system");
            return;
        }
        users.put(userID , user);
    }
}
