import java.util.StringTokenizer;


public class RetirosDP
{
	//Atributos
	private String nocta, nombre, tipo,fecha;
	private int saldo,saldoActualizado;
	
	private RetirosDP next;
	
	//Constructores
	public RetirosDP()
	{
		this.nocta  = "";
		this.nombre = "";
		this.tipo   = "";
		this.saldo  = 0;
		this.saldoActualizado = 0;
		this.fecha="";
		this.next   =  null;
	}
	
	public RetirosDP(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos,"_");
		
		this.nocta  = st.nextToken();
		this.nombre = st.nextToken();
		this.tipo   = st.nextToken();
		this.saldo  = Integer.parseInt(st.nextToken());
		this.saldoActualizado = Integer.parseInt(st.nextToken());
		this.fecha = st.nextToken();
	}
	
	//Metodos: Accesors (geters) y Mutators (seters)
	//Accesors (geters)
	public String getNocta()
	{
		return this.nocta;
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public String getTipo()
	{
		return this.tipo;
	}
	
	public int getSaldo()
	{
		return this.saldo;
	}
	
	public RetirosDP getNext()
	{
		return this.next;
	}
	
	//Mutators (seters)
	public void setNocta(String cta)
	{
		this.nocta = cta;
	}
		
	public void setNombre(String name)
	{
		this.nombre = name;
	}
	
	public void setTipo(String tcta)
	{
		this.tipo = tcta;
	}
	
	/*Saldo anterior*/
	public void setSaldoActualizado(int cantidad)
	{
		this.saldoActualizado = cantidad;
	}
	
	public void setSaldo(int cantidad)
	{
		this.saldo = cantidad;
	}
	
	public void setNext(RetirosDP nodo)
	{
		this.next = nodo;
	}
	
	
	public String toString()
	{
		return this.nocta + "_" + this.nombre + "_" + this.tipo + "_" + this.saldo + "_" + this.saldoActualizado+"_"+fecha;
	}
}