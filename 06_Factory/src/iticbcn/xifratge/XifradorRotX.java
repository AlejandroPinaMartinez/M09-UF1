package iticbcn.xifratge;

class XifradorRotX implements Xifrador {

    public static final char[] abcP = "aàábcçdeèéfghiíïjklmnñoóòpqrstuúüvwxyz".toCharArray();
    public static final char[] abcG = "AÀÁBCÇDEÈÉFGHIÍÏJKLMNÑOÓÒPQRSTUÚÜVWXYZ".toCharArray();

    public String xifraRotX(String text, int desplaçament) {
        String xifrat = "";

        for (int i = 0; i < text.length(); i++) {
            if (Character.isLetter(text.charAt(i))) {
                for (int j = 0; j < 38; j++) {
                    if (abcG[j] == text.charAt(i)) {
                        if (j + desplaçament <= abcG.length-1) {
                            xifrat = xifrat + abcG[j + desplaçament];
                        } else {
                            xifrat = xifrat + abcG[(j + desplaçament) - abcG.length];
                        }
                    } else if (abcP[j] == text.charAt(i)) {
                        if (j + desplaçament <= abcP.length-1) {
                            xifrat = xifrat + abcP[j + desplaçament];
                        } else {
                            xifrat = xifrat + abcP[(j + desplaçament) - abcP.length];
                        }
                    }
                }
            } else {
                xifrat = xifrat + text.charAt(i);
            }
        }
        return xifrat;
    }

    public String desxifraRotX(String text, int desplaçament) {
        String desxifrar = "";

        for (int i = 0; i < text.length(); i++) {
            if (Character.isLetter(text.charAt(i))) {
                for (int j = 0; j < 38; j++) {
                    if (abcG[j] == text.charAt(i)) {
                        if (j - desplaçament >= 0) {
                            desxifrar = desxifrar + abcG[j - desplaçament];
                        } else {
                            desxifrar = desxifrar + abcG[(j - desplaçament) + abcG.length];
                        }
                    } else if (abcP[j] == text.charAt(i)) {
                        if (j - desplaçament >= 0) {
                            desxifrar = desxifrar + abcP[j - desplaçament];
                        } else {
                            desxifrar = desxifrar + abcP[(j - desplaçament) + abcP.length];
                        }
                    }
                }
            } else {
                desxifrar = desxifrar + text.charAt(i);
            }
        }
        return desxifrar;
    }

    public String forcaBrutaRotX(String xifrada) {
        String forcaBruta="";
        
        for (int k = 0; k < 38; k++) {
            for (int i = 0; i < xifrada.length(); i++) {
                if (Character.isLetter(xifrada.charAt(i))) {
                    for (int j = 0; j < 38; j++) {
                        if (abcG[j] == xifrada.charAt(i)) {
                            if (j - k >= 0) {
                                forcaBruta = forcaBruta + abcG[j - k];
                            } else {
                                forcaBruta = forcaBruta + abcG[(j - k) + abcG.length];
                            }
                        } else if (abcP[j] == xifrada.charAt(i)) {
                            if (j - k >= 0) {
                                forcaBruta = forcaBruta + abcP[j - k];
                            } else {
                                forcaBruta = forcaBruta + abcP[(j - k) + abcP.length];
                            }
                        }
                    }
                } else {
                    forcaBruta = forcaBruta + xifrada.charAt(i);
                }
            }
            if (k==1){
            forcaBruta = forcaBruta + " <-- Text xifrat, desxifrat amb " + k + " desplaçament " + "\n";
            } else {
            forcaBruta = forcaBruta + " <-- Text xifrat, desxifrat amb " + k + " desplaçaments " + "\n";
            }
        }
        return forcaBruta;
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'xifra'");
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'desxifra'");
    }
}

