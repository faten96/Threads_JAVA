public class Main {

    public static void main(String[] args) {

        int[] t={5,8,3,2,7,10,1};
        Trieur trieur=new Trieur(t,0,t.length-1);
        trieur.start();
        try {
            trieur.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // diplay result
        for(int i=0;i<t.length;i++){
            System.out.print(t[i]+" | ");
        }

    }
}
