package Interface_Segregation_Principle.Ugly_Code.Concrete_Printers;

import Interface_Segregation_Principle.Ugly_Code.Machine;

public class BasicPrinter implements Machine {
    @Override
    public void print() {

    }

    @Override
    public void scan() {
        throw new UnsupportedOperationException("Cannot scan");
    }

    @Override
    public void fax() {
        throw new UnsupportedOperationException("Cannot fax");
    }
}
