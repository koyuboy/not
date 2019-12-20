package otomasyon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import static otomasyon.Dersler.dersList;
import static otomasyon.Memur.dersTakvimi;


class Ogrenci extends Uyeler implements OgrenciInterface{

    //static int ogrenciSayisi = 0;
    
    
    Ogrenci(String id/*, String sifre, String ad, String soyad*/) throws SQLException{
      super(id);  
    }
    
    @Override
    public void yeniKayit(String sifre, String ad, String soyad) {
        try {
            db.yeniEkle("ogrenci", m_id, sifre, ad, soyad);
        } catch (SQLException ex) {
            Logger.getLogger(Ogrenci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    
    @Override
    String getAd() throws SQLException{
        return db.veriAl("ogrenci", m_id, 3);
    }
    
    @Override
    String getSoyad() throws SQLException{
        return db.veriAl("ogrenci", m_id, 4);
    }
    
    
    
    @Override
    public void notGor(String id) {     
        try {
            String dersler = db.veriAl("ogrenci", id, 5);
            String[] derslerim = dersler.split(",");
            for (String a : derslerim) {
                 String vize =  db.veriAl(a,id,2);
                 String finali =  db.veriAl(a,id,3);
                 String ort = db.veriAl(a,id, 4);
                 String harf = db.veriAl(a,id, 5);
                         
                         
                 if(vize==null) vize = "-";
                 if(finali==null) finali = "-";
                 if(ort==null) ort = "-";
                 if(harf==null) harf = "-";
                 
                 System.out.println(a+" dersinin vizesi: "+vize+" finali: "+finali+" ortalamasi: "+ort+" harf notu: "+harf);
            }            
            
        } catch (SQLException ex) {
            Logger.getLogger(Ogrenci.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void dersSec(String ders) {         
        try {
           //System.out.println("AYNI DERSI BIRDAHA SECEMEZSIN!!");
           
           db.derseOgrenciEkle(ders, m_id);
           String kayitliDersler = db.veriAl("ogrenci", m_id, 5);
           if(kayitliDersler == null){
               db.veriGuncelle("ogrenci", m_id, "dersler", ders+",");
           }else{
               db.veriGuncelle("ogrenci", m_id, "dersler", kayitliDersler+ders+",");
           }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Ogrenci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
   public void dersProgram(){
        int counter = 0;
        System.out.format("  %-6s%-6s%-6s%-6s%-6s\n","pzt","sli","csm","psm","cma");
        for(int i=0;i<5;i++){
            System.out.print((++counter)+" ");
            for(int j=0;j<5;j++){
                System.out.format("%-5s ",Memur.dersTakvimi[i][j]);
            }
            System.out.println();
        }
    }
   */
    
    public void dersProgram() throws SQLException{
        
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/okulOtomasyon Data Base");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from dersprogrami");
        while(rs.next()){
            System.out.print(rs.getString(1)+" ");System.out.print(rs.getString(2)+" ");System.out.print(rs.getString(3)+" ");
            System.out.print(rs.getString(4)+" ");System.out.print(rs.getString(5)+" ");System.out.println(rs.getString(6)+" ");
        }
        
    }
   
   
   
   
   
   
   
    
}
