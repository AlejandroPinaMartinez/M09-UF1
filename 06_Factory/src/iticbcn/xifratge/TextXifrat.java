package iticbcn.xifratge;

public class TextXifrat {

    private byte[] dades;

    public TextXifrat(byte[] dades) {
        if (dades == null) {
            throw new IllegalArgumentException("L'array de bytes no pot ser null");
        }
        this.dades = dades;
    }

    public byte[] getBytes() {
        return dades;
    }
    
    @Override
    public String toString() {
        return new String(dades);
    }
}

