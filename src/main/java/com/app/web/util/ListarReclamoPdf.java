package com.app.web.util;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.app.web.modelo.Reclamo;
import com.app.web.modelo.Trabajador;

@Component("/moduloReclamo/listarTodo")
public class ListarReclamoPdf extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<Reclamo> listadoReclamo = (List<Reclamo>) model.get("listaReclamo");
		
		/*Fuentes, tamaños y colores para cada seccion*/
		Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD,20,Color.WHITE);
		Font fuenteTituloColumnas = FontFactory.getFont(FontFactory.HELVETICA_BOLD ,12,Color.BLACK);
		Font fuenteDataCeldas = FontFactory.getFont(FontFactory.COURIER ,10,Color.BLACK);
		
		document.setPageSize(PageSize.LETTER.rotate());
		document.setMargins(-20, -20, 30, 20);
		document.open();
		PdfPCell celda = null;
		
		/*Tabla Para El Titulo del PDF*/
		PdfPTable tablaTitulo = new PdfPTable(1);
						
		celda = new PdfPCell(new Phrase("LISTADO GENERAL DE TRABAJADORES", fuenteTitulo));
		celda.setBorder(0);
		celda.setBackgroundColor(new Color(0,0,0));
		celda.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
		celda.setPadding(30);
		
		tablaTitulo.addCell(celda);
		tablaTitulo.setSpacingAfter(30);
		
		/*Tabla Para Mostrar Listado de Clientes*/
		PdfPTable tablaProductos = new PdfPTable(4);
//		tablaProductos.setWidths(new float[] {0.5f, 2f, 1.5f, 1.1f, 2.5f, 2.3f});
		tablaProductos.setWidths(new float[] {1f, 2.5f, 2.5f, 3.4f});
		celda = new PdfPCell(new Phrase("ID", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaProductos.addCell(celda);
		
		celda = new PdfPCell(new Phrase("CORREO", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaProductos.addCell(celda);
		
		celda = new PdfPCell(new Phrase("ASUNTO", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaProductos.addCell(celda);
		
		celda = new PdfPCell(new Phrase("DESCRIPCION", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaProductos.addCell(celda);
	
		
													
		/*Bucle For, mostrar todos los datos de los clientes*/		
		
		for (Reclamo reclamo: listadoReclamo) {
			celda = new PdfPCell(new Phrase(reclamo.getIdReclamo().toString(), fuenteDataCeldas));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5);
			tablaProductos.addCell(celda);
			
			celda = new PdfPCell(new Phrase(reclamo.getCorreo(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaProductos.addCell(celda);
			
			celda = new PdfPCell(new Phrase(reclamo.getAsunto(), fuenteDataCeldas));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5);
			tablaProductos.addCell(celda);
			
			celda = new PdfPCell(new Phrase(reclamo.getDescripcion(), fuenteDataCeldas));
			
			celda.setPadding(5);
			tablaProductos.addCell(celda);	
			
		}
		
		document.add(tablaTitulo);
		document.add(tablaProductos);
	}
	
}