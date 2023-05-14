/**
 * 
 */
package co.edu.uniquindio.storify.persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import co.edu.uniquindio.storify.models.Persona;
import co.edu.uniquindio.storify.models.Tienda;
import co.edu.uniquindio.storify.models.Usuario;



/**
 * @author GonzalezHDanielaA
 *
 */
public class Persistencia {
	
	
	//public static final String RUTA_ARCHIVO_EMPLEADOS = "src/resources/archivoEmpleados.txt";
		
		public static final String RUTA_ARCHIVO_LOG = "C://td//persistenciamusic//log//SubastaQuindioLog.txt";
		public static final String RUTA_ARCHIVO_USUARIOS = "C://td//persistenciamusic//Archivos//";
		public static final String RUTA_ARCHIVO_OBJETOS = "src/resources/archivoObjetos.txt";
		public static final String RUTA_ARCHIVO_MODELO_TIENDA_BINARIO = "C://td//persistenciamusic//usuarioBin.dat";
		public static final String RUTA_ARCHIVO_MODELO_TIENDASTORIFY_XML = "C://td//persistenciamusic//model.xml";
		
		public static final String RUTA_ARCHIVO_COPIA_ORIGEN_GENERAL = "C://td//persistencia//Archivos//";
		public static final String RUTA_ARCHIVO_COPIA_DESTINO_GENERAL = "C://td//persistencia//respaldo//";
		
		public static final String RUTA_ARCHIVO_COPIA_ORIGEN_GENERAL_SERIAL = "C://td//persistencia//";	
		
		public static final String SEPARADOR = "@@";
		
		public static final String RUTA_ARCHIVO_PRODUCTOS = "C://td//persistencia//Archivos//archivoProducto.txt";
		
		public static final String RUTA_ARCHIVO_PUJAS = "C://td//persistencia//Archivos//archivoPujas.txt";
		

		
		
		public static void guardarRegistroLog(String mensajeLog, int nivel, String accion)
		{
			//ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
		}
		
		public static Usuario obtenerUsuario(String usuario, String contrasenia) throws FileNotFoundException, IOException 
		{
			Tienda tienda = Persistencia.cargarRecursoSubastaQuindioXML();
			/*ArrayList<Usuario> usuarios =  tienda.getListaUsuarios();
			Usuario usuarioAux;
			for (int indiceUsuario = 0; indiceUsuario < usuarios.size(); indiceUsuario++) 
			{
				usuarioAux = usuarios.get(indiceUsuario);
				if(usuarioAux.getUsuario().equalsIgnoreCase(usuario) && usuarioAux.getContrasenia().equalsIgnoreCase(contrasenia)) {
					return usuarioAux;
				}
			}
			return usuarioAux;*/
			return null;
		}
		
		/**
		 * Guarda en un archivo de texto todos la información de las personas
		 * almacenadas en el ArrayList
		 * @param listaUsuario
		 * @throws IOException
		 */
		public static void guardarUsuarios(ArrayList<Persona> listaUsuario ) throws IOException{
			
			String contenido = "";
			
			for(Persona persona: listaUsuario)
			{
				contenido+= persona.getCedula()+SEPARADOR+persona.getNombre()+SEPARADOR+persona.getEdad()
				+SEPARADOR+persona.getUsuario()+SEPARADOR+persona.getContrasenia()+"\n";
			}
			ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_USUARIOS, contenido, true);
		}
		
		
		
		public static Tienda cargarRecursoTiendaBinario() {
			Tienda tienda = null;
			try {
				tienda =  (Tienda) ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_TIENDA_BINARIO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return tienda;
		}
		
		/**
		 * 
		 * @param subasta
		 */
		public static void guardarResourcetiendaXML(Tienda tienda)
		{
			
			try {
				ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_TIENDASTORIFY_XML, tienda);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static Tienda cargarRecursoSubastaQuindioXML() {
			Tienda tienda = null;
			
			try {
				tienda = (Tienda) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_TIENDASTORIFY_XML);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return tienda;
		}
	
		
		

}
