package otomasyon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


class DBconnect {
    
    Statement st;
    Connection conn;
    
    
    public DBconnect() throws SQLException{
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/okulOtomasyon Data Base");
        st = conn.createStatement();
    }
    
    public void tabloEkle(String d) throws SQLException{
    
        st.executeUpdate("create table "+d+" (id varchar (10) ,vize varchar (5),final varchar (5), ort varchar(5), harf varchar(2) ) ");
        
    }
    
    
    public void yeniEkle(String table,String id,String sifre,String ad, String soyad) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("insert into "+table+" (id,sifre,ad,soyad) values(?,?,?,?)");
        ps.setString(1,id);
        ps.setString(2,sifre);
        ps.setString(3,ad);
        ps.setString(4,soyad);
        ps.executeUpdate();
    }/*
    public void ogretimUyesiEkle(String table,String id,String sifre,String ad, String soyad,String dersler) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("insert into "+table+" (id,sifre,ad,soyad) values(?,?,?,?)");
        ps.setString(1,id);
        ps.setString(2,sifre);
        ps.setString(3,ad);
        ps.setString(4,soyad);
        ps.executeUpdate();
    }
    public void memurEkle(String table,String id,String sifre,String ad, String soyad,String dersler) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("insert into "+table+" (id,sifre,ad,soyad) values(?,?,?,?)");
        ps.setString(1,id);
        ps.setString(2,sifre);
        ps.setString(3,ad);
        ps.setString(4,soyad);
        ps.executeUpdate();
    }*/
    
    
    
    public void derseOgrenciEkle(String table,String id) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("insert into "+table+" (id) values(?)");
        ps.setString(1,id);
        ps.executeUpdate();
    }
    
    
    String veriAl(String table,String key,int search) throws SQLException{
        
        ResultSet rs = st.executeQuery("select * from "+table+"");
        while(rs.next()){
            if(key.equals(rs.getString(1)))
                return rs.getString(search);
        } 
        return "x";
    }
    
    
    
    // bunlar değişmez
    
    public void veriSil(String table,String search) throws SQLException{
        st.executeUpdate("DELETE FROM "+table+" WHERE id = '"+search+"'");
    }
    
    
    public void veriGuncelle(String table,String search,String column,String change) throws SQLException{
        
        st.executeUpdate("update "+table+" set "+column+"='"+change+"' where id = '"+search+"' ");
        
    }
    
     public void dersProgramiGuncelle(String table,String search,String column,String change) throws SQLException{
        
        st.executeUpdate("update "+table+" set "+column+"='"+change+"' where saat = '"+search+"' ");
        
    }
    
    
    
    
}



       