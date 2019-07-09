package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;

import Modelo.Persona;
import Vista.frameConsultas;
import Vista.framePersona;

public class ControladorConsultas extends Conexion implements ActionListener {
	
	private DefaultTableModel model;
	private frameConsultas frm;
	
	public ControladorConsultas(DefaultTableModel model,  frameConsultas frmC)
	{
		this.model = model;
		
		frm = frmC;
		
		this.frm.bt_mostrarPersonas.addActionListener(this);
		this.frm.bt_mostrarUsuarios.addActionListener(this);
		
	}
	
	public void iniciar()
	{
		frm.setTitle("Consultas");
		frm.setLocationRelativeTo(null);
		frm.setVisible(false);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == frm.bt_mostrarPersonas)
		{
			//Instancia de model
			String indicesPersonas[] = {"idPersona", "Nombre", "Apellido", "Fecha de Nacimiento"};
			String data[][] = {};
			model = new DefaultTableModel(data, indicesPersonas);
			
			if(selectPersonasMYSQLConnector(model))
			{
				frm.t.setModel(model);		
			}
				
			else
				JOptionPane.showMessageDialog(null, "No se encontro registro");
		}
		
		if(e.getSource() == frm.bt_mostrarUsuarios)
		{
			//Instancia de model
			String indicesUsuarios[] = {"idUsuario","usuario","contraseña","idPersona", "Nombre", "Apellido", "Fecha de Nacimiento"};
			String data[][] = {};
			model = new DefaultTableModel(data, indicesUsuarios);
			
			if(selectUsuariosMYSQLConnector(model))
			{
				frm.t.setModel(model);		
			}
				
			else
				JOptionPane.showMessageDialog(null, "No se encontro registro");
		}
	}
	
	public boolean selectPersonas(DefaultTableModel model) {
		Session session = buildSession(Persona.class).getCurrentSession();

		try {
			session.beginTransaction();
			List<Persona> list = session
					.createQuery(
							"from usuario RIGHT JOIN persona ON usuario.idPersona = usuario.idPersona WHERE usuario.idPersona is NULL")
					.getResultList();

			for (Persona p : list) {
				Object datos[] = { p.getIdPersona(), p.getNombre(), p.getNombre(), p.getfechaNacimiento() };
				model.addRow(datos);
			}

			return true;

		} catch (Exception e) {
			// TODO: handle exception
			return false;
					
		}

	}
	
	public boolean selectPersonasMYSQLConnector(DefaultTableModel model)
	{
		String base = "sistema";
		String user = "root";
		String password = "0123";
		String url = "jdbc:mysql://localhost:3306/sistema?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		
		PreparedStatement ps = null;
		
		String sql = "SELECT * from usuario RIGHT JOIN persona ON usuario.idPersona = persona.idPersona WHERE usuario.idPersona is NULL";
		ResultSet rs = null;
		Connection con = null;
		try {
			con =(Connection)DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			//se usa ps.executeQuery() para que guarde solo el res
			rs = ps.executeQuery();
			//en rs se guarda el res de la query
			
			
			String idPersona, nombre, apellido, fecha;
			
			while(rs.next())
			{
				idPersona = rs.getString("idPersona");
				nombre = rs.getString("nombre");
				apellido = rs.getString("apellido");
				fecha = rs.getString("fechaNacimiento");
				
				Object datos[]={idPersona, nombre, apellido, fecha};
				model.addRow(datos);
			}
			
			return true;
			
			
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				System.err.println(e2);
			}
		
		}
	}
	
	public boolean selectUsuarios(DefaultTableModel model) {
		Session session = buildSession(Persona.class).getCurrentSession();

		try {
			List<Persona> list = session
					.createQuery(
							"FROM sistema.usuario INNER JOIN sistema.persona WHERE sistema.usuario.idPersona = sistema.persona.idPersona")
					.list();

			for (Persona p : list) {
				Object datos[] = { p.getIdPersona(), p.getNombre(), p.getNombre(), p.getfechaNacimiento() };
				model.addRow(datos);
			}

			return true;

		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			return false;
		}

	}
	
	public boolean selectUsuariosMYSQLConnector(DefaultTableModel model)
	{
		String base = "sistema";
		String user = "root";
		String password = "0123";
		String url = "jdbc:mysql://localhost:3306/sistema?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		
		PreparedStatement ps = null;
		
		String sql = "SELECT * FROM sistema.usuario INNER JOIN sistema.persona WHERE sistema.usuario.idPersona = sistema.persona.idPersona";
		ResultSet rs = null;
		Connection con = null;
		try {
			con =(Connection)DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			//se usa ps.executeQuery() para que guarde solo el res
			rs = ps.executeQuery();
			//en rs se guarda el res de la query
			
			
			String idUsuario, usuario, contrasenia, idPersona, nombre, apellido, fecha;
			
			while(rs.next())
			{
				idUsuario = rs.getString("idUsuario");
				usuario = rs.getString("usuario");
				contrasenia = rs.getString("contraseña");
				idPersona = rs.getString("idPersona");
				nombre = rs.getString("nombre");
				apellido = rs.getString("apellido");
				fecha = rs.getString("fechaNacimiento");
				
				Object datos[]={idUsuario,usuario,contrasenia, idPersona, nombre, apellido, fecha};
				model.addRow(datos);
			}
			
			return true;
			
			
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				System.err.println(e2);
			}
		
		}
	}
}
