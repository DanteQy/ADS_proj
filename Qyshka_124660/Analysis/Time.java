/* Dante Qyshka 124660 */
import java.io.FileNotFoundException;
import java.io.IOException;

public class Time {

    private static final double k = 0.7;
    private static final double za = 1.96;
    private static final double Delta = 0.2;
    private static String name;
    private static Graph myGraph = new Graph();
    private static String g_Name = null;

    public static int conta = 0;



    public static void main(String[] args) {
        double tMin = granularita() / k;
            int t = 0;

            double e = 0;
            try {
                for(int i = 0; i< 200; i+=1 ){
                    t++;
                    conta = i;
                    e = misurazione(1, za, tMin, Delta);

                    System.out.println(  e / 1000);
                }

                System.out.println("  za:"+za+" tMin:"+tMin+" Delta:"+Delta);
                System.out.println("Time : " + e / 1000);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

    }

    public static void launch()  {
        GraphUtils.connectGraph(myGraph);
        myGraph.setnode_list(GraphUtils.longestPath(myGraph));
    }

    public static void prepara() {

        g_Name = ReadWrite.readGraph(myGraph, conta );
    }

    public static int calcolaRipTara(double tMin) throws IOException {
        long t0 = 0;
        long t1 = 0;
        int rip = 1;
        while ((t1 - t0) <= tMin) {
            rip = rip * 2;
            t0 = System.currentTimeMillis();
            for (int i = 1; i <= rip; i++) {
                prepara();
            }
            t1 = System.currentTimeMillis();
        }
        int max = rip;
        int min = rip / 2;
        int cicliErrati = 5;
        while ((max - min) >= cicliErrati) {
            rip = (max + min) / 2;
            t0 = System.currentTimeMillis();
            for (int i = 1; i <= rip; i++) {
                prepara();
            }
            t1 = System.currentTimeMillis();
            if ((t1 - t0) <= tMin) {
                min = rip;
            } else {
                max = rip;
            }
        }
        return max;
    }

    private static double misurazione(int n, double za, double tMin, double Delta) throws IOException {
        double t = 0;
        double sum = 0;
        double cn = 0;
        double delta = 0;
        double e = 0;
        double m = 0;
        double s = 0;

        do {
            for (int i = 1; i <= n; i++) {
                m = tempoMedioNetto(tMin);
                t = t + m;
                sum = sum + Math.pow(m, 2);
            }
            cn = cn + n;
            e = t / cn;
            s = Math.sqrt(sum / cn - Math.pow(e, 2));
            delta = 1 / (Math.sqrt(cn) * za * s);
        } while (delta >= (Delta * 500));
        return e;
    }

    public static int calcolaRipLordo(double tMin) throws IOException {
        long t0 = 0;
        long t1 = 0;
        int rip = 1;
        while ((t1 - t0) <= tMin) {
            rip = rip * 2;
            t0 = System.currentTimeMillis();
            for (int i = 1; i <= rip; i++) {
                prepara();

                launch();
            }
            t1 = System.currentTimeMillis();
        }
        int max = rip;
        int min = rip / 2;
        int cicliErrati = 5;
        while ((max - min) >= cicliErrati) {
            rip = (max + min) / 2;
            t0 = System.currentTimeMillis();
            for (int i = 1; i <= rip; i++) {
                prepara();
                launch();
            }
            t1 = System.currentTimeMillis();
            if ((t1 - t0) <= tMin) {
                min = rip;
            } else {
                max = rip;
            }
        }
        return max;
    }

    public static double tempoMedioNetto(double tMin) throws IOException {
        double ripTara = calcolaRipTara(tMin);
        double ripLordo = calcolaRipLordo(tMin);

        long t0 = System.currentTimeMillis();
        for (int i = 1; i <= ripTara; i++) {
            prepara();
        }
        long t1 = System.currentTimeMillis();
        double tara = t1 - t0;
        t0 = System.currentTimeMillis();
        for (int i = 1; i <= ripLordo; i++) {
            prepara();
            launch();
        }
        t1 = System.currentTimeMillis();
        double lordo = t1 - t0;
        double medio = (lordo / ripLordo) - (tara / ripTara);

        return medio;
    }

    private static long granularita() {
        long t0 = System.currentTimeMillis();
        long t1 = System.currentTimeMillis();
        while (t1 == t0) {
            t1 = System.currentTimeMillis();
        }

        return t1 - t0;
    }
}
