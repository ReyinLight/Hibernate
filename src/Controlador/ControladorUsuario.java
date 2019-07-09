package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.hibernate.Session;

import Modelo.Persona;
import Modelo.Usuario;
import Vista.framePersona;
import Vista.frameUsuario;

public class ControladorUsuario extends Conexion implements ActionListener {
	
	private Usuario u;
	private frameUsuario frm;
	
	
	public ControladorUsuario(frameUsuario frm)
	{
		this.u = new Usuario();
		
		this.frm = frm;
		this.frm.bt_guardar.addActionListener(this);
		this.frm.bt_modificar.addActionListener(this);
		this.frm.bt_eliminar.addActionListener(this);
		this.frm.bt_limpiar.addActionListener(this);
		this.frm.bt_buscar.addActionListener(this);
	}
	
	
	public void iniciar()
	{
		frm.setTitle("Usuario");
		frm.setLocationRelativeTo(null);
		frm.setVisible(false);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		try {u.setIdUsuario(Integer.parseInt(frm.tf_idUsuario.getText()));}catch (Exception e2){}
		u.setUsuario(frm.tf_usuario.getText());
		u.setContrasenia(frm.tf_contrasenia.getText());
		try {u.setIdPersona(Integer.parseInt(frm.tf_persona.getText()));} catch (Exception e2) {}
		
		
		if(e.getSource() == frm.bt_guardar)
		{
			
			if(registrar(u))
			{
				JOptionPane.showMessageDialog(null, "Registro Guardado");
				limpiar();
			}
				
			else
				JOptionPane.showMessageDialog(null, "Error al guardar");
		}
		
		if(e.getSource() == frm.bt_modificar)
		{
			if(modificar(u))
			{
				JOptionPane.showMessageDialog(null, "Registro Modificado");
				limpiar();
			}
				
			else
				JOptionPane.showMessageDialog(null, "Error al modificar");
		}
		
		if(e.getSource() == frm.bt_eliminar)
		{	
			if(eliminar(u))
			{
				JOptionPane.showMessageDialog(null, "Registro Eliminado");
				limpiar();
			}
				
			else
				JOptionPane.showMessageDialog(null, "Error al Eliminar");
		}
		
		if(e.getSource() == frm.bt_buscar)
		{	
			if(buscar(u));
			else
				JOptionPane.showMessageDialog(null, "No se encontro registro");
		}
		
		if(e.getSource() == frm.bt_limpiar)
			limpiar();
		
	}
	
	public void limpiar()
	{
		frm.tf_idUsuario.setText(null);
		frm.tf_usuario.setText(null);
		frm.tf_contrasenia.setText(null);
		frm.tf_persona.setText(null);
	}
	
	
	
	public boolean registrar(Usuario us)
	{
		
		Session session = buildSession(Usuario.class).getCurrentSession();
		try {
			session.beginTransaction();
			session.save(us);
			session.getTransaction().commit();
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		
	}
	
	
	public boolean modificar(Usuario us)
	{
		Session session = buildSession(Usuario.class).getCurrentSession();
		
		try {
			session.beginTransaction();
			us = session.get(Usuario.class, us.getIdUsuario());
			us.setUsuario(frm.tf_usuario.getText());
			us.setContrasenia(frm.tf_contrasenia.getText());
			us.setIdPersona(Integer.parseInt(frm.tf_persona.getText()));
			session.getTransaction().commit();
			return true;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public boolean eliminar(Usuario us)
	{
		Session session = buildSession(Usuario.class).getCurrentSession();
		
		try {
			session.beginTransaction();
			us = session.get(Usuario.class, us.getIdUsuario());
			session.delete(us);
			session.getTransaction().commit();
			
			return true;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public boolean buscar(Usuario us)
	{
		Session session = buildSession(Usuario.class).getCurrentSession();
				
		try {
			session.beginTransaction();
			us = session.get(Usuario.class, us.getIdUsuario());
			
			frm.tf_usuario.setText(us.getUsuario());
			frm.tf_contrasenia.setText(us.getContrasenia());
			frm.tf_persona.setText(Integer.toString(us.getIdPersona()));
			
			session.getTransaction().commit();
			
			return true;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

}
