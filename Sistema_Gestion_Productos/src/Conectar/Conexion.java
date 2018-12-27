
package Conectar;

import javax.swing.JOptionPane;


public  class Conexion {
private String url;
private String username;
private String pass;
private final String name="ricardo";
private final String password="ricardo";
private static Conexion conec;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
       
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
   
    
    
   
    private Conexion() {
    }

    public static Conexion getSingletonInstancie(){
    if(conec==null){
    conec= new Conexion();
    }
    else{
        JOptionPane.showMessageDialog(null, "No se permiten mas conexiones a la DB,singleton funciona ");
        System.out.println("No se permiten mas conexiones");
    }
    return(conec);
    }
    


    public void ConectarDB() {
        do{
            
            username= JOptionPane.showInputDialog("Ingresa el nombre de usuario");
            pass=JOptionPane.showInputDialog("Ingresa la la contraseña");
            
            
        }while(pass.compareTo(password) != username.compareTo(name));
        
        url= "jdbc:derby://localhost:1527/Almacen";
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            JOptionPane.showMessageDialog(null, "Conexion exitosaa DB Almacen");
            //System.out.println("Conexion Exitosa");
        } catch (ClassNotFoundException e) {
            // System.out.println("Exception creating connection: " + e);
            JOptionPane.showMessageDialog(null,"Error al conectar" +e );
            System.exit(0);
            
        }
    }
    
        
}

           
    
       