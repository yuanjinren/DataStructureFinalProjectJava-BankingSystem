import java.io.*;
import java.util.StringTokenizer;
//para el tiempo
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 
public class BancoAD
{
	
	//Atributos
	private ClientesDP primero, ultimo, actual,anterior;
	private DepositosDP primeroD, ultimoD, actualD;
	private RetirosDP primeroR, ultimoR, actualR;
	private PrintWriter archivoOut;
	private BufferedReader archivoIn;
	
	String archivo="";
	
	//Constructor
	public BancoAD()
	{
		String aux = "";
		try
		{
			archivoIn = new BufferedReader(new FileReader("Clientes.txt"));	
			
			while(archivoIn.ready())
			{
				aux = archivoIn.readLine();
				capturar(aux);
				archivo = archivo + aux + "\n";
			}
			
			archivoIn.close();
		}
		catch(IOException ioe)
        {
            System.out.println("Error: "+ioe);
        }
	}
	
	
	//Metodos
	public String capturar(String datos)
	{
		if(primero == null)
		{
			primero = new ClientesDP(datos);
			ultimo  = primero;
			ultimo.setNext(null);
		}
		else
		{
			actual = new ClientesDP(datos);
			ultimo.setNext(actual);
			ultimo = actual;
			ultimo.setNext(null);
		}
		return "Datos: "+datos;
	}
	
	public String capturarFirst(String datos)
	{
		if(primero == null)
		{
			primero = new ClientesDP(datos);
			ultimo  = primero;
			ultimo.setNext(null);
		}
		else
		{
			actual = primero;
			primero = new ClientesDP(datos);
			primero.setNext(actual);
		}
		
		return "Datos: "+datos;
	}
	
	public String borrarCliente()
	{
		int nodo=0;
		String respuesta = "";
		
		if(actual==primero)
		{
			primero.getNext();
		}
		else
		{
			if(actual == ultimo)
			{
				ultimo = anterior;
				ultimo.setNext(null);
			}
			else
			{
				anterior.setNext(actual.getNext());
			}
		}
		almacenarBajaCliente();
		
		respuesta = "Nodo cliente eliminado: "+actual.toString();
		
		return respuesta;
	}
	
	public String capturarDep(String datos)
	{
		if(primeroD == null)
		{
			primeroD = new DepositosDP(datos);
			ultimoD  = primeroD;
			ultimoD.setNext(null);
		}
		else
		{
			actualD = new DepositosDP(datos);
			ultimoD.setNext(actualD);
			ultimoD = actualD;
			ultimoD.setNext(null);
		}
		return "Datos de Deposito: "+datos;
	}
	
	public String capturarRet(String datos)
	{
		if(primeroR == null)
		{
			primeroR = new RetirosDP(datos);
			ultimoR  = primeroR;
			ultimoR.setNext(null);
		}
		else
		{
			actualR = new RetirosDP(datos);
			ultimoR.setNext(actualR);
			ultimoR = actualR;
			ultimoR.setNext(null);
		}
		return "Datos de Retiro: "+datos;
	}
	
	public String consultarClientes()
	{
		String datos = "";
		if(primero == null)
		{
			datos = "Lista vacia...";
		}
		else
		{
			actual = primero;
			while(actual != null)
			{
				//datos = datos + actual.getNocta() + "_" + actual.getNombre() + "_" + actual.getTipo()+ "_" + actual.getSaldo() + "\n";
				datos = datos + actual.toString() + "\n";
				actual = actual.getNext();
			}
		}
		
		return datos;
	}
	
	public void guardarArchivo()
	{
		String datos="";
		
		if(primero==null)
		{
			datos="Esta Vacio";
			System.out.println(datos);
		}
		else
		{
			try
        	{
            	// 1. Abrir el archivo
            	archivoOut = new PrintWriter(new FileWriter("Clientes.txt"));
            
            	actual = primero;
	            while(actual != null)
	            {
	            	archivoOut.println(actual.toString());
	            	
	            	actual = actual.getNext();
	            }
	            
	            archivoOut.close();
	            System.out.println("Datos guardados");
	        }
	        catch(IOException ioe)
	        {
	            System.out.println("Error: "+ioe);
	
	        }	
		}
		 	
	}
	
	public String consultarArchivo()
	{
		return archivo;
	}
	
	public String consultarNocta(String noctaBuscar)
	{
		String datos="";
		boolean encontrado = false;
		if(primero == null)
		{
			datos="Esta Vacio";
			System.out.println(datos);
		}
		else
		{
			actual = primero;
			while((actual != null)&&(encontrado==false))
			{
				String numero = actual.getNocta();
				
				if(numero.equals(noctaBuscar))
				{
					datos = datos + actual.toString();
					encontrado = true;
				}
				else
				{
					anterior = actual;
					actual = actual.getNext();	
				}
			
			}	
			
			if(datos.equals(""))
			{
				datos = "No existe...";
			}
		}
		
		
		
		return datos;
	}
	
	
	public int funcionComprobacion(String noctaBuscar)
	{
		int respuesta = 2;
		String datos ="";
		
		if(primero == null)
		{
			datos="Esta Vacio";
			System.out.println(datos);
		}
		else
		{
			actual = primero;
			while(actual != null)
			{
				String numero = actual.getNocta();
				
				if(numero.equals(noctaBuscar))
				{
					datos = datos + actual.toString();
					respuesta = 1;
				}
			
				actual = actual.getNext();
			}	
			
			if(datos.equals(""))
			{
				datos = "No existe...";
			}
		}
		
		
		return respuesta;
	}
	
	public String retiro(int cantidad)
	{
		String datos= "";

		String numero = actual.getNocta();
		String nombre = actual.getNombre();
		String tipo   = actual.getTipo();
		int  saldo    = actual.getSaldo();   
				
				
		if((tipo.equals("INVERSION")) || (tipo.equals("AHORRO"))) 
		{
			//System.out.println(cantidad+" de : "+numero);
			saldo = saldo - cantidad;
			String aux = actual.toString()+"_"+saldo;
			actual.setSaldo(saldo);
			actual.toString();
			datos = "Dato guardado retiro: "+actual.toString();

			capturarRet(aux);	
		}
		
		if(tipo.equals("CREDITO"))
		{
			saldo = saldo + cantidad;
			String aux = actual.toString()+"_"+saldo;
			actual.setSaldo(saldo);
			actual.toString();
			datos = "Dato guardado retiro: "+actual.toString();

			capturarRet(aux);	
		}
		
		if(tipo.equals("HIPOTECA"))
		{
			//saldo = saldo + cantidad;
			datos = "Error, no se puede hacer un retiro de una hipoteca!";
		}
		
		return datos;
	}
	
	public String deposito(int cantidad)
	{
		String datos= "";

		String numero = actual.getNocta();
		String nombre = actual.getNombre();
		String tipo   = actual.getTipo();
		int  saldo    = actual.getSaldo();  
		
				
		if((tipo.equals("INVERSION")) || (tipo.equals("AHORRO"))) 
		{
			//System.out.println(cantidad+" de : "+numero);
			saldo = saldo + cantidad;
			String aux = actual.toString()+"_"+saldo;
			actual.setSaldo(saldo);
			actual.toString();
			datos = "Dato guardado deposito: "+actual.toString();

			capturarDep(aux);	
		}
		
		if((tipo.equals("CREDITO")) || (tipo.equals("HIPOTECA")))
		{
			saldo = saldo - cantidad;
			String aux = actual.toString()+"_"+saldo;
			actual.setSaldo(saldo);
			actual.toString();
			datos = "Dato guardado deposito: "+actual.toString();
			
			capturarDep(aux);
		}
		
		return datos;
	}
	
	
	public String consultarDep()
	{
		String datos = "";
		if(primeroD == null)
		{
			datos = "Lista vacia...Depositos";
		}
		else
		{
			actualD = primeroD;
			while(actualD != null)
			{
				//datos = datos + actual.getNocta() + "_" + actual.getNombre() + "_" + actual.getTipo()+ "_" + actual.getSaldo() + "\n";
				datos = datos + actualD.toStringDep() + "\n";
				actualD = actualD.getNext();
			}
		}
		
		return datos;
	}
	
	public String consultarRet()
	{
		String datos = "";
		
		if(primeroR == null)
		{
			datos = "Lista vacia...Retiros";
		}
		else
		{
			actualR = primeroR;
			while(actualR != null)
			{
				//datos = datos + actual.getNocta() + "_" + actual.getNombre() + "_" + actual.getTipo()+ "_" + actual.getSaldo() + "\n";
				datos = datos + actualR.toStringRet() + "\n";
				actualR = actualR.getNext();
			}
		}
		
		return datos;
	}
	
	
	/*Funciones para guardar los LList En archivos*/
	public void guardarDatosLLRetirosArchRetiros()
	{
		String datos="";
		
		//OBTENER FECHA Y HORA DE CUANDO SE HACE EL DEPOSITO
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
   		LocalDateTime now = LocalDateTime.now();
		
		if(primeroR==null)
		{
			datos="Esta Vacio";
			System.out.println(datos);
		}
		else
		{
			try
        	{
				archivoOut = new PrintWriter(new FileWriter("Retiros.txt",true));
            	
            	actualR = primeroR;
	            while(actualR != null)
	            {
	            	archivoOut.println(actualR.toStringRet());
	            	
	            	actualR = actualR.getNext();
	            }
	            
	            //Siempre imprimir la fecha de cuando se guarda la info
	           	archivoOut.println("Datos guardados: "+(dtf.format(now)));
	            
	            archivoOut.close();
	            System.out.println("Datos guardados");
	        }
	        catch(IOException ioe)
	        {
	            System.out.println("Error: "+ioe);
	
	        }	
		}
	}
	
	public void guardarDatosLLDepositosArchDepositos()
	{
		String datos="";
		
		//OBTENER FECHA Y HORA DE CUANDO SE HACE EL registro
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
   		LocalDateTime now = LocalDateTime.now();
		
		if(primeroD==null)
		{
			datos="Esta Vacio";
			System.out.println(datos);
		}
		else
		{
			try
        	{
				archivoOut = new PrintWriter(new FileWriter("Depositos.txt",true));
            	
            	actualD = primeroD;
	            while(actualD != null)
	            {
	            	archivoOut.println(actualD.toStringDep());
	            	
	            	actualD = actualD.getNext();
	            }
	            
	            //Siempre imprimir la fecha de cuando se guarda la info
	           	archivoOut.println("Datos guardados: "+(dtf.format(now)));
	            archivoOut.close();
	            System.out.println("Datos guardados");
	        }
	        catch(IOException ioe)
	        {
	            System.out.println("Error: "+ioe);
	
	        }	
		}
	}
	
	public void almacenarBajaCliente()
	{
		if(primero==null)
		{
			String datos="Esta Vacio";
			System.out.println(datos);
		}
		else
		{
			try
        	{
				archivoOut = new PrintWriter(new FileWriter("BajaClientes.txt",true));
            	
            	//actual = primero;
	            //while(actual != null)
	            //{
	            	archivoOut.println(actual.toString());
	            	
	            	//actual = actualD.getNext();
	            //}
	            
	            //Siempre imprimir la fecha de cuando se guarda la info
	           	//archivoOut.println("Datos guardados: "+(dtf.format(now)));
	            archivoOut.close();
	            System.out.println("Datos guardados");
	        }
	        catch(IOException ioe)
	        {
	            System.out.println("Error: "+ioe);
	
	        }	
		}
	}
	
	public String eliminar()
    {
    	int n=0;
    	String respuesta="";
    	
    	if(actual==primero)
    	{
    		primero=primero.getNext();
    	}
    	else
    	{
    		if(actual==ultimo)
    		{
    			ultimo=anterior;
    			ultimo.setNext(null);
    		}
    		else
    		{
    			anterior.setNext(actual.getNext());
    		}
    	}
    	
    	almacenarBajaCliente();
    	
    	respuesta="Nodo Cliente eliminado: "+actual.toString();
    	return respuesta;
    }
	
	//guardarDatosLLDepositosArchDepositos
	
	/*Consultar los txt*/
	
	public String consultarArchivo(String nombre)
	{
		String aux = "";
		String info="";
		try
		{
			archivoIn = new BufferedReader(new FileReader(nombre+".txt"));	
			
			while(archivoIn.ready())
			{
				aux = archivoIn.readLine();
				//capturar(aux);
				info = info + aux + "\n";
			}
			
			archivoIn.close();
		}
		catch(IOException ioe)
        {
            System.out.println("Error: "+ioe);
        }
        
        return info;
	}
}