import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

import javax.ejb.Stateless;
import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Stateless(name = "ConverterEJB")
public class ConverterBean implements  IConverter{
    public ConverterBean() {
    }

    @Override
    public double euroToOtherCurrency(double amount, String currencyCode) throws IOException, JDOMException {

        SAXBuilder sxb = new SAXBuilder();
        URL url = new URL("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        Document document = sxb.build(connection.getInputStream());
        Element racine = document.getRootElement();
        Namespace ns = Namespace.getNamespace("https://www.ecb.int/vocabulary/2002-08-01/eurofxref");
        Element firstCube = racine.getChild("Cube", ns);

        Element secondeCube = firstCube.getChild("Cube", ns);

        List<Element> othersCurrency = secondeCube.getChildren();

        for(Element currency : othersCurrency){
            if(currency.getAttribute("currency").getValue().equals(currencyCode)){
                return currency.getAttribute("rate").getDoubleValue() * amount;
            }
        }

        return amount;
    }
}
