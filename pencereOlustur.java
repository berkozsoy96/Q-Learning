
package guideneme;

import static guideneme.GuiDeneme.matrisR;
import static guideneme.GuiDeneme.yol;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class pencereOlustur extends JPanel{
    
    int baslangic, hedef, mesafe, satirSayisi, labBoyut;
    
    public pencereOlustur(int baslangic, int hedef, int mesafe, int satirSayisi, int labBoyut){
        this.baslangic=baslangic;
        this.hedef=hedef;
        this.mesafe=mesafe;
        this.satirSayisi=satirSayisi;
        this.labBoyut=labBoyut;
    }
    
    @Override
    public void paintComponent(Graphics g){
        
    int i, j;
    int ibolum, ikalan, jbolum, jkalan;
    
    for(i=0; i<=labBoyut; i++){
        g.drawLine(10, 10+i*80, 10+labBoyut*80, 10+i*80);
        g.drawLine(10+i*80, 10, 10+i*80, 10+labBoyut*80);
    }
    
    g.setColor(getBackground());
    int xorta, yorta;
    for(i=0; i<satirSayisi; i++){
        for(j=0; j<satirSayisi; j++){
            if(matrisR[i][j]!=-1){
                ibolum=i/labBoyut;
                ikalan=i%labBoyut;
                jbolum=j/labBoyut;
                jkalan=j%labBoyut;
                xorta=((50+(ikalan*80))+(50+(jkalan*80)))/2;
                yorta=((50+(ibolum*80))+(50+(jbolum*80)))/2;
                if(Math.abs(i-j)==1){
                    g.drawLine(xorta, yorta-40, xorta, yorta+40);
                }
                else{
                    g.drawLine(xorta-40, yorta, xorta+40, yorta);
                }
            }
        }
    }
    
    ibolum=baslangic/labBoyut;
    ikalan=baslangic%labBoyut;
    jbolum=hedef/labBoyut;
    jkalan=hedef%labBoyut;

    g.drawLine(10+ikalan*80, 10, 10+(ikalan+1)*80, 10);
    g.drawLine(10+jkalan*80, 10+(jbolum+1)*80, 10+(jkalan+1)*80, 10+(jbolum+1)*80);

    g.setColor(Color.MAGENTA);
    g.drawString("BASLA", 30+ikalan*80, 20);
    g.drawString("BITIS", 30+jkalan*80, 10+(jbolum+1)*80);
    
    
    for(i=0; i<satirSayisi; i++){
        for(j=0; j<satirSayisi; j++){
            if(matrisR[i][j]!=-1){
                ibolum=i/labBoyut;
                ikalan=i%labBoyut;
                jbolum=j/labBoyut;
                jkalan=j%labBoyut;
                for(int k=0; k<mesafe; k++){
                    if(yol[k]==i && yol[k+1]==j){
                        g.drawLine(ikalan*80+50, ibolum*80+50, jkalan*80+50, jbolum*80+50);
                    }
                }
            }
        }
    }
    
    }
}
