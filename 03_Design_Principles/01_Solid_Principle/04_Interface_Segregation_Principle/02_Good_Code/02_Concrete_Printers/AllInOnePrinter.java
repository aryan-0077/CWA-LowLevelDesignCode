package Interface_Segregation_Principle.Good_Code.Concrete_Printers;

import Interface_Segregation_Principle.Good_Code.Interfaces.FaxMachine;
import Interface_Segregation_Principle.Good_Code.Interfaces.Printer;
import Interface_Segregation_Principle.Good_Code.Interfaces.Scanner;

public class AllInOnePrinter implements Printer, Scanner, FaxMachine {
    @Override
    public void fax() {

    }

    @Override
    public void print() {

    }

    @Override
    public void scan() {

    }
}
