package apap.tugas1.sipas.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class KodePasienGenerator {

    static final int ADD_YEAR = 5;
    static final int ALPHABET_LEN = 26;

    public static String generate(Date tanggalLahir, int jenisKelamin) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Format f = new SimpleDateFormat("ddMMyy");

        return
            String.valueOf(year + ADD_YEAR)
                .concat(f.format(tanggalLahir))
                .concat(String.valueOf(jenisKelamin))
                .concat(String.valueOf(randomChar()))
                .concat(String.valueOf(randomChar()));
    }

    private static char randomChar() {
        Random rand = new Random();
        return (char) (((rand.nextInt() % ALPHABET_LEN) + ALPHABET_LEN) % ALPHABET_LEN + 'A');
    }
}
