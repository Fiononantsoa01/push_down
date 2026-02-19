import java.util.List;
import java.util.Objects;

public class Invoice {
    private  Integer id;
    private  String customer_name;
    private  Status invoice_status;
    private List<InvoiceLine> invoice_lines;

    public Invoice(Integer id, String customer_name, Status invoice_status) {
        this.id = id;
        this.customer_name = customer_name;
        this.invoice_status = invoice_status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public Status getInvoice_status() {
        return invoice_status;
    }

    public void setInvoice_status(Status invoice_status) {
        this.invoice_status = invoice_status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id) && Objects.equals(customer_name, invoice.customer_name) && invoice_status == invoice.invoice_status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer_name, invoice_status);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", customer_name='" + customer_name + '\'' +
                ", invoice_status=" + invoice_status +
                '}';
    }
}
