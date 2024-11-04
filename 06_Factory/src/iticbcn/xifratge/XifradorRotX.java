package iticbcn.xifratge;

class XifradorRotX implements Xifrador {

    public static final char[] abcP = "aàábcçdeèéfghiíïjklmnñoóòpqrstuúüvwxyz".toCharArray();
    public static final char[] abcG = "AÀÁBCÇDEÈÉFGHIÍÏJKLMNÑOÓÒPQRSTUÚÜVWXYZ".toCharArray();

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            int desplaçament = Integer.parseInt(clau);
            if (desplaçament < 0 || desplaçament > 40) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
            String xifrat = "";

            for (int i = 0; i < msg.length(); i++) {
                if (Character.isLetter(msg.charAt(i))) {
                    for (int j = 0; j < 38; j++) {
                        if (abcG[j] == msg.charAt(i)) {
                            if (j + desplaçament <= abcG.length-1) {
                                xifrat = xifrat + abcG[j + desplaçament];
                            } else {
                                xifrat = xifrat + abcG[(j + desplaçament) - abcG.length];
                            }
                        } else if (abcP[j] == msg.charAt(i)) {
                            if (j + desplaçament <= abcP.length-1) {
                                xifrat = xifrat + abcP[j + desplaçament];
                            } else {
                                xifrat = xifrat + abcP[(j + desplaçament) - abcP.length];
                            }
                        }
                    }
                } else {
                    xifrat = xifrat + msg.charAt(i);
                }
            }
            return new TextXifrat(xifrat.getBytes());

        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            int desplaçament = Integer.parseInt(clau);
            if (desplaçament < 0 || desplaçament > 40) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
            String xifrada = new String(xifrat.toString());
            String desxifrar = "";

            for (int i = 0; i < xifrada.length(); i++) {
                if (Character.isLetter(xifrada.charAt(i))) {
                    for (int j = 0; j < 38; j++) {
                        if (abcG[j] == xifrada.charAt(i)) {
                            if (j - desplaçament >= 0) {
                                desxifrar = desxifrar + abcG[j - desplaçament];
                            } else {
                                desxifrar = desxifrar + abcG[(j - desplaçament) + abcG.length];
                            }
                        } else if (abcP[j] == xifrada.charAt(i)) {
                            if (j - desplaçament >= 0) {
                                desxifrar = desxifrar + abcP[j - desplaçament];
                            } else {
                                desxifrar = desxifrar + abcP[(j - desplaçament) + abcP.length];
                            }
                        }
                    }
                } else {
                    desxifrar = desxifrar + xifrada.charAt(i);
                }
            }
            return desxifrar;

        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }
}

