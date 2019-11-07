import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Date;
import java.io.*;

public class BancoADLL
{
	private LinkedList listaClientes = new LinkedList();
	private LinkedList listaRetiros = new LinkedList();
	private LinkedList listaDepositos = new LinkedList();
	private int nodoActual;
	private Date fecha;
	private PrintWriter  archivoOut;
    private BufferedReader archivoIn;
	
	public BancoADLL()
	{
		try
        {
            archivoIn = new BufferedReader(new FileReader("Clientes.txt"));
            
            while(archivoIn.ready())
                captura(archivoIn.readLine());
            
            archivoIn.close();
        }
        catch(IOException ioe)
        {
            System.out.println("Error: "+ioe);
        }
	}
	
	//Capturar los datos
	public String capturar(String datos)
	{
		String nocta, respuesta;
		
		StringTokenizer st = new StringTokenizer(datos,"_");
		nocta = st.nextToken();
		
		respuesta = consultarNocta(nocta);
		
		if(respuesta.equals("NOT_FOUND")||(respuesta.equals("EMPTY")))
		{
			respuesta = captura(datos);
		}
		else
		{
			respuesta = "La cuenta ya existe: "+nocta;
		}
		
		return respuesta;
	}
	
	public String captura(String datos)
	{
		String respuesta = "";
		
		listaClientes.add(new ClientesDP(datos));
		
		respuesta = "Nuevo nodo creado en la LinkedList...";
		
		return respuesta;
	}
	
	public String addFirst(String datos)
	{
		String nocta, respuesta;
		
		StringTokenizer st = new StringTokenizer(datos,"_");
		nocta = st.nextToken();
		
		respuesta = consultarNocta(nocta);
		
		if(respuesta.equals("NOT_FOUND")||(respuesta.equals("EMPTY")))
		{
			listaClientes.addFirst(new ClientesDP(datos));	
		}
		
				
		respuesta = "Nuevo nodo creado al inicio de LinkedList...";
		
		return respuesta;
	}
	
	//Buscar TODOS los clientes en LL o por NOCTA
	
	public String consultarClientes()
	{
		String datos = "";
		int i = 0;
		
		if(listaClientes.isEmpty())
		{
			datos = "Lista vacia...";
		}
		else
		{
			while(i<listaClientes.size())
			{
				datos = datos + listaClientes.get(i).toString() + "\n";
				i++;
			}
		}
				
		return datos;
	}
	
	
	public String consultarNocta(String noctaBuscar)
	{
		String datos="";
		boolean encontrado = false;
		int contador = 0;
		ClientesDP actual;
		int i = 0;
		
		if(listaClientes.isEmpty())
		{
			datos = "EMPTY";
		}
		else
		{
			while(i<listaClientes.size() && !encontrado)
			{
				actual = (ClientesDP)listaClientes.get(i);
				if(noctaBuscar.equals(actual.getNocta()))
				{
					datos = actual.toString() + "\nActual es el nodo "+contador;
					encontrado = true;
					nodoActual = i;
				}
				else
				{
					//anterior = actual;
					//actual = actual.getNext();
					contador++;
					i++;
				}
			}
			
			if(!encontrado)
			{
				datos="NOT_FOUND";
			}
		}
		
		
		return datos;
	}
	
	//Una vez buscada la cuenta puede eliminarse retirar o depositar
	public String eliminarNodoCliente()
	{
		int nodo=0;
		
		String respuesta ="";
		almacenarBajaCliente(nodoActual);
		listaClientes.remove(nodoActual);
		
		respuesta = "Nodo cliente eliminado: "+nodoActual;
		
		
		
		return respuesta;
		
	}
	
	public String retiro(int cantidad)
	{
		String datos= "";
		String aux ="";	
		ClientesDP actual;
		
		actual = (ClientesDP)listaClientes.get(nodoActual);
		aux = actual.toString();
		
		String tipo   = actual.getTipo();
		int  saldo    = actual.getSaldo();   
				
				
		if((tipo.equals("INVERSION")) || (tipo.equals("AHORRO"))) 
		{
			//System.out.println(cantidad+" de : "+numero);
			saldo = saldo - cantidad;
			fecha = new Date();
			aux = aux+"_"+saldo+"_"+fecha;
			actual.setSaldo(saldo);
			actual.toString();
			datos = "Dato guardado retiro: "+actual.toString();

			datos = capturarRet(aux);	
		}
		
		if(tipo.equals("CREDITO"))
		{
			saldo = saldo + cantidad;
			fecha = new Date();
			aux = aux+"_"+saldo+"_"+fecha;
			actual.setSaldo(saldo);
			actual.toString();
			datos = "Dato guardado retiro: "+actual.toString();

			datos = capturarRet(aux);	
		}
		
		if(tipo.equals("HIPOTECA"))
		{
			datos = "Error, no se puede hacer un retiro de una hipoteca!";
		}
		
		return datos;
	}
	
	public String deposito(int cantidad)
	{
		String datos= "";
		String aux ="";	
		ClientesDP actual;
		
		actual = (ClientesDP)listaClientes.get(nodoActual);
		aux = actual.toString();
		
		String tipo   = actual.getTipo();
		int  saldo    = actual.getSaldo();   
				
				
		if((tipo.equals("INVERSION")) || (tipo.equals("AHORRO"))) 
		{
			//System.out.println(cantidad+" de : "+numero);
			saldo = saldo + cantidad;
			fecha = new Date();
			aux = aux+"_"+saldo+"_"+fecha;
			actual.setSaldo(saldo);
			actual.toString();
			datos = "Dato guardado retiro: "+actual.toString();

			datos = capturarDep(aux);	
		}
		
		if((tipo.equals("CREDITO")) || (tipo.equals("HIPOTECA")))
		{
			saldo = saldo - cantidad;
			fecha = new Date();
			aux = aux+"_"+saldo+"_"+fecha;
			actual.setSaldo(saldo);
			actual.toString();
			datos = "Dato guardado retiro: "+actual.toString();

			datos = capturarDep(aux);	
		}
		
		
		return datos;
	}
	
	//CAPTURA DE RET Y DEPOSITO EN LL
	
	public String capturarRet(String datos)
	{
		String respuesta = "";
		
		listaRetiros.add(new RetirosDP(datos));
		
		respuesta = "Nuevo nodo creado en la LinkedList...";
		
		return respuesta= "Datos de Retiro: "+datos;
	}
	
	public String capturarDep(String datos)
	{
		String respuesta = "";
		
		listaDepositos.add(new DepositosDP(datos));
		
		respuesta = "Nuevo nodo creado en la LinkedList...";
		
		return respuesta= "Datos de Retiro: "+datos;
	}
	
	//Consultar LL de Ret y Dep
	
	public String consultarRet()
	{
		String datos = "";
		int i = 0;
		
		if(listaRetiros.isEmpty())
		{
			datos = "Lista vacia...";
		}
		else
		{
			while(i<listaRetiros.size())
			{
				datos = datos + listaRetiros.get(i).toString() + "\n";
				i++;
			}
		}
				
		return datos;
	}
	
	
	public String consultarDep()
	{
		String datos = "";
		int i = 0;
		
		if(listaDepositos.isEmpty())
		{
			datos = "Lista vacia...";
		}
		else
		{
			while(i<listaDepositos.size())
			{
				datos = datos + listaDepositos.get(i).toString() + "\n";
				i++;
			}
		}
				
		return datos;
	}
	
	//Guardar en archivos Clientes, Ret y Dep
	public void guardarArchivo()
    {
    	ClientesDP actual;
    	int i=0;
    	
        if(listaClientes.isEmpty())
            System.out.println("Lista vacia...");
        else
        {
            try
            {
                archivoOut = new PrintWriter(new FileWriter("Clientes.txt"));
                
                //actual = (ClienteDP)listaClientes.get(i);
                while(i<listaClientes.size())
                {
                	actual=(ClientesDP)listaClientes.get(i);     
                    
                    archivoOut.println(actual.toString());
                    
                    i++;
                }
                
                archivoOut.close();
                
                System.out.println("Datos almacenados en el archivo...");
            }
            catch(IOException ioe)
            {
                System.out.println("Error: "+ioe);
            }
        }
    }
    
    public void almacenarBajaCliente(int nodoBorrado)
    {
    	ClientesDP actual;
    	
    	if(listaClientes.isEmpty())
            System.out.println("Lista vacia...");
        else
        {
            try
            {
                //archivoOut = new PrintWriter(new FileWriter("BajaClientes.txt"));
                archivoOut = new PrintWriter(new FileWriter("BajaClientes.txt",true));
                
                //actual = (ClienteDP)listaClientes.get(i);
                actual=(ClientesDP)listaClientes.get(nodoBorrado);     
                    
                archivoOut.println(actual.toString());
                    
                  
                
                archivoOut.close();
                
                System.out.println("Datos almacenados en el archivo...");
            }
            catch(IOException ioe)
            {
                System.out.println("Error: "+ioe);
            }
        }
    }
    
    public void guardarDatosLLDepositosArchDepositos()
    {
    	DepositosDP actualD;
    	int i=0;
    	
        if(listaDepositos.isEmpty())
            System.out.println("Lista vacia...");
        else
        {
            try
            {
                archivoOut = new PrintWriter(new FileWriter("Depositos.txt",true));
                
                //actual = (ClienteDP)listaClientes.get(i);
                while(i<listaDepositos.size())
                {
                	actualD=(DepositosDP)listaDepositos.get(i);     
                    
                    archivoOut.println(actualD.toString());
                    
                    i++;
                }
                
                archivoOut.close();
                
                System.out.println("Datos almacenados en el archivo...");
            }
            catch(IOException ioe)
            {
                System.out.println("Error: "+ioe);
            }
        }
    }
    
    public void guardarDatosLLRetirosArchRetiros()
    {
    	RetirosDP actualR;
    	int i=0;
    	
        if(listaRetiros.isEmpty())
            System.out.println("Lista vacia...");
        else
        {
            try
            {
                archivoOut = new PrintWriter(new FileWriter("Retiros.txt",true));
                
                //actual = (ClienteDP)listaClientes.get(i);
                while(i<listaRetiros.size())
                {
                	actualR=(RetirosDP)listaRetiros.get(i);     
                    
                    archivoOut.println(actualR.toString());
                    
                    i++;
                }
                
                archivoOut.close();
                
                System.out.println("Datos almacenados en el archivo...");
            }
            catch(IOException ioe)
            {
                System.out.println("Error: "+ioe);
            }
        }	
    }
    
    //Consultar los 4 txt Clientes Retiros Depositos y Baja Clientes
    
    
    public String consultarArchivo(String tipo)
    {
        String datos="";
        
        try
        {
            archivoIn = new BufferedReader(new FileReader(tipo+".txt"));
            
            while(archivoIn.ready())
                datos = datos + archivoIn.readLine() + "\n";
            
            archivoIn.close();
        }
        catch(IOException ioe)
        {
            datos = "Error: "+ioe;
            System.out.println("Error: "+ioe);
        }
        
        return datos;
    }
    
}


	/*
	 *		size()       -->no. de nodo
	 *		isEmpty      -->boolean true/false
	 *		get(indice)  -->obtener el objeti
	 *		remove(nodo) -->Eliminar un nodo de la LinkedList
	 **/
