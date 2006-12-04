
package org.apache.axis2.jaxws.sample.wrap.sei;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;


/**
 * This class was generated by the JAXWS SI.
 * JAX-WS RI 2.0_01-b15-fcs
 * Generated source version: 2.0
 * 
 */
@WebServiceClient(name = "DocLitWrapService", targetNamespace = "http://wrap.sample.test.org", wsdlLocation = "DocLit.wsdl")
public class DocLitWrapService
    extends Service
{

    private final static URL DOCLITWRAPSERVICE_WSDL_LOCATION;
    private static String wsdlLocation="/test/org/apache/axis2/jaxws/sample/wrap/META-INF/doclitwrap.wsdl";
    static {
        URL url = null;
        try {
        	try{
	        	String baseDir = new File(".").getCanonicalPath();
	        	wsdlLocation = new File(baseDir + wsdlLocation).getAbsolutePath();
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        	File file = new File(wsdlLocation);
        	url = file.toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        DOCLITWRAPSERVICE_WSDL_LOCATION = url;
    }

    public DocLitWrapService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DocLitWrapService() {
        super(DOCLITWRAPSERVICE_WSDL_LOCATION, new QName("http://wrap.sample.test.org", "DocLitWrapService"));
    }

    /**
     * 
     * @return
     *     returns DocLitWrap
     */
    @WebEndpoint(name = "DocLitWrapPort")
    public DocLitWrap getDocLitWrapPort() {
        return (DocLitWrap)super.getPort(new QName("http://wrap.sample.test.org", "DocLitWrapPort"), DocLitWrap.class);
    }

}
