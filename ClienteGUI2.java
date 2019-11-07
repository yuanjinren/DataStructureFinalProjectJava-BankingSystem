import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

import java.awt.GridLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClienteGUI2 extends JFrame implements ActionListener
{
    private JTextField tfNocta, tfNombre, tfTipo, tfSaldo;
    private JButton    bCapturar, bConsultar, bSalir, bConsultarClientes, bConsultarNocta;
    private JButton    bRetiro, bDeposito, bCancelar;
    private JButton    bConRetL, bConDepL, bConRetTxt, bConDepTxt,bConBajaTxt;
    private JButton    bAddFirst, bEliminarCliente;
    private JPanel     panel1, panel2;
    private JTextArea  taDatos;
    private JComboBox comboCuentas;
    private String opcionesCuenta[] = {"INVERSION","CREDITO","AHORRO","HIPOTECA"};
    
    //private BancoAD bancoad = new BancoAD();
    private BancoADLL bancoad = new BancoADLL();
    
    public ClienteGUI2()
    {
        super("BANCO VB CITY LL");
        
        // 1. Crear los objetos de los atributos
        tfNocta = new JTextField();
        tfNombre = new JTextField();
        tfTipo   = new JTextField();
        tfSaldo  = new JTextField();
        bCapturar = new JButton("Capturar datos (LList)");
        bConsultar = new JButton("Consultar Clientes (LList)");
        bConsultarNocta = new JButton("Consultar No. Cuenta");
        bConsultarClientes = new JButton("Consultar Clientes (Archivo)");
        bRetiro = new JButton("Retiro de Cuenta (Arreglo)");
        bDeposito = new JButton("Deposito a Cuenta (Arreglo)");
        bCancelar = new JButton("Cancelar Transaccion");
        bConRetL = new JButton("Consultar Retiros LList");
        bConDepL = new JButton("Consultar Depositos LList"); 
        bConRetTxt = new JButton("Consultar Retiros Txt");
        bConDepTxt = new JButton("Consultar Depositos Txt");
        bAddFirst = new JButton("Add First (LList)");
        bEliminarCliente = new JButton("Eliminar Clientes (LList)");
        bSalir = new JButton("Exit");
        bConBajaTxt = new JButton("Consultar BajaClientes Txt");
        panel1 = new JPanel();
        panel2 = new JPanel();
        taDatos = new JTextArea(10,30);
        comboCuentas = new JComboBox(opcionesCuenta);
        
        // Adicionar addActionListener a lo JButtons
        bCapturar.addActionListener(this);
        bConsultar.addActionListener(this);
        bConsultarNocta.addActionListener(this);
        bConsultarClientes.addActionListener(this);
        bDeposito.addActionListener(this);
        bRetiro.addActionListener(this);
        bCancelar.addActionListener(this);
        bSalir.addActionListener(this);
        bConRetL.addActionListener(this);
        bConDepL.addActionListener(this);
        bConRetTxt.addActionListener(this);
        bConDepTxt.addActionListener(this);
        bAddFirst.addActionListener(this);
        bEliminarCliente.addActionListener(this);
        bConBajaTxt.addActionListener(this);
        
        bRetiro.setEnabled(false);
        bDeposito.setEnabled(false);
        bCancelar.setEnabled(false);
        bEliminarCliente.setEnabled(false);
        
        // 2. Definir los Layouts de los JPanels
        panel1.setLayout(new GridLayout(12,2));
        panel2.setLayout(new FlowLayout());
        
        // 3. Colocar los objetos de los atributos en los JPanels correspondientes
        panel1.add(new JLabel("No. DE CUENTA"));
        panel1.add(tfNocta);
        panel1.add(new JLabel("NOMBRE: "));
        panel1.add(tfNombre);
        panel1.add(new JLabel("TIPO DE CUENTA"));
        //panel1.add(tfTipo);
        panel1.add(comboCuentas);
        panel1.add(new JLabel("SALDO"));
        panel1.add(tfSaldo);
        panel1.add(bCapturar);
        panel1.add(bConsultar);
        panel1.add(bConsultarNocta);
        panel1.add(bConsultarClientes);
        panel1.add(bRetiro);
        panel1.add(bDeposito);
        
        panel1.add(bConRetL);
        panel1.add(bConDepL);
        panel1.add(bConRetTxt);
        panel1.add(bConDepTxt);
        panel1.add(bConBajaTxt);
        
        panel1.add(bCancelar);

        
        panel1.add(bAddFirst);
        panel1.add(bEliminarCliente); 
		panel1.add(bSalir);        
        
        panel2.add(panel1);
        panel2.add(new JScrollPane(taDatos));
        
        // 4. Adicionar el panel2 al JFrame y hacerlo visible
        add(panel2);
        setSize(500,500);
        setVisible(true);
    }
    
    private void inactivarBotones()
    {
        bCapturar.setEnabled(false);
        bConsultar.setEnabled(false);
        bConsultarNocta.setEnabled(false);
        bConsultarClientes.setEnabled(false);
        bRetiro.setEnabled(true);
        bDeposito.setEnabled(true);
        bCancelar.setEnabled(true);
       	bEliminarCliente.setEnabled(true);
    }
    
    private void activarBotones()
    {
        bCapturar.setEnabled(true);
        bConsultar.setEnabled(true);
        bConsultarNocta.setEnabled(true);
        bConsultarClientes.setEnabled(true);
        bRetiro.setEnabled(false);
        bDeposito.setEnabled(false);
        bCancelar.setEnabled(false);
        bEliminarCliente.setEnabled(false);
    }
    
    private String obtenerDatos()
    {
        String datos;
        
        String nocta  = tfNocta.getText();
        String nombre = tfNombre.getText();
        //String tipo   = tfTipo.getText();
        String tipo   = (String)comboCuentas.getSelectedItem();
        String saldo  = tfSaldo.getText();
        
        if(nocta.equals("") || nombre.isEmpty() || tipo.equals("") || saldo.isEmpty())
            datos = "VACIO";
        else
        {
            try
            {
                int n = Integer.parseInt(saldo);
                datos = nocta+"_"+nombre+"_"+tipo+"_"+saldo;
            }
            catch(NumberFormatException nfe)
            {
                datos = "NO_NUMERICO";
            }
        }
        
        return datos;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String datos, respuesta;
        
        if(e.getSource() == bCapturar)
        {
            // 1. Obtener datos
            datos = obtenerDatos();
            
            // 2. Checar datos: datos no vacios y saldo numerico, y realizar la captura de datos
            if(datos.equals("VACIO"))
                respuesta = "Algun campo esta vacio...";
            else
                {
                	String numBuscar = tfNocta.getText();
                	int r = 0;
                	//r = bancoad.funcionComprobacion(numBuscar);
                	if(r==1)
                	{
                		respuesta = "YA EXISTE ESE NUMERO DE CUENTA...";
                	}
                	else
                	{
                		respuesta = bancoad.capturar(datos);	
                	}
                	
                    //respuesta = datos;
                }
            
            // 3. Desplegar esultado de transaccion
            taDatos.setText(respuesta);
        }
        
        if(e.getSource() == bConsultar)
        {
            // 1. Realizar consulta de clientes
            datos = bancoad.consultarClientes();
            
            // 2. Desplegar datos
            taDatos.setText(datos);
            //taDatos.setText("Consultar Clientes ...");
        }
        
        if(e.getSource() == bAddFirst)
        {
        	// 1. Obtener datos
            datos = obtenerDatos();
            
            // 2. Checar datos: datos no vacios y saldo numerico, y realizar la captura de datos
            if(datos.equals("VACIO"))
                respuesta = "Algun campo esta vacio...";
            else
                {
                	String numBuscar = tfNocta.getText();
                	int r = 0;
                	//r = bancoad.funcionComprobacion(numBuscar);
                	if(r==1)
                	{
                		respuesta = "YA EXISTE ESE NUMERO DE CUENTA...";
                	}
                	else
                	{
                		respuesta = bancoad.addFirst(datos);	
                	}
                	
                    //respuesta = datos;
                }
            
            // 3. Desplegar esultado de transaccion
            taDatos.setText(respuesta);
        }
        
        
        if(e.getSource() == bConsultarClientes)
        {
            // 1. Realizar consulta de clientes en el archivo
            datos = bancoad.consultarArchivo("Clientes");
            
            // 2. Desplegar datos
            taDatos.setText(datos);
        }
        
        if(e.getSource() == bConsultarNocta)
        {
            // 1. Obtener el no. de cuenta a buscar
            String ncta = tfNocta.getText();
            
            // 2. Realizar transaccion de consultar no. de cuenta
            respuesta = bancoad.consultarNocta(ncta);
            if(respuesta.equals("NOT_FOUND"))
                respuesta = "No. de Cuenta no localizado: "+ncta;
            else
                if(respuesta.equals("EMPTY"))
                    respuesta = "LISTA VACIA...";
                else
                    inactivarBotones();
            
            // 3. Desplegar resultado de la transaccion
            taDatos.setText(respuesta);
        }
        
        if(e.getSource() == bCancelar)
        {
            activarBotones();
        }
        
        if(e.getSource() == bEliminarCliente)
        {
        	respuesta = bancoad.eliminarNodoCliente();
        	taDatos.setText(respuesta);
        	activarBotones();
        }
        
        
        if(e.getSource() == bRetiro)
        {
        	
        	int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad a Retirar: "));
            //String ncta = tfNocta.getText();
            respuesta = bancoad.retiro(cantidad);    
            
            activarBotones();
            
            taDatos.setText(respuesta);
        }
        
        if(e.getSource() == bDeposito)
        {
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad a Depositar: "));
            String ncta = tfNocta.getText();
            respuesta = bancoad.deposito(cantidad);    
            
            activarBotones();
            
            taDatos.setText(respuesta);
        }
        
        
        
        if(e.getSource() == bSalir)
        {
        	bancoad.guardarArchivo();
            bancoad.guardarDatosLLRetirosArchRetiros();
            bancoad.guardarDatosLLDepositosArchDepositos();
            
            //System.out.println(respuesta);
            System.exit(0);
        }
        
        if(e.getSource() == bConDepL)
        {
            respuesta = bancoad.consultarDep();
            taDatos.setText(respuesta);
        }
        
        if(e.getSource() == bConRetL)
        {
            respuesta = bancoad.consultarRet();
            taDatos.setText(respuesta);
        }
        
        
        if(e.getSource() == bConRetTxt)
        {
        	respuesta = bancoad.consultarArchivo("Retiros");
        	taDatos.setText(respuesta);
        }
        
        if(e.getSource() == bConDepTxt)
        {
        	respuesta = bancoad.consultarArchivo("Depositos");
        	taDatos.setText(respuesta);
        }
        
        if(e.getSource() == bConBajaTxt)
        {
        	respuesta = bancoad.consultarArchivo("BajaClientes");
        	taDatos.setText(respuesta);	
        }
    }
    
    public static void main(String args[])
    {
        new ClienteGUI2();
    }
}



