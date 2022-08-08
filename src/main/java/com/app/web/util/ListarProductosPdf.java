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

import com.app.web.modelo.Producto;

@Component("/moduloProducto/listarTodo")
public class ListarProductosPdf extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<Producto> listadoProducto = (List<Producto>) model.get("listaProductos");
		
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
						
		celda = new PdfPCell(new Phrase("LISTADO GENERAL DE PRODUCTOS", fuenteTitulo));
		celda.setBorder(0);
		celda.setBackgroundColor(new Color(0,0,0));
		celda.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
		celda.setPadding(30);
		
		tablaTitulo.addCell(celda);
		tablaTitulo.setSpacingAfter(30);
		
		/*Tabla Para Mostrar Listado de Clientes*/
		PdfPTable tablaProductos = new PdfPTable(5);
//		tablaProductos.setWidths(new float[] {0.8f, 2f, 2f, 1.5f, 3.5f, 1.5f});		
		tablaProductos.setWidths(new float[] {0.6f, 3.5f, 2f, 2.2f, 1.5f});
		
		celda = new PdfPCell(new Phrase("ID", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaProductos.addCell(celda);
		
		celda = new PdfPCell(new Phrase("NOMBRE", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaProductos.addCell(celda);
		
		celda = new PdfPCell(new Phrase("STOCK", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaProductos.addCell(celda);
		
		celda = new PdfPCell(new Phrase("CATEGORIA", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaProductos.addCell(celda);
		
		celda = new PdfPCell(new Phrase("PRECIO", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaProductos.addCell(celda);
		
				
		
													
		/*Bucle For, mostrar todos los datos de los clientes*/		
		
		for (Producto producto: listadoProducto) {
			celda = new PdfPCell(new Phrase(producto.getIdProducto().toString(), fuenteDataCeldas));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5);
			tablaProductos.addCell(celda);
			
			celda = new PdfPCell(new Phrase(producto.getNombre(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaProductos.addCell(celda);
			
			celda = new PdfPCell(new Phrase(producto.getStock().toString(), fuenteDataCeldas));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5);
			tablaProductos.addCell(celda);
			
			celda = new PdfPCell(new Phrase(producto.getCategoria().getDescripcion(), fuenteDataCeldas));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5);
			tablaProductos.addCell(celda);
			
			celda = new PdfPCell(new Phrase("S/."+producto.getPrecio().toString(), fuenteDataCeldas));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5);
			tablaProductos.addCell(celda);
			
		}
		
		/*
		listadoClientes.forEach(cliente -> {												
			tablaProductos.addCell(cliente.getId().toString());					
			tablaProductos.addCell(cliente.getNombres());
			tablaProductos.addCell(cliente.getApellidos());
			tablaProductos.addCell(cliente.getTelefono());
			tablaProductos.addCell(cliente.getEmail());
			tablaProductos.addCell(cliente.getCiudad().getCiudad());
		});
		*/
		
		/*Anexamos las Tablas al Documento*/
		document.add(tablaTitulo);
		document.add(tablaProductos);
	}
	
	
}
