package com.example.shop.services;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Service
public class FirebaseService {
    private Storage storage = StorageOptions.getDefaultInstance().getService();
    public String save(MultipartFile multipartFile) throws Exception {
        String imageName = String.valueOf(System.currentTimeMillis());
        BlobId blobId = BlobId.of("greywoolfshop.appspot.com", imageName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(multipartFile.getContentType()).build();
        storage = StorageOptions.newBuilder()
                .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream("greywoolfshop-firebase-adminsdk-u3zz4-216df10f80.json")))
                .build()
                .getService();
        storage.create(blobInfo, multipartFile.getInputStream());
        return imageName;
    }

    public String getUrl(String filename){
        return "https://firebasestorage.googleapis.com/v0/b/greywoolfshop.appspot.com/o/"+filename+"?alt=media&token=0086ff65-7396-431c-8c34-31ae3849317a";
    }
}
