import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FileData {
    @XmlAttribute(name = "name")
    private String name;

    private long size;
    private String url;

    public FileData() {}

    public FileData(String name, String url, long size) {
        this.name = name;
        this.size = size;
        this.url = url;
    }

    public FileData(File file) {
        this.name = file.getName();
        this.url = file.getAbsolutePath();
        this.size = file.length();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
