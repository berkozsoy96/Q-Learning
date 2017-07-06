package guideneme;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;

public class GuiDeneme {
    
    public static int[][] matrisR = new int[400][400];
    public static int[][] matrisQ = new int[400][400];
    public static int[] yol = new int[400];
    
    public static int ROlustur(int hedef) throws FileNotFoundException, IOException {
        
        for(int i=0; i<400; i++){
            for(int j=0; j<400; j++){
                matrisR[i][j] = -1;
            }
        }
        
        File dosya = new File("input.txt");
        FileReader fr = new FileReader(dosya);
        String line;
        
        BufferedReader br = new BufferedReader(fr);
        int i, deger = 0, satir=0;
        while((line = br.readLine()) != null){
            for(i = 0; i<line.length(); i++){
                if(line.charAt(i)!= ','){
                    Character rakam = line.charAt(i);
                    deger += Integer.parseInt(rakam.toString());
                    deger*=10;
                }
                if(line.charAt(i)== ',' || i==line.length()-1){
                    deger/=10;
                    if(deger != hedef){
                        matrisR[satir][deger] = 0;
                    }
                    else{
                        matrisR[satir][deger] = 100;
                    }
                    deger=0;
                }
            }
            satir++;
        }
        matrisR[hedef][hedef]=100;
        return satir;
    }
    
    public static void QOlustur(int iterasyon, int hedef, int satir){
    	int i, j;
        float gama = (float) 0.8;
        for(i=0; i<iterasyon; i++){
            int durum = (int) (Math.random() * satir);
            int[] SODlar = new int [4], SSODlar = new int [4];
            int SODsayac, SSODsayac;
            int secim;
            int sonrakiDurum;
            do{
                /**DURUMUN GIDEBILECEGI DURUMLARDAN RASTGELE BIRINI SEC*/
                SODsayac=0;
                SSODsayac=0;
                for(j=0; j<satir; j++){
                    if(matrisR[durum][j]!=-1){
                        SODlar[SODsayac]=j;
                        SODsayac++;
                    }
                }
                secim = (int) (Math.random() * SODsayac);
                sonrakiDurum=SODlar[secim];
                /**SONRAKI DURUMUN GIDEBILECEGI DURUMLARDAN DEGERI BUYUK OLANI BUL*/
                for(j=0; j<satir; j++){
                    if(matrisR[sonrakiDurum][j]!=-1){
                        SSODlar[SSODsayac]=j;
                        SSODsayac++;
                    }
                }
                int enBuyuk=0;
                for(j=0; j<SSODsayac; j++){
                    if(matrisQ[sonrakiDurum][SSODlar[j]] >= enBuyuk){
                        enBuyuk=matrisQ[sonrakiDurum][SSODlar[j]];
                    }
                }
                /**FORMUL*/
                matrisQ[durum][sonrakiDurum]=(int) (matrisR[durum][sonrakiDurum]+(gama*enBuyuk));
                durum=sonrakiDurum;
            }while(durum!=hedef);
        }
    }
    
    public static int yoluBul(int konum, int hedef, int satir){
        int i, mesafe=0, buyuk, indis = 0;
        while(konum!=hedef){
            yol[mesafe]=konum;
            mesafe++;
            buyuk=0;
            for(i=0; i<satir; i++){
                if(matrisQ[konum][i]>buyuk){
                    buyuk=matrisQ[konum][i];
                    indis=i;
                }
            }
            konum=indis;
        }
        yol[mesafe]=konum;
        mesafe++;
        return mesafe;
    }
    
    public static void grafikCiz(int baslangic, int hedef, int mesafe, int satirSayisi){
        
        JFrame.setDefaultLookAndFeelDecorated(false);
        JFrame ekran=new JFrame("labirent");
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int labBoyut = (int) Math.sqrt(satirSayisi);
        ekran.setSize(labBoyut*80, 80*labBoyut);
        pencereOlustur panel= new pencereOlustur(baslangic, hedef, mesafe, satirSayisi, labBoyut);
        ekran.add(panel);
        ekran.setVisible(true);
    }
    
    public static void main(String[] args) throws IOException {
        
        int baslangic, hedef, iterasyon;
        
        Scanner in = new Scanner(System.in);
        System.out.print("Baslangic Giriniz : ");
        baslangic = in.nextInt();
        System.out.print("Bitis Giriniz : ");
        hedef = in.nextInt();
        System.out.print("İterasyon Sayisi Giriniz : ");
        iterasyon = in.nextInt();

        int satirSayisi = ROlustur(hedef);
        QOlustur(iterasyon, hedef, satirSayisi);
        int mesafe = yoluBul(baslangic, hedef, satirSayisi);
        grafikCiz(baslangic, hedef, mesafe, satirSayisi);

    }
}
