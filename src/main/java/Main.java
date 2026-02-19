import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        DataRetriever dr = new DataRetriever();
        List<InvoiceTotal> totals = dr.findInvoiceTotals();
        totals.forEach(System.out::println);
        List<InvoiceTotal> totalsConfirmedAndPaid= dr.findConfirmedAndPaidInvoiceTotals();
        totalsConfirmedAndPaid.forEach(System.out::println);
        InvoiceStatusTotals totalStatus = dr.computeStatusTotals();
        System.out.println(totalStatus);

    }

}
