package Single_Responsibility_Principle.Ugly_Code;

public // Class with multiple responsibilities
class BreadBaker {
    public void bakeBread() {
        System.out.println("Baking high-quality bread...");
    }

    public void manageInventory() {
        System.out.println("Managing inventory...");
    }

    public void orderSupplies() {
        System.out.println("Ordering supplies...");
    }

    public void serveCustomer() {
        System.out.println("Serving customers...");
    }

    public void cleanBakery() {
        System.out.println("Cleaning the bakery...");
    }

    public
    static void main(String[] args) {
        BreadBaker baker = new BreadBaker();
        baker.bakeBread();
        baker.manageInventory();
        baker.orderSupplies();
        baker.serveCustomer();
        baker.cleanBakery();
    }
}
