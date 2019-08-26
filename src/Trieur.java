
public class Trieur extends Thread {

    private  int[] t;
    private int debut, fin;

    /*public Trieur(int[] t){
    }*/

    public Trieur(int[] t,int debut,int fin){
        this.t=t;
        this.debut=debut;
        this.fin=fin;

    }
    public  void  run(){
        if(fin-debut<2){
            if(t[debut]>t[fin]){
                echanger(debut,fin);
            }
        }else {
            int milieu=debut+(fin-debut)/2;
            Trieur trieur1=new Trieur(t,debut,milieu);
            trieur1.start();
            Trieur trieur2=new Trieur(t,milieu+1,fin);
            trieur2.start();

                try {
                    trieur1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    trieur2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            triFusion(debut,fin);
        }
    }

    private  void  echanger(int i, int j){
        int valeur=t[i];
        t[i]=t[j];
        t[j]=valeur;
    }
    private void triFusion(int debut,int fin){
        int[] tFusion=new int[fin-debut+1];
        int milieu=(debut+fin)/2;
        int i1=debut, i2=milieu+1;
        int iFusion=0;
        while(i1<=milieu && i2<=fin){
            if(t[i1]<t[i2]){
                tFusion[iFusion++]=t[i1++];
            }else {
                tFusion[iFusion++]=t[i2++];
            }
        }
        if(i1>milieu){
            for(int i=i2;i<=fin;){
                tFusion[iFusion++]=t[i++];
            }
        }
        else {
            for(int i=i1;i<=milieu;){
                tFusion[iFusion++]=t[i++];
            }
        }
        for(int i=0,j=debut;i<=fin-debut;){
            t[j++]=tFusion[i++];
        }
    }
}
