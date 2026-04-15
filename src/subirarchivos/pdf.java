package intranet;

import static intranet.conexion.AbrirDB;
import static intranet.conexion.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document; 
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Paragraph;

public class pdf {
    public void ITextHelloWorld() throws Exception{
        AbrirDB();
        /*class returnData{
            returnData a=new returnData();
            int dateCedula;

            returnData() {
                this.dateCedula = a.getDateCedula;
            }
        }*/
        Document document = new Document();

        String parrafo = "Usaremos este parrafo como ejemplo de lo sencillo que es el uso de la libreria, "
                         + "pues como podria observar en el codigo esta cadena no lleva saltos de linea, "
                         + "mismos que la libreria agrego.";
 
        String parrafo2 = "A su vez podemos agregar datos con una simple concatenación, como ejemplo ponemos"
                          + "la fecha del dia en el formato deseado: "
                          + ""
                          + " y como podemos ver tambien hacer uso de las diversas funciones de formateo de"
                          + "fecha y numeros, incluso formatos personalidades.";
 
        PdfWriter.getInstance(document, new FileOutputStream("recibo.pdf"));
        document.open();
        document.add(new Paragraph(parrafo));
        document.add(new Paragraph(parrafo2));
        document.close();
    }
    
    public static void main(String args[]){
        try{
            pdf p = new pdf();
            p.ITextHelloWorld();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
