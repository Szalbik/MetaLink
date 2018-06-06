import java.io.File;
import javax.xml.bind.*;

public class XMLCreator {
    public File file;
    public MetalinkData metalink;

    public XMLCreator(MetalinkData metalink, File file) {
        this.file = file;
        this.metalink = metalink;
    }

    public void create() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(metalink.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(metalink, file);
    }
}
