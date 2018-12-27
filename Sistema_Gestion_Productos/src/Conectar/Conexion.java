
package Conectar;

import javax.swing.JOptionPane;


public  class Conexion {
private String url;
private String username;
private String pass;
private final String name="ricardo";
private final String password="ricardo";

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
   
    
    
    
    public Conexion() {
    }

    
    
public  void ConectarDB (){
    
    

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

           
    
       