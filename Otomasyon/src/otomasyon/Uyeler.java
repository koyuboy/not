package otomasyon;

import java.sql.SQLException;


abstract class Uyeler {
    protected String m_id;
    protected String m_sifre;
   // protected String m_ad;
    //protected String m_soyad;
    
    abstract void notGor(String id);
    abstract void yeniKayit(String sifre, String ad, String soyad);
    
    Uyeler(String id/*, String sifre, String ad, String soyad*/) throws SQLException{
        this.db = new DBconnect();
        m_id = id;
        //m_sifre = sifre;
        //m_ad = ad;
       // m_soyad = soyad;
    }
    
    DBconnect db;
    
    String getId(){
        return m_id;
    }
    
    abstract String getAd() throws SQLException;
        //return db.veriAl("ogrenci", m_id, 3);
    
    abstract String getSoyad() throws SQLException;
       // return db.veriAl("ogrenci", m_id, 4);
    
}
