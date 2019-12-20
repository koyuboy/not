package otomasyon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

class OgretimUyesi extends Calisan implements OgretimUyesiInterface{
    
    //String m_ders;
    
    OgretimUyesi(String id/*, String name, String surName, String sifre,,String ders*/) throws SQLException{
        super(id);
       // m_ders = ders;
    }
    /*
    void dersAta(String ders){
        m_ders = ders;
    }*/
    
    @Override
    public void yeniKayit(String sifre, String ad, String soyad) {
        try {
            db.yeniEkle("ogretimuyesi", m_id, sifre, ad, soyad);
        } catch (SQLException ex) {
            Logger.getLogger(Ogrenci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    String getAd() throws SQLException{
        return db.veriAl("ogretimuyesi", m_id, 3);
    }
    
    @Override
    String getSoyad() throws SQLException{
        return db.veriAl("ogretimuyesi", m_id, 4);
    }
    
    
    @Override
    public void notGor(String id){
        try {
            Ogrenci o = new Ogrenci(id);
            o.notGor(id);
        } catch (SQLException ex) {
            Logger.getLogger(OgretimUyesi.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    

    @Override
    public void notGir(String ders,String ogrId,String column,String yeniNot)  {
        try {
            db.veriGuncelle(ders,ogrId, column, yeniNot);
            if("final" == column){
                String sv=db.veriAl(ders, ogrId, 2);
                String sf=db.veriAl(ders, ogrId, 3);
                int intv = Integer.parseInt(sv);
                int intf = Integer.parseInt(sf);
                double dbOrt = (intv+intf)/2;
                String strOrt = Double.toString(dbOrt);
                db.veriGuncelle(ders,ogrId, "ortalama", strOrt);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OgretimUyesi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void harfNotuBelirle(String ders) {
        
       
        try {
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/okulOtomasyon Data Base");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from "+ders+"");
        while(rs.next()){
           String id = rs.getString(1);
           String ort = rs.getString(4);
           double dbOrt = Double.valueOf(ort);
           if(dbOrt <=100.0 && dbOrt>=0.0){
               String harf = harfNotuBul(ort);
               db.veriGuncelle(ders, id, "harf", harf);
           }
                
        } 
        
        } catch (SQLException ex) {
            Logger.getLogger(OgretimUyesi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    String harfNotuBul(String notS){
        double not = Double.valueOf(notS);
   if (not >= 90.0)
   return "AA";
 else if (not >= 83.0)
  return "BA";
 else if (not >= 78.0)
  return "BB";
 else if (not >= 73.0)
  return "CB";
 else if (not >= 65.0)
  return "CC";
 else if (not >= 60.0)
  return "DC";
 else if (not >= 50.0)
  return "DD";
 else if(not>=0.0 && not<50.0)
  return "FF";
        return null;
    }
    
    
    
    
}
