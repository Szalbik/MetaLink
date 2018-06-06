import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Hash {
    public static String createHash(File file) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        FileInputStream fs = new FileInputStream(file);
        BufferedInputStream bs = new BufferedInputStream(fs);
        byte[] buffer = new byte[1024];
        int bytesRead;

        while((bytesRead = bs.read(buffer, 0, buffer.length)) != -1){
            md.update(buffer, 0, bytesRead);
        }
        bs.close();

        byte[] digest = md.digest();

        StringBuilder sb = new StringBuilder();
        for(byte bite : digest){
            sb.append(String.format("%02x", bite & 0xff));
        }
        return sb.toString();
    }
}
