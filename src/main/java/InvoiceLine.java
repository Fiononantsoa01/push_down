import java.util.Objects;

public class InvoiceLine {
    private Integer id;
    private  InvoiceTotal invoice;
    private String label;
    private Integer quantity;
    private Double unit_price;

    public InvoiceLine(Integer id, InvoiceTotal invoice, String label, Integer quantity, Double unit_price) {
        this.id = id;
        this.invoice = invoice;
        this.label = label;
        this.quantity = quantity;
        this.unit_price = unit_price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public InvoiceTotal getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceTotal invoice) {
        this.invoice = invoice;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Double unit_price) {
        this.unit_price = unit_price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceLine that = (InvoiceLine) o;
        return Objects.equals(id, that.id) && Objects.equals(invoice, that.invoice) && Objects.equals(label, that.label) && Objects.equals(quantity, that.quantity) && Objects.equals(unit_price, that.unit_price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, invoice, label, quantity, unit_price);
    }
}
