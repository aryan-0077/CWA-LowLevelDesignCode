package Interface_Segregation_Principle.Ugly_Code.Concrete_Printers;

import Interface_Segregation_Principle.Ugly_Code.Machine;

public class AllInOnePrinter implements Machine {
    @Override
    public void print() {

    }

    @Override
    public void scan() {

    }

    @Override
    public void fax() {

    }
}
