import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "metalink")
@XmlAccessorType(XmlAccessType.FIELD)
class MetalinkData {
    @XmlElement(name = "published")
    Date date;
    @XmlElement(name = "file")
    List<FileData> files;

    MetalinkData() {
        date = new Date();
        files = new ArrayList<>();
    }

    public void addToList(FileData data) {
        files.add(data);
    }
}