package com.example.minimarket2.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.minimarket2.entity.Producto;
import com.example.minimarket2.repository.ProductoRepository;
import com.example.minimarket2.service.ProductoService;
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
public class ProductoServiceimpl implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Producto> findAll() throws Exception {

		return productoRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Producto> findById(Integer id) throws Exception {

		return productoRepository.findById(id);
	}

	@Transactional
	@Override
	public Producto save(Producto entity) throws Exception {

		return productoRepository.save(entity);
	}

	@Transactional
	@Override
	public Producto update(Producto entity) throws Exception {

		return productoRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		productoRepository.deleteById(id);

	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		productoRepository.deleteAll();

	}

	@Override
	public Producto findFirstByCodigo(String codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createPdf(List<Producto> productos, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		Document document = new Document(PageSize.A4, 15, 15, 45, 30);
		try {
			String filePath = context.getRealPath("/resources/reports");
			File file = new File(filePath);
			boolean exists = new File(filePath).exists();
			if (!exists) {
				new File(filePath).mkdirs();
			}

			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file + "/" + "productos" + ".pdf"));
			document.open();
			Font mainFont = FontFactory.getFont("Arial", 10, BaseColor.BLACK);

			Paragraph paragraph = new Paragraph("Productos con bajo Stock", mainFont);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setIndentationLeft(50);
			paragraph.setIndentationRight(50);
			paragraph.setSpacingAfter(10);
			document.add(paragraph);

			PdfPTable table = new PdfPTable(6);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10);

			Font tableHeader = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
			Font tableBody = FontFactory.getFont("Arial", 9, BaseColor.BLACK);

			float[] columnWidths = { 2f, 2f, 2f, 2f, 2f, 2f };
			table.setWidths(columnWidths);

			PdfPCell id = new PdfPCell(new Paragraph("Id", tableHeader));
			id.setBorderColor(BaseColor.BLACK);
			id.setPaddingLeft(10);
			id.setHorizontalAlignment(Element.ALIGN_CENTER);
			id.setVerticalAlignment(Element.ALIGN_CENTER);
			id.setBackgroundColor(BaseColor.GRAY);
			id.setExtraParagraphSpace(5f);
			table.addCell(id);

			PdfPCell nombre = new PdfPCell(new Paragraph("Nombre", tableHeader));
			nombre.setBorderColor(BaseColor.BLACK);
			nombre.setPaddingLeft(10);
			nombre.setHorizontalAlignment(Element.ALIGN_CENTER);
			nombre.setVerticalAlignment(Element.ALIGN_CENTER);
			nombre.setBackgroundColor(BaseColor.GRAY);
			nombre.setExtraParagraphSpace(5f);
			table.addCell(nombre);

			PdfPCell codigo = new PdfPCell(new Paragraph("Codigo", tableHeader));
			codigo.setBorderColor(BaseColor.BLACK);
			codigo.setPaddingLeft(10);
			codigo.setHorizontalAlignment(Element.ALIGN_CENTER);
			codigo.setVerticalAlignment(Element.ALIGN_CENTER);
			codigo.setBackgroundColor(BaseColor.GRAY);
			codigo.setExtraParagraphSpace(5f);
			table.addCell(codigo);

			PdfPCell precio = new PdfPCell(new Paragraph("Precio", tableHeader));
			precio.setBorderColor(BaseColor.BLACK);
			precio.setPaddingLeft(10);
			precio.setHorizontalAlignment(Element.ALIGN_CENTER);
			precio.setVerticalAlignment(Element.ALIGN_CENTER);
			precio.setBackgroundColor(BaseColor.GRAY);
			precio.setExtraParagraphSpace(5f);
			table.addCell(precio);

			PdfPCell stock = new PdfPCell(new Paragraph("Stock", tableHeader));
			stock.setBorderColor(BaseColor.BLACK);
			stock.setPaddingLeft(10);
			stock.setHorizontalAlignment(Element.ALIGN_CENTER);
			stock.setVerticalAlignment(Element.ALIGN_CENTER);
			stock.setBackgroundColor(BaseColor.GRAY);
			stock.setExtraParagraphSpace(5f);
			table.addCell(stock);

			PdfPCell categoria = new PdfPCell(new Paragraph("Categoria", tableHeader));
			categoria.setBorderColor(BaseColor.BLACK);
			categoria.setPaddingLeft(10);
			categoria.setHorizontalAlignment(Element.ALIGN_CENTER);
			categoria.setVerticalAlignment(Element.ALIGN_CENTER);
			categoria.setBackgroundColor(BaseColor.GRAY);
			categoria.setExtraParagraphSpace(5f);
			table.addCell(categoria);

			for (Producto producto : productos) {
				if (producto.getStock() < 5) {
					PdfPCell idValue = new PdfPCell(new Paragraph(producto.getId() + "", tableBody));
					idValue.setBorderColor(BaseColor.BLACK);
					idValue.setPaddingLeft(10);
					idValue.setHorizontalAlignment(Element.ALIGN_CENTER);
					idValue.setVerticalAlignment(Element.ALIGN_CENTER);
					idValue.setBackgroundColor(BaseColor.WHITE);
					idValue.setExtraParagraphSpace(5f);
					table.addCell(idValue);

					PdfPCell nombreValue = new PdfPCell(new Paragraph(producto.getNombre(), tableBody));
					nombreValue.setBorderColor(BaseColor.BLACK);
					nombreValue.setPaddingLeft(10);
					nombreValue.setHorizontalAlignment(Element.ALIGN_CENTER);
					nombreValue.setVerticalAlignment(Element.ALIGN_CENTER);
					nombreValue.setBackgroundColor(BaseColor.WHITE);
					nombreValue.setExtraParagraphSpace(5f);
					table.addCell(nombreValue);

					PdfPCell codigoValue = new PdfPCell(new Paragraph(producto.getCodigo(), tableBody));
					codigoValue.setBorderColor(BaseColor.BLACK);
					codigoValue.setPaddingLeft(10);
					codigoValue.setHorizontalAlignment(Element.ALIGN_CENTER);
					codigoValue.setVerticalAlignment(Element.ALIGN_CENTER);
					codigoValue.setBackgroundColor(BaseColor.WHITE);
					codigoValue.setExtraParagraphSpace(5f);
					table.addCell(codigoValue);

					PdfPCell precioValue = new PdfPCell(new Paragraph(producto.getPrecio() + "", tableBody));
					precioValue.setBorderColor(BaseColor.BLACK);
					precioValue.setPaddingLeft(10);
					precioValue.setHorizontalAlignment(Element.ALIGN_CENTER);
					precioValue.setVerticalAlignment(Element.ALIGN_CENTER);
					precioValue.setBackgroundColor(BaseColor.WHITE);
					precioValue.setExtraParagraphSpace(5f);
					table.addCell(precioValue);

					PdfPCell stockValue = new PdfPCell(new Paragraph(producto.getStock() + "", tableBody));
					stockValue.setBorderColor(BaseColor.BLACK);
					stockValue.setPaddingLeft(10);
					stockValue.setHorizontalAlignment(Element.ALIGN_CENTER);
					stockValue.setVerticalAlignment(Element.ALIGN_CENTER);
					stockValue.setBackgroundColor(BaseColor.WHITE);
					stockValue.setExtraParagraphSpace(5f);
					table.addCell(stockValue);

					PdfPCell categoriaValue = new PdfPCell(
							new Paragraph(producto.getCategoria().getNombre(), tableBody));
					categoriaValue.setBorderColor(BaseColor.BLACK);
					categoriaValue.setPaddingLeft(10);
					categoriaValue.setHorizontalAlignment(Element.ALIGN_CENTER);
					categoriaValue.setVerticalAlignment(Element.ALIGN_CENTER);
					categoriaValue.setBackgroundColor(BaseColor.WHITE);
					categoriaValue.setExtraParagraphSpace(5f);
					table.addCell(categoriaValue);
				}

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
	public List<Producto> listaproductosmayor() throws Exception {
		return productoRepository.listaproductosmayor();
	}

}
