package otomasyon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static otomasyon.Dersler.dersList;

public class Otomasyon {
    
    
    static void ogrGiris(DBconnect main,String id, String sifre) throws SQLException{   //OGRENCİ GİRİS MENUSU
       String check = main.veriAl("ogrenci", id, 2);
       if("x".equals(check)){
           System.out.println("Ogrenci bulunamadi!"
                   + "\n-----------------------------------");
       }else if(!check.equals(sifre)){ 
           System.out.println("Sifre Yanlis"
                   + "\n-----------------------------------");
        }else if(check.equals(sifre)){
           Ogrenci ogr = new Ogrenci(id);
           String ad = ogr.getAd();
           String soyad = ogr.getSoyad();
           boolean durum = true;
           int is;
           while(durum){
           System.out.println("OGRENCİ: "+ad+" "+soyad+" HOSGELDIN...");
           System.out.println("1-Not Gor\n"
                   + "2-Ders Sec\n"
                   + "3-Ders Programim\n"
                   + "4-Ana Menu");
           is = intScan.nextInt();
           switch(is){
               case 1:
                   ogr.notGor(ogr.m_id);
                   break;
               case 2:
                   
                   System.out.println("Ders secin ");
                   
                        int sira = 0;
                        boolean loopKontrol = true;
                        while(loopKontrol){
                       int c = 0;
                       for(String dd : dersList){
                          System.out.println((++c)+"-"+dd); 
                       }
                       System.out.println("0-Cikis");
                       int listIndis = intScan.nextInt();
                       if(listIndis == 0) break;
                       ogr.dersSec(dersList.get(listIndis-1));
                       System.out.println((++sira)+" tane ders sectiniz!");
                      // main.veriGuncelle("ogrenci", ogr.m_id, "dersler", dersList.get(listIndis-1)+",");
                        }
                   
                   
                   break;
                   
               case 3:
                   ogr.dersProgram();
                   break;
               case 4:
                   durum = false;
                   break;
           }
           
           
           }
       }
        
    }
    
    static void ogretimUyesiGiris(DBconnect main,String id, String sifre) throws SQLException{
       String check = main.veriAl("ogretimuyesi", id, 2);
       if("x".equals(check)){
           System.out.println("Ogretim uyesi bulunamadi!"
                   + "\n-----------------------------------");
       }else if(!check.equals(sifre)){ 
           System.out.println("Sifre Yanlis"
                   + "\n-----------------------------------");
        }else if(check.equals(sifre)){
           OgretimUyesi ogrUye = new OgretimUyesi(id);
           String ad = ogrUye.getAd();
           String soyad = ogrUye.getSoyad();
           boolean durum = true;
           int is;
           while(durum){
           System.out.println("OGRETIM UYESI: "+ad+" "+soyad+" HOSGELDIN...");
           System.out.println("1-Not Gor\n"
                   + "2-Not Gir\n"
                   + "3-Harf Notu Belirle\n"
                   + "4-Ana Menu");
           is = intScan.nextInt();
           switch(is){
               case 1:
                   System.out.print("Notlarini gormek istediginiz ogrencinin id sini girin:");
                   String ogrId = strScan.nextLine();
                   ogrUye.notGor(ogrId);
                   break;
               case 2:
                   System.out.print("Not girmek istediginiz ogrencinin id sini girin:");
                   String ogrID = strScan.nextLine();
                   String veri = "vize";
                   System.out.println("Sinav secin:\n"
                           + "1-vize\n"
                           + "2-final");
                   int secim = intScan.nextInt();
                   if(secim==1) veri = "vize";
                   if(secim==2) veri = "final";
                   
                   System.out.println("Ders secin: ");
                   String dersler = main.veriAl("ogrenci", ogrID, 5);
                   String[] derslerim = dersler.split(",");
                   int counter =0;
                   for(String a:derslerim){
                       System.out.println((++counter)+"-"+a);
                   }
                   secim = intScan.nextInt();
                   String notGirilicekDers = derslerim[secim-1];
                   
                   System.out.println("Notu girin: ");
                   String yeniNot = strScan.nextLine();
                   
                   ogrUye.notGir(notGirilicekDers, ogrID, veri, yeniNot);
                   
                   //ders,,not
                   break;
               case 3:
                   System.out.println("Harf notunu belirleyeceğiniz dersi secin: ");
                   int sayac =0;
                   for (String a : dersList) {
                     System.out.println((++sayac)+"-"+a);
                   }
                   int dersDizin = intScan.nextInt();
                   String secilenDers = dersList.get(dersDizin-1);
                   ogrUye.harfNotuBelirle(secilenDers);
                   break;


               case 4:
                   durum = false;
                   break;
           }
           
           
           }
       }
        
    }
    
    static void memurGiris(DBconnect main,String id, String sifre) throws SQLException{
       String check = main.veriAl("memur", id, 2);
       if("x".equals(check)){
           System.out.println("Memur bulunamadi!"
                   + "\n-----------------------------------");
       }else if(!check.equals(sifre)){ 
           System.out.println("Sifre Yanlis"
                   + "\n-----------------------------------");
        }else if(check.equals(sifre)){
           Memur m = new Memur(id);
           String ad = m.getAd();
           String soyad = m.getSoyad();
           boolean durum = true;
           int is;
           while(durum){
           System.out.println("MEMUR : "+ad+" "+soyad+" HOSGELDIN...");
           System.out.println("1-Not Gor\n"
                   + "2-Ders Programı Hazirla\n"
                   + "3-Harf Notu Belirleme\n"
                   + "4-Yeni Ders Ekle\n"
                   + "5-Ana Menu");
           is = intScan.nextInt();
           switch(is){
               case 1:
                   System.out.print("Notlarini gormek istediginiz ogrencinin id sini girin:");
                   String ogrId = strScan.nextLine();
                   m.notGor(ogrId);
                   break;
               case 2:
                   m.dersProgramiYap();
                   break;
               case 3:
                   System.out.println("Harf notunu belirleyeceğiniz dersi secin: ");
                   int sayac =0;
                   for (String a : dersList) {
                     System.out.println((++sayac)+"-"+a);
                   }
                   int dersDizin = intScan.nextInt();
                   String secilenDers = dersList.get(dersDizin-1);
                   m.harfNotuBelirle(secilenDers);
                   break;
               case 4:
                   System.out.println("Eklemek istediginiz dersi girin\n"
                           + "Ders Ekleme Kurallarİ !!!\n"
                           + "1-Dersin kodu(kisaltmasi) eklenmeli\n"
                           + "2-Herseferinde tek ders ekleyin");
                   String eklenenDers = strScan.nextLine();
                   m.dersEkle(eklenenDers);
                   break;
               case 5:
                   durum = false;
                   break;
           }
           
           
           }
       }
        
    }
    
    
    
    
    
    
   
        //static List<String>dersList = new ArrayList<String>();
        static Scanner intScan = new Scanner(System.in);
        static Scanner strScan = new Scanner(System.in);
        
    public static void main(String[] args) throws SQLException {
        
       Dersler d1 = new Dersler("mat1");
       d1 = new Dersler("fiz1");
       d1 = new Dersler("nyp");
       d1 = new Dersler("dif");
        
        boolean durum = true;
        int is;
      
        
        while(durum){
        System.out.println("ANA MENU");
        System.out.println("1-Ogretim Uyesi Girisi\n"
                + "2-Ogrenci Girisi\n"
                + "3-Memur Girisi\n"
                + "4-Kayıt Ol\n"
                + "5-Cikis\n"
                + "-----------------------------------");
        is = intScan.nextInt();
        
            DBconnect main = new DBconnect();
            String id,sifre;
            
           switch(is){
               case 1:
                   System.out.println("OGRETIM UYESI GIRIS MENUSU");
                   System.out.print("ID:"); 
                   id = strScan.nextLine();
                   System.out.print("SIFRE:"); 
                   sifre = strScan.nextLine();
                   ogretimUyesiGiris(main,id,sifre);
                   break;
               case 2:
                   System.out.println("OGRENCI GIRIS MENUSU");
                   System.out.print("ID:"); 
                   id = strScan.nextLine();
                   System.out.print("SIFRE:"); 
                   sifre = strScan.nextLine();
                   ogrGiris(main,id,sifre);
                   
                   
                   break;
               case 3:
                   System.out.println("MEMUR GIRIS MENUSU");
                   System.out.print("ID:"); 
                   id = strScan.nextLine();
                   System.out.print("SIFRE:"); 
                   sifre = strScan.nextLine();
                   memurGiris(main,id,sifre);
                   break;
               case 4:
                   System.out.println("KAYIT MENUSU");
                   System.out.println("Ne olarak kayit olmak istiyorsunuz?\n"
                           + "1-Ogretim Uyesi\n"
                           + "2-Ogrenci\n"
                           + "3-Memur");
                   int uyeTipi = intScan.nextInt();
                   System.out.println("Bilgilerinizi Giriniz...");
                   System.out.println("ID:");
                   String kayitId = strScan.nextLine();
                   System.out.println("SIFRE:");
                   String kayitSifre = strScan.nextLine();
                   System.out.println("AD:");
                   String kayitAd = strScan.nextLine();
                   System.out.println("SOYAD:");
                   String kayitSoyad = strScan.nextLine();
                   if(uyeTipi == 1){
                       OgretimUyesi oU = new OgretimUyesi(kayitId);
                       oU.yeniKayit(kayitSifre, kayitAd, kayitSoyad);
                   }else if(uyeTipi == 2){
                       Ogrenci oG = new Ogrenci(kayitId);
                       oG.yeniKayit(kayitSifre, kayitAd, kayitSoyad);
                   }else if(uyeTipi == 3){
                       Memur mE = new Memur(kayitId);
                       mE.yeniKayit(kayitSifre, kayitAd, kayitSoyad);
                   }
                   
                   break;
               case 5: 
                   durum = false;
                   break;
                   
                
                
            }
            
        }
     
        
        
        
        
        
    }
        
}
    
    

