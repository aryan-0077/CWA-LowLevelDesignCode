package Association;

public class AssociationDemo {
    public static void main(String[] args) {
        Car car = new Car("Tesla Model 3");
        Person person = new Person("Alice", car);
        person.goForDrive();
    }
}

/*
Output :
Alice is going for a drive.
Driving a Tesla Model 3

*/
