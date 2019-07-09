import java.awt.PageAttributes;

import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import Controlador.ControladorConsultas;
import Controlador.ControladorPersona;
import Controlador.ControladorUsuario;
import Modelo.Persona;
import Modelo.Usuario;
import Vista.frameConsultas;
import Vista.framePersona;
import Vista.frameUsuario;

public class Sistema {
	
	static public void main(String[] args)
	{
		framePersona frmP = new framePersona();
		ControladorPersona p = new ControladorPersona(frmP);
		
		frameUsuario frmU = new frameUsuario();
		ControladorUsuario u = new ControladorUsuario(frmU);
//		
//		
		DefaultTableModel modelPersonas = null;
		frameConsultas frmC = new frameConsultas();
		ControladorConsultas ctrC = new ControladorConsultas(modelPersonas,frmC);
//		
		
		p.iniciar();
		u.iniciar();
		
		frmC.setVisible(true);
		frmU.setVisible(true);
		frmP.setVisible(true);
		
		
		
		
		
		
	}
	

}
