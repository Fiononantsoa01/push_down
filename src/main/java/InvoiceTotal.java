import java.math.BigDecimal;

public class InvoiceTotal {

    private Integer id;
    private String customerName;
    private Status status; // enum
    private BigDecimal total;

    public InvoiceTotal(Integer id, String customerName,
                        Status status, BigDecimal total) {
        this.id = id;
        this.customerName = customerName;
        this.status = status;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public InvoiceTotal() {

    }

    @Override
    public String toString() {
        return id + " | " + customerName + " | " + total;
    }
}
