package Ejercicio15;

public class Protocolo {
	
		public static final int PLAY= 0;
		public static final int WAIT = 1;
		public static final int VERSUS = 2;
		public static final int YOURBET = 3;
		public static final int WAITBET = 5;
	 
	    private int state = PLAY;
	   
	    //variables para guardar nombres etc..
	    public String[] jugadores = new String[2]; //inicialmente solo 2 jugadores
	    public int numPlays = 0; //cuando solo ha recibido un play -> numplays=1 envía WAIT
	    						// cuando recibe el segundo play -> numplays=2 envía VERSUS
	    //Mensajes versus que debe envíar a cada uno
	    public String mensajeVersus1=null;
	    public String mensajeVersus2=null;
	    public String mensajeWAIT = null;
	    
	    //para la función wait
	    public String mensaje_waitbet = null;
	    
	    //para método yourbet
	    public String mensaje_yourbet = null;
	    public int[] apuestas = new int[2];
	    public int numBets = 0;
	    public int[] bet_gan = new int[2]; //primer posición quien gana, segunda posicion quien pierde
	    
	    //le pasamos los nomrbes de los jugadores para que cuando entre en versus escriba los mensajes pertitentes
	    public void dame_jugadores(String j1, String j2)
	    {
	    	this.jugadores[0] = j1;
	    	this.jugadores[1] = j2;
	    }
	    
	    public void mensaje_wait()
	    {
	    	System.out.println("Mensaje WAIT al jugador1");
	    	this.mensajeWAIT="WAIT:"+this.jugadores[0];
	    }
	    
	    public void mensaje_versus()
	    {
	    	//para el jugador 1: "VERSUS:jugador2"
	    	this.mensajeVersus1 = "VERSUS:" + this.jugadores[1];
	    	this.mensajeVersus2 = "VERSUS:" + this.jugadores[0];
	    }
	    
	    public void mensaje_waitbet()
	    {
	    	//MENSAJE: 3=WAITBET
	    	this.mensaje_waitbet = "3";
	    }
	    
	    public void mensaje_yourbet()
	    {
	    	//Envíamos 4:APUESTAGANADORA
	    	if(this.bet_gan[0] == this.apuestas[0])
	    	{
	    		//gana jugador 1
	    		this.mensaje_yourbet = "5:"+this.jugadores[0];
	    	}
	    	else if(this.bet_gan[0] == this.apuestas[1])
	    	{
	    		//gana jugador 1
	    		this.mensaje_yourbet = "5:"+this.jugadores[1];
	    	}
	    	else if(this.bet_gan[0] == 0)
	    	{
	    		this.mensaje_yourbet = "5:EMPATE";
	    	}
	    	
	    }
	    
	    public void calculo_apuestas()
	    {
	    	if(this.apuestas[0]>this.apuestas[1])
	    	{
	    		this.bet_gan[0] = this.apuestas[0];
	    		this.bet_gan[1] = this.apuestas[1];
	    	}
	    	else if(this.apuestas[0]<this.apuestas[1])
	    	{
	    		this.bet_gan[0] =this.apuestas[1];
	    		this.bet_gan[1] = this.apuestas[0];
	    	}
	    	else if(this.apuestas[0]==this.apuestas[1])
	    	{
	    		this.bet_gan[0] = 0; //EMPATE
	    	}
	    }
	    
	    //le pasamos la input que indica el estate
	    
	    //Si envíamos -> play es 1
	    /*				 wait es 2
	     * 				 waitbet es 3
	     * 				 yourbet es 4
	     */
	    public String processInput(int theInput) {
	        String theOutput = null;
	        state = theInput;
	        if (state == PLAY) 
	        {
	        	//this.numPlays++; ---> el cliente debe increme
	        	//si solo un play o 2
	        	if(this.numPlays==1)
	        	{
	        		state = WAIT;
	        		this.mensaje_wait();
	        	}
	        	if(this.numPlays==2)
	        	{
	        		//llamamos al metodo que crea el mensaje de VERSUS
	        		this.mensaje_versus();
	        	}
	        }     
	        
	        else if (state == WAIT) 
	        {
	        	this.mensaje_wait();
	        }
	        
	        else if (state == VERSUS) 
	        {
	            this.mensaje_versus(); //para generar los mensajes versus 
	        } 
	        
	        else if (state == YOURBET) 
	        {
	        	 if(this.numBets == 2)
	        	{
	        		this.calculo_apuestas();
	        		this.mensaje_yourbet();
	        	}
	        }
	        
	        else if(state == WAITBET)
	        {
	        	this.mensaje_waitbet();
	        }
	        return theOutput;
	    }
}
