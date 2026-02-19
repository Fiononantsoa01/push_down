import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        DataRetriever dr = new DataRetriever();
        List<InvoiceTotal> totals = dr.findInvoiceTotals();

        totals.forEach(System.out::println);
    }

}
