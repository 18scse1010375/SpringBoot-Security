package com.springrest.springrest.Helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component

public class FileUploadHelper {
	public final String Upload_dir="C:\\Users\\asnghal\\Downloads\\spring-rest\\spring-rest\\src\\main\\resources\\static\\Image";
	
	public boolean uploadFile(MultipartFile file) {
		
		 boolean f=false;
		 try {
			 
			 InputStream is=file.getInputStream();
			 byte data[]=new byte[is.available()];
			 is.read(data);
			 
			 //write the data
			 FileOutputStream fos=new FileOutputStream(Upload_dir + File.separator + file.getOriginalFilename() );
			 fos.write(data);
			 
			 fos.flush();
			 fos.close();
			 f=true;
			 
			 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		 return f;
		
	}

}
