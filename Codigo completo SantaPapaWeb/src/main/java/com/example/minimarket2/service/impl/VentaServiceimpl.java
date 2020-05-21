package com.example.minimarket2.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.minimarket2.entity.ProductoVendido;
import com.example.minimarket2.entity.Usuario;
import com.example.minimarket2.entity.Venta;
import com.example.minimarket2.repository.VentaRepository;
import com.example.minimarket2.service.UsuarioService;
import com.example.minimarket2.service.VentaService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class VentaServiceimpl implements VentaService{

	@Autowired
	VentaRepository ventaRepository;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Transactional(readOnly = true)
	@Override
	public List<Venta> findAll() throws Exception {
		return ventaRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Venta> findById(Integer id) throws Exception {
		return ventaRepository.findById(id);
	}

	@Transactional
	@Override
	public Venta save(Venta entity) throws Exception {
		return ventaRepository.save(entity);
	}

	@Transactional
	@Override
	public Venta update(Venta entity) throws Exception {
		return ventaRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		ventaRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		ventaRepository.deleteAll();
	}

	@Override
	public boolean createPdf(List<Venta> ventas, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		Document document = new Document(PageSize.A4,15,15,45,30);
		try {
			String filePath = context.getRealPath("/resources/reports");
			File file = new File (filePath);
			boolean exists= new File(filePath).exists();
			if(!exists) {
				new File (filePath).mkdirs();
			}
			
			PdfWriter writer= PdfWriter.getInstance(document, new FileOutputStream(file+"/"+"ventas"+".pdf"));
			document.open();
			Font mainFont = FontFactory.getFont("Arial",10,BaseColor.BLACK);
			
			Paragraph paragraph = new Paragraph("Ventas",mainFont);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setIndentationLeft(50);
			paragraph.setIndentationRight(50);
			paragraph.setSpacingAfter(10);
			document.add(paragraph);
			
			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10);
			
			Font tableHeader = FontFactory.getFont("Arial",10,BaseColor.BLACK);
			Font tableBody = FontFactory.getFont("Arial",9,BaseColor.BLACK);
			
			float[] columnWidths = {2f,2f,2f};
			table.setWidths(columnWidths);
			
			PdfPCell id = new PdfPCell(new Paragraph("Id",tableHeader));
			id.setBorderColor(BaseColor.BLACK);
			id.setPaddingLeft(10);
			id.setHorizontalAlignment(Element.ALIGN_CENTER);
			id.setVerticalAlignment(Element.ALIGN_CENTER);
			id.setBackgroundColor(BaseColor.GRAY);
			id.setExtraParagraphSpace(5f);
			table.addCell(id);
			
			PdfPCell fechaYHora = new PdfPCell(new Paragraph("fechaYHora",tableHeader));
			fechaYHora.setBorderColor(BaseColor.BLACK);
			fechaYHora.setPaddingLeft(10);
			fechaYHora.setHorizontalAlignment(Element.ALIGN_CENTER);
			fechaYHora.setVerticalAlignment(Element.ALIGN_CENTER);
			fechaYHora.setBackgroundColor(BaseColor.GRAY);
			fechaYHora.setExtraParagraphSpace(5f);
			table.addCell(fechaYHora); 
			
			PdfPCell total = new PdfPCell(new Paragraph("Total",tableHeader));
			total.setBorderColor(BaseColor.BLACK);
			total.setPaddingLeft(10);
			total.setHorizontalAlignment(Element.ALIGN_CENTER);
			total.setVerticalAlignment(Element.ALIGN_CENTER);
			total.setBackgroundColor(BaseColor.GRAY);
			total.setExtraParagraphSpace(5f);
			table.addCell(total); 
			
			for(Venta venta : ventas) {
				PdfPCell idValue = new PdfPCell(new Paragraph(venta.getId()+"",tableBody));
				idValue.setBorderColor(BaseColor.BLACK);
				idValue.setPaddingLeft(10);
				idValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				idValue.setVerticalAlignment(Element.ALIGN_CENTER);
				idValue.setBackgroundColor(BaseColor.WHITE);
				idValue.setExtraParagraphSpace(5f);
				table.addCell(idValue);
				
				PdfPCell fechaYHoraValue = new PdfPCell(new Paragraph(venta.getFechaYHora(),tableBody));
				fechaYHoraValue.setBorderColor(BaseColor.BLACK);
				fechaYHoraValue.setPaddingLeft(10);
				fechaYHoraValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				fechaYHoraValue.setVerticalAlignment(Element.ALIGN_CENTER);
				fechaYHoraValue.setBackgroundColor(BaseColor.WHITE);
				fechaYHoraValue.setExtraParagraphSpace(5f);
				table.addCell(fechaYHoraValue);
				
				PdfPCell totalValue = new PdfPCell(new Paragraph(venta.getTotal()+"",tableBody));
				totalValue.setBorderColor(BaseColor.BLACK);
				totalValue.setPaddingLeft(10);
				totalValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				totalValue.setVerticalAlignment(Element.ALIGN_CENTER);
				totalValue.setBackgroundColor(BaseColor.WHITE);
				totalValue.setExtraParagraphSpace(5f);
				table.addCell(totalValue); 
				
			}
			document.add(table);
			document.close();
			return true;
			
		} catch (Exception e) {
			System.out.println("Error");
			return false;
		}
	}

	@Override
	public boolean createPdfCom(Venta venta, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		Document document = new Document(PageSize.A4,15,15,45,30);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		try {
			String filePath = context.getRealPath("/resources/reports");
			File file = new File (filePath);
			boolean exists= new File(filePath).exists();
			if(!exists) {
				new File (filePath).mkdirs();
			}
			
			Optional<Usuario> usuario = usuarioService.findByUsername(username);
			
			PdfWriter writer= PdfWriter.getInstance(document, new FileOutputStream(file + "/" + "recibo" + ".pdf"));
			document.open();
			Font mainFont = FontFactory.getFont("Arial",10,BaseColor.BLACK);
			Font mainFont2 = FontFactory.getFont("Arial",20,BaseColor.BLACK);
			Font mainFont3 = FontFactory.getFont("Arial",15,BaseColor.BLACK);
			Font mainFont4 = FontFactory.getFont("Arial",8,BaseColor.BLACK);
			
			Paragraph paragraph12 = new Paragraph("Minimarket Beto",mainFont);
			paragraph12.setAlignment(Element.ALIGN_LEFT);
			paragraph12.setIndentationLeft(50);
			paragraph12.setIndentationRight(50);
			paragraph12.setSpacingAfter(10);
			document.add(paragraph12);
			
			Paragraph paragraph13 = new Paragraph("Jr. Huanuco 3900",mainFont4);
			paragraph13.setAlignment(Element.ALIGN_LEFT);
			paragraph13.setIndentationLeft(50);
			paragraph13.setIndentationRight(50);
			paragraph13.setSpacingAfter(10);
			document.add(paragraph13);
			
			Paragraph paragraph14 = new Paragraph("Telefono: +51 123456789",mainFont4);
			paragraph14.setAlignment(Element.ALIGN_LEFT);
			paragraph14.setIndentationLeft(50);
			paragraph14.setIndentationRight(50);
			paragraph14.setSpacingAfter(10);
			document.add(paragraph13);
			
			Paragraph paragraph = new Paragraph("Recibo",mainFont2);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setIndentationLeft(50);
			paragraph.setIndentationRight(50);
			paragraph.setSpacingAfter(10);
			document.add(paragraph);
			
			Paragraph paragraph8 = new Paragraph("Recibo N°: 001 - "+venta.getId(),mainFont3);
			paragraph8.setAlignment(Element.ALIGN_RIGHT);
			paragraph8.setIndentationLeft(50);
			paragraph8.setIndentationRight(50);
			paragraph8.setSpacingAfter(10);
			document.add(paragraph8);
			
			Paragraph paragraph9 = new Paragraph("Fecha de emisión: "+venta.getFechaYHora(),mainFont);
			paragraph9.setAlignment(Element.ALIGN_RIGHT);
			paragraph9.setIndentationLeft(50);
			paragraph9.setIndentationRight(50);
			paragraph9.setSpacingAfter(10);
			document.add(paragraph9);
			
			Paragraph paragraph2 = new Paragraph("Datos del cliente: ",mainFont);
			paragraph2.setAlignment(Element.ALIGN_LEFT);
			paragraph2.setIndentationLeft(50);
			paragraph2.setIndentationRight(50);
			paragraph2.setSpacingAfter(10);
			document.add(paragraph2);
			
			Paragraph paragraph3 = new Paragraph("Nombre: "+usuario.get().getNombre(),mainFont);
			paragraph3.setAlignment(Element.ALIGN_LEFT);
			paragraph3.setIndentationLeft(50);
			paragraph3.setIndentationRight(50);
			paragraph3.setSpacingAfter(10);
			document.add(paragraph3);
			
			Paragraph paragraph4 = new Paragraph("Apellido: "+usuario.get().getApellido(),mainFont);
			paragraph4.setAlignment(Element.ALIGN_LEFT);
			paragraph4.setIndentationLeft(50);
			paragraph4.setIndentationRight(50);
			paragraph4.setSpacingAfter(10);
			document.add(paragraph4);
			
			Paragraph paragraph5 = new Paragraph("Direccion: "+usuario.get().getDireccion(),mainFont);
			paragraph5.setAlignment(Element.ALIGN_LEFT);
			paragraph5.setIndentationLeft(50);
			paragraph5.setIndentationRight(50);
			paragraph5.setSpacingAfter(10);
			document.add(paragraph5);
			
			Paragraph paragraph6 = new Paragraph("Telefono: "+usuario.get().getTelefono(),mainFont);
			paragraph6.setAlignment(Element.ALIGN_LEFT);
			paragraph6.setIndentationLeft(50);
			paragraph6.setIndentationRight(50);
			paragraph6.setSpacingAfter(10);
			document.add(paragraph6);
			
			Paragraph paragraph7 = new Paragraph("Correo: "+usuario.get().getCorreo(),mainFont);
			paragraph7.setAlignment(Element.ALIGN_LEFT);
			paragraph7.setIndentationLeft(50);
			paragraph7.setIndentationRight(50);
			paragraph7.setSpacingAfter(10);
			document.add(paragraph7);
			
			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10);
			
			Font tableHeader = FontFactory.getFont("Arial",10,BaseColor.BLACK);
			Font tableBody = FontFactory.getFont("Arial",9,BaseColor.BLACK);
			
			float[] columnWidths = {2f,2f,2f,2f};
			table.setWidths(columnWidths);
			
			PdfPCell nombre = new PdfPCell(new Paragraph("Descripcion",tableHeader));
			nombre.setBorderColor(BaseColor.BLACK);
			nombre.setPaddingLeft(10);
			nombre.setHorizontalAlignment(Element.ALIGN_CENTER);
			nombre.setVerticalAlignment(Element.ALIGN_CENTER);
			nombre.setBackgroundColor(BaseColor.RED);
			nombre.setExtraParagraphSpace(5f);
			table.addCell(nombre); 
			
			PdfPCell cantidad = new PdfPCell(new Paragraph("Unidades",tableHeader));
			cantidad.setBorderColor(BaseColor.BLACK);
			cantidad.setPaddingLeft(10);
			cantidad.setHorizontalAlignment(Element.ALIGN_CENTER);
			cantidad.setVerticalAlignment(Element.ALIGN_CENTER);
			cantidad.setBackgroundColor(BaseColor.RED);
			cantidad.setExtraParagraphSpace(5f);
			table.addCell(cantidad); 
			
			PdfPCell precio = new PdfPCell(new Paragraph("Precio Unitario",tableHeader));
			precio.setBorderColor(BaseColor.BLACK);
			precio.setPaddingLeft(10);
			precio.setHorizontalAlignment(Element.ALIGN_CENTER);
			precio.setVerticalAlignment(Element.ALIGN_CENTER);
			precio.setBackgroundColor(BaseColor.RED);
			precio.setExtraParagraphSpace(5f);
			table.addCell(precio); 
			
			PdfPCell total = new PdfPCell(new Paragraph("Total",tableHeader));
			total.setBorderColor(BaseColor.BLACK);
			total.setPaddingLeft(10);
			total.setHorizontalAlignment(Element.ALIGN_CENTER);
			total.setVerticalAlignment(Element.ALIGN_CENTER);
			total.setBackgroundColor(BaseColor.RED);
			total.setExtraParagraphSpace(5f);
			table.addCell(total); 
			
			for(ProductoVendido producto : venta.getProductos()) {

				PdfPCell nombreValue = new PdfPCell(new Paragraph(producto.getNombre(),tableBody));
				nombreValue.setBorderColor(BaseColor.BLACK);
				nombreValue.setPaddingLeft(10);
				nombreValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				nombreValue.setVerticalAlignment(Element.ALIGN_CENTER);
				nombreValue.setBackgroundColor(BaseColor.WHITE);
				nombreValue.setExtraParagraphSpace(5f);
				table.addCell(nombreValue);
				
				PdfPCell cantidadValue = new PdfPCell(new Paragraph(producto.getCantidad()+"",tableBody));
				cantidadValue.setBorderColor(BaseColor.BLACK);
				cantidadValue.setPaddingLeft(10);
				cantidadValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				cantidadValue.setVerticalAlignment(Element.ALIGN_CENTER);
				cantidadValue.setBackgroundColor(BaseColor.WHITE);
				cantidadValue.setExtraParagraphSpace(5f);
				table.addCell(cantidadValue); 
				
				PdfPCell precioValue = new PdfPCell(new Paragraph(producto.getPrecio()+"",tableBody));
				precioValue.setBorderColor(BaseColor.BLACK);
				precioValue.setPaddingLeft(10);
				precioValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				precioValue.setVerticalAlignment(Element.ALIGN_CENTER);
				precioValue.setBackgroundColor(BaseColor.WHITE);
				precioValue.setExtraParagraphSpace(5f);
				table.addCell(precioValue); 
				
				PdfPCell totalValue = new PdfPCell(new Paragraph(producto.getTotal()+"",tableBody));
				totalValue.setBorderColor(BaseColor.BLACK);
				totalValue.setPaddingLeft(10);
				totalValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				totalValue.setVerticalAlignment(Element.ALIGN_CENTER);
				totalValue.setBackgroundColor(BaseColor.WHITE);
				totalValue.setExtraParagraphSpace(5f);
				table.addCell(totalValue); 
			}
			
			document.add(table);
			
			Paragraph paragraph10 = new Paragraph("Total: "+venta.getTotal(),mainFont);
			paragraph10.setAlignment(Element.ALIGN_RIGHT);
			paragraph10.setIndentationLeft(50);
			paragraph10.setIndentationRight(50);
			paragraph10.setSpacingAfter(10);
			document.add(paragraph10);
			
			document.close();
			return true;
			
		} catch (Exception e) {
			System.out.println("Error");
			return false;
		}
	}

}
