package otomasyon;

import java.sql.SQLException;


abstract class Calisan extends Uyeler{
    abstract void harfNotuBelirle(String ders);
    
    Calisan(String id/*, String sifre , String ad, String soyad*/) throws SQLException{
        super(id);
    }
    
}
