# FileUploadDownloadPoc

FileUploadDownloadPoc is a **Spring Boot** application that demonstrates storing and retrieving files using an **H2 Database**. This is a **Proof of Concept (PoC)** and is not intended for production use.

## Features

- **File Upload:** Upload files (images, PDFs, Word documents) directly to the H2 database.
- **File Retrieval:** Retrieve files using a file ID.
- **Spring Boot & H2 Integration:** Uses an in-memory H2 database for temporary storage.

## Why Storing Files in a Database is NOT Recommended

Storing files (binary data) directly in a relational database is **not a best practice**, especially for high-traffic applications. Here’s why:

- **Performance Issues:** Databases are optimized for structured data, not large binary objects.
- **Increased DB Load:** Large files increase database size, slowing down queries and backups.
- **Scaling Issues:** Harder to distribute files across multiple servers efficiently.

### When is it Acceptable?
- Only recommended for **low-traffic applications** or **small-scale proof of concepts**.


## Best Practices for File Storage

- **Store files in a server’s file system** and save the file path in the database.
- **Use cloud storage buckets** (Recommended for scalability and reliability), e.g., AWS S3, Google Cloud Storage, or Azure Blob Storage.

## API Endpoints

### File Operations

| Method | Endpoint          | Description             |
|--------|------------------|-------------------------|
| POST   | `/upload`        | Upload a file           |
| GET    | `/download/{id}` | Retrieve a file by ID   |

## Technologies Used

- **Spring Boot**
- **H2 Database** (In-Memory for PoC)
- **Spring Data JPA**





## Changing Content-Disposition: `inline` vs. `attachment`

When serving a file in a Spring Boot application, you can control how the browser handles it using the **`Content-Disposition`** header.

### 1️⃣ **Setting Content-Disposition in Spring Boot**
You can set `Content-Disposition` 

```java
@GetMapping("/download/{id}")
public ResponseEntity<byte[]> downloadFile(@PathVariable Long id){
                                       
    FileEntity file = fileService.getFileById(id);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.parseMediaType(file.getFileType()));
    headers.setContentDisposition(ContentDisposition.builder("inline")
            .filename(file.getFileName())
            .build());

    return ResponseEntity.ok()
            .headers(headers)
            .body(file.getData());
}
```
### 2️⃣ **Difference Between `inline` and `attachment`**
#### **📄 `inline` (Direct Display in Browser)**
```http
Content-Disposition: inline; filename="document.pdf"
```
- The browser **tries to display** the file instead of downloading it.
- Works well for images (`.jpg`, `.png`), PDFs (`.pdf`), and text files (`.txt`).
- Example: If you request a PDF with `inline`, the browser opens it in the built-in PDF viewer.

#### **📥 `attachment` (Forces Download)**
```http
Content-Disposition: attachment; filename="document.pdf"
```
- Forces the browser to **prompt for download** instead of displaying it.
- Works for all file types.
- Example: When clicking a download link, the browser asks, "Do you want to save this file?"





## H2-Console




![image](https://github.com/user-attachments/assets/7025137a-d722-4fbc-a696-44ac06b2c8d2)


## Swagger


![image](https://github.com/user-attachments/assets/2f56f0f5-be7c-4d46-b06f-487ad722fffc)
![image](https://github.com/user-attachments/assets/4cd53a62-aefc-4fcd-8ea9-965d83cfbfcb)


## POSTMAN

![image](https://github.com/user-attachments/assets/5ab3135e-9a46-491c-9bfd-5816d52f136d)


