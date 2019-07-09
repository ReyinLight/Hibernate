package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Conexion {
	
	public SessionFactory buildSession(Class clase)
	{
		return new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(clase)
				.buildSessionFactory();
	}
}
