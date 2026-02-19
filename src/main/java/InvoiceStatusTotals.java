import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceStatusTotals {
    private BigDecimal totalConfirmed;
    private BigDecimal totalPaid;
    private BigDecimal totalDraft;

    public InvoiceStatusTotals(BigDecimal totalConfirmed, BigDecimal totalPaid, BigDecimal totalDraft) {
        this.totalConfirmed = totalConfirmed;
        this.totalPaid = totalPaid;
        this.totalDraft = totalDraft;
    }

    public InvoiceStatusTotals() {

    }

    public BigDecimal getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(BigDecimal totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

    public BigDecimal getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(BigDecimal totalPaid) {
        this.totalPaid = totalPaid;
    }

    public BigDecimal getTotalDraft() {
        return totalDraft;
    }

    public void setTotalDraft(BigDecimal totalDraft) {
        this.totalDraft = totalDraft;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceStatusTotals that = (InvoiceStatusTotals) o;
        return Objects.equals(totalConfirmed, that.totalConfirmed) && Objects.equals(totalPaid, that.totalPaid) && Objects.equals(totalDraft, that.totalDraft);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalConfirmed, totalPaid, totalDraft);
    }

    @Override
    public String
    toString() {
        return "InvoiceStatusTotals{" +
                "totalConfirmed=" + totalConfirmed +
                ", totalPaid=" + totalPaid +
                ", totalDraft=" + totalDraft +
                '}';
    }
}
