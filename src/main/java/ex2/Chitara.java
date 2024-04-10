package ex2;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public class Chitara extends InstrumentMuzical{
    private TipChitara tipChitara;
    private int nrCorzi;

    public Chitara() {
    }

    public Chitara(String producator, int pret, TipChitara tipChitara, int nrCorzi) {
        super(producator, pret);
        this.tipChitara = tipChitara;
        this.nrCorzi = nrCorzi;
    }

    public TipChitara getTipChitara() {
        return tipChitara;
    }

    public void setTipChitara(TipChitara tipChitara) {
        this.tipChitara = tipChitara;
    }

    public int getNrCorzi() {
        return nrCorzi;
    }

    public void setNrCorzi(int nrCorzi) {
        this.nrCorzi = nrCorzi;
    }

    @Override
    public String toString() {
        return super.toString()+this.getClass().toString()+
                "tipChitara=" + tipChitara +
                ", nrCorzi=" + nrCorzi +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Chitara chitara = (Chitara) o;
        boolean isEqual=(super.equals(o)&& nrCorzi==chitara.nrCorzi&&tipChitara==chitara.tipChitara);
        if (!isEqual)
            System.out.println("Duplicata!");
        return isEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tipChitara, nrCorzi);
    }
}
