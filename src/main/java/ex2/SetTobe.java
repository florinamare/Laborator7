package ex2;


import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public class SetTobe extends InstrumentMuzical {

    private TipTobe tipTobe;
    private int nrTobe;
    private int nrCinele;

    public SetTobe() {
    }

    public SetTobe(String producator, int pret, TipTobe tipTobe, int nrTobe, int nrCinele) {
        super(producator, pret);
        this.tipTobe = tipTobe;
        this.nrTobe = nrTobe;
        this.nrCinele = nrCinele;
    }

    public TipTobe getTipTobe() {
        return tipTobe;
    }

    public int getNrTobe() {
        return nrTobe;
    }

    public int getNrCinele() {
        return nrCinele;
    }

    public void setTipTobe(TipTobe tipTobe) {
        this.tipTobe = tipTobe;
    }

    public void setNrTobe(int nrTobe) {
        this.nrTobe = nrTobe;
    }

    public void setNrCinele(int nrCinele) {
        this.nrCinele = nrCinele;
    }

    @Override
    public String toString() {
        return super.toString()+ " "+this.getClass().toString()+" "+
                "tipTobe=" + tipTobe +
                ", nrTobe=" + nrTobe +
                ", nrCinele=" + nrCinele +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            System.out.println("Sunt la fel, nu este bun!");
            return true;
        }
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SetTobe setTobe = (SetTobe) o;
        boolean isEqual=(super.equals(o)&&nrTobe==setTobe.nrTobe&&nrCinele==setTobe.nrCinele && tipTobe==setTobe.tipTobe);
        if (!isEqual)
            System.out.println("Duplicata!");
        return isEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tipTobe, nrTobe, nrCinele);
    }
}
