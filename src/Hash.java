import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Hash {
    @XmlAttribute(name = "type")
    public String type;
    @XmlValue
    public String value;

    Hash() {}

    Hash(String type, String value) {
        this.type = type;
        this.value = value;
    }
}