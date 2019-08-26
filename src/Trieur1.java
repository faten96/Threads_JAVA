public class Trieur1 extends  Thread {

    private int[] t;
    private int debut, fin;
    private Trieur1 parent;
    private int nbnotify = 0;

    public Trieur1(){

    }

    public Trieur1(Trieur1 parent, int[] t, int debut, int fin) {
        this.parent = parent;
        this.t = t;
        this.debut = debut;
        this.fin = fin;
        start();
    }

    public synchronized void notifier() {
        this.nbnotify++;
        this.notifyAll();
        System.out.println("hi it's me " +  nbnotify);
    }

    public void run() {
        if (fin - debut < 2) {
            if (t[debut] > t[fin]) {
                echanger(debut, fin);
            }
        } else {
            int milieu = debut + (fin - debut) / 2;
            Trieur1 trieur1 = new Trieur1(this, t, debut, milieu);
            Trieur1 trieur2 = new Trieur1(this, t, milieu + 1, fin);

            // wait for the 2 threads
            synchronized (this) {
                try {
                    // wait for two notifications
                    while (nbnotify < 2) {
                        wait();
                    }
                } catch (InterruptedException e) {
                }
            }
            triFusion(debut,fin);
        }
        if(parent!=null){
            parent.notifier(); // indique qu'il a fini au parent qui l'attend
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