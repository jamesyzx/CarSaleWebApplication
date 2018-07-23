package com.me.yzx.pdf;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.me.yzx.pojo.User;



public class PdfReportView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document pdfdoc, PdfWriter pdfwriter,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//model was added to the scope by the Controller
		User user = (User) model.get("user");
		pdfdoc.add(new Paragraph("Congratulation!!"));
		pdfdoc.add(new Paragraph(user.getUsername()+""+"   buy a car" ));
		pdfdoc.add(new Paragraph("You will get email after the manager agree with the order" ));
		
	}

}
