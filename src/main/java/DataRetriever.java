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
       public List<InvoiceTotal>findConfirmedAndPaidInvoiceTotals() {
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
       public InvoiceStatusTotals computeStatusTotals(){
           DBConnection dbConnection = new DBConnection();
           Connection connection = dbConnection.getConnection();
           InvoiceStatusTotals totals = new InvoiceStatusTotals();
           try {
               PreparedStatement ps= connection.prepareStatement(
                       """
                                SELECT 
               i.status,
               SUM(il.quantity * il.unit_price) AS total
        FROM invoice i
        JOIN invoice_line il ON i.id = il.invoice_id
        GROUP BY i.status
"""
               );
               ResultSet rs= ps.executeQuery();
               while (rs.next()){
                   if(rs.getString("status").equals("CONFIRMED")){
                       totals.setTotalConfirmed(rs.getBigDecimal("total"));
                   }
                  if(rs.getString("status").equals("PAID")){
                      totals.setTotalPaid(rs.getBigDecimal("total"));
                  }
                  if(rs.getString("status").equals("DRAFT")){
                      totals.setTotalDraft(rs.getBigDecimal("total"));
                  }
               }
               return totals;
           }catch (Exception e){
               throw new RuntimeException("Error fetching invoice  total by status", e);
           }
       }
    public Double computeWeightedTurnover() {
           DBConnection dbConnection = new DBConnection();
           Connection connection = dbConnection.getConnection();
        try (
             PreparedStatement ps = connection.prepareStatement("""
             SELECT SUM(
                 il.quantity * il.unit_price * 
                 CASE i.status
                     WHEN 'PAID'      THEN 1.0
                     WHEN 'CONFIRMED' THEN 0.5
                     WHEN 'DRAFT'     THEN 0.0
                     ELSE 0.0
                 END
             ) AS weighted_ca
             FROM invoice i
             JOIN invoice_line il ON i.id = il.invoice_id
             """)) {

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                BigDecimal result = rs.getBigDecimal("weighted_ca");
                return result != null ? result.doubleValue() : 0.0;
            }
            return 0.0;

        } catch (Exception e) {
            throw new RuntimeException("Error during the compute weight ", e);
        }
    }
        public  List<InvoiceTaxSummary> findInvoiceTaxSummaries(){
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.getConnection();
        List<InvoiceTaxSummary> invoiceTaxSummaries = new ArrayList<>();
        try {
            PreparedStatement ps= connection.prepareStatement(
                    """
            SELECT
                i.id AS invoice_id,
                COALESCE(SUM(il.quantity * il.unit_price), 0) AS total_ht,
                COALESCE(SUM(il.quantity * il.unit_price), 0) * (tc.rate / 100) AS total_tva,
                COALESCE(SUM(il.quantity * il.unit_price), 0) * (1 + tc.rate / 100) AS total_ttc
            FROM invoice i
            LEFT JOIN invoice_line il ON i.id = il.invoice_id
            CROSS JOIN (SELECT rate FROM tax_config WHERE label = 'TVA STANDARD') tc
            GROUP BY i.id, tc.rate
            ORDER BY i.id;
"""
            );
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                InvoiceTaxSummary invoiceTaxSummary=new InvoiceTaxSummary();
                invoiceTaxSummary.setId(rs.getInt("invoice_id"));
                invoiceTaxSummary.setHT(rs.getBigDecimal("total_ht"));
                invoiceTaxSummary.setTVA(rs.getBigDecimal("total_tva"));
                invoiceTaxSummary.setTTC(rs.getBigDecimal("total_ttc"));
                invoiceTaxSummaries.add(invoiceTaxSummary);
            }
            return invoiceTaxSummaries;
        }catch (Exception e){
            throw new RuntimeException("Error fetching invoice tax summaries", e);
        }
        }

}
