import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class MetalinkTask extends Task {
    private String url;
    private String file;
    private List<FileData> fileDataList = new ArrayList<>();
    private MetalinkData metalink = new MetalinkData();

    private List<FileSet> filesets = new ArrayList<>();

    public void setFile(String file) {
        this.file = file;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<FileData> getFileDataList() {
        return fileDataList;
    }

    public void setFileDataList(List<FileData> fileDataList) {
        this.fileDataList = fileDataList;
    }

    public void addFileset(FileSet fileset) {
        filesets.add(fileset);
    }

    private void addMetalinkData(File file) throws NoSuchAlgorithmException, IOException {
//        String hashValue = MD5Util.createHash(file);
//        Hash hash = new Hash("MD5", hashValue);
        FileData fileData = new FileData(file);
        metalink.addToList(fileData);
    }

    @Override
    public void execute() throws BuildException {
        if (url.equals(null)) {
            throw new BuildException("No message set.");
        } else if (file.equals(null)) {
            throw new BuildException("No file set.");
        } else if (filesets.equals(null)) {
            throw new BuildException("No filesets set.");
        }

        log("Url: " + url);
        log("File: " + file);

        for (FileSet fs:filesets) {
            DirectoryScanner ds = fs.getDirectoryScanner(getProject());
            for (String fileName:ds.getIncludedFiles()) {
                File file = new File(fileName);
                String fileUrl = url + fileName;
                FileData fileData = new FileData(file.getName(), fileUrl, file.length());
                metalink.addToList(fileData);
            }
        }

        try {
            XMLCreator xmlCreator = new XMLCreator(metalink, new File(file));
            xmlCreator.create();
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new BuildException("Could not save file!");
        }
    }
}
