/**
 * 
 */
package co.edu.uniquindio.storify.persistence;



import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.regex.Pattern;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author GonzalezHDanielaA
 *
 */
public class ArchivoUtil {
	
	
	static String fechaSistema = "";
	/**
	 * Este metodo recibe una cadena con el contenido que se quiere guardar en el archivo
	 * @param ruta es la ruta o path donde esta ubicado el archivo
	 * @throws IOException 
	 */
	public static void guardarArchivo(String ruta,String contenido, Boolean flagAnexarContenido) throws IOException {
		
		FileWriter fw = new FileWriter(ruta,flagAnexarContenido);
		BufferedWriter bfw = new BufferedWriter(fw); 
		bfw.write(contenido);
		bfw.close();
		fw.close();
	}
	
	/**
	 * ESte metodo retorna el contendio del archivo ubicado en una ruta,con la lista de cadenas.
	 * @param ruta
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<String> leerArchivo(String ruta) throws IOException {

		ArrayList<String>  contenido = new ArrayList<String>();
		FileReader fr=new FileReader(ruta);
		BufferedReader bfr=new BufferedReader(fr);
		String linea="";	
		while((linea = bfr.readLine())!=null) 
		{
			contenido.add(linea);
		}
		bfr.close();
		fr.close();
		return contenido;
	}
	
	public static void guardarRegistroLog(String mensajeLog, int nivel, String accion, String rutaArchivo)
	{
		String log = "";
		Logger LOGGER = Logger.getLogger(accion);
		FileHandler fileHandler =  null;
		cargarFechaSistema();
		try {
			fileHandler = new FileHandler(rutaArchivo,true);
			fileHandler.setFormatter(new SimpleFormatter());
			LOGGER.addHandler(fileHandler);
			switch (nivel) {
			case 1:
				LOGGER.log(Level.INFO,accion+","+mensajeLog+","+fechaSistema) ;
				break;

			case 2:
				LOGGER.log(Level.WARNING,accion+","+mensajeLog+","+fechaSistema) ;
				break;

			case 3:
				LOGGER.log(Level.SEVERE,accion+","+mensajeLog+","+fechaSistema) ;
				break;

			default:
				break;
			}

		} catch (SecurityException e) {

			LOGGER.log(Level.SEVERE,e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE,e.getMessage());
			e.printStackTrace();
		}
		finally {
			
			fileHandler.close();
		}
	}
	
	
	
	private static void cargarFechaSistema() {

		String diaN = "";
		String mesN = "";
		String a�oN = "";

		Calendar cal1 = Calendar.getInstance();


		int  dia = cal1.get(Calendar.DATE);
		int mes = cal1.get(Calendar.MONTH)+1;
		int a�o = cal1.get(Calendar.YEAR);
		int hora = cal1.get(Calendar.HOUR);
		int minuto = cal1.get(Calendar.MINUTE);


		if(dia < 10){
			diaN+="0"+dia;
		}
		else{
			diaN+=""+dia;
		}
		if(mes < 10){
			mesN+="0"+mes;
		}
		else{
			mesN+=""+mes;
		}

		//		fecha_Actual+= a�o+"-"+mesN+"-"+ diaN;
		//		fechaSistema = a�o+"-"+mesN+"-"+diaN+"-"+hora+"-"+minuto;
		fechaSistema = a�o+"-"+mesN+"-"+diaN;
		//		horaFechaSistema = hora+"-"+minuto;
	}
	
	
	
	//------------------------------------SERIALIZACI�N  y XML
		/**
		 * Escribe en el fichero que se le pasa el objeto que se le envia
		 * 
		 * @param rutaArchivo
		 *            path del fichero que se quiere escribir
		 * @throws IOException
		 */
		
		@SuppressWarnings("unchecked")
		public static Object cargarRecursoSerializado(String rutaArchivo)throws Exception 
		{
			Object aux = null;
//			Empresa empresa = null;
			ObjectInputStream ois = null;
			try {
				// Se crea un ObjectInputStream
				ois = new ObjectInputStream(new FileInputStream(rutaArchivo));
				aux = ois.readObject();
			} catch (Exception e2) {
				throw e2;
			} finally {
				if (ois != null)
					ois.close();
			}
			return aux;
		}
	

		public static void salvarRecursoSerializado(String rutaArchivo, Object object)	throws Exception {
			ObjectOutputStream oos = null;
			try {
				oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo)); 
				oos.writeObject(object);
			} catch (Exception e) {
				throw e;
			} finally {
				if (oos != null)
					oos.close();
			}
		}
	
	

		public static Object cargarRecursoSerializadoXML(String rutaArchivo) throws IOException {

			XMLDecoder decodificadorXML;
			Object objetoXML;
			
			decodificadorXML = new XMLDecoder(new FileInputStream(rutaArchivo));
			objetoXML = decodificadorXML.readObject();
			decodificadorXML.close();
			return objetoXML;
			
		}

		public static void salvarRecursoSerializadoXML(String rutaArchivo,Object objeto) throws Exception
		{
			
			XMLEncoder codificadorXML=null;
			try {
				codificadorXML = new XMLEncoder(new FileOutputStream(rutaArchivo));
				codificadorXML.writeObject(objeto);
			}catch (Exception e) {
				throw e;
			} finally {
				if (codificadorXML != null)
					codificadorXML.close();
			}	
		}	
	/**
	 * 	
	 * @param origenArchivo
	 * @param destinoArchivo
	 */
		public static void hacerBackupArchivo(String origenArchivo, String destinoArchivo) {
            try {
                Path origenPath = Paths.get(origenArchivo);
                Path destinoPath = Paths.get(destinoArchivo);
                //sobreescribir el fichero de destino si existe y lo copia
                Files.copy(origenPath, destinoPath);
            } catch (FileNotFoundException ex) {                
                Persistencia.guardarRegistroLog(ex.getMessage(),3, "ArchivoUtil - hacerBackupArchivo ");
            } catch (IOException ex) {            	
            	Persistencia.guardarRegistroLog(ex.getMessage(),3, "ArchivoUtil - hacerBackupArchivo ");
            }
        }
		
		public static boolean searchPerson(String cedula) {
			boolean bandera=false;
			File archivo = null;
			FileReader fr = null;
			BufferedReader br = null;			
			try {
				archivo = new File(Persistencia.RUTA_ARCHIVO_USUARIOS);
				fr = new FileReader(archivo);
				br = new BufferedReader(fr);
				
				// Lectura del fichero
				String linea = "";
				String separador = Pattern.quote(Persistencia.SEPARADOR);
				while ((linea = br.readLine()) != null) {
					// System.out.println(linea);
					String lineaDividida[] = linea.split(separador);
					if(lineaDividida[0].equals(cedula)) {
						bandera= true;
					}				
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				// En el finally cerramos el fichero, para asegurarnos
				// que se cierra tanto si todo va bien como si salta
				// una excepcion.
				try {
					if (null != fr) {
						fr.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			return bandera;
		}
		
	public static boolean buscarProducto(String codigo)
	{
		boolean bandera=false;
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;			
		try {
			archivo = new File(Persistencia.RUTA_ARCHIVO_PRODUCTOS);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			// Lectura del fichero
			String linea = "";
			String separador = Pattern.quote(Persistencia.SEPARADOR);
			while ((linea = br.readLine()) != null) {
				// System.out.println(linea);
				String lineaDividida[] = linea.split(separador);
				if(lineaDividida[0].equals(codigo)) {
					bandera= true;
				}				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta
			// una excepcion.
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return bandera;
	}
		
	public static boolean buscarPuja(String codigo)
	{
		boolean bandera=false;
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;			
		try {
			archivo = new File(Persistencia.RUTA_ARCHIVO_PUJAS);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			// Lectura del fichero
			String linea = "";
			String separador = Pattern.quote(Persistencia.SEPARADOR);
			while ((linea = br.readLine()) != null) {
				// System.out.println(linea);
				String lineaDividida[] = linea.split(separador);
				if(lineaDividida[0].equals(codigo)) {
					bandera= true;
				}				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta
			// una excepcion.
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return bandera;
	}
			
		
		
}
