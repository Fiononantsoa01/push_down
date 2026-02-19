import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceTaxSummary {
    private  Integer id;
    private BigDecimal HT;
    private BigDecimal TVA;
    private BigDecimal TTC;

    public InvoiceTaxSummary(Integer id, BigDecimal HT, BigDecimal TVA, BigDecimal TTC) {
        this.id = id;
        this.HT = HT;
        this.TVA = TVA;
        this.TTC = TTC;
    }

    public InvoiceTaxSummary() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getHT() {
        return HT;
    }

    public void setHT(BigDecimal HT) {
        this.HT = HT;
    }

    public BigDecimal getTVA() {
        return TVA;
    }

    public void setTVA(BigDecimal TVA) {
        this.TVA = TVA;
    }

    public BigDecimal getTTC() {
        return TTC;
    }

    public void setTTC(BigDecimal TTC) {
        this.TTC = TTC;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceTaxSummary that = (InvoiceTaxSummary) o;
        return Objects.equals(id, that.id) && Objects.equals(HT, that.HT) && Objects.equals(TVA, that.TVA) && Objects.equals(TTC, that.TTC);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, HT, TVA, TTC);
    }

    @Override
    public String toString() {
        return "InvoiceTaxSummary{" +
                "id=" + id +
                ", HT=" + HT +
                ", TVA=" + TVA +
                ", TTC=" + TTC +
                '}';
    }
}
