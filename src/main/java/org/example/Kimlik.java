package org.example;

public class Kimlik {
    private String id;
    private String ad;
    private String soyad;
    private String tcNo;
    private String dogumTarihi;
    private String cinsiyet;
    private String adres;

    public Kimlik() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getAd() { return ad; }
    public void setAd(String ad) { this.ad = ad; }

    public String getSoyad() { return soyad; }
    public void setSoyad(String soyad) { this.soyad = soyad; }

    public String getTcNo() { return tcNo; }
    public void setTcNo(String tcNo) { this.tcNo = tcNo; }

    public String getDogumTarihi() { return dogumTarihi; }
    public void setDogumTarihi(String d) { this.dogumTarihi = d; }

    public String getCinsiyet() { return cinsiyet; }
    public void setCinsiyet(String cinsiyet) { this.cinsiyet = cinsiyet; }

    public String getAdres() { return adres; }
    public void setAdres(String adres) { this.adres = adres; }
}