import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MetalinkTask extends Task {
    private String url;
    private String file;
    private List<FileData> fileDataList;

    private List<FileSet> filesets = new ArrayList<>();

    public void setFile(String file) {
        this.file = file;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void addFileset(FileSet fileset) {
        filesets.add(fileset);
    }

    public List<FileData> getFileDataList() {
        return fileDataList;
    }

    public void setFileDataList(List<FileData> fileDataList) {
        this.fileDataList = fileDataList;
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
                FileData fileData = new FileData(fileName, fileUrl, file.length());
                log(fileData.getName());
                log(fileData.getUrl());
                fileDataList.add(fileData);
            }
        }
    }
}
