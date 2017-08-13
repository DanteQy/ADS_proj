//Dante Qyshka 124660
//
// Classe che genera numeri casuali,
// migliore del random di sistema
//
public class RNG {
    //
    // get(): restituisce un numero compreso tra 0 e 1 (e aggiorna il seme)
    //
    public double get()
    {
        //
        // Costanti
        //
        final int a = 16087;
        final int m = 2147483647;
        final int q = 127773;
        final int r = 2836;

        //
        // Variabili
        //
        double lo, hi, test;

        hi = Math.ceil(seed / q);
        lo = seed - q * hi;
        test = a * lo - r * hi;
        if (test < 0.0) {
            seed = test + m;
        } else {
            seed = test;
        }
        return seed / m;
    }

    //
    // setSeed(s): imposta il valore del seme a s
    //
    public void setSeed(double s)
    {
        seed = s;
    }

    //
    // costruttore della classe, genera un'istanza di RandomGenerator,
    // fissando il seme iniziale a s.
    //
    public RNG(double s)
    {
        seed = s;
    }

    //
    // variabile che tiene memorizzato il seme
    //
    private double seed;

    //
    // esempio di uso della classe RandomGenerator,
    // stampa 10 numeri casuali compresi tra 1 e 100
    //
	
	public static String generateInput(int number){
		String s = "";
		long n;
		int i = 0;
		char c;
        //
        // crea un istanza della classe RandomGenerator
        //
        RNG r = new RNG(System.currentTimeMillis());
		//number è il numero di parole richiesto
        while (i < number ) {
            n = Math.round(r.get() * 150);
            //evita caratteri che possono creare problemi
            if(n == 34 || n == 92 || n == 47 || n == 39 || n == 96){ n = 61;}
			if(n > 32 && n <= 45){
				n = n*2;
			}
			else if ( n >= 46 && n < 150){
				n =n+1;
			}
			else {
			    n = 32;
                i++;
            }

			c = (char)n;
			s += c;

        }
		
		return s;
		
	} 

}