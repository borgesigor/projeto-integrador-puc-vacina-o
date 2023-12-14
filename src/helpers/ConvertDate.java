package helpers;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ConvertDate {
    public static Date ConvertDate(String date){
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date utilDate = formato.parse(date);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            return sqlDate;
        } catch (ParseException e) {
            throw new Error("Formato de data inválido");
        }
    }
}
