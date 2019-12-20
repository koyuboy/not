package otomasyon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


class Memur extends Calisan implements MemurInterface{
    
    
    Memur(String id/*, String sifre, String ad, String soyad*/) throws SQLException{
        super(id);
    }
    
    static String [][] dersTakvimi = new String [5][5];
    
    
    @Override
    public void yeniKayit(String sifre, String ad, String soyad) {
        try {
            db.yeniEkle("memur", m_id, sifre, ad, soyad);
        } catch (SQLException ex) {
            Logger.getLogger(Ogrenci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    String getAd() throws SQLException{
        return db.veriAl("memur", m_id, 3);
    }
    
    @Override
    String getSoyad() throws SQLException{
        return db.veriAl("memur", m_id, 4);
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
    public void harfNotuBelirle(String ders){
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
    

    @Override
    public void dersProgramiYap() {
        
        
        for(int i=0;i<5;i++){
            for(int j=0 ;j<5;j++){
                try {
                    programaEkle(i,j,"");
                } catch (SQLException ex) {
                    Logger.getLogger(Memur.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        
        int dersSayisi = Dersler.dersList.size();
        dersSayisi--;
        while(dersSayisi>=0){
            
        int rIndex1 = (int)(Math.random()*5);
        int rIndex2 = (int)(Math.random()*5);
        if(dersTakvimi[rIndex1][rIndex2] == null){
            try {
                dersTakvimi[rIndex1][rIndex2] = Dersler.dersList.get(dersSayisi);
                programaEkle(rIndex1, rIndex2, Dersler.dersList.get(dersSayisi));
                dersSayisi--;
                
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Memur.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      }
        
        
        for(int i=0;i<5;i++){
            for(int j=0 ;j<5;j++){
               if(dersTakvimi[i][j] == null){
                   try {
                       programaEkle(i,j,"");
                   } catch (SQLException ex) {
                       Logger.getLogger(Memur.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            }
        }
        
        
        
        
        
        
        
        /*---------------------------
        int dersS = Dersler.dersList.size();;
        for(int i=0;i<5;i++){
            String dersSaat=String.valueOf(i+1);
            for(int j=0;j<5;j++){
                if(dersTakvimi[i][j] == null){
                    String gun = "pazartesi";
                    if(j==1){
                        gun = "sali";
                    }else if(j==2){
                        gun = "carsamba";
                    }else if(j==3){
                        gun = "persembe";
                    }else if(j==4){
                        gun = "cuma";
                    }
                    
                    try {
                        db.dersProgramiGuncelle("dersprogrami", dersSaat, gun, dersTakvimi[i][j]);
                        
                        
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(Memur.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        ----------------------------------------------------
        */
        
        
        /*
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(dersTakvimi[i][j] == null)   dersTakvimi[i][j] = "-----";
            }
        }
        int counter = 0;
        System.out.format("  %-6s%-6s%-6s%-6s%-6s\n","pzt","sli","csm","psm","cma");
        for(int i=0;i<5;i++){
            System.out.print((++counter)+" ");
            for(int j=0;j<5;j++){
                System.out.format("%-5s ",dersTakvimi[i][j]);
            }
            System.out.println();
        }
        */
        
    }

    
    public void programaEkle(int j,int saat,String ders) throws SQLException{
        String dersSaat=String.valueOf(saat+1);
        String gun = "pazartesi";
                    if(j==1){
                        gun = "sali";
                    }else if(j==2){
                        gun = "carsamba";
                    }else if(j==3){
                        gun = "persembe";
                    }else if(j==4){
                        gun = "cuma";
                    }
                    
                    db.dersProgramiGuncelle("dersprogrami", dersSaat, gun, ders);
    }
    
    
    
    
    
    
    @Override
    public void dersEkle(String ders) {        try {
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/okulOtomasyon Data Base");
        Statement st = conn.createStatement();
        st.executeUpdate("create table "+ders+ "(id varchar(10), vize varchar(5), final varchar(5), ortalama varchar(5),harf varchar(2), PRIMARY KEY(id))");
        Dersler newD = new Dersler(ders);
    
    } catch (SQLException ex) {
            Logger.getLogger(Memur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
