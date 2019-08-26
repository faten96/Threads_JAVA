public class Main {

    public static void main(String[] args) {

        int[] t={5,8,3,2,7,10,1};
        
        // Use this code to test the first class
        
        
       /* Trieur trieur=new Trieur(t,0,t.length-1);
        trieur.start();
        try {
            trieur.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // diplay result
        for(int i=0;i<t.length;i++){
            System.out.print(t[i]+" | ");
        }*/

        // Use this code to test the second class
        
        
       Trieur1 parent=new Trieur1();
       Trieur1 trieur1=new Trieur1(parent,t,0,t.length-1);

        try {
            trieur1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // diplay result
        for(int i=0;i<t.length;i++){
            System.out.print(t[i]+" | ");
        }
    }
}

