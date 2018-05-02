package de.rieckpil.learning.apachepoiword;

import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

import java.io.*;

public class XDocReportExample {

    public static void main(String[] args) throws Exception {
        InputStream in= new FileInputStream(new File("ODTHelloWordWithVelocity.odt"));
        IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in,TemplateEngineKind.Velocity);

        // 2) Create Java model context
        IContext context = report.createContext();
        context.put("name", "world");

        // 3) Generate report by merging Java model with the ODT
        OutputStream out = new FileOutputStream(new File("ODTHelloWordWithVelocity_Out.odt"));
        report.process(context, out);
    }
}
