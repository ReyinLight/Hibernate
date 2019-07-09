package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



import Modelo.Persona;
import Vista.framePersona;

public class ControladorPersona extends Conexion implements ActionListener  {
	
	private Persona p;
	private framePersona frm;
	
	
	public ControladorPersona(framePersona frm)
	{
		this.p = new Persona();
		this.frm = frm;
		this.frm.bt_guardar.addActionListener(this);
		this.frm.bt_modificar.addActionListener(this);
		this.frm.bt_eliminar.addActionListener(this);
		this.frm.bt_limpiar.addActionListener(this);
		this.frm.bt_buscar.addActionListener(this);
	}
	
	
	public void iniciar()
	{
		frm.setTitle("Persona");
		frm.setLocationRelativeTo(null);
		frm.setVisible(false);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		try{p.setIdPersona(Integer.parseInt(frm.tf_idPersona.getText()));}catch(Exception e2){}
		p.setNombre(frm.tf_nombre.getText());
		p.setApellido(frm.tf_apellido.getText());
		p.setfechaNacimiento(frm.tf_fecha.getText());
		
		if(e.getSource() == frm.bt_guardar)
		{	
			if(registrar(p))
			{
				JOptionPane.showMessageDialog(null, "Registro Guardado");
				limpiar();
			}
				
			else
				JOptionPane.showMessageDialog(null, "Error al guardar");
		}
		
		if(e.getSource() == frm.bt_modificar)
		{
			if(modificar(p))
			{
				JOptionPane.showMessageDialog(null, "Registro Modificado");
				limpiar();
			}
				
			else
				JOptionPane.showMessageDialog(null, "Error al modificar");
		}
		
		if(e.getSource() == frm.bt_eliminar)
		{
			if(eliminar(p))
			{
				JOptionPane.showMessageDialog(null, "Registro Eliminado");
				limpiar();
			}
				
			else
				JOptionPane.showMessageDialog(null, "Error al Eliminar");
		}
		
		if(e.getSource() == frm.bt_buscar)
		{
			if(buscar(p));
			else
				JOptionPane.showMessageDialog(null, "No se encontro registro");
		}
		
		if(e.getSource() == frm.bt_limpiar)
			limpiar();
	}
	
	
	public void limpiar()
	{
		frm.tf_idPersona.setText(null);
		frm.tf_nombre.setText(null);
		frm.tf_apellido.setText(null);
		frm.tf_fecha.setText(null);
	}
	
	
	public boolean registrar(Persona pe)
	{
		
		Session session = buildSession(Persona.class).getCurrentSession();
		try {
			session.beginTransaction();
			session.save(pe);
			session.getTransaction().commit();
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		
	}
	
	public boolean modificar(Persona pe)
	{
		Session session = buildSession(Persona.class).getCurrentSession();
		
		try {
			session.beginTransaction();
			pe = session.get(Persona.class, pe.getIdPersona());
			pe.setNombre(frm.tf_nombre.getText());
			pe.setApellido(frm.tf_apellido.getText());
			pe.setfechaNacimiento(frm.tf_fecha.getText());
			session.getTransaction().commit();
			return true;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public boolean eliminar(Persona pe)
	{
		Session session = buildSession(Persona.class).getCurrentSession();
		
		try {
			session.beginTransaction();
			
			pe = session.get(Persona.class, pe.getIdPersona());
			
			session.delete(pe);
			
			session.getTransaction().commit();
			
			return true;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public boolean buscar(Persona pe)
	{
		Session session = buildSession(Persona.class).getCurrentSession();
				
		try {
			session.beginTransaction();
			
			pe = session.get(pe.getClass(), Integer.parseInt(frm.tf_idPersona.getText()));
			System.out.println(pe);
			
			
			frm.tf_nombre.setText(pe.getNombre());
			frm.tf_apellido.setText(pe.getApellido());
			frm.tf_fecha.setText(pe.getfechaNacimiento());
			
			session.getTransaction().commit();
			
			return true;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}


	