package Interface_Segregation_Principle.Ugly_Code;

public interface Machine {
    // Problematic approach that violates ISP
        void print();
        void scan();
        void fax();
}
