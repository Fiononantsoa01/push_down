import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {


       public  List<InvoiceTotal> findInvoiceTotals() {
           DBConnection dbConnection = new DBConnection();
           Connection connection = dbConnection.getConnection();

           List<InvoiceTotal> totals = new ArrayList<>();

           String sql = """
        SELECT i.id,
               i.customer_name,
               SUM(il.quantity * il.unit_price) AS total
        FROM invoice i
        JOIN invoice_line il ON i.id = il.invoice_id
        GROUP BY i.id, i.customer_name, i.status
        ORDER BY i.id
        """;

           try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

               while (rs.next()) {

                   InvoiceTotal invoiceTotal = new InvoiceTotal();

                   invoiceTotal.setId(rs.getInt("id"));
                   invoiceTotal.setCustomerName(rs.getString("customer_name"));
                   invoiceTotal.setTotal(rs.getBigDecimal("total"));

                   totals.add(invoiceTotal);
               }


           } catch (Exception e) {
               throw new RuntimeException("Error fetching invoice totals", e);
           }

           return totals;
       }
       public List<InvoiceTotal>findCOnfirmdedAndPaidInvoiceTotals() {
           DBConnection dbConnection = new DBConnection();
           Connection connection = dbConnection.getConnection();
           List<InvoiceTotal> totaleConfirmedAndPaid = new ArrayList<>();
           try{
               PreparedStatement ps= connection.prepareStatement(
                       """
                SELECT i.id,
               i.customer_name,
               i.status,
               SUM(il.quantity * il.unit_price) AS total
        FROM invoice i
        JOIN invoice_line il ON i.id = il.invoice_id
        WHERE i.status IN ('CONFIRMED', 'PAID')
        GROUP BY i.id, i.customer_name, i.status
        ORDER BY i.id
"""
               );
               ResultSet rs = ps.executeQuery();
               while (rs.next()) {
                   InvoiceTotal invoiceTotal = new InvoiceTotal();
                   invoiceTotal.setId(rs.getInt("id"));
                   invoiceTotal.setCustomerName(rs.getString("customer_name"));
                   invoiceTotal.setTotal(rs.getBigDecimal("total"));
                   totaleConfirmedAndPaid.add(invoiceTotal);

               }
               return totaleConfirmedAndPaid;
           }catch (Exception e){
               throw new RuntimeException("Error fetching invoice totals confirmed and paid", e);
           }
       }

}
