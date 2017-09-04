package pages;

import database.DataUploader;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;

/**
 * magenta/rest/data/update - получает файл для обновления базы данных
 */
@Path("/data")
public class DataUpdate{

    @POST
    @Path("/update")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(File file) {

        File tempFile = new File("temp.xml");
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))){
                int count = 0;
                while(true) {
                    String line = reader.readLine();
                    if(count++ <= 4) continue;
                    if(!reader.ready()) break;
                    writer.write(line + System.getProperty("line.separator"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(DataUploader.upload(tempFile)){
            return Response.status(200).build();
        }
        return Response.status(400).build();
    }


}